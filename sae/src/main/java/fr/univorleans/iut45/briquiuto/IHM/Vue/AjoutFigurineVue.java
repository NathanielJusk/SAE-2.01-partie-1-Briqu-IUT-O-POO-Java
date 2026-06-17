package fr.univorleans.iut45.briquiuto.IHM.Vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AjoutFigurineVue extends VBox {

    private TextField txtIdFig;
    private TextField txtNomFig;
    private TextField txtNbParties;
    private Button btnValider;
    private Label lblErreur;
    private Button btnHome;

    public AjoutFigurineVue() {
        this.setSpacing(25);
        this.setPadding(new Insets(30));
        this.setStyle("-fx-background-color: #FFFFFF;");

        // --- EN-TÊTE ---
        HBox header = new HBox(20);
        header.setAlignment(Pos.CENTER_LEFT);

        Label lblTitre = new Label("Ajouter une nouvelle figurine");
        lblTitre.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        lblTitre.setStyle("-fx-text-fill: #0055BF;");
        
        header.getChildren().add(lblTitre);

        Separator separateur = new Separator();
        separateur.setStyle("-fx-background-color: #F6D304; -fx-border-width: 2px;");

        // --- CONTENU CENTRAL (grille + bouton + retour) ---
        VBox contenuCentral = new VBox(30);
        contenuCentral.setAlignment(Pos.CENTER);
        contenuCentral.setPadding(new Insets(40, 120, 40, 120));
        contenuCentral.setStyle("-fx-background-color: #F8F9FA; -fx-background-radius: 10;");
        VBox.setVgrow(contenuCentral, javafx.scene.layout.Priority.ALWAYS);

        // --- FORMULAIRE ---
        GridPane grille = new GridPane();
        grille.setVgap(25);
        grille.setHgap(30);
        grille.setAlignment(Pos.CENTER);

        String styleLabel = "-fx-font-weight: bold; -fx-font-size: 15px;";
        String styleChamp = "-fx-border-color: #0055BF; -fx-border-width: 2px; -fx-border-radius: 3; -fx-padding: 8; -fx-font-size: 14px;";

        Label lblId = new Label("ID Figurine :"); 
        lblId.setStyle(styleLabel);
        txtIdFig = new TextField(); 
        txtIdFig.setStyle(styleChamp);
        txtIdFig.setPrefWidth(400);

        Label lblNom = new Label("Nom de la figurine :"); 
        lblNom.setStyle(styleLabel);
        txtNomFig = new TextField(); 
        txtNomFig.setStyle(styleChamp);
        txtNomFig.setPrefWidth(400);

        Label lblNbParties = new Label("Nombre de pièces :"); 
        lblNbParties.setStyle(styleLabel);
        txtNbParties = new TextField(); 
        txtNbParties.setStyle(styleChamp);
        txtNbParties.setPrefWidth(400);

        grille.add(lblId, 0, 0); grille.add(txtIdFig, 1, 0);
        grille.add(lblNom, 0, 1); grille.add(txtNomFig, 1, 1);
        grille.add(lblNbParties, 0, 2); grille.add(txtNbParties, 1, 2);

        // --- BOUTON VALIDER ---
        btnValider = new Button("Valider la figurine");
        btnValider.setStyle("-fx-background-color: #287F46; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 15px; -fx-padding: 12 50; -fx-background-radius: 5; -fx-cursor: hand;");

        lblErreur = new Label("");
        lblErreur.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        lblErreur.setVisible(false);

        // --- BOUTON RETOUR EN BAS ---
        this.btnHome = new Button();
        try {
            Image homeImage = new Image(getClass().getResourceAsStream("/img/logoRetour.png"));
            ImageView homeImageView = new ImageView(homeImage);
            homeImageView.setFitWidth(40);
            homeImageView.setFitHeight(40);
            homeImageView.setPreserveRatio(true);
            this.btnHome.setGraphic(homeImageView);
            this.btnHome.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        } catch (Exception e) {
            this.btnHome.setText("Retour");
        }
        HBox zoneRetour = new HBox(btnHome);
        zoneRetour.setAlignment(Pos.CENTER_LEFT);

        contenuCentral.getChildren().addAll(grille, btnValider, lblErreur, zoneRetour);
        this.getChildren().addAll(header, separateur, contenuCentral);
    }

    // Getters pour le contrôleur
    public TextField getTxtIdFig() { return txtIdFig; }
    public TextField getTxtNomFig() { return txtNomFig; }
    public TextField getTxtNbParties() { return txtNbParties; }
    public Button getBtnValider() { return btnValider; }
    public Button getBtnHome() { return btnHome; }

    public void afficherErreur(String message) { 
        lblErreur.setText(message); 
        lblErreur.setVisible(true); 
    }
    
    public void reinitialiserFormulaire() { 
        txtIdFig.clear(); 
        txtNomFig.clear(); 
        txtNbParties.clear(); 
        lblErreur.setVisible(false); 
    }
}