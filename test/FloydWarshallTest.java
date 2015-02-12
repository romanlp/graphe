import graph.GraphTools;
import graph.IUndirectedValuedGraph;
import graph.impl.Floyd;
import graph.impl.ValuedAdjacencyMatrixUndirectedGraph;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class FloydWarshallTest {

    private static final int[][] edges = {
            { 0, 1, 0, 1, 1 },
            { 1, 0, 1, 1, 1 },
            { 0, 1, 0, 1, 1 },
            { 1, 1, 1, 0, 0 },
            { 1, 1, 1, 0, 0 }
    };

    private static final int[][] weights = {
            { 0, 2, 0, 1, 4 },
            { 2, 0, 5, 3, 2 },
            { 0, 5, 0, 3, 2 },
            { 1, 3, 3, 0, 0 },
            { 4, 2, 2, 0, 0 }
    };

    private static final int[][] expectedDist = {
            { 0, 2, 4, 1, 4 },
            { 2, 0, 4, 3, 2 },
            { 4, 4, 0, 3, 2 },
            { 1, 3, 3, 0, 5 },
            { 4, 2, 2, 5, 0 }
    };

    private static final int[][] expectedPaths = {
            { 0, 0, 3, 0, 0 },
            { 1, 1, 4, 1, 1 },
            { 3, 4, 2, 2, 2 },
            { 3, 3, 3, 3, 0 },
            { 4, 4, 4, 0, 4 }
    };

    private Floyd floydWarshall;
    private IUndirectedValuedGraph graph;

    @Before
    public void setUp() {
        floydWarshall = new Floyd();
        graph = new ValuedAdjacencyMatrixUndirectedGraph(edges, weights);
    }

    void assertMatrixEquals(int[][] expected, int[][] actual) {
        assertEquals(expected.length, actual.length);
        for (int i = 0 ; i < expected.length ; ++i)
            assertArrayEquals(expected[i], actual[i]);
    }

    @Test
    public void floydWarshallTest() {
        int[][] res = floydWarshall.apply(graph);
        GraphTools.show(res);
        assertMatrixEquals(expectedPaths, res);
    }
}