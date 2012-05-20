/*
 * Fichier :	RendezVous.java
 * Package :	jagenda
 * Projet jAgenda - Info 1 2011-2012
 *
 */
package jagenda;

import java.util.regex.Matcher;

/**
 * Classe {@code RendezVous} repr�sente un rendez-vous possible dans l'agenda.
 * Un RendezVous est repr�sent� � l'aide de 6 valeurs qui sont les suivantes :
 * <ul>
 *     <li>Date</li>
 *     <li>Horaire de d�but</li>
 *     <li>Horaire de fin</li>
 *     <li>Libell�</li>
 *     <li>Nature</li>
 *     <li>Description</li>
 * </ul>
 * 
 * 
 * @author  Jason BOURLARD
 * @author  David PELISSIER
 * @version 0.3
 */
public class RendezVous implements Comparable<RendezVous> {

    /**
     * Libell� concis du rendez-vous, limit� � 8 caract�res.<br>
     * Apparaitra lors d'un affichage abr�g�.
     */
    private String libelle;

    /**
     * Libell� complet du rendez-vous, d'au plus 5 lignes.<br>
     * Apparaitra lors d'un affichage d�taill�.
     */
    private String description;

    /**
     * Pr�cision sur la nature du rendez-vous (professionnel ou personnel).
     */
    private String nature;

    /**
     * Date et horaire de d�but du rendez-vous (en jj/mm/aaaa h:min).
     */
    private Horaire horaireDebut;

    /**
     * Date et horaire de fin du rendez-vous (en jj/mm/aaaa h:min)..
     */
    private Horaire horaireFin;

    /**
     * Constructeur par d�faut.<br>
     * Initialise les membres de classe de fa�on � ne pas avoir un objet
     * incoh�rent quelque soit son �tat.
     */
    private RendezVous() {
        this.libelle = "Par d�faut";
        this.nature ="Divers";
        this.horaireDebut = new Horaire();
        this.horaireFin = new Horaire();
    }

    /**
     * Constructeur param�trable prenant en argument une ligne de donn�es au
     * format {@code STRUCTURE_DONNEES}.
     * 
     * @param donnees Ligne de donn�es � lire.
     */
    public RendezVous(Matcher donnees) {
        this.libelle     = donnees.group(10);
        this.nature      = donnees.group(11);
        this.description = donnees.group(12);
        this.horaireDebut = new Horaire(
                // Date
                donnees.group(1) +"/"+ donnees.group(2)+"/"+ donnees.group(3),
                // Heure de d�but
                donnees.group(4) +"h"+ donnees.group(6));
        this.horaireFin = new Horaire(
                // Date
                donnees.group(1) +"/"+ donnees.group(2)+"/"+ donnees.group(3),
                // Heure de d�but
                donnees.group(7) +"h"+ donnees.group(9));
    }

    /**
     * Constructeur param�trable prenant en argument le {@code libelle}, la
     * {@code description}, la {@code nature}, la {@code date}, les 
     * {@code heureDebut} et {@code heureFin} � affecter au rendez-vous cr��.
     * 
     * @param libelle       Libell� du rendez-vous � initialiser.
     * @param description   Description du rendez-vous � initialiser.
     * @param nature        Nature du rendez-vous � initialiser.
     * @param date          Date du rendez-vous � initialiser.
     * @param heureDebut    Heure de d�but du rendez-vous � initialiser. 
     * @param heureFin      Heure de fin du rendez-vous � initialiser.
     */
    public RendezVous(String libelle, String description, int nature,
            String date, String heureDebut, String heureFin) {
        this();
        this.libelle = libelle;
        this.description = description;
        this.setNature(nature);
        this.horaireDebut = new Horaire(date, heureDebut);
        this.horaireFin = new Horaire(date, heureFin);
    }

    /**
     * Accesseur pour le champ {@code libelle}.
     * 
     * @return 	Libell� du rendez-vous renseign�.
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Accesseur pour le champ {@code description}.
     * 
     * @return 	Description du rendez-vous renseign�.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Accesseur pour le champ {@code nature}.
     * 
     * @return 	Nature du rendez-vous renseign�.
     */
    public String getNature() {
        return nature;
    }

    /**
     * Accesseur pour le champ {@code horaireDebut}.
     * 
     * @return 	Horaire de d�but du rendez-vous renseign�
     * 			(comprend la date et l'heure de ce dernier).
     */
    public Horaire getHoraireDebut() {
        return horaireDebut;
    }

    /**
     * Accesseur pour le champ {@code horaireFin}.
     * 
     * @return 	Horaire de fin du rendez-vous renseign�
     * 			(comprend la date et l'heure de ce dernier).
     */
    public Horaire getHoraireFin() {
        return horaireFin;
    }

