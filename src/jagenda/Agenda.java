/*
 * Fichier : 	Jagenda.java
 * Package : 	jagenda
 * Projet jAgenda - Info 1 2011-2012
 */
package jagenda;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jagenda.util.Config;
import jagenda.util.Fichier;
import jagenda.util.Saisie;

/**
 * <p>La classe <code>Agenda</code> repr�sente un agenda, permettant � un 
 * utilisateur de g�rer des rendez-vous, les modifier, les supprimer.</p>
 * <br>
 * <p>Les rendez-vous peuvent �tre d�finis de fa�on ponctuelle, cependant, il
 * est possible que l'utilisateur souhaite fixer une activit� r�guli�re dans 
 * l'agenda comme par exemple un cours de piano tous les jeudis de 17h � 18h30 
 * donc les rendez-vous pourront aussi �tre r�currents.</p>
 * <br>
 * <p>Les dates de rendez-vous peuvent �tre saisies sous deux formats 
 * diff�rents, � savoir, {@code jj/mm/aaaa} ou encore {@code jour_semaine} et 
 * {@code numero_semaine}.</p>
 * <br>
 * <p>Un enregistrement des rendez-vous est effectu� dans un fichier de donn�es.
 * De plus, le logiciel impl�mente une sauvegarde de configuration permettant � 
 * l'utilisateur de sp�cifier vos pr�f�rences concernant les formats de date � 
 * utiliser, l'ann�e g�r�e par l'agenda,...</p>
 * 
 * @author  Jason BOURLARD
 * @author  David PELISSIER
 * @version 0.3
 */
public class Agenda {

    /** 
     * Chemin menant au fichier de sauvegarde .
     */
    private static final String CHEMIN_SAUV = "data";

    /** 
     * Nom du fichier de sauvegarde.
     */
    private static final String FICHIER_SAUV = "jagenda.Rdv";
    
    /** 
     * Expression r�guli�re permettant de lire les donn�es.
     * <i>Exemple : jj/mm/aaaa::hh:mm::hh:mm::libelle:nature::description</i>
     */
    private static final Pattern STRUCTURE_DONNEES = 
            Pattern.compile("(\\d{1,2})/(\\d{1,2})/(\\d{4})::" + 
                    "(\\d{1,2})(h|H|:)(\\d{1,2})::(\\d{1,2})(h|H|:)(\\d{1,2})" +
                    "::(.+)::(.+)::(.+)");
    
    /** Liste des rendez vous de l'agenda. */
    private RendezVous[] listeRdv;

    /** Objet permettant les saisies sur l'entr�e/sortie standard */
    public Saisie saisie;

    /** Configuration qui va contenir les pr�f�rences de l'utilisateur. */
    public Config config;

    /**
     * Constructeur par defaut d'un agenda.<br>
     * <p>Initialise sa configuration et charge les donn�es du fichier de 
     * configuration si celui-ci existe, initialise sa liste de rendez-vous 
     * ainsi qu'un objet de type <code>Saisie</code> permettant la saisie de 
     * donn�es sur l'entr�e/sortie standard.</p>
     */
    public Agenda() {
        System.out.println("\n\t\t\tLogiciel jAgenda\n");
        config = new Config();
        saisie = new Saisie();
        config.chargeConfig();
    }
    
    /**
     * Accesseur pour le champ <code>nbRdv</code>.
     * @return Nombre de rendez-vous que contient l'agenda courant. 	
     */
    public int getNbRdv() {
        return listeRdv.length;
    }
    
    /**
     * Accesseur pour le champ <code>anneeDebut</code> dans le fichier de
     * configuration.
     * @return Ann�e de d�but de la p�riode de temps g�r�e par l'agenda.<br>
     * @see jagenda.util.Config#anneeDebut
     */
    public int getAnneeDebut() {
        return this.config.getAnneeDebut();
    }

