/*
 * @author Kaushan Fernando-2014288
 */
import java.util.Scanner;

public class PathFindingOnSquaredGrid {
    static Scanner in = new Scanner(System.in);
    
     final static int gridSize=10; //give value for grid size to genarate the grid 
        final static double percolationPercentage = 0.6; //give value for percolation
    	static boolean[][] randomlyGenMatrix = random(gridSize, percolationPercentage);
        
static int obstacleCount=0;
     static int circleCount=0;
    public static boolean[][] flow(boolean[][] open) {
        int N = open.length;
    
        boolean[][] full = new boolean[N][N];
        for (int j = 0; j < N; j++) {
            flow(open, full, 0, j);
        }
    	
        return full;
    }
    
   
    public static void flow(boolean[][] open, boolean[][] full, int i, int j) {
        int N = open.length;
       

        // base cases
        if (i < 0 || i >= N) return;    // invalid row
        if (j < 0 || j >= N) return;    // invalid column
        if (!open[i][j]) return;        // not an open cell
        if (full[i][j]) return;         // already marked as open

        full[i][j] = true;

        flow(open, full, i+1, j);   // down
        flow(open, full, i, j+1);   // right
        flow(open, full, i, j-1);   // left
        flow(open, full, i-1, j);   // up
    }

    // does the system percolate?
    public static boolean percolates(boolean[][] open) {
        int N = open.length;
    	
        boolean[][] full = flow(open);
        for (int j = 0; j < N; j++) {
            if (full[N-1][j]) return true;
        }
    	
        return false;
    }
    
 // does the system percolate vertically in a direct way?
    public static boolean percolatesDirect(boolean[][] open) {
        int N = open.length;
    	
        boolean[][] full = flow(open);
        int directPerc = 0;
        for (int j = 0; j < N; j++) {
        	if (full[N-1][j]) {
        		
        		directPerc = 1;
        		int rowabove = N-2;
        		for (int i = rowabove; i >= 0; i--) {
        			if (full[i][j]) {
        				
        				directPerc++;
        			}
        			else break;
        		}
        	}
        }
    	
     
        if (directPerc == N) return true; 
        else return false;
    }
    
    // draw the N-by-N boolean matrix to standard draw
    public static void show(boolean[][] a, boolean which) {
        int N = a.length;
        
        StdDraw.setXscale(-1, N);;
        StdDraw.setYscale(-1, N);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (a[i][j] == which){
                      
                	StdDraw.square(j, N-i-1, .5);
                                } else {
                     obstacleCount++;
                    StdDraw.filledSquare(j, N-i-1, .5);
                }
    }

    // draw the N-by-N boolean matrix to standard draw, including the points A (x1, y1) and B (x2,y2) to be marked by a circle
    public static void show(boolean[][] a, boolean which, int x1, int y1, int x2, int y2) {
        int N = a.length;
        StdDraw.setXscale(-1, N);;
        StdDraw.setYscale(-1, N);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++)
                if (a[i][j] == which)
                	if ((i == x1 && j == y1) ||(i == x2 && j == y2)) {
                		StdDraw.circle(j, N-i-1, .5);
                                circleCount++;
                	}
                	else StdDraw.square(j, N-i-1, .5);
                else StdDraw.filledSquare(j, N-i-1, .5);
        }
        if(!( circleCount==2)){
            System.err.println("You have entered wrong cordinates");
            System.exit(0);
        }
    }
    
    // return a random N-by-N boolean matrix, where each entry is
    // true with probability p
    public static boolean[][] random(int N, double p) {
        boolean[][] a = new boolean[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                a[i][j] = StdRandom.bernoulli(p);
        return a;
    }
    public static void menu(){
         //get user inputs for start and end cordinates
    	
       StdOut.println("Enter x cordinate for starting point: ");
        int Ax = in.nextInt();
        
        StdOut.println("Enter y cordinate for starting point ");
        int Ay = in.nextInt();
        
        StdOut.println("Enter x cordinate for ending point ");
        int Bx = in.nextInt();
        
        StdOut.println("Enter y cordinate for ending point ");
        int By = in.nextInt();
   
        
    show(randomlyGenMatrix, true, Ax, Ay, Bx, By); //draw the circle according to cordinates that user entered
    
     //choose the distance type    
    StdOut.println("Enter 1 for Chebyshev Distance ");
     StdOut.println("Enter 2 for Euclidian Distance ");
      StdOut.println("Enter 3 for Manhatton Distance ");
        int selectedOption = in.nextInt();
        
        switch (selectedOption) {
            case 1:   
        //create object of  ChebyshevDistance java class and pass the values calling methods to generate the shortest path
      ChebyshevDistance cbDistance = new ChebyshevDistance();        
    cbDistance.getChebyshevDistance(randomlyGenMatrix, Ax, Ay, Bx, By,gridSize);
                     break;
           case 2:   
         //create object of  EuclidianDistance java class and pass the values calling methods to generate the shortest path
       EuclidianDistance euDist = new  EuclidianDistance();
        euDist.getEuclidianDistance(randomlyGenMatrix, Ax, Ay, Bx, By,gridSize);
                     break;
           case 3:   
         //create object of  ManhattonDistance java class and pass the values calling methods to generate the shortest path
        ManhattonDistance mnDist = new  ManhattonDistance();
       mnDist.getManhattonDistance(randomlyGenMatrix, Ax, Ay, Bx, By,gridSize);
       
                     break;
           
            default:  System.err.println("Invalid Distance Type");
                       System.exit(0);
                     break;
        }
    
    }
    
    // test client
    public static void main(String[] args) {
        
       StdArrayIO.print(randomlyGenMatrix); // print the grid as 0 and 1
    	show(randomlyGenMatrix, true);//Generate the grid
    	
        StdOut.println("Number of Obstacle are :" +obstacleCount); //print the number of obstacles
    	
    	Stopwatch timerFlow = new Stopwatch();
         StdOut.println("Elapsed time = " + timerFlow.elapsedTime()); //print elapsed time 
    	       menu();// calling method menu to enter values for user
    }
}
