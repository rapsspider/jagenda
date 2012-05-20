/*
 * Fichier :	Config.java
 * Package :	jagenda
 * Projet jAgenda - Info 1 2011-2012
 *
 */
package jagenda.util;

import jagenda.Horaire;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Permet la configurationdu logiciel jAgenda.<br>
 * L'utilisateur possède la liberté de spécifier ses préférences d'utilisation
 * du logiciel.</p>
 * <ul><li><code>anneeDebut</code> :<br>
 *         Année de départ de la période de temps gérée par l'agenda.<br>
 *         Il est nécessaire que <code>anneeDebut</code> soit postérieure ou 
 *         égale à 1950.<br><br>
 *         Par défaut, <code>anneeDebut=2012</code>.
 * </li></ul>
 * <br>
 * <ul><li><code>anneeFin</code> :<br>
 *         Année de fin de la période de temps gérée par l'agenda.<br>
 *         Il est nécessaire que <code>anneeFin</code> soit antérieure ou 
 *         égale à 2050.<br><br>
 *         Par défaut, <code>anneeFin=2012</code>.
 * </li></ul>
<ul><li><code>confirmRdv</code> :<br>
 *         Si elle est activée, cette option permet l'affichage du résumé de la
 *         saisie d'un rendez-vous avant de confirmer sa sauvegarde.<br><br>
 *         Par défaut, <code>confirmRdv=1</code> (activée).
 * </li></ul>
 * <br>
 * <ul><li><code>consoleHauteur</code> :<br>
 *         Hauteur d'affichage de la console de commande en nombre de
 *         lignes.<br><br>
 *         Par défaut, <code>consoleHauteur=25</code>.
 * </li></ul>
 * <br>
 * <ul><li><code>consoleLargeur</code> :<br>
 *         Largeur d'affichage de la console de commande en nombre de
 *         caractères.<br><br>
 *         Par défaut, <code>consoleHauteur=100</code>.
 * </li></ul>
 * <br>
 * <ul><li><code>formatDate</code> :<br>
 *         Format désiré pour la saisie de dates, à savoir, soit 
 *         "<i>{@code jj/mm/aaaa}</i>", soit "<i>{@code jour_semaine} semaine 
 *         {@code numero_semaine} de {@code annee}</i>".<br>
 *         <i>
 *         Exemples format 1 : "05/07/2012", "28/02/1999", "31/07/2002"...<br>
 *         Exemple format 2 : "Jeudi de la semaine 11 de 2011", ...<br>
 *         </i><br>
 *         Par défaut, <code>formatDate=1</code> 
 *         (format "<i>{@code jj/mm/aaaa}</i>").
 * </li></ul>
 * <br>
 * <ul><li><code>nbMaximumRdv</code> :<br>
 *         Nombre maximum de rendez-vous pouvant être gérés par le logiciel 
 *         jAgenda.<br><br>
 *         Par défaut, <code>nbMaximumRdv=50</code>.
 * </li></ul>
 * <br>
 * <ul><li><code>typeSauvegarde</code> :<br>
 *         Préférence de sauvegarde des rendez-vous, à savoir, soit <i>
 *         enregistrement à chaque ajout ou modification d'un rendez-vous</i>
 *         (sécurisé mais détériore la performance du programme), soit
 *         <i>enregistrement à la fermeture du programme</i> (non sécurisé mais
 *         améliore nettement la performance du programme).<br><br>
 *         Par défaut, <code>typeSauvegarde=0</code>.
 * </li></ul>
 * 
 * @author  Jason BOURLARD
 * @author  David PELISSIER
 * @version 0.3
 */
public class Config {

    /** Chemin relatif du fichier de configuration du logiciel. */
    private final static String CHEMIN_CONFIG = "data";

    /** Nom du fichier de configuration du logiciel. */
    private final static String FICHIER_CONFIG = "jagenda.config";
   
    /** Commentaire mis en début du fichier de configuration */
    private final static String COMMENTAIRES_CONFIG = 
        "# ATTENTION, la modification manuelle des données contenues dans \n" +
        "# ce fichier peut affecter le fonctionnement du jAgenda.";

    /** 
     * Expression régulière permettant de lire les données du fichier de 
     * configuration.<br>
     * Format : <code>parametre=valeur</code><br>
     * <i>Exemple : <code>nbMaximumRdv=50</code></i>
     */
    private final static Pattern STRUCTURE_CONFIG = 
        Pattern.compile("([a-zA-Z]+)=(\\d{1,4})");

