import graph.GraphTools;
import graph.IUndirectedGraph;
import graph.impl.AdjacencyListUndirectedGraph;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdjacencyListUndirectedGraphTest {

    private int nbNode1 = 10;
    private int nbNode2 = 12;
    private int nbNode3 = 5;

    private int nbEdges1 = 15;
    private int nbEdges2 = 10;
    private int nbEdges3 = 0;

    private int[][] tab1;
    private int[][] tab2;
    private int[][] tab3;

    @Before
    public void setUp() throws Exception {
        this.tab1 = GraphTools.generateGraphData(nbNode1, nbEdges1, true);
        this.tab2 = GraphTools.generateGraphData(nbNode2, nbEdges2, true);
        this.tab3 = GraphTools.generateGraphData(nbNode3, nbEdges3, true);
    }

    @Test
    public void testGetNbEdges() throws Exception {
        IUndirectedGraph graph1 = new AdjacencyListUndirectedGraph(this.tab1);
        IUndirectedGraph graph2 = new AdjacencyListUndirectedGraph(this.tab2);
        IUndirectedGraph graph3 = new AdjacencyListUndirectedGraph(this.tab3);

        assertEquals(nbEdges1, graph1.getNbEdges());
        assertNotEquals(nbEdges2, graph1.getNbEdges());
        assertEquals(nbEdges2, graph2.getNbEdges());
        assertEquals(nbEdges3, graph3.getNbEdges());
    }

    @Test
    public void testIsEdge() throws Exception {
        IUndirectedGraph graph1 = new AdjacencyListUndirectedGraph(this.tab1);
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
        IUndirectedGraph graph1 = new AdjacencyListUndirectedGraph(this.tab1);
        IUndirectedGraph graph2 = new AdjacencyListUndirectedGraph(this.tab2);
        IUndirectedGraph graph3 = new AdjacencyListUndirectedGraph(this.tab3);

        assertEquals(this.nbNode1, graph1.getNbNodes());

        assertEquals(this.nbNode2, graph2.getNbNodes());

        assertNotEquals(this.nbNode1, graph2.getNbNodes());

        assertEquals(this.nbNode3, graph3.getNbNodes());
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