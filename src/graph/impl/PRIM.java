package graph.impl;

        import graph.IUndirectedValuedGraph;

        import java.util.HashSet;
        import java.util.Set;

public class PRIM {

    class Edge {
        public final int x;
        public final int y;
        public final int weight;

        public Edge(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
    }

    private Edge getSmallestEdge(IUndirectedValuedGraph graph, IUndirectedValuedGraph tree, Set<Integer> treeNodes) {
        int x = -1, y = -1;
        int minWeight = Integer.MAX_VALUE;
        for (int i = 0 ; i < graph.getNbNodes() ; ++i)
            if (!treeNodes.contains(i))
                for (int neighbor : graph.getNeighbors(i))
                    if (treeNodes.contains(neighbor) &&
                            graph.getValue(i, neighbor) <= minWeight) {
                        x = neighbor;
                        y = i;
                        minWeight = graph.getValue(i, neighbor);
                    }

        return new Edge(x, y, minWeight);
    }

    public IUndirectedValuedGraph apply(IUndirectedValuedGraph graph) {
        IUndirectedValuedGraph tree = new ValuedAdjacencyMatrixUndirectedGraph(graph.getNbNodes());
        Set<Integer> treeNodes = new HashSet<Integer>();
        treeNodes.add(0);
        for (int i = 0 ; i < tree.getNbNodes() - 1 ; ++i) {
            Edge edge = getSmallestEdge(graph, tree, treeNodes);
            treeNodes.add(edge.y);
            tree.addEdge(edge.x, edge.y, edge.weight);
        }
        return tree;
    }
}