package fr.univorleans.iut45.briquiuto.IHM.Vue.admin;

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

public class VueAjoutBoiteCatalogueAdmin extends VBox {

    // En-tête et Navigation
    private Button btnHome;
    private Button btnRetour; // LE FAMEUX BOUTON RETOUR
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

    public VueAjoutBoiteCatalogueAdmin() {
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
            this.btnHome.setText("🏠");
        }

        lblTitre = new Label("Ajouter une nouvelle boîte au catalogue");
        lblTitre.setFont(Font.font("Arial", FontWeight.BOLD, 24)); // Agrandi pour coller au design
        lblTitre.setStyle("-fx-text-fill: #0055BF;"); // Bleu LEGO officiel

        header.getChildren().addAll(btnHome, lblTitre);

        Separator separateur = new Separator();
        separateur.setStyle("-fx-background-color: #F6D304; -fx-border-width: 2px;"); // Jaune LEGO

        // 3. Formulaire d'informations de base (GridPane)
        GridPane gridForm = new GridPane();
        gridForm.setHgap(15);
        gridForm.setVgap(12);
        gridForm.setPadding(new Insets(10, 0, 10, 0));

        String styleLabel = "-fx-font-weight: bold; -fx-font-size: 14px;";
        String styleInput = "-fx-border-color: #0055BF; -fx-border-width: 2px; -fx-border-radius: 4px; -fx-padding: 5px;";
        
        Label lblNum = new Label("Numéro officiel :"); lblNum.setStyle(styleLabel);
        txtNumero = new TextField(); txtNumero.setPromptText("Ex: 75192"); txtNumero.setStyle(styleInput);

        Label lblNomBoite = new Label("Nom du set :"); lblNomBoite.setStyle(styleLabel);
        txtNom = new TextField(); txtNom.setPromptText("Ex: Millennium Falcon"); txtNom.setStyle(styleInput);

        Label lblAnneeBoite = new Label("Année de sortie :"); lblAnneeBoite.setStyle(styleLabel);
        txtAnnee = new TextField(); txtAnnee.setPromptText("Ex: 2024"); txtAnnee.setStyle(styleInput);

        Label lblThemeBoite = new Label("Thème associé :"); lblThemeBoite.setStyle(styleLabel);
        cbTheme = new ComboBox<>();
        cbTheme.setPromptText("Choisir un thème...");
        cbTheme.setPrefWidth(200);
        cbTheme.setStyle(styleInput);

        gridForm.add(lblNum, 0, 0);        gridForm.add(txtNumero, 1, 0);
        gridForm.add(lblNomBoite, 0, 1);   gridForm.add(txtNom, 1, 1);
        gridForm.add(lblAnneeBoite, 0, 2); gridForm.add(txtAnnee, 1, 2);
        gridForm.add(lblThemeBoite, 0, 3); gridForm.add(cbTheme, 1, 3);

        // 4. Section Contenu / Gestion des pièces
        VBox zoneContenu = new VBox(10);
        Label lblContenu = new Label("Contenu (Sélection des pièces) :");
        lblContenu.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        lblContenu.setStyle("-fx-text-fill: #0055BF;");
        
        conteneurLignesPieces = new VBox(8);
        
        ScrollPane scrollContenu = new ScrollPane(conteneurLignesPieces);
        scrollContenu.setFitToWidth(true);
        scrollContenu.setPrefHeight(180);
        scrollContenu.setStyle("-fx-background-color: transparent; -fx-border-color: #E0E0E0; -fx-border-radius: 5;");

        btnAjouterLigne = new Button("+ Ajouter une pièce");
        btnAjouterLigne.setStyle("-fx-background-color: #0055BF; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand;");
        btnAjouterLigne.setOnAction(e -> ajouterNouvelleLignePiece());

        zoneContenu.getChildren().addAll(lblContenu, scrollContenu, btnAjouterLigne);
        VBox.setVgrow(zoneContenu, Priority.ALWAYS);

        // 5. Zone basse (Bouton de validation + Zone de message)
        btnValider = new Button("Enregistrer au Catalogue Global");
        btnValider.setStyle("-fx-background-color: #287F46; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10 25; -fx-background-radius: 5; -fx-cursor: hand;");

