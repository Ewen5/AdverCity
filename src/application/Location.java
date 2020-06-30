package application;

import java.io.Serializable;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Location implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4437571914753284931L;
	private Date debutLoc;
	private Date finLoc;
	
	private Panneau panneauLoc;
	private Client clientLoc;
	
	private Integer identifiant;
	
	private static Integer compteur=0;
	
	private DateFormat shortDateFormat;
	
	public Location(Date deb, Date fin, Client client, Panneau panneauLoc) {
		shortDateFormat = DateFormat.getDateInstance();
		
		debutLoc=deb; //il faudra tester que la date de fin est bien aprs la date de dbut
		finLoc=fin;
		clientLoc=client;
		this.panneauLoc=panneauLoc;
		identifiant=compteur;
		compteur++;
	}
	
	public Location(Integer id,Date deb, Date fin, Client client, Panneau panneauLoc) {
		shortDateFormat = DateFormat.getDateInstance();
		
		debutLoc=deb; //il faudra tester que la date de fin est bien aprs la date de dbut
		finLoc=fin;
		clientLoc=client;
		this.panneauLoc=panneauLoc;
		identifiant=id;
	}

	public Date getDebutLoc() {
		return debutLoc;
	}

	public Integer getIdentifiant() {
		return identifiant;
	}
	
	public Integer getDureeJours() {
		return Period.between(toLocalDate(debutLoc), toLocalDate(finLoc)).getDays();
	}
	
	public String dureeToString() {
		Integer jours = Period.between(toLocalDate(debutLoc), toLocalDate(finLoc)).getDays();
		Integer mois = Period.between(toLocalDate(debutLoc), toLocalDate(finLoc)).getMonths();
		Integer annees = Period.between(toLocalDate(debutLoc), toLocalDate(finLoc)).getYears();
		String resultat = jours+" jours";
		if (mois>0) {
			resultat = resultat+" "+mois+" mois";
		}
		if (annees>0) {
			resultat = resultat+" "+annees+" ans";
		}
		return resultat;
	}
	
	public Integer getDureeMois() {
		return Period.between(toLocalDate(debutLoc), toLocalDate(finLoc)).getMonths();
	}
	
	/**
	 * 
	 * @return un double qui est le total a payer en fonction de la longueur de la location et du tarif d'un panneau
	 */
	public Double totalAPayer() {
		Long mois = ChronoUnit.MONTHS.between(toLocalDate(debutLoc), toLocalDate(finLoc));
		return panneauLoc.getTarifD()*(mois+1);
		//+1 car on compte le mois en cours
	}
	
	public LocalDate toLocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public Date getFinLoc() {
		return finLoc;
	}
	
	public String getDebutLocShort() {
		return shortDateFormat.format(debutLoc);
	}
	
	public String getFinLocShort() {
		return shortDateFormat.format(finLoc);
	}
	
	public Panneau getPanneau() {
		return panneauLoc;
	}
	
	public Client getClient() {
		return clientLoc;
	}
	
	public void setDebutLoc(Date debutLoc) {
		this.debutLoc = debutLoc;
	}

	public void setFinLoc(Date finLoc) {
		this.finLoc = finLoc;
	}
	
	
	public Panneau getPanneauLoc() {
		return panneauLoc;
	}

	public void setPanneauLoc(Panneau panneauLoc) {
		this.panneauLoc = panneauLoc;
	}

	public Client getClientLoc() {
		return clientLoc;
	}

	public void setClientLoc(Client clientLoc) {
		this.clientLoc = clientLoc;
	}

    /**
	 * Permet de modifier les dates de début et de fin
	 * @param dateDeb, une Date
	 * @param dateFin, une Date
	 */
	public void modifDates(Date dateDeb, Date dateFin) {
		this.setDebutLoc(dateDeb);
		this.setFinLoc(dateFin);
	}
	
	public boolean equals(Integer id) {
		return identifiant==id;
	}
}

