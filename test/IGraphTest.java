import graph.IGraph;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

interface IGraphFactory<Graph extends IGraph> {
    public Graph create(int[][] matrix);
}

public abstract class IGraphTest<Graph extends IGraph> {

    protected static int[][] noNodesMatrix = { };
    protected static int[][] noEdgesMatrix = {
	{ 0, 0, 0, 0, 0 },
	{ 0, 0, 0, 0, 0 },
	{ 0, 0, 0, 0, 0 },
	{ 0, 0, 0, 0, 0 },
	{ 0, 0, 0, 0, 0 },
    };
    protected static int[][] undirectedGraphMatrix = {
	{ 0, 1, 0, 1, 1 },
	{ 1, 0, 0, 1, 0 },
	{ 0, 0, 0, 0, 1 },
	{ 1, 1, 0, 0, 0 },
	{ 1, 0, 1, 0, 0 },
    };

    protected Graph noNodes;
    protected Graph noEdges;
    protected Graph undirectedGraph;

    public abstract IGraphFactory<Graph> factory();

    @Before
    public void setUp() {
    	noNodes = factory().create(noNodesMatrix);
    	noEdges = factory().create(noEdgesMatrix);
    	undirectedGraph = factory().create(undirectedGraphMatrix);
    }

    @Test
    public void getNbNodesTest() {
	assertEquals(0, noNodes.getNbNodes());
	assertEquals(5, noEdges.getNbNodes());
	assertEquals(5, undirectedGraph.getNbNodes());
    }

    protected void assertMatrixEquals(int[][] expected, int[][] actual) {
	assertEquals(expected.length, actual.length);
	for (int i = 0 ; i < expected.length ; ++i)
	    assertArrayEquals(expected[i], actual[i]);
    }

    @Test
    public void toAdjacencyMatrixTest() {
	assertMatrixEquals(noNodesMatrix, noNodes.toAdjacencyMatrix());
	assertMatrixEquals(noEdgesMatrix, noEdges.toAdjacencyMatrix());
	assertMatrixEquals(undirectedGraphMatrix, undirectedGraph.toAdjacencyMatrix());
    }
}
