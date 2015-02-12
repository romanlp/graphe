import graph.impl.IncidentMatrixDirectedGraph;

class IncidentMatrixDirectedGraphFactory implements IDirectedGraphFactory<IncidentMatrixDirectedGraph> {
    public IncidentMatrixDirectedGraph create(int[][] matrix) {
	return new IncidentMatrixDirectedGraph(matrix);
    }
}

public class IncidentMatrixDirectedGraphTest extends IDirectedGraphTest {
    public IDirectedGraphFactory factory() {
    	return new IncidentMatrixDirectedGraphFactory();
    }
}