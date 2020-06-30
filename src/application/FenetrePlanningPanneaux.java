package application;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FenetrePlanningPanneaux extends Stage {
	/*Detail d'un panneau*/
	private Label titrePanDetail = new Label();

	private Button bnAjoutReservation = new Button("Ajouter location...");
	
	private Button bnSupprimerPanneau = new Button("Supprimer panneau");
	private Button bnModifierPanneau = new Button("Modifier panneau...");

	
	/*Location*/
	private ComboBox<Client> listeClients = new ComboBox<Client>();
	private Label labelClient = new Label("Client :");
	
	private Label labelLocation = new Label("Liste des locations :");
	private TableView<Location> listeLocation = new TableView<Location>();
	private TableColumn<Location, String> idLocation = new TableColumn<Location, String>("Identifiant");
	private TableColumn<Location, Date> dateDebLocation = new TableColumn<Location, Date>("Date de d�but");
	private TableColumn<Location, Date> dateFinLocation = new TableColumn<Location, Date>("Date de fin");
	
	private Button bnModifierDureeLocation = new Button("Modifier location...");
	private Button bnSupprimerLocation = new Button("Supprimer location");
	
	private Label texteDuree = new Label("Dur�e de la location :");
	private Label texteTotal = new Label("Total � payer :");
	
	/*liste des zones*/
	private TableView<Zone> listeZones = new TableView<Zone>();
	private TableColumn<Zone, String> nomZone = new TableColumn<Zone, String>("Nom");
	
	private Label titrePanZone = new Label("Liste des zones");
	private Label nomZoneSelect = new Label();
	private Button supprZoneBtn = new Button("Supprimer zone");
	private Button modifNomZoneBtn = new Button("Modifier zone...");
	private Button bnAjoutZone = new Button("Ajouter zone...");
	
	private Tooltip tooltipSupprImpossible = new Tooltip("Suppression impossible : Des panneaux existent dans cette zone");
	private Alert supprImpossible = new Alert(AlertType.ERROR);
	
	private URL urlWarning = this.getClass().getResource("warning.png");
	private String warningspath = urlWarning.toExternalForm();
	private Image warningImg = new Image(warningspath);
	private ImageView warningImage = new ImageView(warningImg);
	
	private MenuItem 			optionAjouter	= new MenuItem("Ajouter zone...");
	private MenuItem			optionModifier	= new MenuItem("Modifier zone...");
	private MenuItem			optionSupprimer = new MenuItem("Supprimer");
	
	private ContextMenu			ContextMenuZones = new ContextMenu(optionAjouter, new SeparatorMenuItem(),optionModifier, new SeparatorMenuItem(),optionSupprimer);
	/*catalogue des panneaux*/
	
	private TableView<Panneau> cataloguePanneaux = new TableView<Panneau>();
	private TableColumn<Panneau, String> idColonne = new TableColumn<Panneau, String>("ID");
	private TableColumn<Panneau,String> louantColonne = new TableColumn<Panneau, String>("Louant");
	private TableColumn<Panneau,String> emplacementColonne = new TableColumn<Panneau, String>("Emplacement");
	private TableColumn<Panneau,String> visibiliteColonne = new TableColumn<Panneau, String>("Visibilit�");
	private TableColumn<Panneau,String> tarifColonne = new TableColumn<Panneau, String>("Tarif");
	private TableColumn<Panneau,String> formatColonne = new TableColumn<Panneau, String>("Format");
	
	/*planning de location d'un panneau*/
	
	private Rectangle planningPanneau = new Rectangle();
	
	/*Dclaration des lments du menu*/
	private MenuBar menu = new MenuBar();

	private Menu menuPanneaux = new Menu("Panneaux");
	private Menu menuClients = new Menu("Clients");
	private Menu menuZones = new Menu("Zones");
	
	private MenuItem menuListeClients = new MenuItem("Liste des clients");
	private MenuItem menuAjoutClient = new MenuItem("Ajouter un client");
	
	private MenuItem menuCatalogue = new MenuItem("Catalogue des panneaux");
	private MenuItem menuCarte = new MenuItem("Carte des emplacements");
	
	private MenuItem menuGererZones = new MenuItem("G�rer les zones");
	private MenuItem menuAjoutZones = new MenuItem("Ajouter une zone");
	
	private MenuItem menuAjoutEmplacement = new MenuItem("Ajouter un panneau");
	
	/*Dclaration des lments de positionnement*/

	private GridPane racine = new GridPane();
	
	private GridPane panPanneaux = new GridPane();
	
	private HBox headerPanZone = new HBox();
	private GridPane panZone = new GridPane();
	private GridPane itemSelectOptions = new GridPane();

	private GridPane panFormat = new GridPane();
	
	private GridPane panDetailPanneau = new GridPane();
	private VBox headerPanDetail = new VBox();
	private VBox rectanglePanDetail = new VBox();
	private HBox boutonsPanDetail = new HBox();
	private HBox boutonsPanDetail2 = new HBox();
	private HBox boutonsLocation = new HBox();
	private VBox optionsLocPanDetail = new VBox();
	
	private HBox choixClient = new HBox(10);
	
	private VBox listeLocationsPane = new VBox(10);
	
	
	/*chargement des images et la feuille css*/
	private URL urlcss = this.getClass().getResource("application.css");
	private String css = urlcss.toExternalForm();
	
	private URL urlzone = this.getClass().getResource("zone.png");
	private String zonepath = urlzone.toExternalForm();
	private Image zoneImg = new Image(zonepath);
	private ImageView zoneImage = new ImageView(zoneImg);
	
	private URL urlapanneau = this.getClass().getResource("nouveau.png");
	private String apanneaupath = urlapanneau.toExternalForm();
	private Image apanneauImg = new Image(apanneaupath);
	private ImageView ajoutPanneauImage = new ImageView(apanneauImg);
	
	private Button listeZoneBtn = new Button("Liste des zones", zoneImage);
	private Button ajoutPanneauBtn = new Button("Ajouter un panneau", ajoutPanneauImage);
	
	private boolean listeZoneVisible = false;
	private boolean detailPanneauVisible = false;
	
	private boolean listeLocAffiche = false;
	private boolean choixLocAffiche = false;
	
	private boolean itemAffiche = false;
	
	/*modale suppression*/
	private Alert				alerteSupprZone		= new Alert(AlertType.CONFIRMATION);
	private Alert				alerteSupprPan		= new Alert(AlertType.CONFIRMATION);
	private ButtonType			bnConfirmer		= new ButtonType("Oui");
	private ButtonType			bnAnnuler		= new ButtonType("Non");
	
	//Suppression location
	private Alert				alerteSupprLoc		= new Alert(AlertType.CONFIRMATION);
	
	public FenetrePlanningPanneaux() {
		
		///////////////////////////////////////////////////////////
		//Hack afin de diminuer le dlais d'affichage des tooltip//
		///////////////////////////////////////////////////////////
		
		// https://stackoverflow.com/questions/26854301/how-to-control-the-javafx-tooltips-delay
		
			    try {
			        // Get the non public field "BEHAVIOR"
			        Field fieldBehavior = Tooltip.class.getDeclaredField("BEHAVIOR");
			        // Make the field accessible to be able to get and set its value
			        fieldBehavior.setAccessible(true);
			        // Get the value of the static field
			        Object objBehavior = fieldBehavior.get(null);
			        // Get the constructor of the private static inner class TooltipBehavior
			        Constructor<?> constructor = objBehavior.getClass().getDeclaredConstructor(
			            Duration.class, Duration.class, Duration.class, boolean.class
			        );
			        // Make the constructor accessible to be able to invoke it
			        constructor.setAccessible(true);
			        // Create a new instance of the private static inner class TooltipBehavior
			        Object tooltipBehavior = constructor.newInstance(
			            new Duration(10), new Duration(5000),
			            new Duration(200), true
			        );
			        // Set the new instance of TooltipBehavior
			        fieldBehavior.set(null, tooltipBehavior);
			    } catch (Exception e) {
			        throw new IllegalStateException(e);
			    }
			    
			    
			    
			    
			    
			    
			    
		
		this.setTitle("Catalogue des panneaux");
		this.setX(10);
		this.setY(10);
		this.setResizable(true);
		this.setMinHeight(700);
		this.setMinWidth(900);
		this.getIcons().add(new Image(getClass().getResourceAsStream("logo advercity.png")));
		Scene laScene=new Scene(creerContenu());
		
		laScene.getStylesheets().add(css);
		this.setScene(laScene);
	}
	
	@SuppressWarnings("unchecked")
	Parent creerContenu() {
		
		//suppr loc
		
		alerteSupprLoc.setContentText("Voulez-vous vraiment supprimer cette location ?");
		alerteSupprLoc.setHeaderText("Confirmation");
		alerteSupprLoc.setTitle("Confirmation");
		alerteSupprLoc.getButtonTypes().setAll(bnConfirmer,bnAnnuler);
		
		/*suppression modale*/
		supprImpossible.setContentText("Suppression de la zone impossible : des panneaux sont encore contenus dans cette zone");
		supprImpossible.setHeaderText("Suppression zone impossible");
		supprImpossible.setTitle("Suppression zone impossible");
		
		
		
		warningImage.setFitHeight(25);
		warningImage.setFitWidth(25);
		warningImage.setVisible(false);
		
		alerteSupprZone.setContentText("Voulez-vous vraiment supprimer cette zone ?");
		alerteSupprZone.setHeaderText("Confirmation");
		alerteSupprZone.setTitle("Confirmation");
		
		alerteSupprZone.getButtonTypes().setAll(bnConfirmer,bnAnnuler);
		
		alerteSupprPan.setContentText("Voulez-vous vraiment supprimer cet emplacement ?");
		alerteSupprPan.setHeaderText("Confirmation");
		alerteSupprPan.setTitle("Confirmation");
		
		alerteSupprPan.getButtonTypes().setAll(bnConfirmer,bnAnnuler);
		
		/*liste zone*/
		listeZones.getColumns().add(nomZone);
		listeZones.setPlaceholder(new Label("Aucune zone"));
		listeZones.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		listeZones.setContextMenu(ContextMenuZones);
		
		nomZone.setMinWidth(100);
		
		headerPanZone.getChildren().addAll(titrePanZone,bnAjoutZone);
		headerPanZone.setAlignment(Pos.CENTER);
		
		panZone.add(headerPanZone, 0, 0);
		panZone.add(listeZones, 0, 1);
		panZone.setMinWidth(400);
		
		titrePanZone.setPadding(new Insets(10));
		titrePanZone.setFont(Font.font("verdana", FontWeight.BOLD, 15));
		
		listeZones.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		/*Dtail d'un panneau*/
		
		/*Location*/
		listeLocationsPane.getChildren().addAll(labelLocation,listeLocation);
		listeLocationsPane.setAlignment(Pos.CENTER);
		labelLocation.setAlignment(Pos.CENTER);
		listeLocationsPane.setPadding(new Insets(20,0,0,0));
		
		choixClient.getChildren().addAll(labelClient,listeClients);
		choixClient.setAlignment(Pos.CENTER);
		choixClient.setSpacing(20.0);
		
		
		listeLocation.getColumns().addAll(idLocation,dateDebLocation,dateFinLocation);
		listeLocation.setPlaceholder(new Label("Aucune location"));
		listeLocation.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		listeLocation.getSortOrder().add(idLocation);
		
		
		
		
		
		headerPanDetail.getChildren().add(titrePanDetail);
		
		titrePanDetail.setFont(Font.font("verdana", FontWeight.BOLD, 25));
		
		headerPanDetail.setAlignment(Pos.CENTER);
		
		bnAjoutReservation.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		bnModifierPanneau.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		bnSupprimerPanneau.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		GridPane.setHgrow(bnAjoutReservation, Priority.ALWAYS); 
		GridPane.setVgrow(bnAjoutReservation, Priority.ALWAYS);
		
		
		GridPane.setHgrow(bnModifierPanneau, Priority.ALWAYS); 
		GridPane.setVgrow(bnModifierPanneau, Priority.ALWAYS);
		
		GridPane.setHgrow(bnSupprimerPanneau, Priority.ALWAYS); 
		GridPane.setVgrow(bnSupprimerPanneau, Priority.ALWAYS);
		
		boutonsPanDetail.setSpacing(10);
		boutonsPanDetail.setPadding(new Insets(10));
		
		boutonsPanDetail2.setSpacing(10);
		boutonsPanDetail2.setPadding(new Insets(10));
		
		boutonsLocation.getChildren().addAll(bnModifierDureeLocation,bnSupprimerLocation);
		boutonsLocation.setSpacing(10);
		boutonsLocation.setAlignment(Pos.CENTER);
		
		
		
		rectanglePanDetail.setSpacing(10);
		rectanglePanDetail.setPadding(new Insets(20));
		
		optionsLocPanDetail.getChildren().addAll(texteDuree,texteTotal,boutonsLocation);
		optionsLocPanDetail.setPadding(new Insets(10));
		optionsLocPanDetail.setSpacing(10);
		
		rectanglePanDetail.getChildren().addAll(optionsLocPanDetail);

		optionsLocPanDetail.getStyleClass().add("border");
		rectanglePanDetail.getStyleClass().add("border-verticaux");
		
		rectanglePanDetail.setAlignment(Pos.CENTER);
		boutonsPanDetail.setAlignment(Pos.CENTER);
		boutonsPanDetail2.setAlignment(Pos.CENTER);
		
		boutonsPanDetail.getChildren().addAll(bnAjoutReservation);
		boutonsPanDetail2.getChildren().addAll(bnModifierPanneau, bnSupprimerPanneau);
		
		panDetailPanneau.add(headerPanDetail, 0, 0);
		panDetailPanneau.add(choixClient, 0, 1);
		panDetailPanneau.add(boutonsPanDetail, 0, 4);
		panDetailPanneau.add(boutonsPanDetail2, 0, 5);
		panDetailPanneau.setHgap(20);
		panDetailPanneau.setMinWidth(435);
		panDetailPanneau.setMinHeight(400);
		
		GridPane.setHgrow(headerPanDetail, Priority.ALWAYS);
		GridPane.setHgrow(rectanglePanDetail, Priority.ALWAYS);
		GridPane.setHgrow(boutonsPanDetail, Priority.ALWAYS);
		GridPane.setHgrow(boutonsPanDetail2, Priority.ALWAYS);
		GridPane.setHgrow(optionsLocPanDetail, Priority.ALWAYS);

		/*Menu de contexte slection d'un item dans la liste zone*/
		
		itemSelectOptions.setPadding(new Insets(10));
		supprZoneBtn.setPrefHeight(60);
		modifNomZoneBtn.setPrefHeight(60);
		supprZoneBtn.setMinWidth(130);
		modifNomZoneBtn.setMinWidth(180);
		
		itemSelectOptions.setHgap(10.0);
		itemSelectOptions.setVgap(10.0);
		
		/*tableau*/
		cataloguePanneaux.getColumns().addAll(idColonne,louantColonne,emplacementColonne,visibiliteColonne,tarifColonne,formatColonne);
		cataloguePanneaux.setPlaceholder(new Label("Aucun panneau"));
		cataloguePanneaux.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		cataloguePanneaux.getSortOrder().add(idColonne);
		
		idColonne.setMinWidth(50);
		louantColonne.setMinWidth(100);
		emplacementColonne.setMinWidth(150);
		visibiliteColonne.setMinWidth(100);
		tarifColonne.setMinWidth(80);
		formatColonne.setMinWidth(80);
		
		idColonne.setCellValueFactory(new PropertyValueFactory<>("numEmplacement"));
		louantColonne.setCellValueFactory(new PropertyValueFactory<>("louant"));
		emplacementColonne.setCellValueFactory(new PropertyValueFactory<>("zoneString"));
		visibiliteColonne.setCellValueFactory(new PropertyValueFactory<>("nbEtoiles"));
		tarifColonne.setCellValueFactory(new PropertyValueFactory<>("tarif"));
		formatColonne.setCellValueFactory(new PropertyValueFactory<>("format"));
		
		nomZone.setCellValueFactory(new PropertyValueFactory<>("nomZone"));
		
		idLocation.setCellValueFactory(new PropertyValueFactory<>("identifiant"));
		dateDebLocation.setCellValueFactory(new PropertyValueFactory<>("debutLocShort"));
		dateFinLocation.setCellValueFactory(new PropertyValueFactory<>("finLocShort"));
		
		/*Ajout des lments au menu*/
		menuPanneaux.getItems().addAll(menuCatalogue,menuAjoutEmplacement,menuCarte);
		menuClients.getItems().addAll(menuListeClients,menuAjoutClient);
		menuZones.getItems().addAll(menuGererZones,menuAjoutZones);
		menu.getMenus().addAll(menuPanneaux,menuZones,menuClients);
		
		/*positionnement*/
		listeZoneBtn.setMaxSize(Double.MAX_VALUE, 10);
		ajoutPanneauBtn.setMaxSize(Double.MAX_VALUE, 10);
		listeZoneBtn.setMinHeight(60);
		ajoutPanneauBtn.setMinHeight(60);
		
		GridPane.setHgrow(listeZoneBtn, Priority.ALWAYS);
		GridPane.setHgrow(ajoutPanneauBtn, Priority.ALWAYS);
		GridPane.setVgrow(cataloguePanneaux, Priority.ALWAYS);
		GridPane.setHgrow(cataloguePanneaux, Priority.ALWAYS);
		
		GridPane.setHgrow(listeZones, Priority.ALWAYS);
		GridPane.setVgrow(listeZones, Priority.ALWAYS);
		
		GridPane.setHgrow(titrePanZone, Priority.ALWAYS);
		GridPane.setHgrow(supprZoneBtn, Priority.ALWAYS);
		GridPane.setHgrow(modifNomZoneBtn, Priority.ALWAYS);
		
		GridPane.setHgrow(panPanneaux, Priority.ALWAYS);
		GridPane.setVgrow(panPanneaux, Priority.ALWAYS);
		GridPane.setHgrow(panZone, Priority.ALWAYS);
		GridPane.setVgrow(panZone, Priority.ALWAYS);
		GridPane.setHgrow(panDetailPanneau, Priority.ALWAYS);
		GridPane.setVgrow(panDetailPanneau, Priority.ALWAYS);
		
		GridPane.setHalignment(titrePanZone, HPos.CENTER);
		GridPane.setValignment(titrePanZone, VPos.CENTER);
		
		GridPane.setHalignment(supprZoneBtn, HPos.CENTER);
		GridPane.setValignment(supprZoneBtn, VPos.CENTER);
		
		GridPane.setHalignment(modifNomZoneBtn, HPos.CENTER);
		GridPane.setValignment(modifNomZoneBtn, VPos.CENTER);
		
		GridPane.setHalignment(nomZoneSelect, HPos.CENTER);
		GridPane.setValignment(nomZoneSelect, VPos.CENTER);
		
		panPanneaux.add(listeZoneBtn, 0, 1);
		panPanneaux.add(ajoutPanneauBtn, 2, 1);
		panPanneaux.add(cataloguePanneaux, 0,2,3,1);
		panPanneaux.add(planningPanneau,4,0,1,2);

		panPanneaux.setVgap(20.0);
		panPanneaux.setHgap(20.0);
		
		panPanneaux.setPadding(new Insets(10));
		panDetailPanneau.setPadding(new Insets(10));
		panZone.setPadding(new Insets(10));
		panFormat.setPadding(new Insets(10));
		
		racine.add(menu, 0, 0,2,1);
		racine.add(panPanneaux, 0, 1);
		racine.setVgap(20.0);
		racine.setHgap(20.0);
		
		menuCatalogue.setDisable(true);
		
		//Events
		
		//////////////////////
		//Menu de navigation//
		//////////////////////
		
		menuListeClients.setOnAction(e -> {
			Principale.ouvrirFenetreListeClients();
		});
		
		menuAjoutEmplacement.setOnAction(e -> {
			Principale.ouvrirFenetreAjoutPanneau();
			this.fermerPanneau();
		});
		
		menuAjoutZones.setOnAction(e -> {
			Principale.ouvrirFenetreAjoutZone();
		});
		
		menuCarte.setOnAction(e -> {
			Principale.ouvrirFenetreCarte();
		});
		
		////////////////////////
		//Bouton ajout Panneau//
		////////////////////////
		
		ajoutPanneauBtn.setOnAction(e -> {
			Principale.ouvrirFenetreAjoutPanneau();
			this.fermerPanneau();
		});
		
		////////////////////////////////////////////
		//Events concernant le dtail d'un panneau//
		////////////////////////////////////////////
		
		//Ouverture de la section dtail d'un emplacement
		cataloguePanneaux.setOnMouseClicked(e -> {
			if (cataloguePanneaux.getItems().size() !=0 && cataloguePanneaux.getSelectionModel().getSelectedIndex()!=-1) {
				if (sectionAffichee()) {
					fermerPanneau();
				}
				actualiserDetailPanneau();
				racine.add(panDetailPanneau, 1, 1);
				detailPanneauVisible=true;
			}
		});
		
		cataloguePanneaux.setOnKeyPressed(e -> {
			if (e.getCode().equals(KeyCode.DOWN) || e.getCode().equals(KeyCode.KP_DOWN)) {
				 selectionnerItemListePanneaux(cataloguePanneaux.getSelectionModel().getSelectedIndex()+1);
			}
			else if (e.getCode().equals(KeyCode.UP) || e.getCode().equals(KeyCode.KP_UP)) {
				 selectionnerItemListePanneaux(cataloguePanneaux.getSelectionModel().getSelectedIndex()-1);
			}
			else if (e.getCode().equals(KeyCode.DELETE)) {
				if (cataloguePanneaux.getItems().size()!=0 && cataloguePanneaux.getSelectionModel().getSelectedIndex()!=-1) {
					supprimerPanneau();
					actualiserDetailPanneau();
				}
			}
				
			//Suppression d'un item
		});
		
		bnSupprimerPanneau.setOnAction(e -> {
			supprimerPanneau();
			actualiserDetailPanneau();
			this.fermerPanneau();
		});
		
		bnModifierPanneau.setOnAction(e -> {
			Principale.ouvrirFenetreModifPanneau(cataloguePanneaux.getSelectionModel().getSelectedItem());
			this.fermerPanneau();
		});
		
		
		///////////////////////////////////
		//Events concernant les locations//
		///////////////////////////////////
		
		listeClients.setOnAction(e -> {
			this.actualiserDetailPanneau();
			if (!listeLocAffiche) {
				panDetailPanneau.add(listeLocationsPane, 0, 2);
				listeLocAffiche=true;
			}
		});
		
		listeLocation.setOnMouseClicked(e -> {
			selectionnerItemListeLocation();
		});
		
		bnModifierDureeLocation.setOnAction(e -> {
			Principale.ouvrirFenetreModifLoc(listeLocation.getSelectionModel().getSelectedItem());
			this.fermerPanneau();
		});
		
		bnAjoutReservation.setOnAction(e -> {
			if (listeClients.getSelectionModel().getSelectedIndex()==-1) {
				Principale.ouvrirFenetreAjoutLoc(cataloguePanneaux.getSelectionModel().getSelectedItem());
			}
			else {
				Principale.ouvrirFenetreAjoutLoc(cataloguePanneaux.getSelectionModel().getSelectedItem(),listeClients.getSelectionModel().getSelectedItem());
			}
			this.fermerPanneau();
		});

		bnSupprimerLocation.setOnAction(e -> {
			this.supprimerLocation();
			this.actualiserDetailPanneau();
			this.fermerPanneau();
		});
		
		///////////////////////////////
		//Events concernant les zones//
		///////////////////////////////
		
		//Affichage de la section liste Zones
		listeZoneBtn.setOnAction(e -> {
			if (listeZoneVisible) {
				fermerPanneau();
			}
			else {
				if (sectionAffichee()){
					fermerPanneau();
				}
				this.actualiserZones(Principale.getLesZones());
				racine.add(panZone, 1, 1);
				listeZoneVisible=true;
				menuGererZones.setDisable(true);
			}

		});
		
		menuGererZones.setOnAction(e -> {
			if (listeZoneVisible) {
				fermerPanneau();
			}
			else {
				if (sectionAffichee()){
					fermerPanneau();
				}
				this.actualiserZones(Principale.getLesZones());
				racine.add(panZone, 1, 1);
				listeZoneVisible=true;
				menuGererZones.setDisable(true);
			}
		});
		
		//Bouton ajouter une zone
		bnAjoutZone.setOnAction(e -> {
			Principale.ouvrirFenetreAjoutZone();
			this.fermerPanneau();
		});
		
		//Bouton supprimer une zone
		supprZoneBtn.setOnAction(e -> {
			supprimerItemListeZone();
			this.fermerPanneau();
		});

		//Bouton modifier une zone
		
		modifNomZoneBtn.setOnAction(e -> {
			Principale.ouvrirFenetreModifierZone(listeZones.getSelectionModel().getSelectedItem(), listeZones.getSelectionModel().getSelectedIndex());
			this.fermerPanneau();
		});
		
		//Double clic sur un item de la liste ouvre la fenetre modifier
		
		listeZones.setOnMouseClicked(e -> {
			selectionnerItemListeZone();
			if (e.getClickCount() == 2) {
				Principale.ouvrirFenetreModifierZone(listeZones.getSelectionModel().getSelectedItem(), listeZones.getSelectionModel().getSelectedIndex());
			}
		});

		//Contrles au clavier
		
		listeZones.setOnKeyPressed(e -> {
			if (e.getCode().equals(KeyCode.DOWN) || e.getCode().equals(KeyCode.KP_DOWN)) {
				 selectionnerItemListeZone(listeZones.getSelectionModel().getSelectedIndex()+1);
			}
			else if (e.getCode().equals(KeyCode.UP) || e.getCode().equals(KeyCode.KP_UP)) {
				 selectionnerItemListeZone(listeZones.getSelectionModel().getSelectedIndex()-1);
			}
			
			//Suppression d'un item
			else if (e.getCode().equals(KeyCode.DELETE)) {
				if (listePanneauxContientZone(listeZones.getSelectionModel().getSelectedItem(), Principale.getLesPanneaux()).isEmpty()) {
					supprimerItemListeZone();
				}
				else {
					supprImpossible.show();
				}
			}
		});
		
		return racine;
	}
	
    ////////////////////////////
	//Actualisation des listes//
	////////////////////////////
	
	/**
	 * Permet d'actualiser le détail des panneaux dans le planning
	 */
	public void actualiserDetailPanneau() {
		if (cataloguePanneaux.getItems().size()!=0 && cataloguePanneaux.getSelectionModel().getSelectedIndex()!=-1) {
			titrePanDetail.setText("Panneau N�"+cataloguePanneaux.getSelectionModel().getSelectedItem().getNumEmplacement());
			this.actualiserListeClients();
			this.actualiserListeLocation();
		}
		else {
			racine.getChildren().remove(panDetailPanneau);
			detailPanneauVisible=false;
		}

		selectionnerItemListeLocation();
	}
	/**
	 * Permet d'actualiser le détail d'un panneau dont l'index est passé en paramètre
	 * @param i, un int
	 */
	private void actualiserDetailPanneau(int i) {
		if (cataloguePanneaux.getItems().size()!=0 && cataloguePanneaux.getSelectionModel().getSelectedIndex()!=-1) {
			titrePanDetail.setText("Panneau N�"+cataloguePanneaux.getItems().get(i).getNumEmplacement()); 
		}
		else {
			racine.getChildren().remove(panDetailPanneau);
			detailPanneauVisible=false;
		}

	}
    /**
	 * Actualise la liste des locations
	 */
	public void actualiserListeLocation() {
		if (cataloguePanneaux.getSelectionModel().getSelectedIndex()!=-1) {
			if (listeClients.getSelectionModel().getSelectedIndex()==-1) {
				this.actualiserListeLocationPasClient();
			}
			else {
				this.actualiserListeLocationClient(listeClients.getSelectionModel().getSelectedItem());
			}
		}
	}
	
	/**
	 * Actualise la liste des panneaux passé en paramètre
	 * @param laListe, une ObservableList<Panneau>
	 */
	public void actualiserPanneaux(ObservableList<Panneau> laListe) {
		cataloguePanneaux.setItems(laListe);
		//solution trouve sur internet, car malgr le setItems, la liste panneaux se s'actualisait pas.
		cataloguePanneaux.refresh();
	    
	}
	/**
	 * Actualise la liste des zones passé en paramètre
	 * @param laListe, une ObservableList<Zone>
	 */
	public void actualiserZones(ObservableList<Zone> laListe) {
		listeZones.setItems(laListe);
	}
	
	/**
	 * Actualise le nom de la zone sélectionné
	 */
	public void actualiserNomSelectZone() {
		listeZones.getSelectionModel().selectLast();
		nomZoneSelect.setText(listeZones.getSelectionModel().getSelectedItem().toString());
	}
	
	/**
	 * 
	 */
	public void actualiserOptionsItem() {
		if (listeZones.getItems().size()!=0 && listeZones.getSelectionModel().getSelectedIndex()!=-1) {
			nomZoneSelect.setText(listeZones.getSelectionModel().getSelectedItem().toString());
		}
		else {
			panZone.getChildren().remove(itemSelectOptions);
			itemAffiche=false;
		}
	}

    /**
     * Actualise la zone passé en paramèter dans la liste de panneau passé en paramète
     * @param zone, une Zone
     * @param laListe, une ObservableList<Panneau>
    */
	public void actualiserZoneDansPanneaux(Zone zone, ObservableList<Panneau> laListe) {
		ArrayList<Integer> panneauxConcernes = listePanneauxContientZone(zone, laListe);
		for (int i=0;i<panneauxConcernes.size();i++) {
			laListe.get(panneauxConcernes.get(i)).changerZone(zone);
			laListe.set(panneauxConcernes.get(i), laListe.get(panneauxConcernes.get(i)));
		}
	}
	
	///////////////////
	//Griser lments//
	///////////////////
	
	/**
	 * permet de griser le bouton passé en paramètre
	 * @param btn, un Button
	 */
	public void griserBoutonsZone(Button btn) {
		if (listeZones.getItems().size()==0 || listeZones.getSelectionModel().getSelectedIndex()==-1) {
			btn.setDisable(true);
		}
		else {
			btn.setDisable(false);

		}
	}
	
	/**
	 * permet de griser une zone, l'item étant passé en paramètre
	 * @param item, un MenuItem
	 */
	public void griserOptionSupprZone(MenuItem item) {
		if (listeZones.getItems().size()==0 || listeZones.getSelectionModel().getSelectedIndex()==-1) {
			item.setDisable(true);
		}
		else {
			if (listePanneauxContientZone(listeZones.getSelectionModel().getSelectedItem(), Principale.getLesPanneaux()).isEmpty()) {
				item.setDisable(false);
			}
			else {
				item.setDisable(true);
			}
		}
	}
	/**
	 * @param item, un MenuItem
	 */
	public void griserOptionModifZone(MenuItem item) {
		if (listeZones.getItems().size()==0 || listeZones.getSelectionModel().getSelectedIndex()==-1) {
			item.setDisable(true);
		}
		else {
			item.setDisable(false);
		}
	}
	
	/**
	 * @param btn, un Button
	 * @param tt, un Tooltip
	 */
	public void griserBoutonsZone(Button btn, Tooltip tt) {
		if (listePanneauxContientZone(listeZones.getSelectionModel().getSelectedItem(), Principale.getLesPanneaux()).isEmpty()) {
			btn.setDisable(false);
			warningImage.setVisible(false);
		}
		else {
			btn.setDisable(true);
			warningImage.setVisible(true);
			Tooltip.install(warningImage, tt);
		}
	}
	
	/**
	 * @param btn, un Button
	 * @param tt, un Tooltip
	 * @param i, un int
	 */
	public void griserBoutonsZoneIndex(Button btn, Tooltip tt, int i) {
		if (listePanneauxContientZone(listeZones.getItems().get(i), Principale.getLesPanneaux()).isEmpty()) {
			btn.setDisable(false);
			warningImage.setVisible(false);
		}
		else {
			btn.setDisable(true);
			warningImage.setVisible(true);
			Tooltip.install(warningImage, tt);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	/////////////////////////
	//Selectionner lments//
	/////////////////////////
	public void selectionnerItemListeLocation() {
		if (listeLocation.getItems().size()!=0 && listeLocation.getSelectionModel().getSelectedIndex()!=-1) {
			texteDuree.setText("Dur�e de la location : "+listeLocation.getSelectionModel().getSelectedItem().dureeToString());
			texteTotal.setText("Total � payer : "+listeLocation.getSelectionModel().getSelectedItem().totalAPayer()+"");
			if (!choixLocAffiche) {
				panDetailPanneau.add(rectanglePanDetail, 0, 3);
				choixLocAffiche=true;
			}
		}
		else {
			panDetailPanneau.getChildren().remove(rectanglePanDetail);
			choixLocAffiche=false;
		}
	}
	
	public void selectionnerItemListeLocation(int i) {
		if (i>=0 && i<listeLocation.getItems().size()) {
			if (!choixLocAffiche) {
				panDetailPanneau.add(rectanglePanDetail, 0, 3);
				choixLocAffiche=true;
			}
		}
		else {
			panDetailPanneau.getChildren().remove(rectanglePanDetail);
		}
	}
	
	
	public void selectionnerItemListeZone() {
		if (listeZones.getSelectionModel().getSelectedItem() != null) {
			
			nomZoneSelect.setText(listeZones.getSelectionModel().getSelectedItem().toString());
			griserBoutonsZone(supprZoneBtn, tooltipSupprImpossible);
			
			if (!itemAffiche) {
				
				itemSelectOptions.add(nomZoneSelect, 0, 0,3,1);
				itemSelectOptions.add(warningImage, 0, 1);
				itemSelectOptions.add(supprZoneBtn, 1, 1);
				itemSelectOptions.add(modifNomZoneBtn, 2, 1);
				
				panZone.add(itemSelectOptions, 0, 2);
				
				itemAffiche=true;
			}
		}
		griserOptionSupprZone(optionSupprimer);
		griserOptionModifZone(optionModifier);

	}
	public void selectionnerItemListeZone(int i) {
		if (i>=0 && i<listeZones.getItems().size()) {
			if (!itemAffiche) {
				griserBoutonsZoneIndex(supprZoneBtn, tooltipSupprImpossible, i);
				nomZoneSelect.setText(listeZones.getItems().get(i).toString());
				
				itemSelectOptions.add(nomZoneSelect, 0, 0,2,1);
				itemSelectOptions.add(supprZoneBtn, 0, 1);
				itemSelectOptions.add(modifNomZoneBtn, 1, 1);
				
				panZone.add(itemSelectOptions, 0, 2);
				
				itemAffiche=true;
			}
			else {
				nomZoneSelect.setText(listeZones.getItems().get(i).toString());
				griserBoutonsZoneIndex(supprZoneBtn, tooltipSupprImpossible, i);
			}
		}
	}
	
	public void selectionnerItemListePanneaux(int i) {
		if (i>=0 && i<cataloguePanneaux.getItems().size()) {
			fermerPanneau();
			actualiserDetailPanneau(i);
			racine.add(panDetailPanneau, 1, 1);
			detailPanneauVisible=true;
		}
	}
	
	
	
	
	
	
	
	
	
	/////////
	//Tests//
	/////////
	/**
	 * Permet de sauvegarder les index des panneaux lis  une zone. Utile lors d'un changement dans la liste des Zones pour modifier galement dans la liste Panneaux
	 * @param zone, une Zone 
	 * @param laListe, une ObservableList<Panneau>
	 */
	
	public ArrayList<Integer> listePanneauxContientZone(Zone zone, ObservableList<Panneau> laListe) {
		ArrayList<Integer> panneauxDeCetteZone = new ArrayList<Integer>();
		for (int i=0;i<laListe.size();i++) {
			if (laListe.get(i).getZoneString().equals(zone.toString())) {
				panneauxDeCetteZone.add(i);
			}
		}
		return panneauxDeCetteZone;
	}
	
	
	
	
	////////////////////////
	//Gestion des sections//
	////////////////////////
	
	/**
	 * Permet de savoir si une section est affiche droite
	 */
	public boolean sectionAffichee() {
		return (listeZoneVisible || detailPanneauVisible);
	}
	
	/**
	 * 
	 * Permet de fermer la section  droite qui est ouverte, s'il y en a une
	 */
	public void fermerPanneau() {
		if (listeZoneVisible) {
			racine.getChildren().remove(panZone);
			listeZoneVisible=false;
			menuGererZones.setDisable(false);
		}
		else if (detailPanneauVisible) {
			racine.getChildren().remove(panDetailPanneau);
			detailPanneauVisible=false;
		}
	}
	
	/**
	 * Permet d'ouvrir la section zone
	 */
	public void ouvrirSectionZone() {
		this.fermerPanneau();
		racine.add(panZone, 1, 1);
		listeZoneVisible=true;
		menuGererZones.setDisable(true);
	}
	
	
	////////////////////////
	//Supprimer un lment//
	////////////////////////
	
	/**
	 *  Gere la suppresion de Location
	 */
	public void supprimerLocation() {
		Location loc = listeLocation.getSelectionModel().getSelectedItem();
		
		//Vrifie si la location est en cours
		if (loc.getFinLoc().after(Calendar.getInstance().getTime())) {
			alerteSupprLoc.setContentText("Cette location est en cours,\nvoulez-vous la supprimer ?");
		}
		Optional<ButtonType> choix = alerteSupprLoc.showAndWait();
		if(choix.get() == bnConfirmer) {
			Principale.supprimerLocation(loc);
			this.selectionnerPanneau(loc.getPanneauLoc());
			this.actualiserListeClients();
			this.actualiserListeLocation();
			this.selectionnerItemListeLocation();
		}
	}
	
	/**
	 *  Supprime un item dans la liste des zones
	 */
	public void supprimerItemListeZone() {
		Integer zone = listeZones.getSelectionModel().getSelectedIndex();
		Optional<ButtonType> choix = alerteSupprZone.showAndWait();
		if (choix.get() == bnConfirmer) {
			Principale.supprimerZone(zone);
		}
		griserBoutonsZone(supprZoneBtn);
		griserOptionSupprZone(optionSupprimer);
		griserOptionModifZone(optionModifier);
		griserBoutonsZone(supprZoneBtn, tooltipSupprImpossible);
		actualiserOptionsItem();
	}
	
	/**
	 * Gere la suppresion un Panneau dans le catalogue des panneaux
	 */
	public void supprimerPanneau() {
		Panneau itemPanneau = cataloguePanneaux.getSelectionModel().getSelectedItem();
		
		//Faire toutes les vrifs (une location est en cours, des locations sont prvues...) et afficher le msg adquat
		
		if (!itemPanneau.getPlanning().isEmpty()) {
			alerteSupprPan.setContentText("Des locations existent encore pour ce panneau,\nvoulez-vous le supprimer ?");
		}
		Optional<ButtonType> choix = alerteSupprPan.showAndWait();
		if (choix.get() == bnConfirmer) {
			Principale.supprimerPanneau(itemPanneau);
		}
	}

	
	///////////////////////////////////////
	//Afficher liste clients d'un panneau//
	///////////////////////////////////////
	
	public void actualiserListeClients() {
		ArrayList<Client> listeLouants = cataloguePanneaux.getSelectionModel().getSelectedItem().louants();
		ObservableList<Client> louants = FXCollections.observableArrayList(listeLouants);
		listeClients.setItems(louants);
	}
	public void actualiserListeLocationPasClient() {
		ArrayList<Location> listeLoc = cataloguePanneaux.getSelectionModel().getSelectedItem().getPlanning();
		ObservableList<Location> locations = FXCollections.observableArrayList(listeLoc);
		listeLocation.setItems(locations);
	}
	
	public void actualiserListeLocationClient(Client c) {
		ArrayList<Location> listeLoc = cataloguePanneaux.getSelectionModel().getSelectedItem().getPlanning();
		ArrayList<Location> listeLocCli = new ArrayList<Location>();
		for (int i=0;i<listeLoc.size();i++) {
			if (listeLoc.get(i).getClient().equals(c.getNumclient())) {
				listeLocCli.add(listeLoc.get(i));
			}
		}
		ObservableList<Location> locations = FXCollections.observableArrayList(listeLocCli);
		listeLocation.setItems(locations);
	}

	
	public void actualiserDetailsLocation() {
		if (cataloguePanneaux.getSelectionModel().getSelectedIndex()!=-1 && listeLocation.getSelectionModel().getSelectedIndex()!=-1) {
			texteTotal.setText("Total  payer : "+listeLocation.getSelectionModel().getSelectedItem().totalAPayer()+"");
			texteDuree.setText("Dure de la location : "+listeLocation.getSelectionModel().getSelectedItem().dureeToString());
		}
	}
	
	public void selectionnerPanneau(Panneau p) {
		cataloguePanneaux.getSelectionModel().select(p);
	}
	
	public void actualiserLocationsModif(Panneau pNouveau) {
		if (cataloguePanneaux.getSelectionModel().getSelectedIndex()!=-1) {
			Panneau pAncien = cataloguePanneaux.getSelectionModel().getSelectedItem();
			ArrayList<Location> locations = pAncien.getPlanning();
			for (int i=0;i<locations.size();i++) {
				locations.get(i).setPanneauLoc(pNouveau);
			}
			pNouveau.setPlanning(locations);
		}
	}
}
