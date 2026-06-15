package fr.univorleans.iut45.briquiuto.IHM.Vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AjoutPieceVue extends VBox {

    // Attributs définis dans le diagramme de classes
    private Label lblTitre;
    private Label lblNumero;
    private TextField txtNumero;
    private Label lblCategorie;
    private TextField txtCategorie;
    private Label lblNom;
    private TextField txtNom;
    private Button btnValider;
    private Label lblErreur;

    public AjoutPieceVue() {
        // Configuration du conteneur principal VBox
        this.setSpacing(20);
        this.setPadding(new Insets(30));
        this.setStyle("-fx-background-color: #f5f5f5;");

        // --- EN-TÊTE (Correspondance à la maquette) ---
        BorderPane headerPane = new BorderPane();
        lblTitre = new Label("Ajouter une nouvelle piece");
        lblTitre.setFont(Font.font("Arial", 16));
        
        Label lblAdmin = new Label("Administrateur");
        lblAdmin.setFont(Font.font("Arial", FontWeight.NORMAL, 40)); // Grand texte à droite
        
        headerPane.setLeft(lblTitre);
        headerPane.setRight(lblAdmin);
        BorderPane.setAlignment(lblTitre, Pos.BOTTOM_LEFT);
        BorderPane.setAlignment(lblAdmin, Pos.BOTTOM_RIGHT);

        Separator ligneSeparatrice = new Separator();

        // --- FORMULAIRE ---
        GridPane grille = new GridPane();
        grille.setVgap(15);
        grille.setHgap(30);
        grille.setAlignment(Pos.CENTER);
        grille.setPadding(new Insets(20, 0, 20, 0));

        lblNumero = new Label("Numero de piece");
        txtNumero = new TextField();
        txtNumero.setStyle("-fx-background-color: #dcdcdc;"); // Fond gris selon maquette

        lblCategorie = new Label("Categorie de la pieces");
        txtCategorie = new TextField();
        txtCategorie.setStyle("-fx-background-color: #dcdcdc;");

        lblNom = new Label("Nom de la piece");
        txtNom = new TextField();
        txtNom.setStyle("-fx-background-color: #dcdcdc;");

        grille.add(lblNumero, 0, 0);
        grille.add(txtNumero, 1, 0);
        grille.add(lblCategorie, 0, 1);
        grille.add(txtCategorie, 1, 1);
        grille.add(lblNom, 0, 2);
        grille.add(txtNom, 1, 2);

        // --- BOUTON VALIDER ---
        btnValider = new Button("valider");
        btnValider.setStyle("-fx-background-color: #dcdcdc; -fx-text-fill: black; -fx-padding: 5 15;");
        
        // Conteneur pour centrer le bouton
        VBox btnContainer = new VBox(btnValider);
        btnContainer.setAlignment(Pos.CENTER);

        // --- LABEL ERREUR ---
        lblErreur = new Label("");
        lblErreur.setTextFill(Color.RED);
        lblErreur.setVisible(false); // Caché par défaut
        
        VBox errContainer = new VBox(lblErreur);
        errContainer.setAlignment(Pos.CENTER);
        errContainer.setPadding(new Insets(20, 0, 0, 0));

        // Assemblage
        this.getChildren().addAll(headerPane, ligneSeparatrice, grille, btnContainer, errContainer);
    }

    // --- METHODES GETTERS (Spécifiées dans le diagramme) ---
    public TextField getTxtNumero() { return txtNumero; }
    public TextField getTxtCategorie() { return txtCategorie; }
    public TextField getTxtNom() { return txtNom; }
    public Button getBtnValider() { return btnValider; }
    public Label getLblErreur() { return lblErreur; }

    // --- METHODES D'AFFICHAGE ET REINITIALISATION ---
    public void afficherErreur(String message) {
        lblErreur.setText(message);
        lblErreur.setVisible(true);
    }

    public void reinitialiserFormulaire() {
        txtNumero.clear();
        txtCategorie.clear();
        txtNom.clear();
        lblErreur.setVisible(false);
    }

    public ButtonBase getBtnHome() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBtnHome'");
    }
}