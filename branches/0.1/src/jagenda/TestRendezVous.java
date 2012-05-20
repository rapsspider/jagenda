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
	 * M�thode d'ex�cution des tests
	 * @param args	Non utilis�
	 */
	public static void main(String[] args) {
		
		// Test d'instanciation : constructeur par d�faut
		RendezVous medecin = new RendezVous();
		
		// Test d'utilisation des accesseurs
		System.out.println("Libell� : \t"+ medecin.getLibelle());
		System.out.println("Description : \t"+ medecin.getDescription());
		System.out.println("Nature : \t"+ medecin.getNature());
		System.out.println("Heure d�but : \t"+ medecin.getHeureDebut());
		System.out.println("Heure fin : \t"+ medecin.getHeureFin());

		// Test d'utilisation des mutateurs
		medecin.setLibelle("M�decin");
		medecin.setDescription("Consultation chez le Dr BIDULE pour cong�s maladie.");
		medecin.setNature("Personnel");
		medecin.setHeureDebut(17, 30);
		medecin.setHeureFin(17, 55);
		
		System.out.println("\nModification  \n");
		
		System.out.println("Libell� : \t"+ medecin.getLibelle());
		System.out.println("Description : \t"+ medecin.getDescription());
		System.out.println("Nature : \t"+ medecin.getNature());
		System.out.println("Heure d�but : \t"+ medecin.getHeureDebut());
		System.out.println("Heure fin : \t"+ medecin.getHeureFin());
	}

}
