package graph.impl;

import graph.IUndirectedGraph;

import java.util.List;
import java.util.ArrayList;

public class AdjacencyMatrixUndirectedGraph implements IUndirectedGraph {

    private int[][] matrix;

    public AdjacencyMatrixUndirectedGraph(int[][] matrix) {
	this.matrix = copy(matrix);
    }

    public AdjacencyMatrixUndirectedGraph(IUndirectedGraph graph) {
	this(graph.toAdjacencyMatrix());
    }

    public int getNbEdges() {
	int nbEdges = 0;
	for (int x = 0 ; x < matrix.length ; ++x)
	    for (int y = x ; y < matrix.length ; ++y)
		if (isEdge(x, y))
		    ++nbEdges;
	return nbEdges;
    }

    public boolean isEdge(int x, int y) {
	return matrix[x][y] == 1;
    }

    public void removeEdge(int x, int y) {
	matrix[x][y] = matrix[y][x] = 0;
    }

    public void addEdge(int x, int y) {
	matrix[x][y] = matrix[y][x] = 1;
    }

    public int[] getNeighbors(int x) {
	List<Integer> neighbors = new ArrayList<Integer>();
	for (int i = 0 ; i < getNbNodes() ; ++i)
	    if (matrix[x][i] == 1)
		neighbors.add(i);
	int[] res = new int[neighbors.size()];
	for (int i = 0 ; i < neighbors.size() ; ++i)
	    res[i] = neighbors.get(i);
	return res;
    }

    public int getNbNodes() {
	return matrix.length;
    }

    private int[][] copy(int[][] matrix) {
	int[][] copy = new int[matrix.length][matrix.length];
	for (int i = 0 ; i < matrix.length ; ++i)
	    for (int j = 0 ; j < matrix.length ; ++j)
		copy[i][j] = matrix[i][j];
	return copy;
    }

    public int[][] toAdjacencyMatrix() {
	return copy(matrix);
    }

    public String toString() {
    	String res = "\n";
    	for (int i = 0 ; i < matrix.length ; ++i) {
    	    for (int j = 0 ; j < matrix[0].length ; ++j) {
    		res += matrix[i][j];
    	    }
    	    res += "\n";
    	}
    	return res;
    }
}