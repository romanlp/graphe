package graph.impl;

import graph.IUndirectedGraph;

import java.util.List;
import java.util.ArrayList;

public class IncidentMatrixUndirectedGraph implements IUndirectedGraph {

    class Edge {
	public final int x;
	public final int y;

	public Edge(int x, int y) {
	    this.x = x;
	    this.y = y;
	}

	public boolean contains(int a) {
	    return x == a || y == a;
	}

	public int other(int a) {
	    return a == x ? y : x;
	}
    }

    private List<Edge> edges;

    private List<List<Integer>> matrix;

    public IncidentMatrixUndirectedGraph(int[][] matrix) {
	this.matrix = new ArrayList<List<Integer>>();
	for (int i = 0 ; i < matrix.length ; ++i)
	    this.matrix.add(new ArrayList<Integer>());
	edges = new ArrayList<Edge>();

	for (int x = 0 ; x < matrix.length ; ++x)
	    for (int y = x ; y < matrix.length ; ++y)
		if (matrix[x][y] == 1)
		    addEdge(x, y);
    }

    public IncidentMatrixUndirectedGraph(IUndirectedGraph graph) {
	this(graph.toAdjacencyMatrix());
    }

    public int getNbEdges() {
	return edges.size();
    }

    private int findEdge(int x, int y) {
	if (x == y)
	    return -1;
	for (int i = 0 ; i < matrix.get(x).size() ; ++i)
	    if (matrix.get(x).get(i) == 1 && edges.get(i).contains(y))
		return i;
	return -1;
    }

    public boolean isEdge(int x, int y) {
	return findEdge(x, y) != -1;
    }

    public void removeEdge(int x, int y) {
	int edgeIndex = findEdge(x, y);
	if (edgeIndex == -1)
	    return;

	for (List<Integer> l : matrix)
	    l.remove(edgeIndex);
	edges.remove(edgeIndex);
    }

    public void addEdge(int x, int y) {
	for (List<Integer> l : matrix)
	    l.add(0);
	matrix.get(x).set(edges.size(), 1);
	matrix.get(y).set(edges.size(), 1);
	edges.add(new Edge(x, y));
    }

    public int[] getNeighbors(int x) {
	List<Integer> neighbors = new ArrayList<Integer>();
	for (int i = 0 ; i < matrix.get(x).size() ; ++i) {
	    if (matrix.get(x).get(i) == 1) {
		neighbors.add(edges.get(i).other(x));
	    }
	}
	int[] res = new int[neighbors.size()];
	for (int i = 0 ; i < neighbors.size() ; ++i)
	    res[i] = neighbors.get(i);
	return res;
    }

    public int getNbNodes() {
	return matrix.size();
    }

    public int[][] toAdjacencyMatrix() {
	int[][] res = new int[getNbNodes()][getNbNodes()];
	for (Edge edge : edges)
	    res[edge.x][edge.y] = res[edge.y][edge.x] = 1;
	return res;
    }

    public String toString() {
    	String res = "\n";
	res += "[";
	for (Edge edge : edges)
	    res += "(" + edge.x + ", " + edge.y +"), ";
	res += "]\n";

    	for (int i = 0 ; i < matrix.size() ; ++i) {
    	    for (int j = 0 ; j < matrix.get(i).size() ; ++j) {
    		res += matrix.get(i).get(j);
    	    }
    	    res += "\n";
    	}
    	return res;
    }

    // public String toString() {
    // 	int[][] matrix = toAdjacencyMatrix();
    // 	String res = "\n";
    // 	for (int i = 0 ; i < matrix.length ; ++i) {
    // 	    for (int j = 0 ; j < matrix[0].length ; ++j) {
    // 		res += matrix[i][j];
    // 	    }
    // 	    res += "\n";
    // 	}
    // 	return res;
    // }
}