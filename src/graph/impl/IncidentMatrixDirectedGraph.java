package graph.impl;

import graph.IDirectedGraph;

import java.util.List;
import java.util.ArrayList;

public class IncidentMatrixDirectedGraph implements IDirectedGraph {

    class Arc {
	public final int from;
	public final int to;

	public Arc(int from, int to) {
	    this.from = from;
	    this.to = to;
	}
    }

    private List<Arc> arcs;

    private List<List<Integer>> matrix;

    public IncidentMatrixDirectedGraph(int[][] matrix) {
	this.matrix = new ArrayList<List<Integer>>();
	for (int i = 0 ; i < matrix.length ; ++i)
	    this.matrix.add(new ArrayList<Integer>());
	arcs = new ArrayList<Arc>();

	for (int x = 0 ; x < matrix.length ; ++x)
	    for (int y = 0 ; y < matrix.length ; ++y)
		if (matrix[x][y] == 1)
		    addArc(x, y);
    }

    public IncidentMatrixDirectedGraph(IDirectedGraph graph) {
	this(graph.toAdjacencyMatrix());
    }

    private IncidentMatrixDirectedGraph(List<Arc> arcs, List<List<Integer>> matrix) {
	this.arcs   = arcs;
	this.matrix = matrix;
    }

    public int getNbArcs() {
	return arcs.size();
    }

    private int findArc(int x, int y) {
	if (x == y)
	    return -1;
	for (int i = 0 ; i < matrix.get(x).size() ; ++i)
	    if (matrix.get(x).get(i) == 1 && arcs.get(i).to == y)
		return i;
	return -1;
    }

    public boolean isArc(int x, int y) {
	return findArc(x, y) != -1;
    }

    public void removeArc(int x, int y) {
	int arcIndex = findArc(x, y);
	if (arcIndex == -1)
	    return;

	for (List<Integer> l : matrix)
	    l.remove(arcIndex);
	arcs.remove(arcIndex);
    }

    public void addArc(int x, int y) {
	for (List<Integer> l : matrix)
	    l.add(0);
	matrix.get(x).set(arcs.size(),  1);
	matrix.get(y).set(arcs.size(), -1);
	arcs.add(new Arc(x, y));
    }

    public int[] getSuccessors(int x) {
	List<Integer> successors = new ArrayList<Integer>();
	for (int i = 0 ; i < matrix.get(x).size() ; ++i) {
	    if (matrix.get(x).get(i) == 1) {
		successors.add(arcs.get(i).to);
	    }
	}
	int[] res = new int[successors.size()];
	for (int i = 0 ; i < successors.size() ; ++i)
	    res[i] = successors.get(i);
	return res;
    }

    public int[] getPredecessors(int x) {
	List<Integer> predecessors = new ArrayList<Integer>();
	for (int i = 0 ; i < matrix.get(x).size() ; ++i) {
	    if (matrix.get(x).get(i) == -1) {
		predecessors.add(arcs.get(i).from);
	    }
	}
	int[] res = new int[predecessors.size()];
	for (int i = 0 ; i < predecessors.size() ; ++i)
	    res[i] = predecessors.get(i);
	return res;
    }

    public IDirectedGraph computeInverse() {
	List<Arc> reversedArcs = new ArrayList<Arc>(arcs.size());

	List<List<Integer>> reversedMatrix = new ArrayList<List<Integer>>();
	for (int i = 0 ; i < matrix.size() ; ++i)
	    reversedMatrix.add(new ArrayList<Integer>());

	for (int i = 0 ; i < arcs.size() ; ++i) {
	    int from = arcs.get(i).to;
	    int to   = arcs.get(i).from;
	    reversedArcs.add(new Arc(from, to));
	    for (int j = 0 ; j < matrix.size() ; ++j) {
		reversedMatrix.get(j).add(0);
	    }
	    reversedMatrix.get(from).set(i, 1);
	    reversedMatrix.get(to)  .set(i, -1);
	}

	return new IncidentMatrixDirectedGraph(reversedArcs, reversedMatrix);
    }

    public int getNbNodes() {
	return matrix.size();
    }

    public int[][] toAdjacencyMatrix() {
	int[][] res = new int[getNbNodes()][getNbNodes()];
	for (Arc arc : arcs)
	    res[arc.from][arc.to] = 1;
	return res;
    }

    public String toString() {
    	String res = "\n";
	res += "[";
	for (Arc arc : arcs)
	    res += "(" + arc.from + ", " + arc.to +"), ";
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