/*
 * Fichier :	Saisie.java
 * Package :	jagenda.util
 * Projet jAgenda - Info 1 2011-2012
 *
 */
package jagenda.util;

import jagenda.Horaire;
import jagenda.RendezVous;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe d'outils permettant la {@code Saisie} sur l'entr�e standard de donn�es.
 * Ces donn�es peuvent �tre :
 * <ul>
 * </ul>
 * 
 * @author  Jason BOURLARD
 * @author  David PELISSIER
 * @version 0.3
 */
public class Saisie {

    /** S�parateur d'affichage. */
    public static final String SEPARATEUR =
        "*********************************************************************";

    /**
     * Objet {@code Scanner} permettant les traitements d'entr�es/sorties sur
     * entr�e / sortie standard.
     */
    private Scanner clavier = new Scanner(System.in);


    /**
     * Permet la lecture d'une t�che du menu, entr�e par l'utilisateur, 
     * sur l'entr�e standard.<br>
     * Tant que la donn�e lue sur l'entr�e standard n'est pas une t�che
     * correcte, on demande � l'utilisateur d'effectuer une nouvelle saisie.<br>
     * Pour que la t�che soit jug�e correcte, elle doit respecter les 
     * conditions suivantes :
     * <ul><li>Comrpise entre 0 et 6</li></ul>
     * L'arr�t de la saisie s'effectue lorsque l'utilisateur appuie sur 
     * {@code ENTREE}.<br>
     * 
     * @return T�che saisie sur l'entr�e standard.
     */
    public int menu() {
        // Contiendra la t�che que veut effectuer l'utilisateur.
        int tache = -1;

        // Si cette valeur vaut {@code true}, alors l'utilisateur a commit une 
        // erreur de saisie.
        boolean erreur = false;

        do {
            System.out.println(Saisie.SEPARATEUR);
            System.out.println("1 - Ajouter un nouveau rendez-vous ponctuel.");
            System.out.println("2 - Ajouter un nouveau rendez-vous r�current.");
            System.out.println("3 - Modifier un rendez-vous.");
            System.out.println("4 - Supprimer un rendez-vous.");
            System.out.println("5 - Lister les rendez-vous.");
            System.out.println("6 - Lister les cr�neaux disponibles.");            
            System.out.println("7 - Configurer le logiciel.");
            System.out.println("0 - Quitter.");
            System.out.println(Saisie.SEPARATEUR);
            System.out.print("Saisissez le num�ro de la t�che � effectuer : ");
            try {
                tache = this.clavier.nextInt();
                erreur = !(0 <= tache && tache <= 8);
                if (erreur) {
                    System.err.println(tache + " n'est pas une t�che valide.");
                }
                this.clavier.nextLine();
            } catch (InputMismatchException inEx) {
                System.err.println(this.clavier.nextLine() + 
                                   " n'est pas une t�che valide.");
            }

        } while (erreur);
        return tache;
    }


    /**
     * Permet la lecture d'un libell�, entr� par l'utilisateur, sur l'entr�e
     * standard.<br>
     * Tant que la donn�e lue sur l'entr�e standard n'est pas un libell� 
     * correct, on demande � l'utilisateur d'effectuer une nouvelle saisie.<br>
     * Pour que le libell� soit jug� correct, il doit respecter les conditions
     * suivantes :
     * <ul><li>Doit �tre non nul</li>
     *     <li>Longueur d'au plus 8 caract�res</li>
     *     <li>Ne doit pas contenir d'espaces</li></ul>
     * L'arr�t de la saisie s'effectue lorsque l'utilisateur appuie sur 
     * {@code ENTREE}.<br>
     * 
     * @return Libell� saisi sur l'entr�e standard.
     * @see    #estLibelleCorrect
     */
    public String lireLibelle() {
        // Contiendra le libell� que l'utilisateur a saisi.
        String libelle;
        
        /*
         *  Contiendra le num�ro de l'erreur.
         *      0 si aucune erreur n'est d�tect�
         *      1 si le libell� est vide ou nul
         *      2 si la taille du libell� est trop grande
         *      3 si le libell� contient des espaces
         */
        int erreur = -1;
        do {
            
            System.out.println("Veuillez saisir le libell� de votre RDV " +
                               "(au plus 8 caract�res) : ");
            libelle = this.clavier.nextLine();
            erreur = RendezVous.estLibelleCorrect(libelle);
            switch(erreur) {
            case 1:
                System.err.println("Le libell� saisi est vide.");
                break;
            case 2:
                System.err.println("Le libell� saisi est trop long.");
                break;
            case 3:
                System.err.println("Le libell� saisi contient des espaces.");
                break;
            }
        } while (erreur != 0);
        return libelle;        
    }


