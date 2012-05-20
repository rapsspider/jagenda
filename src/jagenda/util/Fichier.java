/*
 * Fichier :    Fichier.java
 * Package :    jagenda.util
 * Projet jAgenda - Info 1 2011-2012
 *
 */
package jagenda.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Classe {@code Fichier} contient les méthodes utiles à la gestion de fichiers 
 * texte (.txt).<br>
 * Les interactions sont rendues possibles par l'intermédiaire d'un flux 
 * d'entrées et flux de sorties du contenu du fichier.<br><br>
 * <i>Exemple d'utilisation : </i><br>
 * <pre>
 *     try {
 *         Fichier monfichier = new Fichier("repertoire", "monfichier.txt");
 *         monfichier.initEcriture(true);
 *         monfichier.ecriture("Nouvelle ligne");
 *         monfichier.fermeEcriture();
 *         <br>
 *         String[] contenu = new String[0];
 *         String ligne;
 *         <br>
 *         monficher.initLecture();
 *         while((ligne = monficher.lireLigne()) != null) {
 *             contenu = Arrays.copyOf(contenu, contenu.length+1);
 *             contenu[contenu.length-1] = ligne;
 *         }
 *         monfichier.fermeLecture();
 *     } catch (IOException ioEx) {
 *         System.out.println(ioEx.getMessage());
 *     }
 * </pre>
 * 
 * @author  Jason BOURLARD
 * @author  David PELISSIER
 * @version 0.3
 */
public class Fichier {

    /**
     * Expressions interdites pour un nom de fichier et/ou de
     * dossier.
     * <ul>
     *     <li>Pattern :  (.*),</li>
     *     <li>Pattern : \\.(.*),</li>
     *     <li>Pattern : con(\\..*),</li>
     *     <li>Pattern : nul(\\..*),</li>
     *     <li>Pattern : aux(\\..*),</li>                          
     *     <li>Pattern : lpt1(\\..*),</li>
     * </ul>
     */
    private static final Pattern expressionsInterdites = 
        Pattern.compile("(\\..*|(con|nul|aux|lpt1)(\\..*)*)");

    /**
     * Caractères interdits à utiliser dans les noms de fichiers.
     */
    private static final char[] caracteresInterdits = 
        { '\"', '/', '\\', '*', '?', '<', '>',  '|', ':' };
    
    /** 
     * Chemin d'accès au fichier.<br>
     * (/) (\\..*|(con|nul|aux|lpt1)(\\..*)*)/)*
     * (\\..*|(con|nul|aux|lpt1)(\\..*)*)
     */
    private final String CHEMIN;

    /** 
     * Nom du fichier à lire ou à éditer.<br>
     * (\\..*|(con|nul|aux|lpt1)(\\..*)*)
     * 
     * @see #expressionsInterdites
     */
    private final String NOM;

    
    /** Flux de sorties : Ecriture. */
    private BufferedWriter ecriture = null;

    /** Flux d'entrées : Lecture. */
    private BufferedReader lecture = null;
    
