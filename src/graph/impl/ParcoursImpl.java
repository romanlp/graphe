package graph.impl;

import graph.IDirectedGraph;

import java.util.ArrayList;

/**
 * Created by Roman on 12/02/2015.
 */
public class ParcoursImpl {

    public static ArrayList<Integer> parcoursProfondeur(IDirectedGraph graphe) {
        int[] debut = new int[graphe.getNbNodes()];
        int[] fin = new int[graphe.getNbNodes()];
        int compteur = 0;
        ArrayList<Integer> atteint = new ArrayList<Integer>();
        int i = 0;
        while (i < graphe.getNbNodes() && atteint.size() < graphe.getNbNodes()) {
            if (!atteint.contains(i)) {
                atteint.add(i);
                debut[i] = compteur;
                compteur++;
                System.out.println("debut de " + i + " : " + debut[i]);
                for (int s : graphe.getPredecessors(i)) {
                    if (!atteint.contains(s)) {
                        atteint.add(s);
                        debut[s] = compteur;
                        System.out.println("début de " + s + " : " + debut[s]);
                        compteur = explorerSommet(s, atteint, graphe, compteur, debut, fin);
                        fin[s] = compteur;
                        compteur++;
                        System.out.println("fin de " + s + " : " + fin[s]);
                    }
                }
                fin[i] = compteur;
                compteur++;
                System.out.println("fin de " + i + " : " + fin[i]);
            }
            i++;
        }
        return atteint;
    }

    public static int explorerSommet(int s, ArrayList<Integer> atteint, IDirectedGraph graphe, int compteur, int[] debut , int[] fin) {
        int res = compteur;
        for (int t : graphe.getPredecessors(s)) {
            if (!atteint.contains(t)) {
                atteint.add(t);
                res ++ ;
                debut[t] = res;
                System.out.println("debut de "+ t + " : " + debut[t]);
                res = explorerSommet(t,atteint, graphe,res, debut,fin);
                fin[t] = res;
                System.out.println("fin de "+ t + " : " + fin[t]);
            }
        }
        res++;
        return res;
    }
    
}
