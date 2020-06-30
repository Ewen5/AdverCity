package application;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FenetreAjoutLocation extends Stage {
	
	
	
	// les composants de la fentre
		private VBox 			racine				= new VBox();
		private HBox 			zoneBoutons 		= new HBox(20);
		private VBox 			zoneChoix 			= new VBox();
		private HBox 			choixPanneau 		= new HBox();
		private HBox 			choixClient 		= new HBox();
		private GridPane		zoneDate			= new GridPane();

		
		private Label 			lblChoixP		= new Label("Panneau (*) : ");
		private Label 			lblChoixC		= new Label("Client (*) : ");
		
		private ComboBox<Panneau> listePanneau 		= new ComboBox<Panneau>();
		private ComboBox<Client> listeClient		= new ComboBox<Client>();

		
		private Label			lblDateDebut		= new Label("Date de dÈbut (*) : ");
		private Label			lblDateFin			= new Label("Date de fin (*) : ");

		private DatePicker		dateFin			= new DatePicker();
		private DatePicker		dateDebut		= new DatePicker();
		
		private Label			lblInstruction		= new Label("(*) saisie obligatoire");
		private Button 			bnOK				= new Button("Valider");
		private Button 			bnAnnuler			= new Button("Annuler");
		

		private boolean listePanneauVide;
		private boolean listeClientVide;

		//Tooltip
		private Tooltip tooltipLoc					= new Tooltip();
		private Tooltip tooltipClient				= new Tooltip();
		private Tooltip tooltipPanneau 				= new Tooltip();
		
		//Booleens pour verifier que les champs sont remplis

		private boolean panneauOK = false;
		private boolean clientOK = false;
		private boolean dateOK = false;

		
		
		private URL urlWarning = this.getClass().getResource("warning.png");
		private String warningspath = urlWarning.toExternalForm();
		private Image warningImg = new Image(warningspath,25,25,true,true);
		
		private ImageView warningImageLoc = new ImageView(warningImg);
		private ImageView warningImageClient = new ImageView(warningImg);
		private ImageView warningImagePanneau = new ImageView(warningImg);

				
		// constructeur : initialisation de la fentre
		/**
		 * D√©finit les parametre de la page AjoutLocation
		 */
		public FenetreAjoutLocation(){
			this.setTitle("Ajouter une location");
			this.setResizable(false);
			this.setScene(new Scene(creerContenu()));
			this.sizeToScene();
		}
		
		// cration du Scene graph
		private Parent creerContenu() {
			this.bnOK.setPrefWidth(100);
			this.bnAnnuler.setPrefWidth(100);
			this.bnOK.setDisable(true);
			
			// ZONE DE SAISIE
			
			//ZONE SAISIE DATE
			
			zoneDate.setVgap(10);
			zoneDate.setHgap(20);
			zoneDate.add(lblDateDebut, 0, 0);
			zoneDate.add(lblDateFin, 1, 0);
			zoneDate.add(dateDebut, 0, 1);
			zoneDate.add(dateFin, 1, 1);
			zoneDate.add(warningImageLoc, 2, 1);
			
			// ZONE DE BOUTON VALIDER OU NON
			
			zoneBoutons.getChildren().addAll(bnAnnuler, bnOK);
			zoneBoutons.setAlignment(Pos.CENTER_RIGHT);
			zoneBoutons.setSpacing(20.0);

			// COMBOX LISTE DEROULANTE
			listePanneau.setMinWidth(150);
			listeClient.setMinWidth(150);

			// AJOUT DE LA COMBOX DANS UNE HBOX AVEC LE TITRE
			choixPanneau.getChildren().addAll(lblChoixP, listePanneau,warningImagePanneau);		
			choixClient.getChildren().addAll(lblChoixC, listeClient,warningImageClient);		

			choixPanneau.setSpacing(20.0);
			choixClient.setSpacing(20.0);
			
			//INSTALLATION DES TOOLTIP
			Tooltip.install(warningImageClient, tooltipClient);
			Tooltip.install(warningImagePanneau, tooltipPanneau);
			Tooltip.install(warningImageLoc, tooltipLoc);
			
			tooltipLoc.setText("Les dates de dÈbut et de fin doivent Ítre renseignÈes");
			
			// AJOUT DES 2 COMBOX ET TITRE DANS ZONE DE CHOIX
			zoneChoix.getChildren().addAll(choixPanneau, choixClient);	
			zoneChoix.setSpacing(20.0);
			
			// ASSEMBLAGE DE TOUTES LES PARTIES
			racine.getChildren().addAll(zoneChoix, zoneDate, lblInstruction,  zoneBoutons);
			VBox.setMargin(zoneDate, new Insets(20, 20, 20, 20));
			VBox.setMargin(zoneChoix, new Insets(20, 20, 20, 20));
			VBox.setMargin(lblInstruction, new Insets(20, 20, 20, 20));

			VBox.setMargin(zoneBoutons, new Insets(20, 20, 20, 20));

			
			
			
			////////////////////////////////////
			//Tests au dmarrage de la fentre//
			////////////////////////////////////
			this.griserBoutons();
			if (listePanneauVide) {
				tooltipPanneau.setText("La liste des panneaux est vide");
			}
			else {
				tooltipPanneau.setText("Aucun panneau n'est sÈlectionnÈ");
			}
			
			if (listeClientVide) {
				tooltipClient.setText("La liste des clients est vide");
			}
			else {
				tooltipClient.setText("Aucun client n'est sÈlectionnÈ");
			}
			
			
			// EVENTS DATES
			dateDebut.setOnAction(e -> {
				this.verifDates();
				this.griserBoutons();
			});
			dateFin.setOnAction(e -> {
				this.verifDates();
				this.griserBoutons();
			});
			// ACTION DES BOUTON 
			
			// REGLER PRBL TROUVER DATE VALUEOF
			bnOK.setOnAction(e -> {
				LocalDate localDateDebut = dateDebut.getValue();
				Instant instantDDebut = Instant.from(localDateDebut.atStartOfDay(ZoneId.systemDefault()));
				Date datDeb = Date.from(instantDDebut);
				
				LocalDate localDateFin = dateFin.getValue();
				Instant instantDFin = Instant.from(localDateFin.atStartOfDay(ZoneId.systemDefault()));
				Date datFin = Date.from(instantDFin);
				
				Location location = new Location(datDeb,datFin,listeClient.getSelectionModel().getSelectedItem(),listePanneau.getSelectionModel().getSelectedItem());
				location.getClientLoc().ajouterLocation(location);
				location.getPanneauLoc().ajouterLocation(location);
				Principale.ajouterLocation(location);
				this.close();
			});
			
			bnAnnuler.setOnAction(e -> {
				this.close();
			});
			
			
			// COMBOX ET BOOLEAN POUR SAVOIR SI SELECTIONNER OU NON
			
			listePanneau.setOnAction(e -> {
				if (listePanneauVide) {
					warningImagePanneau.setVisible(true);
					tooltipPanneau.setText("La liste de panneau est vide");
					panneauOK=false;
				}
				else {
					if (listePanneau.getSelectionModel().getSelectedIndex()==-1) {
						warningImagePanneau.setVisible(true);
						tooltipPanneau.setText("Aucun panneau n'est sÈlectionnÈ");
						panneauOK=false;
					}
					else {
						warningImagePanneau.setVisible(false);
						panneauOK=true;
					}
				}
				this.verifDates();
				this.griserBoutons();
			});
			
			listeClient.setOnAction(e -> {
				if (listeClientVide) {
					warningImageClient.setVisible(true);
					tooltipPanneau.setText("La liste de clients est vide");
					clientOK=false;
				}
				else {
					if (listeClient.getSelectionModel().getSelectedIndex()==-1) {
						warningImageClient.setVisible(true);
						tooltipClient.setText("Aucun client n'est sÈlectionnÈ");
						clientOK=false;
					}
					else {
						warningImageClient.setVisible(false);
						clientOK=true;
					}
				}
				this.verifDates();
				this.griserBoutons();
			});
			
			return racine;
		}
		
		/**
		 * Actualise la liste des panneaux passÈ en paramËtre
		 * @param laListe, une ObservableList<Panneau>
		 */
		public void listePanneauVide(ObservableList<Panneau> laListe) {
			if (laListe.isEmpty()) {
				listePanneauVide=true;
			}
			else {
				listePanneauVide=false;
			}
		}
		
		/**
		 * Test si la liste pass√© en param√®ter est vide
		 * @param laListe, une ObservableList<Client>
		 */
		public void listeClientVide(ObservableList<Client> laListe) {
			if (laListe.isEmpty()) {
				listeClientVide=true;
			}
			else {
				listeClientVide=false;
			}
		}
		
		/**
		 * Actualise la laListe pass√© en param√®tre
		 * @param laListe, ObservableList<Panneau>
		 */
		public void actualiserPanneau(ObservableList<Panneau> laListe) {
			listePanneau.setItems(laListe);
		}
		
		/**
		 * Actualise la laListe pass√© en param√®tre
		 * @param laListe, ObservableList<Client>
		 */
		public void actualiserClient(ObservableList<Client> laListe) {
			listeClient.setItems(laListe);
		}
		
		/**
		 * Ajoute une Location en connaissant le Client, qui est pass√© en param√®tre
		 * @param c, un Client
		 */
		public void depuisClient(Client c) {
			listeClient.getSelectionModel().select(c);
			listeClient.setDisable(true);
			warningImageClient.setVisible(false);
			clientOK=true;
			this.griserBoutons();
		}
		
		/**
		 * Ajoute une Location en connaissant le Panneau, qui est pass√© en pram√®tre
		 * @param p, un Panneau
		 */
		public void depuisPanneau(Panneau p) {
			listePanneau.getSelectionModel().select(p);
			listePanneau.setDisable(true);
			warningImagePanneau.setVisible(false);
			panneauOK=true;
			this.griserBoutons();
		}
		
		// PERMET DE SAVOIR SI LES CHAMPS SON BIEN REMPLI SI OUI ALORS BN OK POSSIBLE

		
		//TESTS SUR LES DATES
		
		/**
		 * V√©rifie si la date de d√©but et de fin ont √©t√© renseign√©, affiche un message d'erreur sinon
		 * si la date de d√©but et de fin son correctement renseigner, v√©rifie si la date de d√©but et avant la date de fin et ensuite attribue des valeurs aux variables
		 * V√©rifie aussi que les dates ne chevauchent pas avec d'autres dates
		 */
		public void verifDates() {
			if (dateDebut.getValue() == null && dateFin.getValue()!=null) {
				tooltipLoc.setText("La date de dÈbut doit Ítre renseignÈe");
				warningImageLoc.setVisible(true);
				dateOK=false;
			}
			else if (dateDebut.getValue() != null && dateFin.getValue()==null) {
				tooltipLoc.setText("La date de fin doit Ítre renseignÈe");
				warningImageLoc.setVisible(true);
				dateOK=false;
			}
			else if (dateDebut.getValue() == null && dateFin.getValue()==null) {
				tooltipLoc.setText("Les dates de dÈbut et de fin doivent Ítre renseignÈes");
				warningImageLoc.setVisible(true);
				dateOK=false;
			}
			else {
				if (dateDebut.getValue().isBefore(dateFin.getValue())) {
					
					LocalDate localDateDebut = dateDebut.getValue();
					Instant instantDDebut = Instant.from(localDateDebut.atStartOfDay(ZoneId.systemDefault()));
					Date datDeb = Date.from(instantDDebut);
					
					LocalDate localDateFin = dateFin.getValue();
					Instant instantDFin = Instant.from(localDateFin.atStartOfDay(ZoneId.systemDefault()));
					Date datFin = Date.from(instantDFin);
					
					if (listePanneau.getSelectionModel().getSelectedItem().datesChevauchent(datDeb,datFin)) {
						tooltipLoc.setText("Les dates chevauchent avec une location dÈj‡ existante");
						warningImageLoc.setVisible(true);
						dateOK=false;
					}
					else {
						warningImageLoc.setVisible(false);
						dateOK=true;
					}
				}
				else {
					tooltipLoc.setText("La date de dÈbut doit Ítre avant la date de fin");
					warningImageLoc.setVisible(true);
					dateOK=false;
				}
			}
		}
		
		/**
		 * remplace la date d√©but par la valeur null
		 */
		public void clearDateDebut() {
			this.dateDebut.getEditor().clear();
			this.dateDebut.setValue(null);
		}
		
		/**
		 * remplace la date fin par la valeur null
		 */
		public void clearDateFin() {
			this.dateFin.getEditor().clear();
			this.dateFin.setValue(null);
		}
		
		//METHODE GRISER BOUTON
		
		/**
		 * permet de g√©rer le statut des boutons 
		 */
		public void griserBoutons() {
			if (dateOK && panneauOK && clientOK) {
				bnOK.setDisable(false);
			}
			else {
				if (listePanneau.getSelectionModel().getSelectedIndex()==-1 || listeClient.getSelectionModel().getSelectedIndex()==-1) {
					dateDebut.setDisable(true);
					dateFin.setDisable(true);
					tooltipLoc.setText("Veuillez sÈlectionner un panneau et un client avant de renseigner les dates");
					warningImageLoc.setVisible(true);
				}
				else {
					dateDebut.setDisable(false);
					dateFin.setDisable(false);
					this.verifDates();
				}
			}
		}
		
		public void forceGriserBouton() {
			bnOK.setDisable(true);
		}
		
}
