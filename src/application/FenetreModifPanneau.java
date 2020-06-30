package application;

import java.net.URL;

import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FenetreModifPanneau extends Stage {
	
	/*panes*/
	private VBox racine = new VBox();
	private HBox zoneBoutons = new HBox();
	private GridPane zoneSaisie = new GridPane();
	
	/*labels*/
	private Label labelId = new Label();
	private Label labelZone = new Label("Zone de l'emplacement :");
	private Label labelVisibilite = new Label("VisibilitÈ du panneau :");
	private Label labelTarif = new Label("Tarif mensuel du panneau () :");
	
	private Label labelFormat = new Label("Format du panneau");
	private Label labelLongueur = new Label("Longueur (cm) :");
	private Label labelLargeur = new Label("Largeur (cm) :");
	
	/*zones d'entres de donnes*/
	private ComboBox<Zone> listeZones = new ComboBox<Zone>();
	
	private RadioButton tresPeuVisible = new RadioButton();
	private RadioButton peuVisible = new RadioButton();
	private RadioButton moyenVisible = new RadioButton();
	private RadioButton bienVisible = new RadioButton();
	private RadioButton tresBienVisible = new RadioButton();
	
	private ToggleGroup groupeVisibilite = new ToggleGroup();
	
	private TextField txtFieldTarif = new TextField();
	
	private TextField txtFieldLongueur = new TextField();
	private TextField txtFieldLargeur = new TextField();
	
	
	
	/*images*/
	
	private URL urlWarning = this.getClass().getResource("warning.png");
	private String warningspath = urlWarning.toExternalForm();
	private Image warningImg = new Image(warningspath);
	
	private ImageView warningImageZones = new ImageView(warningImg);
	private ImageView warningImageTarif = new ImageView(warningImg);
	private ImageView warningImageLongueur = new ImageView(warningImg);
	private ImageView warningImageLargeur = new ImageView(warningImg);
	
	private URL urlUneEtoile = this.getClass().getResource("une_etoile.png");
	private String uneEtoilespath = urlUneEtoile.toExternalForm();
	private Image uneEtoileImg = new Image(uneEtoilespath);
	private ImageView uneEtoileImage = new ImageView(uneEtoileImg);
	
	private URL urlDeuxEtoiles = this.getClass().getResource("deux_etoiles.png");
	private String deuxEtoilespath = urlDeuxEtoiles.toExternalForm();
	private Image deuxEtoilesImg = new Image(deuxEtoilespath);
	private ImageView deuxEtoilesImage = new ImageView(deuxEtoilesImg);
	
	private URL urlTroisEtoiles = this.getClass().getResource("trois_etoiles.png");
	private String troisEtoilespath = urlTroisEtoiles.toExternalForm();
	private Image troisEtoilesImg = new Image(troisEtoilespath);
	private ImageView troisEtoilesImage = new ImageView(troisEtoilesImg);
	
	private URL urlQuatreEtoiles = this.getClass().getResource("quatre_etoiles.png");
	private String quatreEtoilespath = urlQuatreEtoiles.toExternalForm();
	private Image quatreEtoilesImg = new Image(quatreEtoilespath);
	private ImageView quatreEtoilesImage = new ImageView(quatreEtoilesImg);
	
	private URL urlCinqEtoiles = this.getClass().getResource("cinq_etoiles.png");
	private String cinqEtoilespath = urlCinqEtoiles.toExternalForm();
	private Image cinqEtoilesImg = new Image(cinqEtoilespath);
	private ImageView cinqEtoilesImage = new ImageView(cinqEtoilesImg);
	
	//Boutons
	
	private Button bnOK = new Button("OK");
	private Button bnAnnuler = new Button("Annuler");
	
	//Tooltip
	private Tooltip tooltipZones = new Tooltip();
	private Tooltip tooltipTarif = new Tooltip();
	private Tooltip tooltipLongueur = new Tooltip();
	private Tooltip tooltipLargeur = new Tooltip();
	
	
	//Booleens pour verifier que les champs sont remplis

	private boolean listeZonesOK = true;
	private boolean tarifOK = true;
	private boolean longueurOK = true;
	private boolean largeurOK = true;
	
	private boolean listeZonesVide;
	
	private Integer identifiant;
	
	private Panneau ancienPanneau;
	
	public FenetreModifPanneau() {
		this.setTitle("Modifier un panneau");
		this.setResizable(false);
		this.setScene(new Scene(creerContenu()));
		this.sizeToScene();
	}
	
	private Parent creerContenu() {
		bnOK.setPrefWidth(100);
		bnAnnuler.setPrefWidth(100);
		
		bnOK.setDisable(false);
		
		Tooltip.install(warningImageZones, tooltipZones);
		warningImageZones.setVisible(false);
		
		Tooltip.install(warningImageTarif, tooltipTarif);
		warningImageTarif.setVisible(false);
		
		Tooltip.install(warningImageLongueur, tooltipLongueur);
		warningImageLongueur.setVisible(false);
		
		Tooltip.install(warningImageLargeur, tooltipLargeur);
		warningImageLargeur.setVisible(false);
		
		//Taille images
		
		warningImageZones.setFitHeight(25);
		warningImageZones.setFitWidth(25);
		
		warningImageTarif.setFitHeight(25);
		warningImageTarif.setFitWidth(25);
		
		warningImageLongueur.setFitHeight(25);
		warningImageLongueur.setFitWidth(25);
		
		warningImageLargeur.setFitHeight(25);
		warningImageLargeur.setFitWidth(25);
		
		
		
		uneEtoileImage.setFitHeight(19);
		uneEtoileImage.setFitWidth(100);
		
		deuxEtoilesImage.setFitHeight(19);
		deuxEtoilesImage.setFitWidth(100);
		
		troisEtoilesImage.setFitHeight(19);
		troisEtoilesImage.setFitWidth(100);
		
		quatreEtoilesImage.setFitHeight(19);
		quatreEtoilesImage.setFitWidth(100);
		
		cinqEtoilesImage.setFitHeight(19);
		cinqEtoilesImage.setFitWidth(100);

		
		txtFieldLongueur.setMaxWidth(100.0);
		txtFieldLargeur.setMaxWidth(100.0);
		
		groupeVisibilite.getToggles().addAll(tresPeuVisible,peuVisible,moyenVisible,bienVisible,tresBienVisible);
		groupeVisibilite.selectToggle(tresPeuVisible);
		
		labelId.setFont(Font.font("verdana", FontWeight.BOLD, 15));
		
		zoneSaisie.add(labelId, 0, 0,2,1);
		
		GridPane.setHalignment(labelId, HPos.CENTER);
		GridPane.setHalignment(labelFormat, HPos.CENTER);
		GridPane.setHalignment(labelVisibilite, HPos.CENTER);
		
		labelVisibilite.setFont(Font.font("verdana", FontWeight.BOLD, 12));
		
		labelFormat.setFont(Font.font("verdana", FontWeight.BOLD, 12));
		
		zoneSaisie.add(labelZone, 0, 1);
		zoneSaisie.add(listeZones, 1, 1);
		zoneSaisie.add(warningImageZones, 2, 1);
		
		zoneSaisie.add(new Text(""), 0, 2,3,1);
		
		zoneSaisie.add(labelVisibilite, 0, 3,3,1);
		
		zoneSaisie.add(tresPeuVisible, 0, 4);
		zoneSaisie.add(uneEtoileImage, 1, 4);
		
		zoneSaisie.add(peuVisible, 0, 5);
		zoneSaisie.add(deuxEtoilesImage, 1, 5);
		
		zoneSaisie.add(moyenVisible, 0, 6);
		zoneSaisie.add(troisEtoilesImage, 1, 6);
		
		zoneSaisie.add(bienVisible, 0, 7);
		zoneSaisie.add(quatreEtoilesImage, 1, 7);
		
		zoneSaisie.add(tresBienVisible, 0, 8);
		zoneSaisie.add(cinqEtoilesImage, 1, 8);
		
		zoneSaisie.add(labelTarif, 0, 9);
		zoneSaisie.add(txtFieldTarif, 1, 9);
		zoneSaisie.add(warningImageTarif, 2, 9);
		
		zoneSaisie.add(new Text(""), 0, 10,3,1);
		
		zoneSaisie.add(labelFormat, 0, 11,3,1);
		
		zoneSaisie.add(labelLongueur, 0, 12);
		zoneSaisie.add(txtFieldLongueur, 1, 12);
		zoneSaisie.add(warningImageLongueur, 2, 12);
		
		zoneSaisie.add(labelLargeur, 0, 13);
		zoneSaisie.add(txtFieldLargeur, 1, 13);
		zoneSaisie.add(warningImageLargeur, 2, 13);
		
		zoneSaisie.setVgap(20.0);
		zoneSaisie.setHgap(20.0);
		
		zoneBoutons.getChildren().addAll(bnOK,bnAnnuler);
		zoneBoutons.setAlignment(Pos.CENTER_RIGHT);
		zoneBoutons.setSpacing(20.0);
		
		racine.getChildren().addAll(zoneSaisie,zoneBoutons);
		
		VBox.setMargin(zoneSaisie, new Insets(20, 20, 20, 20));
		VBox.setMargin(zoneBoutons, new Insets(20, 20, 20, 20));
		
		
		////////////////////////////////////
		//Tests au dmarrage de la fentre//
		////////////////////////////////////
		
		
		listeZones.setOnAction(e -> {
			if (listeZonesVide) {
				warningImageZones.setVisible(true);
				tooltipZones.setText("La liste de zones est vide");
				listeZonesOK=false;
			}
			else {
				if (listeZones.getSelectionModel().getSelectedIndex()==-1) {
					warningImageZones.setVisible(true);
					tooltipZones.setText("Aucune zone n'est sÈlectionnÈe");
					listeZonesOK=false;
				}
				else {
					warningImageZones.setVisible(false);
					listeZonesOK=true;
				}
			}
			tousChampsRemplis();
		});
		
		
		
		txtFieldTarif.setOnKeyReleased(e -> {
			if (txtFieldTarif.getText().isEmpty()) {
				warningImageTarif.setVisible(true);
				tooltipTarif.setText("La zone de saisie est vide");
				tarifOK=false;
			}
			else {
				if (txtFieldTarif.getText().matches("([0-9]+(\\.[0-9]+)?)+")) {
					warningImageTarif.setVisible(false);
					tarifOK=true;
				}
				else {
					warningImageTarif.setVisible(true);
					tooltipTarif.setText("La chaine rentrÈe n'est pas un nombre rÈel");
					tarifOK=false;
				}
			}
			tousChampsRemplis();
		});
		
		txtFieldLongueur.setOnKeyReleased(e -> {
			if (txtFieldLongueur.getText().isEmpty()) {
				warningImageLongueur.setVisible(true);
				tooltipLongueur.setText("La zone de saisie est vide");
				longueurOK=false;
			}
			else {
				if (txtFieldLongueur.getText().matches("([0-9]+(\\.[0-9]+)?)+")) {
					warningImageLongueur.setVisible(false);
					longueurOK=true;
				}
				else {
					warningImageLongueur.setVisible(true);
					tooltipLongueur.setText("La chaine rentrÈe n'est pas un nombre rÈel");
					longueurOK=false;
				}
			}
			tousChampsRemplis();
		});
		
		txtFieldLargeur.setOnKeyReleased(e -> {
			if (txtFieldLargeur.getText().isEmpty()) {
				warningImageLargeur.setVisible(true);
				tooltipLargeur.setText("La zone de saisie est vide");
				largeurOK=false;
			}
			else {
				if (txtFieldLargeur.getText().matches("([0-9]+(\\.[0-9]+)?)+")) {
					warningImageLargeur.setVisible(false);
					largeurOK=true;
				}
				else {
					warningImageLargeur.setVisible(true);
					tooltipLargeur.setText("La chaine rentrÈe n'est pas un nombre rÈel");
					largeurOK=false;
				}
			}
			tousChampsRemplis();
		});
		
		bnOK.setOnAction(e -> {
			Panneau panneau = new Panneau(ancienPanneau.getCoordonnees(),identifiant,Float.valueOf(txtFieldTarif.getText()),nbEtoiles(),Float.valueOf(txtFieldLongueur.getText()),Float.valueOf(txtFieldLargeur.getText()),listeZones.getSelectionModel().getSelectedItem());
			panneau.setPlanning(ancienPanneau.getPlanning());
			Principale.modifierPanneau(panneau);
			Principale.setPanneauCacheCarte(panneau);
			this.close();
		});
		
		bnAnnuler.setOnAction(e -> {
			Principale.modifierPanneau(ancienPanneau);
			Principale.setPanneauCacheCarte(ancienPanneau);
			this.close();
		});
		
		return racine;
	}
	
	/**
	 * Actualise la liste de zone pass√© en param√®tre
	 * @param laListe, une ObservableList<Zone>
	 */
	public void actualiserZones(ObservableList<Zone> laListe) {
		listeZones.setItems(laListe);
	}

    /**
	 * change la valeur de listeZonesVide par true si la liste pass√© en param√®ter est vide
	 * @param laListe, une ObservableList<Zone>
	 */
	public void listeZonesVide(ObservableList<Zone> laListe) {
		if (laListe.isEmpty()) {
			listeZonesVide=true;
		}
		else {
			listeZonesVide=false;
		}
	}
	
	public void setId(Integer id) {
		labelId.setText("Panneau N"+id);
		identifiant=id;
	}
	
	public void setTarif(String tarif) {
		txtFieldTarif.setText(tarif);
	}
	
	public void setLongueur(String longueur) {
		txtFieldLongueur.setText(longueur);
	}
	
	public void setLargeur(String largeur) {
		txtFieldLargeur.setText(largeur);
	}
	
	public void setZone(Zone zone) {
		listeZones.getSelectionModel().select(zone);
	}
	
	/**
	 * Change la visibilit√© en fonction de l'Integer pass√© en param√®tre
	 * @param nbEtoiles, un Integer
	 */
	public void setVisibilite(Integer nbEtoiles) {
		switch(nbEtoiles) {
			case 1:
				groupeVisibilite.selectToggle(tresPeuVisible);
				break;
			case 2:
				groupeVisibilite.selectToggle(peuVisible);
				break;
			case 3:
				groupeVisibilite.selectToggle(moyenVisible);
				break;
			case 4:
				groupeVisibilite.selectToggle(bienVisible);
				break;
			case 5:
				groupeVisibilite.selectToggle(tresBienVisible);
		}
	}
	/**
	 * @return un Integer en fonction de la visibilit√©
	 */
	public Integer nbEtoiles() {
		Integer nbEtoiles=-1;
		if (groupeVisibilite.getSelectedToggle()==tresPeuVisible) {
			nbEtoiles=1;
		}
		else if (groupeVisibilite.getSelectedToggle()==peuVisible) {
			nbEtoiles=2;
		}
		else if (groupeVisibilite.getSelectedToggle()==moyenVisible) {
			nbEtoiles=3;
		}
		else if (groupeVisibilite.getSelectedToggle()==bienVisible) {
			nbEtoiles=4;
		}
		else if (groupeVisibilite.getSelectedToggle()==tresBienVisible) {
			nbEtoiles=5;
		}
		return nbEtoiles;
	}
	
	/**
	 * V√©rifie si tout les champs sont remplis, si oui, active le bouton OK
	 */
	public void tousChampsRemplis() {
		if (listeZonesOK && tarifOK && longueurOK && largeurOK) {
			bnOK.setDisable(false);
		}
		else {
			bnOK.setDisable(true);
		}
	}
	
	public void setPanneau(Panneau p) {
		this.ancienPanneau=p;
	}
}
