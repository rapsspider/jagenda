/*
 * Fichier :	RendezVous.java
 * Package :	jagenda
 * Projet jAgenda - Info 1 2011-2012
 *
 * $Revision$
 * $Date$
 * $HeadURL$
 */
package jagenda;

import jagenda.Horaire;
import jagenda.Date;

/**
 * Classe <code>RendezVous</code> représente un rendez-vous
 * possible dans l'agenda.
 * 
 * @author Jason BOURLARD
 * @author David PELISSIER
 */
public class RendezVous {

	/**
	 * Libellé concis du rendez-vous, limité à 8 caractères.
	 * Apparaitra lors d'un affichage abrégé.
	 */
	protected String libelle;
	
	/**
	 * Libellé complet du rendez-vous, d'au plus 5 lignes.
	 * Apparaitra lors d'un affichage détaillé.
	 */
	protected String description;

	/**
	 * Précision sur la nature du rendez-vous (professionnel
	 * ou personnel).
	 */
	protected String nature;
	
	/**
	 * Horaire de début du rendez-vous (en h:min).
	 */
	protected Horaire heureDebut;
	
	/**
	 * Horaire de fin du rendez-vous (en h:min).
	 */
	protected Horaire heureFin;
	
	/**
	 * Jour de planification du rendez-vous.
	 */
	protected Date date;
	
	/**
	 * Constructeur par défaut.
	 */
	public RendezVous() {
		libelle = "Libellé par defaut";
		description = "Description par défaut";
		nature = "Inconnu";
		heureDebut = new Horaire(0, 0);
		heureFin = new Horaire(0, 0);
		
		System.out.println("Rendez-vous créé.");
	}

	/**
	 * Accesseur pour <code>libelle</code>
	 * @return 	Libelle du rendez-vous.
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * Accesseur pour <code>description</code>
	 * @return 	Description du rendez-vous.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Accesseur pour <code>nature</code>
	 * @return 	Nature du rendez-vous.
	 */
	public String getNature() {
		return nature;
	}
	
	/**
	 * Accesseur pour <code>heureDebut</code>
	 * @return Heure de début du rendez-vous
	 */
	public String getHeureDebut() {
		return heureDebut.affichHoraire();
	}

	/**
	 * Accesseur pour <code>heureFin</code>
	 * @return Heure de fin du rendez-vous
	 */
	public String getHeureFin() {
		return heureFin.affichHoraire();
	}

	/**
	 * Mutateur pour le <code>libelle</code> d'un 
	 * rendez-vous.
	 * @param libelle	Nouveau libelle (au plus 8 caractères)
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * Mutateur pour la <code>description</code> d'un 
	 * rendez-vous.
	 * @param description	Nouvelle description (au plus 5 lignes)
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Mutateur pour la <code>nature</code> d'un 
	 * rendez-vous.
	 * @param nature	Nouvelle nature (personnel ou professionel)
	 */
	public void setNature(String nature) {
		this.nature = nature;
	}

	/**
	 * Mutateur pour l'<code>heureDebut</code> d'un 
	 * rendez-vous.
	 * @param heures	Nouvelles heures de début (entre 0-23)
	 * @param minutes	Nouvelles minutes de début (entre 0-59)
	 */
	public void setHeureDebut(int heures, int minutes) {
		this.heureDebut.setHeures(heures);
		this.heureDebut.setMinutes(minutes);	
	}

	/**
	 * Mutateur pour l'<code>heureFin</code> d'un 
	 * rendez-vous.
	 * @param heures	Nouvelles heures de fin (entre 0-23)
	 * @param minutes	Nouvelles minutes de fin (entre 0-59)
	 */
	public void setHeureFin(int heures, int minutes) {
		this.heureFin.setHeures(heures);
		this.heureFin.setMinutes(minutes);		
	}

}
