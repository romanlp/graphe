package arbre;

import java.util.Arrays;

// tas binaire compos� d'ar�tes. Utilis� dans PRIM et KRUSKAL
public class tasAretes{
	private Sommet[] sommets;
	private Sommet racine;
	private int[] from, to;

	//attention, il faut que from, to et sommets fassent la meme taille
	public tasAretes(int racine, int profondeur, int[] from, int[] to) {
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
		System.out.println(Arrays.toString(sommets));
		this.from = from;
		this.to = to;
	}
	
	public tasAretes(int racine, int from, int to){
		this.racine = new Sommet(racine,0);
		this.sommets = new Sommet[1];
		this.sommets[0] = this.racine;
		this.from = new int[1];
		this.from[0] = from;
		this.to = new int[1];
		this.to[0] = to;
	}
	public Sommet[] getSommets() {
		return sommets;
	}

	public void setSommets(Sommet[] sommets) {
		this.sommets = sommets;
	}

	public int[] getFrom() {
		return from;
	}

	public void setFrom(int[] from) {
		this.from = from;
	}

	public int[] getTo() {
		return to;
	}

	public void setTo(int[] to) {
		this.to = to;
	}

	//ajoute une feuille en la positionnant correctement dans le tas.
	public void ajouterFeuille(int valeur, int from, int to){
		Sommet[] copieS = this.sommets.clone();
		int[] copieF = this.from.clone();
		int[] copieT = this.to.clone();

		Sommet s = new Sommet(valeur,copieS.length);
		this.sommets = new Sommet[this.sommets.length+1];
		this.from = new int[copieS.length+1];
		this.to = new int[copieS.length+1];


		for (int i = 0 ; i<copieS.length ; i ++ ){
			this.sommets[i] = copieS[i];
			this.from[i] = copieF[i];
			this.to[i] = copieT[i];
		}
		this.sommets[copieS.length] = s;
		this.from[copieF.length] = from;
		this.to[copieT.length] = to;

		this.remonter(s);
		this.racine = this.sommets[0];
	}

	//echange un sommet avec son pere tant que le p�re � une valeur inf�rieure au sommet
	private void remonter(Sommet s) {
		// TODO Auto-generated method stub
		Sommet pere;

		do{
			pere = this.getPere(s);
			if(pere!= null && s.getValeur() < pere.getValeur()){
				change(s,pere);
				remonter(s);
			}
		}while(pere!=null && s.getValeur()<pere.getValeur());
	}

	//supprime le sommet s
	public void supprimerFeuille(Sommet s){

		this.descendre(s);
		Sommet[] copieS = this.getSommets().clone();
		int[] copieF = this.getFrom().clone();
		int[] copieT = this.getTo().clone();

		this.from = new int[copieF.length-1];
		this.to = new int[copieT.length-1];
		this.sommets = new Sommet[copieS.length-1];

		for (int i = 0 ; i<s.getIndex() ; i ++ ){
			this.sommets[i] = copieS[i];
			this.from[i] = copieF[i];
			this.to[i] = copieT[i];
		}
		for(int i = s.getIndex()+1 ; i<copieS.length ; i++){
			this.sommets[i-1] = copieS[i];
			this.sommets[i-1].setIndex(this.sommets[i-1].getIndex()-1);
			this.from[i-1] = copieF[i];
			this.to[i-1] = copieT[i];
		}

	}

	// Echange le sommet s avec son premier fils jusqu'� ce qu'il soit une feuille
	private void descendre(Sommet s) {
		// TODO Auto-generated method stub
		int nbFils = this.getNbFils(s);
		while(nbFils!=0){
			//System.out.println("s est : "+s+". Il a pour index : " + s.getIndex());
			//System.out.println("   le sommet s a : " + nbFils+"  fils");
			nbFils = this.getNbFils(s);
			if(nbFils!=0){
				Sommet[] fils = this.getFils(s);
				if(nbFils==1){
					this.change(s, fils[0]);
				}
				else{
					if(fils[0].getValeur() > fils[1].getValeur()){
						this.change(s, fils[1]);
					}
					else{
						this.change(s, fils[0]);
					}
				}
			}
		}

	}

	/**
	 * @return
	 *	un tableau d'entiers avec :
	 *		- 0 : l'index de la racine, qui est toujours 0
	 *		- 1 : la valeur de la racine (qui est la plus petite) [cout de l'ar�te]
	 *		- 2 : la provenance de la racine (from) [point de depart de l'arc]
	 *		- 3 : la destination de l'ar�te (to)
	 **/
	public int[] removeFirst(){
		int[] res = new int[4];
		res[0] = this.racine.getIndex();
		res[1] = this.racine.getValeur();
		res[2] = this.from[0];
		res[3] = this.to[0];

		this.supprimerFeuille(this.racine);
		if(!this.isEmpty()){
			this.racine = this.getSommets()[0];
		}
		else{
			this.racine = null;
		}
		return res;
	}

	public Sommet getRacine() {
		// TODO Auto-generated method stub
		return this.racine;
	}
	
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

	public Sommet[] getFils(Sommet s) {
		// TODO Auto-generated method stub
		int index = s.getIndex();
		Sommet[] fils = new Sommet[this.getNbFils(s)];
		for (int i =  0 ; i<fils.length ; i++){
			fils[i] = this.sommets[2*index+1+i]; 
		}
		return fils;
	}

	public Sommet getPere(Sommet s) {
		// TODO Auto-generated method stub
		if(s.getIndex()-1/2>=0){
			return this.sommets[(s.getIndex()-1)/2];
		}
		else{
			return null;
		}
	}

	public int getNbNodes() {
		// TODO Auto-generated method stub
		return this.getSommets().length;
	}

	//retourne true si le nombre de sommets de l'arbre est nul, false sinon
	public boolean isEmpty(){
		if(this.sommets.length == 0){
			return true;
		}
		else{
			return false;
		}
	}

	public void change(Sommet s, Sommet p){
		Sommet s2 = new Sommet(s.getValeur(), s.getIndex());
		int from2 = this.from[s.getIndex()];
		int to2 = this.to[s.getIndex()];
		this.sommets[s.getIndex()] = p;
		this.from[s.getIndex()] = this.from[p.getIndex()]; 
		this.to[s.getIndex()] = this.to[p.getIndex()];
		s.setIndex(p.getIndex());

		this.sommets[p.getIndex()] = s;
		this.from[p.getIndex()] = from2;
		this.to[p.getIndex()] = to2;

		p.setIndex(s2.getIndex());

	}
	public String toString(){
		String res = "[";
		for(int i = 0 ; i<this.getSommets().length ; i++){
			res+= this.sommets[i].toString()+",";
		}
		res+="]";
		return res;
	}
}
