package arbre;

//Interface d'Arbre.
public interface IArbre {
	
	public Sommet getRacine();
	
	public int[][] toAdjacencyMatrix();
	public int getNbFils(Sommet s);

	public Sommet[] getFils(Sommet s);
	public Sommet getPere(Sommet s);
	public void ajouterFeuille(int s);
	public void supprimerFeuille(Sommet s);
}
