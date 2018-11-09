import java.io.*;

public class LaunchAndLandWithLog {
    public static void main(String args[]) {
        double x, y, x_speed, y_speed, x_initial_speed, x_wind_speed1;
        double y_brakepower,y_brakefuel,y_braking_point;
        double x_lounchpower, y_lounchpower, y_lounchfuel, y_releasepoint;
        double r;
        double g;
        int c;
        
        g = -9.80665;
        
        x = 0;
        y = 0;
        
        x_initial_speed = 0;
        x_wind_speed1 = 200;
        
        y_brakepower = 50;
        y_lounchpower = 25;
        x_lounchpower = 4;
        
        y_brakefuel = 200;
        y_lounchfuel = 300;
        y_releasepoint =  45000;
        y_braking_point = 15000;
        
        x_speed = x_initial_speed;
        y_speed = 0;
 
        r = 1; //100000;
        c = 0;
        
        String buf;
       	 try{
           BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            	System.out.print("Enter Release Point");
            	buf = br.readLine();
            	y_releasepoint = Integer.parseInt(buf);
            	
            	System.out.print("Enter braking Point");
            	buf = br.readLine();
            	y_braking_point = Integer.parseInt(buf);
            
		    while( y >= 0 ) {
		        y_speed += g/r;
		        if( y_lounchfuel > 0 ) {
		            /* use booster to lounch */
		            x_speed += x_lounchpower/r;
		            y_speed += y_lounchpower/r;
		            y_lounchfuel -= 1.0/r;
		            if( y > y_releasepoint ) {
		                y_lounchfuel = 0; /* released */
		            }
		        }else if( y_brakefuel > 0 ) {
		            /* use brake thruster */
		            if( y < y_braking_point && y_speed < -2.0 ) {
		                y_speed += y_brakepower/r;
		                y_brakefuel -= 1.0/r;
		            }
		        }
		        x += x_speed/r;
		        y += y_speed/r;
		      /*  c++;
		        if( c >= r ) {
		            System.out.print("" + x + "\t" + y + "\n");
		            c = 0;
		        }*/
		    }
		    /*
		    System.out.print("" + x + "\t" + y + "\n");*/
		    
		    System.out.print("estimated distance = "+x+"\n");
		    System.out.print("landing y speed = "+ y_speed + "\n");
		    System.out.print("rest brakefuel = "+ y_brakefuel + "\n");
		  	} catch(Exception e){
            	System.err.print("Error:" + e);
            }
        
        return;
    }
}
