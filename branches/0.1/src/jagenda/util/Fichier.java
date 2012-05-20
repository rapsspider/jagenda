/*
 * Fichier :    Fichier.java
 * Package :    jagenda.util
 * Projet jAgenda - Info 1 2011-2012
 *
 * $Revision$
 * $Date$
 * $HeadURL$
 */
package jagenda.util;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

/**
 * Classe <code>Fichier</code> contient les méthodes utiles à la
 * gestion de fichiers texte (.txt) :
 * <ul><li>Lecture</li>
 *     <li>Ecriture</li></ul>
 * Les interactions sont rendues possibles par l'intermédiaire d'un
 * flux d'entrée et flux de sortie du contenu du fichier.
 * 
 * @author Jason BOURLARD
 * @author David PELISSIER
 */
public class Fichier {

    /** 
     * Flux de sortie : Ecriture 
     */
    protected BufferedWriter ecriture;
    
    /** 
     * Flux d'entrée : Lecture
     */
    protected BufferedReader lecture;
    
    /** 
     * Nom du fichier à lire ou à modifier 
     */
    protected String nom;
    
    /** 
     * Chemin d'accès au fichier
     * [/] ((a-z)(a-z)*(/))* (a-z)(a-z)*
     */
    protected String chemin;
    
    /**
     * Créé un dossier.<br>
     * Le dossier est créé au même endroit que l'exécutable
     * java qui contient le main().<br>
     * Le dossier n'est pas crée s'il comporte un nom nul.
     * @param dossier Dossier à créer
     */
    public static void creationDossier(String dossier) {
        if(dossier.length() > 0 && dossier.charAt(dossier.length()-1) != '/') {
            new File(dossier).mkdirs();
        }
    }
    
    /**
     * Initialise le flux de lecture.<br>
     * C'est le flux d'entrée pour permettant la lecture d'un fichier.
     * @throws IOException est généré si le chemin du fichier est anormal
     */
    private void initReader() throws IOException {
    	if (this.chemin == "/") {
    		this.lecture = new BufferedReader(new FileReader(this.chemin + this.nom));
    	} else if (this.chemin != "") {
            this.lecture = new BufferedReader(new FileReader(this.chemin + '/' + this.nom));
    	} else {
    		this.lecture = new BufferedReader(new FileReader(this.nom));
    	}
    }
    
    /**
     * Initialise le flux d'écriture.<br>
     * C'est le flux de sortie permettant d'écrire dans le fichier.
     * @param sansEcraser Contient TRUE si on écris à la fin du fichier
     * @throws IOException est généré si le chemin du fichier est anormal
     */
    private void initWriter(boolean sansEcraser) throws IOException {
    	if (this.chemin == "/") {
    		this.ecriture = new BufferedWriter(new FileWriter(this.chemin  +  this.nom, sansEcraser));
    	} else if (this.chemin != "") {
    		this.ecriture = new BufferedWriter(new FileWriter(this.chemin  + '/' +  this.nom, sansEcraser));
   	    } else {
   	    	this.ecriture = new BufferedWriter(new FileWriter(this.nom, sansEcraser));
        }
    }
    
    /**
     *  Supprime une ligne du fichier.<br>
     *  La première ligne du fichier a pour indice 0.
     *  @param indice Ligne a supprimer
     */
    public void supprimerLigne(int indice) {
        /*
         * On garde en mémoire le contenu du fichier
         * avant d'executer la suppression de la ligne
         */
        String[] contenuAvant = lectureDesLignes();
        
        try {
        	/*
             * On remet à zero le fichier avant de commencer
             * l'écriture
             */
        	initWriter(false);
        
            /*
             * On remet toutes les lignes en mémoire dans
             * le fichier sauf celle qui doit être
             * supprimée
             */
            for(int i = 0; i < contenuAvant.length; i++) {
                if(i != indice) {
                    ecrire(contenuAvant[i]);
                }
            }
        } catch (IOException e) {
        }
    }
    
