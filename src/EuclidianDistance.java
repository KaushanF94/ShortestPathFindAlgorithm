/*
 * @author Kaushan Fernando-2014288
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;


public class EuclidianDistance {

long startingTime = System.currentTimeMillis();
DistancePrint disPrint2 = new DistancePrint();
 String nameOfDType = "Euclidian";
    String lineColor= "ORANGE";
    Node starting;
    Node ending;
    Node[][] grid_area;
    boolean skipVal2 = true;
     public int matrixSize = 0;
  

    // Horizontal and VerticalDistance
    double hVDistance = 1.0; 
    
    // Diagonal Distance
    double dDistance = 1.4; 

    ArrayList<Node> getEuclidianDistance(boolean[][] matrix, int si, int sj, int ei, int ej,int gridSize) {

         matrixSize = matrix.length;
  
       starting = new Node(si, sj);
        ending = new Node(ei, ej);
        // The grid that is used to store nodes
        grid_area = new Node[matrixSize][matrixSize];

        // Creating nodes and finding blockedVal cells in matrix and mapping accordingly to our grid
        for (int i = 0; i < matrixSize; ++i) {
            for (int j = 0; j < matrixSize; ++j) {
                grid_area[i][j] = new Node(i, j);
                if (matrix[i][j] == false) {
                    grid_area[i][j].blockedVal = true;
                }	
            }
        }
             findPath();

        ArrayList<Node> path = new ArrayList<>();

          disPrint2.print(path, grid_area, ending, gridSize, matrixSize, ei, si, nameOfDType,lineColor,startingTime);
        return path;
    }
     public void findPath(){
         // setting startingPoint pathDistance to 0. 
        // All other nodes will have infinity pathDistance at the beginning
        starting.pathDistance =0;

        // a comparator object to deal with Priority Queue
        Comparator<Node> adjacencyComparator = (left, right) -> {
            if (left.pathDistance > (right.pathDistance)) {
                return 1;
            }
            return -1;
        };

        // Queue to store visiting nodes
        Queue<Node> queueB = new PriorityQueue(matrixSize, adjacencyComparator);

        queueB.add(starting);

        while (queueB.size() > 0) {
            Node current = queueB.remove();
            Node t;

            // Top
            if (current.x - 1 >= 0) {

                // Top Top
                t = grid_area[current.x - 1][current.y];
                if (!t.visitedVal && !t.blockedVal && t.pathDistance > current.pathDistance + hVDistance) {
                    t.pathDistance = current.pathDistance + hVDistance;
                    t.parentOb = current;
                    queueB.add(t);
                  
                }

                // Top Left
                if (current.y - 1 > 0) {
                    t = grid_area[current.x - 1][current.y - 1];
                    if (!t.visitedVal && !t.blockedVal && t.pathDistance > current.pathDistance + dDistance) {
                        t.pathDistance = current.pathDistance + dDistance;
                        t.parentOb = current;
                        queueB.add(t);
                       
                    }
                }

                // Top Right
                if (current.y + 1 < matrixSize) {
                    t = grid_area[current.x - 1][current.y + 1];
                    if (!t.visitedVal && !t.blockedVal && t.pathDistance > current.pathDistance + dDistance) {
                        t.pathDistance = current.pathDistance + dDistance;
                        t.parentOb = current;
                        queueB.add(t);
                         
                    }
                }
            }

            // Left
            if (current.y - 1 > 0) {
                t = grid_area[current.x][current.y - 1];
                if (!t.visitedVal && !t.blockedVal && t.pathDistance > current.pathDistance + hVDistance) {
                    t.pathDistance = current.pathDistance + hVDistance;
                    t.parentOb = current;
                    queueB.add(t);
                 
                }
            }

            // Right
            if (current.y + 1 < matrixSize) {
                t = grid_area[current.x][current.y + 1];
                if (!t.visitedVal && !t.blockedVal && t.pathDistance > current.pathDistance + hVDistance) {
                    t.pathDistance = current.pathDistance + hVDistance;
                    t.parentOb = current;
                    queueB.add(t);
                   
                }
            }
            // Down
            if (current.x + 1 < matrixSize) {

                // Down Down
                t = grid_area[current.x + 1][current.y];
                if (!t.visitedVal && !t.blockedVal && t.pathDistance > current.pathDistance + hVDistance) {
                    t.pathDistance = current.pathDistance + hVDistance;
                    t.parentOb = current;
                    queueB.add(t);
                
                }

                // Down Left
                if (current.y - 1 >= 0) {
                    t = grid_area[current.x + 1][current.y - 1];
                    if (!t.visitedVal && !t.blockedVal && t.pathDistance > current.pathDistance + dDistance) {
                        t.pathDistance = current.pathDistance + dDistance;
                        t.parentOb = current;
                        queueB.add(t);
                      
                    }
                }

                // Down Right
                if (current.y + 1 < matrixSize) {
                    t = grid_area[current.x + 1][current.y + 1];
                    if (!t.visitedVal && !t.blockedVal && t.pathDistance > current.pathDistance + dDistance) {
                        t.pathDistance = current.pathDistance + dDistance;
                        t.parentOb = current;
                        queueB.add(t);
                        
                    }
                }
            }    
            current.visitedVal = true;
        }

       
    } 
    
}

