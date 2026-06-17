package fr.univorleans.iut45.briquiuto.IHM.Vue.Connexion;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VueConnexion extends VBox {

    private TextField txtIdentifiant;
    private PasswordField txtMotDePasse;
    private Button btnSeConnecter;
    private Button btnHome;

    public VueConnexion() {
        this.setAlignment(Pos.CENTER);
        
        // FOND D'ÉCRAN
        try {
            Image fondImage = new Image(getClass().getResourceAsStream("/img/fondEcran.jpeg"));
            BackgroundSize bgSize = new BackgroundSize(100, 100, true, true, false, true);
            this.setBackground(new Background(new BackgroundImage(fondImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bgSize)));
        } catch (Exception e) {}

        // CARTE BLANCHE
        VBox carteCentrale = new VBox(20);
        carteCentrale.setPadding(new Insets(30));
        carteCentrale.setAlignment(Pos.CENTER);
        carteCentrale.setMaxWidth(400);
        carteCentrale.setStyle("-fx-background-color: rgba(255, 255, 255, 0.95); -fx-background-radius: 15; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 15, 0, 0, 5);");

        // EN-TÊTE : BOUTON HOME + LOGO
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER);
        
        this.btnHome = new Button();
        try {
            ImageView homeImageView = new ImageView(new Image(getClass().getResourceAsStream("/img/70083.png")));
            homeImageView.setFitWidth(30); homeImageView.setFitHeight(30); homeImageView.setPreserveRatio(true);
            this.btnHome.setGraphic(homeImageView);
            this.btnHome.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        } catch (Exception e) { this.btnHome.setText("ðŸ "); }

        try {
            ImageView logoView = new ImageView(new Image(getClass().getResourceAsStream("/img/LogoPrincipale.png")));
            logoView.setFitHeight(50);
            logoView.setPreserveRatio(true);
            header.getChildren().addAll(btnHome, logoView);
        } catch (Exception e) { header.getChildren().add(btnHome); }

        Separator separateur = new Separator();
        separateur.setStyle("-fx-background-color: #F6D304; -fx-border-width: 2px;");

        // FORMULAIRE
        GridPane grilleFormulaire = new GridPane();
        grilleFormulaire.setVgap(15);
        grilleFormulaire.setHgap(10);
        grilleFormulaire.setAlignment(Pos.CENTER);

        String styleLabel = "-fx-font-weight: bold; -fx-font-size: 14px;";
        String styleChamp = "-fx-border-color: #0055BF; -fx-border-width: 2px; -fx-border-radius: 3; -fx-padding: 5;";

        Label lblIdentifiant = new Label("Identifiant :"); lblIdentifiant.setStyle(styleLabel);
        txtIdentifiant = new TextField(); txtIdentifiant.setStyle(styleChamp);

        Label lblMotDePasse = new Label("Mot de passe :"); lblMotDePasse.setStyle(styleLabel);
        txtMotDePasse = new PasswordField(); txtMotDePasse.setStyle(styleChamp);

        grilleFormulaire.add(lblIdentifiant, 0, 0); grilleFormulaire.add(txtIdentifiant, 1, 0);
        grilleFormulaire.add(lblMotDePasse, 0, 1); grilleFormulaire.add(txtMotDePasse, 1, 1);

        btnSeConnecter = new Button("Se connecter");
        btnSeConnecter.setStyle("-fx-background-color: #287F46; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10 30; -fx-background-radius: 5; -fx-cursor: hand;");
        
        carteCentrale.getChildren().addAll(header, separateur, grilleFormulaire, btnSeConnecter);
        this.getChildren().add(carteCentrale);
    }

    public TextField getTxtIdentifiant() { return txtIdentifiant; }
    public PasswordField getTxtMotDePasse() { return txtMotDePasse; }
    public Button getBtnSeConnecter() { return btnSeConnecter; }
    public Button getBtnHome() { return btnHome; }
}