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

public class CollectionneurHomeVue extends VBox {

    private Button btnMaCollection;
    private Button btnRechercheBoite;
    private Button btnCompoBoite;
    private Button btnDeconnexion;
    private Button btnHome;

    public CollectionneurHomeVue() {
        this.setSpacing(25);
        this.setPadding(new Insets(30));
        this.setStyle("-fx-background-color: #FFFFFF;"); 

        // --- EN-TÊTE STANDARD ---
        HBox header = new HBox(20);
        header.setAlignment(Pos.CENTER_LEFT);
        
        this.btnHome = new Button();
        try {
            Image homeImage = new Image(getClass().getResourceAsStream("/img/70083.png"));
            ImageView homeImageView = new ImageView(homeImage);
            homeImageView.setFitWidth(35); homeImageView.setFitHeight(35); homeImageView.setPreserveRatio(true);
            this.btnHome.setGraphic(homeImageView);
            this.btnHome.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        } catch (Exception e) {
            this.btnHome.setText("🏠");
        }

        Label lblTitre = new Label("Espace Collectionneur");
        lblTitre.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        lblTitre.setStyle("-fx-text-fill: #0055BF;");
        
        header.getChildren().addAll(btnHome, lblTitre);

        // --- SÉPARATEUR JAUNE LEGO ---
        Separator separateur = new Separator();
        separateur.setStyle("-fx-background-color: #F6D304; -fx-border-width: 2px;");

        // --- BOUTONS D'ACTION ---
        VBox conteneurBoutons = new VBox(15);
        conteneurBoutons.setAlignment(Pos.CENTER);
        conteneurBoutons.setPadding(new Insets(20, 0, 0, 0));

        String styleBoutonBleu = "-fx-background-color: #0055BF; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 12 30; -fx-background-radius: 5;";

        btnMaCollection = new Button("Voir Ma Collection");
        btnMaCollection.setStyle(styleBoutonBleu);
        btnMaCollection.setPrefWidth(300);

        btnRechercheBoite = new Button("Rechercher une Boîte");
        btnRechercheBoite.setStyle(styleBoutonBleu);
        btnRechercheBoite.setPrefWidth(300);

        btnCompoBoite = new Button("Composer une Boîte Personnalisée");
        btnCompoBoite.setStyle(styleBoutonBleu);
        btnCompoBoite.setPrefWidth(300);

        btnDeconnexion = new Button("Déconnexion");
        btnDeconnexion.setStyle("-fx-background-color: #E3000B; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10 20; -fx-background-radius: 5;");
        
        VBox.setMargin(btnDeconnexion, new Insets(40, 0, 0, 0));

        conteneurBoutons.getChildren().addAll(btnMaCollection, btnRechercheBoite, btnCompoBoite, btnDeconnexion);

        this.getChildren().addAll(header, separateur, conteneurBoutons);
    }

    public Button getBtnMaCollection() { return btnMaCollection; }
    public Button getBtnRechercheBoite() { return btnRechercheBoite; }
    public Button getBtnCompoBoite() { return btnCompoBoite; }
    public Button getBtnDeconnexion() { return btnDeconnexion; }
    public Button getBtnHome() { return btnHome; }
}