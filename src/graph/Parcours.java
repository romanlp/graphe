package graph;

import graph.impl.AdjacencyListDirectedGraph;
import graph.impl.AdjacencyMatrixDirectedGraph;
import graph.impl.IncidentMatrixDirectedGraph;

import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Parcours {

    public List<Integer> parcoursLargeur(int sommet, IDirectedGraph graph) {
	List<Integer> nodes = new ArrayList<Integer>();
	boolean[] visited = new boolean[graph.getNbNodes()];
	Queue<Integer> toVisit = new LinkedList<Integer>();

	nodes.add(sommet);
	visited[sommet] = true;
	toVisit.add(sommet);

	while (!toVisit.isEmpty()) {
	    int pred = toVisit.poll();
	    for (int succ : graph.getSuccessors(pred))
		if (!visited[succ]) {
		    nodes.add(succ);
		    visited[succ] = true;
		    toVisit.add(succ);
		}
	}
	return nodes;
    }

    public class ResultProfondeur {
	public final int[] debut;
	public final int[] fin;
	public final List<HashSet<Integer>> composantesFortementConnexes;

	public ResultProfondeur(int[] debut, int[] fin, List<HashSet<Integer>> cfc) {
	    this.debut = debut;
	    this.fin   = fin;
	    this.composantesFortementConnexes = cfc;
	}
    }

    class Date {
	private int value;
	public int increase() { return ++value; }
    }

    private void explorerSommet(int sommet, IDirectedGraph graph,
				Date date,
				int[] visited,
				int[] debut,
				int[] fin,
				Set<Integer> composante) {
	debut[sommet] = date.increase();
	visited[sommet] = 1;

	composante.add(sommet);
	for (int successor : graph.getSuccessors(sommet))
	    if (visited[successor] == 0) {
		explorerSommet(successor, graph, date, visited, debut, fin, composante);
	    }

	visited[sommet] = 2;
	fin[sommet] = date.increase();
    }

    public ResultProfondeur parcoursProfondeur(IDirectedGraph graph) {
	List<Integer> nodes = new ArrayList<Integer>();
	for (int i = 0 ; i < graph.getNbNodes() ; ++i)
	    nodes.add(i);
	return parcoursProfondeur(graph, nodes);
    }

    class OrderedNode implements Comparable<OrderedNode> {
	public final int order;
	public final int node;

	public OrderedNode(int order, int node) {
	    this.order = order;
	    this.node  = node;
	}

	public int compareTo(OrderedNode other) {
	    if      (order < other.order) return  1;
	    else if (order > other.order) return -1;
	    else return 0;
	}
    }

    private ResultProfondeur parcoursProfondeurInverse(IDirectedGraph inverse, int[] fin) {
	List<OrderedNode> orderedNodes = new ArrayList<OrderedNode>();
	for (int i = 0 ; i < fin.length ; ++i)
	    orderedNodes.add(new OrderedNode(fin[i], i));
	Collections.sort(orderedNodes);

	List<Integer> nodes = new ArrayList<Integer>();
	for (int i = 0 ; i < fin.length ; ++i) {
	    nodes.add(orderedNodes.get(i).node);
	}
	return parcoursProfondeur(inverse, nodes);
    }

    private ResultProfondeur parcoursProfondeur(IDirectedGraph graph, List<Integer> nodes) {
	final int n = graph.getNbNodes();
	int[] visited = new int[n];
	int[] debut   = new int[n];
	int[] fin     = new int[n];
	Date date = new Date();
	List<HashSet<Integer>> composantesFortementConnexes = new ArrayList<HashSet<Integer>>();

	for (int i : nodes)
	    if (visited[i] == 0) {
		HashSet<Integer> composante = new HashSet<Integer>();
		explorerSommet(i, graph, date, visited, debut, fin, composante);
		composantesFortementConnexes.add(composante);
	    }
	return new ResultProfondeur(debut, fin, composantesFortementConnexes);
    }

    public List<HashSet<Integer>> composantesFortementConnexes(IDirectedGraph graph) {
	int[] fin = parcoursProfondeur(graph).fin;
	IDirectedGraph inverse = graph.computeInverse();
	return parcoursProfondeurInverse(inverse, fin).composantesFortementConnexes;
    }

    public static void main(String[] args) {
	int[][] matrix = GraphTools.generateGraphData(200, 7500, false);

	IDirectedGraph adjList   = new AdjacencyListDirectedGraph(matrix);
	IDirectedGraph adjMatrix = new AdjacencyMatrixDirectedGraph(matrix);
	IDirectedGraph incMatrix = new IncidentMatrixDirectedGraph(matrix);

	Parcours p = new Parcours();
	long startTime;

	for (int i = 0 ; i < 10 ; ++i)
	    p.composantesFortementConnexes(adjList);
	startTime = System.nanoTime();
	p.composantesFortementConnexes(adjList);
	long adjListTime = System.nanoTime() - startTime;

	for (int i = 0 ; i < 10 ; ++i)
	    p.composantesFortementConnexes(adjMatrix);
	startTime = System.nanoTime();
	p.composantesFortementConnexes(adjMatrix);
	long adjMatrixTime = System.nanoTime() - startTime;

	for (int i = 0 ; i < 10 ; ++i)
	    p.composantesFortementConnexes(incMatrix);
	startTime = System.nanoTime();
	p.composantesFortementConnexes(incMatrix);
	long incMatrixTime = System.nanoTime() - startTime;

	System.out.println("### Result ###");
	System.out.println("Adjacency List   => " + adjListTime   + "ns");
	System.out.println("Adjacency Matrix => " + adjMatrixTime + "ns");
	System.out.println("Incidence Matrix => " + incMatrixTime + "ns");
   }
}