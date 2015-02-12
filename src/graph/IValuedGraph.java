package graph;

/**
 * Created by Roman on 10/02/2015.
 */
public interface IValuedGraph {
    
    public int getValue(int x, int y);
    
    public int[][] toValuedMatrix();
}
