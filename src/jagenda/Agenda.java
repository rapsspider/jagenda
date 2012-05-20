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
 * <p>La classe <code>Agenda</code> représente un agenda, permettant à un 
 * utilisateur de gérer des rendez-vous, les modifier, les supprimer.</p>
 * <br>
 * <p>Les rendez-vous peuvent être définis de façon ponctuelle, cependant, il
 * est possible que l'utilisateur souhaite fixer une activité régulière dans 
 * l'agenda comme par exemple un cours de piano tous les jeudis de 17h à 18h30 
 * donc les rendez-vous pourront aussi être récurrents.</p>
 * <br>
 * <p>Les dates de rendez-vous peuvent être saisies sous deux formats 
 * différents, à savoir, {@code jj/mm/aaaa} ou encore {@code jour_semaine} et 
 * {@code numero_semaine}.</p>
 * <br>
 * <p>Un enregistrement des rendez-vous est effectué dans un fichier de données.
 * De plus, le logiciel implémente une sauvegarde de configuration permettant à 
 * l'utilisateur de spécifier vos préférences concernant les formats de date à 
 * utiliser, l'année gérée par l'agenda,...</p>
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
     * Expression régulière permettant de lire les données.
     * <i>Exemple : jj/mm/aaaa::hh:mm::hh:mm::libelle:nature::description</i>
     */
    private static final Pattern STRUCTURE_DONNEES = 
            Pattern.compile("(\\d{1,2})/(\\d{1,2})/(\\d{4})::" + 
                    "(\\d{1,2})(h|H|:)(\\d{1,2})::(\\d{1,2})(h|H|:)(\\d{1,2})" +
                    "::(.+)::(.+)::(.+)");
    
    /** Liste des rendez vous de l'agenda. */
    private RendezVous[] listeRdv;

    /** Objet permettant les saisies sur l'entrée/sortie standard */
    public Saisie saisie;

    /** Configuration qui va contenir les préférences de l'utilisateur. */
    public Config config;

    /**
     * Constructeur par defaut d'un agenda.<br>
     * <p>Initialise sa configuration et charge les données du fichier de 
     * configuration si celui-ci existe, initialise sa liste de rendez-vous 
     * ainsi qu'un objet de type <code>Saisie</code> permettant la saisie de 
     * données sur l'entrée/sortie standard.</p>
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
     * @return Année de début de la période de temps gérée par l'agenda.<br>
     * @see jagenda.util.Config#anneeDebut
     */
    public int getAnneeDebut() {
        return this.config.getAnneeDebut();
    }

    /**
     * Accesseur pour le champ <code>anneeFin</code> dans le fichier de
     * configuration.
     * @return Année de fin de la période de temps gérée par l'agenda.<br>
     * @see #anneeFin
     */
    public int getAnneeFin() {
        return this.config.getAnneeFin();    }
    
    /**
     * Accesseur pour le champ <code>confirmRdv</code> dans le fichier de
     * configuration.
     * @return <code>1</code> si l'option est activée.<br>
     *         <code>0</code> si l'option est désactivée.
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
     * @return Largeur de la console de commande en nombre de caractères.
     * @see #consoleLargeur
     */
    public int getConsoleLargeur() {
        return this.config.getConsoleLargeur();
    }

    /**
     * Accesseur pour le champ <code>formatDate</code> dans le fichier de
     * configuration.
     * @return Format désiré pour la saisie de dates, à savoir,<br>
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
     * @return Nombre maximum de rendez-vous pouvant être gérés par le logiciel 
     *         jAgenda.
     * @see #nbMaximumRdv
     */
    public int getNbMaximumRdv() {
        return this.config.getNbMaximumRdv();
    }

    /**
     * Accesseur pour le champ <code>typeSauvegarde</code> dans le fichier de
     * configuration.
     * @return Préférence de sauvegarde des rendez-vous, à savoir,<br> 
     *         soit <code>1</code> pour un <i>enregistrement à chaque ajout ou 
     *         modification d'un rendez-vous</i>,<br>
     *         soit <code>0</code> pour un <i>enregistrement à la fermeture du 
     *         programme</i>.
     * @see #typeSauvegarde
     */
    public int getTypeSauvegarde() {
        return this.config.getTypeSauvegarde();
    }
    
    
    /**
     * Lancement de l'agenda.<br>
     * <p>Lors du lancement de l'agenda, l'utilisateur doit saisir la tâche 
     * qu'il souhaite effectuer. Le programme exécutera alors les méthodes liées
     * à la tâche choisie.<br>
     * L'utilisateur peut choisir de quitter l'application, ce qui entraine la
     * fin du programme.<br>
     * Lorsque le programme s'arrête, l'agenda sauvegarde la liste des 
     * rendez-vous {@code listeRdv}.</p>
     * @see jagenda.util.Saisie#menu()
     * @see listeRdv
     */
    public void lance() {
        listeRdv = listeRendezVous();
        // Contient l'identifiant numérique de la tâche que veut effectuer 
        // l'utilisateur
        int tache;
        
        do {
            tache = saisie.menu();
            switch(tache) {
            case 1:
                this.creerRdv();
                break;
            case 2:
                // TODO Ajouter un rendez-vous récurrent
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
        
        // L'utilisateur a décidé de quitter l'application
        System.out.println(Saisie.SEPARATEUR);
        System.out.println("Ne pas quitter le programme ! " +
                           "Sauvegarde en cours !");
        sauveRdv(listeRdv);
    }
    
    /**
     * Permet de créer un nouveau rendez-vous.
     */
    public void creerRdv() {
        //TODO Vérifier que la taille de listeRdv < Config.getNbMaximumRdv
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
        heureDebut = saisie.lireHeure("début");
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
     * Permet d'insérer un {@code RendezVous} dans un tableau de 
     * {@code RendezVous} si ce {@code RendezVous} n'est pas déja existant dans
     * le tableau de {@code RendezVous}.<br>
     * On suppose que le tableau de {@code RendezVous} est trié et qu'il ne
     * contient aucun conflit. Le comportement de cette méthode sur un tableau 
     * de {@code RendezVous} non trié ou contenant des conflits est impossible à
     * déterminer.<br>
     * Lors de l'insertion du {@code RendezVous}, on recherche la position qui 
     * lui convient à l'aide de la méthode {@code compareTo(RendezVous)} de la 
     * classe {@code RendezVous}. Si le {@code RendezVous} à insérer n'est pas
     * présent dans le tableau de {@code RendezVous}, alors on augmente d'un
     * slot la taille du tableau de {@code RendezVous}.
     * @param listeRdv Le tableau de {@code RendezVous} trié où sera inséré le 
     *                 rendez-vous {@code aInserer} mis en argument.
     * @param aInserer Le {@code RendezVous} à inséré dans {@code listeRdv}.
     */
    public static void insertRdv(RendezVous[] listeRdv, RendezVous aInserer) {

        /* On agrandit le tableau d'une case */
        int i = 0;
        
        /* On regarde où doit être inséré le rendez-vous */
        for(i=0;i < listeRdv.length && listeRdv[i].compareTo(aInserer) < 0;i++);
        
        /* 
         * Si le rendez-vous n'est pas à insérer à la fin de la liste
         * et qu'il n'existe pas déjà dans le tableau
         */
        if(!listeRdv[i].equals(aInserer)) {
            listeRdv = Arrays.copyOf(listeRdv, listeRdv.length+1);
            /* 
             * On déplace tout les rendez-vous placés après le rendez-vous
             * à supprimer d'un indice vers la gauche.
             */
            for(int j = listeRdv.length-1; i < j; i--) {
                listeRdv[j] = listeRdv[j-1];
            }
            listeRdv[i] = aInserer;
        }
        
        /* On insère le rendez-vous dans le tableau */
        
    }
    
    /**
     * Permet de supprimer un {@code RendezVous} dans un tableau de 
     * {@code RendezVous}. Si le {@code RendezVous} à supprimer n'est pas
     * présent dans le tableau de {@code RendezVous}, ce dernier restera
     * inchangé.<br>
     * On suppose que le tableau de {@code RendezVous} est trié et qu'il ne
     * contient aucun conflit. Le comportement de cette méthode sur un tableau 
     * de {@code RendezVous} non trié ou contenant des conflits est impossible à
     * déterminer.<br>
     * Lors de la suppression du {@code RendezVous}, on recherche sa position 
     * dans le tablea de {@code RendezVous} à l'aide de la méthode 
     * {@code compareTo(RendezVous)} de la classe {@code RendezVous}.
     * @param listeRdv   Le tableau de {@code RendezVous} trié où sera supprimé
     *                   le rendez-vous {@code aSupprimer} mis en argument.
     * @param aSupprimer Le {@code RendezVous} à supprimer dans 
     *                   {@code listeRdv}.
     */
    public static void supprimeRdv(RendezVous[] listeRdv,
                                   RendezVous aSupprimer) {
        /* On agrandit le tableau d'une case */
        int i = 0;
        
        /* On cherche le rendez-vous à supprimer parmis la liste */
        for(i=0; i < listeRdv.length 
                 && !(listeRdv[i].compareTo(aSupprimer) < 0); i++);
        
        /* Si le rendez-vous a été trouvé */
        if(listeRdv[i].equals(aSupprimer) && i < listeRdv.length) {
            
            /* 
             * On déplace tout les rendez-vous placés après le rendez-vous
             * à supprimer d'un indice vers la gauche.
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
     * Si le tableau est un tableau nul ou vide, rien ne sera effectué. Une
     * ligne de ce fichier correspondra à un {@code RendezVous}. Les
     * informations spécifiques à ce {@code RendezVous} seront écris de la
     * façon suivante : 
     * {@code (date)::(heureDebut)::(heureFin)::libellé::nature::description}
     * <br>Exemple : 6/5/2012::8h00::20h00::Élection::personnel::Absence de DSK
     * @param listeRdv Le tableau de {@code RendezVous} à sauvegarder.
     * @return {@code TRUE} si la sauvegarde a été effectué.
     */
    @SuppressWarnings("null")
    public static boolean sauveRdv(RendezVous[] listeRdv) {
        boolean aRetourner = true;
        if(listeRdv != null || listeRdv.length != 0) {
            try {
                /* 
                 * Instanciation d'un objet de type Fichier représentant le
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
                // Problème de droits d'accès ?
                aRetourner = false;
            }
        }
        return aRetourner;
    }
        
    /**
     * Retourne les {@code RendezVous} du tableau de {@code RendezVous} en 
     * conflit avec le {@code RendezVous} à comparer. Un {@code RendezVous} est
     * en conflit avec un autre {@code RendezVous} si son horaire de début
     * est inférieur à l'horaire de fin de l'autre {@code RendezVous} et que son
     * horaire de fin est supérieur à son horaire de début.<br>
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
     * Lecture des {@code RendezVous} enregistrés dans le fichier de sauvegarde.
     * Ce fichier se trouve dans le repertoire {@code CHEMIN_SAUV} portant pour
     * nom {@code FICHIER_SAUV}.<br>
     * Chaque ligne de ce fichier représente un {@code RendezVous} enregistré au 
     * format {@code STRUCTURE_DONNEES}. Dans le cas contraire, on dit que la 
     * donnée est incorrecte.<br><br>
     * <i>Exemple :<br>
     * {@code jj/mm/yyyy::hh:mm::HH:MM::(libelle)::(nature)::(description)}</i>
     * <br>où :
     * <ul><li>{@code jj} correspond au jour de la date,</li> 
     *     <li>{@code mm} correspond au mois de la date,</li>
     *     <li>{@code yyyy} correspond à l'année de la date,</li> 
     *     <li>{@code hh:mm} correspond à l'heure de début,</li>
     *     <li>{@code HH:MM} correspond à l'heure de fin.</li></ul>
     * Chaque donnée est séparée par la suite de caractères {@code ::}.
     *  
     * @return Liste des rendez-vous inscrits dans le fichier de sauvegarde.
     */
    public static RendezVous[] listeRendezVous() {

        // Tableau qui contiendra la liste des rendez-vous à retourner.
        RendezVous[] listeARetourner = null;

        try {
            String ligne;    // Contient la ligne à l'index i
            int nbErreur = 0;// Contient le nombre de lignes de données erronées
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
                        nbErreur + " rendez-vous n'a pas pu être chargé." :
                            nbErreur + " rendez-vous n'ont pas pu être chargé.");
            }
        } catch (FileNotFoundException fileEx) {
            /*
             * 1. C'est peut-être la première fois que le logiciel est lancé.
             * 2. Le fichier de sauvegarde a été supprimé.
             */
            System.err.println("Le fichier de sauvegarde n'existe pas. " +
                    fileEx.getMessage());
        } catch (IOException ioEx) {
            /*
             * 1. Nom ou Chemin de fichier incorrect.
             * 2. Problème de droit d'accès ?
             */
            System.err.println("Impossible de lire le fichier. " + 
                    ioEx.getMessage());
        }
        return listeARetourner;
    }
}
