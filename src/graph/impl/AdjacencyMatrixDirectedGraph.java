package graph.impl;

import graph.IDirectedGraph;

import java.util.List;
import java.util.ArrayList;

public class AdjacencyMatrixDirectedGraph implements IDirectedGraph {

    private int[][] matrix;

    public AdjacencyMatrixDirectedGraph(int[][] matrix) {
	this.matrix = copy(matrix);
    }

    public AdjacencyMatrixDirectedGraph(IDirectedGraph graph) {
	this(graph.toAdjacencyMatrix());
    }

    public int getNbArcs() {
	int nbArcs = 0;
	for (int x = 0 ; x < matrix.length ; ++x)
	    for (int y = 0 ; y < matrix.length ; ++y)
		if (isArc(x, y))
		    ++nbArcs;
	return nbArcs;
    }

    public boolean isArc(int x, int y) {
	return matrix[x][y] == 1;
    }

    public void removeArc(int x, int y) {
	matrix[x][y] = 0;
    }

    public void addArc(int x, int y) {
	matrix[x][y] = 1;
    }

    public int[] getSuccessors(int x) {
	List<Integer> successors = new ArrayList<Integer>();
	for (int i = 0 ; i < getNbNodes() ; ++i)
	    if (matrix[x][i] == 1)
		successors.add(i);
	int[] res = new int[successors.size()];
	for (int i = 0 ; i < successors.size() ; ++i)
	    res[i] = successors.get(i);
	return res;
    }

    public int[] getPredecessors(int x) {
	List<Integer> predecessors = new ArrayList<Integer>();
	for (int i = 0 ; i < getNbNodes() ; ++i)
	    if (matrix[i][x] == 1)
		predecessors.add(i);
	int[] res = new int[predecessors.size()];
	for (int i = 0 ; i < predecessors.size() ; ++i)
	    res[i] = predecessors.get(i);
	return res;
    }

    public IDirectedGraph computeInverse() {
	int[][] inverse = new int[matrix.length][matrix.length];
	for (int i = 0 ; i < matrix.length ; ++i)
	    for (int j = 0 ; j < matrix.length ; ++j)
		inverse[j][i] = matrix[i][j];
	return new AdjacencyMatrixDirectedGraph(inverse);
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