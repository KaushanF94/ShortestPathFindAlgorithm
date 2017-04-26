/*
 * @author Kaushan Fernando
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class ChebyshevDistance {
    long startingTime = System.currentTimeMillis();
    DistancePrint disPrint3= new DistancePrint();
     String nameOfDType = "Chebyshev";
    String lineColor= "GREEN";
    Node startingPoint;
    Node endingPoint;
    Node[][] grid_area;
    boolean skipVal3 = true;
    public  int matrixSize = 0;
    
    // Horizontal and VerticalDistance
    double horVertDistance = 1.0; 
    
    // Diagonal Distance
    double diagonalDistance = 1.0; 

  

    ArrayList<Node> getChebyshevDistance(boolean[][] metrix, int si, int sj, int ei, int ej,int gridSize) {
             
 
        matrixSize = metrix.length;
  
       startingPoint = new Node(si, sj);
        endingPoint = new Node(ei, ej);
        // The grid that is used to store nodes
        grid_area = new Node[matrixSize][matrixSize];

        // Creating nodes and finding blockedVal cells in metrix and mapping accordingly to our grid
        for (int i = 0; i < matrixSize; ++i) {
            for (int j = 0; j < matrixSize; ++j) {
                grid_area[i][j] = new Node(i, j);
                if (metrix[i][j] == false) {
                    grid_area[i][j].blockedVal = true;
                }	
            }
        }

               findPath();
        ArrayList<Node> path = new ArrayList<>();

          disPrint3.print(path, grid_area, endingPoint, gridSize, matrixSize, ei, si, nameOfDType,lineColor,startingTime);
        return path;
    }
    
          public void findPath(){
         // setting startingPoint pathDistance to 0. 
        // All other nodes will have infinity pathDistance at the beginning
        startingPoint.pathDistance =0;

        // a comparator object to deal with Priority Queue
        Comparator<Node> adjacencyComparator = (left, right) -> {
            if (left.pathDistance > (right.pathDistance)) {
                return 1;
            }
            return -1;
        };

        // Queue to store visiting nodes
        Queue<Node> queueB = new PriorityQueue(matrixSize, adjacencyComparator);

        queueB.add(startingPoint);

        while (queueB.size() > 0) {
            Node current = queueB.remove();
            Node t;

            // Top
            if (current.x - 1 >= 0) {

                // Top Top
                t = grid_area[current.x - 1][current.y];
                if (!t.visitedVal && !t.blockedVal && t.pathDistance > current.pathDistance + horVertDistance) {
                    t.pathDistance = current.pathDistance + horVertDistance;
                    t.parentOb = current;
                    queueB.add(t);
                   
                }

                // Top Left
                if (current.y - 1 > 0) {
                    t = grid_area[current.x - 1][current.y - 1];
                    if (!t.visitedVal && !t.blockedVal && t.pathDistance > current.pathDistance + diagonalDistance) {
                        t.pathDistance = current.pathDistance + diagonalDistance;
                        t.parentOb = current;
                        queueB.add(t);
                        
                    }
                }

                // Top Right
                if (current.y + 1 < matrixSize) {
                    t = grid_area[current.x - 1][current.y + 1];
                    if (!t.visitedVal && !t.blockedVal && t.pathDistance > current.pathDistance + diagonalDistance) {
                        t.pathDistance = current.pathDistance + diagonalDistance;
                        t.parentOb = current;
                        queueB.add(t);
                        
                    }
                }
            }

            // Left
            if (current.y - 1 > 0) {
                t = grid_area[current.x][current.y - 1];
                if (!t.visitedVal && !t.blockedVal && t.pathDistance > current.pathDistance + horVertDistance) {
                    t.pathDistance = current.pathDistance + horVertDistance;
                    t.parentOb = current;
                    queueB.add(t);
                     
                }
            }

            // Right
            if (current.y + 1 < matrixSize) {
                t = grid_area[current.x][current.y + 1];
                if (!t.visitedVal && !t.blockedVal && t.pathDistance > current.pathDistance + horVertDistance) {
                    t.pathDistance = current.pathDistance + horVertDistance;
                    t.parentOb = current;
                    queueB.add(t);
                     
                }
            }
            // Down
            if (current.x + 1 < matrixSize) {

                // Down Down
                t = grid_area[current.x + 1][current.y];
                if (!t.visitedVal && !t.blockedVal && t.pathDistance > current.pathDistance + horVertDistance) {
                    t.pathDistance = current.pathDistance + horVertDistance;
                    t.parentOb = current;
                    queueB.add(t);
                
                }

                // Down Left
                if (current.y - 1 >= 0) {
                    t = grid_area[current.x + 1][current.y - 1];
                    if (!t.visitedVal && !t.blockedVal && t.pathDistance > current.pathDistance + diagonalDistance) {
                        t.pathDistance = current.pathDistance + diagonalDistance;
                        t.parentOb = current;
                        queueB.add(t);
                       
                    }
                }

                // Down Right
                if (current.y + 1 < matrixSize) {
                    t = grid_area[current.x + 1][current.y + 1];
                    if (!t.visitedVal && !t.blockedVal && t.pathDistance > current.pathDistance + diagonalDistance) {
                        t.pathDistance = current.pathDistance + diagonalDistance;
                        t.parentOb = current;
                        queueB.add(t);
                        
                    }
                }
            }
            current.visitedVal = true;
        }

        }

}

