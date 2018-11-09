    public class Test2 {
        public static void main(String args[]) {
            double x, y, x_speed, y_speed, x_initial_speed, x_wind_speed1;
            double y_power,y_fuel,y_braking_point;
            double r;
            double g;
            int c;
            MyDisplayWindow mdw = new MyDisplayWindow();
            
            g = -9.80665;
            
            x = 0;
            y = 100000;
            x_initial_speed = 800;
            x_wind_speed1 = 200;
            y_power = 50;
            y_fuel = 200;
            
            y_braking_point = 20000;
            
            x_speed = x_initial_speed;
            y_speed = 0;
            
            r = 100000;
     
            mdw.setObjectPosition1((int)(x/1000),(int)(y/1000));
            mdw.setObjectVisible(true);
            
            c = 0;
            while( y >= 0 ) {
                y_speed += g/r;
                
                if( y_fuel > 0 ) {
                    /* use thruster */
                    if( y < y_braking_point && y_speed < -2.0 ) {
                        y_speed += y_power/r;
                        y_fuel -= 1.0/r;
                    }
                }
                
                x += x_speed/r;
                y += y_speed/r;
     
                c++;
                if( c >= r/100 ) {
                    mdw.setObjectPosition1((int)(x/1000),(int)(y/1000));
                    mdw.repaint();
                    c = 0;
                }
            }
     
            mdw.setObjectPosition1((int)(x/1000),(int)(y/1000));
            mdw.repaint();
            
            System.out.print("estimated distance = "+x+"\n");
            System.out.print("landing y speed = "+ y_speed + "\n");
            System.out.print("rest fuel = "+ y_fuel + "\n");
            
            return;
        }
    }
    class MyDisplayWindow extends java.awt.Frame {
        int x1, y1;
        int x2, y2;
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
        public void setObjectPosition1(int x, int y) {
            // NOTE: (0,0) is at the upper-left corner
            x1 = x; // no adjustment for x axis
            y1 = 300-y*3; // adjust direction, origin, and scale
            // for debug
            // System.out.print("x:"+x1+",y:"+y1+"\n");
        }
        public void paint(java.awt.Graphics g) {
            g.setColor(new java.awt.Color(0,255,0));
            g.fillRect(x1,y1,objectSize,objectSize/2);
        }
    }
