/*
 * Fichier :    TestFichier.java
 * Package :    jagenda.test
 * Projet jAgenda - Info 1 2011-2012
 *
 * $Revision$
 * $Date$
 * $HeadURL$
 */

package jagenda.tests;

import static jagenda.Principale.*;
import jagenda.Agenda;

/**
 * 
 * @author Jason BOURLARD
 * @author David PELISSIER
 */

public class TestSaisie {
    /**
     * TODO Commenter la méthode <code>main</code>
     * @param args
     */
    public static void main(String[] args) {
         agenda = new Agenda();
         String date = agenda.saisie.lireDate();
         String heureDebut = agenda.saisie.lireHeure("début");
         String heureFin = agenda.saisie.lireHeure("fin");
     }
}
