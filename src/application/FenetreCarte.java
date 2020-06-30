package application;

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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class FenetreCarte extends Stage {
	
	//Elements du menu
	
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

	
	private Label titreSectionPanneau = new Label("Placer un panneau");
	private CheckBox existeDeja = new CheckBox("Le panneau existe déjà");
	private ComboBox<Panneau> listePanneaux = new ComboBox<Panneau>();
	
	private String panneauUrl = this.getClass().getResource("emp_neutre.png").toExternalForm();
	private ImageView imgPanneauNeutre = new ImageView(new Image(panneauUrl));
	private Image imgPNeutreIcone = new Image(panneauUrl,20,20,true,true);
	
	private String panneau1Url = this.getClass().getResource("emp_1e.png").toExternalForm();
	private ImageView imgPanneau1 = new ImageView(new Image(panneau1Url));
	private Image imgP1Icone = new Image(panneau1Url,20,20,true,true);
	
	private String panneau2Url = this.getClass().getResource("emp_2e.png").toExternalForm();
	private ImageView imgPanneau2 = new ImageView(new Image(panneau2Url));
	private Image imgP2Icone = new Image(panneau2Url,20,20,true,true);
	
	private String panneau3Url = this.getClass().getResource("emp_3e.png").toExternalForm();
	private ImageView imgPanneau3 = new ImageView(new Image(panneau3Url));
	private Image imgP3Icone = new Image(panneau3Url,20,20,true,true);
	
	private String panneau4Url = this.getClass().getResource("emp_4e.png").toExternalForm();
	private ImageView imgPanneau4 = new ImageView(new Image(panneau4Url));
	private Image imgP4Icone = new Image(panneau4Url,20,20,true,true);
	
	private String panneau5Url = this.getClass().getResource("emp_5e.png").toExternalForm();
	private ImageView imgPanneau5 = new ImageView(new Image(panneau5Url));
	private Image imgP5Icone = new Image(panneau5Url,20,20,true,true);
	
	private String interditUrl = this.getClass().getResource("croix_rouge.png").toExternalForm();
	private ImageView imgInterdit = new ImageView(new Image(interditUrl));
	private Image imgInterditIcone = new Image(interditUrl,20,20,true,true);
	
	private Button itemPanneau = new Button("",imgPanneauNeutre);
	
	private Button itemIndisponible = new Button("",imgInterdit);
	
	private Label titreSectionIndisponibles = new Label("Interdire des emplacements");
	
	
	private Integer[] listeCoordIndisponibles = {0,17,0,18,1,18,1,19,2,19,2,20,2,21,2,22,2,23,3,22,3,23,3,24,3,25,3,26,3,27,3,28,4,25,4,26,4,27,4,28,4,29,5,28,5,29};
	private ArrayList<Paire> casesIndisponibles = new ArrayList<Paire>();
	
	private ArrayList<Paire> casesPanneaux = new ArrayList<Paire>();
	
	private ArrayList<Panneau> panneauxPoses = new ArrayList<Panneau>();
	
	private VBox sectionPanneau = new VBox();
	private VBox sectionCasesIndisponibles = new VBox();
	private VBox sectionGauche = new VBox();
	
	private GridPane carte = new GridPane();
	private BorderPane racine = new BorderPane();
	private VBox containerCarte = new VBox();
	
	
	//Detail panneau
	private VBox listeLocationsPane = new VBox(10);
	private GridPane panDetailPanneau = new GridPane();
	private VBox headerPanDetail = new VBox();
	private VBox rectanglePanDetail = new VBox();
	private HBox boutonsPanDetail = new HBox();
	private HBox boutonsPanDetail2 = new HBox();
	private HBox boutonsLocation = new HBox();
	private VBox optionsLocPanDetail = new VBox();
	
	private HBox choixClient = new HBox();
	
	/*Detail d'un panneau*/
	private Label titrePanDetail = new Label();

	private Button bnAjoutReservation = new Button("Ajouter location...");
	
	private Button bnSupprimerPanneau = new Button("Supprimer panneau");
	private Button bnModifierPanneau = new Button("Modifier panneau...");
	
	private Button enleverPanneauCarte = new Button("Enlever panneau de la carte");
	
	/*Location*/
	private ComboBox<Client> listeClients = new ComboBox<Client>();
	private Label labelClient = new Label("Client :");
	
	private Label labelLocation = new Label("Liste des locations :");
	private TableView<Location> listeLocation = new TableView<Location>();
	private TableColumn<Location, String> idLocation = new TableColumn<Location, String>("Identifiant");
	private TableColumn<Location, Date> dateDebLocation = new TableColumn<Location, Date>("Date de début");
	private TableColumn<Location, Date> dateFinLocation = new TableColumn<Location, Date>("Date de fin");
	
	private Button bnModifierDureeLocation = new Button("Modifier location...");
	private Button bnSupprimerLocation = new Button("Supprimer location");
	
	private Label texteDuree = new Label("Durée de la location :");
	private Label texteTotal = new Label("Total à payer :");
	
	
	
	private URL urlcss = this.getClass().getResource("application.css");
	private String css = urlcss.toExternalForm();
	
	
	private String dragAndDropType;
	
	private Panneau panneauCache = null;
	private Paire caseCache = new Paire();
	private StackPane celluleCache;
	
	private boolean choixCaseVisible = false;
	private Button enleverCaseIndCarte = new Button("Enlever case indisponible de la carte");
	
	private boolean detailPanneauVisible = false;
	private boolean listeLocAffiche = false;
	private boolean choixLocAffiche = false;
	
	//Fenetre alerte ajout impossible
	private Alert alerteAjout = new Alert(AlertType.ERROR);
	
	
	/*modale suppression*/
	private Alert				alerteSupprPan		= new Alert(AlertType.CONFIRMATION);
	private ButtonType			bnConfirmer		= new ButtonType("Oui");
	private ButtonType			bnAnnuler		= new ButtonType("Non");
	
	//Suppression location
	private Alert				alerteSupprLoc		= new Alert(AlertType.CONFIRMATION);

    /**
	 * DÃ©finit les parametre de la fenetre Carte
	 */
	public FenetreCarte() {
		this.setTitle("Carte interactive");
		this.setX(10);
		this.setY(10);
		this.setResizable(true);
		this.setMinHeight(700);
		this.setMinWidth(1350);
		this.getIcons().add(new Image(getClass().getResourceAsStream("logo advercity.png")));
		Scene laScene=new Scene(creerContenu());
		
		laScene.getStylesheets().add(css);
		this.setScene(laScene);
	}
	
	@SuppressWarnings("unchecked")
	Parent creerContenu() {
		alerteAjout.setTitle("Ajout impossible");
		alerteAjout.setHeaderText("Ajout impossible");
		
		//Alerte supression panneau
		
		alerteSupprPan.setContentText("Voulez-vous vraiment supprimer cet emplacement ?");
		alerteSupprPan.setHeaderText("Confirmation");
		alerteSupprPan.setTitle("Confirmation");
		
		alerteSupprPan.getButtonTypes().setAll(bnConfirmer,bnAnnuler);
		
		/*Ajout des lments au menu*/
		menuPanneaux.getItems().addAll(menuCatalogue,menuAjoutEmplacement,menuCarte);
		menuClients.getItems().addAll(menuListeClients,menuAjoutClient);
		menuZones.getItems().addAll(menuGererZones,menuAjoutZones);
		menu.getMenus().addAll(menuPanneaux,menuZones,menuClients);
		
		menuCarte.setDisable(true);
		
		
		
		//Ajout de la liste des coordonnes  une arraylist
		for (int h=0;h<listeCoordIndisponibles.length;h+=2) {
			casesIndisponibles.add(new Paire(listeCoordIndisponibles[h],listeCoordIndisponibles[h+1]));
		}
		
		for (int i=0;i<30;i++) {
			for (int j=0;j<30;j++) {
				StackPane cellule = new StackPane();
				cellule.setPrefSize(20, 20);
				carte.add(cellule, i, j);
				cellule.getStyleClass().add("cellule");
				for (int k=0;k<casesIndisponibles.size();k++) {
					if (casesIndisponibles.get(k).equals(i, j)) {
						cellule.getStyleClass().add("indisponible");
						Tooltip.install(cellule, new Tooltip("Impossible de poser un panneau sur cette case"));
					}
						cellule.setOnDragOver(e -> {
								if (typeCase(cellule).compareTo("vide")==0) {
									e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
								}
						});
						cellule.setOnDragDropped(e -> {
								if (dragAndDropType.compareTo("indisponible")==0) {
									cellule.getStyleClass().add("indisponible");
									Tooltip.install(cellule, new Tooltip("Impossible de poser un panneau sur cette case"));
									casesIndisponibles.add(new Paire(GridPane.getColumnIndex(cellule),GridPane.getRowIndex(cellule)));
									e.setDropCompleted(true);
								}
								else if (dragAndDropType.compareTo("panneauExiste")==0) {
									Panneau p = listePanneaux.getSelectionModel().getSelectedItem();
									cellule.getStyleClass().add("etoiles_"+p.getNbEtoiles());
									Tooltip.install(cellule, new Tooltip("ID : "+p.getNumEmplacement()+
											"\nZone : "+p.getZoneString()+
											"\nLouant : "+p.getLouant()+
											"\nTarif : "+p.getTarif()+
											"\nFormat : "+p.getFormat()+
											"\nVisibilité : "+p.getNbEtoiles()+"/5"
											));
									p.setCoordonnes(new Paire(GridPane.getColumnIndex(cellule),GridPane.getRowIndex(cellule)));
									casesPanneaux.add(p.getCoordonnees());
									panneauxPoses.add(p);
									e.setDropCompleted(true);
									this.actualiserListePanneaux(Principale.getLesPanneaux());
								}
								else if (dragAndDropType.compareTo("panneauExistePas")==0) {
									Panneau p = Principale.ouvrirFenetreAjoutPanneauCarte();
									if (p!=null) {
										cellule.getStyleClass().add("etoiles_"+p.getNbEtoiles());
										Tooltip.install(cellule, new Tooltip("ID : "+p.getNumEmplacement()+
												"\nZone : "+p.getZoneString()+
												"\nLouant : "+p.getLouant()+
												"\nTarif : "+p.getTarif()+
												"\nFormat : "+p.getFormat()+
												"\nVisibilité : "+p.getNbEtoiles()+"/5"
												));
										p.setCoordonnes(new Paire(GridPane.getColumnIndex(cellule),GridPane.getRowIndex(cellule)));
										casesPanneaux.add(p.getCoordonnees());
										panneauxPoses.add(p);
										e.setDropCompleted(true);
										this.actualiserListePanneaux(Principale.getLesPanneaux());
									}
								}
						});
						
						cellule.setOnMouseClicked(e -> {
							caseCache.setCoordonnees(GridPane.getColumnIndex(cellule),GridPane.getRowIndex(cellule));
							celluleCache=cellule;
							if (detailPanneauVisible) {
								fermerPanneau();
							}
							
							Panneau p = this.cherchePanneauCoordonnees(GridPane.getColumnIndex(cellule),GridPane.getRowIndex(cellule));
							if (p!=null) {
								panneauCache=p;
								actualiserDetailPanneau();
								racine.setRight(panDetailPanneau);
								detailPanneauVisible=true;
							}
							
							else if (cellule.getStyleClass().contains("indisponible")) {
								racine.setRight(enleverCaseIndCarte);
								choixCaseVisible=true;
							}
						});
				}
			}
		}
		//carte.setGridLinesVisible(true);
		carte.setId("carte");
		
		carte.setAlignment(Pos.CENTER_LEFT);
		
		containerCarte.getChildren().add(carte);
		listePanneaux.setDisable(true);
		
		titreSectionPanneau.setPadding(new Insets(10));
		titreSectionPanneau.setFont(Font.font("verdana", FontWeight.BOLD, 15));
		
		imgPanneauNeutre.setFitHeight(50);
		imgPanneauNeutre.setFitWidth(50);
		
		imgPanneau1.setFitHeight(50);
		imgPanneau1.setFitWidth(50);
		
		imgPanneau2.setFitHeight(50);
		imgPanneau2.setFitWidth(50);
		
		imgPanneau3.setFitHeight(50);
		imgPanneau3.setFitWidth(50);
		
		imgPanneau4.setFitHeight(50);
		imgPanneau4.setFitWidth(50);
		
		imgPanneau5.setFitHeight(50);
		imgPanneau5.setFitWidth(50);
		
		imgInterdit.setFitHeight(50);
		imgInterdit.setFitWidth(50);
		
		sectionPanneau.getChildren().addAll(titreSectionPanneau,existeDeja,listePanneaux,itemPanneau);
		sectionPanneau.setAlignment(Pos.CENTER);
		sectionPanneau.setSpacing(10);
		
		titreSectionIndisponibles.setPadding(new Insets(10));
		titreSectionIndisponibles.setFont(Font.font("verdana", FontWeight.BOLD, 15));
		
		sectionCasesIndisponibles.getChildren().addAll(titreSectionIndisponibles,itemIndisponible);
		sectionCasesIndisponibles.setAlignment(Pos.CENTER);
		sectionCasesIndisponibles.setSpacing(10);
		
		sectionGauche.getChildren().addAll(sectionPanneau, sectionCasesIndisponibles);
		sectionGauche.setPadding(new Insets(20.0));
		
		
		//Detail d'un panneau
		//suppr loc
		
		alerteSupprLoc.setContentText("Voulez-vous vraiment supprimer cette location ?");
		alerteSupprLoc.setHeaderText("Confirmation");
		alerteSupprLoc.setTitle("Confirmation");
		alerteSupprLoc.getButtonTypes().setAll(bnConfirmer,bnAnnuler);
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

		idLocation.setCellValueFactory(new PropertyValueFactory<>("identifiant"));
		dateDebLocation.setCellValueFactory(new PropertyValueFactory<>("debutLocShort"));
		dateFinLocation.setCellValueFactory(new PropertyValueFactory<>("finLocShort"));
		
		
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
		panDetailPanneau.add(enleverPanneauCarte, 0, 6);
		panDetailPanneau.setHgap(20);
		panDetailPanneau.setMinWidth(435);
		panDetailPanneau.setMinHeight(400);
		
		GridPane.setHgrow(headerPanDetail, Priority.ALWAYS);
		GridPane.setHgrow(rectanglePanDetail, Priority.ALWAYS);
		GridPane.setHgrow(boutonsPanDetail, Priority.ALWAYS);
		GridPane.setHgrow(boutonsPanDetail2, Priority.ALWAYS);
		GridPane.setHgrow(optionsLocPanDetail, Priority.ALWAYS);
		
		
		enleverPanneauCarte.setAlignment(Pos.CENTER);
		BorderPane.setMargin(enleverCaseIndCarte, new Insets(20.0));
		
		GridPane.setHalignment(enleverPanneauCarte, HPos.CENTER);
		GridPane.setMargin(enleverPanneauCarte, new Insets(10.0));
		
		racine.setTop(menu);
		racine.setCenter(containerCarte);
		racine.setLeft(sectionGauche);
		
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
		
		////////////////////////
		//Slection du panneau//
		////////////////////////
		
		existeDeja.setOnAction(e -> {
			if (existeDeja.isSelected()) {
				listePanneaux.setDisable(false);
				colorerBoutonPanneau();
			}
			else {
				listePanneaux.setDisable(true);
				itemPanneau.setGraphic(imgPanneauNeutre);
			}
		});
		
		listePanneaux.setOnAction(e -> {
			colorerBoutonPanneau();
		});
		
		
		
		
		/////////////////
		//Drag and drop//
		/////////////////
		
		//Bouton case indisponible
		itemIndisponible.setOnDragDetected(e -> {
			Dragboard db = itemIndisponible.startDragAndDrop(TransferMode.COPY_OR_MOVE);
			ClipboardContent content = new ClipboardContent();
			content.putImage(imgInterditIcone);
			db.setContent(content);
			dragAndDropType="indisponible";
		});
		
		itemPanneau.setOnDragDetected(e -> {
			Dragboard db = itemPanneau.startDragAndDrop(TransferMode.COPY_OR_MOVE);
			ClipboardContent content = new ClipboardContent();
			if (existeDeja.isSelected()) {
				if (listePanneaux.getItems().size()>0 && listePanneaux.getSelectionModel().getSelectedIndex()!=-1) {
					switch(listePanneaux.getSelectionModel().getSelectedItem().getNbEtoiles()) {
					case 1:
						content.putImage(imgP1Icone);
						break;
					case 2:
						content.putImage(imgP2Icone);
						break;
					case 3:
						content.putImage(imgP3Icone);
						break;
					case 4:
						content.putImage(imgP4Icone);
						break;
					case 5:
						content.putImage(imgP5Icone);
						break;
					default:
						content.putImage(imgPNeutreIcone);
					}
					dragAndDropType="panneauExiste";
				}
				else {
					content.putImage(imgPNeutreIcone);
					dragAndDropType="panneauExistePas";
				}
			}
			else {
				content.putImage(imgPNeutreIcone);
				dragAndDropType="panneauExistePas";
			}
			db.setContent(content);

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
				Principale.ouvrirFenetreAjoutLoc(panneauCache);
			}
			else {
				Principale.ouvrirFenetreAjoutLoc(panneauCache,listeClients.getSelectionModel().getSelectedItem());
			}
			this.fermerPanneau();
		});
		
		bnSupprimerLocation.setOnAction(e -> {
			this.supprimerLocation();
			this.actualiserDetailPanneau();
			this.fermerPanneau();
		});
		
		////////////////////////////////
		//Events concernant un panneau//
		////////////////////////////////
		
		bnSupprimerPanneau.setOnAction(e -> {
			supprimerPanneau();
			this.actualiserListePanneaux(Principale.getLesPanneaux());
		});
		
		bnModifierPanneau.setOnAction(e -> {
			Principale.ouvrirFenetreModifPanneau(panneauCache);
			this.modifierPanneau();
			//this.actualiserListePanneaux(Principale.getLesPanneaux());
		});
		
		////////////////////////////////////////////////
		//Events de suppression d'une case de la carte//
		////////////////////////////////////////////////
		
		enleverCaseIndCarte.setOnAction(e -> {
			Node resultat = null;
			ObservableList<Node> cellules = carte.getChildren();
			
			for (Node node : cellules) {
				if (caseCache.equals(GridPane.getColumnIndex(node), GridPane.getRowIndex(node))) {
					resultat = node;
					break;
				}
			}
			
			if (resultat!=null) {
				resultat.getStyleClass().remove("indisponible");
				Tooltip.uninstall(resultat, new Tooltip());
				casesIndisponibles.remove(caseCache);
				this.fermerPanneau();
			}
		});
		
		
		enleverPanneauCarte.setOnAction(e -> {
			this.enleverPanneauCarte();
		});
		
		return racine;
	}
	
	/**
	 * GÃ¨re l'affichage des informations a droite de la fenÃªtre
	 */
	public void cacherDroite() {
		if (panneauCache==null) {
			panDetailPanneau.getChildren().remove(listeLocationsPane);
			racine.getChildren().remove(panDetailPanneau);
			detailPanneauVisible=false;
			listeLocAffiche=false;
			choixLocAffiche=false;
		}
	}
	
	/**
	 * Permet de retirer un panneau de la carte
	 */
	public void enleverPanneauCarte() {
		Node resultat = null;
		ObservableList<Node> cellules = carte.getChildren();
		
		for (Node node : cellules) {
			if (panneauCache.getCoordonnees().equals(GridPane.getColumnIndex(node), GridPane.getRowIndex(node))) {
				resultat = node;
				break;
			}
		}
		
		if (resultat!=null) {
			resultat.getStyleClass().remove("etoiles_"+panneauCache.getNbEtoiles());
			Tooltip.uninstall(resultat, new Tooltip());
			casesPanneaux.remove(panneauCache.getCoordonnees());
			panneauxPoses.remove(panneauCache);
			panneauCache.setCoordonnes(null);
			this.fermerPanneau();
			Principale.modifierPanneau(panneauCache);
			actualiserListePanneaux(Principale.getLesPanneaux());
		}
	}
	
	/**
	 * Permet de retirer un panneau de la carte sans actualiser la liste des panneaux
	 */
	public void enleverPanneauCarteSansMajCoo() {
		Node resultat = null;
		ObservableList<Node> cellules = carte.getChildren();
		
		for (Node node : cellules) {
			if (panneauCache.getCoordonnees().equals(GridPane.getColumnIndex(node), GridPane.getRowIndex(node))) {
				resultat = node;
				break;
			}
		}
		
		if (resultat!=null) {
			resultat.getStyleClass().remove("etoiles_"+panneauCache.getNbEtoiles());
			Tooltip.uninstall(resultat, new Tooltip());
			casesPanneaux.remove(panneauCache.getCoordonnees());
			panneauxPoses.remove(panneauCache);
			panneauCache.setCoordonnes(null);
			this.fermerPanneau();
		}
	}
	
	/**
	 * Actualise la liste des panneaux
	 * qui est passÃ© en parametre
	 * @param laListe, une ObservableList<Panneau>
	 */
	public void actualiserListePanneaux(ObservableList<Panneau> laListe) {
		listePanneaux.getItems().clear();
		for (int i=0;i<laListe.size();i++) {
			if (laListe.get(i).getCoordonnees()==null) {
				listePanneaux.getItems().add(laListe.get(i));
			}
		}
		this.colorerBoutonPanneau();
	}
	
	/**
	 * Permet d'ajouter Ã  casesIndisponibles une Paire p.
	 * @param p, une Paire
	 */
	public void ajouterCaseIndisponible(Paire p) {
		casesIndisponibles.add(p);
	}
	
	/**
	 * Permet de colorer les boutons des panneaux en fonction du nombre d'Ã©toiles qu'ils ont.
	 */
	public void colorerBoutonPanneau() {
		if (listePanneaux.getItems().size()>0 && listePanneaux.getSelectionModel().getSelectedIndex()!=-1) {
			switch(listePanneaux.getSelectionModel().getSelectedItem().getNbEtoiles()) {
			case 1:
				itemPanneau.setGraphic(imgPanneau1);
				break;
			case 2:
				itemPanneau.setGraphic(imgPanneau2);
				break;
			case 3:
				itemPanneau.setGraphic(imgPanneau3);
				break;
			case 4:
				itemPanneau.setGraphic(imgPanneau4);
				break;
			case 5:
				itemPanneau.setGraphic(imgPanneau5);
				break;
			default:
				itemPanneau.setGraphic(imgPanneauNeutre);
			}
		}
		else {
			itemPanneau.setGraphic(imgPanneauNeutre);
		}
	}
	
	/////////////////
	//Drag and drop//
	/////////////////
	
	/**
	 * @param cellule, un StackPane
	 * @return un String qui permet de savoir si la case est vide / indisponible ou si il y a un panneau Ã  cet endroit
	 */
	public String typeCase(StackPane cellule) {
		Integer y = GridPane.getRowIndex(cellule);
		Integer x = GridPane.getColumnIndex(cellule);
		
		String type = "vide";
		//Test afin de voir si la case n'est pas dans la liste des cases indisponibles
		boolean caseImpossible = false;
		int i=0;
		while (i<casesIndisponibles.size() && !caseImpossible) {
			if (casesIndisponibles.get(i).equals(x, y)) {
				caseImpossible=true;
				type="indisponible";
			}
			else {
				i++;
			}
		}
		

		if (!caseImpossible) {
			//Test afin de voir si la case n'est pas dans la liste des cases où un panneau est déjà posé
			int j=0;
			boolean caseDejaPanneau = false;
			while (j<casesPanneaux.size() && !caseDejaPanneau) {
				if (casesPanneaux.get(j).equals(x, y)) {
					caseDejaPanneau=true;
					type="panneau";
				}
				else {
					j++;
				}
			}
		}
		return type;
	}
	
	////////////////////////
	//Gestion des sections//
	////////////////////////
	
	//Permet de savoir si une section est affiche  droite
	
	//Permet de fermer la section  droite qui est ouverte, s'il y en a une
	/**
	 * Permet de savoir si une section est affichÃ©e Ã  droite, si il y en a une ouverte, permet de la fermer
	 */
	public void fermerPanneau() {
		if (detailPanneauVisible) {
			racine.getChildren().remove(panDetailPanneau);
			detailPanneauVisible=false;
		}
		else if (choixCaseVisible) {
			racine.getChildren().remove(enleverCaseIndCarte);
			choixCaseVisible=false;
		}
	}
	
	
	
	////////////////////////////
	//Actualisation des listes//
	////////////////////////////
	/**
	 * Actualise le dÃ©tail d'un panneau
	 */
	public void actualiserDetailPanneau() {
		titrePanDetail.setText("Panneau N°"+panneauCache.getNumEmplacement());
		this.actualiserListeClients();
		this.actualiserListeLocation();
		selectionnerItemListeLocation();
	}
	
	///////////////////////////////////////
	//Afficher liste clients d'un panneau//
	///////////////////////////////////////
	/**
	 * Actualise la liste des clients
	 */
	public void actualiserListeClients() {
		ArrayList<Client> listeLouants = panneauCache.louants();
		ObservableList<Client> louants = FXCollections.observableArrayList(listeLouants);
		listeClients.setItems(louants);
	}
	
	/**
	 * Actualise la liste des Locations
	 */
	public void actualiserListeLocation() {
		if (panneauCache!=null) {
			if (listeClients.getSelectionModel().getSelectedIndex()==-1) {
				this.actualiserListeLocationPasClient();
			}
			else {
				this.actualiserListeLocationClient(listeClients.getSelectionModel().getSelectedItem());
			}
		}
	}
	
	/**
	 * Actualise la liste des locations mais pas les clients
	 */
	public void actualiserListeLocationPasClient() {
		ArrayList<Location> listeLoc = panneauCache.getPlanning();
		ObservableList<Location> locations = FXCollections.observableArrayList(listeLoc);
		listeLocation.setItems(locations);
	}
	
	/**
	 * Actualise la liste des locations des clients
	 * @param c, un Client
	 */
	public void actualiserListeLocationClient(Client c) {
		ArrayList<Location> listeLoc = panneauCache.getPlanning();
		ArrayList<Location> listeLocCli = new ArrayList<Location>();
		for (int i=0;i<listeLoc.size();i++) {
			if (listeLoc.get(i).getClient().equals(c.getNumclient())) {
				listeLocCli.add(listeLoc.get(i));
			}
		}
		ObservableList<Location> locations = FXCollections.observableArrayList(listeLocCli);
		listeLocation.setItems(locations);
	}
	
	/////////////////////////
	//Slectionner lments//
	/////////////////////////
	/**
	 * Permet de sÃ©lectionner les locations et d'avoir des informations sur elles. Ou de fermer les dÃ©tails !
	 */
	public void selectionnerItemListeLocation() {
		if (listeLocation.getItems().size()!=0 && listeLocation.getSelectionModel().getSelectedIndex()!=-1) {
			texteDuree.setText("Durée de la location : "+listeLocation.getSelectionModel().getSelectedItem().dureeToString());
			texteTotal.setText("Total à payer : "+listeLocation.getSelectionModel().getSelectedItem().totalAPayer()+"");
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
	
	///////////////////////////////////////////
	//Cherche panneau  partir de coordonnes//
	///////////////////////////////////////////
	/**
	 * @param x1, un Integer
	 * @param y1, un Integer
	 * @return un Panneau en fonction des coordonnÃ©es x1 et y1
	 */
	public Panneau cherchePanneauCoordonnees(Integer x1, Integer y1) {
		Panneau p = null;
		boolean trouve = false;
		int i=0;
		while (i<panneauxPoses.size() && !trouve) {
			if (panneauxPoses.get(i).getCoordonnees().equals(x1, y1)) {
				trouve=true;
				p=panneauxPoses.get(i);
			}
			else {
				i++;
			}
		}
		return p;
	}
	
	/**
	 * Permet de modifier les informations d'un ancien panneau via un nouveau panneau
	 * @param pNouveau, un Panneau
	 */
	public void actualiserLocationsModif(Panneau pNouveau) {
		if (panneauCache!=null) {
			Panneau pAncien = panneauCache;
			ArrayList<Location> locations = pAncien.getPlanning();
			for (int i=0;i<locations.size();i++) {
				locations.get(i).setPanneauLoc(pNouveau);
			}
			pNouveau.setPlanning(locations);
		}
	}
	
	/**
	 * Actualise les dÃ©tails d'une location (grace au panneauCache)
	 */
	public void actualiserDetailsLocation() {
		if (panneauCache!=null && choixLocAffiche) {
			texteTotal.setText("Total à payer : "+listeLocation.getSelectionModel().getSelectedItem().totalAPayer()+"");
			texteDuree.setText("Durée de la location : "+listeLocation.getSelectionModel().getSelectedItem().dureeToString());
		}
	}
	
	/**
	 * Actualise le cache de panneau passÃ© en paramÃ¨tre
	 * @param p, un Panneau
	 */
	public void actualiserPanneauCache(Panneau p) {
		panneauCache=p;
	}
	
	/**
	 * Permet de sÃ©lectionner une Location passÃ© en paramÃ¨tre
	 * @param loc, une Location
	 */
	public void selectionnerLocation(Location loc) {
		listeLocation.getSelectionModel().select(loc);
	}
	
	/**
	 * Permet d'ajouter un panneau Ã  la carte, le panneau est passÃ© en paramÃ¨tre
	 * @param p, un Panneau
	 */
	public void ajouterPanneauCarte(Panneau p) {
		celluleCache.getStyleClass().add("etoiles_"+p.getNbEtoiles());
		Tooltip.install(celluleCache, new Tooltip("ID : "+p.getNumEmplacement()+
				"\nZone : "+p.getZoneString()+
				"\nLouant : "+p.getLouant()+
				"\nTarif : "+p.getTarif()+
				"\nFormat : "+p.getFormat()+
				"\nVisibilité : "+p.getNbEtoiles()+"/5"
				));
		p.setCoordonnes(new Paire(GridPane.getColumnIndex(celluleCache),GridPane.getRowIndex(celluleCache)));
		casesPanneaux.add(p.getCoordonnees());
		panneauxPoses.add(p);
		this.actualiserListePanneaux(Principale.getLesPanneaux());
	}
	
	/**
	 * Permet de modifier un Panneau sur la carte
	 */
	public void modifierPanneau() {
		this.enleverPanneauCarte();
		this.ajouterPanneauCarte(Principale.getPanneauCacheCarte());
		panneauCache=null;
		this.cacherDroite();
	}
	
	/**
	 * Permet de supprimer un panneau, un message alternatif s'affichera si le panneau appartient Ã  une Location.
	 */
	public void supprimerPanneau() {
		//Faire toutes les vrifs (une location est en cours, des locations sont prvues...) et afficher le msg adquat
		if (panneauCache!=null) {
			if (!panneauCache.getPlanning().isEmpty()) {
				alerteSupprPan.setContentText("Des locations existent encore pour ce panneau,\nvoulez-vous le supprimer ?");
			}
			Optional<ButtonType> choix = alerteSupprPan.showAndWait();
			if (choix.get() == bnConfirmer) {
				Principale.supprimerPanneau(panneauCache);
				this.enleverPanneauCarteSansMajCoo();
				panneauCache=null;
				cacherDroite();
			}
		}
	}
	
	/**
	 * Permet de supprimer une Location, un message alternatif s'affichera si la Location est en cours.
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
			this.actualiserListeClients();
			this.actualiserListeLocation();
			this.selectionnerItemListeLocation();
		}
	}
}