    /**
     * Accesseur pour le champ <code>anneeFin</code> dans le fichier de
     * configuration.
     * @return Ann�e de fin de la p�riode de temps g�r�e par l'agenda.<br>
     * @see #anneeFin
     */
    public int getAnneeFin() {
        return this.config.getAnneeFin();    }
    
    /**
     * Accesseur pour le champ <code>confirmRdv</code> dans le fichier de
     * configuration.
     * @return <code>1</code> si l'option est activ�e.<br>
     *         <code>0</code> si l'option est d�sactiv�e.
     * @see #confirmRdv
     */
    public int getConfirmRdv() {
        return this.config.getConfirmRdv();
    }

    /**
     * Accesseur pour le champ <code>consoleHauteur</code> dans le fichier de
     * configuration.
     * @return Hauteur de la console de commande en nombre de lignes.
     * @see #consoleHauteur
     */
    public int getConsoleHauteur() {
        return this.config.getConsoleHauteur();
    }

    /**
     * Accesseur pour le champ <code>consoleLargeur</code> dans le fichier de
     * configuration.
     * @return Largeur de la console de commande en nombre de caract�res.
     * @see #consoleLargeur
     */
    public int getConsoleLargeur() {
        return this.config.getConsoleLargeur();
    }

    /**
     * Accesseur pour le champ <code>formatDate</code> dans le fichier de
     * configuration.
     * @return Format d�sir� pour la saisie de dates, � savoir,<br>
     *         soit <code>1</code> pour le format 1,<br>
     *         soit <code>2</code> pour le format 2.
     * @see #formatDate
     */
    public int getFormatDate() {
        return this.config.getFormatDate();
    }


    /**
     * Accesseur pour le champ <code>nbMaximumRdv</code> dans le fichier de
     * configuration.
     * @return Nombre maximum de rendez-vous pouvant �tre g�r�s par le logiciel 
     *         jAgenda.
     * @see #nbMaximumRdv
     */
    public int getNbMaximumRdv() {
        return this.config.getNbMaximumRdv();
    }

    /**
     * Accesseur pour le champ <code>typeSauvegarde</code> dans le fichier de
     * configuration.
     * @return Pr�f�rence de sauvegarde des rendez-vous, � savoir,<br> 
     *         soit <code>1</code> pour un <i>enregistrement � chaque ajout ou 
     *         modification d'un rendez-vous</i>,<br>
     *         soit <code>0</code> pour un <i>enregistrement � la fermeture du 
     *         programme</i>.
     * @see #typeSauvegarde
     */
    public int getTypeSauvegarde() {
        return this.config.getTypeSauvegarde();
    }
    
    
    /**
     * Lancement de l'agenda.<br>
     * <p>Lors du lancement de l'agenda, l'utilisateur doit saisir la t�che 
     * qu'il souhaite effectuer. Le programme ex�cutera alors les m�thodes li�es
     * � la t�che choisie.<br>
     * L'utilisateur peut choisir de quitter l'application, ce qui entraine la
     * fin du programme.<br>
     * Lorsque le programme s'arr�te, l'agenda sauvegarde la liste des 
     * rendez-vous {@code listeRdv}.</p>
     * @see jagenda.util.Saisie#menu()
     * @see listeRdv
     */
    public void lance() {
        listeRdv = listeRendezVous();
        // Contient l'identifiant num�rique de la t�che que veut effectuer 
        // l'utilisateur
        int tache;
        
        do {
            tache = saisie.menu();
            switch(tache) {
            case 1:
                this.creerRdv();
                break;
            case 2:
                // TODO Ajouter un rendez-vous r�current
                break;
            case 3:
                // TODO Modifier un rendez-vous
                break;
            case 4:
                // TODO Supprimer un rendez-vous
                break;
            case 5:
                // TODO Lister les rendez vous
                break;
            case 6:
                // TODO Configurer le logiciel
                config.sauveConfig();
                break;
            }
        } while(tache != 0);
        
        // L'utilisateur a d�cid� de quitter l'application
        System.out.println(Saisie.SEPARATEUR);
        System.out.println("Ne pas quitter le programme ! " +
                           "Sauvegarde en cours !");
        sauveRdv(listeRdv);
    }
    