    /**
     * Création d'un nouvel objet représentant un {@code Fichier}.<br>
     * Un {@code Fichier} porte un {@code nomFichier} et possède un 
     * {@code chemin}.<br>
     * Un fichier peut être lu et modifié.<br>
     * Pour cela on utilise deux flux, un {@code flux d'entrées} dit 
     * {@code lecture} pour lire, et un {@code flux de sorties} dit 
     * {@code ecriture} pour écrire. Ces flux ne sont pas initialisés lors
     * de l'appel du constructeur. <br>
     * Pour les initialiser, il faut faire appel aux méthodes 
     * {@code initLecture()} et {@code initEcriture()}.
     * 
     * @param  chemin       Dossier ou chemin menant au {@code Fichier}.
     * @param  nomFichier   {@code Fichier} à ouvrir.
     * @throws IOException  est généré si le chemin du {@code Fichier} est 
     *                      incorrect.
     * @see   #estCheminValide(String)
     * @see   #estNomValide(String)
     * @see   #expressionsInterdites
     * @see   #caracteresInterdits
     */
    public Fichier(String chemin, String nomFichier) throws IOException {

        // Si le chemin est nul ou vide, on instance la valeur de chemin à 
        // "vide" et on verifie simplement la validité du nom.
        if(!estNomValide(nomFichier)) {
            throw new IOException(nomFichier + " n'est pas un nom de fichier"
                    + " correct.");
        } else if (!estCheminValide(chemin)) {
            throw new IOException(chemin + " est un chemin incorrect.");
        }

        this.NOM = nomFichier;
        this.CHEMIN = chemin != "" && chemin != null ? chemin : "";
    }

    
    /**
     * Création d'un nouvel objet représentant un {@code Fichier}.<br>
     * Un {@code Fichier} porte un {@code nomFichier} et possède un 
     * {@code chemin}.<br>
     * Un fichier peut être lu et modifié.<br>
     * Pour cela on utilise deux flux, un {@code flux d'entrées} dit 
     * {@code lecture} pour lire, et un {@code flux de sorties} dit 
     * {@code ecriture} pour écrire. Ces flux ne sont pas initialisés lors
     * de l'appel du constructeur. <br>
     * Pour les initialiser, il faut faire appel aux méthodes 
     * {@code initLecture()} et {@code initEcriture()}.
     * 
     * @param  nomFichier   {@code Fichier} à ouvrir.
     * @throws IOException  est généré si le chemin du {@code Fichier} est 
     *                      incorrect.
     * @see   #estCheminValide(String)
     * @see   #estNomValide(String)
     * @see   #expressionsInterdites
     * @see   #caracteresInterdits
     */
    public Fichier(String nomFichier) throws IOException {

         // Si le nom du fichier est correct, on peut continuer...
        if(!estNomValide(nomFichier)) {
            throw new IOException(nomFichier + " n'est pas un nom de fichier"
                    + "correct");
        }
        this.CHEMIN = "";
        this.NOM = nomFichier;
    }

    
    /**
     * Initialise le {@code flux de sorties} dit {@code Ecriture}.<br>
     * C'est le flux de sorties permettant d'écrire dans le {@code Fichier}.
     * Les données envoyées sur ce flux seront immédiatement inscrites à la fin 
     * du {@code Fichier}.
     * 
     * @throws  IOException          est généré si le {@code chemin} du 
     *                               {@code Fichier} est anormal ou bien si les 
     *                               droits d'écriture sont insuffisants.
     *                               Il faut peut-être faire appel à 
     *                               {@code creationDossier()} afin  de créer
     *                               les différents dossiers qui forment le 
     *                               {@code chemin}.
     * @throws FileNotFountException est généré si le {@code chemin} menant au 
     *                               {@code Fichier} est incorrect ou n'existe 
     *                               pas.
     * @see    #creationDossiers(String)
     */
    public void initEcriture() throws IOException {
        initEcriture(true);
    }

    
    /**
     * Initialise le {@code flux de sorties} dit {@code Ecriture}.<br>
     * C'est le flux de sorties permettant d'écrire dans le {@code Fichier}.<br>
     * Si {@code sansEcrase} vaut {@code true}, les données envoyées sur ce flux
     * seront immédiatement inscrites à la fin du {@code Fichier}.<br> 
     * Dans le cas contraire, le {@code Fichier} est réinitialisé, et les
     * données sont inscrites au début du {@code Fichier}.
     * 
     * @param  sansEcraser           {@code true} si on désire écrire en fin de
     *                               {@code Fichier}
     * @throws IOException           est généré si le {@code chemin} du 
     *                               {@code Fichier} est anormal ou bien si les 
     *                               droits d'écriture sont insufisants.<br>
     *                               Il faut peut-être faire appel à 
     *                               {@code creationDossier()} afin  de créer
     *                               les différents dossiers qui forment le 
     *                               {@code chemin}.
     * @throws FileNotFountException est généré si le {@code chemin} menant au 
     *                               {@code Fichier} est incorrect ou n'existe 
     *                               pas.
     * @see    #creationDossiers(String)
     */
    public void initEcriture(boolean sansEcraser) throws IOException {
        if (this.CHEMIN.equals("/")) {
            this.ecriture = new BufferedWriter(
                    new FileWriter(this.CHEMIN + this.NOM, sansEcraser));
        } else if (!this.CHEMIN.equals("")) {
            this.ecriture = new BufferedWriter(
                    new FileWriter(this.CHEMIN + '/' + this.NOM, sansEcraser));
        } else {
            this.ecriture = new BufferedWriter(
                    new FileWriter(this.NOM, sansEcraser));
        }
    }

    
    /**
     * Initialise le {@code flux d'entrées} dit {@code Lecture}.<br>
     * C'est le flux d'entrées permettant la lecture d'un {@code Fichier}.
     * 
     * @throws FileNotFoundException est généré si le {@code chemin} du
     *         {@code Fichier} est anormal.
     */
    public void initLecture() throws FileNotFoundException {
        if (this.CHEMIN.equals("/")) {
            this.lecture = new BufferedReader(new FileReader(this.CHEMIN 
                    + this.NOM));
        } else if (!this.CHEMIN.equals("")) {
            this.lecture = new BufferedReader(new FileReader(this.CHEMIN + '/' 
                                                           + this.NOM));
        } else {
            this.lecture = new BufferedReader(new FileReader(this.NOM));
        }
    }

    
    /**
     *  Accesseur pour le champ {@code CHEMIN}
     *  
     *  @return Chemin d'accès au {@code Fichier}.
     */
    public String getChemin() {
        return CHEMIN;
    }

    
    /**
     *  Accesseur pour le champ {@code NOM}.
     *  
     *  @return Nom du {@code Fichier}.
     */
    public String getNom() {
        return NOM;
    }

    
    /**
     * Vérifie si la {@code chaine} ne comporte pas de caractères interdits, 
     * n'est pas nulle et comporte au moins 1 caractère.<br>
     * Les caractères interdits sont : ", /, \, *, ?, <, >, | et : <br>
     * De plus, la {@code chaine} de caractères doit être différente des 
     * expressions suivantes :
     * <ul>
     *     <li>Pattern :  (.*),</li>
     *     <li>Pattern : \\.(.*),</li>
     *     <li>Pattern : con(\\..*),</li>
     *     <li>Pattern : nul(\\..*),</li>
     *     <li>Pattern : aux(\\..*),</li>                          
     *     <li>Pattern : lpt1(\\..*),</li>
     * </ul>
     * 
     * @param  chaine   {@code Chaine} à tester.
     * @return {@code TRUE} si la {@code chaine} est correcte.
     * @see    #expressionsInterdites
     * @see    #caracteresInterdits
     * 
     */
    public static boolean estValide(String chaine) {
        boolean chaineCorrecte;
        if(chaine != null && chaine.length() > 0) {
            chaineCorrecte = true;
            /*
             * On verifie que la chaine ne commence pas par
             * con(.[a-z]), nul(.[a-z]), aux(.[a-z]) ou lpt1(.[a-z]).
             *
             * On regarde que la structure du mot expression soit différente de
             * la chaine mis en paramètre.
             * Si le mot ressemble à une expression interdite, alors on signale 
             * que la chaine est incorrecte.
             */
            chaineCorrecte = !expressionsInterdites.matcher(chaine).matches();
            
            /*
             * On vérifie que tous les caractères de la chaine sont différents
             * des caractères interdits. 
             */
            for(int c = 0; c < chaine.length() && chaineCorrecte; c++) {
                for(int i = 0; i < caracteresInterdits.length; i++) {
                    if (caracteresInterdits[i] == chaine.charAt(c)) {
                        chaineCorrecte = false;
                    }
                }
            }
        } else {
            chaineCorrecte = false;
        }
        return chaineCorrecte;
    }