    /**
     * Création d'un nouvel objet représentant un <code>Fichier</code>.<br>
     * Un <code>Fichier</code> porte un <code>nomDuFichier</code> et possède
     * un <code>chemin</code>.<br>
     * Un fichier peut être lu et modifié.<br>
     * Pour cela on utilise deux flux, un flux d'entrée pour lire,<br>
     * et un flux de sortie pour écrire.
     * @param  chemin  Dossier ou chemin menant au fichier
     * @param  nomDuFichier Fichier à ouvrir
     * @throws IOException est généré si le chemin du fichier est anormal
     */
    public Fichier(String chemin, String nomDuFichier) throws IOException {
    	/*
    	 * Si le chemin et le nom du fichier sont correct
    	 * On peut continuer
    	 */
        if(setChemin(chemin) && setNom(nomDuFichier)) {
            try {
                initWriter(true);
            } catch (IOException e) {
                creationDossier(this.chemin);
                initWriter(true);
            }
            initReader();
        } else {
        	throw new IOException("Chemin ou nom de fichier anormal");
        }
    }
    
    /**
     * Création d'un nouvel objet représentant un <code>Fichier</code>.<br>
     * Un <code>Fichier</code> porte un <code>nomDuFichier</code> et possède
     * un <code>chemin</code>.<br>
     * Un fichier peut être lu et modifié.<br>
     * Pour cela on utilise deux flux, un flux d'entrée pour lire,<br>
     * et un flux de sortie pour écrire.
     * @param  chemin       Dossier ou chemin menant au fichier
     * @param  nomDuFichier Fichier à ouvrir
     * @param  ecrase       Indique si le fichier doit être écrasé ou non.
     * @throws IOException  est généré si le chemin du fichier est anormal
     */
    public Fichier(String chemin, String nomDuFichier, boolean ecrase) throws IOException {
    	/*
    	 * Si le chemin et le nom du fichier sont correct
    	 * On peut continuer
    	 */
        if(setChemin(chemin) && setNom(nomDuFichier)) {
            try {
                initWriter(!ecrase);
            } catch (IOException e) {
                creationDossier(this.chemin);
                initWriter(!ecrase);
            }
            initReader();
        } else {
        	throw new IOException("Chemin ou nom de fichier anormal");
        }
    }
    
    /**
     * Création d'un nouvel objet représentant un <code>Fichier</code>.<br>
     * Un <code>Fichier</code> porte un <code>nomDuFichier</code>
     * et possède un <code>chemin</code> qui ici sera vide.<br>
     * Un fichier peut être lu et modifié.<br>
     * Pour cela on utilise deux flux, un flux d'entrée pour lire,<br>
     * et un flux de sortie pour écrire.
     * @param  nomDuFichier Fichier à ouvrir
     * @throws IOException  est généré si le chemin du fichier est anormal
     */
    public Fichier(String nomDuFichier) throws IOException {
    	this.chemin = "";
    	
    	/*
    	 * Si le nom du fichier est correct
    	 * On peut continuer
    	 */
        if(setNom(nomDuFichier)) {
            try {
                initWriter(true);
            } catch (IOException e) {
                initWriter(true);
            }
            initReader();
        } else {
        	throw new IOException("Chemin ou nom de fichier anormal");
        }
    }
    
    /**
     * Création d'un nouvel objet représentant un <code>Fichier</code>.<br>
     * Un <code>Fichier</code> porte un <code>nomDuFichier</code>
     * et possède un <code>chemin</code> qui ici sera vide.<br>
     * Un fichier peut être lu et modifié.<br>
     * Pour cela on utilise deux flux, un flux d'entrée pour lire,<br>
     * et un flux de sortie pour écrire.
     * Le fichier est écrasé avant d'être utilisé.
     * @param  nomDuFichier Fichier à ouvrir
     * @param  ecrase       Indique si le fichier doit être écrasé ou non.
     * @throws IOException  est généré si le chemin du fichier est anormal
     */
    public Fichier(String nomDuFichier, boolean ecrase) throws IOException {
    	this.chemin = "";
    	/*
    	 * Si le nom du fichier est correct
    	 * On peut continuer
    	 */
        if(setNom(nomDuFichier)) {
            try {
                initWriter(!ecrase);
            } catch (IOException e) {
                initWriter(!ecrase);
            }
            initReader();
        } else {
        	throw new IOException("Chemin ou nom de fichier anormal");
        }
    }
    
