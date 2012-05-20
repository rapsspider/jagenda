/*
 * Fichier :    TestRendezVous.java
 * Package :    jagenda
 * Projet jAgenda - Info 1 2011-2012
 *
 */
package jagenda.tests;

import jagenda.Horaire;
import jagenda.RendezVous;


/**
 * Classe de test de la classe <code>RendezVous</code>
 * 
 * @author  Jason BOURLARD
 * @author  David PELISSIER
 * @version 0.3
 */
public class TestRendezVous {

    /**
     * Batterie de tests pour la classe {@code RendezVous}.
     * @param args    Non utilisé
     */
    public static void test2() {
        
        // Tests d'instanciantion : constructeur
        String[] testsLibelle = {" ", null, "Libellé", "Libellé trop long"};
        String[] testsDescription = {" ", null, "Description à la con.", 
                "Quisque congue sagittis dapibus. Nam consectetur erat nec " +
                "magna lobortis eget eleifend diam euismod. Sed venenatis " +
                "hendrerit iaculis. Etiam sed magna sit amet magna sagittis " +
                "fringilla nec in mi. Duis viverra nisl at urna accumsan " +
                "pellentesque. Praesent mi libero, molestie ut varius in, " +
                "pretium vel ipsum. Ut molestie sapien id metus commodo " +
                "tincidunt ac vitae purus. Ut dignissim lobortis nisi amet. "};
        int[] testsNature = {0, 1, 2, 3};
        String[] testsDate = {null, " ", "12/02", "32/01"};
        String[] testsHeureDebut = {null, " ", "11:32", "34:89"};
        String[] testsHeureFin = {null, " ", "11:32", "34:89"};   
        
        RendezVous[] testsRdv = new RendezVous[testsLibelle.length];
        
        for (int i=0; i < testsLibelle.length; i++) {
            testsRdv[i] = new RendezVous(testsLibelle[i], testsDescription[i],
                    testsNature[i], testsDate[i], testsHeureDebut[i],
                    testsHeureFin[i]);
        }

        for (int i=0; i < testsRdv.length; i++) {
            System.out.println("Libellé : \t" + testsRdv[i].getLibelle());
            System.out.println("Description : \t" + testsRdv[i].getDescription());
            System.out.println("Nature : \t"+ testsRdv[i].getNature());
            System.out.println("Horaire début : " +
                    testsRdv[i].getHoraireDebut());
            System.out.println("Horaire fin : \t" +
                        testsRdv[i].getHoraireFin());
            System.out.println();
        }
    }
        
    // TODO Finir les jeux de test
    
    
    /**
     * 
     */
    public static void chargeRDV() {
        RendezVous[] liste = RendezVous.listeRendezVous();
        
        if(liste != null && liste.length != 0) {
            for(int i=0; i < liste.length; i++) {
                System.out.println("*****************************************************************************************");
                System.out.println("Libelle : \t\t"+ liste[i].getLibelle());
                System.out.println("Description : \t\t"+ liste[i].getDescription());
                System.out.println("Nature : \t\t"+ liste[i].getNature());
                System.out.println("Horaire de début : \t"+ liste[i].getHoraireDebut());
                System.out.println("Horaire de fin : \t"+ liste[i].getHoraireFin());
            }
        } else {
            System.out.println("Aucun rendez-vous");
        }
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        RendezVous[] liste = RendezVous.listeRendezVous();
        RendezVous aComparer = new RendezVous("AComparer", "Rencontre conflit",
                                              1,"10/01/2012", "14h29", "14h32");
        RendezVous[] conflit = RendezVous.conflitRDV(liste, aComparer);
        
        afficherListeRDV(liste, "");
        afficherListeRDV(conflit, "conflit : ");
    }
    
    /**
     * Afficher les rendezVous
     * @param listeRDV à afficher
     * @param prefix   à afficher avant le contenue de chaque rendez-vous
     */
    public static void afficherListeRDV(RendezVous[] listeRDV, String prefix) {
        for(int i = 0; i < listeRDV.length; i++) {
            if(prefix != null) {
                System.out.print(prefix);
            }
            System.out.println(listeRDV[i].getLibelle());
        }
    }
    
