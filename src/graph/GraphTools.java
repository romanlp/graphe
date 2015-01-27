package graph;

/**
 * Created by Roman on 27/01/2015.
 */
public class GraphTools {

    public static int[][] generateGraphData(int n, int m, boolean s){

        int[][] matrice = new int[n][n];


        int loop = m;

        while (loop != 0){
            int x = (int) Math.floor(Math.random()*n);
            int y = (int) Math.floor(Math.random()*n);

            if(matrice[x][y] == 0 && x != y){
                matrice[x][y] = 1;
                if (s){
                    matrice[y][x] = 1;
                }
                loop--;
            }

        }
        return matrice;

    }

    public static void show(int[][] matrice){
        System.out.println();
        for (int i=0 ; i<matrice.length ; i++){
            for (int j=0 ; j<matrice[i].length ; j++){
                System.out.print(matrice[i][j] + " ");
            }
            System.out.println();
        }
    }
}
