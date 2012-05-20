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
     * Déclaration d'un objet de type Jagenda contenant le jagenda à utiliser
     */
    public static Jagenda jagenda;
    
    /**
     * Lancement du programme jagenda.
     * On instance dans un premier temps un objet de type Jagenda. Celui-ci
     * se place dans un état par défaut en chargeant sa configuration, sa liste
     * de rendez-vous ainsi qu'un objet de type Saisie permettant la saisie
     * de données sur l'entrée/sortie standard.
     * @param args non utilisé
     */
    public static void main(String[] args) {
        jagenda = new Jagenda();
        jagenda.lance();
    }
}
