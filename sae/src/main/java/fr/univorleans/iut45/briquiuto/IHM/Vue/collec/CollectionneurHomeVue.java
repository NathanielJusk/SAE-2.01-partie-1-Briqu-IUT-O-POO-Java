package fr.univorleans.iut45.briquiuto.IHM.Vue.collec;

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

public class CollectionneurHomeVue extends VBox {

    /**
     * Vue principale pour l'espace collectionneur.
     * Contient les boutons de navigation vers recherche, composition et MOCs.
     * Mise en page simple et adaptée pour un projet de première année.
     */

    private Button btnRechercheParPiece;
    private Button btnExplorerParTheme;
    private Button btnDetailsBoite;
    private Button btnComposerBoite;
    private Button btnMesMOCs; // NOUVEAU BOUTON
    private Button btnDeconnexion;
    private Button btnHome;

    public CollectionneurHomeVue() {
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
            this.btnHome.setText("acceuil");
        }

        Label lblTitre = new Label("Espace Collectionneur");
        lblTitre.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        lblTitre.setStyle("-fx-text-fill: #0055BF;");

        header.setLeft(btnHome);
        header.setCenter(lblTitre);
        BorderPane.setAlignment(lblTitre, Pos.CENTER);

        Separator separateur = new Separator();
        separateur.setStyle("-fx-background-color: #F6D304; -fx-border-width: 2px;");

        // --- BOUTONS D'ACTION ---
        VBox conteneurBoutons = new VBox(15);
        conteneurBoutons.setAlignment(Pos.CENTER);
        conteneurBoutons.setPadding(new Insets(20, 0, 0, 0));

        String styleBoutonBleu = "-fx-background-color: #0055A4; -fx-border-color: #003D7A; -fx-border-width: 0 0 4 0; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 12 30; -fx-background-radius: 8; -fx-border-radius: 8; -fx-cursor: hand;";
        String styleBoutonRouge = "-fx-background-color: #DA291C; -fx-border-color: #A31E14; -fx-border-width: 0 0 4 0; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 12 30; -fx-background-radius: 8; -fx-border-radius: 8; -fx-cursor: hand;";
        String styleBoutonVert = "-fx-background-color: #00852B; -fx-border-color: #005C1E; -fx-border-width: 0 0 4 0; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 12 30; -fx-background-radius: 8; -fx-border-radius: 8; -fx-cursor: hand;";
        String styleBoutonNoir = "-fx-background-color: #27251F; -fx-border-color: #11100E; -fx-border-width: 0 0 4 0; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 12 30; -fx-background-radius: 8; -fx-border-radius: 8; -fx-cursor: hand;";
        String styleBoutonGris = "-fx-background-color: #6C6E68; -fx-border-color: #4A4B47; -fx-border-width: 0 0 4 0; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 12 30; -fx-background-radius: 8; -fx-border-radius: 8; -fx-cursor: hand;";
        String styleBoutonOrange = "-fx-background-color: #FF6900; -fx-border-color: #C25000; -fx-border-width: 0 0 4 0; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 12 30; -fx-background-radius: 8; -fx-border-radius: 8; -fx-cursor: hand;";        
       
        btnRechercheParPiece = new Button("Rechercher une boîte par pièce");
        btnRechercheParPiece.setStyle(styleBoutonBleu);
        btnRechercheParPiece.setPrefWidth(350);

        btnExplorerParTheme = new Button("Explorer le catalogue par Thème");
        btnExplorerParTheme.setStyle(styleBoutonVert);
        btnExplorerParTheme.setPrefWidth(350);

        btnDetailsBoite = new Button("Statistiques et Détails d'une Boîte");
        btnDetailsBoite.setStyle(styleBoutonNoir);
        btnDetailsBoite.setPrefWidth(350);

        btnComposerBoite = new Button("Composer une boîte personnalisée (MOC)");
        btnComposerBoite.setStyle(styleBoutonGris);
        btnComposerBoite.setPrefWidth(350);

    
        btnMesMOCs = new Button("Consulter mes Créations (MOCs)");
        btnMesMOCs.setStyle(styleBoutonOrange);
        btnMesMOCs.setPrefWidth(350);

        btnDeconnexion = new Button("Déconnexion");
        btnDeconnexion.setStyle(styleBoutonRouge);
        VBox.setMargin(btnDeconnexion, new Insets(30, 0, 0, 0));

        conteneurBoutons.getChildren().addAll(btnRechercheParPiece, btnExplorerParTheme, btnDetailsBoite, btnComposerBoite, btnMesMOCs, btnDeconnexion);

        this.getChildren().addAll(header, separateur, conteneurBoutons);
    }

    public Button getBtnRechercheParPiece() { return btnRechercheParPiece; }
    public Button getBtnExplorerParTheme() { return btnExplorerParTheme; }
    public Button getBtnDetailsBoite() { return btnDetailsBoite; }
    public Button getBtnComposerBoite() { return btnComposerBoite; }
    public Button getBtnMesMOCs() { return btnMesMOCs; }
    public Button getBtnDeconnexion() { return btnDeconnexion; }
    public Button getBtnHome() { return btnHome; }
}