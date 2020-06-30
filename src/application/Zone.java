package application;

import java.io.Serializable;

public class Zone implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8353728123192672547L;
	private String nomZone;
	
	public Zone(String nomZone) {
		this.nomZone = nomZone;
	}
	
	public void changerNomZone(String nouveauNom) {
		this.nomZone=nouveauNom;
	}
	
	public String getNomZone() {
		return nomZone;
	}
	
	public String toString() {
		return nomZone;
	}
}