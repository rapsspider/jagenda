/*
 * Fichier :	RendezVous.java
 * Package :	jagenda
 * Projet jAgenda - Info 1 2011-2012
 *
 */
package jagenda;

import java.util.regex.Matcher;

/**
 * Classe {@code RendezVous} représente un rendez-vous possible dans l'agenda.
 * Un RendezVous est représenté à l'aide de 6 valeurs qui sont les suivantes :
 * <ul>
 *     <li>Date</li>
 *     <li>Horaire de début</li>
 *     <li>Horaire de fin</li>
 *     <li>Libellé</li>
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
     * Libellé concis du rendez-vous, limité à 8 caractères.<br>
     * Apparaitra lors d'un affichage abrégé.
     */
    private String libelle;

    /**
     * Libellé complet du rendez-vous, d'au plus 5 lignes.<br>
     * Apparaitra lors d'un affichage détaillé.
     */
    private String description;

    /**
     * Précision sur la nature du rendez-vous (professionnel ou personnel).
     */
    private String nature;

    /**
     * Date et horaire de début du rendez-vous (en jj/mm/aaaa h:min).
     */
    private Horaire horaireDebut;

    /**
     * Date et horaire de fin du rendez-vous (en jj/mm/aaaa h:min)..
     */
    private Horaire horaireFin;

    /**
     * Constructeur par défaut.<br>
     * Initialise les membres de classe de façon à ne pas avoir un objet
     * incohérent quelque soit son état.
     */
    private RendezVous() {
        this.libelle = "Par défaut";
        this.nature ="Divers";
        this.horaireDebut = new Horaire();
        this.horaireFin = new Horaire();
    }

    /**
     * Constructeur paramétrable prenant en argument une ligne de données au
     * format {@code STRUCTURE_DONNEES}.
     * 
     * @param donnees Ligne de données à lire.
     */
    public RendezVous(Matcher donnees) {
        this.libelle     = donnees.group(10);
        this.nature      = donnees.group(11);
        this.description = donnees.group(12);
        this.horaireDebut = new Horaire(
                // Date
                donnees.group(1) +"/"+ donnees.group(2)+"/"+ donnees.group(3),
                // Heure de début
                donnees.group(4) +"h"+ donnees.group(6));
        this.horaireFin = new Horaire(
                // Date
                donnees.group(1) +"/"+ donnees.group(2)+"/"+ donnees.group(3),
                // Heure de début
                donnees.group(7) +"h"+ donnees.group(9));
    }

    /**
     * Constructeur paramètrable prenant en argument le {@code libelle}, la
     * {@code description}, la {@code nature}, la {@code date}, les 
     * {@code heureDebut} et {@code heureFin} à affecter au rendez-vous créé.
     * 
     * @param libelle       Libellé du rendez-vous à initialiser.
     * @param description   Description du rendez-vous à initialiser.
     * @param nature        Nature du rendez-vous à initialiser.
     * @param date          Date du rendez-vous à initialiser.
     * @param heureDebut    Heure de début du rendez-vous à initialiser. 
     * @param heureFin      Heure de fin du rendez-vous à initialiser.
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
     * @return 	Libellé du rendez-vous renseigné.
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Accesseur pour le champ {@code description}.
     * 
     * @return 	Description du rendez-vous renseigné.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Accesseur pour le champ {@code nature}.
     * 
     * @return 	Nature du rendez-vous renseigné.
     */
    public String getNature() {
        return nature;
    }

    /**
     * Accesseur pour le champ {@code horaireDebut}.
     * 
     * @return 	Horaire de début du rendez-vous renseigné
     * 			(comprend la date et l'heure de ce dernier).
     */
    public Horaire getHoraireDebut() {
        return horaireDebut;
    }

    /**
     * Accesseur pour le champ {@code horaireFin}.
     * 
     * @return 	Horaire de fin du rendez-vous renseigné
     * 			(comprend la date et l'heure de ce dernier).
     */
    public Horaire getHoraireFin() {
        return horaireFin;
    }

    /**
     * Mutateur pour le champ {@code libelle}.

     * @param libelle  Nouveau libellé pour le rendez-vous renseigné.
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Mutateur pour le champ {@code description}.
     * 
     * @param description  Nouvelle description pour le rendez-vous renseigné.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Mutateur pour le champ {@code nature}.
     * 
     * @param nature  Nouvelle nature pour le rendez-vous renseigné.
     */
    public void setNature(int nature) {
        // Nature du rendez-vous organisé via un menu de choix :
        // 1 = Personnel
        // 2 = Professionnel
        if (nature == 1)
            this.nature = "Personnel";
        else if (nature == 2)
            this.nature = "Professionnel";
    }
    
    /**
     * Vérifie la validité d'un libellé, à savoir :
     * <ul><li>Doit être non nul</li>
     *     <li>Doit être composé d'au moins 1 caractère et d'au plus 8 
     *     caractères</li>
     *     <li>Ne soit pas être c</li></ul>
     *     
     * @param libelle 
     * @return  1 si le libellé est vide ou nul<br>
     *          2 si la taille du libellé est trop grande<br>
     *          3 si le libellé contient des espaces<br>
     */
    public static int estLibelleCorrect(String libelle) {
        // Contiendra le numéro de l'erreur.
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
     * Vérifie la validité d'une description, à savoir :
     * <ul><li>Doit être non nulle</li>
     *     <li>Doit être composée d'au moins 1 caractère et d'au plus 400 
     *     caractères (5 lignes)</li>
     *     
     * @param description
     * @return {@code true} si la description est correcte.
     */
    public static boolean estDescriptionCorrecte(String description) {
        return description.length() < 400;
     }
     
    /**
     * Compare ce {@code RendezVous} avec un {@code RendezVous} à comparer mis
     * en paramètre et renvoie un nombre significatif au résultat de la 
     * comparaison.<br> On ne compare que les horaires des {@code RendezVous},
     * les informations de type {@code String} ne sont pas comparer.
     * Dans les exemples de retour, on supposera que les {@code RendezVous} sont
     * inscrit dans le même jour.
     * @param aComparer Le {@code RendezVous} à comparer.
     * @return Un entier compris entre -3 et 3 représentant le résultat de la
     *         comparaison.
     *         <ul>
     *             <li>-3 : Ce {@code RendezVous} est avant le {@code RendezVous} à 
     *                 comparer.<br>
     *                 Exemple : Ce {@code RendezVous} commence à 12h30 et fini à 13h00
     *                 et le {@code RendezVous} à comparer commence à 13h00 et fini à
     *                 13h30.
     *             </li>
     *             <li>-2 : Ce {@code RendezVous} est inclut dans le {@code RendezVous}
     *                 à comparer.<br>
     *                 Exemple : Ce {@code RendezVous} commence à 12h30 et fini à 13h00
     *                 et le {@code RendezVous} à comparer commence à 12h00 et fini à
     *                 13h00.
     *             </li>
     *              <li>-1 : Ce {@code RendezVous} est avant le {@code RendezVous} à
     *                  comparer mais son {@code Horaire} de fin se situe dans les 
     *                  {@code Horaires} du {@code RendezVous} à comparer.<br>
     *                  Exemple : Ce {@code RendezVous} commence à 12h00 et fini à 13h00
     *                  et le {@code RendezVous} à comparer commence à 12h30 et fini à
     *                  13h30.
     *              </li>
     *              <li> 0 : Ce {@code RendezVous} a ses {@code Horaires} de début et de 
     *                  fin identique au {@code RendezVous} à comparer. Equivalent à la
     *                  méthode #equals(RendezVous)<br>
     *                  Exemple : Ce {@code RendezVous} commence à 12h00 et fini à 13h00
     *                  et le {@code RendezVous} à comparer commence à 12h00 et fini à
     *                  13h00.
     *              </li>
     *              <li> 1 : Ce {@code RendezVous} est après le {@code RendezVous} à
     *                  comparer mais son {@code Horaire} de début se situe dans les 
     *                  {@code Horaires} du {@code RendezVous} à comparer.<br>
     *                  Exemple : Ce {@code RendezVous} commence à 13h00 et fini à 14h00
     *                  et le {@code RendezVous} à comparer commence à 12h00 et fini à
     *                  13h30.
     *              </li>
     *              <li> 2 : Ce {@code RendezVous} encadre le {@code RendezVous} à
     *                  comparer.<br>
     *                  Exemple : Ce {@code RendezVous} commence à 12h00 et fini à 13h30
     *                  et le {@code RendezVous} à comparer commence à 12h30 et fini à
     *                  13h00.
     *              </li>
     *              <li> 3 : Ce {@code RendezVous} est après le {@code RendezVous} à 
     *                  comparer.<br>
     *                  Exemple : Ce {@code RendezVous} commence à 13h30 et fini à 14h00
     *                  et le {@code RendezVous} à comparer commence à 12h00 et fini à
     *                  13h00.
     *              </li>
     * </ul>
     * @see jagenda.Horaire#compareTo(java.util.Calendar)
     */
    public int compareTo(RendezVous aComparer) {
        int aRetourner;
        /* Premier cas, ce RendezVous a lieu avant celui à comparer. */
        if(this.horaireFin.compareTo(aComparer.horaireDebut) <= 0) {
            aRetourner = -3;
        /* Second cas, ce RendezVous a lieu après celui à comparer. */
        } else if(this.horaireDebut.compareTo(aComparer.horaireFin) >= 0) {
            aRetourner = 3;
        /* 
         * Troisième cas, l'horaire de début de ce RendezVous a lieu avant ou en
         * même temps que l'horaire de début de celui à comparer .
         */
        } else if(this.horaireDebut.compareTo(aComparer.horaireDebut) <= 0 ) {
            /* 
             * Premier cas, l'horaire de début de ce RendezVous est le même que
             * l'horaire de début du RendezVous à comparer.
             */
            if(this.horaireDebut.equals(aComparer.horaireDebut)) {
                /*
                 * Premier cas, l'horaire de fin de ce RendezVous est le même
                 * que l'horaire de fin à comparer.
                 */
                if(this.horaireFin.equals(aComparer.horaireFin)) {
                    aRetourner = 0;
                /*
                 * Second cas, l'horaire de fin de ce RendezVous est après
                 * l'horaire de fin à comparer.
                 */
                } else if (this.horaireFin.compareTo(aComparer.horaireFin) > 0){
                    aRetourner = 2;
                /*
                 * Dernier cas, l'horaire de fin de ce RendezVous est avant
                 * l'horaire de fin à comparer.
                 */
                } else {
                    aRetourner = -2;
                }
            /* 
             * Second cas, l'horaire de fin de ce RendezVous a lieu après ou en
             * même temps que l'horaire de fin à comparer.
             */
            } else if (this.horaireFin.compareTo(aComparer.horaireFin) >= 0) {
                aRetourner = 2;
            /* 
             * Dernier cas, l'horaire de fin de ce RendezVous a lieu avant
             * l'horaire de fin à comparer.
             */
            } else {
                aRetourner = -1;
            }
        /* 
         * Dernier cas, L'horaire de début de ce RendezVous a lieu après 
         * l'horaire de début de celui à comparer 
         */
        } else {
            /* 
             * Premier cas, l'horaire de fin de ce RendezVous a lieu après que
             * l'horaire de fin à comparer.
             */
            if (this.horaireFin.compareTo(aComparer.horaireFin) > 0) {
                aRetourner = 1;
            /* 
             * Dernier cas, l'horaire de fin de ce RendezVous a lieu avant ou en
             * même temps que l'horaire de fin à comparer.
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
     * On considère qu'un RendezVous est equals à un autre RendezVous si les
     * Horaire de début et de fin sont identiques.
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
