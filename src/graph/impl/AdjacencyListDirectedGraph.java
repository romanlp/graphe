package graph.impl;

import graph.IDirectedGraph;
import graph.IUndirectedGraph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roman on 02/02/2015.
 */
public class AdjacencyListDirectedGraph implements IDirectedGraph {

    private List<Integer> node;
    private List<Integer> succ;

    public AdjacencyListDirectedGraph(int[][] matrice) {

        this.node = new ArrayList<Integer>();
        this.succ = new ArrayList<Integer>();

        int indiceSucc = 0;
        for (int i = 0; i < matrice.length; i++) {
            node.add(indiceSucc);
            for (int j = 0; j < matrice.length; j++) {
                if (matrice[i][j] == 1) {
                    succ.add(j);
                    indiceSucc ++;
                }
            }
        }
        this.node.add(indiceSucc);
        this.succ.add(null);
    }

    public AdjacencyListDirectedGraph(List<Integer> nodeInv, List<Integer> succInv) {

        this.node = new ArrayList<Integer>();
        this.succ = new ArrayList<Integer>();

        this.node = nodeInv;
        this.succ = succInv;
    }


    public AdjacencyListDirectedGraph(IDirectedGraph graph){

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
    public int getNbNodes() {

        return this.node.size() - 1;
    }

    @Override
    public int[][] toAdjacencyMatrix() {

        int[][] matrice = new int[getNbNodes()][getNbNodes()];
        for (int i = 0; i < getNbNodes(); i++) {
            int[] successors = getSuccessors(i);
            for (int j = 0; j < successors.length; j++) {
                matrice[i][successors[j]] = 1;
            }
        }
        return matrice;
    }

    @Override
    public int getNbArcs() {

        return (this.succ.size() - 1);
    }

    @Override
    public boolean isArc(int from, int to) {

        boolean ret = false;
        int indiceSuccStart = this.node.get(from);
        int indiceSuccStop = this.node.get(from+1);
        for (int i = 0; i < indiceSuccStop - indiceSuccStart; i++) {
            if (this.succ.get(indiceSuccStart + i) == to) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    @Override
    public void removeArc(int from, int to) {

        if(isArc(from, to)) {
            for (int i = 0; i < node.get(from + 1) - node.get(from); i++) {
                if (succ.get(node.get(from) + i) == to) {
                    succ.remove(node.get(from) + i);
                    break;
                }
            }

            for (int i = 1; i < node.size() - from; i++) {
                node.set(from + i, node.get(i + from) - 1);
            }
        }
    }

    @Override
    public void addArc(int from, int to) {

        if(!isArc(from, to)) {
            for (int i = 1; i < node.size() - from; i++) {
                node.set(i + from, node.get(from+i) + 1);
            }

            Integer save = null;
            Integer toPut = to;
            for (int i = 0; i < succ.size() - node.get(from); i++) {
                save = succ.get(node.get(from) + i);
                succ.set(node.get(from) + i, toPut);
                toPut = save;
            }
            succ.add(null);
        }
    }

    @Override
    public int[] getSuccessors(int x) {

        ArrayList<Integer> successors = new ArrayList<Integer>();
        int indiceStart = node.get(x);
        int indiceStop = node.get(x + 1);
        while (indiceStop - indiceStart > 0) {
            successors.add(succ.get(indiceStart));
            indiceStart ++;
        }

        int[] ret = new int[successors.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = successors.get(i);
        }
        return ret;
    }

    @Override
    public int[] getPredecessors(int x) {

        List<Integer> predecessors = new ArrayList<Integer>();
        List<Integer> indicesSucc = new ArrayList<Integer>();
        for (int i = 0; i < succ.size() - 1; i++) {
            if (succ.get(i) == x) {
                indicesSucc.add(i);
            }
        }
        for (int i = 0; i < indicesSucc.size(); i++) {
            for (int j = 0; j < node.size() - 1; j++) {
                if(indicesSucc.get(i) >= node.get(j) && indicesSucc.get(i) < node.get(j+1)) {
                    predecessors.add(j);
                    break;
                }
            }
        }
        int[] ret = new int[predecessors.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = predecessors.get(i);
        }
        return ret;
    }

    @Override
    public IDirectedGraph computeInverse() {
        int[] d           = new int[this.getNbNodes()];
        int[] inverseNode = new int[this.getNbNodes()+1];
        int[] inverseSucc = new int[this.getNbArcs()+1];


        for (int i = 0 ; i<this.getNbArcs() ; i++){
            d[this.succ.get(i)]++;
        }

        inverseNode[0] = 0;
        for (int k = 1 ; k<inverseNode.length ; k++){
            inverseNode[k] = inverseNode[k-1] + d[k-1];
        }

        for (int k = 0 ; k<this.getNbNodes() ; k++){
            for (int i = node.get(k) ; i<node.get(k+1) ; i++){
                inverseSucc[inverseNode[succ.get(i)+1]-d[succ.get(i)]] = k;
                d[succ.get(i)]--;
            }
        }

        ArrayList<Integer> listNode = new ArrayList<Integer>();
        for (int i = 0; i < inverseNode.length; i++) {
            listNode.add(inverseNode[i]);
        }
        ArrayList<Integer> listSucc = new ArrayList<Integer>();
        for (int i = 0; i < inverseSucc.length; i++) {
            listSucc.add(inverseSucc[i]);
        }
        listSucc.set(listSucc.size() - 1, null);
        return new AdjacencyListDirectedGraph(listNode, listSucc);
    }

    public void explorerGraphe(){

    }
}
