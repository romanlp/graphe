package graph.impl;

import graph.IUndirectedGraph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roman on 27/01/2015.
 * @author Roman
 */
public class AdjacencyListUndirectedGraph implements IUndirectedGraph {


    private final List<Integer> node;
    private final List<Integer> succ;


    public AdjacencyListUndirectedGraph(int[][] matrice){
        this.node = new ArrayList<Integer>();
        this.succ = new ArrayList<Integer>();

        int indiceSucc = 0;
        for (int i = 0; i < matrice.length; i++) {
            this.node.add(indiceSucc);
            for (int j = 0; j < matrice.length; j++) {
                if (matrice[i][j] == 1) {
                    this.succ.add(j);
                    indiceSucc ++;
                }
            }
        }
        this.node.add(indiceSucc);
        this.succ.add(null);
    }

    public AdjacencyListUndirectedGraph(IUndirectedGraph graph){
        this.node = new ArrayList<Integer>();
        this.succ = new ArrayList<Integer>();
        graph.toAdjacencyMatrix();
    }

    @Override
    public int getNbEdges() {

        return (this.succ.size()-1)/2;
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
        List<Integer> subList = succ.subList(this.node.get(x), this.node.get(x + 1));
        int[] neighbors = new int[subList.size()];
        for (int i = 0; i < subList.size(); i++) {
            neighbors[i] = subList.get(i);
        }
        return neighbors;
    }

    @Override
    public int getNbNodes() {

        return this.node.size()-1;
    }

    @Override
    public int[][] toAdjacencyMatrix() {
        int[][] matrice = new int[this.getNbNodes()][this.getNbNodes()];

        for (int i = 0; i < this.getNbNodes(); i++) {
            for (int elem : this.getNeighbors(i)){
                matrice[i][elem] = 1;
            }
        }
        return matrice;
    }
}
