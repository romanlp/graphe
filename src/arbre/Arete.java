package arbre;

import java.util.Comparator;

//Structure d'ar�te avec un debut, une fin et un cout
public class Arete {
	private int from;
	private int to;
	private int cout;
	
	public Arete(int from, int to , int cout) {
		// TODO Auto-generated constructor stub
		this.from = from;
		this.to = to;
		this.cout = cout;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}

	public int getCout() {
		return cout;
	}

	public void setCout(int cout) {
		this.cout = cout;
	}

	// retourne l'entier le plus grand
	public static int compareArete(Arete a, Arete b){
		return Integer.compare(a.getCout() , b.getCout());
	}
}