        lblMessage = new Label("");
        lblMessage.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
        lblMessage.setVisible(false);

        VBox zoneValidation = new VBox(10);
        zoneValidation.setAlignment(Pos.CENTER);
        zoneValidation.getChildren().addAll(btnValider, lblMessage);

        // ========================================================
        // 6. PIED DE PAGE : LE BOUTON RETOUR (En bas à gauche)
        // ========================================================
        HBox footerNav = new HBox();
        footerNav.setAlignment(Pos.BOTTOM_LEFT); 
        
        this.btnRetour = new Button();
        try {
            ImageView retourImageView = new ImageView(new Image(getClass().getResourceAsStream("/img/logoRetour.png")));
            retourImageView.setFitWidth(50);
            retourImageView.setFitHeight(50); 
            retourImageView.setPreserveRatio(true);
            this.btnRetour.setGraphic(retourImageView);
            this.btnRetour.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        } catch (Exception e) { 
            this.btnRetour.setText("⬅ Retour"); 
            this.btnRetour.setStyle("-fx-background-color: #E3000B; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 10 20; -fx-background-radius: 5;");
        }
        footerNav.getChildren().add(btnRetour);

        // Assemblage final dans la VBox Racine
        this.getChildren().addAll(header, separateur, gridForm, zoneContenu, zoneValidation, footerNav);
        
        // Ajouter une première ligne par défaut pour guider l'utilisateur
        ajouterNouvelleLignePiece();
    }

    private void ajouterNouvelleLignePiece() {
        LignePieceContenu nouvelleLigne = new LignePieceContenu(cataloguePiecesDisponibles);
        listeLignesChoisies.add(nouvelleLigne);
        conteneurLignesPieces.getChildren().add(nouvelleLigne);
        
        nouvelleLigne.getBtnSupprimer().setOnAction(e -> {
            if (listeLignesChoisies.size() > 1) { 
                conteneurLignesPieces.getChildren().remove(nouvelleLigne);
                listeLignesChoisies.remove(nouvelleLigne);
            }
        });
    }

    public void setPiecesDisponibles(List<Piece> pieces) {
        this.cataloguePiecesDisponibles.setAll(pieces);
    }

    public void setThemesDisponibles(List<Theme> themes) {
        this.cbTheme.setItems(FXCollections.observableArrayList(themes));
    }

    public void reinitialiserFormulaire() {
        txtNumero.clear();
        txtNom.clear();
        txtAnnee.clear();
        cbTheme.getSelectionModel().clearSelection();
        conteneurLignesPieces.getChildren().clear();
        listeLignesChoisies.clear();
        ajouterNouvelleLignePiece(); 
    }

    public void afficherMessage(String msg, String codeCouleurHex) {
        lblMessage.setText(msg);
        lblMessage.setStyle("-fx-text-fill: " + codeCouleurHex + ";");
        lblMessage.setVisible(true);
    }

    // ── NOUVEAU GETTER POUR LE BOUTON RETOUR ──
    public Button getBtnHome() { return btnHome; }
    public Button getBtnRetour() { return btnRetour; }
    
    public Button getBtnValider() { return btnValider; }
    public TextField getTxtNumero() { return txtNumero; }
    public TextField getTxtNom() { return txtNom; }
    public TextField getTxtAnnee() { return txtAnnee; }
    public ComboBox<Theme> getCbTheme() { return cbTheme; }
    public List<LignePieceContenu> getListeLignesChoisies() { return listeLignesChoisies; }

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

            btnSupprimer = new Button("✖"); // Plus propre qu'un "No"
            btnSupprimer.setStyle("-fx-background-color: transparent; -fx-text-fill: #E3000B; -fx-font-weight: bold; -fx-cursor: hand;");

            this.getChildren().addAll(lblP, cbPiece, lblQ, txtQuantite, btnSupprimer);
        }

        public ComboBox<Piece> getCbPiece() { return cbPiece; }
        public TextField getTxtQuantite() { return txtQuantite; }
        public Button getBtnSupprimer() { return btnSupprimer; }
    }
}