    /**
     * Verifie si la chaine ne comporte pas de caractère interdit.<br>
     * Ces caractères interdits sont : ", /, \, *, ?, <, >, | et : <br>
     * La <code>chaine</chaine> doit être différente de con(.[a-z]), nul(.[a-z])
     * aux(.[a-z]), lpt1(.[a-z]).
     * @param chaine Contient la <code>chaine</code> à tester
     * @return TRUE si la <code>chaine</code> est correct
     */
    public static boolean estCorrect(String chaine) {
    	final char[] caractereInterdit = { '\"', '/', '\\', '*', '?', '<', '>', '|', ':' };
    	final String[] nomInterdit = { "con", "nul", "aux", "ltp1" };
    	boolean chaineCorrect = true;
    	
    	/*
    	 * On verifie que la chaine ne commence pas par
    	 * con(.[a-z]), nul(.[a-z]), aux(.[a-z]) ou lpt1(.[a-z])
    	 */
    	for(int c, i = 0; i < nomInterdit.length && chaineCorrect; i++) {
    		
    		/*
    		 * On regarde si la taille de la chaine est supérieur
    		 * à la taille du mot interdit
    		 */
    		if (chaine.length() > nomInterdit[i].length()) {
    			/*
    			 * On regarde caractère par caractère
    			 */
	    		for(c = 0; c < nomInterdit[i].length() 
	    				&& nomInterdit[i].charAt(c) == chaine.charAt(c); c++);
	    		
	    		/*
	    		 * Si le mot interdit est présent en début de chaine
	    		 * On s'assure qu'il n'est pas suivis du caractère '.'
	    		 */
	    		if(c == nomInterdit[i].length() && nomInterdit[i].charAt(c-1) == chaine.charAt(c-1)) {
	    			if(chaine.charAt(nomInterdit[i].length()) == '.') {
	    				chaineCorrect = false;
	    			}
	    		}
	    		
	    	/*
	    	 * On s'assure que la chaine est différent du mot interdit
	    	 */
    		} else if (chaine.compareTo(nomInterdit[i]) == 0) {
    			chaineCorrect=false;
    		}
    	}
    	
    	/*
    	 * On vérifie que tous les caractères de la chaine
    	 * sont différents des caractères interdits
    	 */
    	for(int c = 0; c < chaine.length() && chaineCorrect; c++) {
    		for(int i=0; i < caractereInterdit.length; i++) {
    			if (caractereInterdit[i] == chaine.charAt(c)) {
    				chaineCorrect = false;
    			}
    		}
    	}
    	
    	return chaineCorrect;
    }
    	
    /**
     * Méthode permettant de tester si le <code>chemin</code> est possible.<br>
     * Si le chemin comporte un / en premier caractère, cela indique
     * que le chemin commence à la racine.
     * Le chemin se représente comme ceci : "[/]( (a-z)(a-z)* / )*(a-z)" + ""<br>
     * <lu><li>/chemin/ est possible et sera remplacé par /chemin</li>
     *     <li>/ est possible</li>
     *     <li>null est possible</li>
     *     <li>chemin est possible</li>
     *     <li>chemin//dir/ est impossible et sera remplacé par chemin/dir</li>
     *     </ul>
     * @param chemin Contient le nom du <code>chemin</code> a analyser
     * @return TRUE si le <code>chemin</code> peut être utilisé
     */
    private boolean setChemin(String chemin) {
        boolean estCorrect = true;
        
    	if (chemin == null) {
    		chemin = "";
    	} else if (chemin != "/") {
    		
    		String[] dossier=chemin.split("/");    	
    		
    	    /*
    	     *  On enlève ici les / en trop
    	     *  toute chaine vide entre un / et un autre
    	     *  se voit supprimé.
    	     *  Si la première chaine est vide,
    	     *  cela indique que le chemin commencera
    	     *  à la racine.
    	     */
    	    chemin = dossier[0];
    	    if(dossier[0] != null && dossier[0] != "") {
        		estCorrect = estCorrect(dossier[0]);
        	}
    	    
    	    /*
    	     * On supprimer les dossiers null ou vide
    	     * Et on test si le nom du dossier est
    	     * correct
    	     */
            for(int i = 1; i < dossier.length && estCorrect;i++) {
            	if(dossier[i] != null && dossier[i] != "") {
            		chemin += "/" + dossier[i];
            		estCorrect = estCorrect(dossier[i]);
            	}
            }
        }
    	
    	if(estCorrect) {
    		this.chemin = chemin;
    		System.out.print("Chemin : " + chemin + ", ");
    	}
        return estCorrect;
    }
    
