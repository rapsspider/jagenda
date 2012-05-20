/*
 * Fichier :	Saisie.java
 * Package :	jagenda.util
 * Projet jAgenda - Info 1 2011-2012
 *
 * $Revision$
 * $Date$
 * $HeadURL$
 */

package jagenda.util;
import java.io.Console;
import java.util.Scanner;
import jagenda.Horaire;
import jagenda.Date;

/**
 * Outils permettant la <code>Saisie</code> sur l'entr�e standart
 * de donn�es. Ces donn�es peuvent �tre :
 * <lu><li>int</li>
 *     <li>double</li>
 *     <li>float</li>
 *     <li>String</li>
 *     <li>Password (Sortie standart d�sactiv�)</li>
 *     <li>Date (jj/mm/aaaa)</li>
 *     <li>Heure (hh:mm)</li> 
 * </lu>
 * @author Jason BOURLARD
 * @author David PELISSIER
 * @version 1.0.0
 */
public class Saisie {
    
    /**
     * Permet la lecture d'une phrase. L'arr�t de la saisie
     * s'�ffectue lorsque l'utilisateur appuie sur ENTREE.<br>
     * @return la ligne entr�e sur l'entr�e standart
     */
    public static String lirePhrase() {
        Scanner clavier = new Scanner(System.in);
        return clavier.nextLine();
    }

    /**
     * Permet la lecture d'un mot de passe.<br>
     * L'arr�t de la saisie s'�ffectue lorsque l'utilisateur
     * appuie sur ENTREE.<br>
     * @return le tableau de caract�re entr�e par l'utilisateur sur l'entr�e standart.
     */
    public static char[] lirePasse(){
        char[] tableauCar=null;
        Console cons = System.console();
        if (cons != null && (tableauCar = cons.readPassword("%s :", "Password")) != null) {
            return tableauCar;
        }
        return tableauCar;
    }

    /**
     * Permet la lecture d'un flottant de type double
     * lu sur l'entr�e standart.
     * Tant que la donn�e lu sur l'entr�e standart n'est
     * pas un flottant, on demande � l'utilisateur
     * de rentrer la donn�e.<br>
     * L'arr�t d'une saisie s'�ffectue lorsque l'utilisateur
     * appuie sur ENTREE.<br>
     * @return le flottant entr�e par l'utilisateur sur l'entr�e standart
     */
    public static double lireDouble(){
        Scanner clavier = new Scanner(System.in);
        double flottant=0;
        boolean erreur=true;
        
        do {
        	System.out.print("Veuillez entrer un flottant : ");
        	if (clavier.hasNextDouble()) {
        		erreur = false;
        		flottant = clavier.nextDouble();
        	} else {
        		erreur = true;
        		System.err.print("\"" + clavier.nextLine() + "\"");
        		System.err.println(" n'est pas une entree valide.");
        	}
        } while (erreur);
        return flottant;
    }
    
    /**
     * Permet la lecture d'un flottant de type float
     * lu sur l'entr�e standart.
     * Tant que la donn�e lu sur l'entr�e standart n'est
     * pas un flottant, on demande � l'utilisateur
     * de rentrer la donn�e.<br>
     * L'arr�t d'une saisie s'�ffectue lorsque l'utilisateur
     * appuie sur ENTREE.<br>
     * @return le flottant entr�e par l'utilisateur sur l'entr�e standart
     */
    public static float lireFloat() {
        Scanner clavier = new Scanner(System.in);
        float flottant=0;
        boolean erreur=true;
        
        do {
        	System.out.print("Veuillez entrer un flottant : ");
        	if (clavier.hasNextFloat()) {
        		erreur = false;
        		flottant = clavier.nextFloat();
        	} else {
        		erreur = true;
        		System.err.print("\"" + clavier.nextLine() + "\"");
        		System.err.println("n'est pas une entree valide.");
        	}
        } while (erreur);
        return flottant;
    }

