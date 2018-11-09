import java.io.*;

public class Test3 {
	//定数
	public static final double ERROR_VALUE_X = 10;				//落ちるx座標の許容誤差
	public static final double GAP = 1;							//2分探索する際の中心値からのずれ
	public static final double R = 10000;						//時間分解能
	public static final double RELEASE_POINT_INTERVAL = 10;		//線形探索する際のリリースポイントのインターバル

	public static double _y_releasepoint;
	public static double _y_braking_point;
	
	//狙ったところに降りられるかを判定する
	//-1:狙った座標より左方向, 0:範囲内, 1:狙った座標より右方向
	public static int CheckPoint(double x_target ,double y_releasepoint, double y_braking_point)
	{
        double x, y, x_speed, y_speed;
        double y_brakepower,y_brakefuel;
        double x_lounchpower, y_lounchpower, y_lounchfuel;
        double r;
        double g;

        g = -9.80665;
        
        x = 0;
        y = 0;
        
        y_brakepower = 50;
        y_lounchpower = 25;
        x_lounchpower = 4;
        
        y_brakefuel = 200;
        y_lounchfuel = 300;
        
        x_speed = 0;
        y_speed = 0;
 
        r = R;
        while( y >= 0 ) {
            y_speed += g/r;
            if( y_lounchfuel > 0 ) {
                x_speed += x_lounchpower/r;
                y_speed += y_lounchpower/r;
                y_lounchfuel -= 1.0/r;
                if( y > y_releasepoint ) {
                    y_lounchfuel = 0; 
                   
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

        	//デバッグ
			/*System.out.println("x_target:" + x_target);
			System.out.println("y_releasepoint:" + y_releasepoint);
			System.out.println("y_braking_point:" + y_braking_point);
			System.out.println("x:" + x);
			System.out.println("y:" + y);
			System.out.println("x_speed:" + x_speed);
			System.out.println("y_speed:" + y_speed);
			System.out.println("");*/
        
        if( Math.abs(x - x_target) <= ERROR_VALUE_X) {

        	return 0;
		}
        else{
        	return ((x - x_target) > 0) ? 1 : -1; 
        }
	}

	//リリースポイントは線形探索、ブレイキングポイントは二分探索を行う		
	public static boolean CalcPoints(double x_target)
	{
		for(double current_release_point = RELEASE_POINT_INTERVAL; current_release_point<100000; current_release_point+=RELEASE_POINT_INTERVAL){//100000という値自体に意味はない
			double est_breaking_point = CalcBreakingPoint(x_target, current_release_point, 0, current_release_point);	//物体の最大の高さとrelease_pointはあまり変わらないので、暫定的にbraking_pointHにrelease_pointを入れる
			if(est_breaking_point > 0){
				_y_releasepoint = current_release_point;
				_y_braking_point = est_breaking_point;
				return true;
			}
		}
		
		return false;
	}
	
	//再帰処理を使った二分探索でy_braking_pointの値を求める
	//見つかったらその時のbreaking_pointを返し、見つからなかったら-1を返す
	public static double CalcBreakingPoint(double x_target, double est_y_releasepoint, double y_braking_pointL, double y_braking_pointH)
	{
		if(y_braking_pointL > y_braking_pointH)
			return -1;
			
		double pivot_y_braking_point = (y_braking_pointL + y_braking_pointH)/2;	//LとHの中心値
		int rtn = CheckPoint(x_target, est_y_releasepoint, pivot_y_braking_point);
		if( rtn == 0 )
			return pivot_y_braking_point;

		if( rtn == -1){
			return CalcBreakingPoint(x_target, est_y_releasepoint, y_braking_pointL, pivot_y_braking_point - GAP);
		}else{
			return CalcBreakingPoint(x_target, est_y_releasepoint, pivot_y_braking_point + GAP, y_braking_pointH);
		}
	}
	
    public static void main(String args[]) {
		double x_target;
		String buf;

		try{
			//降りる座標の入力
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.err.print("着地するx座標を入力してください:");
			buf = br.readLine();
			x_target = Double.parseDouble(buf);
			
			if(CalcPoints(x_target)){
				System.out.println("y_releasepoint:" + _y_releasepoint);
				System.out.println("y_braking_point:" + _y_braking_point);
			}
			else
				System.out.println("error");
				
			br.close();			
		}
		catch(Exception e){
			System.out.print("Error:" + e);
		}
		
		
		//デバッグ
/*        double x, y, x_speed, y_speed;
        double y_brakepower,y_brakefuel;
        double x_lounchpower, y_lounchpower, y_lounchfuel;
        double r;
        double g;

        g = -9.80665;
        
        x = 0;
        y = 0;
        
        y_brakepower = 50;
        y_lounchpower = 25;
        x_lounchpower = 4;
        
        y_brakefuel = 200;
        y_lounchfuel = 300;
        
        x_speed = 0;
        y_speed = 0;
 
        r = R;
        while( y >= 0 ) {
            y_speed += g/r;
            if( y_lounchfuel > 0 ) {
                x_speed += x_lounchpower/r;
                y_speed += y_lounchpower/r;
                y_lounchfuel -= 1.0/r;
                if( y > _y_releasepoint ) {
                    y_lounchfuel = 0; 
                }
            }else if( y_brakefuel > 0 ) {
                if( y < _y_braking_point && y_speed < -2.0 ) {
                    y_speed += y_brakepower/r;
                    y_brakefuel -= 1.0/r;
                }
            }
            x += x_speed/r;
            y += y_speed/r;
            System.out.print("" + x + "\t" + y + "\n");
        }
        System.out.print("" + x + "\t" + y + "\n");
        System.out.print("estimated distance = "+x+"\n");
        System.out.print("landing y speed = "+ y_speed + "\n");*/
        
        return;
    }
}

