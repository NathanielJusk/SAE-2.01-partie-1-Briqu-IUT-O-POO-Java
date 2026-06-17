package fr.univorleans.iut45.briquiuto.IHM.Vue.admin;

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

    // Boutons correspondant aux exigences de la SAE et statistiques
    private Button btnAjoutPiece;
    private Button btnAjoutTheme;
    private Button btnAjoutBoite; 
    private Button btnAjoutFigurine; 
    private Button btnCatalogue; 
    private Button btnStatsAdmin; // <- NOUVEAU BOUTON AJOUTÉ
    private Button btnDeconnexion;
    private Button btnHome;

    public AdminHomeVue() {
        this.setSpacing(20);
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
            this.btnHome.setText("🏠");
        }

        Label lblTitre = new Label("Menu Principal - Administrateur");
        lblTitre.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        lblTitre.setStyle("-fx-text-fill: #0055BF;");

        header.setLeft(btnHome);
        header.setCenter(lblTitre);

        Separator separateur = new Separator();
        separateur.setStyle("-fx-background-color: #F6D304; -fx-border-width: 2px;");

        // --- CONTENEUR DES BOUTONS ---
        VBox conteneurBoutons = new VBox(12);
        conteneurBoutons.setAlignment(Pos.CENTER);
        conteneurBoutons.setPadding(new Insets(10, 0, 0, 0));

        String styleBoutonBleu = "-fx-background-color: #0055BF; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 12 30; -fx-background-radius: 8; -fx-cursor: hand;";

        btnAjoutPiece = new Button("Insérer une nouvelle Pièce");
        btnAjoutPiece.setStyle(styleBoutonBleu);
        btnAjoutPiece.setPrefWidth(350);

        btnAjoutTheme = new Button("Créer un nouveau Thème");
        btnAjoutTheme.setStyle(styleBoutonBleu);
        btnAjoutTheme.setPrefWidth(350);

        btnAjoutBoite = new Button("Ajouter une Boîte au Catalogue");
        btnAjoutBoite.setStyle(styleBoutonBleu);
        btnAjoutBoite.setPrefWidth(350);

        btnAjoutFigurine = new Button("Insérer une nouvelle Figurine");
        btnAjoutFigurine.setStyle(styleBoutonBleu);
        btnAjoutFigurine.setPrefWidth(350);

        btnCatalogue = new Button("Consulter le catalogue officiel");
        btnCatalogue.setStyle(styleBoutonBleu);
        btnCatalogue.setPrefWidth(350);

        // CONFIGURATION DU NOUVEAU BOUTON STATISTIQUES (Couleur Bleu LEGO standard)
        btnStatsAdmin = new Button("Afficher les Statistiques d'une Boîte");
        btnStatsAdmin.setStyle(styleBoutonBleu);
        btnStatsAdmin.setPrefWidth(350);

        btnDeconnexion = new Button("Déconnexion");
        btnDeconnexion.setStyle("-fx-background-color: #E3000B; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5; -fx-cursor: hand;");
        VBox.setMargin(btnDeconnexion, new Insets(30, 0, 0, 0));

        // Ajout de tous les boutons au panneau central vertical
        conteneurBoutons.getChildren().addAll(
                btnAjoutPiece, 
                btnAjoutTheme, 
                btnAjoutBoite, 
                btnAjoutFigurine,
                btnCatalogue, 
                btnStatsAdmin, // <- Inséré ici dans l'affichage
                btnDeconnexion
        );

        this.getChildren().addAll(header, separateur, conteneurBoutons);
    }

    // --- GETTERS POUR LE CONTRÔLEUR ---
    public Button getBtnAjoutPiece() { return btnAjoutPiece; }
    public Button getBtnAjoutTheme() { return btnAjoutTheme; }
    public Button getBtnAjoutBoite() { return btnAjoutBoite; }
    public Button getBtnFigurines() { return btnAjoutFigurine; }
    public Button getBtnCatalogue() { return btnCatalogue; }
    public Button getBtnStatsAdmin() { return btnStatsAdmin; } // <- Getter pour capter le clic
    public Button getBtnDeconnexion() { return btnDeconnexion; }
    public Button getBtnHome() { return btnHome; }
}