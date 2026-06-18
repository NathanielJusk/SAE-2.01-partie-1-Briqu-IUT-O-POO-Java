package fr.univorleans.iut45.briquiuto.IHM.Vue.admin;

import fr.univorleans.iut45.briquiuto.modele.Theme;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ViewNewTheme extends VBox {

    /**
     * Petite vue d'ajout de thème. Contient un champ nom et un bouton valider.
    * Simple et adaptée à un rendu étudiant; je me suis aidé de la documentation JavaFX.
     */

    private Button btnHome;
    private Button btnRetour;
    private TextField txtNumero;
    private TextField txtNom;
    private ComboBox<Theme> cbParent;
    private Button btnValider;

    public ViewNewTheme() {
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

        Label lblTitre = new Label("Créer un thème ou sous-thème");
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

        Label lblNum = new Label("Numéro du thème :"); lblNum.setStyle(styleLabel);
        txtNumero = new TextField(); txtNumero.setStyle(styleInput); txtNumero.setPrefWidth(300);

        Label lblNom = new Label("Nom du thème :"); lblNom.setStyle(styleLabel);
        txtNom = new TextField(); txtNom.setStyle(styleInput); txtNom.setPrefWidth(300);

        Label lblParent = new Label("Thème parent (optionnel) :"); lblParent.setStyle(styleLabel);
        cbParent = new ComboBox<>(); cbParent.setStyle(styleInput); cbParent.setPrefWidth(300);
        cbParent.setPromptText("Aucun parent (Thème principal)");

        grid.add(lblNum, 0, 0); grid.add(txtNumero, 1, 0);
        grid.add(lblNom, 0, 1); grid.add(txtNom, 1, 1);
        grid.add(lblParent, 0, 2); grid.add(cbParent, 1, 2);

        btnValider = new Button("Valider le thème");
        btnValider.setStyle("-fx-background-color: #287F46; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10 30; -fx-cursor: hand; -fx-background-radius: 5;");

        boxFormulaire.getChildren().addAll(grid, btnValider);

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
    public TextField getTxtNumero() { return txtNumero; }
    public TextField getTxtNom() { return txtNom; }
    public ComboBox<Theme> getCbParent() { return cbParent; }
    public Button getBtnValider() { return btnValider; }
    public void reinitialiserFormulaire() { txtNumero.clear(); txtNom.clear(); cbParent.getSelectionModel().clearSelection(); }
}