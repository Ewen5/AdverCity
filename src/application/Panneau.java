package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Panneau implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8242532374175285692L;
	private Integer numEmplacement;
	private double tarif;
	private ArrayList<Location> planning;
	private Integer nbEtoiles;
	private String louant;
	
	private Paire coordonnees;
	
	private double formatLongueur;
	private double formatLargeur;
	private Zone zone;
	
	private static Integer compteur=0;
	
	public Panneau(Paire coordonnes,double tarif, Integer nbEtoiles,double formatLongueur, double formatLargeur, Zone zone) {
		this.coordonnees=coordonnes;
		this.formatLongueur=formatLongueur;
		this.formatLargeur=formatLargeur;
		this.zone=zone;
		this.louant="Aucun louant";
		this.tarif=tarif;
		this.nbEtoiles=nbEtoiles;
		numEmplacement=compteur;
		compteur++;
		planning = new ArrayList<Location>();
	}
	
	public Panneau(double tarif, Integer nbEtoiles,double formatLongueur, double formatLargeur, Zone zone) {
		this.coordonnees=null;
		this.formatLongueur=formatLongueur;
		this.formatLargeur=formatLargeur;
		this.zone=zone;
		this.louant="Aucun louant";
		this.tarif=tarif;
		this.nbEtoiles=nbEtoiles;
		numEmplacement=compteur;
		compteur++;
		planning = new ArrayList<Location>();
	}
	
	public Panneau(Integer id,double tarif, Integer nbEtoiles,double formatLongueur, double formatLargeur, Zone zone) {
		this.coordonnees=null;
		this.formatLongueur=formatLongueur;
		this.formatLargeur=formatLargeur;
		this.zone=zone;
		this.louant="Aucun louant";
		this.tarif=tarif;
		this.nbEtoiles=nbEtoiles;
		numEmplacement=id;
		planning = new ArrayList<Location>();
	}
	
	public Panneau(Paire coordonnees,Integer id,double tarif, Integer nbEtoiles,double formatLongueur, double formatLargeur, Zone zone) {
		this.coordonnees=coordonnees;
		this.formatLongueur=formatLongueur;
		this.formatLargeur=formatLargeur;
		this.zone=zone;
		this.louant="Aucun louant";
		this.tarif=tarif;
		this.nbEtoiles=nbEtoiles;
		numEmplacement=id;
		planning = new ArrayList<Location>();
	}
	
	//Gestion des locations
	/**
	 * Permet d'ajouter une Location
	 * @param loc, une Location
	 */
	public void ajouterLocation(Location loc) {
		planning.add(loc);
	}
	
	/**
	 * permet de supprimer une Location passé en paramètre
	 * @param loc, une Location
	 */
	public void supprimerLocation(Location loc) {
		planning.remove(loc);
	}
	
	/**
	 * Permet de modifier une Location passé en paramètre
	 * @param loc, une Location
	 */
	public void modifierLocation(Location loc) {
		int i=0;
		boolean trouve = false;
		while (i<planning.size() && !trouve) {
			if (planning.get(i).getIdentifiant()==loc.getIdentifiant()) {
				trouve=true;
				planning.set(i, loc);
			}
			else {
				i++;
			}
		}
	}
	
	public void setPlanning(ArrayList<Location> loc) {
		planning = loc;
	}
	
	/**
	 * Permet de vérifier si les dates ne se chevauchent pas
	 * @param dateDebut, une Date
	 * @param dateFin, une Date
	 * @return un booléen vrai si les dates se chevauchent
	 */
	public boolean datesChevauchent(Date dateDebut, Date dateFin) {
		boolean chevauchent=false;
		int i=0;
		while (i<planning.size() && !chevauchent) {
			if (planning.get(i).getDebutLoc().after(dateFin) || planning.get(i).getFinLoc().before(dateDebut)) {
				i++;
			}
			else {
				chevauchent=true;
			}
		}
		return chevauchent;
	}
	
	/**
	 * Permet de vérifier si les dates ne se chevauchent pas sur une Location
	 * @param dateDebut, une Date
	 * @param dateFin, une Date
	 * @param loc, une Location
	 * @return un booléen vrai si les dates se chevauchent
	 */
	public boolean datesChevauchent(Date dateDebut, Date dateFin, Location loc) {
		boolean chevauchent=false;
		int i=0;
		while (i<planning.size() && !chevauchent) {
			if ((planning.get(i).getDebutLoc().after(dateFin) || planning.get(i).getFinLoc().before(dateDebut)) || planning.get(i).equals(loc.getIdentifiant())) {
				i++;
			}
			else {
				chevauchent=true;
			}
		}
		return chevauchent;
	}

	public Paire getCoordonnees() {
		return coordonnees;
	}

	public Integer getNumEmplacement() {
		return numEmplacement;
	}

	public Integer getNbEtoiles() {
		return nbEtoiles;
	}
	
	public String getLouant() {
		return louant;
	}

	public ArrayList<Location> getPlanning() {
		return planning;
	}

	public Double getFormatLongueur() {
		return formatLongueur;
	}

	public Double getFormatLargeur() {
		return formatLargeur;
	}

	public String getTarif() {
		return tarif+"/mois";
	}
	
	public Double getTarifD() {
		return tarif;
	}
	
	public String getFormat() {
		return formatLongueur+"x"+formatLargeur+"cm";
	}
	
	public String getZoneString() {
		return zone.toString();
	}
	
	
	
	//get zone retourne un string car l'affichage de la tableview se base sur les getters
	//En substitution, on a un avoirZone
	
	public void setTarif(double tarif) {
		this.tarif = tarif;
	}

	public void setNbEtoiles(Integer nbEtoiles) {
		this.nbEtoiles = nbEtoiles;
	}

	public void setFormatLongueur(double formatLongueur) {
		this.formatLongueur = formatLongueur;
	}

	public void setFormatLargeur(double formatLargeur) {
		this.formatLargeur = formatLargeur;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public Zone getZone() {
		return zone;
	}
	
	public void changerZone(Zone zone) {
		this.zone=zone;
	}
	
	public String toString() {
		return "Panneau N�"+numEmplacement;
	}
	
	public static Integer getCompteur() {
		return compteur;
	}
	
	/**
	 * 
	 * @return une ArrayList<Client> qui loue un Panneau
	 */
	public ArrayList<Client> louants() {
		ArrayList<Client> louants = new ArrayList<Client>();
		for (int i=0;i<planning.size();i++) {
			if (!louants.contains(planning.get(i).getClient())) {
				louants.add(planning.get(i).getClient());
			}
		}
		return louants;
	}
	
	public void setCoordonnes(Integer x, Integer y) {
		this.coordonnees.setCoordonnees(x, y);
	}
	
	public void setCoordonnes(Paire p) {
		this.coordonnees=p;
	}
	
	public boolean equals(Integer id) {
		return this.numEmplacement==id;
	}
}
