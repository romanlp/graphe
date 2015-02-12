import graph.IDirectedGraph;
import graph.Parcours;
import graph.impl.AdjacencyListDirectedGraph;
import graph.impl.ParcoursImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParcoursImplTest {

    private static int[][] graphMatrix = {
	{ 0, 0, 0, 0, 0, 0, 0 },
	{ 1, 0, 1, 1, 1, 0, 0 },
	{ 0, 0, 0, 1, 0, 0, 0 },
	{ 0, 1, 0, 0, 1, 0, 0 },
	{ 0, 0, 0, 0, 0, 1, 1 },
	{ 0, 0, 0, 0, 0, 0, 1 },
	{ 0, 0, 0, 0, 1, 0, 0 }
    };

    private ParcoursImpl parcours;

    private IDirectedGraph graph;

    @Before
    public void setUp() {
	this.parcours = new ParcoursImpl();
	this.graph    = new AdjacencyListDirectedGraph(graphMatrix);
    }

    @Test
    public void parcoursProfondeurTest() {
	    List<Integer> rp = parcours.parcoursProfondeur(graph);
        System.out.println(rp);
    }

}