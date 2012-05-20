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
 * Classe d'outils permettant la {@code Saisie} sur l'entrée standard de données.
 * Ces données peuvent être :
 * <ul>
 * </ul>
 * 
 * @author  Jason BOURLARD
 * @author  David PELISSIER
 * @version 0.3
 */
public class Saisie {

    /** Séparateur d'affichage. */
    public static final String SEPARATEUR =
        "*********************************************************************";

    /**
     * Objet {@code Scanner} permettant les traitements d'entrées/sorties sur
     * entrée / sortie standard.
     */
    private Scanner clavier = new Scanner(System.in);


    /**
     * Permet la lecture d'une tâche du menu, entrée par l'utilisateur, 
     * sur l'entrée standard.<br>
     * Tant que la donnée lue sur l'entrée standard n'est pas une tâche
     * correcte, on demande à l'utilisateur d'effectuer une nouvelle saisie.<br>
     * Pour que la tâche soit jugée correcte, elle doit respecter les 
     * conditions suivantes :
     * <ul><li>Comrpise entre 0 et 6</li></ul>
     * L'arrêt de la saisie s'effectue lorsque l'utilisateur appuie sur 
     * {@code ENTREE}.<br>
     * 
     * @return Tâche saisie sur l'entrée standard.
     */
    public int menu() {
        // Contiendra la tâche que veut effectuer l'utilisateur.
        int tache = -1;

        // Si cette valeur vaut {@code true}, alors l'utilisateur a commit une 
        // erreur de saisie.
        boolean erreur = false;

        do {
            System.out.println(Saisie.SEPARATEUR);
            System.out.println("1 - Ajouter un nouveau rendez-vous ponctuel.");
            System.out.println("2 - Ajouter un nouveau rendez-vous récurrent.");
            System.out.println("3 - Modifier un rendez-vous.");
            System.out.println("4 - Supprimer un rendez-vous.");
            System.out.println("5 - Lister les rendez-vous.");
            System.out.println("6 - Lister les créneaux disponibles.");            
            System.out.println("7 - Configurer le logiciel.");
            System.out.println("0 - Quitter.");
            System.out.println(Saisie.SEPARATEUR);
            System.out.print("Saisissez le numéro de la tâche à effectuer : ");
            try {
                tache = this.clavier.nextInt();
                erreur = !(0 <= tache && tache <= 8);
                if (erreur) {
                    System.err.println(tache + " n'est pas une tâche valide.");
                }
                this.clavier.nextLine();
            } catch (InputMismatchException inEx) {
                System.err.println(this.clavier.nextLine() + 
                                   " n'est pas une tâche valide.");
            }

        } while (erreur);
        return tache;
    }


    /**
     * Permet la lecture d'un libellé, entré par l'utilisateur, sur l'entrée
     * standard.<br>
     * Tant que la donnée lue sur l'entrée standard n'est pas un libellé 
     * correct, on demande à l'utilisateur d'effectuer une nouvelle saisie.<br>
     * Pour que le libellé soit jugé correct, il doit respecter les conditions
     * suivantes :
     * <ul><li>Doit être non nul</li>
     *     <li>Longueur d'au plus 8 caractères</li>
     *     <li>Ne doit pas contenir d'espaces</li></ul>
     * L'arrêt de la saisie s'effectue lorsque l'utilisateur appuie sur 
     * {@code ENTREE}.<br>
     * 
     * @return Libellé saisi sur l'entrée standard.
     * @see    #estLibelleCorrect
     */
    public String lireLibelle() {
        // Contiendra le libellé que l'utilisateur a saisi.
        String libelle;
        
        /*
         *  Contiendra le numéro de l'erreur.
         *      0 si aucune erreur n'est détecté
         *      1 si le libellé est vide ou nul
         *      2 si la taille du libellé est trop grande
         *      3 si le libellé contient des espaces
         */
        int erreur = -1;
        do {
            
            System.out.println("Veuillez saisir le libellé de votre RDV " +
                               "(au plus 8 caractères) : ");
            libelle = this.clavier.nextLine();
            erreur = RendezVous.estLibelleCorrect(libelle);
            switch(erreur) {
            case 1:
                System.err.println("Le libellé saisi est vide.");
                break;
            case 2:
                System.err.println("Le libellé saisi est trop long.");
                break;
            case 3:
                System.err.println("Le libellé saisi contient des espaces.");
                break;
            }
        } while (erreur != 0);
        return libelle;        
    }


