import java.io.*;

public class FallAndCatch{
        public static void main(String args[]) {
            double x_gun, y_gun,x_monkey,y_monkey;
            double x_gun_speed,y_gun_speed,x_monkey_speed,y_monkey_speed;
            double r;
            double g;
            
            g = -9.80665;
            
            y_monkey_speed = 0;
            
            x_gun = 0;
            y_gun = 0;

            
            r = 100000;
            
            String buf;
       	 	try{
          	 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            	System.out.print("Enter Gun Speed");
            	buf = br.readLine();
            	x_gun_speed = Integer.parseInt(buf);
            	
            	System.out.print("Enter Height of Monkey");
            	buf = br.readLine();
            	y_monkey = Integer.parseInt(buf);
            	
            	System.out.print("Enter Distance of Monkey From Gun");
            	buf = br.readLine();
            	x_monkey = Integer.parseInt(buf);
            	
            	y_gun_speed = x_gun_speed * y_gun / x_gun;
           
           	while( y_gun >= 0 && y_monkey >= 0) {
           		y_gun_speed += g/r;
           		y_monkey_speed += g/r;
           	
                x_gun += x_gun_speed/r;
                y_gun += y_gun_speed/r;
                y_monkey += y_monkey_speed;
                       
           if( Math.sqrt( Math.pow( Math.abs( x_monkey - x_gun),2) + Math.pow( Math.abs(y_monkey - y_gun), 2)) <= 1){
				System.out.println("物体をキャッチできました");
				System.out.println("x coordinate of monkey:" + x_monkey);
				System.out.println("y coordinate of monkey:" + y_monkey);
				System.out.println("x coordinate of gun:" + x_gun);
				System.out.println("y coordinate of gun:" + y_gun);
				return;
          	 }
            
            }
            
           	System.out.println("物体をキャッチできませんでした");
           	
           	} catch(Exception e){
            	System.err.print("Error:" + e);
            }
            
            return;
        }
    }
