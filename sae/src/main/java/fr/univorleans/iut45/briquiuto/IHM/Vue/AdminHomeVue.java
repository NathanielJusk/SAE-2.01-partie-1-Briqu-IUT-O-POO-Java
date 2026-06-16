package fr.univorleans.iut45.briquiuto.IHM.Vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AdminHomeVue extends VBox {

    // Boutons correspondant aux exigences exactes de la SAE
    private Button btnAjoutPiece;
    private Button btnAjoutTheme;
    private Button btnAjoutBoite; // Pour ajouter / modifier une boite officielle
    private Button btnDeconnexion;
    private Button btnHome;
    private Button btnCatalogue; // Pour consulter le catalogue officiel
    private Button btnAjoutFigurine; // Pour ajouter une figurine

    public AdminHomeVue() {
        this.setSpacing(25);
        this.setPadding(new Insets(30));
        this.setStyle("-fx-background-color: #F8F9FA;");

        // --- EN-TÊTE ---
        BorderPane header = new BorderPane();

        this.btnHome = new Button();
        try {
            ImageView homeImageView = new ImageView(new Image(getClass().getResourceAsStream("/img/70083.png")));
            homeImageView.setFitWidth(30);
            homeImageView.setFitHeight(30);
            homeImageView.setPreserveRatio(true);
            this.btnHome.setGraphic(homeImageView);
            this.btnHome.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        } catch (Exception e) {
            this.btnHome.setText("Accueil");
        }

        Label lblTitre = new Label("Espace Administrateur");
        lblTitre.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        lblTitre.setStyle("-fx-text-fill: #0055BF;");

        ImageView logoView = new ImageView();
        try {
            logoView.setImage(new Image(getClass().getResourceAsStream("/img/LogoPrincipale.png")));
            logoView.setFitHeight(40);
            logoView.setPreserveRatio(true);
        } catch (Exception e) {
        }

        header.setLeft(btnHome);
        header.setCenter(lblTitre);
        header.setRight(logoView);
        BorderPane.setAlignment(lblTitre, Pos.CENTER);

        Separator separateur = new Separator();
        separateur.setStyle("-fx-background-color: #F6D304; -fx-border-width: 2px;");

        // --- BOUTONS D'ACTION (Liés au Modèle RequetesLEGO) ---
        VBox conteneurBoutons = new VBox(15);
        conteneurBoutons.setAlignment(Pos.CENTER);
        conteneurBoutons.setPadding(new Insets(30, 0, 0, 0));

        String styleBoutonBleu = "-fx-background-color: #0055BF; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 12 30; -fx-background-radius: 8; -fx-cursor: hand;";

        btnAjoutPiece = new Button("Insérer une nouvelle Pièce");
        btnAjoutPiece.setStyle(styleBoutonBleu);
        btnAjoutPiece.setPrefWidth(350);

        btnAjoutTheme = new Button("Créer un nouveau Thème / Sous-thème");
        btnAjoutTheme.setStyle(styleBoutonBleu);
        btnAjoutTheme.setPrefWidth(350);

        btnAjoutBoite = new Button("Ajouter une Boîte au catalogue officiel");
        btnAjoutBoite.setStyle(styleBoutonBleu);
        btnAjoutBoite.setPrefWidth(350);

        btnAjoutFigurine = new Button("Insérer une nouvelle Figurine");
        btnAjoutFigurine.setStyle(styleBoutonBleu);
        btnAjoutFigurine.setPrefWidth(350);

        btnDeconnexion = new Button("Déconnexion");
        btnDeconnexion.setStyle(
                "-fx-background-color: #E3000B; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5; -fx-cursor: hand;");
        VBox.setMargin(btnDeconnexion, new Insets(40, 0, 0, 0));

        this.btnCatalogue = new Button("Consulter le catalogue officiel");
        this.btnCatalogue.setStyle(styleBoutonBleu);
        this.btnCatalogue.setPrefWidth(350);
        conteneurBoutons.getChildren().addAll(btnAjoutPiece, btnAjoutTheme, btnAjoutBoite, btnAjoutFigurine,
                btnCatalogue, btnDeconnexion);

        this.getChildren().addAll(header, separateur, conteneurBoutons);
    }

    public Button getBtnAjoutPiece() {
        return btnAjoutPiece;
    }

    public Button getBtnAjoutTheme() {
        return btnAjoutTheme;
    }

    public Button getBtnAjoutBoite() {
        return btnAjoutBoite;
    }
    public Button getBtnFigurines() {
        return btnAjoutFigurine;
    }

    public Button getBtnDeconnexion() {
        return btnDeconnexion;
    }

    public Button getBtnHome() {
        return btnHome;
    }

    public Button getBtnCatalogue() {
        return btnCatalogue;
    }


}