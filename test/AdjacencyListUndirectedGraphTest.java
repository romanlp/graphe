import graph.GraphTools;
import graph.IUndirectedGraph;
import graph.impl.AdjacencyListUndirectedGraph;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdjacencyListUndirectedGraphTest {

    private int nbEdges1 = 15;
    private int nbEdges2 = 10;
    private int[][] tab1;
    private int[][] tab2;

    @Before
    public void setUp() throws Exception {
        this.tab1 = GraphTools.generateGraphData(10, nbEdges1, true);
        this.tab2 = GraphTools.generateGraphData(12, nbEdges2, true);
    }

    @Test
    public void testGetNbEdges() throws Exception {
        IUndirectedGraph graph1 = new AdjacencyListUndirectedGraph(this.tab1);
        IUndirectedGraph graph2 = new AdjacencyListUndirectedGraph(this.tab2);

        assertEquals(nbEdges1, graph1.getNbEdges());
        assertNotEquals(nbEdges2, graph1.getNbEdges());
        assertEquals(nbEdges2, graph2.getNbEdges());
    }

    @Test
    public void testIsEdge() throws Exception {

    }

    @Test
    public void testRemoveEdge() throws Exception {

    }

    @Test
    public void testAddEdge() throws Exception {

    }

    @Test
    public void testGetNeighbors() throws Exception {

    }

    @Test
    public void testGetNbNodes() throws Exception {

    }

    @Test
    public void testToAdjacencyMatrix() throws Exception {
        IUndirectedGraph graph = new AdjacencyListUndirectedGraph(this.tab1);
        assertEquals(this.tab1.length, graph.toAdjacencyMatrix().length);

        for(int i=0; i< this.tab1.length ; i++){
            assertArrayEquals(this.tab1[i], graph.toAdjacencyMatrix()[i]);
        }

        assertNotEquals(this.tab2.length, graph.toAdjacencyMatrix().length);

    }
}