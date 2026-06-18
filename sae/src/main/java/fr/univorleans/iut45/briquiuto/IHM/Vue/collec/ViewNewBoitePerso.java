package fr.univorleans.iut45.briquiuto.IHM.Vue.collec;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ViewNewBoitePerso extends VBox {

    /**
     * Petite vue d'aide pour créer une nouvelle boîte personnalisée.
     * Contient uniquement des contrôles basiques : titre, zone de saisie et boutons.
    * Je me suis aidé de la documentation JavaFX pour choisir les contrôles.
     */

    private TextField numTextField;
    private TextField nomTextField;
    private TextField anneeTextField;
    private TextField nbPiecesTextField;
    private Button validerButton;
    private Button homeButton;
    private Label lblErreur; 

    public ViewNewBoitePerso() {
        this.setSpacing(20);
        this.setPadding(new Insets(25));
        this.setStyle("-fx-background-color: #FFFFFF;");

        // --- EN-TÊTE ---
        HBox header = new HBox(20);
        header.setAlignment(Pos.CENTER_LEFT);
        
        this.homeButton = new Button();
        try {
            Image homeImage = new Image(getClass().getResourceAsStream("/img/70083.png"));
            ImageView homeImageView = new ImageView(homeImage);
            homeImageView.setFitWidth(35); homeImageView.setFitHeight(35); homeImageView.setPreserveRatio(true);
            this.homeButton.setGraphic(homeImageView);
            this.homeButton.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        } catch (Exception e) {
            this.homeButton.setText("accueil");
        }

        Label lblTitre = new Label("Ajouter une nouvelle Boîte LEGO");
        lblTitre.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        lblTitre.setStyle("-fx-text-fill: #0055BF;");
        header.getChildren().addAll(homeButton, lblTitre);

        Separator separateur = new Separator();
        separateur.setStyle("-fx-background-color: #F6D304; -fx-border-width: 2px;");

        // --- FORMULAIRE ---
        GridPane grid = new GridPane();
        grid.setVgap(15); grid.setHgap(15);
        grid.setAlignment(Pos.CENTER);

        String styleLabel = "-fx-font-weight: bold; -fx-font-size: 14px;";
        String styleChamp = "-fx-border-color: #0055BF; -fx-border-width: 2px; -fx-border-radius: 3; -fx-padding: 5;";

        Label numLabel = new Label("Numéro de boîte :"); numLabel.setStyle(styleLabel);
        numTextField = new TextField(); numTextField.setStyle(styleChamp);

        Label nomLabel = new Label("Nom de la boîte :"); nomLabel.setStyle(styleLabel);
        nomTextField = new TextField(); nomTextField.setStyle(styleChamp);

        Label anneeLabel = new Label("Année de sortie :"); anneeLabel.setStyle(styleLabel);
        anneeTextField = new TextField(); anneeTextField.setStyle(styleChamp);

        Label piecesLabel = new Label("Nombre de pièces :"); piecesLabel.setStyle(styleLabel);
        nbPiecesTextField = new TextField(); nbPiecesTextField.setStyle(styleChamp);

        // Ajout des composants au Grid (sans la ligne de la CheckBox)
        grid.add(numLabel, 0, 0); grid.add(numTextField, 1, 0);
        grid.add(nomLabel, 0, 1); grid.add(nomTextField, 1, 1);
        grid.add(anneeLabel, 0, 2); grid.add(anneeTextField, 1, 2);
        grid.add(piecesLabel, 0, 3); grid.add(nbPiecesTextField, 1, 3);

        // --- ZONE DE STATUT / ERREUR ---
        lblErreur = new Label("");
        lblErreur.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));

        // --- BOUTON VALIDER ---
        validerButton = new Button("Enregistrer la boîte");
        validerButton.setStyle("-fx-background-color: #287F46; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10 30; -fx-background-radius: 5;");
        
        VBox bottomContainer = new VBox(15, lblErreur, validerButton);
        bottomContainer.setAlignment(Pos.CENTER);

        this.getChildren().addAll(header, separateur, grid, bottomContainer);
    }

    public TextField getNumTextField() { return numTextField; }
    public TextField getNomTextField() { return nomTextField; }
    public TextField getAnneeTextField() { return anneeTextField; }
    public TextField getNbPiecesTextField() { return nbPiecesTextField; }
    public Button getValiderButton() { return validerButton; }
    public Button getHomeButton() { return homeButton; }
    public Label getLblErreur() { return lblErreur; }

    public void afficherMessage(String msg, javafx.scene.paint.Color couleur) {
        lblErreur.setTextFill(couleur);
        lblErreur.setText(msg);
    }

    public void reinitialiserFormulaire() {
        numTextField.clear();
        nomTextField.clear();
        anneeTextField.clear();
        nbPiecesTextField.clear();
    }
}