    /**
     * Permet de cr�er un nouveau rendez-vous.
     */
    public void creerRdv() {
        //TODO V�rifier que la taille de listeRdv < Config.getNbMaximumRdv
        String libelle;
        String description;
        int nature;
        String date;
        String heureDebut;
        String heureFin;
        
        System.out.println("\n\n" + Saisie.SEPARATEUR);
        System.out.println("Ajouter un nouveau rendez-vous");
        System.out.println(Saisie.SEPARATEUR);
        
        libelle = saisie.lireLibelle();
        description = saisie.lireDescription();
        nature = saisie.lireNature();
        date = saisie.lireDate();
        heureDebut = saisie.lireHeure("d�but");
        heureFin = saisie.lireHeure("fin");
        
        RendezVous rendezvous = new RendezVous(libelle, description,
                                               nature, date,
                                               heureDebut, heureFin);
        
        // TODO Affichage du retour si Config.getConfirmRdv vaut TRUE
        // TODO Ajouter le rendezVous dans listeRdv
        System.out.println("\n\n" + rendezvous.getLibelle());
        System.out.println(rendezvous.getDescription());
        System.out.println(rendezvous.getNature());
        System.out.println(rendezvous.getHoraireDebut());
        System.out.println(rendezvous.getHoraireFin());
    }

    /**
     * Permet d'ins�rer un {@code RendezVous} dans un tableau de 
     * {@code RendezVous} si ce {@code RendezVous} n'est pas d�ja existant dans
     * le tableau de {@code RendezVous}.<br>
     * On suppose que le tableau de {@code RendezVous} est tri� et qu'il ne
     * contient aucun conflit. Le comportement de cette m�thode sur un tableau 
     * de {@code RendezVous} non tri� ou contenant des conflits est impossible �
     * d�terminer.<br>
     * Lors de l'insertion du {@code RendezVous}, on recherche la position qui 
     * lui convient � l'aide de la m�thode {@code compareTo(RendezVous)} de la 
     * classe {@code RendezVous}. Si le {@code RendezVous} � ins�rer n'est pas
     * pr�sent dans le tableau de {@code RendezVous}, alors on augmente d'un
     * slot la taille du tableau de {@code RendezVous}.
     * @param listeRdv Le tableau de {@code RendezVous} tri� o� sera ins�r� le 
     *                 rendez-vous {@code aInserer} mis en argument.
     * @param aInserer Le {@code RendezVous} � ins�r� dans {@code listeRdv}.
     */
    public static void insertRdv(RendezVous[] listeRdv, RendezVous aInserer) {

        /* On agrandit le tableau d'une case */
        int i = 0;
        
        /* On regarde o� doit �tre ins�r� le rendez-vous */
        for(i=0;i < listeRdv.length && listeRdv[i].compareTo(aInserer) < 0;i++);
        
        /* 
         * Si le rendez-vous n'est pas � ins�rer � la fin de la liste
         * et qu'il n'existe pas d�j� dans le tableau
         */
        if(!listeRdv[i].equals(aInserer)) {
            listeRdv = Arrays.copyOf(listeRdv, listeRdv.length+1);
            /* 
             * On d�place tout les rendez-vous plac�s apr�s le rendez-vous
             * � supprimer d'un indice vers la gauche.
             */
            for(int j = listeRdv.length-1; i < j; i--) {
                listeRdv[j] = listeRdv[j-1];
            }
            listeRdv[i] = aInserer;
        }
        
        /* On ins�re le rendez-vous dans le tableau */
        
    }
    