    /**
     * Méthode permettant de tester si le {@code chemin} est possible.<br>
     * Si le chemin comporte un '/' en premier caractère, cela indique que le 
     * {@code chemin} débute à la racine.<br>
     * Un chemin ne fini pas par un '/' sauf s'il ne contient qu'un seul 
     * caractère.<br>
     * <i>Exemples<br>
     * <ul>
     *     <li>/chemin/ est <b>impossible</b></li>
     *     <li>/ est <b>possible</b></li>
     *     <li>null est <b>possible</b></li>
     *     <li>chemin est <b>possible</b></li>
     *     <li>chemin//dir/ est <b>impossible</b></li>
     *     <li>/chemin est <b>possible</b></li>
     * </ul>
     * </i>
     * 
     * @param  chemin    Nom du {@code chemin} à analyser.
     * @return {@code true} si le {@code chemin} est valide.
     * @see    #estValide(String)
     */
    public static boolean estCheminValide(String chemin) {
        boolean estCorrect = true;

        if (chemin != null && !chemin.equals("/") && chemin != "") {
            // On découpe le chemin en dossiers
            String[] dossier = chemin.split("/");
            // On s'assure que tous les noms de dossiers soient corrects.
            for(int i = 1; i < dossier.length && estCorrect;i++) {
                estCorrect = estValide(dossier[i]);
            }
            // Vérification du dernier caractère de la chaine, différent de '/'.
            estCorrect = estCorrect && chemin.charAt(chemin.length()-1) != '/';
        }
        return estCorrect;
    }

    
    /**
     * Méthode permettant de tester la valeur du {@code nom} de {@code Fichier}.
     * S'il est valide, sa valeur est mise à jour.<br>
     * Un {@code nom} de {@code Fichier} comporte au moins 1 caractère. Il peut
     * ou non être suivis d'une extention.<br>
     * <i>Exemple : nomdufichier.extension</i><br>
     * Cependant certains caractères sont interdits.
     * 
     * @param   fichier {@code Nom} du {@code Fichier} à analyser.
     * @return  {@code true} si le {@code nom} est correct.
     * @see     #estValide(String)
     * @see     #expressionsInterdites
     * @see     #caracteresInterdits
     * 
     */
    public static boolean estNomValide(String fichier) {
        return estValide(fichier);
    }