    /**
     * Méthode permettant de tester si le <code>nom</code> du <code>fichier</code> est possible.<br>
     * Un fichier comporte au moins 1 caractère. Il peut ou non être
     * suivis d'une extention. (exemple : .txt). Cependant certains
     * caractères sont interdits.
     * @param fichier Contient le nom du fichier à analyser
     * @return TRUE si le nom du fichier peut être utilisé
     */
    private boolean setNom(String fichier) {
    	if (fichier == null) {
    		return false;
    	} else {
    		if (estCorrect(fichier)) {
                this.nom = fichier;
                System.out.print("Fichier : " + fichier + ", ");
    	    } else {
    		    return false;
    	    }
    	}
        return true;
    }
    
    /**
     *  Accesseur sur le <code>chemin</code>
     *  @return le chemin menant au fichier
     */
    public String getChemin() {
        return this.chemin;
    }
    
    /**
     *  Accesseur sur le <code>nom</code>
     *  @return le nom du fichier
     */
    public String getNom() {
        return this.nom;
    }
    
    /**
     * Ajout d'un <code>message</code> dans le fichier
     * puis ajout d'une nouvelle ligne.<br>
     * Cette méthode fait appel au flux de sortie.
     * Tout caractère '\r' ou '\n' créé un retour à la ligne.
     * @param message Contient le message à écrire dans le fichier
     */
    public void ecrire(String message) {
    	/*
    	 * TODO
    	 * Tout caractère '\r' ou '\n' créé un retour à la ligne ?!
    	 */
    	if(message != null) {
            try {
	            this.ecriture.write(message);
	            this.ecriture.newLine();
	            this.ecriture.flush();
	        } catch(IOException e) {
	        }
    	}
    }
    
    /**
     * Ajout d'un <code>message</code> dans le fichier à la ligne 
     * <code>indice</code>.<br>
     * Cette méthode fait appel au flux de sortie et à un flux d'entrée.
     * Chaque ligne du fichier est mis en mémoire, puis on les
     * écris une à une dans le nouveau fichier jusqu'à atteindre
     * l'indice. Si l'indice n'est pas atteint et que toutes les lignes
     * ont été réécrite. On place le message à la fin du fichier.
     * Tout caractère '\r' ou '\n' créé un retour à la ligne.
     * La première ligne a pour indice 0
     * @param message Contient le message à écrire dans le fichier.
     * @param indice  Contient l'indice de la ligne où écrire le message.
     */
    public void ecrire(String message, int indice) {
    	
        /*
         * On garde en mémoire le contenu du fichier
         * avant d'executer la suppression de la ligne
         */
        String[] contenuAvant = lectureDesLignes();
        
        try {
        	/*
             * On remet à zero le fichier avant de commencer
             * l'écriture
             */
        	initWriter(false);
        
            /*
             * On remet toutes les lignes en mémoire dans
             * le fichier et lorsque l'index vaut l'indice
             * de la ligne correspondant au message
             * à écrire, on écrit le message.
             */
            for(int i = 0; i < contenuAvant.length; i++) {
            	if(indice == i) {
            		ecrire(message);
            	}
                ecrire(contenuAvant[i]);
            }
        } catch (IOException e) {
        }
    }
    