    /**
     * Permet la lecture d'un entier compris born� par un min
     * et un max mis en argument.<br>
     * Tant que la donn�e lu sur l'entr�e standart n'est
     * pas un entier compris entre min et max,
     * on demande � l'utilisateur de rentrer la donn�e.<br>
     * L'arr�t d'une saisie s'�ffectue lorsque l'utilisateur
     * appuie sur ENTREE.<br>
     * @param  min Contient l'entier minimum autoris�.
     * @param  max Contient l'entier maximum autoris�.
     * @return l'entier entr�e sur l'entr�e standart
     */
    public static int lireInt(int min, int max){
        Scanner clavier = new Scanner(System.in);
        int entier=0;
        boolean erreur=true;
        
        do {
        	System.out.print("Veuillez entrer un entier compris entre " + min + " et " + max +" : ");
        	if (clavier.hasNextInt()) {
        		erreur = false;
        		entier = clavier.nextInt();
        		
        		if ( max < entier || entier < min) {
        			erreur = true;
        			System.err.println(entier + " n'est pas compris entre " + min + " et " + max + ".");
        		} 
        	} else {
        		erreur = true;
        		System.err.print("\"" + clavier.nextLine() + "\"");
        		System.err.println(" n'est pas une entree valide.");
        	}
        } while (erreur);
        return entier;
    }
    
    /**
     * Permet la lecture d'un entier lu sur l'entr�e
     * standart.<br>
     * Tant que la donn�e lu sur l'entr�e standart n'est
     * pas un entier on demande � l'utilisateur de
     * rentrer la donn�e.<br>
     * L'arr�t d'une saisie s'�ffectue lorsque l'utilisateur
     * appuie sur ENTREE.<br>
     * @return l'entier entr�e sur l'entr�e standart
     */
    public static int lireInt(){
        Scanner clavier = new Scanner(System.in);
        int entier=0;
        boolean erreur=true;
        
        do {
        	System.out.print("Veuillez entrer un entier : ");
        	if (clavier.hasNextInt()) {
        		erreur = false;
        		entier = clavier.nextInt();
        	} else {
        		erreur = true;
        		System.err.print("\""+ clavier.nextLine() +"\"");
        		System.err.println(" n'est pas une entree valide.");
        	}
        } while (erreur);
        return entier;
    }
    
    /**
     * Permet la lecture d'une <code>Date</code> lu sur l'entr�e
     * standart.<br>
     * Tant que la donn�e lu sur l'entr�e standart n'est
     * pas une date, on demande � l'utilisateur de
     * rentrer la donn�e.<br>
     * L'arr�t d'une saisie s'�ffectue lorsque l'utilisateur
     * appuie sur ENTREE.<br>
     * @return l'entier entr�e sur l'entr�e standart
     */
    public static Date lireDate(){
        Scanner clavier = new Scanner(System.in);
        String date;
        Date aRetourner = null;
        boolean erreur=true;
        
        do {
        	System.out.print("Veuillez entrer une date au format jj/mm/aaaa : ");
        	date = clavier.nextLine();
        	if (Date.estUneDate(date)) {
        		erreur = false;
        		try {
        		    aRetourner = new Date(date);
        		} catch (Exception e) {
        			erreur = true;
        			System.err.print(e);
        			System.err.print(" : \""+ date +"\"");
            		System.err.println(" n'est pas une entree valide.");
        		}
        		erreur = false;
        	} else {
        		System.err.print("\""+ date +"\"");
        		System.err.println(" n'est pas une entree valide.");
        	}
        } while (erreur);
        return aRetourner;
    }
    
    /**
     * Permet la lecture d'une <code>Horaire</code> lu sur l'entr�e
     * standart.<br>
     * Une heure a pour format hh:mm (h -> heure, m -> minute)
     * Tant que la donn�e lu sur l'entr�e standart n'est
     * pas un <code>Horaire</code>, on demande � l'utilisateur de
     * rentrer la donn�e.<br>
     * L'arr�t de la saisie s'�ffectue lorsque l'utilisateur
     * appuie sur ENTREE.<br>
     * @return l'entier entr�e sur l'entr�e standart
     */
    public static Horaire lireHeure(){
        Scanner clavier = new Scanner(System.in);
        String heure;
        Horaire aRetourner = null;
        boolean erreur=true;
        
        do {
        	System.out.print("Veuillez entrer une heure au format hh:mm : ");
        	heure = clavier.nextLine();
        	
        	if (Horaire.estUneHeure(heure)) {
        		erreur = false;
        		try {
        		    aRetourner = new Horaire(heure);
        		} catch (Exception e) {
        			erreur = true;
        			System.err.print(e);
        			System.err.print(" : \""+ heure +"\"");
        			System.err.println(" n'est pas une entree valide.");
        		}
        	} else {
        		System.err.print("\""+ heure +"\"");
        		System.err.println(" n'est pas une entree valide.");
        	}
        } while (erreur);
        return aRetourner;
    }
}