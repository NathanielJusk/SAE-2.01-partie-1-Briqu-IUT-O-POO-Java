package fr.univorleans.iut45.briquiuto.IHM.Vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class VueModifierBoite extends VBox {

    /**
     * Vue pour modifier une boîte existante.
     * Interface simple avec 3 états : recherche, édition, erreur.
     * Commentaires simples écrits comme par un étudiant de BUT1.
    * Pour les parties JavaFX (layouts, visibilité, etc.) je me suis
    * aidé de la Javadoc officielle de JavaFX pour comprendre les API.
     */

    // En-tête
    private Label lblTitre;
    private Button btnHome;

    // Formulaire (GridPane)
    private GridPane gridForm;
    private Label lblNumero;
    private TextField txtNumero;
    private Button btnRechercher;
    private Label lblErreur;

    private Label lblNbPieces;
    private TextField txtNbPieces;
    private Label lblNom;
    private TextField txtNom;
    private Label lblAnnee;
    private TextField txtAnnee;
    private Button btnValider;

    public VueModifierBoite() {
        // 1. Configuration de la VBox principale
        this.setSpacing(20);
        this.setPadding(new Insets(30));
        this.setAlignment(Pos.TOP_CENTER);
        this.setStyle("-fx-background-color: #F8F9FA;"); 

        // 2. Création de l'en-tête avec le titre et le bouton "Home"
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);
        header.setStyle("-fx-border-color: transparent transparent black transparent; -fx-border-width: 0 0 1 0;");
        header.setPadding(new Insets(0, 0, 10, 0));

        lblTitre = new Label("Modifier une boite");
        lblTitre.setFont(Font.font("System", FontWeight.BOLD, 18));

        btnHome = new Button("accueil"); 
        btnHome.setStyle("-fx-background-color: transparent; -fx-font-size: 20px; -fx-cursor: hand;");

        header.getChildren().addAll(lblTitre, btnHome);
        HBox.setHgrow(lblTitre, Priority.ALWAYS);

        // 3. Création du GridPane pour les 3 états du graphe de scène
        gridForm = new GridPane();
        gridForm.setAlignment(Pos.CENTER);
        gridForm.setHgap(15);
        gridForm.setVgap(15);
        gridForm.setPadding(new Insets(20, 0, 0, 0));

        // -- Ligne 0 : Recherche
        lblNumero = new Label("Numero de boite");
        txtNumero = new TextField();
        txtNumero.setPromptText("Ex: B001");
        btnRechercher = new Button("rechercher");
        
        gridForm.add(lblNumero, 0, 0);
        gridForm.add(txtNumero, 1, 0);
        gridForm.add(btnRechercher, 2, 0);

        // -- Message d'erreur (État 3)
        lblErreur = new Label("Aucune boite trouver pour ce numero");
        lblErreur.setTextFill(Color.RED);
        lblErreur.setVisible(false); // Caché par défaut
        lblErreur.setManaged(false);
        gridForm.add(lblErreur, 1, 1, 2, 1);

        // -- Champs d'édition (État 2)
        lblNbPieces = new Label("Nombre de pieces");
        txtNbPieces = new TextField();
        
        lblNom = new Label("Nom de la boite");
        txtNom = new TextField();
        
        lblAnnee = new Label("Année de la boite");
        txtAnnee = new TextField();

        btnValider = new Button("valider");

        // Ajout des champs d'édition à la grille (Cachés par défaut)
        gridForm.add(lblNbPieces, 0, 2);
        gridForm.add(txtNbPieces, 1, 2);
        
        gridForm.add(lblNom, 0, 3);
        gridForm.add(txtNom, 1, 3);
        
        gridForm.add(lblAnnee, 0, 4);
        gridForm.add(txtAnnee, 1, 4);
        
        gridForm.add(btnValider, 1, 5);

        // Masquer les champs d'édition initialement
        cacherModeEdition();

        // 4. Assemblage final
        this.getChildren().addAll(header, gridForm);
    }

    // ── Méthodes pour basculer entre les 3 graphes de scène de la maquette ──

    /**
     * Affiche uniquement la barre de recherche (État 1)
     */
    public void reinitialiserVue() {
        lblErreur.setVisible(false);
        lblErreur.setManaged(false);
        cacherModeEdition();
        txtNumero.clear();
    }

    /**
     * Affiche les champs de modification "XXXXXX" (État 2)
     */
    public void afficherModeEdition(String nbPieces, String nom, String annee) {
        lblErreur.setVisible(false);
        lblErreur.setManaged(false);
        
        txtNbPieces.setText(nbPieces);
        txtNom.setText(nom);
        txtAnnee.setText(annee);

        basculerVisibiliteEdition(true);
    }

    /**
     * Affiche le message d'erreur (État 3)
     */
    public void afficherModeErreur() {
        cacherModeEdition();
        lblErreur.setVisible(true);
        lblErreur.setManaged(true);
    }

    // ── Méthodes utilitaires et Getters pour le Contrôleur ──

    private void cacherModeEdition() {
        basculerVisibiliteEdition(false);
    }

    private void basculerVisibiliteEdition(boolean visible) {
        lblNbPieces.setVisible(visible);  lblNbPieces.setManaged(visible);
        txtNbPieces.setVisible(visible);  txtNbPieces.setManaged(visible);
        lblNom.setVisible(visible);       lblNom.setManaged(visible);
        txtNom.setVisible(visible);       txtNom.setManaged(visible);
        lblAnnee.setVisible(visible);     lblAnnee.setManaged(visible);
        txtAnnee.setVisible(visible);     txtAnnee.setManaged(visible);
        btnValider.setVisible(visible);   btnValider.setManaged(visible);
    }

    public Button getBtnHome() { return btnHome; }
    public Button getBtnRechercher() { return btnRechercher; }
    public Button getBtnValider() { return btnValider; }
    public TextField getTxtNumero() { return txtNumero; }
    public TextField getTxtNbPieces() { return txtNbPieces; }
    public TextField getTxtNom() { return txtNom; }
    public TextField getTxtAnnee() { return txtAnnee; }
}