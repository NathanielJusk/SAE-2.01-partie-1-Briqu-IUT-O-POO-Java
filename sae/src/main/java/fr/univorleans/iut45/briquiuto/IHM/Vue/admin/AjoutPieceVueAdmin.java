package fr.univorleans.iut45.briquiuto.IHM.Vue.admin;

import fr.univorleans.iut45.briquiuto.modele.Categorie;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AjoutPieceVueAdmin extends VBox {

    /**
     * Vue pour ajouter une nouvelle pièce/brique dans la base.
     * Contient les champs de saisie (id, nom, image) et un bouton Valider.
     * Commentaire concis rédigé comme par un étudiant (BUT1).
     */

    private Button btnHome;
    private Button btnRetour;
    private TextField txtNumeroPiece;
    private ComboBox<Categorie> cbCategorie;
    private TextField txtNomPiece;
    private Button btnValider;
    private Label lblMessage;

    public AjoutPieceVueAdmin() {
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

        Label lblTitre = new Label("Ajouter une nouvelle pièce");
        lblTitre.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        lblTitre.setStyle("-fx-text-fill: #0055BF;");
        header.getChildren().addAll(btnHome, lblTitre);

        Separator separateur = new Separator();
        separateur.setStyle("-fx-background-color: #F6D304; -fx-border-width: 2px;");

        // 2. FORMULAIRE CENTRAL (Le bloc gris)
        VBox boxFormulaire = new VBox(20);
        boxFormulaire.setAlignment(Pos.CENTER);
        boxFormulaire.setStyle("-fx-background-color: #F8F9FA; -fx-border-color: #E0E0E0; -fx-border-radius: 8; -fx-padding: 40;");
        
        GridPane grid = new GridPane();
        grid.setHgap(15); grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);

        String styleLabel = "-fx-font-weight: bold; -fx-font-size: 14px;";
        String styleInput = "-fx-border-color: #0055BF; -fx-border-width: 2px; -fx-border-radius: 4px; -fx-padding: 5px;";

        Label lblNum = new Label("Numéro de pièce :"); lblNum.setStyle(styleLabel);
        txtNumeroPiece = new TextField(); txtNumeroPiece.setStyle(styleInput); txtNumeroPiece.setPrefWidth(300);

        Label lblCat = new Label("Catégorie :"); lblCat.setStyle(styleLabel);
        cbCategorie = new ComboBox<>(); cbCategorie.setStyle(styleInput); cbCategorie.setPrefWidth(300);
        cbCategorie.setPromptText("Sélectionnez une catégorie...");

        Label lblNom = new Label("Nom de la pièce :"); lblNom.setStyle(styleLabel);
        txtNomPiece = new TextField(); txtNomPiece.setStyle(styleInput); txtNomPiece.setPrefWidth(300);

        grid.add(lblNum, 0, 0); grid.add(txtNumeroPiece, 1, 0);
        grid.add(lblCat, 0, 1); grid.add(cbCategorie, 1, 1);
        grid.add(lblNom, 0, 2); grid.add(txtNomPiece, 1, 2);

        btnValider = new Button("Valider la pièce");
        btnValider.setStyle("-fx-background-color: #287F46; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10 30; -fx-cursor: hand; -fx-background-radius: 5;");
        
        lblMessage = new Label("");
        lblMessage.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        boxFormulaire.getChildren().addAll(grid, btnValider, lblMessage);

        // 3. RESSORT POUR POUSSER LE BOUTON RETOUR EN BAS
        Region ressort = new Region();
        VBox.setVgrow(ressort, Priority.ALWAYS);

        // 4. PIED DE PAGE (Footer)
        HBox footer = new HBox();
        footer.setAlignment(Pos.BOTTOM_LEFT); 
        this.btnRetour = new Button();
        try {
            ImageView retourView = new ImageView(new Image(getClass().getResourceAsStream("/img/logoRetour.png")));
            retourView.setFitWidth(90); retourView.setFitHeight(90); retourView.setPreserveRatio(true);            this.btnRetour.setGraphic(retourView);
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
    public Button getBtnRetour() { return btnRetour; }
    public TextField getTxtNumeroPiece() { return txtNumeroPiece; }
    public ComboBox<Categorie> getCbCategorie() { return cbCategorie; }
    public TextField getTxtNomPiece() { return txtNomPiece; }
    public Button getBtnValider() { return btnValider; }
    public void afficherMessage(String msg, String color) { lblMessage.setText(msg); lblMessage.setStyle("-fx-text-fill: " + color + ";"); }
    public void reinitialiserFormulaire() { txtNumeroPiece.clear(); txtNomPiece.clear(); cbCategorie.getSelectionModel().clearSelection(); }
}