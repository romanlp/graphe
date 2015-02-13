package arbre;

import graph.IUndirectedGraph;

// Un arbre compos� de sommets et de couts, pas utilis�.
public class ArbreCout implements IUndirectedGraph{
	private int[] sommets;
	private int[] couts;
	
	public ArbreCout(int n) {
		// TODO Auto-generated constructor stub
		this.sommets = new int[n];
		this.couts = new int[n];
	}
	public ArbreCout(int[] sommets,int[]couts){
		this.sommets = sommets;
		this.couts = couts;		
	}
	
	@Override
	public int getNbNodes() {
		// TODO Auto-generated method stub
		return sommets.length;
	}
	@Override
	public int[][] toAdjacencyMatrix() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getNbEdges() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean isEdge(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void removeEdge(int x, int y) {
		// TODO Auto-generated method stub
		
	}

    @Override
    public void addEdge(int x, int y) {

    }

    @Override
    public int[] getNeighbors(int x) {
        return new int[0];
    }
		

}