    /**
     * Permet la lecture d'une description, entr�e par l'utilisateur, sur 
     * l'entr�e standard.<br>
     * Tant que la donn�e lue sur l'entr�e standard n'est pas une description
     * correcte, on demande � l'utilisateur d'effectuer une nouvelle saisie.<br>
     * Pour que la description soit jug�e correcte, elle doit respecter les 
     * conditions suivantes :
     * <ul><li>Longueur d'au plus 5 lignes soit 400 caract�res</li></ul>
     * L'arr�t de la saisie s'effectue lorsque l'utilisateur appuie sur 
     * {@code ENTREE}.<br>
     * 
     * @return Description saisie sur l'entr�e standard.
     * @see    #estDescriptionCorrecte
     */
    public String lireDescription() {
        // Contiendra la description que l'utilisateur a saisie.
        String description;
        
        // Si cette valeur vaut {@code true}, alors l'utilisateur a commit une 
        // erreur de saisie.
        boolean erreur = false;
        do {
            System.out.println("Veuillez saisir la description de votre RDV " +
                               "(au plus 5 lignes) : ");
            description = this.clavier.nextLine();
            erreur = !RendezVous.estDescriptionCorrecte(description);
            if (erreur) {
                System.err.println("Le description saisie est trop longue.");
            }
        } while (erreur);
        return description;        
    }        


    /**
     * Permet la lecture d'une nature de rendez-vous, entr�e par l'utilisateur, 
     * sur l'entr�e standard.<br>
     * Tant que la donn�e lue sur l'entr�e standard n'est pas une nature
     * correcte, on demande � l'utilisateur d'effectuer une nouvelle saisie.<br>
     * Pour que la description soit jug�e correcte, elle doit respecter les 
     * conditions suivantes :
     * <ul><li>Comprise entre 1 et 2 ("Personnel" ou "Professionnel")</li></ul>
     * L'arr�t de la saisie s'effectue lorsque l'utilisateur appuie sur 
     * {@code ENTREE}.<br>
     * 
     * @return Num�ro de la nature saisi sur l'entr�e standard.
     * 
     */
    public int lireNature() {
        // Contiendra la nature que l'utilisateur a saisie.
        int nature = -1;
        
        // Si cette valeur vaut {@code true}, alors l'utilisateur a commit une 
        // erreur de saisie.
        boolean erreur = false;
        do {
            System.out.println("Veuillez saisir le num�ro de la nature de " +
                               "votre RDV : ");
            System.out.println("1 - Personnel");
            System.out.println("2 - Professionnel");
            try {
                nature = this.clavier.nextInt();
                erreur = !(1 <= nature && nature <= 2);
                if (erreur) {
                    System.err.println("La nature saisie n'existe pas.");
                }
            } catch (InputMismatchException inEx) {
                erreur = true;
                System.err.println("La nature saisie n'existe pas.");
            } finally {
                this.clavier.nextLine();    // On vide le tampon
            }
        } while (erreur);
        return nature;        
    } 


    /**
     * Permet la lecture d'une date de rendez-vous, entr�e par l'utilisateur, 
     * sur l'entr�e standard.<br>
     * Tant que la donn�e lue sur l'entr�e standard n'est pas une date
     * correcte, on demande � l'utilisateur d'effectuer une nouvelle saisie.<br>
     * Pour que la date soit jug�e correcte, elle doit respecter les 
     * conditions suivantes :
     * <ul><li>Respecte le format de configuration</li>
     *     <li>Date valide du calendrier gr�gorien</li>
     *     <li>Comprise dans l'intervalle d'ann�es d�fini dans la 
     *         configuration</li></ul>
     * L'arr�t de la saisie s'effectue lorsque l'utilisateur appuie sur 
     * {@code ENTREE}.<br>
     * 
     * @return Date saisie sur l'entr�e standard.
     */
    public String lireDate() {
        // Contiendra la date que l'utilisateur a saisie.
        String date;
        
        // Si cette valeur vaut {@code true}, alors l'utilisateur a commit une 
        // erreur de saisie.
        boolean erreur = false;
        do {
            System.out.println("Veuillez saisir la date de votre RDV : ");
            date = this.clavier.nextLine();
            erreur = !(Horaire.estDateValide(date));
            System.out.println(erreur);
            if (erreur) {
                System.err.println("Le date saisie est invalide.");
            }
        } while (erreur);
        return date;            
    } 


    /**
     * Permet la lecture d'une heure du rendez-vous, entr�e par l'utilisateur, 
     * sur l'entr�e standard.<br>
     * Tant que la donn�e lue sur l'entr�e standard n'est pas une heure
     * correcte, on demande � l'utilisateur d'effectuer une nouvelle saisie.<br>
     * Pour que l'heure soit jug�e correcte, elle doit respecter les 
     * conditions suivantes :
     * <ul><li>Heure valide</li></ul>
     * L'arr�t de la saisie s'effectue lorsque l'utilisateur appuie sur 
     * {@code ENTREE}.<br>
     * @param moment D�fini si l'heure saisie est l'heure de d�but ou l'heure de
     *               fin du rendez-vous.
     * @return Heure saisie sur l'entr�e standard.
     * 
     */
    public String lireHeure(String moment) {
        // Contiendra l'heure que l'utilisateur a saisie.
        String heure;    
        
        // Si cette valeur vaut {@code true}, alors l'utilisateur a commit une 
        // erreur de saisie.
        boolean erreur = false;
        do {
            System.out.println("Veuillez saisir l'heure de "+ moment +" de " +
                               "votre RDV (ex : 14h25) : ");
            heure = this.clavier.nextLine();
            erreur = !(Horaire.estHeureValide(heure));
            if (erreur) {
                System.err.println("L'heure de d�but saisie est invalide.");
            }
        } while (erreur);
        return heure;            
    } 
}