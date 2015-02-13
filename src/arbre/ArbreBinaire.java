package arbre;

import graph.IGraph;

// Structure d'arbre Binaire, form�e d'une racine et d'un tableau de sommets fils
public class ArbreBinaire implements IGraph, IArbre {
	private Sommet[] sommets;
	//private Sommet[] couts;
	private Sommet racine;


	// ce serait bien d'avoir un constructeur d'arbre en fonction d'une matrice
	public ArbreBinaire(int racine, int profondeur) {
		// TODO Auto-generated constructor stub
		int taille = 1;

		for (int i = 1 ; i<=profondeur ; i++){
			taille+=Math.pow(2,i);
		}
		this.racine = new Sommet(racine,0);
		this.sommets = new Sommet[taille];	
		this.sommets[0] = this.racine;

		for(int j = 1 ; j<taille ; j++){
			this.sommets[j] = new Sommet(j,j) ;
		}
	}

	public ArbreBinaire(){
		this(0,1);
	}

	public ArbreBinaire(int racine){
		this(racine, 1);
	}
	@Override
	public Sommet getRacine() {
		// TODO Auto-generated method stub
		return this.racine;
	}

	//n'est pas utilis�
	@Override
	public int[][] toAdjacencyMatrix() {
		// TODO Auto-generated method stub
		return null;
	}

	//retourne le nombre de fils du sommet s
	@Override
	public int getNbFils(Sommet s) {
		// TODO Auto-generated method stub
		int index = s.getIndex();

		if(2*index+2 < this.sommets.length){
			return 2;
		}
		else if(this.sommets.length > 2*index+1 && 2*index+2 >= this.sommets.length){
			return 1;
		}
		else{
			return 0;
		}
	}

	//retourne un tableau de Sommets compos� des fils de s
	@Override
	public Sommet[] getFils(Sommet s) {
		// TODO Auto-generated method stub
		int index = s.getIndex();
		Sommet[] fils = new Sommet[this.getNbFils(s)];
		for (int i =  0 ; i<fils.length ; i++){
			fils[i] = this.sommets[2*index+1+i]; 
		}
		return fils;
	}

	//retourne le pere du sommet s
	@Override
	public Sommet getPere(Sommet s) {
		// TODO Auto-generated method stub
		if((s.getIndex()-1)/2 >0){
			return this.sommets[(s.getIndex()-1)/2];
		}
		else{
			return null;
		}
	}

	//ajoute une feuille � l'arbre en la positionnant correctement
	@Override
	public void ajouterFeuille(int valeur){
		Sommet[] copie = this.sommets.clone();
		Sommet s = new Sommet(valeur,copie.length);
		this.sommets = new Sommet[this.sommets.length+1];
		for (int i = 0 ; i<copie.length ; i ++ ){
			this.sommets[i] = copie[i];
		}
		this.sommets[copie.length] = s;
		this.remonter(s);
	}

	//�change un sommet et son pere si la valeur du sommet est inferieure
	//A celle du pere
	private void remonter(Sommet s) {
		// TODO Auto-generated method stub
		Sommet pere;
		do{
			pere = this.getPere(s);
			if(pere!=null && s.getValeur() < this.getPere(s).getValeur()){
				Sommet s2 = new Sommet(s.getValeur(), s.getIndex());
				this.sommets[s.getIndex()] = pere;
				s2.setIndex(pere.getIndex());
				this.sommets[pere.getIndex()] = s2;
				pere.setIndex(s.getIndex());
				remonter(s);
			}
		}while(pere!=null && s.getValeur() < this.getPere(s).getValeur());
	}
	
	
	//Attention aux cas limites qui n'ont pas encore �t� trait�s
	//retire le sommet s de l'arbre en l'�changeant avec un fils
	public void supprimerFeuille(Sommet s){

		this.descendre(s);
		Sommet[] copie = this.sommets.clone();
		this.sommets = new Sommet[this.sommets.length-1];
		for (int i = 0 ; i<this.sommets.length ; i ++ ){
			this.sommets[i] = copie[i];
		}

	}

	//echange un sommet et son fils jusqu'� atteindre une feuille
	private void descendre(Sommet s) {
		// TODO Auto-generated method stub
		int nbFils = 0;
		do{
			System.out.println("s est : "+s+". Il a pour index : " + s.getIndex());
			nbFils = this.getNbFils(s);
			System.out.println("   le sommet s a : " + nbFils+"  fils");

			Sommet[] fils = this.getFils(s);
			if(nbFils!=0){
				Sommet s2 = new Sommet(s.getValeur(),s.getIndex());
				System.out.println("   on �change s avec : " + fils[0]);
				this.sommets[s2.getIndex()] = fils[0];
				s.setIndex(fils[0].getIndex());
				this.sommets[fils[0].getIndex()] = s;
				fils[0].setIndex(s2.getIndex());
			}

		}while(nbFils!=0);

	}

	//supprime la racine de l'arbre
	public int[] removeFirst(){
		int[] res = {this.racine.getIndex(),this.racine.getValeur()};
		this.supprimerFeuille(this.racine);
		return res;
	}

	//retourne le nombre de noeuds
	@Override
	public int getNbNodes() {
		// TODO Auto-generated method stub
		return sommets.length;
	}

	public String toString(){
		String res = "[";
		for(int i = 0 ; i<this.getNbNodes() ; i++){
			res+= this.sommets[i].toString()+",";
		}
		res+="]";
		return res;
	}

}
