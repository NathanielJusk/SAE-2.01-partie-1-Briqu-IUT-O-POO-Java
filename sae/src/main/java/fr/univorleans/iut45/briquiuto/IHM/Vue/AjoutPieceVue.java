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

public class AjoutPieceVue extends VBox {

    private TextField txtNumero;
    private TextField txtCategorie;
    private TextField txtNom;
    private Button btnValider;
    private Label lblErreur;
    private Button btnHome;

    public AjoutPieceVue() {
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

        Label lblTitre = new Label("Ajouter une nouvelle pièce");
        lblTitre.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        lblTitre.setStyle("-fx-text-fill: #0055BF;");
        
        header.getChildren().addAll(btnHome, lblTitre);

        Separator separateur = new Separator();
        separateur.setStyle("-fx-background-color: #F6D304; -fx-border-width: 2px;");

        // --- FORMULAIRE ---
        GridPane grille = new GridPane();
        grille.setVgap(20);
        grille.setHgap(20);
        grille.setAlignment(Pos.CENTER);
        grille.setPadding(new Insets(30, 0, 20, 0));

        String styleLabel = "-fx-font-weight: bold; -fx-font-size: 14px;";
        String styleChamp = "-fx-border-color: #0055BF; -fx-border-width: 2px; -fx-border-radius: 3; -fx-padding: 5;";

        Label lblNumero = new Label("Numéro de pièce :"); lblNumero.setStyle(styleLabel);
        txtNumero = new TextField(); txtNumero.setStyle(styleChamp);

        Label lblCategorie = new Label("Catégorie :"); lblCategorie.setStyle(styleLabel);
        txtCategorie = new TextField(); txtCategorie.setStyle(styleChamp);

        Label lblNom = new Label("Nom de la pièce :"); lblNom.setStyle(styleLabel);
        txtNom = new TextField(); txtNom.setStyle(styleChamp);

        grille.add(lblNumero, 0, 0); grille.add(txtNumero, 1, 0);
        grille.add(lblCategorie, 0, 1); grille.add(txtCategorie, 1, 1);
        grille.add(lblNom, 0, 2); grille.add(txtNom, 1, 2);

        // --- BOUTON VALIDER (Vert LEGO) ---
        btnValider = new Button("Valider la pièce");
        btnValider.setStyle("-fx-background-color: #287F46; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10 30; -fx-background-radius: 5;");
        
        VBox btnContainer = new VBox(btnValider);
        btnContainer.setAlignment(Pos.CENTER);

        lblErreur = new Label("");
        lblErreur.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        lblErreur.setVisible(false);
        VBox errContainer = new VBox(lblErreur);
        errContainer.setAlignment(Pos.CENTER);
        errContainer.setPadding(new Insets(15, 0, 0, 0));

        this.getChildren().addAll(header, separateur, grille, btnContainer, errContainer);
    }

    public TextField getTxtNumero() { return txtNumero; }
    public TextField getTxtCategorie() { return txtCategorie; }
    public TextField getTxtNom() { return txtNom; }
    public Button getBtnValider() { return btnValider; }
    public Label getLblErreur() { return lblErreur; }
    public Button getBtnHome() { return btnHome; }

    public void afficherErreur(String message) { lblErreur.setText(message); lblErreur.setVisible(true); }
    public void reinitialiserFormulaire() { txtNumero.clear(); txtCategorie.clear(); txtNom.clear(); lblErreur.setVisible(false); }
}