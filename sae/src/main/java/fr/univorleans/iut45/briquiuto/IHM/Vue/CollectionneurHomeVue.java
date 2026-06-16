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

public class CollectionneurHomeVue extends VBox {

    // Boutons correspondant aux exigences exactes de la SAE
    private Button btnRechercheParPiece; // rechercherBoitesContenantPiece()
    private Button btnExplorerParTheme;  // listerBoitesParTheme()
    private Button btnDetailsBoite;      // listerPiecesBoite(), listerFigurinesBoite()...
    private Button btnComposerBoite;     // ajouterPieceDansBoite()...
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
            homeImageView.setFitWidth(30); homeImageView.setFitHeight(30); homeImageView.setPreserveRatio(true);
            this.btnHome.setGraphic(homeImageView);
            this.btnHome.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        } catch (Exception e) { this.btnHome.setText("🏠"); }

        Label lblTitre = new Label("Espace Collectionneur");
        lblTitre.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        lblTitre.setStyle("-fx-text-fill: #0055BF;"); 
        
        ImageView logoView = new ImageView();
        try {
            logoView.setImage(new Image(getClass().getResourceAsStream("/img/LogoPrincipale.png")));
            logoView.setFitHeight(40);
            logoView.setPreserveRatio(true);
        } catch (Exception e) {}

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

        btnRechercheParPiece = new Button("Rechercher les Boîtes contenant une Pièce");
        btnRechercheParPiece.setStyle(styleBoutonBleu); 
        btnRechercheParPiece.setPrefWidth(350);

        btnExplorerParTheme = new Button("Explorer le catalogue par Thèmes");
        btnExplorerParTheme.setStyle(styleBoutonBleu); 
        btnExplorerParTheme.setPrefWidth(350);

        btnDetailsBoite = new Button("Voir le contenu détaillé d'une Boîte");
        btnDetailsBoite.setStyle(styleBoutonBleu); 
        btnDetailsBoite.setPrefWidth(350);

        btnComposerBoite = new Button("Composer une Boîte Personnalisée");
        // On le met en Vert LEGO pour le différencier (c'est une action de création)
        btnComposerBoite.setStyle("-fx-background-color: #287F46; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 12 30; -fx-background-radius: 8; -fx-cursor: hand;"); 
        btnComposerBoite.setPrefWidth(350);

        btnDeconnexion = new Button("Déconnexion");
        btnDeconnexion.setStyle("-fx-background-color: #E3000B; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5; -fx-cursor: hand;");
        VBox.setMargin(btnDeconnexion, new Insets(40, 0, 0, 0));

        conteneurBoutons.getChildren().addAll(
            btnRechercheParPiece, 
            btnExplorerParTheme, 
            btnDetailsBoite, 
            btnComposerBoite, 
            btnDeconnexion
        );

        this.getChildren().addAll(header, separateur, conteneurBoutons);
    }

    public Button getBtnRechercheParPiece() { return btnRechercheParPiece; }
    public Button getBtnExplorerParTheme() { return btnExplorerParTheme; }
    public Button getBtnDetailsBoite() { return btnDetailsBoite; }
    public Button getBtnComposerBoite() { return btnComposerBoite; }
    public Button getBtnDeconnexion() { return btnDeconnexion; }
    public Button getBtnHome() { return btnHome; }
}