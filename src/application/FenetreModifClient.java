package application;

import java.net.URL;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FenetreModifClient extends Stage {
	private VBox racine = new VBox();
	private HBox zoneBoutons = new HBox();
	private GridPane zoneSaisie = new GridPane();
	
	private Label labelNom = new Label("Libellé du client :");
	private TextField txtNom = new TextField();
	private Button bnOK = new Button("OK");
	private Button bnAnnuler = new Button("Annuler");
	
	private Tooltip tooltip = new Tooltip();
	
	private URL urlWarning = this.getClass().getResource("warning.png");
	private String warningspath = urlWarning.toExternalForm();
	private Image warningImg = new Image(warningspath);
	private ImageView warningImage = new ImageView(warningImg);
	
	private String nomDeBase;
	
	private Client ancienClient;
	
	public FenetreModifClient() {
		this.setTitle("Modifier un client");
		this.setResizable(false);
		this.setScene(new Scene(creerContenu()));
		this.sizeToScene();
	}
	
	private Parent creerContenu() {
		bnOK.setPrefWidth(100);
		bnAnnuler.setPrefWidth(100);
		
		bnOK.setDisable(false);
		Tooltip.install(warningImage, tooltip);
		warningImage.setVisible(false);
		
		warningImage.setFitHeight(25);
		warningImage.setFitWidth(25);
		
		zoneSaisie.setHgap(10);
		zoneSaisie.setVgap(20);
		zoneSaisie.add(labelNom, 0, 0);
		zoneSaisie.add(txtNom, 1, 0);
		
		zoneBoutons.getChildren().addAll(warningImage,bnOK,bnAnnuler);
		zoneBoutons.setAlignment(Pos.CENTER_RIGHT);
		zoneBoutons.setSpacing(20.0);
		
		racine.getChildren().addAll(zoneSaisie, zoneBoutons);
		VBox.setMargin(zoneSaisie, new Insets(20, 20, 20, 20));
		VBox.setMargin(zoneBoutons, new Insets(20, 20, 20, 20));
		
		txtNom.setOnKeyReleased(e -> {
			//Dsactive le bouton OK en indiquant l'erreur si le textfield est vide
			if (txtNom.getText().isEmpty()) {
				bnOK.setDisable(true);
				tooltip.setText("Libellé du client vide");
				warningImage.setVisible(true);
			}
			//Dsactive le bouton OK en indiquant l'erreur si le nom de la zone est dj dans la liste des zones
			else if (listeClientContientNomClient(txtNom.getText(), Principale.getLesClients())){
				bnOK.setDisable(true);
				tooltip.setText("Un autre client avec ce libellé existe déjà");
				warningImage.setVisible(true);
			}
			else if (txtNom.getText().length()<=50){
				bnOK.setDisable(false);
				warningImage.setVisible(false);
			}
		});
		
		txtNom.setOnKeyPressed(e -> {
			//Dsactive le bouton OK en indiquant l'erreur si le nom de la zone est suprieur  50 caractres
			if (txtNom.getText().length()>50) {
				bnOK.setDisable(true);
				tooltip.setText("Libellé du client supérieur à 50 caractres");
				warningImage.setVisible(true);
			}
		});
		
		bnAnnuler.setOnAction(e -> {
			this.close();
		});
		
		bnOK.setOnAction(e -> {
			
			Client c = ancienClient;
			c.modifierLibelle(txtNom.getText());
			
			Principale.modifierClient(c);
			this.close();
		});
		
		return racine;
	}
	
	public void setNom(String nom) {
		txtNom.setText(nom);
		nomDeBase=nom;
	}
	
	public void setClient(Client c) {
		this.ancienClient=c;
	}
	
	/**
	 * 
	 * @param nom, un String
	 * @param laListe, une ObservableList<Client>
	 * @return un boolÃ©en qui est vrai si un Client est contenu dans la liste
	 */
	public boolean listeClientContientNomClient(String nom, ObservableList<Client> laListe) {
		boolean contient = false;
		int i=0;
		while (!contient && i<laListe.size()) {
			
			if (laListe.get(i).getLibelle().equals(nom) && !laListe.get(i).getLibelle().equals(nomDeBase)) {
				contient=true;
			}
			else {
				i++;
			}
		}
		return contient;
	}
}