    /**
     * Mutateur pour le champ {@code libelle}.

     * @param libelle  Nouveau libell� pour le rendez-vous renseign�.
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Mutateur pour le champ {@code description}.
     * 
     * @param description  Nouvelle description pour le rendez-vous renseign�.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Mutateur pour le champ {@code nature}.
     * 
     * @param nature  Nouvelle nature pour le rendez-vous renseign�.
     */
    public void setNature(int nature) {
        // Nature du rendez-vous organis� via un menu de choix :
        // 1 = Personnel
        // 2 = Professionnel
        if (nature == 1)
            this.nature = "Personnel";
        else if (nature == 2)
            this.nature = "Professionnel";
    }
    
    /**
     * V�rifie la validit� d'un libell�, � savoir :
     * <ul><li>Doit �tre non nul</li>
     *     <li>Doit �tre compos� d'au moins 1 caract�re et d'au plus 8 
     *     caract�res</li>
     *     <li>Ne soit pas �tre c</li></ul>
     *     
     * @param libelle 
     * @return  1 si le libell� est vide ou nul<br>
     *          2 si la taille du libell� est trop grande<br>
     *          3 si le libell� contient des espaces<br>
     */
    public static int estLibelleCorrect(String libelle) {
        // Contiendra le num�ro de l'erreur.
        int erreur = 0;
        
        if (libelle.equals(null) || libelle.length() == 0) {
            erreur = 1;
        } else if (libelle.length() > 8) {
            erreur = 2;    
        } else if (libelle.contains(" ")) {
            erreur = 3;     
        }
        return erreur;
    }

    /**
     * V�rifie la validit� d'une description, � savoir :
     * <ul><li>Doit �tre non nulle</li>
     *     <li>Doit �tre compos�e d'au moins 1 caract�re et d'au plus 400 
     *     caract�res (5 lignes)</li>
     *     
     * @param description
     * @return {@code true} si la description est correcte.
     */
    public static boolean estDescriptionCorrecte(String description) {
        return description.length() < 400;
     }
     
