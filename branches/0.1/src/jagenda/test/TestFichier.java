/*
 * Fichier :    TestFichier.java
 * Package :    jagenda.test
 * Projet jAgenda - Info 1 2011-2012
 *
 * $Revision$
 * $Date$
 * $HeadURL$
 */

package jagenda.test;

import jagenda.util.Fichier;
import java.io.IOException;

/**
 * Classe <code>Fichier</code> contient les méthodes utiles à la
 * gestion de fichiers texte (.txt) :
 * <ul><li>Lecture</li>
 *     <li>Ecriture</li></ul>
 * Les interactions sont rendues possibles par l'intermédiaire d'un
 * flux d'entrée et flux de sortie du contenu du fichier texte (.txt)
 * 
 * @author Jason BOURLARD
 * @author David PELISSIER
 */
public class TestFichier {
    public static String nomdufichier = "monfichierdetest";
    public static String[] message = { "Ligne 0",
                                       "Ligne 1",
                                       "Ligne 2",
                                       "Ligne 3",
                                       "Ligne 4",
                                       "Ligne 5",
                                       "Ligne 6",
                                       "Ligne 7",
                                       "Ligne 8",
                                       "Ligne 9" };
    
    public static Fichier fichier;
    
    
    public static void main(String[] args) {
    	try {
    		// On écrase le fichier précédant
    		fichier = new Fichier(nomdufichier, true);
    	} catch (IOException e) {
        }  
    	// On inscrit toutes les lignes
        for(int k=0; k < message.length; k++) {
            fichier.ecrire(message[k]);
        }
        
        // On execute le test
        TestModifLigne();
    }
    
    /**
     * Méthode testant le remplacement d'une ligne
     * par une autre.
     */
    public static void TestModifLigne() {
        fichier.remplacerLigne("NouvelleLigne",5);
    }
    
    /**
     * Méthode testant l'ajout d'une ligne à un
     * numéro de ligne donnée.
     */
    public static void TestAjoutLigne() {
        fichier.ecrire("NouvelleLigne",5);
    }
    
    /**
     * Méthode testant la supression d'une ligne
     * à un numéro de ligne donnée.
     */
    public static void TestSuppressionLigne() {        
        fichier.supprimerLigne(5);
    }
    
    /**
     * Méthode testant la création d'un <code>Fichier</code>.
     */
    public static void TestPrincipaux() {
        String[] chemin = { null,
                        "/",
                        "a",
                        "/a",
                        "b/",
                        "a/c",
                        "b/c/",
                        "/c/a/"
                        };
        
        String[] file = { //null,
                      "fichier"
                      //"fichier.a",
                      //"/fichier.b",
                        //"fichier/fichier.c" 
                      };
        
        String[] message = { null,
                          "Ligne 2"
                          //"Ligne 3\nLigne 3.2?",
                          //"Ligne 4\tLigne 4.1?\rLigne4.2" 
                          };
        
        Fichier fichier;
        
        for(int i=0; i < chemin.length; i++) {
            for(int j=0; j < file.length; j++) {
                try {
                     System.out.print("\nCode ( " + i + "," + j + ") ");
                    fichier = new Fichier(chemin[i], file[j]);
                    System.out.println(fichier);
                    for(int k=0; k < message.length; k++) {
                        fichier.ecrire("Code ( " + i + "," + j + ") " + message[k]);
                    }
                    System.out.println("\tLe fichier contient " + fichier.nbLigne() + " ligne(s)\n\tContenu : ");
                    
                    String[] contenu = fichier.lectureDesLignes();
                    for(int l = 0; l < contenu.length; l++) {
                        System.out.println(contenu[l]);
                    }
                } catch (IOException e) {
                    System.err.println("\nCode ( " + i + "," + j + ") " + e);
                } catch (NullPointerException e2) {
                    System.err.println("\nCode ( " + i + "," + j + ") " + e2);
                }
            }
        }
    }
}
