package fr.univorleans.iut45.briquiuto.IHM.Vue;

import fr.univorleans.iut45.briquiuto.modele.Theme;
import fr.univorleans.iut45.briquiuto.modele.Piece;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;

/**
 * Vue permettant à l'administrateur d'ajouter une nouvelle boîte au catalogue global,
 * avec saisie des informations de base et ajout dynamique de pièces (contenu).
 */
public class VueAjoutBoiteCatalogue extends VBox {

    // En-tête
    private Button btnHome;
    private Label lblTitre;

    // Formulaire d'informations de base (GridPane)
    private TextField txtNumero;
    private TextField txtNom;
    private TextField txtAnnee;
    private ComboBox<Theme> cbTheme;

    // Zone dynamique du contenu (VBox + Bouton +)
    private VBox conteneurLignesPieces;
    private Button btnAjouterLigne;
    private ObservableList<Piece> cataloguePiecesDisponibles;
    private List<LignePieceContenu> listeLignesChoisies;

    // Pied de page / Actions
    private Button btnValider;
    private Label lblMessage;

    public VueAjoutBoiteCatalogue() {
        // 1. Configuration du conteneur général (VBox)
        this.setSpacing(20);
        this.setPadding(new Insets(25));
        this.setStyle("-fx-background-color: #FFFFFF;");
        
        this.listeLignesChoisies = new ArrayList<>();
        this.cataloguePiecesDisponibles = FXCollections.observableArrayList();

        // 2. Création de l'en-tête (Bouton Maison + Titre)
        HBox header = new HBox(20);
        header.setAlignment(Pos.CENTER_LEFT);

        this.btnHome = new Button();
        try {
            Image homeImage = new Image(getClass().getResourceAsStream("/img/70083.png"));
            ImageView homeImageView = new ImageView(homeImage);
            homeImageView.setFitWidth(35);
            homeImageView.setFitHeight(35);
            homeImageView.setPreserveRatio(true);
            this.btnHome.setGraphic(homeImageView);
            this.btnHome.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        } catch (Exception e) {
            this.btnHome.setText("Home");
        }

        lblTitre = new Label("Ajouter une nouvelle boîte au catalogue");
        lblTitre.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        lblTitre.setStyle("-fx-text-fill: #0055BF;"); // Bleu LEGO officiel

        header.getChildren().addAll(btnHome, lblTitre);

        Separator separateur = new Separator();
        separateur.setStyle("-fx-background-color: #F6D304; -fx-border-width: 1px;"); // Jaune LEGO

        // 3. Formulaire d'informations de base (GridPane)
        GridPane gridForm = new GridPane();
        gridForm.setHgap(15);
        gridForm.setVgap(12);
        gridForm.setPadding(new Insets(10, 0, 10, 0));

        String styleLabel = "-fx-font-weight: bold; -fx-font-size: 13px;";
        
        Label lblNum = new Label("Numéro officiel :"); lblNum.setStyle(styleLabel);
        txtNumero = new TextField(); txtNumero.setPromptText("Ex: 75192");

        Label lblNomBoite = new Label("Nom du set :"); lblNomBoite.setStyle(styleLabel);
        txtNom = new TextField(); txtNom.setPromptText("Ex: Millennium Falcon");

        Label lblAnneeBoite = new Label("Année de sortie :"); lblAnneeBoite.setStyle(styleLabel);
        txtAnnee = new TextField(); txtAnnee.setPromptText("Ex: 2024");

        Label lblThemeBoite = new Label("Thème associé :"); lblThemeBoite.setStyle(styleLabel);
        cbTheme = new ComboBox<>();
        cbTheme.setPromptText("Choisir un thème...");
        cbTheme.setPrefWidth(200);

        gridForm.add(lblNum, 0, 0);        gridForm.add(txtNumero, 1, 0);
        gridForm.add(lblNomBoite, 0, 1);   gridForm.add(txtNom, 1, 1);
        gridForm.add(lblAnneeBoite, 0, 2); gridForm.add(txtAnnee, 1, 2);
        gridForm.add(lblThemeBoite, 0, 3); gridForm.add(cbTheme, 1, 3);

        // 4. Section Contenu / Gestion des pièces (VBox dynamique de ta maquette en bas)
        VBox zoneContenu = new VBox(10);
        Label lblContenu = new Label("Contenu (Sélection des pièces) :");
        lblContenu.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        // C'est ce conteneur qui va recevoir les lignes HBox à chaque clic sur "+"
        conteneurLignesPieces = new VBox(8);
        
        ScrollPane scrollContenu = new ScrollPane(conteneurLignesPieces);
        scrollContenu.setFitToWidth(true);
        scrollContenu.setPrefHeight(150);
        scrollContenu.setStyle("-fx-background-color: transparent; -fx-border-color: #ccc; -fx-border-radius: 5;");

        btnAjouterLigne = new Button("+ Ajouter une pièce");
        btnAjouterLigne.setStyle("-fx-background-color: #0055BF; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand;");
        btnAjouterLigne.setOnAction(e -> ajouterNouvelleLignePiece());

        zoneContenu.getChildren().addAll(lblContenu, scrollContenu, btnAjouterLigne);
        VBox.setVgrow(zoneContenu, Priority.ALWAYS);

        // 5. Zone basse (Bouton de validation + Zone de message)
        btnValider = new Button("Enregistrer au Catalogue Global");
        btnValider.setStyle("-fx-background-color: #287F46; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10 25; -fx-background-radius: 5; -fx-cursor: hand;");

        lblMessage = new Label("");
        lblMessage.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 13));
        lblMessage.setVisible(false);

        HBox footer = new HBox(20);
        footer.setAlignment(Pos.CENTER);
        footer.getChildren().addAll(btnValider, lblMessage);

        // Assemblage final dans la VBox Racine
        this.getChildren().addAll(header, separateur, gridForm, zoneContenu, footer);
        
        // Ajouter une première ligne par défaut pour guider l'utilisateur
        ajouterNouvelleLignePiece();
    }

    /**
     * Ajoute dynamiquement une nouvelle HBox (Sélecteur + Quantité) dans le formulaire.
     */
    private void ajouterNouvelleLignePiece() {
        LignePieceContenu nouvelleLigne = new LignePieceContenu(cataloguePiecesDisponibles);
        listeLignesChoisies.add(nouvelleLigne);
        conteneurLignesPieces.getChildren().add(nouvelleLigne);
        
        // Bouton de suppression de ligne optionnel pour l'ergonomie
        nouvelleLigne.getBtnSupprimer().setOnAction(e -> {
            if (listeLignesChoisies.size() > 1) { // On garde au moins une ligne
                conteneurLignesPieces.getChildren().remove(nouvelleLigne);
                listeLignesChoisies.remove(nouvelleLigne);
            }
        });
    }

    /**
     * Alimente le catalogue général des pièces pour les menus déroulants dynamiques.
     */
    public void setPiecesDisponibles(List<Piece> pieces) {
        this.cataloguePiecesDisponibles.setAll(pieces);
    }

    /**
     * Alimente le ComboBox des thèmes officiels.
     */
    public void setThemesDisponibles(List<Theme> themes) {
        this.cbTheme.setItems(FXCollections.observableArrayList(themes));
    }

    /**
     * Permet de vider le formulaire après un enregistrement réussi.
     */
    public void reinitialiserFormulaire() {
        txtNumero.clear();
        txtNom.clear();
        txtAnnee.clear();
        cbTheme.getSelectionModel().clearSelection();
        conteneurLignesPieces.getChildren().clear();
        listeLignesChoisies.clear();
        ajouterNouvelleLignePiece(); // Remet une ligne vierge
    }

    public void afficherMessage(String msg, String codeCouleurHex) {
        lblMessage.setText(msg);
        lblMessage.setStyle("-fx-text-fill: " + codeCouleurHex + ";");
        lblMessage.setVisible(true);
    }

    // ── GETTERS POUR LE CONTRÔLEUR ──
    public Button getBtnHome() { return btnHome; }
    public Button getBtnValider() { return btnValider; }
    public TextField getTxtNumero() { return txtNumero; }
    public TextField getTxtNom() { return txtNom; }
    public TextField getTxtAnnee() { return txtAnnee; }
    public ComboBox<Theme> getCbTheme() { return cbTheme; }
    public List<LignePieceContenu> getListeLignesChoisies() { return listeLignesChoisies; }


    /**
     * Classe interne (Inner Class) représentant graphiquement une ligne de sélection de pièce.
     * Correspond exactement au HBox imbriqué de ton graphe de scène.
     */
    public static class LignePieceContenu extends HBox {
        private ComboBox<Piece> cbPiece;
        private TextField txtQuantite;
        private Button btnSupprimer;

        public LignePieceContenu(ObservableList<Piece> piecesDisponibles) {
            super(15);
            this.setAlignment(Pos.CENTER_LEFT);
            this.setPadding(new Insets(5));

            Label lblP = new Label("Pièce :");
            lblP.setStyle("-fx-font-weight: bold;");
            cbPiece = new ComboBox<>(piecesDisponibles);
            cbPiece.setPromptText("Sélectionner la brique...");
            cbPiece.setPrefWidth(220);

            Label lblQ = new Label("Qte :");
            lblQ.setStyle("-fx-font-weight: bold;");
            txtQuantite = new TextField();
            txtQuantite.setPromptText("Ex: 5");
            txtQuantite.setPrefWidth(60);

            btnSupprimer = new Button("No");
            btnSupprimer.setStyle("-fx-background-color: transparent; -fx-text-fill: red; -fx-cursor: hand;");

            this.getChildren().addAll(lblP, cbPiece, lblQ, txtQuantite, btnSupprimer);
        }

        public ComboBox<Piece> getCbPiece() { return cbPiece; }
        public TextField getTxtQuantite() { return txtQuantite; }
        public Button getBtnSupprimer() { return btnSupprimer; }
    }
}