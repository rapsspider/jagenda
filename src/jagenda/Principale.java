package jagenda;

/*
 * Principale.java
 * jagenda2                                   15 mai 2012
 */

/**
 * 
 * @author jason BOURLARD
 */
public class Principale {
    
    /**
     * D�claration d'un objet de type Jagenda contenant le jagenda � utiliser
     */
    public static Jagenda jagenda;
    
    /**
     * Lancement du programme jagenda.
     * On instance dans un premier temps un objet de type Jagenda. Celui-ci
     * se place dans un �tat par d�faut en chargeant sa configuration, sa liste
     * de rendez-vous ainsi qu'un objet de type Saisie permettant la saisie
     * de donn�es sur l'entr�e/sortie standard.
     * @param args non utilis�
     */
    public static void main(String[] args) {
        jagenda = new Jagenda();
        jagenda.lance();
    }
}
