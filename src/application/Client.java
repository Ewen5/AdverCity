package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class Client implements Serializable {
	
	private static final long serialVersionUID = 6374419480282586745L;
	private String libelle;
	private Integer numclient;
	
	private ArrayList<Location> locEnCours;
	private ArrayList<Location> locTotal;
	
	private static Integer compteur = 0;
	
	/**
	 * Créer une instance Client, son numéro de client numclient est généré automatiquement
	 * @param libelle, un String
	 */
	public Client(String libelle) {
		locEnCours = new ArrayList<Location>();
		locTotal = new ArrayList<Location>();
		
		numclient=compteur;
		compteur++;
		this.libelle=libelle;
	}
	
	/**
	 * Créer une instance Client
	 * @param id, un Integer
	 * @param libelle, un String
	 */
	public Client(Integer id,String libelle) {
		locEnCours = new ArrayList<Location>();
		locTotal = new ArrayList<Location>();
		
		numclient=id;
		this.libelle=libelle;
	}
	
	/**
	 * Ajoute une Location à un Client, 
	 * si la date de fin de la Location n'est pas terminée alors elle est ajoutée à la liste des Locations en cours
	 * sinon elle est ajoutée à la liste des Locations totales
	 * @param loc, une Location
	 */
	public void ajouterLocation(Location loc) {
		locEnCours.add(loc);
		locTotal.add(loc);
	}
	
	/**
	 * Supprime la Location de la liste des Locations en cours
	 * @param loc, une Location
	 */
	public void locationTerminee(Location loc) {
		locEnCours.remove(loc);
	}
	
	public Integer getNbTotalLoc() {
		return locTotal.size();
	}
	
	public Integer getNbLocEnCours() {
		return locEnCours.size();
	}
	
	/**
	 * Permet de modifier une Location dans les ArrayList LocEnCours et LocTotal
	 * @param loc, une Location
	 */
	public void modifierLocation(Location loc) {
		int i=0;
		boolean trouve = false;
		while (i<locEnCours.size() && !trouve) {
			if (locEnCours.get(i).getIdentifiant()==loc.getIdentifiant()) {
				trouve=true;
				locTotal.set(locTotal.indexOf(locEnCours.get(i)),loc);
				locEnCours.set(i, loc);
			}
			else {
				i++;
			}
		}
	}
	
	/**
	 * Permet de supprimer une Location dans les ArrayList LocEnCours et LocTotal
	 * @param loc
	 */
	public void supprimerLocation(Location loc) {
		locEnCours.remove(loc);
		locTotal.remove(loc);
	}
	
	/**
	 * Permet d'actualiser les Location en cours. 
	 * On vérifie que la date de fin est avant la date d'aujourd'hui pour chaque Location
	 * si c'est le cas on enleve la Location de la liste de Location en cours.
	 */
	public void actualiserLocationsEnCours() {
		for (int i =0;i<locTotal.size();i++) {
			if (locTotal.get(i).getFinLoc().before(Calendar.getInstance().getTime()) && locEnCours.contains(locTotal.get(i))) {
				this.locationTerminee(locTotal.get(i));
			}
		}
	}
	
	/**
	 * @return un Double qui est le montant total à payer par rapport aux locations en cours d'un client
	 */
	public Double getTotalAPayerLocEnCours() {
		Double total=0.0;
		for (int i=0; i<this.getNbLocEnCours();i++) {
			total=total+locEnCours.get(i).getPanneau().getTarifD();
		}
		return total;
	}
	
	public String getTotalAPayerLocEnCoursString() {
		return this.getTotalAPayerLocEnCours()+"";
	}
	
	public String getLibelle() {
		return libelle;
	}

	public Integer getNumclient() {
		return numclient;
	}

	
	public String toString() {
		return libelle;
	}
	
	public static Integer getCompteur() {
		return compteur;
	}
	
	public void modifierLibelle(String nouveauLibelle) {
		libelle=nouveauLibelle;
	}
	
	/**
	 * @return une ArrayList de Panneau appelée panneaux
	 */
	public ArrayList<Panneau> panneaux() {
		ArrayList<Panneau> panneaux = new ArrayList<Panneau>();
		for (int i=0;i<locTotal.size();i++) {
			if (!panneaux.contains(locTotal.get(i).getPanneauLoc())) {
				panneaux.add(locTotal.get(i).getPanneauLoc());
			}
		}
		return panneaux;
	}
	public ArrayList<Location> getLocEnCours() {
		return locEnCours;
	}

	public void setLocEnCours(ArrayList<Location> locEnCours) {
		this.locEnCours = locEnCours;
	}

	public ArrayList<Location> getLocTotal() {
		return locTotal;
	}

	public void setLocTotal(ArrayList<Location> locTotal) {
		this.locTotal = locTotal;
	}

	public boolean equals(Integer numclient) {
		return this.numclient==numclient;
	}
	
}
