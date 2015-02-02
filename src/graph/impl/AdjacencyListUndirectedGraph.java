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
        int[][] matrice = graph.toAdjacencyMatrix();

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

    @Override
    public int getNbEdges() {

        return (this.succ.size()-1)/2;
    }

    @Override
    public boolean isEdge(int x, int y) {

        boolean ret = false;
        int indiceSuccStart = this.node.get(x);
        int indiceSuccStop = this.node.get(x+1);
        for (int i = 0; i < indiceSuccStop - indiceSuccStart; i++) {
            if (this.succ.get(indiceSuccStart + i) == y) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    @Override
    public void removeEdge(int x, int y) {
        if(isEdge(x, y)) {
            for (int i = 0; i < node.get(x + 1) - node.get(x); i++) {
                if (succ.get(node.get(x) + i) == y) {
                    succ.remove(node.get(x) + i);
                    break;
                }
            }

            for (int i = 1; i < node.size() - x; i++) {
                node.set(x + i, node.get(i + x) - 1);
            }
            removeEdge(y, x);
        }
    }

    @Override
    public void addEdge(int x, int y) {
        if(!isEdge(x, y)) {
            for (int i = 1; i < node.size() - x; i++) {
                node.set(i + x, node.get(x + i) + 1);
            }

            Integer save = null;
            Integer toPut = y;
            for (int i = 0; i < succ.size() - node.get(x); i++) {
                save = succ.get(node.get(x) + i);
                succ.set(node.get(x) + i, toPut);
                toPut = save;
            }
            succ.add(null);
            addEdge(y, x);
        }
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
