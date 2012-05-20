/*
 * Fichier :    TestFichier.java
 * Package :    jagenda.test
 * Projet jAgenda - Info 1 2011-2012
 *
 */

package jagenda.tests;

import jagenda.util.Fichier;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

/**
 * Classe {@code TestFichier} contient les jeux de tests n�cessaire � la gestion
 * des diff�rentes m�thodes de la classe {@code Fichier} :
 * 
 * @author  Jason BOURLARD
 * @author  David PELISSIER
 * @version 0.3
 */
public class TestFichier {
    
    /** Exemple de nom de fichier afin d'effectuer les test*/
    public static String nomdufichier = "monfichierdetest";
    
    /** Fichier o� seront effectu�s les tests */
    public static Fichier fichier;
    
    /**
     * Permet de lancer une des m�thodes qui test la classe Fichier
     * @param args non utilis�
     */
    public static void main(String[] args) {
        //testEquals();
        //testSupprimeFichier();
        //testCompareTo();
        //testCreationFichier();
        //testCreeFichier();
        //testPerformanceLectureLigne();
        //testSuppressionLigne();
        testModifLigne();
        //testAjoutLigne();
        //testPattern();
    }
    
    /**
     * Test sur la m�thode supprime de {@code Fichier}.
     */
    public static void testSupprimeFichier() {
        try {
            Fichier thefile = new Fichier("test","thefile");
            thefile.initEcriture();
            thefile.fermeEcriture();
            thefile.supprime();
            System.out.println();
            
            thefile = new Fichier("test2","thefile");
            thefile.initEcriture();
            thefile.supprime();
            
            thefile = new Fichier("test23","thefile");
            thefile.initEcriture();
            thefile.fermeEcriture();
            thefile.initLecture();
            thefile.supprime();
        } catch (IOException ioEx) {
        }
    }
    
    /**
     * Test de performance lors de la lecture des donn�es. Ces donn�es sont
     * ensuite stock�es dans un tableau de string.
     */
    public static void testPerformanceLectureLigne() {
        
        long debut, fin;
        
            /*
             * Ici on test la rapidit� de la premi�re m�thode qui consiste
             * � stocker le contenue du fichier dans un tableau de string
             * en initialisant ce tableau � l'aide de la m�thode nbLigne().
             */
            debut = System.currentTimeMillis();
            try {
                String[] contenu = new String[fichier.nbLigne()];
                String ligne;
                fichier.initLecture();
                for(int i = 0; (ligne = fichier.lireLigne()) != null; i++) {
                    contenu[i] = ligne;
                }
                fichier.fermeLecture();
            } catch (FileNotFoundException e) {
            }
            fin = System.currentTimeMillis();
            System.out.println("Temps d'execution de la premiere m�thode : " +
                               (fin-debut) + "ms");
        
            /*
             * Ici on test la rapidit� de la seconde m�thode qui consiste
             * � stocker le contenue du fichier dans un tableau de string
             * en augmentant la taille du tableau de 1 � chaque entr� dans
             * la boucle.
             */
            debut = System.currentTimeMillis();
            try {
                String[] contenu = new String[0];
                String ligne;
                fichier.initLecture();
                for(int i = 0; (ligne = fichier.lireLigne()) != null; i++) {
                    contenu = Arrays.copyOf(contenu,
                                            contenu.length+1);
                    contenu[i] = ligne;
                }
                fichier.fermeLecture();
            } catch (FileNotFoundException e) {
            }
            fin = System.currentTimeMillis();
            System.out.println("Temps d'execution de la seconde m�thode : " +
                               (fin-debut) + "ms");
    }
    
