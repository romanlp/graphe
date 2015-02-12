package graph;

public interface IUndirectedValuedGraph extends IValuedGraph, IUndirectedGraph {

    public void addEdge(int x, int y, int weight);
}