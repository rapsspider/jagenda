/*
 * Fichier :    Date.java
 * Package :    jagenda
 * Projet jAgenda - Info 1 2011-2012
 *
 * $Revision$
 * $Date$
 * $HeadURL$
 */
package jagenda;

/**
 * La classe Date repr�sente un jour d'un mois d'une ann�e.
 * Une date est exprim�e au format jj/mm/aaaa.
     * <ul>
     *     <li>Une <code>ann�e</code> est repr�sent�e par un entier compris
     *         <code>AN_MIN</code> et <code>AN_MAX</code>.</li>
     *     <li>Un <code>mois</code> est repr�sent�e par un entier compris entre 1 et 12.</li>
     *     <li>Le <code>jour</code> est repr�sent�e par un entier compris entre 1 et 31.</li>
     * </ul>
 * @author Jason BOURLARD
 * @author David PELISSIER
 * @version 0.1.0
 */
public class Date {
	
	/**
	 * Contient l'ann�e
	 */
	private short annee;
	
	/**
	 * Contient le mois
	 */
	private byte mois;
	
	/**
	 * Contient le jour
	 */
	private byte jour;
	
	/**
	 * Contient l'ann�e minimale accept�e.
	 */
	public final static short AN_MIN = 2012;
	
	/**
	 * Contient l'ann�e maximale accept�e.
	 */
	public final static short AN_MAX = 2012;
	
	/**
	 * Contient le nombre de jour correspondant
	 * au mois dans une ann�e non bissextile.
	 * <ul>
     *     <li>0 => 31 (Janvier)</li>
     *     <li>1 => 28 (Fevrier)</li>
     *     <li>2 => 31 (Mars)</li>
     *     <li>3 => 30 (Avril)</li>
     *     <li>4 => 31 (Mai)</li>
     *     <li>5 => 30 (Juin)</li>
     *     <li>6 => 31 (Juillet)</li>
     *     <li>7 => 31 (Ao�t)</li>
     *     <li>8 => 30 (Septembre)</li>
     *     <li>9 => 31 (Octobre)</li>
     *     <li>10 => 30 (Novembre)</li>
     *     <li>11 => 31 (Decembre)</li>
     * </ul>
	 */
	public final static int[] nombreJour = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	
	/**
     * Constructeur d'un objet <code>Date</code> au
     * format jj/mm/aaaa.<br>
     * <ul>
     *     <li>Une <code>ann�e</code> est repr�sent�e par un entier compris
     *         <code>AN_MIN</code> et <code>AN_MAX</code>.</li>
     *     <li>Un <code>mois</code> est repr�sent�e par un entier compris entre 1 et 12.</li>
     *     <li>Le <code>jour</code> est repr�sent�e par un entier compris entre 1 et 31.</li>
     * </ul>
     * @param date Contient la date au format jj/mm/aaaa
     * @throws Exception <code>Date</code> invalide
     */
    public Date(String date) throws Exception  {
    	if(estUneDate(date)) {
    		this.jour = (byte) (Character.getNumericValue(date.charAt(0)) * 10
		    + Character.getNumericValue(date.charAt(1)));
		    this.mois = (byte) (Character.getNumericValue(date.charAt(3)) * 10
		    + Character.getNumericValue(date.charAt(4)));
		    this.annee = (short) (Character.getNumericValue(date.charAt(6)) * 1000
		    + Character.getNumericValue(date.charAt(7)) * 100
		    + Character.getNumericValue(date.charAt(8)) * 10
		    + Character.getNumericValue(date.charAt(9)));
    	} else {
    		throw new Exception("Date invalide");
    	}
    }
    
