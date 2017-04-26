/*
 * @author Kaushan Fernando-2014288
 */
import java.util.ArrayList;

public class DistancePrint {
   
          int countName=0;
           double cost;
   public  ArrayList<Node> print(ArrayList<Node> Path ,Node[][] grid_area,Node ending,int gridSize,int matrixSize,int ei,int si,String nameOfDType,String lineColor,long startingTime){
               
       
      
        // Checking whether path exists
        if (!(grid_area[ending.x][ending.y].pathDistance == Integer.MAX_VALUE)) {
            //Trace Back the path
            Node current = grid_area[ending.x][ending.y];

            while (current.parentOb != null) {
                Path.add(current.parentOb);
                current = current.parentOb;
            }
            
             
             int y = ending.y;
             int x = (gridSize-1)-ending.x;
             int a=ending.y;
             int b=ending.x+(gridSize-1);
             //check the user entered x value
             if(ei<si){
                 //print the path
               for (Node node :Path) {
            
          //set color according to received value
               if(lineColor.equals("BLUE")){
                              StdDraw.setPenColor(StdDraw.BLUE);
                              StdDraw.line(a,b,node.y, matrixSize - node.x-1);
                        }
                        if(lineColor.equals("ORANGE")){
                              StdDraw.setPenColor(StdDraw.ORANGE);
                              StdDraw.line(a,b,node.y, matrixSize - node.x-1);
                        }
           
                        if(lineColor.equals("GREEN")){
                              StdDraw.setPenColor(StdDraw.GREEN);
                              StdDraw.line(a,b,node.y, matrixSize - node.x-1);
                        }
            
            a = node.y;
            b = matrixSize - node.x-1;
            cost=Path.size();
            
            //print distance of the shortest path
      
               if(nameOfDType.equals("Chebyshev") && countName==0){
                StdOut.println("Chebyshev distance is " + cost);
            countName++;
            }
            if(nameOfDType.equals("Manhatton") && countName==0){
                StdOut.println("Manhatton distance is " + cost);
            countName++;
            }
              if(nameOfDType.equals("Euclidian") && countName==0){
                StdOut.println("Euclidian distance is " + cost);
            countName++;
            }
            
             
        }
                timeDuration(startingTime); //calling method to calculate elapesd time of each type
             
             }else{
             //print the path
        for (Node node : Path) {
                        if(lineColor.equals("BLUE")){
                              StdDraw.setPenColor(StdDraw.BLUE);
                              StdDraw.line(y,x,node.y, matrixSize - node.x-1);
                              
                        }
                        if(lineColor.equals("ORANGE")){
                              StdDraw.setPenColor(StdDraw.ORANGE);
                              StdDraw.line(y,x,node.y, matrixSize - node.x-1);
                        }
           
                        if(lineColor.equals("GREEN")){
                              StdDraw.setPenColor(StdDraw.GREEN);
                              StdDraw.line(y,x,node.y, matrixSize - node.x-1);
                        }
              cost=Path.size();          
             //print distance of the shortest path
      
               if(nameOfDType.equals("Chebyshev") && countName==0){
                StdOut.println("Chebyshev distance is " + cost);
            countName++;
            }
            if(nameOfDType.equals("Manhatton") && countName==0){
                StdOut.println("Manhatton distance is " + cost);
            countName++;
            }
              if(nameOfDType.equals("Euclidian") && countName==0){
                StdOut.println("Euclidian distance is " + cost);
            countName++;
            }
            
          
            y = node.y;
            x = matrixSize - node.x-1;
            
             
        }
             }
             timeDuration(startingTime);
        } 
         
         else 
            //print error message if the program cannot generate the distance line
            System.err.println("No possible path found for " +nameOfDType);
        

   return Path;
      
   }
   // method to calculate elapsed time 
     public void timeDuration(long startingTime){
          
           long endingTime = System.currentTimeMillis();
              long timeDuration = (endingTime - startingTime); 
             StdOut.println("time duration is :" + timeDuration);
      }
      
}
