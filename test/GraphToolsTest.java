import graph.GraphTools;
import graph.IDirectedGraph;
import graph.IUndirectedGraph;
import graph.impl.AdjacencyMatrixDirectedGraph;
import graph.impl.AdjacencyMatrixUndirectedGraph;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GraphToolsTest {

    @Test
    public void generateGraphDataDirectedTest() {
	IDirectedGraph graph = new AdjacencyMatrixDirectedGraph(GraphTools.generateGraphData(5, 10, false));
	assertEquals(5 , graph.getNbNodes());
	assertEquals(10, graph.getNbArcs());
    }

    @Test
    public void generateGraphDataUndirectedTest() {
	IUndirectedGraph graph = new AdjacencyMatrixUndirectedGraph(GraphTools.generateGraphData(5, 5, true));
	assertEquals(5, graph.getNbNodes());
	assertEquals(5, graph.getNbEdges());
    }

    @Test
    public void generateGraphDataDirectedFullTest() {
	IDirectedGraph graph = new AdjacencyMatrixDirectedGraph(GraphTools.generateGraphData(100, 9900, false));
	assertEquals(100 , graph.getNbNodes());
	assertEquals(9900, graph.getNbArcs());
    }

    @Test
    public void generateGraphDataUndirectedFullTest() {
	IUndirectedGraph graph = new AdjacencyMatrixUndirectedGraph(GraphTools.generateGraphData(100, 4950, true));
	assertEquals(100 , graph.getNbNodes());
	assertEquals(4950, graph.getNbEdges());
    }
}