	/**
     * Constructeur d'un objet <code>Date</code>.<br>
     * <ul>
     *     <li>Une <code>ann�e</code> est repr�sent�e par un entier compris
     *         <code>AN_MIN</code> et <code>AN_MAX</code>.</li>
     *     <li>Un <code>mois</code> est repr�sent�e par un entier compris entre 1 et 12.</li>
     *     <li>Le <code>jour</code> est repr�sent�e par un entier compris entre 1 et 31.</li>
     * </ul>
     * @param annee  Contient l'<code>ann�e</code>.
     * @param mois   Contient le <code>mois</code>.
     * @param jour   Contient le <code>jour</code>.
     * @throws Exception <code>Date</code> invalide.
     */
    public Date(int annee, int mois, int jour) throws Exception {
    	if(estUneDate(annee, mois, jour)) {
    		this.annee = (short)annee;
    		this.mois = (byte) mois;
    		this.jour = (byte)jour;
    	} else {
    		throw new Exception("Date invalide");
    	}
    }
    
    /**
     * M�thode qui retourne le num�ro du <code>jour</code> correspondant 
     * au premier janvier de l'<code>ann�e</code>.<br>
     * <ul>
     *     <li>0 = dimanche</li>
     *     <li>1 = lundi</li>
     *     <li>2 = mardi</li>
     *     <li>3 = mercredi</li>
     *     <li>4 = jeudi</li>
     *     <li>5 = vendredi</li>
     *     <li>6 = samedi</li>
     * </ul>
     * 
     * Pour une <code>ann�e</code> post�rieure � 1582, la formule est
     * la suivante : <br>
     * A = annee / 100;<br>
     * S = (annee - A) / 100<br>
     * <br>
     * J = ( 5 S + S : 4 + A + A : 4 ) [mod 7]<br>
     * pour toute ann�e non bissextile,<br>
     * et<br>
     * J = ( 5 S + S : 4 + A + A : 4 + 6 ) [mod 7]<br>
     * pour toute ann�e bissextile<br>
     *
     * @param annee Contient l'annee � tester.
     * @return le numero du jour correspondant.
     */
    public static int premierJour(int annee) {
    	int premierJour;
    	
    	annee = annee % 100;
    	int siecle = (annee - annee) / 100;
    	
    	if(isBissextile(annee)) {
    		premierJour =  (5 * siecle + siecle/4 
				       + annee / 4 + annee + 6) % 7;
    	} else {
    		premierJour = (5 * siecle + siecle/4 
				      + annee / 4 + annee) % 7;
    	}
    	
    	return premierJour;
    }
    
    /**
     * M�thode qui retourne le num�ro du <code>jour</code> correspondant 
     * au premier janvier de l'<code>ann�e</code>.<br>
     * <ul>
     *     <li>0 = dimanche</li>
     *     <li>1 = lundi</li>
     *     <li>2 = mardi</li>
     *     <li>3 = mercredi</li>
     *     <li>4 = jeudi</li>
     *     <li>5 = vendredi</li>
     *     <li>6 = samedi</li>
     * </ul>
     * 
     * Pour une <code>ann�e</code> post�rieure � 1582, la formule est
     * la suivante : <br>
     * A = annee / 100;<br>
     * S = (annee - A) / 100<br>
     * <br>
     * J = ( 5 S + S : 4 + A + A : 4 ) [mod 7]<br>
     * pour toute ann�e non bissextile,<br>
     * et<br>
     * J = ( 5 S + S : 4 + A + A : 4 + 6 ) [mod 7]<br>
     * pour toute ann�e bissextile<br>
     *
     * @return le numero du jour correspondant.
     */
    public int premierJour() {
    	return premierJour(this.annee);
    }
    
