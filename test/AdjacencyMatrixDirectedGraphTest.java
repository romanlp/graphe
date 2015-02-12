import graph.impl.AdjacencyMatrixDirectedGraph;

class AdjacencyMatrixDirectedGraphFactory implements IDirectedGraphFactory<AdjacencyMatrixDirectedGraph> {
    public AdjacencyMatrixDirectedGraph create(int[][] matrix) {
	return new AdjacencyMatrixDirectedGraph(matrix);
    }
}

public class AdjacencyMatrixDirectedGraphTest extends IDirectedGraphTest {
    public IDirectedGraphFactory factory() {
    	return new AdjacencyMatrixDirectedGraphFactory();
    }
}