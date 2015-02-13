package eval.arbre;

import graph.GraphTools;
import graph.impl.Floyd;
import graph.impl.ValuedAdjacencyMatrixUndirectedGraph;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Roman on 13/02/2015.
 */
public class ParcoursArbreTest {
    
    private Arbre a;

    private List<String> infixeAttendu;
    private List<String> prefixeAttendu;
    private List<String> postfixeAttendu;
    
    @Before
    public void setUp() {
        a = new Arbre("1");
        Arbre a1 = new Arbre("1.1");
        Arbre a2 = new Arbre("1.2");
        a.add(a1);
        a.add(a2);
        a.add(new Arbre("1.3"));

        a1.add(new Arbre("1.1.1"));
        a1.add(new Arbre("1.1.2"));

        Arbre a3 = new Arbre("1.2.1");
        a2.add(a3);
        a3.add(new Arbre("1.2.1.1"));
        
        infixeAttendu = new ArrayList<String>();
        infixeAttendu.add("1.1.1");
        infixeAttendu.add("1.1");
        infixeAttendu.add("1.1.2");
        infixeAttendu.add("1");
        infixeAttendu.add("1.2.1.1");
        infixeAttendu.add("1.2.1");
        infixeAttendu.add("1.2");
        infixeAttendu.add("1.3");

        prefixeAttendu = new ArrayList<String>();
        prefixeAttendu.add("1");
        prefixeAttendu.add("1.1");
        prefixeAttendu.add("1.1.1");
        prefixeAttendu.add("1.1.2");
        prefixeAttendu.add("1.2");
        prefixeAttendu.add("1.2.1");
        prefixeAttendu.add("1.2.1.1");
        prefixeAttendu.add("1.3");
        
        postfixeAttendu = new ArrayList<String>();
        postfixeAttendu.add("1.1.1");
        postfixeAttendu.add("1.1.2");
        postfixeAttendu.add("1.1");
        postfixeAttendu.add("1.2.1.1");
        postfixeAttendu.add("1.2.1");
        postfixeAttendu.add("1.2");
        postfixeAttendu.add("1.3");
        postfixeAttendu.add("1");
        
    }
    
    @Test
    public void prefixeTest() {
        List<String> prefixe = new ArrayList<String>();
        ParcoursArbre.prefixe(a, prefixe);
        
        assertEquals(prefixeAttendu, prefixe);
        
    }
    
    @Test
    public void infixeTest() {
        List<String> infixe = new ArrayList<String>();
        ParcoursArbre.infixe(a, infixe);

        assertEquals(infixeAttendu, infixe);

    }
    
    @Test
    public void postfixeTest() {
        List<String> postfixe = new ArrayList<String>();
        ParcoursArbre.postfixe(a, postfixe);

        assertEquals(postfixeAttendu, postfixe);

    }
}