    /**
     * Accesseur sur le num�ro de la semaine.<br>
     * semaine = ( J + (365 ou 366) + 5 ) : 7 - ( J : 5 )<br>
     * <br>
     * On peut trouver W = 0 dans le cas du ou des premiers
     * jours de l�ann�e lorsque la premi�re semaine (incompl�te)
     * de janvier a 1, 2, ou 3 jours ; ces quelques jours
     * appartiennent alors a la derni�re semaine, num�rot�e
     * 52 ou 53, de l�ann�e pr�c�dente.<br>
     * <br>
     * De m�me, le 29 / 12 / m -si c�est un lundi- ,
     * le 30 / 12 / m -si c�est un lundi ou un mardi-,
     * le 31 / 12 / m -si c�est un lundi, un mardi ou un mercredi-
     * appartienent � la semaine 1 de l�ann�e m + 1.<br>
     * 
     * @return le num�ro de la semaine
     */
    public int getSemaine() {
    	/*
    	 * On recherche le premier janvier
    	 * 0 = dimanche
    	 * 1 = lundi
    	 * ....
    	 * 6 = samedi
    	 */
    	int premierJour = premierJour(this.annee);
    	
    	/*
    	 * On r�cup�re le num�ro correspondant
    	 * au jour
    	 */
    	int numJour = getNumJour();
    	
    	/*
    	 * Retourne le num�ro de la semaine correspondant
    	 * TODO ex : le cas d'un lundi 29 decembre
    	 */
    	return (premierJour + numJour + 5) / 7 - (premierJour / 5);
    }
    
    /**
     * Renvoie vrai si l'ann�e mis en argument est une ann�e
     * bissextile.<br>
     * Une ann�e est bissextile si elle est divisible par 4
     * mais est non divisible par 100, ou bien si elle est
     * divisible par 400.
     * @param annee Contient l'<code>ann�e</code> � tester.
     * @return TRUE si l'<code>ann�e</code> est bissextile
     */
    public static boolean isBissextile(int annee) {
    	return annee % 4 == 0 && annee%100 != 0 || annee%400 == 0;
    }
    
    /**
     * Accesseur sur le <code>mois</code>
     * @return le mois
     */
    public int getMois() {
    	return (int)this.mois;
    }
    
    /**
     * Accesseur sur le <code>mois</code>
     * @return le mois
     */
    public int getAnnee() {
    	return (int)this.annee;
    }
    
    /**
     * Accesseur sur le <code>jour</code>
     * @return le jour
     */
    public int getJour() {
    	return (int)this.jour;
    }
    
    /**
     * Renvoie le nom correspondant au <code>jour</code>.<br>
     * <ul>
     *     <li>0 = dimanche</li>
     *     <li>1 = lundi</li>
     *     <li>2 = mardi</li>
     *     <li>3 = mercredi</li>
     *     <li>4 = jeudi</li>
     *     <li>5 = vendredi</li>
     *     <li>6 = samedi</li>
     * </ul>
     * @return le nom du <code>jour</code>.
     */
    public static String getNomJour(int numJour) {
    	String jour = "Invalide";
    	switch(numJour) {
    	case 0:
    	    jour = "Dimanche";
    	    break;
    	case 1:
    		jour = "Lundi";
    		break;
    	case 2:
    	    jour = "Mardi";
    	    break;
    	case 3:
    		jour = "Mercredi";
    		break;
    	case 4:
    	    jour = "Jeudi";
    	    break;
    	case 5:
    		jour = "Vendredi";
    		break;
    	case 6:
    	    jour = "Samedi";
    	    break;
    	}
    	return jour;
    }
    
    /**
     * Accesseur sur le num�ro du <code>jour</code>
     * dans l'<code>ann�e</code>.<br>
     * Exemple : Le 28/02/2012 = 59.
     * @return le num�ro du <code>jour</code>
     */
    public int getNumJour() {
    	int numJour = 0;
    	boolean bissextile = isBissextile(annee);
    	
    	for(int mois=1; mois < this.mois; 
    			/*
    			 * Il faut faire attention au 29 f�vrier
    			 */
    			numJour += mois == 2 && bissextile ? nombreJour[mois-1]+1 
    					                           : nombreJour[mois-1],
    			mois++);
    	
    	numJour += this.jour;
    	
    	return numJour;
    }
    