    /**
     * Compare ce {@code RendezVous} avec un {@code RendezVous} � comparer mis
     * en param�tre et renvoie un nombre significatif au r�sultat de la 
     * comparaison.<br> On ne compare que les horaires des {@code RendezVous},
     * les informations de type {@code String} ne sont pas comparer.
     * Dans les exemples de retour, on supposera que les {@code RendezVous} sont
     * inscrit dans le m�me jour.
     * @param aComparer Le {@code RendezVous} � comparer.
     * @return Un entier compris entre -3 et 3 repr�sentant le r�sultat de la
     *         comparaison.
     *         <ul>
     *             <li>-3 : Ce {@code RendezVous} est avant le {@code RendezVous} � 
     *                 comparer.<br>
     *                 Exemple : Ce {@code RendezVous} commence � 12h30 et fini � 13h00
     *                 et le {@code RendezVous} � comparer commence � 13h00 et fini �
     *                 13h30.
     *             </li>
     *             <li>-2 : Ce {@code RendezVous} est inclut dans le {@code RendezVous}
     *                 � comparer.<br>
     *                 Exemple : Ce {@code RendezVous} commence � 12h30 et fini � 13h00
     *                 et le {@code RendezVous} � comparer commence � 12h00 et fini �
     *                 13h00.
     *             </li>
     *              <li>-1 : Ce {@code RendezVous} est avant le {@code RendezVous} �
     *                  comparer mais son {@code Horaire} de fin se situe dans les 
     *                  {@code Horaires} du {@code RendezVous} � comparer.<br>
     *                  Exemple : Ce {@code RendezVous} commence � 12h00 et fini � 13h00
     *                  et le {@code RendezVous} � comparer commence � 12h30 et fini �
     *                  13h30.
     *              </li>
     *              <li> 0 : Ce {@code RendezVous} a ses {@code Horaires} de d�but et de 
     *                  fin identique au {@code RendezVous} � comparer. Equivalent � la
     *                  m�thode #equals(RendezVous)<br>
     *                  Exemple : Ce {@code RendezVous} commence � 12h00 et fini � 13h00
     *                  et le {@code RendezVous} � comparer commence � 12h00 et fini �
     *                  13h00.
     *              </li>
     *              <li> 1 : Ce {@code RendezVous} est apr�s le {@code RendezVous} �
     *                  comparer mais son {@code Horaire} de d�but se situe dans les 
     *                  {@code Horaires} du {@code RendezVous} � comparer.<br>
     *                  Exemple : Ce {@code RendezVous} commence � 13h00 et fini � 14h00
     *                  et le {@code RendezVous} � comparer commence � 12h00 et fini �
     *                  13h30.
     *              </li>
     *              <li> 2 : Ce {@code RendezVous} encadre le {@code RendezVous} �
     *                  comparer.<br>
     *                  Exemple : Ce {@code RendezVous} commence � 12h00 et fini � 13h30
     *                  et le {@code RendezVous} � comparer commence � 12h30 et fini �
     *                  13h00.
     *              </li>
     *              <li> 3 : Ce {@code RendezVous} est apr�s le {@code RendezVous} � 
     *                  comparer.<br>
     *                  Exemple : Ce {@code RendezVous} commence � 13h30 et fini � 14h00
     *                  et le {@code RendezVous} � comparer commence � 12h00 et fini �
     *                  13h00.
     *              </li>
     * </ul>
     * @see jagenda.Horaire#compareTo(java.util.Calendar)
     */
    public int compareTo(RendezVous aComparer) {
        int aRetourner;
        /* Premier cas, ce RendezVous a lieu avant celui � comparer. */
        if(this.horaireFin.compareTo(aComparer.horaireDebut) <= 0) {
            aRetourner = -3;
        /* Second cas, ce RendezVous a lieu apr�s celui � comparer. */
        } else if(this.horaireDebut.compareTo(aComparer.horaireFin) >= 0) {
            aRetourner = 3;
        /* 
         * Troisi�me cas, l'horaire de d�but de ce RendezVous a lieu avant ou en
         * m�me temps que l'horaire de d�but de celui � comparer .
         */
        } else if(this.horaireDebut.compareTo(aComparer.horaireDebut) <= 0 ) {
            /* 
             * Premier cas, l'horaire de d�but de ce RendezVous est le m�me que
             * l'horaire de d�but du RendezVous � comparer.
             */
            if(this.horaireDebut.equals(aComparer.horaireDebut)) {
                /*
                 * Premier cas, l'horaire de fin de ce RendezVous est le m�me
                 * que l'horaire de fin � comparer.
                 */
                if(this.horaireFin.equals(aComparer.horaireFin)) {
                    aRetourner = 0;
                /*
                 * Second cas, l'horaire de fin de ce RendezVous est apr�s
                 * l'horaire de fin � comparer.
                 */
                } else if (this.horaireFin.compareTo(aComparer.horaireFin) > 0){
                    aRetourner = 2;
                /*
                 * Dernier cas, l'horaire de fin de ce RendezVous est avant
                 * l'horaire de fin � comparer.
                 */
                } else {
                    aRetourner = -2;
                }
            /* 
             * Second cas, l'horaire de fin de ce RendezVous a lieu apr�s ou en
             * m�me temps que l'horaire de fin � comparer.
             */
            } else if (this.horaireFin.compareTo(aComparer.horaireFin) >= 0) {
                aRetourner = 2;
            /* 
             * Dernier cas, l'horaire de fin de ce RendezVous a lieu avant
             * l'horaire de fin � comparer.
             */
            } else {
                aRetourner = -1;
            }
        /* 
         * Dernier cas, L'horaire de d�but de ce RendezVous a lieu apr�s 
         * l'horaire de d�but de celui � comparer 
         */
        } else {
            /* 
             * Premier cas, l'horaire de fin de ce RendezVous a lieu apr�s que
             * l'horaire de fin � comparer.
             */
            if (this.horaireFin.compareTo(aComparer.horaireFin) > 0) {
                aRetourner = 1;
            /* 
             * Dernier cas, l'horaire de fin de ce RendezVous a lieu avant ou en
             * m�me temps que l'horaire de fin � comparer.
             */
            } else {
                aRetourner = -2;
            }
        }
        return aRetourner;
    }
    
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((horaireDebut == null) ? 0 : horaireDebut.hashCode());
        result = prime * result
                + ((horaireFin == null) ? 0 : horaireFin.hashCode());
        return result;
    }

    /**
     * On consid�re qu'un RendezVous est equals � un autre RendezVous si les
     * Horaire de d�but et de fin sont identiques.
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        boolean resultat = false;
        if (this == obj) {
            resultat = true;
        } else if (obj == null || !(obj instanceof RendezVous)) {
            return resultat;
        } else {
            RendezVous other = (RendezVous) obj;
            if (horaireDebut.equals(other.horaireDebut) 
                    && horaireFin.equals(other.horaireFin)) {
                resultat = true;
            }
        }
        return resultat;
    }
    
    public String toString() {
        return this.libelle + this.horaireDebut + this.horaireFin;
    }
    

}
