package graph.impl;

import graph.IUndirectedGraph;

public class AdjacencyMatrixUndirectedGraph extends ValuedAdjacencyMatrixUndirectedGraph implements IUndirectedGraph {

    public AdjacencyMatrixUndirectedGraph(int[][] matrix) {
        
	    super(matrix, matrix);
    }

    public AdjacencyMatrixUndirectedGraph(IUndirectedGraph graph) {
    	
        super(graph.toAdjacencyMatrix(), graph.toAdjacencyMatrix());
    }
}