package graph;

/**
 * Created by Roman on 27/01/2015.
 */
public interface IGraph {

    /**
     * Retourne le nombre de node présents dans le graphe
     * @return nombre de node
     */
    public int getNbNodes();

    /**
     * Retourne la matrice d'adjacence representant le graphe
     * @return matrice d'adjacence
     */
    public int[][] toAdjacencyMatrix();


}
