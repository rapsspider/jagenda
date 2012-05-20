/*
 * Fichier :    TestFichier.java
 * Package :    jagenda.test
 * Projet jAgenda - Info 1 2011-2012
 *
 * $Revision$
 * $Date$
 * $HeadURL$
 */

package jagenda.test;
import jagenda.Date;
import jagenda.util.Saisie;

/**
 * 
 * @author Jason BOURLARD
 * @author David PELISSIER
 */

public class TestDate {
    public static void main(String arg[]) {
    	Date date = Saisie.lireDate();
    	System.out.println("Le premier janvier de l'année " + date.getAnnee() + " est " + date.premierJour());
    	System.out.println("Le numéro du jour de    " + date + " est " + date.getNumJour());
    	System.out.println("Le numéro de semaine de " + date + " est " + date.getSemaine());
    }
}
