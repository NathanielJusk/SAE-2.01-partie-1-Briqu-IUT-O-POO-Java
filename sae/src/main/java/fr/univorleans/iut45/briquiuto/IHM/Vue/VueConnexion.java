package fr.univorleans.iut45.briquiuto.IHM.Vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VueConnexion extends VBox {

    private TextField txtIdentifiant;
    private PasswordField txtMotDePasse;
    private Button btnSeConnecter;
    
    // Nouveaux attributs pour le bouton Home
    private Button btnHome;
    private Image homeImage;

    public VueConnexion() {
        this.setSpacing(25);
        this.setPadding(new Insets(40));
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: #f5f5f5;");

        // --- BOUTON HOME ---
        this.homeImage = new Image(getClass().getResourceAsStream("/img/70083.png"));
        ImageView homeImageView = new ImageView(this.homeImage);
        homeImageView.setFitWidth(30);
        homeImageView.setFitHeight(30);
        homeImageView.setPreserveRatio(true);
        this.btnHome = new Button();
        this.btnHome.setGraphic(homeImageView);
        this.btnHome.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");

        // --- EN-TÊTE ---
        Label titreApp = new Label("Briqu'IUT-O");
        titreApp.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        
        HBox topBox = new HBox(20, btnHome, titreApp);
        topBox.setAlignment(Pos.CENTER);

        Label sousTitre = new Label("Bienvenue sur l'application de gestion LEGO");
        sousTitre.setFont(Font.font("Arial", 14));
        sousTitre.setStyle("-fx-text-fill: #555555;");

        // --- FORMULAIRE ---
        GridPane grilleFormulaire = new GridPane();
        grilleFormulaire.setVgap(15);
        grilleFormulaire.setHgap(10);
        grilleFormulaire.setAlignment(Pos.CENTER);

        Label lblIdentifiant = new Label("Identifiant :");
        txtIdentifiant = new TextField();
        Label lblMotDePasse = new Label("Mot de passe :");
        txtMotDePasse = new PasswordField();

        grilleFormulaire.add(lblIdentifiant, 0, 0);
        grilleFormulaire.add(txtIdentifiant, 1, 0);
        grilleFormulaire.add(lblMotDePasse, 0, 1);
        grilleFormulaire.add(txtMotDePasse, 1, 1);

        // --- ACTION ---
        btnSeConnecter = new Button("se connecter");
        btnSeConnecter.setStyle("-fx-background-color: #e0e0e0; -fx-text-fill: black; -fx-padding: 8 20;");

        this.getChildren().addAll(topBox, sousTitre, grilleFormulaire, btnSeConnecter);
    }

    public TextField getTxtIdentifiant() { return txtIdentifiant; }
    public PasswordField getTxtMotDePasse() { return txtMotDePasse; }
    public Button getBtnSeConnecter() { return btnSeConnecter; }
    public Button getBtnHome() { return btnHome; } // Getter ajouté !
}