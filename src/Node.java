/*
 * @author Kaushan Fernando
 */
public class Node {
    	int x;
    int y;
    double pathDistance = Integer.MAX_VALUE;
    Node parentOb = null;
    boolean visitedVal;
    boolean blockedVal;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