    /**
     * Année de départ de la période de temps gérée par l'agenda.<br>
     * Il est nécessaire que <code>anneeDebut</code> soit postérieure ou 
     * égale à 1950.<br><br>
     * Par défaut, <code>anneeDebut=2012</code>.
     */
    private int anneeDebut;
    
    /**
     * Année de fin de la période de temps gérée par l'agenda.<br>
     * Il est nécessaire que <code>anneeFin</code> soit antérieure ou 
     * égale à 2050.<br><br>
     * Par défaut, <code>anneeFin=2012</code>.
     */
    private int anneeFin;
    
    /**
     * Si elle est activée, cette option permet l'affichage du résumé de la
     * saisie d'un rendez-vous avant de confirmer sa sauvegarde.<br><br>
     * Par défaut, <code>confirmRdv=1</code> (activée).
     */
    private int confirmRdv;
        
    /**
     * Hauteur d'affichage de la console de commande en nombre de
     * lignes.<br><br>
     * Par défaut, <code>consoleHauteur=25</code>.
     */
    private int consoleHauteur;
    
    /**
     * Largeur d'affichage de la console de commande en nombre de
     * caractères.<br><br>
     * Par défaut, <code>consoleLargeur=100</code>.
     */
    private int consoleLargeur;
    
    /**
     * Format désiré pour la saisie de dates, à savoir, soit 
     * "<i>{@code jj/mm/aaaa}</i>", soit "<i>{@code jour_semaine} semaine 
     * {@code numero_semaine} de {@code annee}</i>".<br>
     * <i>
     * Exemples format 1 : "05/07/2012", "28/02/1999", "31/07/2002"...<br>
     * Exemple format 2 : "Jeudi de la semaine 11 de 2011", ...<br>
     * </i><br>
     * Par défaut, <code>formatDate=1</code> 
     * (format "<i>{@code jj/mm/aaaa}</i>").
     */
    private int formatDate;
    
    /**
     * Nombre maximum de rendez-vous pouvant être gérés par le logiciel 
     * jAgenda.<br><br>
     * Par défaut, <code>nbMaximumRdv=50</code>.
     */
    private int nbMaximumRdv;
    
    /**
     * Préférence de sauvegarde des rendez-vous.
     * Soit <i> enregistrement à chaque ajout ou modification d'un rendez-vous
     * (sécurisé mais détériore la performance du programme)</i>, soit
     * <i>enregistrement à la fermeture du programme (non sécurisé mais
     * améliore nettement la performance du programme)</i>.<br>
     * Par défaut, <code>typeSauvegarde=0</code>.
     */
    private int typeSauvegarde;
    
    /**
     * Constructeur par defaut d'une configuration.<br>
     * Par defaut :
     * <ul><li><code>anneeDebut=2012</code></li>
     *     <li><code>anneeFin=2012</code></li>
     *     <li><code>confirmRdv=1</code> (activée)</li>
     *     <li><code>consoleHauteur=25</code></li>
     *     <li><code>consoleLargeur=100</code></li>
     *     <li><code>formatDate=1</code> (format "<i>{@code jj/mm/aaaa}</i>")</li>
     *     <li><code>nbMaximumRdv=50</code></li>
     *     <li><code>typeSauvegarde=0</code></li></ul>
     */
    public Config() {
        this.anneeDebut     = 2012;
        this.anneeFin       = 2012;
        this.confirmRdv     = 1;
        this.consoleLargeur = 100;
        this.consoleHauteur = 25;
        this.formatDate     = 0;
        this.nbMaximumRdv   = 50;
        this.typeSauvegarde = 0;
    }; 
    
    /**
     * Accesseur pour le champ <code>anneeDebut</code>.
     * @return Année de début de la période de temps gérée par l'agenda.<br>
     * @see #anneeDebut
     */
    public int getAnneeDebut() {
        return anneeDebut;
    }

    /**
     * Accesseur pour le champ <code>anneeFin</code>.
     * @return Année de fin de la période de temps gérée par l'agenda.<br>
     * @see #anneeFin
     */
    public int getAnneeFin() {
        return anneeFin;
    }
    
    /**
     * Accesseur pour le champ <code>confirmRdv</code>.
     * @return <code>1</code> si l'option est activée.<br>
     *         <code>0</code> si l'option est désactivée.
     * @see #confirmRdv
     */
    public int getConfirmRdv() {
        return confirmRdv;
    }

    /**
     * Accesseur pour le champ <code>consoleHauteur</code>.
     * @return Hauteur de la console de commande en nombre de lignes.
     * @see #consoleHauteur
     */
    public int getConsoleHauteur() {
        return consoleHauteur;
    }

