/*Main class */

 public class Fractal{
    public static void main(String[] args) {
		Mandelbrot mandelbrot;
		Julia julia;
	    int n=args.length -1;   //get the number of input of arguments
        double ca,cb;
		double xLeft,xRight,yBottom,yTop;
        String set = args[0];
		int maxIterations;
		
		//check whether there is at least one argument
		if(args.length>0){
			
			//check whether the set needed to be implemented is either julia or Mandelbrot
			if(set.equalsIgnoreCase("Julia")){
				if(n==0){     //n=0 ; the default condition
					ca = -0.4;
					cb = 0.6;
					maxIterations=1000;
					julia=new Julia(ca,cb,maxIterations);  //create an object in Julia class
				}
				else if(n==2){    // number of maximum iterations are not given as an input
					maxIterations=1000;
					//change the argument type into double
					ca = Double.parseDouble(args[1]);   
					cb = Double.parseDouble(args[2]);
					julia=new Julia(ca,cb,maxIterations);
					
				}
				else if(n==3){    // all three input arguments are given
					ca = Double.parseDouble(args[1]);
					cb = Double.parseDouble(args[2]);
					maxIterations=Integer.parseInt(args[3]);  //change the argument type into integer
				
					julia=new Julia(ca,cb,maxIterations);
				}
			
				else{
					System.out.println("Input correct argumnets");
				}
			}
			else if(set.equalsIgnoreCase("Mandelbrot")){
				if(n==0){    //n=0 ; the default condition
					xLeft = -1.0;
					xRight = 1.0;
					yBottom = -1.0;
					yTop = 1.0;
					maxIterations =1000;
					mandelbrot=new Mandelbrot(xLeft,xRight,yBottom,yTop,maxIterations);  //create an object in Mandelbrot class
				}
			
				else if(n==4){   //number of maximum iterations are not given as an input
					xLeft=Double.parseDouble(args[1]);
					xRight=Double.parseDouble(args[2]);
					yBottom=Double.parseDouble(args[3]);
					yTop=Double.parseDouble(args[4]);
					maxIterations=1000;
			  
				mandelbrot= new Mandelbrot(xLeft,xRight,yBottom,yTop,maxIterations);
				}
				
				else if(n==5){   //all five input arguments are given
					
					xLeft=Double.parseDouble(args[1]);
					xRight=Double.parseDouble(args[2]);
					yBottom=Double.parseDouble(args[3]);
					yTop=Double.parseDouble(args[4]);
					maxIterations=Integer.parseInt(args[5]);
					mandelbrot= new Mandelbrot(xLeft,xRight,yBottom,yTop,maxIterations);
		
				}
				else{
					System.out.println("Input correct argumnets");  //print an error message when the input arguments are not in the correct manner
				}
		
			}
			else{
				System.out.println("Input correct argumnets");     //print an error message when the input arguments are not in the correct manner
			}
		
		
		}
		else{
			System.out.println("Input correct argumnets");         //print an error message when the input arguments are not in the correct manner
			}
	}
 }