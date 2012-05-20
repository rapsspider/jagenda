/*
 * Fichier :	TestRendezVous.java
 * Package :	jagenda
 * Projet jAgenda - Info 1 2011-2012
 *
 * $Revision$
 * $Date$
 * $HeadURL$
 */
package jagenda;

import jagenda.RendezVous;

/**
 * Classe de test de la classe <code>RendezVous</code> :
 * <ul><li>Instanciation des objets,</li>
 *     <li>Test de comportement</li></ul>
 * 
 * @author Jason BOURLARD
 * @author David PELISSIER
 */
public class TestRendezVous {

	/**
	 * Méthode d'exécution des tests
	 * @param args	Non utilisé
	 */
	public static void main(String[] args) {
		
		// Test d'instanciation : constructeur par défaut
		RendezVous medecin = new RendezVous();
		
		// Test d'utilisation des accesseurs
		System.out.println("Libellé : \t"+ medecin.getLibelle());
		System.out.println("Description : \t"+ medecin.getDescription());
		System.out.println("Nature : \t"+ medecin.getNature());
		System.out.println("Heure début : \t"+ medecin.getHeureDebut());
		System.out.println("Heure fin : \t"+ medecin.getHeureFin());

		// Test d'utilisation des mutateurs
		medecin.setLibelle("Médecin");
		medecin.setDescription("Consultation chez le Dr BIDULE pour congés maladie.");
		medecin.setNature("Personnel");
		medecin.setHeureDebut(17, 30);
		medecin.setHeureFin(17, 55);
		
		System.out.println("\nModification  \n");
		
		System.out.println("Libellé : \t"+ medecin.getLibelle());
		System.out.println("Description : \t"+ medecin.getDescription());
		System.out.println("Nature : \t"+ medecin.getNature());
		System.out.println("Heure début : \t"+ medecin.getHeureDebut());
		System.out.println("Heure fin : \t"+ medecin.getHeureFin());
	}

}
