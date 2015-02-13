package eval.arbre;

import java.util.ArrayList;
import java.util.List;

/**
 * COMPLEXITE : O(n)
 * On passe une fois a travers tous les sommets. La complexité est linéaire
 * Created by Roman on 13/02/2015.
 */
public class ParcoursArbre {

    public static void infixe(Arbre a, List<String> list){

        if(!a.getSousArbres().isEmpty()){
            infixe(a.getSousArbres().get(0), list);
        }

        list.add(a.getId());

        if(a.getSousArbres().size() > 1){
            Arbre sousArbre;
            for(int i = 1;i<a.getSousArbres().size();i++){
                sousArbre = a.getSousArbres().get(i);
                infixe(sousArbre, list);
            }
        }

    }


    public static List<String> postfixe(Arbre a, List<String> list){


        if(a.getSousArbres().isEmpty()){
            list.add(a.getId());
            return list;
        }
        else{

            for (Arbre sousArbre : a.getSousArbres()){

                postfixe(sousArbre, list);
            }

            list.add(a.getId());
        }
        return list;

    }

    public static List<String> prefixe(Arbre a, List<String> list){

        list.add(a.getId());
        if(a.getSousArbres().isEmpty()){
            return list;
        }

        for (Arbre sousArbre : a.getSousArbres()){

            prefixe(sousArbre, list);
        }

        return list;

    }

}