    /**
     * Crée un ou plusieurs {@code dossiers}.<br>
     * Le (premier) {@code dossiers} est créé dans le même repertoire que 
     * l'exécutable Java.<br>
     * Le dossier n'est pas créé s'il comporte un nom nul. Plusieurs 
     * {@code dossiers} sont créés si l'argument {@code dossier} comporte des 
     * <b>/</b> permettant de définir un chemin.<br>
     * Un nom de {@code dossier} doit être différent de 
     * {@code expressionsInterdites}.
     * 
     * @param dossiers Nom du(des) dossier(s) à créer.
     * @see   #estValide(String)
     * @see   #expressionsInterdites
     * @see   #caracteresInterdits
     */
    public static void creationDossiers(String dossiers) {
        String[] dossier = dossiers.split("/");
        int i;

        for(i=0; i < dossier.length && estValide(dossier[i]); i++);
        if (i==dossier.length && dossiers != null && dossiers.length() > 0 
                && dossiers.charAt(dossiers.length()-1) != '/') {
            new File(dossiers).mkdirs();
        }
    }

    /**
     * Méthode permettant la suppression définitive du {@code Fichier}.<br>
     * On ferme le {@code flux d'entrées} dit {@code Lecture}, et le 
     * {@code flux de sorties} dit {@code Ecriture}, avant de supprimer le 
     * {@code Fichier}.<br>
     * Si un flux est encore en actif, le {@code Fichier} ne pourra pas être 
     * supprimé.
     */
    public void supprimeFichier() {
        fermeLecture();
        fermeEcriture();
    
        File fichierASupprimer;
        if (this.CHEMIN.equals("/")) {
            fichierASupprimer = new File(this.CHEMIN + this.NOM);
        } else if (this.CHEMIN != "") {
            fichierASupprimer = new File(this.CHEMIN + '/' + this.NOM);
        } else {
            fichierASupprimer = new File(this.NOM);
        }
    
        if(!fichierASupprimer.delete()) {
            System.err.println("Le fichier n'a pas pu être supprimé. " +
                    "Peut-être est t-il en cours d'utilisation ?"); 
        }
    }


    /**
     * Fermeture du {@code flux d'entrées} dit {@code Lecture} permettant la 
     * lecture du {@code Fichier}.
     */
    public void fermeLecture() {
        if(this.lecture != null) {
            try {
                this.lecture.close();
            } catch (IOException ioEx) {
                System.err.println("Erreur dans la fermeture du flux de "
                        + "lecture.");
            }
        }
    }

    /**
     * Fermeture du {@code flux de sorties} dit {@code Ecriture} permettant 
     * d'écrire dans le {@code Fichier}.
     */
    public void fermeEcriture() {
        if(this.ecriture != null) {
            try {
                this.ecriture.close();
            } catch (IOException ioEx) {
                System.err.println("Erreur dans la fermeture du flux "
                        + "d'écriture.");
            }
        }
    }