    /**
     * Permet de supprimer un {@code RendezVous} dans un tableau de 
     * {@code RendezVous}. Si le {@code RendezVous} � supprimer n'est pas
     * pr�sent dans le tableau de {@code RendezVous}, ce dernier restera
     * inchang�.<br>
     * On suppose que le tableau de {@code RendezVous} est tri� et qu'il ne
     * contient aucun conflit. Le comportement de cette m�thode sur un tableau 
     * de {@code RendezVous} non tri� ou contenant des conflits est impossible �
     * d�terminer.<br>
     * Lors de la suppression du {@code RendezVous}, on recherche sa position 
     * dans le tablea de {@code RendezVous} � l'aide de la m�thode 
     * {@code compareTo(RendezVous)} de la classe {@code RendezVous}.
     * @param listeRdv   Le tableau de {@code RendezVous} tri� o� sera supprim�
     *                   le rendez-vous {@code aSupprimer} mis en argument.
     * @param aSupprimer Le {@code RendezVous} � supprimer dans 
     *                   {@code listeRdv}.
     */
    public static void supprimeRdv(RendezVous[] listeRdv,
                                   RendezVous aSupprimer) {
        /* On agrandit le tableau d'une case */
        int i = 0;
        
        /* On cherche le rendez-vous � supprimer parmis la liste */
        for(i=0; i < listeRdv.length 
                 && !(listeRdv[i].compareTo(aSupprimer) < 0); i++);
        
        /* Si le rendez-vous a �t� trouv� */
        if(listeRdv[i].equals(aSupprimer) && i < listeRdv.length) {
            
            /* 
             * On d�place tout les rendez-vous plac�s apr�s le rendez-vous
             * � supprimer d'un indice vers la gauche.
             */
            for(; i < listeRdv.length-1; i++) {
                listeRdv[i] = listeRdv[i+1];
            }
            
            /*
             * On diminue la taille du tableau.
             */
            listeRdv = Arrays.copyOf(listeRdv, listeRdv.length-1);
        }
    }

    /**
     * Sauvegarde un tableau de {@code RendezVous} dans le fichier respectif.
     * Si le tableau est un tableau nul ou vide, rien ne sera effectu�. Une
     * ligne de ce fichier correspondra � un {@code RendezVous}. Les
     * informations sp�cifiques � ce {@code RendezVous} seront �cris de la
     * fa�on suivante : 
     * {@code (date)::(heureDebut)::(heureFin)::libell�::nature::description}
     * <br>Exemple : 6/5/2012::8h00::20h00::�lection::personnel::Absence de DSK
     * @param listeRdv Le tableau de {@code RendezVous} � sauvegarder.
     * @return {@code TRUE} si la sauvegarde a �t� effectu�.
     */
    @SuppressWarnings("null")
    public static boolean sauveRdv(RendezVous[] listeRdv) {
        boolean aRetourner = true;
        if(listeRdv != null || listeRdv.length != 0) {
            try {
                /* 
                 * Instanciation d'un objet de type Fichier repr�sentant le
                 * fichier de sauvegarde des rendezVous.
                 */
                Fichier sauvegarde = new Fichier(CHEMIN_SAUV, FICHIER_SAUV);
                
                sauvegarde.initEcriture(false);
                for (int i = 0; i < listeRdv.length; i++) {
                    sauvegarde.ecrire(
                        listeRdv[i].getHoraireDebut().getDate() + "::"+
                        listeRdv[i].getHoraireDebut().getHeure() +"::"+
                        listeRdv[i].getHoraireFin().getHeure()  + "::"+ 
                        listeRdv[i].getLibelle() + ":: " +
                        listeRdv[i].getNature() + "::" +
                        listeRdv[i].getDescription());
                }
                sauvegarde.fermeEcriture();
            } catch (FileNotFoundException fileEx) {
                // Nom ou chemin de fichier incorrect.
                aRetourner = false;
            } catch (IOException ioEx) {
                // Probl�me de droits d'acc�s ?
                aRetourner = false;
            }
        }
        return aRetourner;
    }
        
