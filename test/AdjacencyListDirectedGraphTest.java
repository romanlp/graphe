import graph.GraphTools;
import graph.IDirectedGraph;
import graph.impl.AdjacencyListDirectedGraph;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdjacencyListDirectedGraphTest {

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
                    { 0, 0, 1, 0 } ,
                    { 0, 0, 0, 1 } ,
                    { 1, 0, 1, 0 } ,
            };

    @Before
    public void setUp() throws Exception {
        this.tab1 = GraphTools.generateGraphData(nbNode1, nbEdges1, false);
        this.tab2 = GraphTools.generateGraphData(nbNode2, nbEdges2, false);
        this.tab3 = GraphTools.generateGraphData(nbNode3, nbEdges3, false);
    }

    @Test
    public void testGetNbArcs() throws Exception {
        IDirectedGraph graph1 = new AdjacencyListDirectedGraph(this.tab1);
        IDirectedGraph graph2 = new AdjacencyListDirectedGraph(this.tab2);
        IDirectedGraph graph3 = new AdjacencyListDirectedGraph(this.tab3);

        assertEquals(nbEdges1, graph1.getNbArcs());
        assertNotEquals(nbEdges2, graph1.getNbArcs());
        assertEquals(nbEdges2, graph2.getNbArcs());
        assertEquals(nbEdges3, graph3.getNbArcs());
    }

    @Test
    public void testConstructeur() throws Exception {
        IDirectedGraph graph1 = new AdjacencyListDirectedGraph(this.tab1);
        IDirectedGraph graph2 = new AdjacencyListDirectedGraph(this.tab2);
        IDirectedGraph graph3 = new AdjacencyListDirectedGraph(this.matrice1);

        IDirectedGraph graphCopy1 = new AdjacencyListDirectedGraph(graph1);
        IDirectedGraph graphCopy2 = new AdjacencyListDirectedGraph(graph2);
        IDirectedGraph graphCopy3 = new AdjacencyListDirectedGraph(graph3);

        assertArrayEquals(this.tab1, graphCopy1.toAdjacencyMatrix());
        assertArrayEquals(this.tab2, graphCopy2.toAdjacencyMatrix());
        assertArrayEquals(matrice1, graphCopy3.toAdjacencyMatrix());

    }

    @Test
    public void testIsArc() throws Exception {
        IDirectedGraph graph1 = new AdjacencyListDirectedGraph(this.matrice1);

        for (int x = 0 ; x < matrice1.length-1 ; x++){
            for (int y = 0 ; y < matrice1.length-1 ; y++){
                assertEquals(this.matrice1[x][y] == 1 , graph1.isArc(x, y));
            }
        }

    }

    @Test
    public void testRemoveArc() throws Exception {
        IDirectedGraph graph1 = new AdjacencyListDirectedGraph(this.matrice1);

        assertEquals(true, graph1.isArc(0, 1));

        graph1.removeArc(0, 1);

        assertEquals(false, graph1.isArc(0, 1));

    }

    @Test
    public void testAddArc() throws Exception {
        IDirectedGraph graph1 = new AdjacencyListDirectedGraph(this.matrice1);

        assertEquals(false, graph1.isArc(1, 3));

        graph1.addArc(1, 3);

        assertEquals(true, graph1.isArc(1, 3));
    }


    @Test
    public void testGetNbNodes() throws Exception {
        IDirectedGraph graph1 = new AdjacencyListDirectedGraph(this.tab1);
        IDirectedGraph graph2 = new AdjacencyListDirectedGraph(this.tab2);
        IDirectedGraph graph3 = new AdjacencyListDirectedGraph(this.tab3);

        assertEquals(this.nbNode1, graph1.getNbNodes());

        assertEquals(this.nbNode2, graph2.getNbNodes());

        assertNotEquals(this.nbNode1, graph2.getNbNodes());

        assertEquals(this.nbNode3, graph3.getNbNodes());
    }

    @Test
    public void testToAdjacencyMatrix() throws Exception {
        IDirectedGraph graph = new AdjacencyListDirectedGraph(this.tab1);
        assertEquals(this.tab1.length, graph.toAdjacencyMatrix().length);

        for(int i=0; i< this.tab1.length ; i++){
            assertArrayEquals(this.tab1[i], graph.toAdjacencyMatrix()[i]);
        }

        assertNotEquals(this.tab2.length, graph.toAdjacencyMatrix().length);

    }

    @Test
    public void testComputeInverse(){
        IDirectedGraph graph = new AdjacencyListDirectedGraph(this.matrice1);

        IDirectedGraph graph2 = graph.computeInverse();

        for (int x = 0 ; x < matrice1.length-1 ; x++){
            for (int y = 0 ; y < matrice1.length-1 ; y++){
                assertEquals(this.matrice1[y][x] == 1 , graph2.isArc(x, y));
            }
        }

    }
}