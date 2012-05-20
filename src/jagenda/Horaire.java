/*
 * Fichier :    Horaire.java
 * Package :    jagenda
 * Projet jAgenda - Info 1 2011-2012
 *
 * $Revision$
 * $Date$
 * $HeadURL$
 */
package jagenda;

/**
 * Classe <code>Horaire</code> représente une heure de la journée
 * en heure(s) et minute(s) (h:min).
 * <ul>
 *     <li>Une heure est représentée par un entier compris 0 et 23. 
 *         Minuit (1 a.m.) est représenté par le chiffre 0 et midi (1 p.m.) 
 *         est représenté par le nombre 12.</li>
 *     <li>Une minute est représentée par un entier compris entre 0 et 59.</li>
 * </ul>
 *
 * @author Jason BOURLARD
 * @author David PELISSIER
 *
 */
public class Horaire {

    /**
     * Heures entre 0 et 23.
     */
    protected int heures;

    /**
     * Minutes entre 0 et 59.
     */
    protected int minutes;
    
    /**
     * Constructeur paramétrable.
     * @param heures    Heures de l'horaire à instancier
     * @param minutes   Minutes de l'horaire à instancier
     */
    public Horaire(int heures, int minutes) {
        this.heures = heures;
        this.minutes = minutes;
        
        System.out.println("Horaire allouée.");
    }
    
    /**
     * Constructeur paramétrable.
     * @param heure     Heures de l'horaire à instancier
     * @throws Exception Heure invalide
     */
    public Horaire(String heure) throws Exception {
        if(estUneHeure(heure)) {
        	this.heures = Character.getNumericValue(heure.charAt(0)) * 10 
                          + Character.getNumericValue(heure.charAt(1));
        	this.minutes = Character.getNumericValue(heure.charAt(3)) * 10 
                           + Character.getNumericValue(heure.charAt(4));
        } else {
        	throw new Exception("Heure invalide");
        }
        
    }
    
    /**
     * Vérifie la validité d'une heure au format hh:mm.<br>
     * Les heures sont comprises 0 et 23, les minutes sont 
     * comprises entre 00 et 59.
     * @param heure contient l'heure à tester
     * @return TRUE si l'heure est correct.
     */
    public static boolean estUneHeure(String heure) {
        boolean estCorrect = true;
        
        /*
         * On s'assure que l'heure est au format hh:mm
         * avec h et m des chiffres.
         */
        estCorrect = heure.length() == 5 && heure.charAt(2) == ':'
                     && Character.isDigit(heure.charAt(0))
                     && Character.isDigit(heure.charAt(1))
                     && Character.isDigit(heure.charAt(3))
                     && Character.isDigit(heure.charAt(4));
        
        /*
         * On test la validité de l'heure si le résultat
         * boolean est égal à TRUE.
         */
        if (estCorrect) {
            estCorrect = estUneHeure(Character.getNumericValue(heure.charAt(0)) * 10 
                                     + Character.getNumericValue(heure.charAt(1)),
                                     Character.getNumericValue(heure.charAt(3)) * 10
                                     + Character.getNumericValue(heure.charAt(4)));
        }
        return estCorrect;
    }
    
    /**
     * Méthode permettant de tester si l'<code>Horaire</code> est correct.
     * Les heures sont comprises entre 0 et 23.
     * Les minutes sont comprises entre 0 et 59.
     * @param heures  Contient les heures à tester.
     * @param minutes Contient les minutes à tester.
     * @return TRUE si l'<code>Horaire</code> est correct.
     */
    public static boolean estUneHeure(int heures, int minutes) {
        return 0 <= heures && heures < 24 
        	   && 0 <= minutes && minutes < 60;
    }
    
    /**
     * Accesseur pour <code>heures</code>
     * @return Heures de l'horaire demandée
     */
    public int getHeures() {
        return heures;
    }

    /**
     * Accesseur pour <code>minutes</code>
     * @return Minutes de l'horaire demandée
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * Mutateur pour <code>heures</code>
     * @param heures    Nouvelle valeur pour les heures
     */
    public void setHeures(int heures) {
        if (0 <= heures && heures <= 23) {
            this.heures = heures;
        } else {
            System.out.println("L'heure doit être comprise entre 0 et 23.");
        }
    }

    /**
     * Mutateur pour <code>minutes</code>
     * @param minutes    Nouvelle valeur pour les minutes
     */
    public void setMinutes(int minutes) {
        if (0 <= minutes && minutes <= 59) {
            this.minutes = minutes;
        } else {
            System.out.println("Les minutes doivent être comprises entre 0 et 59.");
        }
    }
    
    /**
     * Affichage d'un horaire.
     */
    public String affichHoraire() {
        return (this.heures > 10 ? this.heures : "0"+ this.heures) 
               + ":"+ 
               (this.minutes > 10 ? this.minutes : "0"+ this.minutes);
    }
    
    /**
     * Polymorphisme de la classe <code>Objet</code> affichant 
     * l'<code>Horaire</code> au format hh:mm.
     * @return l'<code>Horaire</code> au format hh:min.
     */
    public String toString() {
    	return (this.heures > 10 ? this.heures : "0"+ this.heures) 
               + ":" + 
               (this.minutes > 10 ? this.minutes : "0"+ this.minutes);
    }
}
