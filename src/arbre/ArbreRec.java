package arbre;

import java.util.ArrayList;
import java.util.Arrays;

import graph.IDirectedGraph;

//Repr�sentation R�cursive d'un arbre. Pas utilis�
public class ArbreRec {
	private int valeur;
	ArbreRec[] fils;

	public ArbreRec(int v, int n) {
		// TODO Auto-generated constructor stub
		this.valeur = v;
		this.fils = new ArbreRec[n];
	}

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public ArbreRec[] getFils() {
        return fils;
    }

	public void setFils(ArbreRec[] fils) {
		this.fils = fils;
	}

	public int getNbFils(){
		return this.getFils().length;
	}
	public void ajouterNoeud(){

	}

	// count commence � 0
	public static int[][] prefixe(ArbreRec arbre, int count, int[][] tab, int index){
		System.out.println("une fois");
		ArbreRec[] fils = arbre.getFils();
		int nbFils = fils.length;
		tab[0][0] = 0;
		if(nbFils == 0){
			return tab;
		}
		else{
			index++;
			for (int i = 0; i<nbFils ; i++){
				count++;
				tab[index][i] = count;
				prefixe(fils[i],count,tab,index);
			}
		}
		return tab;
	}
	//count commence � n-1
	//avec un tableau de 0 de taille n*n
	public static int[][] postfixe(ArbreRec arbre, int count, int[][] tab){
		ArbreRec[] fils = arbre.getFils();
		int nbFils = fils.length;
		tab[0][0] = tab.length;
		if(nbFils == 0){
			return tab;
		}
		else{
			for (int i = 0; i<nbFils ; i++){
				count--;
				tab[count][i] = count;
				postfixe(fils[i],count,tab);
			}
		}
		return tab;
	}
	public static ArrayList<Integer> prefixe(ArbreRec graphe){

		ArrayList<Integer> atteint = new ArrayList<Integer>();
		int i = 0 ;
		atteint.add(graphe.getValeur());
		while(i<graphe.getNbFils() && atteint.size()<graphe.getNbFils()){
			if(!atteint.contains(i)){
				for (ArbreRec s : graphe.getFils()) {
					if (!atteint.contains(s)) {
						atteint.add(s.getValeur());
						explorerSommet(s, atteint);
					}
				}
			}
			i++;
		}

		return atteint;
	}


	public static void explorerSommet(ArbreRec s, ArrayList<Integer> atteint) {
		for (ArbreRec t : s.getFils()) {
			if (!atteint.contains(t.getValeur())) {
				atteint.add(t.getValeur());
				
			}
		}
		
	} 
	
	public String toString(){
		String res = "|";
		res+=this.getValeur();
		for (int i = 0 ; i<this.getNbFils();i++){
			res+=this.getFils()[i].toString();
		}
		res+=" |";
		return res;
	}


}
