package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FenetreListeClients extends Stage {
	
	/* Dclaration des lments du menu */
	private MenuBar menu = new MenuBar();

	private Menu menuPanneaux = new Menu("Panneaux");
	private Menu menuClients = new Menu("Clients");
	private Menu menuZones = new Menu("Zones");
	
	private MenuItem menuListeClients = new MenuItem("Liste des clients");
	private MenuItem menuAjoutClient = new MenuItem("Ajouter un client");
	
	private MenuItem menuCatalogue = new MenuItem("Catalogue des panneaux");
	private MenuItem menuCarte = new MenuItem("Carte des emplacements");
	
	private MenuItem menuGererZones = new MenuItem("GÈrer les zones");
	private MenuItem menuAjoutZones = new MenuItem("Ajouter une zone");
	
	private MenuItem menuAjoutEmplacement = new MenuItem("Ajouter un panneau");
	
	
	/*PARTIE DETAIL DROITE DETAIL CLIENT*/
	
	/*Partie du haut */
	
	private Label 			titrePartCli 		= new Label("Details");
	private Label 			nomCliSelect		= new Label();
	private Label 			lblNumeroCli		= new Label();
	private Label 			lblLibelle			= new Label("LibellÈ :");
	private Label			lblNbrLocTot		= new Label();
	private Label			lblNbrLocCours		= new Label();
	private Label			lblAPayer			= new Label();

	
	/*liste des locations*/
	
	private Label			lblTitreTabLoc		= new Label("Historique des locations");
	
	private ComboBox<Panneau> listePanneaux = new ComboBox<Panneau>();
	
	private TableView<Location> tabHistLoc = new TableView<Location>();
	private TableColumn<Location, String> numeroLocColonne = new TableColumn<Location, String>("NumÈro location");
	private TableColumn<Location, String> dateDebutColonne = new TableColumn<Location, String>("DÈbut");
	private TableColumn<Location, String> dateFinColonne = new TableColumn<Location, String>("Fin");

	

	private Button bnNouvelleLoc = new Button("Nouvelle location");
	
	//Une location
	
	private Button bnModifierLoc = new Button("Modifier location...");
	private Button bnSupprimerLoc = new Button("Supprimer location");
	
	/*Partie basse */
	private Button bnSupprimerCli = new Button("Supprimer client");
	private Button bnModif = new Button("Modifier libellÈ");
	
	private Label texteDuree = new Label();
	private Label texteTotal = new Label();
	
	 
	/*PARTIE DETAIL DROITE AJOUT CLIENT*/
	private Alert supprImpossible = new Alert(AlertType.ERROR);
	
	private URL urlWarning = this.getClass().getResource("warning.png");
	private String warningspath = urlWarning.toExternalForm();
	private Image warningImg = new Image(warningspath);
	private ImageView warningImage = new ImageView(warningImg);
	
	// PARTIE GAUCHE 
	
	/*liste des clients*/
	private TableView<Client> listeClients = new TableView<Client>();
	private TableColumn<Client, String> numCliColonne = new TableColumn<Client, String>("N∞ client");
	private TableColumn<Client, String> libelleColonne = new TableColumn<Client, String>("LibellÈ");
	private TableColumn<Client, String> locationEnCoursColonne = new TableColumn<Client, String>("Locations en cours");
	private TableColumn<Client, String> tarifLocActuellesColonne = new TableColumn<Client, String>("Tarif des locations actuelles ‡ payer");

	private Button 	bnAjoutCli	= new Button("Ajouter un client");
	/*Dclaration des lments de positionnement*/

	// Les panes
	
	private GridPane racine = new GridPane();
	
	private GridPane paneClients = new GridPane();
	
	private GridPane partieDroite = new GridPane();
	private GridPane paneLocations = new GridPane();
	
	private VBox rectangleUneLocation = new VBox(10);
	private HBox optionsLocCliDetail = new HBox(10);
	
	private HBox boutonsLigne1 = new HBox(10);
	private HBox boutonsLigne2 = new HBox(10);
	private VBox boutonsLocations = new VBox(10);
	
	private VBox detailsClient = new VBox();
	
	private VBox tabCli = new VBox(10);
	
	//BOOLEAN SAVOIR SI UNE PARTIE DE DROITE EST AFFICHE
	
	private boolean ajoutClientVisible = false;
	private boolean detailClientVisible =false;
	private boolean choixLocAffiche = false;
	
	/*modale suppression*/
	private Alert				alerteSuppr		= new Alert(AlertType.CONFIRMATION);
	
	private ButtonType			bnConfirmer		= new ButtonType("Oui");
	private ButtonType			bnAnnuler		= new ButtonType("Non");
	
	//Suppression location
	private Alert				alerteSupprLoc		= new Alert(AlertType.CONFIRMATION);
	
	
	public FenetreListeClients() {
		this.setTitle("Liste des clients");
		this.setX(10);
		this.setY(10);
		this.setResizable(true);
		this.setMinHeight(700);
		this.setMinWidth(1200);
		this.getIcons().add(new Image(getClass().getResourceAsStream("logo advercity.png")));
		Scene laScene=new Scene(creerContenu());
		
		this.setScene(laScene);
		
		
	}
	
	@SuppressWarnings("unchecked")
	Parent creerContenu() {
		//Location
		
		optionsLocCliDetail.getChildren().addAll(bnModifierLoc,bnSupprimerLoc);
		optionsLocCliDetail.setAlignment(Pos.CENTER);
		rectangleUneLocation.getChildren().addAll(texteDuree,texteTotal,optionsLocCliDetail);
		optionsLocCliDetail.getStyleClass().add("border");
		rectangleUneLocation.getStyleClass().add("border-verticaux");
		
		//suppr loc
		
		alerteSupprLoc.setContentText("Voulez-vous vraiment supprimer cette location ?");
		alerteSupprLoc.setHeaderText("Confirmation");
		alerteSupprLoc.setTitle("Confirmation");
		alerteSupprLoc.getButtonTypes().setAll(bnConfirmer,bnAnnuler);
		
		/*suppression modale*/
		supprImpossible.setContentText("Suppression du client impossible : il loue actuellement un ou plusieurs panneaux");
		supprImpossible.setHeaderText("Suppression client impossible");
		supprImpossible.setTitle("Suppression client impossible");
		
		warningImage.setFitHeight(25);
		warningImage.setFitWidth(25);
		warningImage.setVisible(false);
		
		alerteSuppr.setContentText("Voulez-vous vraiment supprimer ce client ?");
		alerteSuppr.setHeaderText("Confirmation");
		alerteSuppr.setTitle("Confirmation");
		
		alerteSuppr.getButtonTypes().setAll(bnConfirmer,bnAnnuler);
		
		//PARTIE GAUCHE TABLEAU
		/* liste client */
		
		listeClients.getColumns().addAll(numCliColonne, libelleColonne, locationEnCoursColonne, tarifLocActuellesColonne);		
		listeClients.setPlaceholder(new Label("Aucun client"));
		listeClients.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		listeClients.getSortOrder().add(numCliColonne);
		
		numCliColonne.setMinWidth(100);
		libelleColonne.setMinWidth(150);
		locationEnCoursColonne.setMinWidth(200);
		tarifLocActuellesColonne.setMinWidth(300);
		
		numCliColonne.setCellValueFactory(new PropertyValueFactory<>("numclient"));
		libelleColonne.setCellValueFactory(new PropertyValueFactory<>("libelle"));
		locationEnCoursColonne.setCellValueFactory(new PropertyValueFactory<>("NbLocEnCours"));
		tarifLocActuellesColonne.setCellValueFactory(new PropertyValueFactory<>("TotalAPayerLocEnCoursString"));


		paneClients.add(tabCli, 0, 0);
		paneClients.setPadding(new Insets(20));
		partieDroite.setPadding(new Insets(20));
		//PARTIE DROITE DETAIL CLIENT TABLEAU
		
		titrePartCli.setAlignment(Pos.CENTER);
		
		tabHistLoc.getColumns().addAll(numeroLocColonne, dateDebutColonne, dateFinColonne);		
		tabHistLoc.setPlaceholder(new Label("Aucune location"));
		tabHistLoc.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tabHistLoc.getSortOrder().add(numeroLocColonne);
		
		numeroLocColonne.setMinWidth(200);
		dateDebutColonne.setMinWidth(120);
		dateFinColonne.setMinWidth(120);

		numeroLocColonne.setCellValueFactory(new PropertyValueFactory<>("identifiant"));
		dateDebutColonne.setCellValueFactory(new PropertyValueFactory<>("debutLocShort"));
		dateFinColonne.setCellValueFactory(new PropertyValueFactory<>("finLocShort"));

		/*Ajout des lments au menu*/
		menuPanneaux.getItems().addAll(menuCatalogue,menuAjoutEmplacement,menuCarte);
		menuClients.getItems().addAll(menuListeClients,menuAjoutClient);
		menuZones.getItems().addAll(menuGererZones,menuAjoutZones);
		menu.getMenus().addAll(menuPanneaux,menuZones,menuClients);
		
		
		/*positionnement*/
		
		// PARIE GAUCHE
		
		bnAjoutCli.setMaxSize(Double.MAX_VALUE, 10);
		bnAjoutCli.setMinHeight(60);
		
		
		VBox.setVgrow(bnAjoutCli, Priority.ALWAYS);

		tabCli.getChildren().addAll(bnAjoutCli, listeClients);
		
		
		//PARTIE DROITE DETAIL CLIENT
		
		// HAUT DE LA PARTIE AVEC LE NOM DU CLIENT
		nomCliSelect.setMaxWidth(Double.MAX_VALUE);
		titrePartCli.setMaxWidth(Double.MAX_VALUE);
		HBox.setHgrow(titrePartCli, Priority.ALWAYS);
		HBox.setHgrow(nomCliSelect, Priority.ALWAYS);
		
		
		//INFORMATION DU CLIENT		
		detailsClient.getChildren().addAll(titrePartCli,lblNumeroCli,lblLibelle,lblNbrLocTot,lblNbrLocCours,lblAPayer);
		detailsClient.setSpacing(10.0);

		// PARTIE DES LOC DU CLIENT
		lblTitreTabLoc.setAlignment(Pos.CENTER);
		// TABLEAU HISORIQUE DES LOC
		lblTitreTabLoc.setMaxWidth(Double.MAX_VALUE);
		VBox.setVgrow(lblTitreTabLoc, Priority.ALWAYS);
		
		paneLocations.setPadding(new Insets(10,0,0,0));
		
		GridPane.setHalignment(listePanneaux, HPos.CENTER);
		
		paneLocations.setVgap(10);
		
		paneLocations.add(lblTitreTabLoc, 0, 0);
		paneLocations.add(listePanneaux, 0, 1);
		paneLocations.add(tabHistLoc, 0, 2);
		
		
		//Zone de boutons
		
		boutonsLigne1.getChildren().addAll(bnNouvelleLoc);
		boutonsLigne2.getChildren().addAll(bnSupprimerCli,bnModif);
		
		boutonsLigne1.setAlignment(Pos.CENTER);
		boutonsLigne2.setAlignment(Pos.CENTER);
		
		boutonsLocations.getChildren().addAll(boutonsLigne1,boutonsLigne2);
		boutonsLocations.setAlignment(Pos.CENTER);
		
		boutonsLocations.setPadding(new Insets(20,0,0,0));
		
		//H et V grow
		
		GridPane.setHgrow(paneClients, Priority.ALWAYS);
		GridPane.setVgrow(paneClients, Priority.ALWAYS);
		
		GridPane.setHgrow(tabCli, Priority.ALWAYS);
		GridPane.setVgrow(tabCli, Priority.ALWAYS);
		
		GridPane.setHgrow(listeClients, Priority.ALWAYS);
		GridPane.setVgrow(listeClients, Priority.ALWAYS);
		
		GridPane.setHgrow(bnAjoutCli, Priority.ALWAYS);
		GridPane.setVgrow(bnAjoutCli, Priority.ALWAYS);
		
		// ASSEMBLAGE DE TOUTE LA PARTIE DETAIL CLIENT
		partieDroite.add(detailsClient, 0, 0);
		partieDroite.add(paneLocations, 0, 1);
		partieDroite.add(boutonsLocations, 0, 2);
		//...PUIS RACINE

		racine.add(menu, 0, 0,2,1);
		racine.add(paneClients, 0, 1);
		paneClients.setVgap(20.0);
		paneClients.setHgap(20.0);
		
		//////////////////////
		//Menu de navigation//
		//////////////////////
		
		menuCatalogue.setOnAction(e -> {
			Principale.ouvrirFenetrePlanningPanneau();
		});
		
		menuAjoutEmplacement.setOnAction(e -> {
			Principale.ouvrirFenetreAjoutPanneau();
		});
		
		menuCarte.setOnAction(e -> {
			Principale.ouvrirFenetreCarte();
		});
		
		menuListeClients.setOnAction(e -> {
			Principale.ouvrirFenetreListeClients();
		});
		
		menuAjoutClient.setOnAction(e -> {
			Principale.ouvrirFenetreAjoutClient();
		});
		
		menuGererZones.setOnAction(e -> {
			Principale.ouvrirFenetreZone();
		});
		
		menuAjoutZones.setOnAction(e -> {
			Principale.ouvrirFenetreAjoutZone();
		});
		//Ouverture de la fenetre dtaille du client
	
		
		
		listeClients.setOnMouseClicked(e ->{
			if (listeClients.getItems().size() !=0 && listeClients.getSelectionModel().getSelectedIndex()!=-1) {
				if (sectionAffiche()) {
					fermerPartieDroite();
				}
				actualiserDetailClient();
				racine.add(partieDroite, 1, 1);
				detailClientVisible=true;
			}
		});
		
		
		//Ouverture de la fenetre ajout d'un client
		
		bnAjoutCli.setOnAction(e ->{
			Principale.ouvrirFenetreAjoutClient();
		});

		
		bnNouvelleLoc.setOnAction(e -> {
			if (listePanneaux.getSelectionModel().getSelectedIndex()!=-1) {
				Principale.ouvrirFenetreAjoutLoc(listePanneaux.getSelectionModel().getSelectedItem(), listeClients.getSelectionModel().getSelectedItem());
			}
			else {
				Principale.ouvrirFenetreAjoutLoc(listeClients.getSelectionModel().getSelectedItem());
			}
			this.fermerPartieDroite();
		});
		
		bnModif.setOnAction(e -> {
			if (listeClients.getSelectionModel().getSelectedIndex()!=-1) {
				Principale.ouvrirFenetreModifClient(listeClients.getSelectionModel().getSelectedItem());
				this.fermerPartieDroite();
			}
		});
		
		bnSupprimerCli.setOnAction(e -> {
			supprimerClient();
			actualiserDetailClient();
		});

		tabHistLoc.setOnMouseClicked(e -> {
			selectionnerItemListeLocation();
		});
		
		bnSupprimerLoc.setOnAction(e -> {
			supprimerLocation();
			actualiserDetailClient();
		});
		
		bnModifierLoc.setOnAction(e -> {
			Principale.ouvrirFenetreModifLoc(tabHistLoc.getSelectionModel().getSelectedItem());
			this.fermerPartieDroite();
		});
		
		return racine;

	}
	
	/**
	 * Permet de supprimer une Location, un message alternatif s'affichera si la Location est en cours.
	 */
	public void supprimerLocation() {
		Location loc = tabHistLoc.getSelectionModel().getSelectedItem();
		
		//Vrifie si la location est en cours
		if (loc.getFinLoc().after(Calendar.getInstance().getTime())) {
			alerteSupprLoc.setContentText("Cette location est en cours,\nvoulez-vous la supprimer ?");
		}
		Optional<ButtonType> choix = alerteSupprLoc.showAndWait();
		if(choix.get() == bnConfirmer) {
			Principale.supprimerLocation(loc);
			this.selectClient(loc.getClientLoc());
			this.actualiserListePanneaux();
			this.actualiserListeLocation();
			this.selectionnerItemListeLocation();
			this.fermerPartieDroite();
		}
	}
	
	/**
	 * Permet de s√©lectionner les locations et d'avoir des informations sur eux. Ou de fermer les d√©tails !
	 */
	public void selectionnerItemListeLocation() {
		if (tabHistLoc.getItems().size()!=0 && tabHistLoc.getSelectionModel().getSelectedIndex()!=-1) {
			texteDuree.setText("DurÈe de la location : "+tabHistLoc.getSelectionModel().getSelectedItem().dureeToString());
			texteTotal.setText("Total ‡ payer : "+tabHistLoc.getSelectionModel().getSelectedItem().totalAPayer()+"");
			if (!choixLocAffiche) {
				paneLocations.add(rectangleUneLocation, 0, 3);
				choixLocAffiche=true;
			}
		}
		else {
			paneLocations.getChildren().remove(rectangleUneLocation);
			choixLocAffiche=false;
		}
	}
	
	/**
	 * Actualise la liste des clients
	 * @param laListe, une ObservableList<Client>
	 */
	public void actualiserListeClient(ObservableList<Client> laListe) {
		listeClients.setItems(laListe);
		listeClients.refresh();
	}
	
	/**
	 * Actualise les d√©tails d'un Client
	 */
	public void actualiserDetailClient() {
		if (listeClients.getItems().size()!=0 && listeClients.getSelectionModel().getSelectedIndex()!=-1) {
			lblNumeroCli.setText("Numero du client :  "+listeClients.getSelectionModel().getSelectedItem().getNumclient());
			nomCliSelect.setText(listeClients.getSelectionModel().getSelectedItem().getLibelle());
			lblLibelle.setText("Nom du client : "+listeClients.getSelectionModel().getSelectedItem().getLibelle());
			lblNbrLocCours.setText("Nombre de location en cours :  "+listeClients.getSelectionModel().getSelectedItem().getNbLocEnCours());
			lblNbrLocTot.setText("Nombre de location total :  "+listeClients.getSelectionModel().getSelectedItem().getNbTotalLoc());
			lblAPayer.setText("Total ‡ payer des locations actuelles :  "+listeClients.getSelectionModel().getSelectedItem().getTotalAPayerLocEnCours());
			this.actualiserListePanneaux();
			this.actualiserListeLocation();
		}
		else {
			racine.getChildren().remove(partieDroite);
			detailClientVisible=false;
		}

	}
	
	/**
	 * Permet de supprimer un client, un message s'affiche si le Client √† des Locations en cours
	 */
	public void supprimerClient() {
		Client itemClient = listeClients.getSelectionModel().getSelectedItem();
		
		if (!itemClient.getLocEnCours().isEmpty()) {
			alerteSuppr.setContentText("Ce client a des locations en cours,\nvoulez-vous vraiment le supprimer ?");
		}
		
		Optional<ButtonType> choix = alerteSuppr.showAndWait();
		if (choix.get() == bnConfirmer) {
			Principale.supprimerClient(itemClient);
			this.fermerPartieDroite();
		}
	}
	
	/**
	 * @return un bool√©en qui permet de savoir si une section d'un client est affich√©e √† droite
	 */
		public boolean sectionAffiche() {
			return (detailClientVisible || ajoutClientVisible);
		}
		
	/**
	 * Permet de fermer la section √† droite qui est ouverte, s'il y en a une
	 */
		public void fermerPartieDroite() {
			if (detailClientVisible) {
				racine.getChildren().remove(partieDroite);
				detailClientVisible=false;
			}
			else if (ajoutClientVisible) {
				racine.getChildren().remove(partieDroite);
				ajoutClientVisible=false;
			}
		}
		
		
		/**
		 * @param libelle, un String
		 * @param laListe, une ObservableList<Client>
		 * @return un bool√©e qui renvoie vrai si un Client est contenu dans la liste laListe
		 */
		public boolean listeClientContient(String libelle, ObservableList<Client> laListe) {
			boolean contient = false;
			int i=0;
			while (!contient && i<laListe.size()) {
				if (laListe.get(i).getLibelle().equals(libelle)) {
					contient=true;
				}
				else {
					i++;
				}
			}
			return contient;
		}
		
		/**
		 * Actualise la liste des panneaux
		 */
		public void actualiserListePanneaux() {
			ArrayList<Panneau> listePanneaux = listeClients.getSelectionModel().getSelectedItem().panneaux();
			ObservableList<Panneau> panneaux = FXCollections.observableArrayList(listePanneaux);
			this.listePanneaux.setItems(panneaux);
		}
		
		// SET DE DETAIL CLIENT 
		
		public void setLibelle(String libelle){
			nomCliSelect.setText(libelle);
			lblLibelle.setText("Nom du client : "+libelle);
		}
		
		public void setNum(Integer num) {
			lblNumeroCli.setText("NumÈro du client :"+num);
		}
		
		public void setNbrLocTot(Integer NbrLT) {
			lblNbrLocTot.setText("Nombre total de location :"+NbrLT);
		}
		
		public void setNbrLocC(Integer NbrLC) {
			lblNbrLocCours.setText("Nombre de location en cours :"+NbrLC);
		}
		
		public void setAPayer(Integer Prix) {
			lblAPayer.setText("Total ‡ payer des locations actuelles :"+Prix);
		}
		
		// FIN AJOUT CHOSE DE FENTRE AJOUT CLIENT

		// ACTUALISE LISTE LOCATION
		
		/**
		 * Actualise la liste des locations
		 */
		public void actualiserListeLocation() {
			if (listeClients.getSelectionModel().getSelectedIndex()!=-1) {
				if (listePanneaux.getSelectionModel().getSelectedIndex()==-1) {
					this.actualiserListeLocationPasPanneau();
				}
				else {
					this.actualiserListeLocationPanneau(listePanneaux.getSelectionModel().getSelectedItem());
				}
			}
		}
		
		/**
		 * Actualise la liste des locations sans les panneaux
		 */
		public void actualiserListeLocationPasPanneau() {
			ArrayList<Location> listeLoc = listeClients.getSelectionModel().getSelectedItem().getLocTotal();
			ObservableList<Location> locations = FXCollections.observableArrayList(listeLoc);
			tabHistLoc.setItems(locations);
		}
		
		/**
		 * Actualise la liste des locations d'un panneaux pass√© en parametre
		 */
		public void actualiserListeLocationPanneau(Panneau p) {
			ArrayList<Location> listeLoc = listeClients.getSelectionModel().getSelectedItem().getLocTotal();
			ArrayList<Location> listeLocPan = new ArrayList<Location>();
			for (int i=0;i<listeLoc.size();i++) {
				if (listeLoc.get(i).getPanneauLoc().equals(p.getNumEmplacement())) {
					listeLocPan.add(listeLoc.get(i));
				}
			}
			ObservableList<Location> locations = FXCollections.observableArrayList(listeLoc);
			tabHistLoc.setItems(locations);
		}
		
		/**
		 *  Actualise la liste des Location de la liste pass√© en param√®tre
		 * @param laListe, une ObservableList<Location>
		 */
		public void actualiserLocation(ObservableList<Location> laListe) {
			tabHistLoc.setItems(laListe);
		}
		
		/**
		 * permet de s√©lectionner un client
		 */
		public void selectClient(Client c) {
			listeClients.getSelectionModel().select(c);
		}
		
}