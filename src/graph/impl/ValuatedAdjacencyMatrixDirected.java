package graph.impl;

import graph.IDirectedGraph;
import graph.IDirectedValuedGraph;

//A tester
//sur la diagonale on met 0 et +infini et sur les chemins inexistants 
public class ValuatedAdjacencyMatrixDirected implements IDirectedValuedGraph {
	private int[][] adjacencyMatrix;
	
	public ValuatedAdjacencyMatrixDirected(int[][] adjacencyMatrix) {
		// TODO Auto-generated constructor stub
		this.adjacencyMatrix = adjacencyMatrix;
	}

	public ValuatedAdjacencyMatrixDirected(int n) {
		// TODO Auto-generated constructor stub
		int[][] adjacencyMatrix = new int[n][n];
		for (int i = 0 ; i< n ; i++){
			for (int j = 0 ; j < n ; j++){
			    if(i!=j){
					adjacencyMatrix[i][j] = Integer.MAX_VALUE;
				}
				else{
					adjacencyMatrix[i][i] = 0;
				}
			}
		}
		this.adjacencyMatrix = adjacencyMatrix;
	}

	public int get(int x , int y ){
        
		return this.adjacencyMatrix[x][y];
	}
	public void set(int x, int y , int value){
		
        this.adjacencyMatrix[x][y] = value;
	}
	
	
	@Override
	public int getNbNodes() {
		// TODO Auto-generated method stub
		return this.toAdjacencyMatrix().length;
	}

	@Override
	public int[][] toAdjacencyMatrix() {
		// TODO Auto-generated method stub
		return this.adjacencyMatrix;
	}

	public int[][] getAdjacencyMatrix() {
		
        return adjacencyMatrix;
	}

	public void setAdjacencyMatrix(int[][] adjacencyMatrix) {
		
        this.adjacencyMatrix = adjacencyMatrix;
	}

	@Override
	public int getNbArcs() {
		// TODO Auto-generated method stub
		int compteur = 0;
		for (int i = 0 ; i<this.toAdjacencyMatrix().length ; i++){
			for (int j = 0 ; j<this.toAdjacencyMatrix()[0].length ; j++){
				if(this.get(i, j) != Integer.MAX_VALUE){
					compteur++;
				}
			}
		}
		return compteur;
	}

    @Override
	public boolean isArc(int from, int to) {
		// TODO Auto-generated method stub
		return this.get(from, to) != Integer.MAX_VALUE && from!=to;
	}

	@Override
	public void removeArc(int from, int to) {
		// TODO Auto-generated method stub
		this.set(from, to, Integer.MAX_VALUE);
	}

    @Override
    public void addArc(int from, int to) {
        this.addArc(from, to, 1);
    }

    @Override
	public void addArc(int x, int y, int weight) {
		// TODO Auto-generated method stub
		this.set(x, y, weight);
	}

	@Override
	public int[] getSuccessors(int x) {
		// TODO Auto-generated method stub
		int nbSucc = 0 ;
		for (int i = 0 ; i< this.getAdjacencyMatrix().length ; i++){
			if (this.isArc(x, i)){
				nbSucc++;
			}
		}
		int compteur = 0;
		int[] res = new int[nbSucc];
		for (int index = 0 ; index<this.getAdjacencyMatrix().length ; index++){
			if(this.isArc(x, index)){
			res[compteur] = index;
			compteur++;
			}
		}
		return res;
	}

	@Override
	public int[] getPredecessors(int y) {
		// TODO Auto-generated method stub
		int nbPrec = 0 ;
		for (int i = 0 ; i< this.getAdjacencyMatrix().length ; i++){
			if (this.isArc(i, y)){
				nbPrec++;
			}
		}
		int compteur = 0;
		int[] res = new int[nbPrec];
		for (int index = 0 ; index<this.getAdjacencyMatrix().length ; index++){
			if(this.isArc(index, y)){
			res[compteur] = index;
			compteur++;
			}
		}
		return res;
	}

	
	@Override
	public IDirectedGraph computeInverse() {
		// TODO Auto-generated method stub
		int[][] inverse = new int[this.adjacencyMatrix.length][this.adjacencyMatrix[0].length];
		//normalement elle est carr�e donc pas de probl�me pour la taille

		for (int i = 0; i<this.adjacencyMatrix.length; i++){
			for(int j = i; j<this.adjacencyMatrix[i].length;j++){
				if(i!=j){
					inverse[j][i] = this.adjacencyMatrix[i][j];
					inverse[i][j] = this.adjacencyMatrix[j][i];
				}
				else{
					inverse[i][i] = 0;
				}
			}
		}
		return new AdjacencyMatrixDirectedGraph(inverse);

	}

    @Override
    public int getValue(int x, int y) {
        return 0;
    }

    @Override
    public int[][] toValuedMatrix() {
        return new int[0][];
    }
}
