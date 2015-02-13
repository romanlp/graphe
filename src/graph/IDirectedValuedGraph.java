package graph;

import graph.IDirectedGraph;
import graph.IValuedGraph;

public interface IDirectedValuedGraph extends IValuedGraph, IDirectedGraph {

    public void addArc(int x, int y, int weight);
}