    /**
     * Permet la lecture d'une description, entrée par l'utilisateur, sur 
     * l'entrée standard.<br>
     * Tant que la donnée lue sur l'entrée standard n'est pas une description
     * correcte, on demande à l'utilisateur d'effectuer une nouvelle saisie.<br>
     * Pour que la description soit jugée correcte, elle doit respecter les 
     * conditions suivantes :
     * <ul><li>Longueur d'au plus 5 lignes soit 400 caractères</li></ul>
     * L'arrêt de la saisie s'effectue lorsque l'utilisateur appuie sur 
     * {@code ENTREE}.<br>
     * 
     * @return Description saisie sur l'entrée standard.
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
     * Permet la lecture d'une nature de rendez-vous, entrée par l'utilisateur, 
     * sur l'entrée standard.<br>
     * Tant que la donnée lue sur l'entrée standard n'est pas une nature
     * correcte, on demande à l'utilisateur d'effectuer une nouvelle saisie.<br>
     * Pour que la description soit jugée correcte, elle doit respecter les 
     * conditions suivantes :
     * <ul><li>Comprise entre 1 et 2 ("Personnel" ou "Professionnel")</li></ul>
     * L'arrêt de la saisie s'effectue lorsque l'utilisateur appuie sur 
     * {@code ENTREE}.<br>
     * 
     * @return Numéro de la nature saisi sur l'entrée standard.
     * 
     */
    public int lireNature() {
        // Contiendra la nature que l'utilisateur a saisie.
        int nature = -1;
        
        // Si cette valeur vaut {@code true}, alors l'utilisateur a commit une 
        // erreur de saisie.
        boolean erreur = false;
        do {
            System.out.println("Veuillez saisir le numéro de la nature de " +
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
     * Permet la lecture d'une date de rendez-vous, entrée par l'utilisateur, 
     * sur l'entrée standard.<br>
     * Tant que la donnée lue sur l'entrée standard n'est pas une date
     * correcte, on demande à l'utilisateur d'effectuer une nouvelle saisie.<br>
     * Pour que la date soit jugée correcte, elle doit respecter les 
     * conditions suivantes :
     * <ul><li>Respecte le format de configuration</li>
     *     <li>Date valide du calendrier grégorien</li>
     *     <li>Comprise dans l'intervalle d'années défini dans la 
     *         configuration</li></ul>
     * L'arrêt de la saisie s'effectue lorsque l'utilisateur appuie sur 
     * {@code ENTREE}.<br>
     * 
     * @return Date saisie sur l'entrée standard.
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
     * Permet la lecture d'une heure du rendez-vous, entrée par l'utilisateur, 
     * sur l'entrée standard.<br>
     * Tant que la donnée lue sur l'entrée standard n'est pas une heure
     * correcte, on demande à l'utilisateur d'effectuer une nouvelle saisie.<br>
     * Pour que l'heure soit jugée correcte, elle doit respecter les 
     * conditions suivantes :
     * <ul><li>Heure valide</li></ul>
     * L'arrêt de la saisie s'effectue lorsque l'utilisateur appuie sur 
     * {@code ENTREE}.<br>
     * @param moment Défini si l'heure saisie est l'heure de début ou l'heure de
     *               fin du rendez-vous.
     * @return Heure saisie sur l'entrée standard.
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
                System.err.println("L'heure de début saisie est invalide.");
            }
        } while (erreur);
        return heure;            
    } 
}