    /**
     * Cr�e et �cris dans un fichier.
     */
    public static void testCreeFichier() {
        int nbLigne = 30;
        try {
            try {
                // On �crase le fichier pr�c�dant
                fichier = new Fichier("Chemin/de/merde", nomdufichier);
                
                //On initialise le flux d'�criture
                fichier.initEcriture(false);
            } catch (FileNotFoundException ioe) {
                try {
                    Fichier.creationDossiers("Chemin/de/merde");
                    
                    // On �crase le fichier pr�c�dant
                    fichier = new Fichier("Chemin/de/merde", nomdufichier);
                    
                    //On initialise le flux d'�criture
                    fichier.initEcriture(false);
                } catch (FileNotFoundException ioe2) {
                    System.err.println(ioe2.getMessage());
                }  
            } finally {
                // On inscrit toutes les lignes
                for(int k=0; k < nbLigne; k++) {
                    fichier.ecrire("Ligne " + k);
                }
                // On ferme le flux d'�criture
                fichier.fermeEcriture();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    
    /**
     * M�thode testant le remplacement d'une ligne par une autre.
     */
    public static void testModifLigne() {
        int indice = 5; //Comporte l'indice de la ligne � remplacer
        try {
            fichier.initEcriture();
            fichier.initLecture();
            fichier.remplacerLigne("La ligne " + indice + " a �t� remplace.",
                                    indice);
            fichier.fermeLecture();
            fichier.fermeEcriture();
        } catch (IOException ioe) {
        }
    }
    
    /**
     * M�thode testant l'ajout d'une ligne � un num�ro de ligne donn�e.
     */
    public static void testAjoutLigne() {
        int indice = 7; //Comporte l'indice de la ligne � ins�rer
        try {
            fichier.initEcriture();
            fichier.initLecture();
            fichier.ecrire("La ligne " + indice + " a �t� ajout�.",indice);
            fichier.fermeLecture();
            fichier.fermeEcriture();
        } catch (IOException ioe) {
        }
    }
    
    
    /**
     * M�thode testant la supression d'une ligne � un num�ro de ligne donn�e.
     */
    public static void testSuppressionLigne() {  
        int indice = 9; //Comporte l'indice de la ligne � supprimer
        try {
            fichier.initEcriture();
            fichier.initLecture();
            fichier.supprimerLigne(indice);
            fichier.fermeLecture();
            fichier.fermeEcriture();
        } catch (IOException ioe) {
        }
    }
    
    /**
     * M�thode permettant de tester la verification des noms des fichiers.
     * Certain nom sont interdit, on verifie ici le r�sultat obtenu.
     */
    public static void testPattern() {
        String[] nom = {                    "",       ".",       ",",      "/",      
                                           "a",      ".a",     "con",   "con.",
                                     "con.txt", "concept", "ch/fich"};
        
        boolean[] doitEtreCorrect = { false,        false,       true,    false,
                                       true,        false,      false,    false,
                                      false,         true,     false };
        
        int nbErreur = 0;
        for(int i = 0; i < nom.length; i++) {
            if (Fichier.estValide(nom[i]) != doitEtreCorrect[i]) {
                nbErreur++;
            }
            System.out.println(nom[i] + " est correct : " 
                + Fichier.estValide(nom[i])
                + "\t\tResultat attendu : " + doitEtreCorrect[i]);
        }
        System.out.println("Nombre d'erreur rencontr� : " + nbErreur);
    }
    
    /**
     * M�thode testant la cr�ation d'un {@code Fichier} avec divers chemins et 
     * noms de fichier.
     */
    public static void testCreationFichier() {
        String[] chemin = { null,
                        "/",
                        "a",
                        "/a",
                        "b/",
                        "a/c",
                        "b/c/",
                        "/c/a/"
                        };
        
        String[] nomfichier = { null,
                          "fichier",
                          "fichier.a",
                          "/fichier.b",
                          "fichier/fichier.c" 
                         };
        
        Fichier fichier;
        
        for(int i=0; i < chemin.length; i++) {
            for(int f=0; f < nomfichier.length; f++) {
                try {
                    fichier = new Fichier(chemin[i], nomfichier[f]);
                    System.out.println("\nDEBUG ( chemin = " + chemin[i] + ", "
                    		       + "fichier " + "= " + nomfichier[f] + ")"
                    		       + " " + fichier);
                } catch (IOException ioEx) {
                    System.err.println("\nDEBUG ( chemin = " + chemin[i] + ", "
                    		       + "fichier = " + nomfichier[f] + ")" 
                                       + ioEx);
                }
            }
        }
    }
    
    /**
     * M�thode testant la comparaison de deux {@code Fichiers} avec compareTo.
     */
    public static void testCompareTo() {
        String[] chemin = { null, "/", "a", "/a","/ab"};
        
        String[] nomfichier = { "a.txt", "b.txt",};
        
        Fichier fichiera, fichierb;
        
        for(int i=0; i < chemin.length; i++) {
            try {
                fichiera = new Fichier(chemin[i], nomfichier[0]);
                fichierb = new Fichier(chemin[i], nomfichier[1]);
                System.out.println(fichiera + "\t" 
                                   + fichiera.compareTo(fichierb) + "\t" 
                                   + fichierb);
            } catch (IOException e) {
            }
        }
        
        for(int i=0; i < chemin.length-1; i++) {
            try {
                fichiera = new Fichier(chemin[i], nomfichier[0]);
                fichierb = new Fichier(chemin[i+1], nomfichier[0]);
                System.out.println(fichiera + "\t" 
                        + fichiera.compareTo(fichierb) + "\t" 
                        + fichierb);
            } catch (IOException e) {
            }
        }
    }
    
    /**
     * M�thode testant la comparaison de deux {@code Fichiers} avec equals.
     */
    public static void testEquals() {
        
        Fichier fichiera, fichierb;
    
        try {
            fichiera = new Fichier("chemin", "a.txt");
            fichierb = new Fichier("chemin", "a.txt");
            System.out.println(fichiera + "\t" + fichiera.equals(fichierb) 
                               + "\t" + fichierb);
            
            fichiera = new Fichier("chemin", "a.txt");
            fichierb = new Fichier("CHEMIN", "a.txt");
            System.out.println(fichiera + "\t" + fichiera.equals(fichierb) 
                               + "\t" + fichierb);
            
            fichiera = new Fichier("chemin", "a.txt");
            fichierb = new Fichier("chemin", "A.txt");
            System.out.println(fichiera + "\t" + fichiera.equals(fichierb) 
                               + "\t" + fichierb);
            
            fichiera = new Fichier("chemin", "a.txt");
            fichierb = fichiera;
            System.out.println(fichiera + "\t" + fichiera.equals(fichierb) 
                               + "\t" + fichierb);
        } catch (IOException e) {
        }
    }
}
