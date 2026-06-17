package fr.univorleans.iut45.briquiuto.IHM.Vue.Connexion;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ConnexionBDVue extends VBox {

    private TextField txtLoginBD;
    private PasswordField txtMdpBD;
    private Button btnConnecterBD;
    private Label lblErreurBD;

    public ConnexionBDVue() {
        this.setSpacing(25);
        this.setPadding(new Insets(40));
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: #FFFFFF;");

        // --- TITRE ---
        Label lblTitre = new Label("Connexion au serveur IUT");
        lblTitre.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        lblTitre.setStyle("-fx-text-fill: #0055BF;"); // Bleu LEGO

        Separator separateur = new Separator();
        separateur.setStyle("-fx-background-color: #F6D304; -fx-border-width: 2px;");

        Label sousTitre = new Label("Veuillez entrer vos identifiants MariaDB (ex: o22403771)");
        sousTitre.setFont(Font.font("Arial", 14));
        sousTitre.setStyle("-fx-text-fill: #555555;");

        // --- FORMULAIRE ---
        GridPane grille = new GridPane();
        grille.setVgap(15);
        grille.setHgap(15);
        grille.setAlignment(Pos.CENTER);

        String styleLabel = "-fx-font-weight: bold; -fx-font-size: 14px;";
        String styleChamp = "-fx-border-color: #0055BF; -fx-border-width: 2px; -fx-border-radius: 3; -fx-padding: 5;";

        Label lblLogin = new Label("Login IUT :"); lblLogin.setStyle(styleLabel);
        txtLoginBD = new TextField(); txtLoginBD.setStyle(styleChamp);

        Label lblMdp = new Label("Mot de passe :"); lblMdp.setStyle(styleLabel);
        txtMdpBD = new PasswordField(); txtMdpBD.setStyle(styleChamp);

        grille.add(lblLogin, 0, 0); grille.add(txtLoginBD, 1, 0);
        grille.add(lblMdp, 0, 1); grille.add(txtMdpBD, 1, 1);

        // --- BOUTON ET ERREUR ---
        btnConnecterBD = new Button("Connexion à MariaDB");
        btnConnecterBD.setStyle("-fx-background-color: #287F46; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10 20; -fx-background-radius: 5;");

        lblErreurBD = new Label("");
        lblErreurBD.setStyle("-fx-text-fill: #E3000B; -fx-font-weight: bold;");
        lblErreurBD.setVisible(false);

        this.getChildren().addAll(lblTitre, separateur, sousTitre, grille, btnConnecterBD, lblErreurBD);
    }

    public TextField getTxtLoginBD() { return txtLoginBD; }
    public PasswordField getTxtMdpBD() { return txtMdpBD; }
    public Button getBtnConnecterBD() { return btnConnecterBD; }
    public Label getLblErreurBD() { return lblErreurBD; }
    
    public void afficherErreur(String message) {
        lblErreurBD.setText(message);
        lblErreurBD.setVisible(true);
    }
}