    /**
     * Ajout d'un {@code message} dans le {@code Fichier} puis ajout d'une
     * nouvelle ligne.<br>
     * Cette méthode fait appel au {@code flux de sorties} dit {@code Ecriture}.
     * <br><b>WARNING</b> Tout caractère '\r' ou '\n' risque de créer un retour 
     * à la ligne.
     * 
     * @param message  Message à écrire dans le {@code Fichier}
     * @see   #ecriture
     */
    public void ecrire(String message) {
        if(this.ecriture != null) {

            if(message != null) {
                try {
                    this.ecriture.write(message);
                    this.ecriture.newLine();
                    this.ecriture.flush();
                } 
                catch(IOException ioEx) {
                }
            }
        } else {
            System.err.println("Le flux d'écriture n'a pas été initialisé.");
        }
    }

    /**
     * Ajout d'un {@code message} dans le fichier à la ligne {@code indice}.<br>
     * Cette méthode fait appel au {@code flux de sorties} dit {@code Ecriture}.
     * et à un {@code flux d'entrées} dit {@code Lecture}.<br><br>
     * Chaque ligne du {@code Fichier} est mis en mémoire, puis écrite une
     * à une dans le nouveau {@code Fichier} jusqu'à atteindre l'{@code indice}.
     * <br>
     * Si l'{@code indice} n'est pas atteint et que toutes les lignes ont été
     * réécrites. On place le {@code message} à la fin du {@code Fichier}.
     * La première ligne a pour indice 0.<br>
     * <br><b>WARNING</b> Tout caractère '\r' ou '\n' risque de créer un retour 
     * à la ligne.
     * 
     * @param message Message à écrire dans le {@code Fichier}.
     * @param indice  {@code Indice} de la ligne où écrire le {@code message}.
     * @see   #ecrire(String)
     * @see   #lireLigne()
     * @see   #lecture
     * @see   #ecriture
     */
    public void ecrire(String message, int indice) {
        if(this.ecriture != null && this.lecture != null) {
            // On garde en mémoire le contenu du fichier avant d'exécuter la 
            // suppression de la ligne.
            String[] contenuAvant = new String[0];
            String ligne;

            for(int i = 0; (ligne = lireLigne()) != null; i++) {
                contenuAvant=Arrays.copyOf(contenuAvant, contenuAvant.length+1);
                contenuAvant[i] = ligne;
            }

            try {
                // On remet à zéro le fichier avant de commencer l'écriture.
                initEcriture(false);

                /*
                 * On remet toutes les lignes en mémoire dans le fichier et 
                 * lorsque l'index vaut l'indice de la ligne correspondant au 
                 * messageà écrire, on écrit le message.
                 */
                for(int i = 0; i < contenuAvant.length; i++) {
                    if(indice == i) {
                        ecrire(message);
                    }
                    ecrire(contenuAvant[i]);
                }
            } catch (IOException ioEx) {
            }
        } else {
            System.err.println("Le flux d'écriture n'a pas été initialisé.");
        }
    }

    /**
     * Retourne la ligne courante dans le tampon du {@code Fichier}.<br>
     * Chaque appel à la fonction {@code readLine()} une nouvelle ligne est lu.
     * La fin du ligne se fait lors de la rencontre de '\n' ou '\r'.<br>
     * On retourne {@code null} si toutes les lignes du {@code Fichier} ont été 
     * lues. Pour réinitialiser la lecture, il faut faire appel à la méthode 
     * {@code initLecture()}.<br>
     * <i>Exemple d'utilisation :</i><br>
     * <pre>
     *     try {
     *         Fichier fichier = new Fichier("nomdufichier");
     *         String[] contenu = new String[0];
     *         String ligne;
     *     
     *         fichier.initLecture();
     *         while((ligne = lireLigne()) != null) {
     *             contenuAvant = Arrays.copyOf(contenuAvant,
     *                                          contenuAvant.length+1);
     *             contenuAvant[contenuAvant.length-1] = ligne;
     *          }
     *          fichier.fermeLecture();
     *      } catch (FileNotFoundException e) {
     *      }
     * </pre>
     * @return Ligne courante dans le tampon du {@code Fichier}.
     * @see #lecture
     */
    public String lireLigne() {
        // Contient le contenu de la ligne i
        String contenu = null;
        if(this.lecture != null) {
            try {
                // On prend la prochaine ligne du fichier
                contenu = this.lecture.readLine();
            } catch (IOException ioEx) {
                contenu = null;
            }
        } else {
            System.err.println("Le flux de lecture n'a pas été initialisé.");
        }
        return contenu;
    }

