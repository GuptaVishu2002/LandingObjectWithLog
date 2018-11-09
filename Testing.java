import java.io.*;
import java.lang.Math.*;
 
    public class Testing {
        public static void main(String args[]) {
            double x, y, x_speed, y_speed, x_initial_speed, x_wind_speed1,y_final_speed;
            double y_power,y_fuel,y_braking_point,x_landing;
            double r;
            double g;
            int c;
            double bTime,sTime,aTime;
            
            g = -9.80665;
            
            x = 0;
            y = 100000;
            x_initial_speed = 800;
            x_wind_speed1 = 200;
            y_power = 50;
            y_fuel = 0;
            
            y_braking_point = 20000;
            
            x_speed = x_initial_speed;
            y_speed = 0;
            
            r = 100000;
            sTime = (-y_speed - Math.sqrt( Math.pow(y_speed,2) - 2*g*y))/g;
            aTime = (-y_speed - Math.sqrt( Math.pow(y_speed,2) - 2*g*y_braking_point))/g;
           
            String buf;
            try{
            
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
				System.out.print("1.Release Normally \n 2.Postpond braking time \n 3. Prepond braking time");
				buf = br.readLine();
				int option = Integer.parseInt(buf);
				
				if(option==1){

            	System.out.print("Enter Landing point");
            	buf = br.readLine();
            	x_landing = Double.parseDouble(buf);
            	
            	System.out.print("Enter Force");
            	buf = br.readLine();
            	y_power = Double.parseDouble(buf);
            	
            	System.out.print("Enter Final Velocity(greater then 3)");
            	buf = br.readLine();
            	y_final_speed = Double.parseDouble(buf);
		        
		        y_final_speed = (-1)*y_final_speed;
				    
				    
				   /* c = 0;*/
		        while( y >= 0 && x_landing > x) {
		            y_speed += g/r;
		           
		                /* use thruster */
		                if( y_speed < -2.0  ) {
		                	y_braking_point += 1.0/r;
		                    y_speed += y_power/r;
		                    y_fuel += 1.0/r;
		                }
		            
		            x += x_speed/r;
		            y += y_speed/r;
		 
		           /* c++;
				        if( c >= r ) {
				            System.out.print("" + x + "\t" + y + "\n");
				            c = 0;
				        }*/
				    }
				    /*System.out.print("" + x + "\t" + y + "\n");*/
				   System.out.print("Braking Point = "+y_braking_point+"\n");
		       	   System.out.print("Fuel = "+ y_fuel + "\n"); 
				}
				if(option==2){
					System.out.print("Enter Landing point");
		        	buf = br.readLine();
		        	x_landing = Double.parseDouble(buf);
		        	
		        	System.out.print("Enter Force");
		        	buf = br.readLine();
		        	y_power = Double.parseDouble(buf);
		        	
		        	System.out.print("Enter Final Velocity(greater then 3)");
		        	buf = br.readLine();
		        	y_final_speed = Double.parseDouble(buf);
				    
				    y_final_speed = (-1)*y_final_speed;
		        	
		        	System.out.print("Enter Postponed Time");
		        	buf = br.readLine();
		        	bTime = Integer.parseInt(buf);
			
				    /* c = 0;*/
				    while( y >= 0 && x_landing > x) {
				        y_speed += g/r;
				       
				            /* use thruster */
				            if( y_speed < -2.0  ) {
				            	y_braking_point += 1.0/r;
				                y_speed += y_power/r;
				                y_fuel += 1.0/r;
				            }
				        
				        x += x_speed/r;
				        y += y_speed/r;
		 
		           /* c++;
				        if( c >= r ) {
				            System.out.print("" + x + "\t" + y + "\n");
				            c = 0;
				        }*/
				    }
				    /*System.out.print("" + x + "\t" + y + "\n");*/
				    
				    double Final1 = y_braking_point-y_speed*bTime;
				    
				    System.out.print("Braking Point = "+Final1+"\n");
		       	   System.out.print("Fuel = "+ y_fuel + "\n"); 
				}
				
				if(option==3){
					System.out.print("Enter Landing point");
		        	buf = br.readLine();
		        	x_landing = Double.parseDouble(buf);
		        	
		        	System.out.print("Enter Force");
		        	buf = br.readLine();
		        	y_power = Double.parseDouble(buf);
		        	
		        	System.out.print("Enter Final Velocity(greater then 3)");
		        	buf = br.readLine();
		        	y_final_speed = Double.parseDouble(buf);
				    
				    y_final_speed = (-1)*y_final_speed;
		        	
		        	System.out.print("Enter Preponed Time");
		        	buf = br.readLine();
		        	bTime = Integer.parseInt(buf);
				  
				    /* c = 0;*/
				    while( y >= 0 && x_landing > x) {
				        y_speed += g/r;
				       
				            /* use thruster */
				            if( y_speed < -2.0  ) {
				            	y_braking_point += 1.0/r;
				                y_speed += y_power/r;
				                y_fuel += 1.0/r;
				            }
				        
				        x += x_speed/r;
				        y += y_speed/r;
		 
		         	  /* c++;
				        if( c >= r ) {
				            System.out.print("" + x + "\t" + y + "\n");
				            c = 0;
				        }*/
				    }
				    /*System.out.print("" + x + "\t" + y + "\n");*/
				    
				    double Final2 = y_braking_point+y_speed*bTime
				    
				     System.out.print("Braking Point = "+Final2+"\n");
		       	     System.out.print("Fuel = "+ y_fuel + "\n"); 
				    
				}
				
            } catch(Exception e){
            	System.err.print("Error:" + e);
            }
            return;
        }
    }
