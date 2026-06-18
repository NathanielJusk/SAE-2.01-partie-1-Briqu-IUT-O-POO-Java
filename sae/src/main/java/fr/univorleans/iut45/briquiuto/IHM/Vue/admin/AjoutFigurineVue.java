package fr.univorleans.iut45.briquiuto.IHM.Vue.admin;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AjoutFigurineVue extends VBox {

    /**
     * Vue pour insérer une nouvelle figurine au catalogue.
     * Formulaire basique (id, nom, nb parties) et validation simple.
     * Rédigé en style étudiant pour la lisibilité du projet.
     */

    private Button btnHome;
    private Button btnRetour;
    private TextField txtIdFigurine;
    private TextField txtNomFigurine;
    private TextField txtNbParties;
    private Button btnValider;
    private Label lblMessage;

    public AjoutFigurineVue() {
        this.setSpacing(20);
        this.setPadding(new Insets(25));
        this.setStyle("-fx-background-color: #FFFFFF;");

        // 1. EN-TÊTE
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        
        this.btnHome = new Button();
        try {
            ImageView homeView = new ImageView(new Image(getClass().getResourceAsStream("/img/70083.png")));
            homeView.setFitWidth(35); homeView.setFitHeight(35); homeView.setPreserveRatio(true);
            this.btnHome.setGraphic(homeView);
            this.btnHome.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        } catch (Exception e) { this.btnHome.setText("🏠"); }

        Label lblTitre = new Label("Ajouter une nouvelle figurine");
        lblTitre.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        lblTitre.setStyle("-fx-text-fill: #0055BF;");
        header.getChildren().addAll(btnHome, lblTitre);

        Separator separateur = new Separator();
        separateur.setStyle("-fx-background-color: #F6D304; -fx-border-width: 2px;");

        // 2. FORMULAIRE CENTRAL
        VBox boxFormulaire = new VBox(20);
        boxFormulaire.setAlignment(Pos.CENTER);
        boxFormulaire.setStyle("-fx-background-color: #F8F9FA; -fx-border-color: #E0E0E0; -fx-border-radius: 8; -fx-padding: 40;");
        
        GridPane grid = new GridPane();
        grid.setHgap(15); grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);

        String styleLabel = "-fx-font-weight: bold; -fx-font-size: 14px;";
        String styleInput = "-fx-border-color: #0055BF; -fx-border-width: 2px; -fx-border-radius: 4px; -fx-padding: 5px;";

        Label lblId = new Label("Identifiant (Ex: fig-001) :"); lblId.setStyle(styleLabel);
        txtIdFigurine = new TextField(); txtIdFigurine.setStyle(styleInput); txtIdFigurine.setPrefWidth(300);

        Label lblNom = new Label("Nom de la figurine :"); lblNom.setStyle(styleLabel);
        txtNomFigurine = new TextField(); txtNomFigurine.setStyle(styleInput); txtNomFigurine.setPrefWidth(300);

        Label lblParties = new Label("Nombre de parties :"); lblParties.setStyle(styleLabel);
        txtNbParties = new TextField(); txtNbParties.setStyle(styleInput); txtNbParties.setPrefWidth(300);

        grid.add(lblId, 0, 0); grid.add(txtIdFigurine, 1, 0);
        grid.add(lblNom, 0, 1); grid.add(txtNomFigurine, 1, 1);
        grid.add(lblParties, 0, 2); grid.add(txtNbParties, 1, 2);

        btnValider = new Button("Valider la figurine");
        btnValider.setStyle("-fx-background-color: #287F46; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10 30; -fx-cursor: hand; -fx-background-radius: 5;");
        
        lblMessage = new Label("");
        lblMessage.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        boxFormulaire.getChildren().addAll(grid, btnValider, lblMessage);

        // 3. RESSORT
        Region ressort = new Region();
        VBox.setVgrow(ressort, Priority.ALWAYS);

        // 4. PIED DE PAGE
        HBox footer = new HBox();
        footer.setAlignment(Pos.BOTTOM_LEFT); 
        this.btnRetour = new Button();
        try {
            ImageView retourView = new ImageView(new Image(getClass().getResourceAsStream("/img/logoRetour.png")));
            retourView.setFitWidth(50); retourView.setFitHeight(50); retourView.setPreserveRatio(true);
            this.btnRetour.setGraphic(retourView);
            this.btnRetour.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        } catch (Exception e) { 
            this.btnRetour.setText("⬅ Retour"); 
            this.btnRetour.setStyle("-fx-background-color: #E3000B; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 10 20; -fx-background-radius: 5;");
        }
        footer.getChildren().add(btnRetour);

        // Assemblage
        this.getChildren().addAll(header, separateur, boxFormulaire, ressort, footer);
    }

    public Button getBtnHome() { return btnHome; }
    public Button getBtnRetour() { return btnRetour; } // Pour le lier dans le contrôleur !
    public TextField getTxtIdFigurine() { return txtIdFigurine; }
    public TextField getTxtNomFigurine() { return txtNomFigurine; }
    public TextField getTxtNbParties() { return txtNbParties; }
    public Button getBtnValider() { return btnValider; }
    public void afficherMessage(String msg, String color) { lblMessage.setText(msg); lblMessage.setStyle("-fx-text-fill: " + color + ";"); }
    public void reinitialiserFormulaire() { txtIdFigurine.clear(); txtNomFigurine.clear(); txtNbParties.clear(); }
}