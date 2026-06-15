package fr.univorleans.iut45.briquiuto.IHM.Vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AdminHomeVue extends VBox {

    private Button btnAjoutPiece;
    private Button btnAjoutTheme;
    private Button btnAjoutBoite;
    private Button btnDeconnexion;
    private Button btnHome;
    private Image homeImage;

    public AdminHomeVue() {
        this.setSpacing(20);
        this.setPadding(new Insets(40));
        this.setAlignment(Pos.TOP_CENTER);
        this.setStyle("-fx-background-color: #f0f8ff;");

        // --- BOUTON HOME ---
        this.homeImage = new Image(getClass().getResourceAsStream("/img/70083.png"));
        ImageView homeImageView = new ImageView(this.homeImage);
        homeImageView.setFitWidth(30); homeImageView.setFitHeight(30); homeImageView.setPreserveRatio(true);
        this.btnHome = new Button();
        this.btnHome.setGraphic(homeImageView);
        this.btnHome.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");

        Label lblTitre = new Label("Tableau de bord : Administrateur");
        lblTitre.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        
        HBox header = new HBox(20, btnHome, lblTitre);
        header.setAlignment(Pos.CENTER);

        Separator separateur = new Separator();

        btnAjoutPiece = new Button("Ajouter une nouvelle Pièce");
        btnAjoutTheme = new Button("Créer un nouveau Thème");
        btnAjoutBoite = new Button("Gérer les Boîtes / Catalogue");
        btnDeconnexion = new Button("Déconnexion");

        this.getChildren().addAll(header, separateur, btnAjoutPiece, btnAjoutTheme, btnAjoutBoite, btnDeconnexion);
    }

    public Button getBtnAjoutPiece() { return btnAjoutPiece; }
    public Button getBtnAjoutTheme() { return btnAjoutTheme; }
    public Button getBtnAjoutBoite() { return btnAjoutBoite; }
    public Button getBtnDeconnexion() { return btnDeconnexion; }
    public Button getBtnHome() { return btnHome; }
}