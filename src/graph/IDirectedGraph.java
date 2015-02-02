package graph;

/**
 * Created by Roman on 27/01/2015.
 */
public interface IDirectedGraph extends IGraph{

    public int getNbArcs();

    public boolean isArc(int from, int to );

    public void removeArc(int from, int to );

    public void addArc(int from, int to );

    public int [] getSuccessors(int x);

    public int [] getPredecessors(int x);

    public IDirectedGraph computeInverse();
}