    /**
     * 
     */
    public static void compareToRDV() {
        RendezVous[] table = {
                new RendezVous("One", "One", 1,"10/01/2012", "12h00", "13h00"),
                new RendezVous("Two", "Two", 2,"10/01/2012", "13h00", "14h00"),
                new RendezVous("Thr", "Thr", 3,"10/01/2012", "12h30", "13h30"),
                new RendezVous("Fou", "Fou", 4,"10/01/2012", "12h00", "13h30"),
                new RendezVous("Fiv", "Fiv", 5,"10/01/2012", "12h00", "14h30"),
                new RendezVous("Six", "Six", 6,"10/01/2012", "12h00", "13h00")
                };
        
        for(int i = 0; i < table.length; i++) {
            for(int j = i; j < table.length; j++) {
                System.out.println(table[i]);
                System.out.println(table[j]);
                System.out.println("Resultat de la comparaison avec la méthode" +
                		"compareTo() \t" + table[i].compareTo(table[j]) + 
                		"\n\n");
            }
        }
    }
    
    /**
     * 
     */
/*    public static void testGetterSetter() {
        
        // Test d'instanciation : constructeur par défaut
        RendezVous medecin = null;

        // Test d'utilisation des accesseurs
        System.out.println("\nTest d'utilisation des accesseurs");
        System.out.println("*****************************************************************************************");
        System.out.println("Libelle : \t\t"+ medecin.getDescription());
        System.out.println("Description : \t\t"+ medecin.getLibelle());
        System.out.println("Nature : \t\t"+ medecin.getNature());
        System.out.println("Horaire de début : \t"+ RendezVous.affichHoraire(medecin.getHoraireDebut()));
        System.out.println("Horaire de fin : \t"+ RendezVous.affichHoraire(medecin.getHoraireFin()));
                
        // Test d'utilisation des accesseurs
        System.out.println("\nTest d'utilisation des mutateurs");
        System.out.println("*****************************************************************************************");
        
        medecin.setLibelle("Médecin");
        medecin.setDescription("Consultation chez le Dr BIDULE afin de me faire prescrire un arrêt de travail.");
        medecin.setNature("Personnel");
        medecin.setHoraireDebut("24/05/2011", "14:10");            // Format jj/mm/aaaa hh:mm
        medecin.setHoraireFin("mERCredi", 17, 2012);            // Format jour, semaine, annee
        
        System.out.println("Libelle : \t\t"+ medecin.getDescription());
        System.out.println("Description : \t\t"+ medecin.getLibelle());
        System.out.println("Nature : \t\t"+ medecin.getNature());
        System.out.println("Horaire de début : \t"+ RendezVous.affichHoraire(medecin.getHoraireDebut()));
        System.out.println("Horaire de fin : \t"+ RendezVous.affichHoraire(medecin.getHoraireFin()));
        
        // Test de méthodes de vérification
        System.out.println("\nTests de vérification d'horaires");
        System.out.println("*****************************************************************************************");
        
        String date = "ml;mp:polm;";
        String heure = "22:10";
        if(Horaire.estValide(date, heure)) {
            System.out.println(date +" "+ heure +" : \tValide");
        } else {
            System.out.println(date +" "+ heure +" : \tNon valide");        
        }
        
        String jour = "lsdfcxdi";
        int semaine = 11;
        if(Horaire.estValide(jour, semaine, 2012)) {
            System.out.println(jour +" de la semaine "+ semaine +" : \tValide");
        } else {
            System.out.println(jour +" de la semaine "+ semaine +" : \tNon valide");        
        }
        
        System.out.println("\nTests de comparaison d'horaires");
        System.out.println("*****************************************************************************************");
        if (medecin.getHoraireDebut().compareTo(medecin.getHoraireFin()) == 0) {
            System.out.println("Les horaires sont identiques.");
        } else if (medecin.getHoraireDebut().compareTo(medecin.getHoraireFin()) > 0) {
            System.out.println(RendezVous.affichHoraire(medecin.getHoraireDebut()) +" est postérieure à "
                             + RendezVous.affichHoraire(medecin.getHoraireFin()));
        } else {
            System.out.println(RendezVous.affichHoraire(medecin.getHoraireDebut()) +" est antérieure à "
                             + RendezVous.affichHoraire(medecin.getHoraireFin()));
        }
    }*/
}
