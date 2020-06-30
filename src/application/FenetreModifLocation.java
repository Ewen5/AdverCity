package application;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FenetreModifLocation extends Stage {
	
	
	
	// les composants de la fentre
		private VBox 			racine				= new VBox();
		private HBox 			zoneBoutons 		= new HBox(20);
		private GridPane		zoneDate			= new GridPane();

		private Label			lblDateDebut		= new Label("Date de début (*) : ");
		private Label			lblDateFin			= new Label("Date de fin (*) : ");

		private Location ancienneLoc;
		
		
		private DatePicker		dateFin				= new DatePicker();
		private DatePicker		dateDebut			= new DatePicker();

		private Button 			bnOK				= new Button("Valider");
		private Button 			bnAnnuler			= new Button("Annuler");

		//Tooltip
		private Tooltip tooltipLoc					= new Tooltip();
		
		//Booleens pour verifier que les champs sont remplis
		
		private boolean dateOK = false;

		
		
		private URL urlWarning = this.getClass().getResource("warning.png");
		private String warningspath = urlWarning.toExternalForm();
		private Image warningImg = new Image(warningspath,25,25,true,true);
		
		private ImageView warningImageLoc = new ImageView(warningImg);
				

		
		// constructeur : initialisation de la fentre
		public FenetreModifLocation(){
			this.setTitle("Modifier une location");
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
			
			//INSTALLATION DES TOOLTIP
			Tooltip.install(warningImageLoc, tooltipLoc);
			
			tooltipLoc.setText("Les dates de début et de fin doivent être renseignées");
			
			// ASSEMBLAGE DE TOUTES LES PARTIES
			racine.getChildren().addAll(zoneDate,  zoneBoutons);
			VBox.setMargin(zoneDate, new Insets(20, 20, 20, 20));

			VBox.setMargin(zoneBoutons, new Insets(20, 20, 20, 20));
			
			////////////////////////////////////
			//Tests au dmarrage de la fentre//
			////////////////////////////////////
			this.griserBoutons();
			
			
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
				
				Location location = new Location(ancienneLoc.getIdentifiant(),datDeb,datFin,ancienneLoc.getClientLoc(),ancienneLoc.getPanneauLoc());
				location.getClientLoc().modifierLocation(location);
				location.getPanneauLoc().modifierLocation(location);
				Principale.modifierLocation(location);
				this.close();
			});
			
			bnAnnuler.setOnAction(e -> {
				this.close();
			});
			
			
			// COMBOX ET BOOLEAN POUR SAVOIR SI SELECTIONNER OU NON
			
			return racine;
		}
		
		// PERMET DE SAVOIR SI LES CHAMPS SON BIEN REMPLI SI OUI ALORS BN OK POSSIBLE

		
		//TESTS SUR LES DATES
		
		/**
		 * VÃ©rifie si la date de dÃ©but et de fin ont Ã©tÃ© renseignÃ©, affiche un message d'erreur sinon
		 * si la date de dÃ©but et de fin son correctement renseigner, vÃ©rifie si la date de dÃ©but et avant la date de fin et ensuite attribue des valeurs aux variables
		 * VÃ©rifie aussi que les dates ne chevauchent pas avec d'autres dates
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
			else if (dateDebut.getValue() == null && dateFin.getValue()==null) {
				tooltipLoc.setText("Les dates de début et de fin doivent être renseignées");
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
					
					if (ancienneLoc.getPanneauLoc().datesChevauchent(datDeb,datFin,ancienneLoc)) {
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
		
		
		//METHODE GRISER BOUTON
		/**
		 * Gere les boutons
		 */
		public void griserBoutons() {
			if (dateOK) {
				bnOK.setDisable(false);
			}
			else {
				bnOK.setDisable(true);
			}
		}
		
		/**
		 * Met la location passÃ© en param dans ancienneLoc
		 * @param loc, une Location
		 */
		public void getAncienneLoc(Location loc) {
			this.ancienneLoc=loc;
			LocalDate localDebutAncien = ancienneLoc.getDebutLoc().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate localFinAncien = ancienneLoc.getFinLoc().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			dateDebut.setValue(localDebutAncien);
			dateFin.setValue(localFinAncien);
		}
		
}
