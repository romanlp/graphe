import graph.IUndirectedGraph;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

interface IUndirectedGraphFactory<Graph extends IUndirectedGraph> extends IGraphFactory<Graph> {
    public Graph create(int[][] matrix);
}

public abstract class IUndirectedGraphTest<Graph extends IUndirectedGraph> extends IGraphTest<Graph> {

    @Test
    public void getNbEdgesTest() {
	assertEquals(0, noNodes.getNbEdges());
	assertEquals(0, noEdges.getNbEdges());
	assertEquals(5, undirectedGraph.getNbEdges());
    }

    @Test
    public void isEdgeTest() {
	for (int x = 0 ; x < noNodes.getNbNodes() ; ++x)
	    for (int y = 0 ; y < noNodes.getNbNodes() ; ++y)
		assertFalse(noNodes.isEdge(x, y));

	for (int x = 0 ; x < noEdges.getNbNodes() ; ++x)
	    for (int y = 0 ; y < noEdges.getNbNodes() ; ++y)
		assertFalse(noEdges.isEdge(x, y));

	assertTrue(undirectedGraph.isEdge(0, 1));
	assertTrue(undirectedGraph.isEdge(1, 0));
	assertTrue(undirectedGraph.isEdge(0, 3));
	assertTrue(undirectedGraph.isEdge(3, 0));
	assertTrue(undirectedGraph.isEdge(0, 4));
	assertTrue(undirectedGraph.isEdge(4, 0));
	assertTrue(undirectedGraph.isEdge(1, 3));
	assertTrue(undirectedGraph.isEdge(3, 1));
	assertTrue(undirectedGraph.isEdge(2, 4));
	assertTrue(undirectedGraph.isEdge(4, 2));
	assertFalse(undirectedGraph.isEdge(0, 0));
	assertFalse(undirectedGraph.isEdge(0, 2));
	assertFalse(undirectedGraph.isEdge(2, 0));
	assertFalse(undirectedGraph.isEdge(1, 1));
	assertFalse(undirectedGraph.isEdge(1, 2));
	assertFalse(undirectedGraph.isEdge(2, 1));
	assertFalse(undirectedGraph.isEdge(1, 4));
	assertFalse(undirectedGraph.isEdge(4, 1));
	assertFalse(undirectedGraph.isEdge(2, 2));
	assertFalse(undirectedGraph.isEdge(2, 3));
	assertFalse(undirectedGraph.isEdge(3, 2));
	assertFalse(undirectedGraph.isEdge(3, 3));
	assertFalse(undirectedGraph.isEdge(3, 4));
	assertFalse(undirectedGraph.isEdge(4, 3));
	assertFalse(undirectedGraph.isEdge(4, 4));
    }

    @Test
    public void removeEdgeTest() {
	int oldNbEdges = undirectedGraph.getNbEdges();
	assertTrue(undirectedGraph.isEdge(0, 3));
	assertTrue(undirectedGraph.isEdge(3, 0));
	undirectedGraph.removeEdge(0, 3);
	assertFalse(undirectedGraph.isEdge(0, 3));
	assertFalse(undirectedGraph.isEdge(3, 0));
	assertEquals(oldNbEdges-1, undirectedGraph.getNbEdges());
    }

    @Test
    public void addEdgeTest() {
	int oldNbEdges = undirectedGraph.getNbEdges();
	assertFalse(undirectedGraph.isEdge(0, 2));
	assertFalse(undirectedGraph.isEdge(2, 0));
	undirectedGraph.addEdge(0, 2);
	assertTrue(undirectedGraph.isEdge(0, 2));
	assertTrue(undirectedGraph.isEdge(2, 0));
	assertEquals(oldNbEdges+1, undirectedGraph.getNbEdges());
    }

    @Test
    public void getNeighbors() {
	assertEquals(1, undirectedGraph.getNeighbors(0)[0]);
	assertEquals(3, undirectedGraph.getNeighbors(0)[1]);
	assertEquals(4, undirectedGraph.getNeighbors(0)[2]);
	assertEquals(3, undirectedGraph.getNeighbors(0).length);

	assertEquals(0, undirectedGraph.getNeighbors(1)[0]);
	assertEquals(3, undirectedGraph.getNeighbors(1)[1]);
	assertEquals(2, undirectedGraph.getNeighbors(1).length);

	assertEquals(4, undirectedGraph.getNeighbors(2)[0]);
	assertEquals(1, undirectedGraph.getNeighbors(2).length);

	assertEquals(0, undirectedGraph.getNeighbors(3)[0]);
	assertEquals(1, undirectedGraph.getNeighbors(3)[1]);
	assertEquals(2, undirectedGraph.getNeighbors(3).length);

	assertEquals(0, undirectedGraph.getNeighbors(4)[0]);
	assertEquals(2, undirectedGraph.getNeighbors(4)[1]);
	assertEquals(2, undirectedGraph.getNeighbors(4).length);
    }
}
