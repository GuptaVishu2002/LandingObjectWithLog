import java.io.*;

public class LaunchAndLandWithLogAdv {

	public static double _y_releasepoint;
	public static double _y_braking_point;
	

	public static int CheckP(double x_target ,double y_releasepoint, double y_braking_point)
	{
        double x, y, x_speed, y_speed;
        double y_brakepower,y_brakefuel;
        double x_launchpower, y_launchpower, y_launchfuel;
        double r;
        double g;

        g = -9.80665;
        
        x = 0;
        y = 0;
        
        y_brakepower = 50;
        y_launchpower = 25;
        x_launchpower = 4;
        
        y_brakefuel = 200;
        y_launchfuel = 300;
        
        x_speed = 0;
        y_speed = 0;
 
        r = 100000;
        while( y >= 0 ) {
            y_speed += g/r;
            if( y_launchfuel > 0 ) {
                x_speed += x_launchpower/r;
                y_speed += y_launchpower/r;
                y_launchfuel -= 1.0/r;
                if( y > y_releasepoint ) {
                    y_launchfuel = 0; 
                   
                }
            }else if( y_brakefuel > 0 ) {
                if( y < y_braking_point && y_speed < -2.0 ) {
                    y_speed += y_brakepower/r;
                    y_brakefuel -= 1.0/r;
                }
            }
            x += x_speed/r;
            y += y_speed/r;            
        }
        
        if( Math.abs(x - x_target) <= 10) {

        	return 0;
		}
        else{
        	return ((x - x_target) > 0) ? 1 : -1; 
        }
	}

	public static boolean CalcP(double x_target)
	{
		for(double current_release_point = 10; current_release_point<100000; current_release_point+=10){
			double est_braking_point = CalcBrakingP(x_target, current_release_point, 0, current_release_point);	
			if(est_braking_point > 0){
				_y_releasepoint = current_release_point;
				_y_braking_point = est_braking_point;
				return true;
			}
		}
		
		return false;
	}

	public static double CalcBrakingP(double x_target, double est_y_releasepoint, double y_braking_point1, double y_braking_point2)
	{
		if(y_braking_point1 > y_braking_point2)
			return -1;
			
		double pivot_y_braking_point = (y_braking_point1 + y_braking_point2)/2;	
		int X = CheckP(x_target, est_y_releasepoint, pivot_y_braking_point);
		if( X == 0 )
			return pivot_y_braking_point;

		if( X == -1){
			return CalcBrakingP(x_target, est_y_releasepoint, y_braking_point1, pivot_y_braking_point - 1);
		}else{
			return CalcBrakingP(x_target, est_y_releasepoint, pivot_y_braking_point + 1, y_braking_point2);
		}
	}
	
    public static void main(String args[]) {
		double x_target;
		String buf;

		try{

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter Target Distance");
			buf = br.readLine();
			x_target = Double.parseDouble(buf);
			
			if(CalcP(x_target)){
				System.out.println("releasepoint is:" + _y_releasepoint);
				System.out.println("braking_point is:" + _y_braking_point);
			}
			else
				System.out.println("error");
				
			br.close();			
		}
		catch(Exception e){
			System.out.print("Error:" + e);
		}
        
        return;
    }
}

