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

/**
 * Vue principale pour l'administrateur (menu).
 * Contient les boutons pour ajouter des éléments et naviguer.
 * Mise en page simple et lisible, conçue pour un projet de première année.
 */
public class AdminHomeVue extends VBox {

    // Boutons correspondant aux exigences de la SAE et statistiques
    private Button btnAjoutPiece;
    private Button btnAjoutTheme;
    private Button btnAjoutBoite; 
    private Button btnAjoutFigurine; 
    private Button btnCatalogue; 
    private Button btnStatsAdmin; 
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
            this.btnHome.setText("accueil");
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

        String styleBoutonBleu = "-fx-background-color: #0055A4; -fx-border-color: #003D7A; -fx-border-width: 0 0 4 0; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 12 30; -fx-background-radius: 8; -fx-border-radius: 8; -fx-cursor: hand;";
        String styleBoutonRouge = "-fx-background-color: #DA291C; -fx-border-color: #A31E14; -fx-border-width: 0 0 4 0; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 12 30; -fx-background-radius: 8; -fx-border-radius: 8; -fx-cursor: hand;";
        String styleBoutonVert = "-fx-background-color: #00852B; -fx-border-color: #005C1E; -fx-border-width: 0 0 4 0; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 12 30; -fx-background-radius: 8; -fx-border-radius: 8; -fx-cursor: hand;";
        String styleBoutonNoir = "-fx-background-color: #27251F; -fx-border-color: #11100E; -fx-border-width: 0 0 4 0; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 12 30; -fx-background-radius: 8; -fx-border-radius: 8; -fx-cursor: hand;";
        String styleBoutonGris = "-fx-background-color: #6C6E68; -fx-border-color: #4A4B47; -fx-border-width: 0 0 4 0; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 12 30; -fx-background-radius: 8; -fx-border-radius: 8; -fx-cursor: hand;";
        String styleBoutonOrange = "-fx-background-color: #FF6900; -fx-border-color: #C25000; -fx-border-width: 0 0 4 0; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 12 30; -fx-background-radius: 8; -fx-border-radius: 8; -fx-cursor: hand;";        
        
        btnAjoutPiece = new Button("Insérer une nouvelle Pièce");
        btnAjoutPiece.setStyle(styleBoutonBleu);
        btnAjoutPiece.setPrefWidth(350);

        btnAjoutTheme = new Button("Créer un nouveau Thème");
        btnAjoutTheme.setStyle(styleBoutonVert);
        btnAjoutTheme.setPrefWidth(350);

        btnAjoutBoite = new Button("Ajouter une Boîte au Catalogue");
        btnAjoutBoite.setStyle(styleBoutonRouge);
        btnAjoutBoite.setPrefWidth(350);

        btnAjoutFigurine = new Button("Insérer une nouvelle Figurine");
        btnAjoutFigurine.setStyle(styleBoutonNoir);
        btnAjoutFigurine.setPrefWidth(350);

        btnCatalogue = new Button("Consulter le catalogue officiel");
        btnCatalogue.setStyle(styleBoutonGris);
        btnCatalogue.setPrefWidth(350);

        btnStatsAdmin = new Button("Afficher les Statistiques d'une Boîte");
        btnStatsAdmin.setStyle(styleBoutonOrange);
        btnStatsAdmin.setPrefWidth(350);

        btnDeconnexion = new Button("Déconnexion");
        btnDeconnexion.setStyle(styleBoutonRouge);
        VBox.setMargin(btnDeconnexion, new Insets(30, 0, 0, 0));

        // Ajout de tous les boutons au panneau central vertical
        conteneurBoutons.getChildren().addAll(
                btnAjoutPiece, 
                btnAjoutTheme, 
                btnAjoutBoite, 
                btnAjoutFigurine,
                btnCatalogue, 
                btnStatsAdmin, 
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
    public Button getBtnStatsAdmin() { return btnStatsAdmin; } 
    public Button getBtnDeconnexion() { return btnDeconnexion; }
    public Button getBtnHome() { return btnHome; }
}