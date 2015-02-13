package arbre;

//Structure de sommet avec une valeur et un index (pour mieux le retrouver)
public class Sommet {
	private int valeur;
	private int index;
	
	public Sommet(int valeur, int index) {
		// TODO Auto-generated constructor stub
		this.index = index;
		this.valeur = valeur;
	}

	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public String toString(){
		return ""+this.getValeur();
	}
}
