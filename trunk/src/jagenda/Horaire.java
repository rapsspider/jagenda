/*
 * Fichier :	Horaire.java
 * Package :	jagenda.util
 * Projet jAgenda - Info 1 2011-2012
 *
 */
package jagenda;

import static jagenda.Principale.*;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe <code>Horaire</code> r�f�rence un moment pr�cis dans un rep�re 
 * temporel, en pr�cisant une date et une heure.</p>
 * 
 * @author	Jason BOURLARD
 * @author	David PELISSIER
 * @version	0.3
 */
@SuppressWarnings("serial")
public class Horaire extends GregorianCalendar {

    /** 
     * Ann�e minimale de la p�riode g�r�e par l'agenda, � savoir 1950-2050
     * (post-r�volutionnaire).
     */
    public final static int AN_MIN = 1950;

    /** 
     * Ann�e maximale de la p�riode g�r�e par l'agenda, � savoir 1950-2050
     * (post-r�volutionnaire).
     */
    public final static int AN_MAX = 2050;

    /** Jours de la semaine. */
    public static final String[] JOURS_SEMAINE = 
        {"dimanche","lundi","mardi","mercredi","jeudi","vendredi","samedi"};

    /** Format explicite de date : jj/mm/aaaa */
    private final static Pattern FORMAT_DATE_1 = 
            Pattern.compile("(\\d{1,2})/(\\d{1,2})/(\\d{4})");

    /** Format de date en semaine : jeudi semaine 11 */
    private final static Pattern FORMAT_DATE_2 = 
            Pattern.compile("([a-zA-Z]{1,8}) semaine (\\d{1,2}) (\\d{4})");

    /** Format de l'heure d'un rendez-vous : hh:mm */
    private final static Pattern FORMAT_HEURE = 
            Pattern.compile("(\\d{1,2})(h|H|:)(\\d{1,2})");        
   
    /**
     * Format d'affichage des dates sous forme de String.
     * <ul><li>dd :   Jour sur 2 chiffres</li>
     *     <li>MM :   Mois sur 2 chiffres</li>
     *     <li>yyyy : Ann�e sur 4 chiffres</li></ul>
     */
    public final static SimpleDateFormat SDF_DATE = 
            new SimpleDateFormat("dd/MM/yyyy" );
    
    /**
     * Format d'affichage des heures sous forme de String.
     * <ul><li>hh :   Heure sur 2 chiffres</li>
     *     <li>mm :   Minutes sur 2 chiffres</li></ul>
     */
    public final static SimpleDateFormat SDF_HEURE = 
            new SimpleDateFormat("HH'h'mm");

    /**
     * Constructeur par d�faut.
     * <p>Initialise une date et heure au <i>01/01/<code>anneeDebut</code> 
     * 00:00</i>. On rappelle que <code>anneeDebut</code> est d�finie comme le 
     * d�but de la p�riode g�r�e par le calendrier (fichier de configuration).
     * </p>
     */
    public Horaire() {
        super(agenda.getAnneeDebut(),0,1,0,0);
    }

    /**
     * Constructeur param�trable prenant en argument la {@code date} et l'
     * {@code heure} � affecter � l'objet {@code Horaire} � cr�er.
     * @param date  Date pour l'horaire du rendez-vous.
     * @param heure Heures et minutes pour l'horaire du rendez-vous.
     * @param format 
     */
    public Horaire(String date, String heure) {
        this();
        if (agenda.getFormatDate() == 1) {
            this.setHoraire1(date, heure);
        } else {
            this.setHoraire2(date, heure);
        }
    }
    
    /**
     * Accesseur pour la date de l'horaire, sous forme de cha�ne de caract�res
     * au format <code>jj/mm/aaaa</code>.
     * @return Cha�ne de caract�res repr�sentant la date au format
     *         <code>jj/mm/aaaa</code>.
     */
    public String getDate() {
        return SDF_DATE.format(this.getTime());
    }
    
    /**
     * Accesseur pour l'heure de l'horaire, sous forme de cha�ne de caract�res
     * au format <code>'HH'h'mm'</code>.
     * @return Cha�ne de caract�res repr�sentant l'heure au format
     *         <code>'HH'h'mm'</code>.
     */
    public String getHeure() {
        return SDF_HEURE.format(this.getTime());
    }
    
    /**
     * Formate un horaire suivant le format 1, � savoir, 
     * "<i>{@code jj/mm/aaaa}</i>", seulement si les param�tres constituant le
     * nouvel horaire sont valides.<br>
     * Dans le cas contraire, l'horaire reste inchang�e.
     */
    private void setHoraire1(String date, String heure) {
        if (estDateValide(date) && estHeureValide(heure)) {
            // Initialisation des analyseurs de regex
            Matcher analyseDate = FORMAT_DATE_1.matcher(date);
            Matcher analyseHeure = FORMAT_HEURE.matcher(heure);
            // Si l'analyse par regex est correcte, on extrait les param�tres
            if (analyseDate.matches() && analyseHeure.matches()) {
                int jour    = Integer.parseInt(analyseDate.group(1));
                // Calendar.JANUARY = 00 or janvier s'�crit jj/01/aaa
                int mois    = Integer.parseInt(analyseDate.group(2))-1;
                int annee   = Integer.parseInt(analyseDate.group(3));
                int heures  = Integer.parseInt(analyseHeure.group(1));
                int minutes = Integer.parseInt(analyseHeure.group(3));

                this.set(annee,mois,jour,heures,minutes);
            }
        }
    }
    