    /**
     * Remplacement de la ligne {@code indice} du {@code Fichier}.<br>
     * Cette méthode fait appel au {@code flux de sorties} dit {@code Ecriture}.
     * et à un {@code flux d'entrées} dit {@code Lecture}.<br><br>
     * Chaque ligne du {@code Fichier} est mis en mémoire, puis écrite une
     * à une dans le nouveau {@code Fichier} jusqu'à atteindre l'{@code indice}.
     * <br>
     * Si l'{@code indice} n'est pas atteint et que toutes les lignes ont été
     * réécrites. On place le {@code message} à la fin du {@code Fichier}.
     * La première ligne a pour indice 0.<br>
     * <br><b>WARNING</b> Tout caractère '\r' ou '\n' risque de créer un retour 
     * à la ligne.
     * 
     * @param message Message à écrire à l'indice {@code indice}.
     * @param indice  Indice de la ligne à remplacer.
     * @see   #ecrire(String)
     * @see   #lireLigne()
     * @see   #lecture
     * @see   #ecriture
     */
    public void remplacerLigne(String message, int indice) {
        if(this.ecriture != null && this.lecture != null) {
            // On garde en mémoire le contenu du fichier avant d'exécuter la 
            // suppression de la ligne.
            String[] contenuAvant = new String[0];
            String ligne;

            for(int i = 0; (ligne = lireLigne()) != null; i++) {
                contenuAvant=Arrays.copyOf(contenuAvant, contenuAvant.length+1);
                contenuAvant[i] = ligne;
            }

            try {
                // On remet à zéro le fichier avant de commencer l'écriture.
                initEcriture(false);

                /*
                 * On remet toutes les lignes en mémoire dans le fichier et 
                 * lorsque l'index vaut l'indice de la ligne correspondant au 
                 * messageà écrire, on écrit le message.
                 */
                for(int i = 0; i < contenuAvant.length; i++) {
                    if(indice == i) {
                        ecrire(message);
                    } else {
                        ecrire(contenuAvant[i]);
                    }

                }
            } catch (IOException ioEx) {
            }
        } else {
            System.err.println("Le flux d'écriture n'a pas été initialisé.");
        }
    }

    
    /**
     * Supprime une ligne du {@code Fichier}.<br>
     * La première ligne du {@code Fichier} a pour indice 0.<br>
     * Cette méthode fait appel au {@code flux de sorties} dit {@code Ecriture}.
     * et à un {@code flux d'entrées} dit {@code Lecture}.<br><br>
     * On stocke dans la mémoire toutes les lignes contenues dans le 
     * {@code Fichier} sosu forme de tableau. Ensuite, on réinitialise le 
     * {@code Fichier} à zéro et on réinsère toutes les lignes une par une.<br>
     * Si on atteint la ligne {@code indice}, on l'ignore et on insère les 
     * lignes suivantes.<br>
     * Si la ligne {@code indice} n'est pas atteinte avant la fin des lignes 
     * contenues dans le {@code Fichier}, rien ne se passe.
     * 
     * @param indice  Indice de de la ligne a supprimer.
     * @see   #ecrire(String)
     * @see   #lireLigne()
     * @see   #lecture
     * @see   #ecriture
     */
    public void supprimerLigne(int indice) {
        if(this.ecriture != null && this.lecture != null) {
            /*
             * On garde en mémoire le contenu du fichier
             * avant d'executer la suppression de la ligne
             */
            String[] contenuAvant = new String[0];
            String ligne;
    
            for(int i = 0; (ligne = lireLigne()) != null; i++) {
                contenuAvant = Arrays.copyOf(contenuAvant,
                        contenuAvant.length+1);
                contenuAvant[i] = ligne;
            }
    
            try {
                // On remet à zéro le fichier avant de commencer l'écriture.
                initEcriture(false);

                /*
                 * On remet toutes les lignes en mémoire dans le fichier et 
                 * lorsque l'index vaut l'indice de la ligne correspondant au 
                 * message à écrire, on omet la ligne pour la supprimer.
                 */
                for(int i = 0; i < contenuAvant.length; i++) {
                    if(i != indice) {
                        ecrire(contenuAvant[i]);
                    }
                }
            } catch (IOException ioEx) {
            }
        } else {
            System.err.println("Le flux d'écriture n'a pas été initialisé.");
        }
    }

    
    /**
     * Retourne le nombre de lignes que contient le {@code Fichier}.<br>
     * Une ligne se termine par "\n" ou "\r".<br>
     * Cette methode fait appel au {@code flux d'entrées} dit {@code Lecture}.
     * <br>Si une erreur survient, on renvoie -1.<br>
     * Cette méthode gère toute seule la fermeture et l'ouverture du 
     * {@code flux d'entrées} dit {@code Lecture}.
     * 
     * @return Nombre de lignes.
     * @see    #lecture
     */
    public int nbLigne() {
        int nbLigne = 0;
        try {
            // On place l'ancien BufferedReader en mémoire
            BufferedReader tampon = this.lecture;
            
            // On réinitialise le flux d'entrée
            initLecture();

            // On incrémente nbLigne tant que readLine() retourne un résultat
            while (this.lecture.readLine() != null) {
                nbLigne++;
            }

            // Toutes les lignes ont été lues. On peut fermer le flux d'entrées.
            fermeLecture();

            // On replace le BufferedReader mis en mémoire dans this.lecture
            this.lecture = tampon;
        } catch (IOException ioEx) {
            nbLigne = -1;
        }
        return nbLigne;
    }