    /**
     * Accesseur pour le champ <code>consoleLargeur</code>.
     * @return Largeur de la console de commande en nombre de caractères.
     * @see #consoleLargeur
     */
    public int getConsoleLargeur() {
        return consoleLargeur;
    }

    /**
     * Accesseur pour le champ <code>formatDate</code>.
     * @return Format désiré pour la saisie de dates, à savoir,<br>
     *         soit <code>1</code> pour le format 1,<br>
     *         soit <code>2</code> pour le format 2.
     * @see #formatDate
     */
    public int getFormatDate() {
        return formatDate;
    }


    /**
     * Accesseur pour le champ <code>nbMaximumRdv</code>.
     * @return Nombre maximum de rendez-vous pouvant être gérés par le logiciel 
     *         jAgenda.
     * @see #nbMaximumRdv
     */
    public int getNbMaximumRdv() {
        return nbMaximumRdv;
    }

    /**
     * Accesseur pour le champ <code>typeSauvegarde</code>.
     * @return Préférence de sauvegarde des rendez-vous, à savoir,<br> 
     *         soit <code>1</code> pour un <i>enregistrement à chaque ajout ou 
     *         modification d'un rendez-vous</i>,<br>
     *         soit <code>0</code> pour un <i>enregistrement à la fermeture du 
     *         programme</i>.
     * @see #typeSauvegarde
     */
    public int getTypeSauvegarde() {
        return typeSauvegarde;
    }

    /**
     * Mutateur pour le champ <code>anneeDebut</code>.<br>
     * Valeurs possibles : : Nombre entre 1950 et <code>anneeFin</code>.
     * @param anneeDebut Année de début de la période de temps gérée par 
     *                   l'agenda à attribuer.
     */
    public void setAnneeDebut(int anneeDebut) {
        if (Horaire.AN_MIN <= anneeDebut && anneeDebut <= this.anneeFin) {
            this.anneeDebut = anneeDebut;
        }
    }

    /**
     * Mutateur pour le champ <code>anneeFin</code>.<br>
     * Valeurs possibles : Nombre entre <code>anneeDebut</code> et 2050.
     * @param anneeFin   Année de fin de la période de temps gérée par 
     *                   l'agenda à attribuer.
     */
    public void setAnneeFin(int anneeFin) {
        if (this.anneeDebut <= anneeFin && anneeFin <= Horaire.AN_MAX) {
            this.anneeFin = anneeFin;
        }
    }

    /**
     * Mutateur pour le champ <code>confirmRdv</code>.<br>
     * Valeurs possibles : 0 (désactivée) ou 1 (activée).
     * @param confirmRdv <code>1</code> si l'option doit être.<br>
     *                   <code>0</code> si l'option doit être désactivée.
     */
    public void setConfirmRdv(int confirmRdv) {
        this.confirmRdv = confirmRdv;
    }

    /**
     * Mutateur pour le champ <code>consoleHauteur</code>.
     * @param consoleHauteur Hauteur de la console de commande en nombre de 
     *                       lignes à attribuer.
     */
    public void setConsoleHauteur(int consoleHauteur) {
        this.consoleHauteur = consoleHauteur;
    }

    /**
     * Mutateur pour le champ <code>consoleLargeur</code>.
     * @param consoleLargeur Largeur de la console de commande en nombre de 
     *                       caractères à attribuer.
     */
    public void setConsoleLargeur(int consoleLargeur) {
        this.consoleLargeur = consoleLargeur;
    }

    /**
     * Mutateur pour le champ <code>formatDate</code>.<br>
     * Valeurs possibles : 0 (désactivée) ou 1 (activée).
     * @param formatDate Format de date lors des saisies.
     */
    public void setFormatDate(int formatDate) {
        if (formatDate == 0 || formatDate == 1) {
            this.formatDate = formatDate;
        }
    }

    /**
     * Mutateur pour le champ <code>nbMaximumRdv</code>.<br>
     * La valeur entrée doit être supérieure au nombre de rendez-vous existants.
     * @param nbMaximumRdv Nombre maximum de rendez-vous pouvant être gérés à
     *                     attribuer.
     */
    public void setNbMaximumRdv(int nbMaximumRdv) {
        if (nbMaximumRdv > 20) {
            this.nbMaximumRdv = nbMaximumRdv;
        }
    }