    /**
     * Formate un horaire suivant le format 1, � savoir, 
     * "<i>{@code jour_semaine} semaine 
     * {@code numero_semaine} de {@code annee}</i>", seulement si les param�tres
     * constituant le nouvel horaire sont valides.<br>
     * Dans le cas contraire, l'horaire reste inchang�e.
     */
    private void setHoraire2(String date, String heure) {
        if (estDateValide(date) && estHeureValide(heure)) {
            // Initialisation des analyseurs de regex
            Matcher analyseDate = FORMAT_DATE_1.matcher(date);
            Matcher analyseHeure = FORMAT_HEURE.matcher(heure);
            // Si l'analyse par regex est correcte, on extrait les param�tres
            if (analyseDate.matches() && analyseHeure.matches()) {
                int jour = numJour(analyseDate.group(1));
                int semaine = Integer.parseInt(analyseDate.group(2));
                int annee = Integer.parseInt(analyseDate.group(3));
                int heures  = Integer.parseInt(analyseHeure.group(1));
                int minutes = Integer.parseInt(analyseHeure.group(3));
                
                this.setWeekDate(annee,semaine,jour);
                this.set(HOUR_OF_DAY, heures);
                this.set(MINUTE, minutes);
            }
        }
    }    

    /**
     * Permet de v�rifier la validit� de la date d'un horaire.
     * 
     * @param date  Date pour l'horaire du rendez-vous.
     * @return  {@code true} si valide.   
     */
    public static boolean estDateValide(String date) {
        boolean correct = false;
        GregorianCalendar dateTest = new GregorianCalendar();
        if (agenda.getFormatDate() == 1) {
            Matcher analyseDate = FORMAT_DATE_1.matcher(date);
            try {
                if (analyseDate.matches()) {
                    int jour = Integer.parseInt(analyseDate.group(1));
                    // Calendar.JANUARY = 00 or janvier s'�crit jj/01/aaaa
                    int mois = Integer.parseInt(analyseDate.group(2))-1;  
                    int annee = Integer.parseInt(analyseDate.group(3));
                    dateTest.set(annee,mois,jour);            
                    dateTest.setLenient(false);
                    dateTest.getTime();
                    correct =  agenda.getAnneeDebut() <= annee
                            && annee <= agenda.getAnneeFin();
                }
            } catch (IllegalStateException staEx) {
            } catch (IllegalArgumentException argEx) {
            }
        } else {
            Matcher analyseDate = FORMAT_DATE_2.matcher(date);
            try {
                analyseDate.matches();
                int jour = numJour(analyseDate.group(1));
                int semaine = Integer.parseInt(analyseDate.group(2));
                int annee = Integer.parseInt(analyseDate.group(3));
                dateTest.setWeekDate(annee,semaine,jour);            
                dateTest.setLenient(false);
                dateTest.getTime();
                correct =    agenda.getAnneeDebut() <= annee
                          && annee <= agenda.getAnneeFin();
            } catch (IllegalStateException staEx) {
            } catch (IllegalArgumentException argEx) {
            }
  
        }
        return correct;
    }
    
    /**
     * Permet de v�rifier la validit� de l'heure d'un horaire.
     * 
     * @param heure  Heure pour l'horaire du rendez-vous.
     * @return  {@code true} si valide.   
     */
    public static boolean estHeureValide(String heure) {
        Matcher analyseHeure = FORMAT_HEURE.matcher(heure);
        if (analyseHeure.matches()) {
            int heures = Integer.parseInt(analyseHeure.group(1));
            int minutes = Integer.parseInt(analyseHeure.group(3));
            return    0 <= heures && heures <= 59
                    && 0<= minutes && minutes <= 59;
        } else {
            return false;
        }
    }
    

    /**
     * Permet de retourner le num�ro du jour dans la semaine.
     * @param jourSemaine  Jour sous forme textuelle � tester.
     * @return Valeur positive correspondant au num�ro du jour dans la semaine,
     *         � savoir :
     *         <ul><li>1 => dimanche</li>
     *             <li>2 => lundi</li>
     *             <li>...</li></ul>
     *         Retourne -1 si le <code>jourSemaine</code> test� n'est pas valide.
     */
    private static int numJour(String jourSemaine) {
        int jour;      // Valeur de retour de la v�rification.
        try {
            for (jour = 0; !(jourSemaine.equalsIgnoreCase(JOURS_SEMAINE[jour]))  
              && jour < JOURS_SEMAINE.length; jour++);
            // Afin d'obtenir "dimanche=1", on ajoute 1 � la variable jour
            // Ceci permet de rester homog�ne avec java.util.Calendat puisque
            // Calendar.SUNDAY = 1.
            jour += 1;
        } catch (IndexOutOfBoundsException e) {
            jour = -1;
        }
        return jour;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        // Nombre de secondes depuis le 1er janvier 1970
        int timestamp = (int) (this.getTimeInMillis() / 1000);
        return timestamp;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals()
     */
    @Override
    public boolean equals(Object autreHoraire) {
        return   autreHoraire instanceof Horaire
              && autreHoraire.hashCode() == this.hashCode();      
    }

    /**
     * Compare deux horaires.
     * @param autreHoraire Horaire � comparer.
     * @return Nombre de secondes s�parant les deux horaires.
     */
    public int compareTo(Horaire autreHoraire) {
        return this.hashCode() - autreHoraire.hashCode();
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.getDate() +" "+ this.getHeure();
    }
}