    import java.io.*;
     
    class Ex3_23 {
        public static void main(String arts[]) {
     		int total, payment, change;
        	int c1=0,c2=0,c3=0,c4=0,c5=0,c6=0,c7=0,c8=0,c9=0;
        	String buf;
       	 	try{
          	 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            	System.out.print("Enter Total");
            	buf = br.readLine();
            	total = Integer.parseInt(buf);
            	
            	System.out.print("Enter Payment");
            	buf = br.readLine();
            	payment = Integer.parseInt(buf);
            	
            	change = total - payment;
            	
            	while(change>10000){
            		c1++;
					change = change - 10000;
            	}
            	while(change>5000){
            		c2++;
					change = change - 5000;
            	}
            	while(change>1000){
            		c3++;
					change = change - 1000;
            	}
            	while(change>500){
            		c4++;
					change = change - 500;
            	}
            	while(change>100){
            		c5++;
					change = change - 100;
            	}
            	while(change>50){
            		c6++;
					change = change - 50;
            	}
            	while(change>10){
            		c7++;
					change = change - 10;
            	}
            	while(change>5){
            		c8++;
					change = change - 5;
            	}
            	while(change>1){
            		c9++;
					change = change - 1;
            	}
            	System.out.print("    " + c1 + " x 10000 yen coin\n");
				System.out.print("    " + c2 + " x 5000 yen coin\n");
				System.out.print("    " + c3 + " x 1000 yen coin\n");
				System.out.print("    " + c4 + " x 500 yen coin\n");
				System.out.print("    " + c5 + " x 100 yen coin\n");
				System.out.print("    " + c6 + " x 50 yen coin\n");
				System.out.print("    " + c7 + " x 10yen coin\n");
				System.out.print("    " + c8 + " x 5 yen coin\n");
				System.out.print("    " + c9 + " x 1 yen coin\n");

           	
           	} catch(Exception e){
            	System.err.print("Error:" + e);
            }
        	
        }
    }
