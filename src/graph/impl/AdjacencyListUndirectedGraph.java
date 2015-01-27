package graph.impl;

import graph.IUndirectedGraph;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Roman on 27/01/2015.
 * @author Roman
 */
public class AdjacencyListUndirectedGraph implements IUndirectedGraph {


    private final List<Integer> node;
    private final CopyOnWriteArrayList<Integer> succ;


    public AdjacencyListUndirectedGraph(int[][] matrice){
        this.node = new CopyOnWriteArrayList<Integer>();
        this.succ = new CopyOnWriteArrayList<Integer>();

        int indiceSucc = 0;
        for (int i = 0; i < matrice.length; i++) {
            this.node.add(indiceSucc);
            for (int j = 0; j < matrice.length; j++) {
                if (matrice[i][j] == 1) {
                    this.succ.add(matrice[i][j]);
                    indiceSucc ++;
                }
            }
        }

        this.node.add(indiceSucc);
        this.succ.add(null);
    }

    @Override
    public int getNbEdges() {
        return 0;
    }

    @Override
    public boolean isEdge(int x, int y) {
        return false;
    }

    @Override
    public void removeEdge(int x, int y) {

    }

    @Override
    public void addEdge(int x, int y) {

    }

    @Override
    public int[] getNeighbors(int x) {
        return new int[0];
    }

    @Override
    public int getNbNodes() {
        return 0;
    }

    @Override
    public int[][] toAdjacencyMatrix() {
        return new int[0][];
    }
}
