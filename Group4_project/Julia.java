import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import static java.lang.Math.sin;

/* class for Julia set implementation */
public class Julia extends JFrame {
 
    
    private BufferedImage Image;
    private double z_real, z_img, c_real, c_img, tmp;
	static double ca,cb;
    static double xLeft = -1.0;
    static  double xRight = 1.0;
    static double yBottom = -1.0;
    static double yTop = 1.0;
    static int maxIterations;
     
    Canvas canvas;
     

    static final int WIDTH = 800;
    static final int HEIGHT = 800;

 //constructor for the Julia class
    public Julia(double ca, double cb,int maxIterations) {
                                        
            this.ca = ca;
			this.cb = cb;
			this.maxIterations = maxIterations;
			 
            setGUI();  //call SetGUI method to set the GUI properties
            showCanvas();   //Create the canvas
             
			 
	        Thread thread;
            int j;
			for( j=0;j<4;j++){
				thread = new Thread(new JuliaThread(j*200,(j+1)*200)); //calling the JuliaThread class for creating threads
				thread.start();
				try{
					thread.join();
				}
				catch (InterruptedException ex){
					System.out.println("error");
				}
			}


    }
	
	
	//Implementing the JuliaThread class from the Runnable class
	class JuliaThread implements Runnable{
            
		int height1,height2;
          
		//the constructor for the JuliaThread class
		public JuliaThread(int height1,int height2){
			//selecting hieghts for the thread to be created
			this.height1=height1;
			this.height2=height2;
		}
		//run() method to create the threads for the canvas implementation 
		public void run(){
			
        //map the points in canvas to a complex plane and filling the colors
		 for(int x=height1;x< height2;x++){
			 
            for(int y=0; y < WIDTH; y++){
               
                 double precentX=(double)(xRight - xLeft)/800;
                  double precentY=(double) (yTop - yBottom)/800;
				  
                  c_real= xLeft + (precentX *y) ;  //calculate the x position of a particular point
                  c_img = yBottom+(precentY* x) ;      //calculate the y position of a particular point
				  
                  z_real = c_real;
		          z_img = c_img;
								 
                  int iter ;
				   
				   //compare the number of iterations with maximum number of iterations to get the new position of the point
                  for (iter=0; iter < maxIterations && z_real * z_real + z_img * z_img < 4  ; iter++) {
                        tmp = z_real;
                        z_real = z_real*z_real -z_img*z_img + ca;
                        z_img = 2.0 * tmp * z_img + cb;
                      
                    }
                 
				  //fill color to the pixels
                  int pointColor = setColor(iter);
                  Image.setRGB(x, y, pointColor);
                }
            }
                  canvas.repaint();   //repaint the canvas to get the new look
		}
	}
	
	
//display the canvas
    private void showCanvas(){
        canvas= new Canvas();
        Image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
        canvas.setVisible(true);
        this.add(canvas,BorderLayout.CENTER);
        
    }

	
    

     //create colors to fill the pixels
    private int setColor(int iterCount){
        
             
        int color =0b011010000000000000000000;   //color code
        int mask  =0b000000000000010101110111;
        int shiftAmount = iterCount / 11;
        
        if(iterCount == maxIterations ){
            
        return Color.BLACK.getRGB();}

        return color | (mask << shiftAmount) ;
        
    }
	
	//create the canvas
    private class Canvas extends JPanel {


         public Canvas(){}
            
            
         @Override
         public Dimension getPreferredSize(){
            return new Dimension(WIDTH,HEIGHT);
            
         }
         @Override
         public void paint(Graphics g) {
            g.drawImage(Image, 0, 0, this);
         }
    }
	
	//method to set GUI properties
    public void setGUI(){
               
               this.setTitle("Fractal Explorer");  //set the title of the panel
               this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//method which determines how the window open and close
               this.setSize(WIDTH,HEIGHT); //set the size of the window
               this.setResizable(true);   //select whether the image window is resizable or not
               this.setLocationRelativeTo(null);    //get the reference value
               this.setVisible(true);     //set the window visible 
            }   

	   }