    /**
     * Mutateur pour le champ <code>typeSauvegarde</code>.<br>
     * Valeurs possibles : 0 (enregistrement à la fermeture du logiciel) ou 1 
     * (enregistrement après chaque modification).
     * @param typeSauvegarde <code>1</code> pour un enregistrement après chaque 
     *                       modification.<br>
     *                       <code>0</code> pour un enregistrement à la 
     *                       fermeture du logiciel.
     */
    public void setTypeSauvegarde(int typeSauvegarde) {
        if(typeSauvegarde == 0 || typeSauvegarde == 1) {
            this.typeSauvegarde = typeSauvegarde;
        }
    } 

    /**
     * Charge le contenu du fichier de configuration si celui-ci existe, et met
     * à jour les paramètres de la configuration courante.<br>
     * Dans le cas contraire, la commande ne modifie pas aucun attribut.
     * @return <code>true</code> si le chargement a bien été éxecutée.
     *         <code>false</code> si une erreur a été rencontrée.
     */
    public boolean chargeConfig() {
        boolean reussie = true;     // Code de retour de la commande. 
        String ligne;               // Ligne du fichier de config à l'index i
        String parametre;           // Désignation du paramètre
        int valeur;                 // Valeur du paramètre
        Matcher valeursConfig;      

        try {
            Fichier fichierConfig = new Fichier(CHEMIN_CONFIG, FICHIER_CONFIG);

            // Initialisation du flux d'entrées dit de lecture
            fichierConfig.initLecture();

            while ((ligne = fichierConfig.lireLigne()) != null) {
                valeursConfig = STRUCTURE_CONFIG.matcher(ligne);
                if (valeursConfig.matches()) {
                    // Récupération du paramètre et de sa valeur
                    parametre = valeursConfig.group(1);
                    valeur = Integer.parseInt(valeursConfig.group(2));
                    // Mise à jour des données par les nouvelles les.
                    if (parametre.equals("anneeDebut")) {
                        setAnneeDebut(valeur);
                    } else if (parametre.equals("anneefin")) {
                        setAnneeFin(valeur);
                    } else if (parametre.equals("confirmRdv")) {
                        setConfirmRdv(valeur);
                    } else if (parametre.equals("consoleHauteur")) {
                        setConsoleHauteur(valeur);
                    } else if (parametre.equals("consoleLargeur")) {
                        setConsoleLargeur(valeur);                
                    } else if (parametre.equals("formatDate")) {
                        setFormatDate(valeur);
                    } else if (parametre.equals("nbMaximumRdv")) {
                        setNbMaximumRdv(valeur);
                    } else if (parametre.equals("typeSauvegarde")) {
                        setTypeSauvegarde(valeur);
                    } else {
                        reussie = false;
                    }
                }
            }
            // Fermeture du flux d'entrées dit de lecture
            fichierConfig.fermeLecture();
        } catch (FileNotFoundException fileEx) {
            // Le fichier de configuration n'existe pas.
            reussie = false;
        } catch (IOException ioEx) {
            // Nom ou chemin de fichier incorrect.
            // Problème de droits d'accès ?
            reussie = false;
        }
        return reussie;
    }
    
    /**
     * Sauvegarde la configuration courante dans le fichier de configuration si 
     * celui-ci existe.<br>
     * Dans le cas contraire, la commande ne modifie pas le fichier.
     * @return <code>true</code> si la sauvegarde a bien été éxecutée.
     *         <code>false</code> si une erreur a été rencontrée.
     */
    public boolean sauveConfig() {
        boolean reussie = true;     // Code de retour de la commande. 

        try {
            Fichier fichierConfig = new Fichier(CHEMIN_CONFIG, FICHIER_CONFIG);

            // Initialisation du flux de sorties dit d'écriture
            fichierConfig.initEcriture(false);

            fichierConfig.ecrire(COMMENTAIRES_CONFIG);
            fichierConfig.ecrire("anneeDebut=" + anneeDebut);
            fichierConfig.ecrire("anneeFin=" + anneeFin);
            fichierConfig.ecrire("confirmRdv=" + confirmRdv);
            fichierConfig.ecrire("consoleHauteur=" + consoleHauteur);
            fichierConfig.ecrire("consoleLargeur=" + consoleLargeur);
            fichierConfig.ecrire("formatDate=" + formatDate);
            fichierConfig.ecrire("nbMaximumRdv=" + nbMaximumRdv);
            fichierConfig.ecrire("typeSauvegarde=" + typeSauvegarde);

            // Fermeture du flux de sorties dit d'écriture
            fichierConfig.fermeEcriture();
        } catch (FileNotFoundException fileEx) {
            // Le fichier de configuration n'existe pas.
            reussie = false;
        } catch (IOException ioEx) {
            // Nom ou chemin de fichier incorrect.
            // Problème de droits d'accès ?
            reussie = false;
        }
        return reussie;
    }
}