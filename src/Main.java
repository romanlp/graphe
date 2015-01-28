import graph.GraphTools;
import graph.IUndirectedGraph;
import graph.impl.AdjacencyListUndirectedGraph;

/**
 * Created by Roman on 27/01/2015.
 */
public class Main {

    public static void main(String[] args) {
        int[][] tab1 = GraphTools.generateGraphData(6, 8, true);
        GraphTools.show(tab1);

        IUndirectedGraph graph1 = new AdjacencyListUndirectedGraph(tab1);

        GraphTools.show(graph1.toAdjacencyMatrix());
    }
}
