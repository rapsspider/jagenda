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

import jagenda.util.Saisie;
import jagenda.Date;
import jagenda.Horaire;

/**
 * 
 * @author Jason BOURLARD
 * @author David PELISSIER
 */

public class TestSaisie {
     public static void main(String[] args) {
    	 String phrase;
    	 float nombreFloat;
    	 double nombreDouble;
    	 int nombreEntier;
    	 int nombreEntierBorne;
    	 Date date;
    	 Horaire heure;
    	 
    	 date = Saisie.lireDate();
    	 phrase = Saisie.lirePhrase();
    	 nombreFloat = Saisie.lireFloat();
    	 nombreDouble = Saisie.lireDouble();
    	 nombreEntier = Saisie.lireInt();
    	 nombreEntierBorne = Saisie.lireInt(0,1);
    	 heure = Saisie.lireHeure();
    	 
    	 System.out.println("\n\n---------------------------\n");
    	 System.out.println("Votre phrase  : " + phrase);
    	 System.out.println("Votre Flotant : " + nombreFloat);
    	 System.out.println("Votre Double  : " + nombreDouble);
    	 System.out.println("Votre entier  : " + nombreEntier);
    	 System.out.println("Votre entier  : " + nombreEntierBorne);
    	 System.out.println("Votre date    : " + date);
    	 System.out.println("Votre heure   : " + heure);
    	 
     }
}
