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

    int[][] matrice1 =
            {
                    { 0, 1, 1, 1 } ,
                    { 1, 0, 0, 0 } ,
                    { 1, 0, 0, 1 } ,
                    { 1, 0, 1, 0 } ,
            };

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
    public void testConstructeur() throws Exception {
        IUndirectedGraph graph1 = new AdjacencyListUndirectedGraph(this.tab1);
        IUndirectedGraph graph2 = new AdjacencyListUndirectedGraph(this.tab2);
        IUndirectedGraph graph3 = new AdjacencyListUndirectedGraph(this.matrice1);

        IUndirectedGraph graphCopy1 = new AdjacencyListUndirectedGraph(graph1);
        IUndirectedGraph graphCopy2 = new AdjacencyListUndirectedGraph(graph2);
        IUndirectedGraph graphCopy3 = new AdjacencyListUndirectedGraph(graph3);

        assertArrayEquals(this.tab1, graphCopy1.toAdjacencyMatrix());
        assertArrayEquals(this.tab2, graphCopy2.toAdjacencyMatrix());
        assertArrayEquals(matrice1, graphCopy3.toAdjacencyMatrix());

    }

    @Test
    public void testIsEdge() throws Exception {
        IUndirectedGraph graph1 = new AdjacencyListUndirectedGraph(this.matrice1);

        for (int x = 0 ; x < matrice1.length-1 ; x++){
            for (int y = 0 ; y < matrice1.length-1 ; y++){
                assertEquals(this.matrice1[x][y] == 1, graph1.isEdge(x,y));
            }
        }

    }

    @Test
    public void testRemoveEdge() throws Exception {
        IUndirectedGraph graph1 = new AdjacencyListUndirectedGraph(this.matrice1);

        assertEquals(true, graph1.isEdge(0,1));

        graph1.removeEdge(0,1);

        assertEquals(false, graph1.isEdge(0, 1));

    }

    @Test
    public void testAddEdge() throws Exception {
        IUndirectedGraph graph1 = new AdjacencyListUndirectedGraph(this.matrice1);

        assertEquals(false, graph1.isEdge(1,3));

        graph1.addEdge(1,3);

        assertEquals(true, graph1.isEdge(1,3));
    }

    @Test
    public void testGetNeighbors() throws Exception {

        IUndirectedGraph graph = new AdjacencyListUndirectedGraph(matrice1);

        assertEquals(1, graph.getNeighbors(1).length);
        int [] test = {0};
        assertArrayEquals(test, graph.getNeighbors(1));

        assertEquals(3, graph.getNeighbors(0).length);
        int [] test2 = {1,2,3};
        assertArrayEquals(test2, graph.getNeighbors(0));

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