    /**
     * Ajout d'un <code>message</code> dans le fichier à la ligne 
     * <code>indice</code>.<br>
     * Cette méthode fait appel au flux de sortie et à un flux d'entrée.
     * Chaque ligne du fichier est mis en mémoire, puis on les
     * écris une à une dans le nouveau <code>Fichier</code> jusqu'à atteindre
     * l'<code>indice</code>. Si l'<code>indice</code> n'est pas atteint
     * et que toutes les lignes ont été réécrite. On place le 
     * <code>message</code> à la fin du fichier.
     * Tout caractère '\r' ou '\n' créé un retour à la ligne.
     * La première ligne a pour <code>indice</code> 0
     * @param message Contient le <code>message</code> à écrire à l'indice <code>indice</code>.
     * @param indice  Contient l'<code>indice</code> de la ligne à remplacer.
     */
    public void remplacerLigne(String message, int indice) {
    	
        /*
         * On garde en mémoire le contenu du fichier
         * avant d'executer la suppression de la ligne
         */
        String[] contenuAvant = lectureDesLignes();
        
        try {
        	/*
             * On remet à zero le fichier avant de commencer
             * l'écriture
             */
        	initWriter(false);
        
            /*
             * On remet toutes les lignes en mémoire dans
             * le fichier sauf celle qui doit être
             * remplacé
             */
            for(int i = 0; i < contenuAvant.length; i++) {
            	if(indice == i) {
            		ecrire(message);
            	} else {
            		ecrire(contenuAvant[i]);
            	}
            	
            }
        } catch (IOException e) {
        }
    }
    
    /**
     * Retourne le contenu du fichier.
     * Ce contenu est sous la forme d'un tableau de <code>String</code>.
     * Chaque index du tableau contient une ligne du fichier.
     * La première ligne du fichier est l'indice 0 du tableau.
     * La fin du ligne se fait lors de la rencontre de '\n' ou '\r'
     * On retourne un tableau vide (Taille : 0) si le fichier est vide.
     * Exemple : <ul>
     *               <li>tableauLigne[0] => "Première ligne du fichier"</li>
     *               <li>tableauLigne[1] => "Seconde ligne du fichier"</li>
     *           </ul>
     * @return Le tableau contenant chaque ligne du fichier
     */
    public String[] lectureDesLignes() {
    	
    	/*
    	 * C'est la taille du tableau
    	 * Contenant à chaque index une ligne
    	 * du fichier
    	 */
    	int nbLigne = nbLigne();
    	
    	/*
    	 * Tableau contenant a chaque index
    	 * une ligne du fichier
    	 */
    	String[] ligne;
    	
    	/*
    	 * Contient le contenu de la ligne i
    	 */
    	String contenu;
    	
    	/*
    	 * Index du tableau de ligne
    	 */
    	int i;
    	
    	/*
    	 * On s'assure que le fichier comporte
    	 * au moins une ligne.
    	 */
    	if (nbLigne > 0) {
    		
    		/*
    		 * On initialise le tableau
    		 */
	    	ligne = new String[nbLigne];
	    	
	    	/*
	    	 * On initialise l'index
	    	 */
	    	i = 0;
	    	
	    	try {
	    		
	    		/*
	    		 * On réinitialise le flux d'entrée
	    		 */
	    		initReader();
	    		
	    		/*
	    		 * On mets chaque ligne du fichier dans le tableau
	    		 * tant que la ligne ne vaut pas null
	    		 */
	        	while ((contenu = this.lecture.readLine()) != null) {
	        		ligne[i] = contenu;
	    			i++;
	    		}
	    	} catch (IOException e) {
	    		ligne = null;
	    	}
    	} else {
    		ligne = new String[0];
    	}
    	return ligne;
    }
    
    /**
     * Retourne un objet de type <code>String</code>.
     * Exemple : iut/java/nomdufichier.ext
     * @return le chemin suivis du nom du fichier
     */
    public String toString() {
    	String aRetourner;
        if (this.chemin != "") {
        	aRetourner = this.chemin + "/" + this.nom;
	   	} else {
	   		aRetourner = this.nom;
	   	}
        return aRetourner;
    }
    
    
    /**
     * Retourne le nombre de ligne que contient le fichier.<br>
     * Une ligne se termine par "\n" ou "\r".<br>
     * Cette methode fais appel au flux d'entrée.
     * Si une erreur parvient, on renvoie -1.
     * @return le nombre de ligne
     */
    public int nbLigne() {
    	int nbLigne = 0;
    	try {
    		/*
    		 * On réinitialise le flux d'entrée
    		 */
    		initReader();
    		
    		/*
    		 * On incrémente nbLigne tant que
    		 * readLine est différent de null
    		 */
        	while (this.lecture.readLine() != null) {
    			nbLigne++;
    		}
    	} catch (IOException e) {
    		nbLigne = -1;
    	}
        return nbLigne;
    }
}
