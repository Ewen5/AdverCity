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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FenetreAjoutClient extends Stage {
	
		/* Panes*/
		private VBox 			racine				= new VBox();
		private GridPane 		zoneSaisie 			= new GridPane();
		private HBox 			zoneBoutons 		= new HBox(20);
		
		private GridPane		zoneDate			= new GridPane();
		private HBox			zonePanneaux		= new HBox(20);
		
		private VBox			zoneLocation		= new VBox(10);
		
		/*Elements de la page*/
		
		private Label 			lblTitre			= new Label("Nouveau client");
		private Label 			lblNumero			= new Label("N° client :");
		private Label 			lblLibelle			= new Label("Libellé (*) :");
		private TextField		txtLibelle			= new TextField();
		private CheckBox 		cbAjoutLoc			= new CheckBox("Ajouter une location");
		private Label			lblDateDebut		= new Label("Date de début (*) : ");
		private Label			lblDateFin			= new Label("Date de fin (*) : ");
		private Label			lblInstruction		= new Label("(*) saisie obligatoire");
		private Button 			bnOK				= new Button("Valider");
		private Button 			bnAnnuler			= new Button("Annuler");



		private boolean 		locVisible 		= false;
		private boolean			dateOK			= false;
		private boolean			panneauxOK		= false;
		private boolean			libelleOK		= false;
		private boolean			listePanneauxVide = false;
		
		private ComboBox<Panneau> listePanneaux = new ComboBox<Panneau>();
		private DatePicker		dateDebut		= new DatePicker();
		private DatePicker		dateFin			= new DatePicker();
		
		private Tooltip tooltip = new Tooltip();
		private Tooltip tooltipPan = new Tooltip();
		private Tooltip tooltipLoc = new Tooltip();
		
		private URL urlWarning = this.getClass().getResource("warning.png");
		private String warningspath = urlWarning.toExternalForm();
		private Image warningImg = new Image(warningspath);
		
		private ImageView warningImageLib = new ImageView(warningImg);
		private ImageView warningImagePan = new ImageView(warningImg);
		private ImageView warningImageLoc = new ImageView(warningImg);

				
		// constructeur : initialisation de la fentre
		/**
		 * DÃ©finit les paramÃ¨tres de la fenetre AjoutClient
		 */
		public FenetreAjoutClient(){
			this.setTitle("Ajouter un client");
			this.setResizable(false);
			this.setScene(new Scene(creerContenu()));
			this.sizeToScene();
		}
		
		// cration du Scene graph
		private Parent creerContenu() {
			this.bnOK.setPrefWidth(100);
			this.bnAnnuler.setPrefWidth(100);
			this.bnOK.setDisable(true);
			this.cbAjoutLoc.setSelected(false);
			this.zoneLocation.setVisible(false);
			
			zoneSaisie.setHgap(10);
			zoneSaisie.setVgap(20);
			zoneSaisie.add(lblTitre,  0,  0);
			zoneSaisie.add(lblNumero,  0,  1);

			zoneSaisie.add(lblLibelle,  0,  2);
			zoneSaisie.add(txtLibelle,  1,  2);
			zoneSaisie.add(warningImageLib, 2, 2);
			zoneSaisie.add(cbAjoutLoc,  0,  3);
			zoneSaisie.add(zoneLocation, 0, 4,2,1);
			zoneSaisie.add(lblInstruction,  0,  5);

			zonePanneaux.getChildren().addAll(listePanneaux,warningImagePan);
			
			zoneDate.setVgap(10);
			zoneDate.setHgap(20);
			zoneDate.add(lblDateDebut, 0, 0);
			zoneDate.add(lblDateFin, 1, 0);
			zoneDate.add(dateDebut, 0, 1);
			zoneDate.add(dateFin, 1, 1);
			zoneDate.add(warningImageLoc, 2, 1);
			
			zoneLocation.getChildren().addAll(zonePanneaux,zoneDate);
			
			zoneBoutons.getChildren().addAll(bnAnnuler, bnOK);
			zoneBoutons.setAlignment(Pos.CENTER_RIGHT);
			zoneBoutons.setSpacing(20.0);

			warningImageLib.setFitWidth(25);
			warningImageLib.setFitHeight(25);
			
			warningImagePan.setFitWidth(25);
			warningImagePan.setFitHeight(25);
			
			warningImageLoc.setFitWidth(25);
			warningImageLoc.setFitHeight(25);
			
			if(listePanneauxVide) {
				tooltipPan.setText("Aucun panneau n'existe");
			}
			else {
				tooltipPan.setText("Aucun panneau n'est sélectionné");
			}
			Tooltip.install(warningImagePan, tooltipPan);
			
			tooltipLoc.setText("Les dates de début et de fin doivent être renseignées");
			Tooltip.install(warningImageLoc, tooltipLoc);
			
			tooltip.setText("Le client doit avoir un libellé");
			Tooltip.install(warningImageLib, tooltip);
			
			racine.getChildren().addAll(zoneSaisie, zoneBoutons);
			VBox.setMargin(zoneSaisie, new Insets(20, 20, 20, 20));
			VBox.setMargin(zoneBoutons, new Insets(20, 20, 20, 20));

			// dtection et traitement des vnements
			
			listePanneaux.setOnAction(e -> {
				if(listePanneaux.getItems().isEmpty()) {
					tooltipPan.setText("Aucun panneau n'existe");
					warningImagePan.setVisible(true);
					panneauxOK=false;
				}
				else if (listePanneaux.getSelectionModel().getSelectedIndex()==-1) {
					tooltipPan.setText("Aucun panneau n'est sélectionné");
					warningImagePan.setVisible(true);
					panneauxOK=false;
				}
				else {
					warningImagePan.setVisible(false);
					panneauxOK=true;
				}
				this.griserBoutons();
			});
			
			txtLibelle.setOnKeyReleased(e -> {
				//Désactive le bouton OK en indiquant l'erreur si le textfield est vide
				if (txtLibelle.getText().isEmpty()) {
					libelleOK=false;
					tooltip.setText("Le client doit avoir un libellé");
					warningImageLib.setVisible(true);
				}
				//Désactive le bouton OK en indiquant l'erreur si le nom du client est déjà dans la liste des clients
				else if (listeClientContient(txtLibelle.getText(), Principale.getLesClients())){
					libelleOK=false;
					tooltip.setText("Un autre client avec ce libellé existe déjà");
					warningImageLib.setVisible(true);
				}
				else if (txtLibelle.getText().length()>50){
					tooltip.setText("Le libelle doit faire moins de 50 caractères");
					libelleOK=false;
					warningImageLib.setVisible(true);
				}
				else {
					libelleOK=true;
					warningImageLib.setVisible(false);
				}
				this.griserBoutons();
			});
			
			bnOK.setOnAction(e -> {
				Client client = new Client(txtLibelle.getText());
				Principale.ajouterClient(client);
				if (locVisible) {
					LocalDate localDateDebut = dateDebut.getValue();
					Instant instantDDebut = Instant.from(localDateDebut.atStartOfDay(ZoneId.systemDefault()));
					Date datDeb = Date.from(instantDDebut);
					
					LocalDate localDateFin = dateFin.getValue();
					Instant instantDFin = Instant.from(localDateFin.atStartOfDay(ZoneId.systemDefault()));
					Date datFin = Date.from(instantDFin);
					
					Location location = new Location(datDeb,datFin,client,listePanneaux.getSelectionModel().getSelectedItem());
					location.getClientLoc().ajouterLocation(location);
					location.getPanneauLoc().ajouterLocation(location);
					Principale.ajouterLocation(location);
				}
				this.close();
			});
			
			bnAnnuler.setOnAction(e -> {
				this.close();
			});
			
			cbAjoutLoc.setOnAction(e -> {
				if (locVisible) {
					zoneLocation.setVisible(false);
					locVisible=false;
				}
				else {
					zoneLocation.setVisible(true);
					locVisible=true;
				}
				this.griserBoutons();
			});
			
			dateDebut.setOnAction(e -> {
				this.verifDates();
				this.griserBoutons();
			});
			dateFin.setOnAction(e -> {
				this.verifDates();
				this.griserBoutons();
			});
			
			return racine;
		}
		
		public void init(){
			this.setLibelle("");
			bnOK.setDisable(true);
		}
		
		/**
		 * Vérifie si la date de début et de fin ont été renseignées, affiche un message d'erreur sinon
		 * si la date de début et de fin son correctement renseigner, vérifie si la date de début et avant la date de fin et ensuite attribue des valeurs aux variables
		 * Vérifie aussi que les dates ne chevauchent pas avec d'autres dates
		 */
		public void verifDates() {
			if (dateDebut.getValue() == null && dateFin.getValue()!=null) {
				tooltipLoc.setText("La date de début doit être renseignée");
				warningImageLoc.setVisible(true);
				dateOK=false;
			}
			else if (dateDebut.getValue() != null && dateFin.getValue()==null) {
				tooltipLoc.setText("La date de fin doit être renseignée");
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
					
					if (listePanneaux.getSelectionModel().getSelectedItem().datesChevauchent(datDeb,datFin)) {
						tooltipLoc.setText("Les dates chevauchent avec une location déjà existante");
						warningImageLoc.setVisible(true);
						dateOK=false;
					}
					else {
						warningImageLoc.setVisible(false);
						dateOK=true;
					}
				}
				else {
					tooltipLoc.setText("La date de début doit être avant la date de fin");
					warningImageLoc.setVisible(true);
					dateOK=false;
				}
			}
		}
		
		/**
		 * permet de gérer le statut des boutons 
		 */
		public void griserBoutons() {
			if ((libelleOK && (locVisible && dateOK && panneauxOK)) || (libelleOK && !locVisible)) {
				bnOK.setDisable(false);
			}
			else {
				bnOK.setDisable(true);
			}
			
			if (listePanneaux.getSelectionModel().getSelectedIndex()==-1) {
				dateDebut.setDisable(true);
				dateFin.setDisable(true);
			}
			else {
				dateDebut.setDisable(false);
				dateFin.setDisable(false);
			}
		}
		
		/**
		 * @param libelle, un String
		 * @param laListe, une ObservableList<Client>
		 * @return un booléen qui est vrai si le libelle du Client est contenu dans laListe
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

		public void setLibelle(String libelle){
			txtLibelle.setText(libelle);
		}
		
		public void setId(Integer id) {
			lblNumero.setText("Client N"+id);
		}
		
		/**
		 * Sert à indiquer par un tootip les informations sur les panneaux tel que : "Aucun panneau n'existe" si laListe est vide.
		 * @param laListe, une ObservableList<Panneau> 
		 */
		public void listePanneauxVide(ObservableList<Panneau> laListe) {
			if (laListe.isEmpty()) {
				listePanneauxVide=true;
			}
			else {
				listePanneauxVide=false;
			}
		}
		/**
		 * Actualise la liste des panneaux
		 * @param laListe, une ObservableList<Panneau>
		 */
		public void actualiserListePanneaux(ObservableList<Panneau> laListe) {
			listePanneaux.setItems(laListe);
		}
}

