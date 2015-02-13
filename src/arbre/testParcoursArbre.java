package arbre;

import graph.GraphTools;
import graph.impl.ValuatedAdjacencyMatrixDirected;

import java.util.ArrayList;
public class testParcoursArbre {



	public static void main(String[] args) {
		ArbreRec fils0 = new ArbreRec(1, 0);
		ArbreRec fils1 = new ArbreRec(2,0);
		ArbreRec fils2 = new ArbreRec(3,1);
		ArbreRec fils21 = new ArbreRec(1000,0);


		ArbreRec[] filsDe2 = new ArbreRec[1];
		filsDe2[0] = fils21;
		fils2.setFils(filsDe2);

		ArbreRec[] fils = new ArbreRec[3];
		fils[0] = fils0;
		fils[1] = fils1;
		fils[2] = fils2;
		ArbreRec arbre = new ArbreRec(5,2);
		arbre.setFils(fils);
		System.out.println(arbre);
		test(arbre,4);
	}


	public static void test(ArbreRec arbre, int n){
		System.out.println("\nPr�fixe");
		testPrefixe2(arbre, n);
		
		
	}



	//Cette m�thode semble fonctionner. J'ai utilis� la m�me structure que pour le parcours en profondeur r�cursif
	public static void testPrefixe2(ArbreRec arbre,int n){
		ValuatedAdjacencyMatrixDirected valTabPe = new ValuatedAdjacencyMatrixDirected(n);
		for (int i = 0 ; i< n ;i++){
			valTabPe.addArc(i, i, Integer.MAX_VALUE);
		}		
		int[][] tabPe = valTabPe.getAdjacencyMatrix();
		System.out.print("\non doit avoir une matrice vide :");
	
		GraphTools.show(tabPe);
	
		ArrayList<Integer> prefixe = ArbreRec.prefixe(arbre);
	
		System.out.println(prefixe.toString());
	
	
	}

	
	private static void testPostfixe(ArbreRec arbre, int n) {
		// TODO Auto-generated method stub
		ValuatedAdjacencyMatrixDirected  valTabPo = new ValuatedAdjacencyMatrixDirected(n);
		for (int i = 0 ; i< n ;i++){
			valTabPo.addArc(i, i, Integer.MAX_VALUE);
		}		
		int[][] tabPo = valTabPo.getAdjacencyMatrix();
		System.out.print("\non doit avoir une matrice vide :");
		GraphTools.show(tabPo);

		int[][] postfixe = ArbreRec.postfixe(arbre,n-1,tabPo);
		GraphTools.show(postfixe);


    }


}