    /**
     * Retourne les {@code RendezVous} du tableau de {@code RendezVous} en 
     * conflit avec le {@code RendezVous} � comparer. Un {@code RendezVous} est
     * en conflit avec un autre {@code RendezVous} si son horaire de d�but
     * est inf�rieur � l'horaire de fin de l'autre {@code RendezVous} et que son
     * horaire de fin est sup�rieur � son horaire de d�but.<br>
     * @param listeRdv
     * @param aComparer
     * @return Les rendez-vous en conflits
     */
    public static RendezVous[] conflitRdv(RendezVous[] listeRdv,
                                          RendezVous aComparer) {
        
        RendezVous[] aRenvoyer = new RendezVous[0];
            
        for(int i=0; i < listeRdv.length; i++) {
            
            if (aComparer.getHoraireFin().compareTo(listeRdv[i].getHoraireDebut()) > 0
                && aComparer.getHoraireDebut().compareTo(listeRdv[i].getHoraireFin()) < 0) {

                aRenvoyer = Arrays.copyOf(aRenvoyer, aRenvoyer.length+1);
                aRenvoyer[aRenvoyer.length-1] = listeRdv[i];
            }
        }
        return aRenvoyer;
    }
    
    /**
     * Lecture des {@code RendezVous} enregistr�s dans le fichier de sauvegarde.
     * Ce fichier se trouve dans le repertoire {@code CHEMIN_SAUV} portant pour
     * nom {@code FICHIER_SAUV}.<br>
     * Chaque ligne de ce fichier repr�sente un {@code RendezVous} enregistr� au 
     * format {@code STRUCTURE_DONNEES}. Dans le cas contraire, on dit que la 
     * donn�e est incorrecte.<br><br>
     * <i>Exemple :<br>
     * {@code jj/mm/yyyy::hh:mm::HH:MM::(libelle)::(nature)::(description)}</i>
     * <br>o� :
     * <ul><li>{@code jj} correspond au jour de la date,</li> 
     *     <li>{@code mm} correspond au mois de la date,</li>
     *     <li>{@code yyyy} correspond � l'ann�e de la date,</li> 
     *     <li>{@code hh:mm} correspond � l'heure de d�but,</li>
     *     <li>{@code HH:MM} correspond � l'heure de fin.</li></ul>
     * Chaque donn�e est s�par�e par la suite de caract�res {@code ::}.
     *  
     * @return Liste des rendez-vous inscrits dans le fichier de sauvegarde.
     */
    public static RendezVous[] listeRendezVous() {

        // Tableau qui contiendra la liste des rendez-vous � retourner.
        RendezVous[] listeARetourner = null;

        try {
            String ligne;    // Contient la ligne � l'index i
            int nbErreur = 0;// Contient le nombre de lignes de donn�es erron�es
            Matcher donnees;

            Fichier fichierSauv = new Fichier(CHEMIN_SAUV, FICHIER_SAUV);
            fichierSauv.initLecture();

            listeARetourner = new RendezVous[0];

            int i = 0;      // Indice de la ligne lue
            while((ligne = fichierSauv.lireLigne()) != null) {
                donnees = STRUCTURE_DONNEES.matcher(ligne);
                if((donnees.matches())) {
                    listeARetourner = Arrays.copyOf(listeARetourner,
                            listeARetourner.length+1);
                    listeARetourner[i] = new RendezVous(donnees);
                } else {
                    // La ligne est incorrecte
                    nbErreur++;
                }
                i++;
            }
            if(nbErreur > 0) {
                System.err.println(nbErreur == 1 ? 
                        nbErreur + " rendez-vous n'a pas pu �tre charg�." :
                            nbErreur + " rendez-vous n'ont pas pu �tre charg�.");
            }
        } catch (FileNotFoundException fileEx) {
            /*
             * 1. C'est peut-�tre la premi�re fois que le logiciel est lanc�.
             * 2. Le fichier de sauvegarde a �t� supprim�.
             */
            System.err.println("Le fichier de sauvegarde n'existe pas. " +
                    fileEx.getMessage());
        } catch (IOException ioEx) {
            /*
             * 1. Nom ou Chemin de fichier incorrect.
             * 2. Probl�me de droit d'acc�s ?
             */
            System.err.println("Impossible de lire le fichier. " + 
                    ioEx.getMessage());
        }
        return listeARetourner;
    }
}
