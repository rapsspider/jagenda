/*
 * Fichier :	TestHoraire.java
 * Package :	jagenda.tests
 * Projet jAgenda - Info 1 2011-2012
 *
 */
package jagenda.tests;

import jagenda.Agenda;
import jagenda.Horaire;
import static jagenda.Principale.*;

/**
 * TODO Classe <code></code>.
 * 
 * @author 	Jason BOURLARD
 * @author 	David PELISSIER
 * @version 
 */
public class TestHoraire {

    /**
     * TODO Commenter la méthode <code>main</code>
     * @param args
     */
    public static void main(String[] args) {
        agenda = new Agenda();
        
        // TODO Auto-generated method stub
        Horaire dateTest1 = new Horaire();
        System.out.println(dateTest1.hashCode());
        
        Horaire dateTest2 = new Horaire("19/05/2012","23h50");
        System.out.println(dateTest2.hashCode());
        
        Horaire dateTest3 = new Horaire("19/05/2012","23h50");
        System.out.println(dateTest3.hashCode());
        
        System.out.println(dateTest3.equals(dateTest2));
        System.out.println(dateTest3.compareTo(dateTest2));
        System.out.println(dateTest1.compareTo(dateTest2));

    }

}
