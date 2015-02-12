import graph.IDirectedGraph;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

interface IDirectedGraphFactory<Graph extends IDirectedGraph> extends IGraphFactory<Graph> {
    public Graph create(int[][] matrix);
}

public abstract class IDirectedGraphTest<Graph extends IDirectedGraph> extends IGraphTest<Graph> {

    protected static int[][] directedGraphMatrix = {
	{ 0, 1, 0, 1, 1 },
	{ 0, 0, 0, 1, 0 },
	{ 0, 0, 0, 0, 1 },
	{ 0, 0, 0, 0, 0 },
	{ 0, 0, 0, 0, 0 },
    };
    protected static int[][] reversedDirectedGraphMatrix = {
	{ 0, 0, 0, 0, 0 },
	{ 1, 0, 0, 0, 0 },
	{ 0, 0, 0, 0, 0 },
	{ 1, 1, 0, 0, 0 },
	{ 1, 0, 1, 0, 0 },
    };

    protected Graph directedGraph;

    @Before
    public void setUp() {
	super.setUp();
    	directedGraph = factory().create(directedGraphMatrix);
    }

    @Test
    public void getNbNodesTest() {
	assertEquals(5, directedGraph.getNbNodes());
    }

    @Test
	public void toAdjacencyMatrixTest() {
	assertMatrixEquals(directedGraphMatrix, directedGraph.toAdjacencyMatrix());
    }

    @Test
    public void getNbArcsTest() {
	assertEquals(0, noNodes.getNbArcs());
	assertEquals(0, noEdges.getNbArcs());
	assertEquals(5, directedGraph.getNbArcs());
    }

    @Test
    public void isArcTest() {
	for (int x = 0 ; x < noNodes.getNbNodes() ; ++x)
	    for (int y = 0 ; y < noNodes.getNbNodes() ; ++y)
		assertFalse(noNodes.isArc(x, y));

	for (int x = 0 ; x < noEdges.getNbNodes() ; ++x)
	    for (int y = 0 ; y < noEdges.getNbNodes() ; ++y)
		assertFalse(noEdges.isArc(x, y));

	assertTrue(directedGraph.isArc(0, 1));
	assertTrue(directedGraph.isArc(0, 3));
	assertTrue(directedGraph.isArc(0, 4));
	assertTrue(directedGraph.isArc(1, 3));
	assertTrue(directedGraph.isArc(2, 4));
	assertFalse(directedGraph.isArc(0, 0));
	assertFalse(directedGraph.isArc(0, 2));
	assertFalse(directedGraph.isArc(1, 0));
	assertFalse(directedGraph.isArc(1, 1));
	assertFalse(directedGraph.isArc(1, 2));
	assertFalse(directedGraph.isArc(2, 0));
	assertFalse(directedGraph.isArc(2, 1));
	assertFalse(directedGraph.isArc(1, 4));
	assertFalse(directedGraph.isArc(4, 1));
	assertFalse(directedGraph.isArc(2, 2));
	assertFalse(directedGraph.isArc(2, 3));
	assertFalse(directedGraph.isArc(3, 0));
	assertFalse(directedGraph.isArc(3, 1));
	assertFalse(directedGraph.isArc(3, 2));
	assertFalse(directedGraph.isArc(3, 3));
	assertFalse(directedGraph.isArc(3, 4));
	assertFalse(directedGraph.isArc(4, 0));
	assertFalse(directedGraph.isArc(4, 2));
	assertFalse(directedGraph.isArc(4, 3));
	assertFalse(directedGraph.isArc(4, 4));
    }

    @Test
    public void removeArcTest() {
	int oldNbArcs = directedGraph.getNbArcs();
	assertTrue(directedGraph.isArc(0, 3));
	directedGraph.removeArc(0, 3);
	assertFalse(directedGraph.isArc(0, 3));
	assertEquals(oldNbArcs-1, directedGraph.getNbArcs());
    }

    @Test
    public void addArcTest() {
	int oldNbArcs = directedGraph.getNbArcs();
	assertFalse(directedGraph.isArc(0, 2));
	directedGraph.addArc(0, 2);
	assertTrue(directedGraph.isArc(0, 2));
	assertEquals(oldNbArcs+1, directedGraph.getNbArcs());
    }

    @Test
    public void getSuccessorsTest() {
	assertEquals(3, directedGraph.getSuccessors(0).length);
	assertEquals(1, directedGraph.getSuccessors(0)[0]);
	assertEquals(3, directedGraph.getSuccessors(0)[1]);
	assertEquals(4, directedGraph.getSuccessors(0)[2]);

	assertEquals(1, directedGraph.getSuccessors(1).length);
	assertEquals(3, directedGraph.getSuccessors(1)[0]);

	assertEquals(1, directedGraph.getSuccessors(2).length);
	assertEquals(4, directedGraph.getSuccessors(2)[0]);

	assertEquals(0, directedGraph.getSuccessors(3).length);
	assertEquals(0, directedGraph.getSuccessors(4).length);
    }

    @Test
    public void getPredecessorsTest() {
    	assertEquals(0, directedGraph.getPredecessors(0).length);

    	assertEquals(1, directedGraph.getPredecessors(1).length);
	assertEquals(0, directedGraph.getPredecessors(1)[0]);

    	assertEquals(0, directedGraph.getPredecessors(2).length);

    	assertEquals(2, directedGraph.getPredecessors(3).length);
	assertEquals(0, directedGraph.getPredecessors(3)[0]);
	assertEquals(1, directedGraph.getPredecessors(3)[1]);

    	assertEquals(2, directedGraph.getPredecessors(4).length);
	assertEquals(0, directedGraph.getPredecessors(4)[0]);
	assertEquals(2, directedGraph.getPredecessors(4)[1]);
    }

    @Test
    public void computeInverseTest() {
	IDirectedGraph graph = directedGraph.computeInverse();
	assertMatrixEquals(reversedDirectedGraphMatrix, graph.toAdjacencyMatrix());
    }
}