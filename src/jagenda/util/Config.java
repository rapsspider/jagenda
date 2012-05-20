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
 * L'utilisateur poss�de la libert� de sp�cifier ses pr�f�rences d'utilisation
 * du logiciel.</p>
 * <ul><li><code>anneeDebut</code> :<br>
 *         Ann�e de d�part de la p�riode de temps g�r�e par l'agenda.<br>
 *         Il est n�cessaire que <code>anneeDebut</code> soit post�rieure ou 
 *         �gale � 1950.<br><br>
 *         Par d�faut, <code>anneeDebut=2012</code>.
 * </li></ul>
 * <br>
 * <ul><li><code>anneeFin</code> :<br>
 *         Ann�e de fin de la p�riode de temps g�r�e par l'agenda.<br>
 *         Il est n�cessaire que <code>anneeFin</code> soit ant�rieure ou 
 *         �gale � 2050.<br><br>
 *         Par d�faut, <code>anneeFin=2012</code>.
 * </li></ul>
<ul><li><code>confirmRdv</code> :<br>
 *         Si elle est activ�e, cette option permet l'affichage du r�sum� de la
 *         saisie d'un rendez-vous avant de confirmer sa sauvegarde.<br><br>
 *         Par d�faut, <code>confirmRdv=1</code> (activ�e).
 * </li></ul>
 * <br>
 * <ul><li><code>consoleHauteur</code> :<br>
 *         Hauteur d'affichage de la console de commande en nombre de
 *         lignes.<br><br>
 *         Par d�faut, <code>consoleHauteur=25</code>.
 * </li></ul>
 * <br>
 * <ul><li><code>consoleLargeur</code> :<br>
 *         Largeur d'affichage de la console de commande en nombre de
 *         caract�res.<br><br>
 *         Par d�faut, <code>consoleHauteur=100</code>.
 * </li></ul>
 * <br>
 * <ul><li><code>formatDate</code> :<br>
 *         Format d�sir� pour la saisie de dates, � savoir, soit 
 *         "<i>{@code jj/mm/aaaa}</i>", soit "<i>{@code jour_semaine} semaine 
 *         {@code numero_semaine} de {@code annee}</i>".<br>
 *         <i>
 *         Exemples format 1 : "05/07/2012", "28/02/1999", "31/07/2002"...<br>
 *         Exemple format 2 : "Jeudi de la semaine 11 de 2011", ...<br>
 *         </i><br>
 *         Par d�faut, <code>formatDate=1</code> 
 *         (format "<i>{@code jj/mm/aaaa}</i>").
 * </li></ul>
 * <br>
 * <ul><li><code>nbMaximumRdv</code> :<br>
 *         Nombre maximum de rendez-vous pouvant �tre g�r�s par le logiciel 
 *         jAgenda.<br><br>
 *         Par d�faut, <code>nbMaximumRdv=50</code>.
 * </li></ul>
 * <br>
 * <ul><li><code>typeSauvegarde</code> :<br>
 *         Pr�f�rence de sauvegarde des rendez-vous, � savoir, soit <i>
 *         enregistrement � chaque ajout ou modification d'un rendez-vous</i>
 *         (s�curis� mais d�t�riore la performance du programme), soit
 *         <i>enregistrement � la fermeture du programme</i> (non s�curis� mais
 *         am�liore nettement la performance du programme).<br><br>
 *         Par d�faut, <code>typeSauvegarde=0</code>.
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
   
    /** Commentaire mis en d�but du fichier de configuration */
    private final static String COMMENTAIRES_CONFIG = 
        "# ATTENTION, la modification manuelle des donn�es contenues dans \n" +
        "# ce fichier peut affecter le fonctionnement du jAgenda.";

    /** 
     * Expression r�guli�re permettant de lire les donn�es du fichier de 
     * configuration.<br>
     * Format : <code>parametre=valeur</code><br>
     * <i>Exemple : <code>nbMaximumRdv=50</code></i>
     */
    private final static Pattern STRUCTURE_CONFIG = 
        Pattern.compile("([a-zA-Z]+)=(\\d{1,4})");

    /**
     * Ann�e de d�part de la p�riode de temps g�r�e par l'agenda.<br>
     * Il est n�cessaire que <code>anneeDebut</code> soit post�rieure ou 
     * �gale � 1950.<br><br>
     * Par d�faut, <code>anneeDebut=2012</code>.
     */
    private int anneeDebut;
    
    /**
     * Ann�e de fin de la p�riode de temps g�r�e par l'agenda.<br>
     * Il est n�cessaire que <code>anneeFin</code> soit ant�rieure ou 
     * �gale � 2050.<br><br>
     * Par d�faut, <code>anneeFin=2012</code>.
     */
    private int anneeFin;
    
    /**
     * Si elle est activ�e, cette option permet l'affichage du r�sum� de la
     * saisie d'un rendez-vous avant de confirmer sa sauvegarde.<br><br>
     * Par d�faut, <code>confirmRdv=1</code> (activ�e).
     */
    private int confirmRdv;
        
    /**
     * Hauteur d'affichage de la console de commande en nombre de
     * lignes.<br><br>
     * Par d�faut, <code>consoleHauteur=25</code>.
     */
    private int consoleHauteur;
    
    /**
     * Largeur d'affichage de la console de commande en nombre de
     * caract�res.<br><br>
     * Par d�faut, <code>consoleLargeur=100</code>.
     */
    private int consoleLargeur;
    
    /**
     * Format d�sir� pour la saisie de dates, � savoir, soit 
     * "<i>{@code jj/mm/aaaa}</i>", soit "<i>{@code jour_semaine} semaine 
     * {@code numero_semaine} de {@code annee}</i>".<br>
     * <i>
     * Exemples format 1 : "05/07/2012", "28/02/1999", "31/07/2002"...<br>
     * Exemple format 2 : "Jeudi de la semaine 11 de 2011", ...<br>
     * </i><br>
     * Par d�faut, <code>formatDate=1</code> 
     * (format "<i>{@code jj/mm/aaaa}</i>").
     */
    private int formatDate;
    
    /**
     * Nombre maximum de rendez-vous pouvant �tre g�r�s par le logiciel 
     * jAgenda.<br><br>
     * Par d�faut, <code>nbMaximumRdv=50</code>.
     */
    private int nbMaximumRdv;
    
    /**
     * Pr�f�rence de sauvegarde des rendez-vous.
     * Soit <i> enregistrement � chaque ajout ou modification d'un rendez-vous
     * (s�curis� mais d�t�riore la performance du programme)</i>, soit
     * <i>enregistrement � la fermeture du programme (non s�curis� mais
     * am�liore nettement la performance du programme)</i>.<br>
     * Par d�faut, <code>typeSauvegarde=0</code>.
     */
    private int typeSauvegarde;
    
    /**
     * Constructeur par defaut d'une configuration.<br>
     * Par defaut :
     * <ul><li><code>anneeDebut=2012</code></li>
     *     <li><code>anneeFin=2012</code></li>
     *     <li><code>confirmRdv=1</code> (activ�e)</li>
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
     * @return Ann�e de d�but de la p�riode de temps g�r�e par l'agenda.<br>
     * @see #anneeDebut
     */
    public int getAnneeDebut() {
        return anneeDebut;
    }

    /**
     * Accesseur pour le champ <code>anneeFin</code>.
     * @return Ann�e de fin de la p�riode de temps g�r�e par l'agenda.<br>
     * @see #anneeFin
     */
    public int getAnneeFin() {
        return anneeFin;
    }
    
    /**
     * Accesseur pour le champ <code>confirmRdv</code>.
     * @return <code>1</code> si l'option est activ�e.<br>
     *         <code>0</code> si l'option est d�sactiv�e.
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
     * @return Largeur de la console de commande en nombre de caract�res.
     * @see #consoleLargeur
     */
    public int getConsoleLargeur() {
        return consoleLargeur;
    }

    /**
     * Accesseur pour le champ <code>formatDate</code>.
     * @return Format d�sir� pour la saisie de dates, � savoir,<br>
     *         soit <code>1</code> pour le format 1,<br>
     *         soit <code>2</code> pour le format 2.
     * @see #formatDate
     */
    public int getFormatDate() {
        return formatDate;
    }


    /**
     * Accesseur pour le champ <code>nbMaximumRdv</code>.
     * @return Nombre maximum de rendez-vous pouvant �tre g�r�s par le logiciel 
     *         jAgenda.
     * @see #nbMaximumRdv
     */
    public int getNbMaximumRdv() {
        return nbMaximumRdv;
    }

    /**
     * Accesseur pour le champ <code>typeSauvegarde</code>.
     * @return Pr�f�rence de sauvegarde des rendez-vous, � savoir,<br> 
     *         soit <code>1</code> pour un <i>enregistrement � chaque ajout ou 
     *         modification d'un rendez-vous</i>,<br>
     *         soit <code>0</code> pour un <i>enregistrement � la fermeture du 
     *         programme</i>.
     * @see #typeSauvegarde
     */
    public int getTypeSauvegarde() {
        return typeSauvegarde;
    }

    /**
     * Mutateur pour le champ <code>anneeDebut</code>.<br>
     * Valeurs possibles : : Nombre entre 1950 et <code>anneeFin</code>.
     * @param anneeDebut Ann�e de d�but de la p�riode de temps g�r�e par 
     *                   l'agenda � attribuer.
     */
    public void setAnneeDebut(int anneeDebut) {
        if (Horaire.AN_MIN <= anneeDebut && anneeDebut <= this.anneeFin) {
            this.anneeDebut = anneeDebut;
        }
    }

    /**
     * Mutateur pour le champ <code>anneeFin</code>.<br>
     * Valeurs possibles : Nombre entre <code>anneeDebut</code> et 2050.
     * @param anneeFin   Ann�e de fin de la p�riode de temps g�r�e par 
     *                   l'agenda � attribuer.
     */
    public void setAnneeFin(int anneeFin) {
        if (this.anneeDebut <= anneeFin && anneeFin <= Horaire.AN_MAX) {
            this.anneeFin = anneeFin;
        }
    }

    /**
     * Mutateur pour le champ <code>confirmRdv</code>.<br>
     * Valeurs possibles : 0 (d�sactiv�e) ou 1 (activ�e).
     * @param confirmRdv <code>1</code> si l'option doit �tre.<br>
     *                   <code>0</code> si l'option doit �tre d�sactiv�e.
     */
    public void setConfirmRdv(int confirmRdv) {
        this.confirmRdv = confirmRdv;
    }

    /**
     * Mutateur pour le champ <code>consoleHauteur</code>.
     * @param consoleHauteur Hauteur de la console de commande en nombre de 
     *                       lignes � attribuer.
     */
    public void setConsoleHauteur(int consoleHauteur) {
        this.consoleHauteur = consoleHauteur;
    }

    /**
     * Mutateur pour le champ <code>consoleLargeur</code>.
     * @param consoleLargeur Largeur de la console de commande en nombre de 
     *                       caract�res � attribuer.
     */
    public void setConsoleLargeur(int consoleLargeur) {
        this.consoleLargeur = consoleLargeur;
    }

    /**
     * Mutateur pour le champ <code>formatDate</code>.<br>
     * Valeurs possibles : 0 (d�sactiv�e) ou 1 (activ�e).
     * @param formatDate Format de date lors des saisies.
     */
    public void setFormatDate(int formatDate) {
        if (formatDate == 0 || formatDate == 1) {
            this.formatDate = formatDate;
        }
    }

    /**
     * Mutateur pour le champ <code>nbMaximumRdv</code>.<br>
     * La valeur entr�e doit �tre sup�rieure au nombre de rendez-vous existants.
     * @param nbMaximumRdv Nombre maximum de rendez-vous pouvant �tre g�r�s �
     *                     attribuer.
     */
    public void setNbMaximumRdv(int nbMaximumRdv) {
        if (nbMaximumRdv > 20) {
            this.nbMaximumRdv = nbMaximumRdv;
        }
    }

    /**
     * Mutateur pour le champ <code>typeSauvegarde</code>.<br>
     * Valeurs possibles : 0 (enregistrement � la fermeture du logiciel) ou 1 
     * (enregistrement apr�s chaque modification).
     * @param typeSauvegarde <code>1</code> pour un enregistrement apr�s chaque 
     *                       modification.<br>
     *                       <code>0</code> pour un enregistrement � la 
     *                       fermeture du logiciel.
     */
    public void setTypeSauvegarde(int typeSauvegarde) {
        if(typeSauvegarde == 0 || typeSauvegarde == 1) {
            this.typeSauvegarde = typeSauvegarde;
        }
    } 

    /**
     * Charge le contenu du fichier de configuration si celui-ci existe, et met
     * � jour les param�tres de la configuration courante.<br>
     * Dans le cas contraire, la commande ne modifie pas aucun attribut.
     * @return <code>true</code> si le chargement a bien �t� �xecut�e.
     *         <code>false</code> si une erreur a �t� rencontr�e.
     */
    public boolean chargeConfig() {
        boolean reussie = true;     // Code de retour de la commande. 
        String ligne;               // Ligne du fichier de config � l'index i
        String parametre;           // D�signation du param�tre
        int valeur;                 // Valeur du param�tre
        Matcher valeursConfig;      

        try {
            Fichier fichierConfig = new Fichier(CHEMIN_CONFIG, FICHIER_CONFIG);

            // Initialisation du flux d'entr�es dit de lecture
            fichierConfig.initLecture();

            while ((ligne = fichierConfig.lireLigne()) != null) {
                valeursConfig = STRUCTURE_CONFIG.matcher(ligne);
                if (valeursConfig.matches()) {
                    // R�cup�ration du param�tre et de sa valeur
                    parametre = valeursConfig.group(1);
                    valeur = Integer.parseInt(valeursConfig.group(2));
                    // Mise � jour des donn�es par les nouvelles les.
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
            // Fermeture du flux d'entr�es dit de lecture
            fichierConfig.fermeLecture();
        } catch (FileNotFoundException fileEx) {
            // Le fichier de configuration n'existe pas.
            reussie = false;
        } catch (IOException ioEx) {
            // Nom ou chemin de fichier incorrect.
            // Probl�me de droits d'acc�s ?
            reussie = false;
        }
        return reussie;
    }
    
    /**
     * Sauvegarde la configuration courante dans le fichier de configuration si 
     * celui-ci existe.<br>
     * Dans le cas contraire, la commande ne modifie pas le fichier.
     * @return <code>true</code> si la sauvegarde a bien �t� �xecut�e.
     *         <code>false</code> si une erreur a �t� rencontr�e.
     */
    public boolean sauveConfig() {
        boolean reussie = true;     // Code de retour de la commande. 

        try {
            Fichier fichierConfig = new Fichier(CHEMIN_CONFIG, FICHIER_CONFIG);

            // Initialisation du flux de sorties dit d'�criture
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

            // Fermeture du flux de sorties dit d'�criture
            fichierConfig.fermeEcriture();
        } catch (FileNotFoundException fileEx) {
            // Le fichier de configuration n'existe pas.
            reussie = false;
        } catch (IOException ioEx) {
            // Nom ou chemin de fichier incorrect.
            // Probl�me de droits d'acc�s ?
            reussie = false;
        }
        return reussie;
    }
}