package graph;

/**
 * Created by Roman on 27/01/2015.
 */
public interface IUndirectedGraph extends IGraph {

    public int getNbEdges();

    public boolean isEdge(int x, int y);

    public void removeEdge(int x, int y);

    public void addEdge(int x, int y);

    public int[] getNeighbors(int x);


}
