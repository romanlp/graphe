import graph.IUndirectedValuedGraph;
import graph.impl.PRIM;
import graph.impl.ValuedAdjacencyMatrixUndirectedGraph;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class PRIMTest {


    private static final int[][] adjacence = {
            { 0, 1, 1, 1, 1 },
            { 1, 0, 1, 1, 1 },
            { 1, 1, 0, 1, 1 },
            { 1, 1, 1, 0, 0 },
            { 1, 1, 1, 0, 0 }
    };

    private static final int[][] matrix = {
            { 0, 2, 6, 1, 4 },
            { 2, 0, 5, 3, 2 },
            { 6, 5, 0, 3, 2 },
            { 1, 3, 3, 0, 0 },
            { 4, 2, 2, 0, 0 }
    };

    private static final int[][] expectedTree = {
            { 0, 2, 0, 1, 0 },
            { 2, 0, 0, 0, 2 },
            { 0, 0, 0, 0, 2 },
            { 1, 0, 0, 0, 0 },
            { 0, 2, 2, 0, 0 }
    };

    private PRIM prim;
    private IUndirectedValuedGraph graph;

    @Before
    public void setUp() {
        prim = new PRIM();
        graph = new ValuedAdjacencyMatrixUndirectedGraph(adjacence, matrix);
    }

    void assertMatrixEquals(int[][] expected, int[][] actual) {
        assertEquals(expected.length, actual.length);
        for (int i = 0 ; i < expected.length ; ++i)
            assertArrayEquals(expected[i], actual[i]);
    }

    @Test
    public void primTest() {
        IUndirectedValuedGraph tree = prim.apply(graph);
        assertMatrixEquals(expectedTree, tree.toAdjacencyMatrix());
    }

}