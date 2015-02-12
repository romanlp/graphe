import graph.impl.AdjacencyMatrixUndirectedGraph;

class AdjacencyMatrixUndirectedGraphFactory implements IUndirectedGraphFactory<AdjacencyMatrixUndirectedGraph> {
    public AdjacencyMatrixUndirectedGraph create(int[][] matrix) {
	return new AdjacencyMatrixUndirectedGraph(matrix);
    }
}

public class AdjacencyMatrixUndirectedGraphTest extends IUndirectedGraphTest {
    public IUndirectedGraphFactory factory() {
    	return new AdjacencyMatrixUndirectedGraphFactory();
    }
}