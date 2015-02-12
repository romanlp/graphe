package graph.impl;

import graph.IUndirectedGraph;
import graph.IUndirectedValuedGraph;
import graph.IValuedGraph;

import java.util.List;
import java.util.ArrayList;

public class ValuedAdjacencyMatrixUndirectedGraph implements IUndirectedValuedGraph {

    private int[][] matrix;

    private int[][] weigths;

    public ValuedAdjacencyMatrixUndirectedGraph(int n) {

        this.matrix = new int[n][n];
        this.weigths = new int[n][n];
    }
    
    public ValuedAdjacencyMatrixUndirectedGraph(int[][] matrix, int[][] weigths) {

        this.matrix = copy(matrix);
        this.weigths = copy(weigths);
    }

    public ValuedAdjacencyMatrixUndirectedGraph(IUndirectedValuedGraph graph) {

        this(graph.toAdjacencyMatrix(), graph.toValuedMatrix());
    }

    @Override
    public int getNbEdges() {
        int nbEdges = 0;
        for (int x = 0 ; x < matrix.length ; ++x)
            for (int y = x ; y < matrix.length ; ++y)
                if (isEdge(x, y))
                    ++nbEdges;
        return nbEdges;
    }

    @Override
    public int getValue(int x, int y) {

        return weigths[x][y];
    }

    @Override
    public boolean isEdge(int x, int y) {

        return matrix[x][y] == 1;
    }

    @Override
    public void removeEdge(int x, int y) {

        this.weigths[y][x] = this.weigths[y][x] = 0;
        this.matrix[x][y] = this.matrix[y][x]  = 0;
    }

    @Override
    public void addEdge(int x, int y, int weight) {

        this.weigths[x][y] = this.weigths[y][x] = weight;
        this.matrix[x][y] = this.matrix[y][x] = 1;
    }

    @Override
    public void addEdge(int x, int y) {

        addEdge(x, y, 1);
    }

    @Override
    public int[] getNeighbors(int x) {
        List<Integer> neighbors = new ArrayList<Integer>();
        for (int i = 0 ; i < getNbNodes() ; ++i)
            if (isEdge(x, i))
                neighbors.add(i);
        int[] res = new int[neighbors.size()];
        for (int i = 0 ; i < neighbors.size() ; ++i)
            res[i] = neighbors.get(i);
        return res;
    }

    @Override
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

    private boolean[][] copy(boolean[][] matrix) {
        boolean[][] copy = new boolean[matrix.length][matrix.length];
        for (int i = 0 ; i < matrix.length ; ++i)
            for (int j = 0 ; j < matrix.length ; ++j)
                copy[i][j] = matrix[i][j];
        return copy;
    }

    @Override
    public int[][] toAdjacencyMatrix() {

        return copy(weigths);
    }

    @Override
    public int[][] toValuedMatrix() {

        return copy(weigths);
    }

    @Override
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