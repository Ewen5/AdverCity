package application;

import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Principale extends Application { 
	
	private static FenetrePlanningPanneaux fplanpanneau	= new FenetrePlanningPanneaux();
	private static FenetreAccueil faccueil = new FenetreAccueil();
	
	private static FenetreModifZone fmzone = new FenetreModifZone();
	private static FenetreAjoutZone fazone = new FenetreAjoutZone();
	
	private static FenetreListeClients fclients = new FenetreListeClients();
	
	private static FenetreModifPanneau fmpanneaux = new FenetreModifPanneau();
	private static FenetreAjoutPanneau fapanneaux = new FenetreAjoutPanneau();
	
	private static FenetreCarte fcarte = new FenetreCarte();
	
	private static FenetreAjoutLocation falocation = new FenetreAjoutLocation();
	private static FenetreModifLocation fmlocation = new FenetreModifLocation();

	private static FenetreAjoutClient faclient = new FenetreAjoutClient();
	private static FenetreModifClient fmclient = new FenetreModifClient();
	
	private static FenetreAffichee faffiche = new FenetreAffichee();
	
	private static int index;
	
	//instances de test
	private static Zone zone1 = new Zone("Zone industrielle");
	
	private static Zone zone2 = new Zone("Centre ville");
	
	private static Zone zone3 = new Zone("Rue �douard branly");
	
	private static Zone zone4 = new Zone("Zone sans panneaux");
	
	private static Panneau panneau1 = new Panneau(90.0,3,90.0,75.0,zone1);
	private static Panneau panneau2 = new Panneau(8.0,5,95.0,70.0,zone2);
	private static Panneau panneau3 = new Panneau(480.0,2,100.0,45.0,zone3);
	
	private static Panneau panneauCacheCarte;
	
	private static Client client1 = new Client("Client 1");
	private static Client client2 = new Client("Client 2");
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private static String fenetreAffichee;
	
	//private static File fichier = new File("./donnes.adv");
	
	private static ObservableList<Panneau> lesPanneaux = FXCollections.observableArrayList(panneau1, panneau2, panneau3);
	private static ObservableList<Zone> lesZones = FXCollections.observableArrayList(zone1,zone2,zone3,zone4);
	private static ObservableList<Client> lesClients = FXCollections.observableArrayList(client1,client2);
	private static ObservableList<Location> lesLocations = FXCollections.observableArrayList();
	
	public void start(Stage primaryStage) throws Exception {
		

		Date date1 = sdf.parse("10/05/2001");
		Date date2 = sdf.parse("19/06/2020");
		
		Location loc2 = new Location(date1,new Date(),client2,panneau2);
		Location loc1 = new Location(date2,new Date(),client1,panneau1);
		
		panneau2.ajouterLocation(loc2);
		panneau1.ajouterLocation(loc1);
		lesLocations.add(loc2);
		lesLocations.add(loc1);
		client1.ajouterLocation(loc1);
		client2.ajouterLocation(loc2);

		faffiche.setScene(faccueil.getScene());
		faffiche.show();
		fenetreAffichee="accueil";
		
		fmzone.initModality(Modality.APPLICATION_MODAL);
		fazone.initModality(Modality.APPLICATION_MODAL);
		
		fmpanneaux.initModality(Modality.APPLICATION_MODAL);
		fapanneaux.initModality(Modality.APPLICATION_MODAL);
		
		faclient.initModality(Modality.APPLICATION_MODAL);
		fmclient.initModality(Modality.APPLICATION_MODAL);
		
		fmlocation.initModality(Modality.APPLICATION_MODAL);
		falocation.initModality(Modality.APPLICATION_MODAL);
		
	} 
	
	public static ObservableList<Panneau> getLesPanneaux() {
		return lesPanneaux;
	}

	public static ObservableList<Zone> getLesZones() {
		return lesZones;
	}
	
	public static ObservableList<Client> getLesClients() {
		return lesClients;
	}
	
	public static ObservableList<Location> getLesLocations() {
		return lesLocations;
	}
	
	/**
	 * @param loc, une Location
	 * @return une Integer qui correspond à l'index de la Location loc
	 */
	public static Integer getIndexLocation(Location loc) {
		int i=0;
		boolean trouve = false;
		while (i<lesLocations.size() && !trouve) {
			if(lesLocations.get(i).equals(loc.getIdentifiant())) {
				trouve=true;
			}
			else {
				i++;
			}
		}
		if(!trouve) {
			i=-1;
		}
		return i;
	}
	/**
	 * 
	 * @param cli, un Client
	 * @return une Integer qui correspond à l'index du Client cli
	 */
	public static Integer getIndexClient(Client cli) {
		int i=0;
		boolean trouve = false;
		while (i<lesClients.size() && !trouve) {
			if(lesClients.get(i).equals(cli.getNumclient())) {
				trouve=true;
			}
			else {
				i++;
			}
		}
		if(!trouve) {
			i=-1;
		}
		return i;
	}
	/**
	 * 
	 * @param p, un Panneau
	 * @return un Integer qui correspond à l'index du panneau p
	 */
	public static Integer getIndexPanneau(Panneau p) {
		int i=0;
		boolean trouve = false;
		while (i<lesPanneaux.size() && !trouve) {
			if(lesPanneaux.get(i).equals(p.getNumEmplacement())) {
				trouve=true;
			}
			else {
				i++;
			}
		}
		if(!trouve) {
			i=-1;
		}
		return i;
	}
	
	/////////////////////////////////////////////////////
	// Gestion des fentres
	/////////////////////////////////////////////////////
	/**
	 * Ouvre la fenetre carte et définit les paramètres de la page et actualise les Panneaux
	 */
	public static void ouvrirFenetreCarte() {
		FenetreCarte fc = new FenetreCarte();
		fc.actualiserListePanneaux(lesPanneaux);
		faffiche.setTitle("Carte des emplacements");
		
		faffiche.setMinHeight(700);
		faffiche.setMinWidth(1350);
		
		faffiche.setHeight(700);
		faffiche.setWidth(1350);
		
		faffiche.setScene(fc.getScene());
		fenetreAffichee="carte";
	}
	/**
	 * Ouvre la fenetre PlanningPanneau et définit les paramètres de la page et actualise les Panneaux
	 */
	public static void ouvrirFenetrePlanningPanneau() {
		FenetrePlanningPanneaux fp = new FenetrePlanningPanneaux();
		fp.actualiserPanneaux(lesPanneaux);
		faffiche.setTitle("Catalogue des panneaux");
		
		faffiche.setMinHeight(700);
		faffiche.setMinWidth(900);
		
		faffiche.setHeight(700);
		faffiche.setWidth(900);
		
		faffiche.setScene(fp.getScene());
		
		fenetreAffichee="panneaux";
	}
	/**
	 * Ouvre la fenetre ModifierZone et définit les paramètres de la page et permet de modifier une zone
	 * @param zone, une zone
	 * @param i, un int
	 */
	public static void ouvrirFenetreModifierZone(Zone zone, int i) {
		fmzone.setNom(zone.getNomZone());
		index=i;
		fmzone.showAndWait();
	}
	
/**
	 * Ouvre la fenetre AjoutZone et définit les paramètres de la page
	 */
	public static void ouvrirFenetreAjoutZone() {
		fazone.setNom("");
		fazone.showAndWait();
	}
	/**
	 * Ouvre la fenetre listeClients et définit les paramètres de la page et actualise la liste des clients
	 */
	public static void ouvrirFenetreListeClients() {
		FenetreListeClients fc = new FenetreListeClients();
		fc.actualiserListeClient(lesClients);
		faffiche.setTitle("Liste des clients");
		
		faffiche.setMinHeight(700);
		faffiche.setMinWidth(1200);
		
		faffiche.setHeight(700);
		faffiche.setWidth(1200);
		
		faffiche.setScene(fc.getScene());

		fenetreAffichee="clients";
	}
	/**
	 * Ouvre la fenetre ModifPanneau et définit les paramètres de la page et permet de mofifier un panneau passé en parametre
	 * @param panneau, un Panneau
	 */
	public static void ouvrirFenetreModifPanneau(Panneau panneau) {
		fmpanneaux.setPanneau(panneau);
		fmpanneaux.setId(panneau.getNumEmplacement());
		fmpanneaux.setZone(panneau.getZone());
		fmpanneaux.setVisibilite(panneau.getNbEtoiles());
		fmpanneaux.setTarif(panneau.getTarifD().toString());
		fmpanneaux.setLongueur(panneau.getFormatLongueur().toString());
		fmpanneaux.setLargeur(panneau.getFormatLargeur().toString());
		fmpanneaux.actualiserZones(lesZones);
		fmpanneaux.showAndWait();
	}
	/**
	 * Ouvre la fenetre AjoutPanneau et définit les paramètres de la page
	 */
	public static void ouvrirFenetreAjoutPanneau() {
		fapanneaux.listeZonesVide(lesZones);
		fapanneaux.setId(Panneau.getCompteur());
		fapanneaux.setLargeur("");
		fapanneaux.setLongueur("");
		fapanneaux.setTarif("");
		fapanneaux.actualiserZones(lesZones);
		fapanneaux.showAndWait();
	}
	/**
	 * Ouvre la section Zone et définit les paramètres de la page et actualise les panneaux et les zones
	 */
	public static void ouvrirFenetreZone() {	
		FenetrePlanningPanneaux fp = new FenetrePlanningPanneaux();
		fp.actualiserPanneaux(lesPanneaux);
		fp.actualiserZones(lesZones);
		fp.ouvrirSectionZone();
		faffiche.setTitle("Catalogue des panneaux");
		
		faffiche.setMinHeight(700);
		faffiche.setMinWidth(1200);
		
		faffiche.setHeight(700);
		faffiche.setWidth(1200);
		
		faffiche.setScene(fp.getScene());

		fenetreAffichee="panneau";
	}
	/**
	 * Ouvre la fenetre AjoutPanneauCarte et définit les paramètres de la page, actualise les zones et la fenetre Zones
	 * @return
	 */
	public static Panneau ouvrirFenetreAjoutPanneauCarte() {
		fapanneaux.listeZonesVide(lesZones);
		fapanneaux.setId(Panneau.getCompteur());
		fapanneaux.setLargeur("");
		fapanneaux.setLongueur("");
		fapanneaux.setTarif("");
		fapanneaux.actualiserZones(lesZones);
		fapanneaux.actualiserFenetre(lesZones);
		fapanneaux.showAndWait();
		return fapanneaux.getPanneauCarte();
	}
	/**
	 * Ouvre la fenetre AjoutLoc et définit les paramètres de la page et actualise la liste des clients et des panneaux
	 */
	public static void ouvrirFenetreAjoutLoc() {
		falocation.actualiserClient(lesClients);
		falocation.actualiserPanneau(lesPanneaux);
		
		falocation.clearDateDebut();
		falocation.clearDateFin();
		falocation.verifDates();
		falocation.griserBoutons();
		falocation.forceGriserBouton();
		
		falocation.showAndWait();
	}
	/**
	 * Ouvre la fenetre AjoutLoc et définit les paramètres de la page et définie les valeurs des champs de panneau passé en paramètre
	 * @param p, un Panneau
	 */
	public static void ouvrirFenetreAjoutLoc(Panneau p) {
		falocation.actualiserClient(lesClients);
		falocation.actualiserPanneau(lesPanneaux);
		falocation.depuisPanneau(p);
		
		falocation.clearDateDebut();
		falocation.clearDateFin();
		falocation.verifDates();
		falocation.griserBoutons();
		falocation.forceGriserBouton();
		
		falocation.showAndWait();
	}
	/**
	 * Ouvre la fenetre AjoutLoc et définit les paramètres de la page et définie les valeurs des champs du client passé en paramèter
	 * @param c, un Client
	 */
	public static void ouvrirFenetreAjoutLoc(Client c) {
		falocation.actualiserClient(lesClients);
		falocation.actualiserPanneau(lesPanneaux);
		falocation.depuisClient(c);
		
		falocation.clearDateDebut();
		falocation.clearDateFin();
		falocation.verifDates();
		falocation.griserBoutons();
		falocation.forceGriserBouton();
		
		falocation.showAndWait();
	}
	/**
	 * Ouvre la fenetre AjoutLoc et définit les paramètres de la page et définie les valeurs des champs du panneau et du client passé en paramètre
	 * @param p, un Panneau
	 * @param c, un Client
	 */
	public static void ouvrirFenetreAjoutLoc(Panneau p,Client c) {
		falocation.actualiserClient(lesClients);
		falocation.actualiserPanneau(lesPanneaux);
		falocation.depuisClient(c);
		falocation.depuisPanneau(p);
		
		falocation.clearDateDebut();
		falocation.clearDateFin();
		falocation.verifDates();
		falocation.griserBoutons();
		falocation.forceGriserBouton();
		
		falocation.showAndWait();
	}
	/**
	 * Ouvre la fenetre ModifLoc et modifie l'index de la location passé en paramètre
	 * @param loc, une Location
	 * @param i, un Integer
	 */
	public static void ouvrirFenetreModifLoc(Location loc) {
		fmlocation.getAncienneLoc(loc);
		fmlocation.showAndWait();
	}
	/**
	 * Ouvre la fenetre AjoutClient et définit les paramètres de la page et actualise la liste des panneaux
	 */
	public static void ouvrirFenetreAjoutClient() {
		faclient.listePanneauxVide(lesPanneaux);
		faclient.setId(Client.getCompteur());
		faclient.actualiserListePanneaux(lesPanneaux);
		faclient.showAndWait();
	}
	/**
	 * Ouvre la fenetre ModifClient, définit les paramètres de la page et permet de modifier les données du client passé en paramètre
	 * @param c
	 */
	public static void ouvrirFenetreModifClient(Client c) {
		fmclient.setClient(c);
		fmclient.setNom(c.getLibelle());
		fmclient.showAndWait();
	}
	
	/////////////////////////////////////////////////////
	// Gestion de la liste des donnes
	/////////////////////////////////////////////////////
    /**
     * Permet d'ajouter une zone
    * @param zone, une Zone
     */
	public static void ajouterZone(Zone zone) {
		// mise  jour des donnes : ajout
		lesZones.add(zone);
		fplanpanneau.actualiserZones(lesZones);
	}
	/**
	 * permet de supprimer une zone dont l'index est donné en paramètre
	 * @param i, un int
	 */
	public static void supprimerZone(int i) {
		lesZones.remove(i);
		fplanpanneau.actualiserZones(lesZones);
	}
	/**
	 * permet de modifier une zone dont le nom est passé en paramètre
	 * @param nom, un String
	 */
	public static void modifierZone(String nom) {
		lesZones.get(index).changerNomZone(nom);
		lesZones.set(index, lesZones.get(index));
		fplanpanneau.actualiserZones(lesZones);
		fplanpanneau.actualiserNomSelectZone();
		fplanpanneau.actualiserZoneDansPanneaux(lesZones.get(index), lesPanneaux);
		fplanpanneau.actualiserPanneaux(lesPanneaux);
	}
	/**
	 * permet d'ajouter une panneau passé en paramètre
	 * @param panneau, un Panneau
	 */
	public static void ajouterPanneau(Panneau panneau) {
		lesPanneaux.add(panneau);
		fplanpanneau.actualiserPanneaux(lesPanneaux);
	}
	
	public static void setPanneauCacheCarte(Panneau p) {
		panneauCacheCarte=p;
	}
	
	public static Panneau getPanneauCacheCarte() {
		return panneauCacheCarte;
	}
	/**
	 * Permet de modifier un panneau passé en paramètre
	 * @param panneau, un Panneau
	 */
	public static void modifierPanneau(Panneau panneau) {
		lesPanneaux.set(Principale.getIndexPanneau(panneau), panneau);
		if (fenetreAffichee.compareTo("panneaux")==0) {
			fplanpanneau.actualiserPanneaux(lesPanneaux);
			fplanpanneau.fermerPanneau();
		}
		else if (fenetreAffichee.compareTo("carte")==0) {
			//fcarte.actualiserListePanneaux(lesPanneaux);
			fcarte.actualiserPanneauCache(panneauCacheCarte);
		}
	}

	/**
	 * Permet de supprimer un panneau passé en paramètre
	 * @param p, un Panneau
	 */
	public static void supprimerPanneau(Panneau p) {
		lesPanneaux.remove(Principale.getIndexPanneau(p).intValue());
		if (fenetreAffichee.compareTo("panneaux")==0) {
			fplanpanneau.actualiserPanneaux(lesPanneaux);
		}
		else if (fenetreAffichee.compareTo("carte")==0) {
			fcarte.actualiserListePanneaux(lesPanneaux);
		}
	}
	/**
	 * permet d'ajouter un client passé en paramètre
	 * @param client, un Client
	 */
	public static void ajouterClient(Client client) {
		lesClients.add(client);
		if (fenetreAffichee.compareTo("clients")==0) {
			fclients.actualiserListeClient(lesClients);
		}
	}
	/**
	 * permet de modifier un client passé en paramètre
	 * @param client, un Client
	 */
	public static void modifierClient(Client client) {
		lesClients.set(Principale.getIndexClient(client), client);
		if (fenetreAffichee.compareTo("clients")==0) {
			fclients.actualiserListeClient(lesClients);
			fclients.selectClient(client);
			fclients.actualiserDetailClient();
		}
		
	}
	/**
	 * Permet de suppimer un client passé en paramètre
	 * @param c, un Client
	 */
	public static void supprimerClient(Client c) {
		lesClients.remove(Principale.getIndexClient(c).intValue());
		if (fenetreAffichee.compareTo("clients")==0) {
			fclients.actualiserListeClient(lesClients);
		}
	}
	/**
	 * Permet d'ajouter une location passé en paramètre
	 * @param location, une Location
	 */
	public static void ajouterLocation(Location location) {
		lesLocations.add(location);
		lesPanneaux.set(Principale.getIndexPanneau(location.getPanneauLoc()), location.getPanneauLoc());
		lesClients.set(Principale.getIndexClient(location.getClientLoc()), location.getClientLoc());
		if (fenetreAffichee.compareTo("panneaux")==0) {
			fplanpanneau.actualiserListeLocation();
		}
		else if (fenetreAffichee.compareTo("clients")==0) {
			fclients.actualiserListeLocation();
		}
		else if (fenetreAffichee.compareTo("carte")==0) {
			fcarte.actualiserListeLocation();
			fcarte.actualiserDetailsLocation();
		}
	}
	/**
	 * permet de modifier une Location passé en paramètre
	 * @param location, une Location
	 */
	public static void modifierLocation(Location location) {
		lesLocations.set(Principale.getIndexLocation(location), location);
		lesPanneaux.set(Principale.getIndexPanneau(location.getPanneauLoc()), location.getPanneauLoc());
		lesClients.set(Principale.getIndexClient(location.getClientLoc()), location.getClientLoc());
		if (fenetreAffichee.compareTo("panneaux")==0) {
			fplanpanneau.actualiserListeLocation();
		}
		else if (fenetreAffichee.compareTo("clients")==0) {
			fclients.actualiserListeLocation();
		}
		else if (fenetreAffichee.compareTo("carte")==0) {
			fcarte.selectionnerLocation(location);
			fcarte.actualiserListeLocation();
			fcarte.actualiserDetailsLocation();
		}
	}
	/**
	 * permet de supprimer une location passé en paramètre
	 * @param location, une Location
	 */
	public static void supprimerLocation(Location location) {
		lesLocations.remove(Principale.getIndexLocation(location).intValue());
		Panneau p = location.getPanneauLoc();
		p.supprimerLocation(location);
		Client c = location.getClientLoc();
		c.supprimerLocation(location);
		lesPanneaux.set(Principale.getIndexPanneau(p), p);
		lesClients.set(Principale.getIndexClient(c), c);
		if (fenetreAffichee.compareTo("panneaux")==0) {
			fplanpanneau.actualiserListeLocation();
		}
		else if (fenetreAffichee.compareTo("clients")==0) {
			fclients.actualiserListeClient(lesClients);
		}
		else if (fenetreAffichee.compareTo("carte")==0) {
			fcarte.selectionnerLocation(location);
			fcarte.actualiserListeLocation();
			fcarte.actualiserDetailsLocation();
		}
	}
	
	public static void main(String[] args) { 
		Application.launch(args);
	}

}