    /**
     * Formate le chemin du fichier sous forme de chaîne de caractères au format
     *  String.<br>
     * Polymorphisme de la méthode {@code toString} de la classe {@code Object}.
     * <br>
     * <i>Exemple : iut/java/nomdufichier.ext</i>
     * 
     * @return Chemin du fichier et nom du fichier.
     */
    public String toString() {
        String aRetourner;
        if (this.CHEMIN != "" && this.CHEMIN != "/") {
            aRetourner = this.CHEMIN + "/" + this.NOM;
        } else if (this.CHEMIN == "/"){
            aRetourner = "/" + this.NOM;
        } else {
            aRetourner = this.NOM;
        }
        return aRetourner;
    }

    /**
     * TODO commenter
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + CHEMIN.hashCode();
        result = prime * result + NOM.hashCode();
        return result;
    }

    /**
     * Polymorphisme de la classe {@code Object}.
     * Retourne vrai si l'{@code Object} en argument est le même {@code Object}
     * que celui-ci.
     * @param  unObject {@code Object} à comparer avec {@code Fichier}.
     * @return {@code true} Si l'{@code Object} mis en argument représente
     *         un {@code Fichier} équivalent à ce {@code Fichier}, retourne
     *         {@code false} dans les autres cas.
     */
    @Override
    public boolean equals(Object unObject) {
        if (this == unObject) {
            return true;
        } else if (unObject == null || getClass() != unObject.getClass()) {
            return false;
        }

        Fichier fich = (Fichier) unObject;
        return this.CHEMIN.equals(fich.CHEMIN) && this.NOM.equals(fich.NOM);
    }

    /**
     * Polymorphisme de la classe {@code Object}.
     * Compare le {@code CHEMIN} suivis du {@code NOM} de deux fichiers 
     * lexicographiquement.<br>
     * Cette méthode fais appel a java.lang.String.compareTo(String).
     * @param   fich      the {@code Fichier} to be compared.
     * @return  La valeur {@code 0} si le fichier en argument est équivalent
     *          à ce fichier; Une valeur inférieur à {@code 0} si le fichier
     *          a un chemin suivis de son nom lexicographiquement plus petit que
     *          le fichier en argument; Une valeur supérieur à {@code 0} si le 
     *          fichier a un chemin suivis de son nom lexicographiquement plus 
     *          grand que le fichier en argument.
     */
    public int compareTo(Fichier fich) {
        return (this.CHEMIN + this.NOM).compareTo(fich.CHEMIN + fich.NOM);
    }

}