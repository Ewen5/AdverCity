package application;

import java.net.URL;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class FenetreAccueil extends Stage {
	
	/* Déclaration des élments du menu */
	private MenuBar menu = new MenuBar();

	private Menu menuPanneaux = new Menu("Panneaux");
	private Menu menuClients = new Menu("Clients");
	private Menu menuZones = new Menu("Zones");
	
	private MenuItem menuListeClients = new MenuItem("Liste des clients");
	private MenuItem menuAjoutClient = new MenuItem("Ajouter un client");
	
	private MenuItem menuCatalogue = new MenuItem("Catalogue des panneaux");
	private MenuItem menuCarte = new MenuItem("Carte des emplacements");
	
	private MenuItem menuGererZones = new MenuItem("Gérer les zones");
	private MenuItem menuAjoutZones = new MenuItem("Ajouter une zone");
	
	private MenuItem menuAjoutEmplacement = new MenuItem("Ajouter un panneau");
	
	private Label titreAdvercity = new Label("AdverCity");
	private Label textAccueil = new Label("Que voulez-vous faire ?");
	private Label titrePanneaux = new Label("Panneaux");
	private Label titreClients = new Label("Clients");
	
	/* Déclaration des boutons de l'accueil */
	
	private Button btnCarte = new Button("Accéder à la carte");
	
	private Button btn1 = new Button("Consulter le catalogue des panneaux");
	private Button btn2 = new Button("Ajouter un panneau");
	private Button btn3 = new Button("Ajouter une zone");
	private Button btn4 = new Button("Ajouter une location");
	private Button btn5 = new Button("Consulter le catalogue des clients");
	private Button btn6 = new Button("Ajouter un nouveau client");
	
	/*Déclaration des éléments de positionnement*/
	private AnchorPane racine = new AnchorPane();
	private GridPane contenu = new GridPane();
	private GridPane grillePanneaux = new GridPane();
	private GridPane grilleClients = new GridPane();
	//private VBox page = new VBox();
	
	/* Chargement des images et la feuille css */
	
	private URL urlcss = this.getClass().getResource("application.css");
	private String css = urlcss.toExternalForm();
	
	/* Constructeur */
	/**
	 * Définit les paramètres de la page Accueil
	 */
	public FenetreAccueil() {
		this.setTitle("Accueil");
		this.setX(10);
		this.setY(10);
		this.setResizable(true);
		this.setMinHeight(500);
		this.setMinWidth(800);
		this.getIcons().add(new Image(getClass().getResourceAsStream("logo advercity.png")));
		Scene laScene=new Scene(creerContenu());
		
		laScene.getStylesheets().add(css);
		this.setScene(laScene);
	}
	
	/* ---- Contenu ---- */
	
	Parent creerContenu() {
		btn1.setWrapText(true);
		btn2.setWrapText(true);
		btn3.setWrapText(true);
		btn4.setWrapText(true);
		btn5.setWrapText(true);
		btn6.setWrapText(true);
		
		btn1.setTextAlignment(TextAlignment.CENTER);
		btn2.setTextAlignment(TextAlignment.CENTER);
		btn3.setTextAlignment(TextAlignment.CENTER);
		btn4.setTextAlignment(TextAlignment.CENTER);
		btn5.setTextAlignment(TextAlignment.CENTER);
		btn6.setTextAlignment(TextAlignment.CENTER);

		/*Ajout des éléments au menu*/
		menuPanneaux.getItems().addAll(menuCatalogue,menuAjoutEmplacement,menuCarte);
		menuClients.getItems().addAll(menuListeClients,menuAjoutClient);
		menuZones.getItems().addAll(menuGererZones,menuAjoutZones);
		menu.getMenus().addAll(menuPanneaux,menuZones,menuClients);
		
		AnchorPane.setRightAnchor(menu, 0.0);
		AnchorPane.setLeftAnchor(menu, 0.0);
		AnchorPane.setTopAnchor(menu, 0.0);

		//Contenu
		contenu.setPadding(new Insets(20.0));
		contenu.setAlignment(Pos.CENTER);
		AnchorPane.setRightAnchor(contenu, 0.0);
		AnchorPane.setLeftAnchor(contenu, 0.0);
		AnchorPane.setBottomAnchor(contenu, 0.0);
		AnchorPane.setTopAnchor(contenu, 25.0);
		/*Ajout de tout les éléments au contenu (GridPane)*/
		contenu.add(titreAdvercity,0,2,2,1);
		contenu.add(textAccueil,0,3,2,1);
		contenu.add(btnCarte, 0, 4,2,1);
		contenu.add(grillePanneaux, 0,5,1,1);
		contenu.add(grilleClients, 1,5,1,1);
		

		/*Parametre du contenu*/
		contenu.setHgap(70);
		contenu.setVgap(10);
		
		GridPane.setHalignment(titreAdvercity,HPos.CENTER); //positionnement titre Advercity
		GridPane.setHalignment(textAccueil,HPos.CENTER);
		GridPane.setHalignment(btnCarte,HPos.CENTER); // positionnement bouton Aide


		
		/*Parametre de la GridPane panneaux*/
		GridPane.setHalignment(titrePanneaux,HPos.CENTER);
		
		grillePanneaux.setAlignment(Pos.CENTER);
		grillePanneaux.setPadding(new Insets(50));
		grillePanneaux.setHgap(25);
		grillePanneaux.setVgap(25);
		
		GridPane.setVgrow(grillePanneaux, Priority.ALWAYS);
		
		grillePanneaux.add(titrePanneaux,0,0,2,1);
		grillePanneaux.add(btn1,0,1);
		btn1.setPrefWidth(300);
		btn1.setPrefHeight(120);
		
		grillePanneaux.add(btn2,1,1);
		btn2.setPrefWidth(300);
		btn2.setPrefHeight(120);
		
		grillePanneaux.add(btn3,0,2);
		btn3.setPrefWidth(300);
		btn3.setPrefHeight(120);
		
		grillePanneaux.add(btn4,1,2);
		btn4.setPrefWidth(300);
		btn4.setPrefHeight(120);
		
		/*Parametre de la GridPane Clients*/
		grilleClients.setAlignment(Pos.CENTER);

		GridPane.setVgrow(grilleClients, Priority.ALWAYS);
		GridPane.setHalignment(titreClients,HPos.CENTER);
		grilleClients.setPadding(new Insets(50));
		grilleClients.setHgap(25);
		grilleClients.setVgap(25);
		
		grilleClients.add(titreClients,0,0,1,1);
		grilleClients.add(btn5,0,1);
		btn5.setPrefWidth(300);
		btn5.setPrefHeight(120);
		
		grilleClients.add(btn6,0,2);
		btn6.setPrefWidth(300);
		btn6.setPrefHeight(120);
		
		racine.getChildren().addAll(menu,contenu);
		
		//Events
		
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
		
		//////////////////////
		///////Boutons////////
		//////////////////////
		
		btnCarte.setOnAction(e -> {
			Principale.ouvrirFenetreCarte();
		});
		
		btn1.setOnAction(e -> {
			Principale.ouvrirFenetrePlanningPanneau();
		});
		
		btn2.setOnAction(e -> {
			Principale.ouvrirFenetreAjoutPanneau();
		});
		
		btn3.setOnAction(e -> {
			Principale.ouvrirFenetreAjoutZone();
		});
		
		btn4.setOnAction(e -> {
			Principale.ouvrirFenetreAjoutLoc();
		});
		
		btn5.setOnAction(e -> {
			Principale.ouvrirFenetreListeClients();
		});
		
		btn6.setOnAction(e -> {
			Principale.ouvrirFenetreAjoutClient();
		});
		
		return racine;
	}
	
}