    /**
     * Polymorphisme de la classe <code>Objet</code> affichant 
     * la <code>Date</code> au format jj/mm/aaaa.
     * @return la <code>Date</code> au format jj/mm/aaaa.
     */
    public String toString() {
    	return (this.jour > 10 ? this.jour : "0"+ this.jour) 
                + "/" + 
                (this.mois > 10 ? this.mois : "0"+ this.mois)
    	        + "/" + this.annee;
    }
    
    /**
     * M�thode testant la validit� d'une <code>Date</code> au
     * format jj/mm/aaaa.<br>
     * <ul>
     *     <li>Une <code>ann�e</code> est repr�sent�e par un entier compris
     *         <code>AN_MIN</code> et <code>AN_MAX</code>.</li>
     *     <li>Un <code>mois</code> est repr�sent�e par un entier compris entre 1 et 12.</li>
     *     <li>Le <code>jour</code> est repr�sent�e par un entier compris entre 1 et 31.</li>
     * </ul>
     * @param date Contient la date a tester.
     * @return TRUE si <code>date</code> est une <code>date</code> valide;
     */
    public static boolean estUneDate(String date) {
    	/*
    	 * Dans un premier temps on verifie que le nombre
    	 * de caract�re contenu dans le String date est
    	 * 10 et on verifie que les '/' sont � leurs
    	 * emplacements respectifs
    	 */
    	return date.length() == 10 && date.charAt(2) == '/'
    			&& date.charAt(5) == '/'
    			/*
    			 * On verifie que les autres caract�res sont des
    			 * nombres.
    			 */
    			&& Character.isDigit(date.charAt(0))
    			&& Character.isDigit(date.charAt(1))
    			&& Character.isDigit(date.charAt(3))
    			&& Character.isDigit(date.charAt(4))
    			&& Character.isDigit(date.charAt(6))
    			&& Character.isDigit(date.charAt(7))
    	    	&& Character.isDigit(date.charAt(8))
    	    	&& Character.isDigit(date.charAt(9))
    	    	/*
    	    	 * Si c'est le cas, on s'assure que la date est une date
    	    	 * valide.
    	    	 */
    	    	&& estUneDate(Character.getNumericValue(date.charAt(6)) * 1000
	    			          + Character.getNumericValue(date.charAt(7)) * 100
	    			          + Character.getNumericValue(date.charAt(8)) * 10
	    			          + Character.getNumericValue(date.charAt(9)),
    	    			      Character.getNumericValue(date.charAt(3)) * 10
    	    			      + Character.getNumericValue(date.charAt(4)),
    	    			      Character.getNumericValue(date.charAt(0)) * 10
        	    			  + Character.getNumericValue(date.charAt(1)));
    }
    
    /**
     * M�thode testant la validit� d'une <code>Date</code>.<br>
     * <ul>
     *     <li>Une <code>ann�e</code> est repr�sent�e par un entier compris
     *         <code>AN_MIN</code> et <code>AN_MAX</code>.</li>
     *     <li>Un <code>mois</code> est repr�sent�e par un entier compris entre 1 et 12.</li>
     *     <li>Le <code>jour</code> est repr�sent�e par un entier compris entre 1 et 31.</li>
     * </ul>
     * @param annee  Contient l'<code>ann�e</code> � tester.
     * @param mois   Contient le <code>mois</code> � tester.
     * @param jour   Contient le <code>jour</code> � tester.
     * @return TRUE si <code>date</code> est une <code>date</code> valide;
     */
    public static boolean estUneDate(int annee, int mois, int jour) {
    	boolean bissextile = isBissextile(annee);
    	
    	return AN_MIN <= annee && annee <= AN_MAX
    		   && 1 <= mois && mois <= 12 
               && 1 <= jour && ( jour <= nombreJour[mois-1] 
            		             /*
            		              * Sinon on verifie le cas d'une ann�e bissextile
            		              */
                                 || jour == 29 && mois == 2 
                                    && (bissextile));
    }
}
