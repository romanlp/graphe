package graph.impl;

import graph.IUndirectedValuedGraph;

/**
 * Created by Roman on 10/02/2015.
 */
public class Floyd {

    public int[][] apply(IUndirectedValuedGraph graph){

        final int n = graph.getNbNodes();
        int [][] pred = new int[n][n];
        int [][] dist = new int[n][n];
        
        for (int i = 0; i < n ; i++){
            pred[i][i] = i;
            dist[i][i] = 0;
            for(int j : graph.getNeighbors(i)){
                dist[i][j] = graph.getValue(i, j);
                pred[i][j] = i;
            }
        }
        
        for (int k = 0; k < n ; k++){
            for(int i = 0; i < n ; i++){
                for(int j = 0; j < n ; j++){
                    if (pred[j][k] !=0 && pred[k][i] != 0) {
                        if (dist[i][k] + dist[k][j] < dist[i][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
                            pred[i][j] = pred[k][j];
                        }
                    }

                }
            }
        }
        
        return pred;
    }
}
