import java.io.*;

public class FallAndCatchWindow{
        public static void main(String args[]) {
            double x_gun, y_gun,x_monkey,y_monkey;
            double x_gun_speed,y_gun_speed,x_monkey_speed,y_monkey_speed;
            double r;
            double g;
            int c;
        	MyDisplayWindow mdw = new MyDisplayWindow();
            
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
            	
            	mdw.setObjectPosition1(x_monkey,y_monkey);
            	mdw.setObjectPosition2(x_gun,y_gun);
        		mdw.setObjectVisible(true);
        
      		    c = 0;
           
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
          	 
          	   c++;
		        if( c >= r/100 ) {
		            mdw.setObjectPosition1(x_monkey,y_monkey);
		            mdw.setObjectPosition2(x_gun,y_gun);
		            mdw.repaint();
		            c = 0;
		        }
            
            }
            
           	System.out.println("物体をキャッチできませんでした");
           	
           	} catch(Exception e){
            	System.err.print("Error:" + e);
            }
            
            return;
        }
    }
  class MyDisplayWindow extends java.awt.Frame {
    double max_x, max_y;
    double x1, y1;
    double x2, y2;
    int objectSize = 16;
    boolean objectVisible = false;
    public MyDisplayWindow() {
        super();
        setSize(400,400);
        setVisible(true);
    }
    public void setObjectVisible(boolean b) {
        objectVisible = b;
    }
    public void setMaxPoint(double x, double y){
      	max_x = x;
        max_y = y;
    }
    public void setObjectPosition1(double x, double y) {

        x1 = x;
        y1 = y; 

    }
    public void setObjectPosition2(double x, double y) {

        x2 = x;
        y2 = y; 

    }
    public void paint(java.awt.Graphics g) {
    	int x_gun_window,y_gun_window,x_monkey_window,y_monkey_window;
        	

        x_gun_window = (int)(x1/max_x * 400);
        x_monkey_window = (int)(x2/max_x * 400);
        y_gun_window = (int)((max_y - y1)/max_y * 400);
        y_monkey_window = (int)((max_y - y2)/max_y * 400);
    
        g.setColor(new java.awt.Color(0,255,0));
        g.fillRect(x_gun_window,y_gun_window,objectSize,objectSize/2);
        
        g.setColor(new java.awt.Color(0,0,255));
        g.fillRect(x_monkey_window,y_monkey_window,objectSize,objectSize/2);
    }
}
