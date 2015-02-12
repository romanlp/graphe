import graph.impl.IncidentMatrixUndirectedGraph;

class IncidentMatrixUndirectedGraphFactory implements IUndirectedGraphFactory<IncidentMatrixUndirectedGraph> {
    public IncidentMatrixUndirectedGraph create(int[][] matrix) {
	return new IncidentMatrixUndirectedGraph(matrix);
    }
}

public class IncidentMatrixUndirectedGraphTest extends IUndirectedGraphTest {
    public IUndirectedGraphFactory factory() {
    	return new IncidentMatrixUndirectedGraphFactory();
    }
}