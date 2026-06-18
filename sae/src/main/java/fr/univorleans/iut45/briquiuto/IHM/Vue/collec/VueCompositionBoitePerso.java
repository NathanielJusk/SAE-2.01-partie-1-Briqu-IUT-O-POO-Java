package fr.univorleans.iut45.briquiuto.IHM.Vue.collec;

import fr.univorleans.iut45.briquiuto.modele.Couleur;
import fr.univorleans.iut45.briquiuto.modele.Figurine;
import fr.univorleans.iut45.briquiuto.modele.Piece;
import fr.univorleans.iut45.briquiuto.modele.Theme;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class VueCompositionBoitePerso extends VBox {

    private Button btnHome;
    private Button btnRetour;
    
    private TextField txtNumero;
    private TextField txtNom;
    private TextField txtAnnee;
    private ComboBox<Theme> cbTheme;

    private ComboBox<Piece> cbPieces;
    private ComboBox<Couleur> cbCouleurs;
    private TextField txtQuantitePiece;
    private Button btnAjouterPiece;

    private ComboBox<Figurine> cbFigurines;
    private TextField txtQuantiteFigurine;
    private Button btnAjouterFigurine;

    private ListView<String> listeContenuTemporaire;
    private Label lblTotalPieces;

    private Button btnValiderBoite;
    private Label lblMessage;

    public VueCompositionBoitePerso() {
        // 1. Réduction de l'espacement global pour tasser l'interface
        this.setSpacing(10);
        this.setPadding(new Insets(15, 20, 15, 20));
        this.setStyle("-fx-background-color: #FFFFFF;");

        // --- STYLES LEGO ---
        String styleBoutonBleu = "-fx-background-color: #0055A4; -fx-border-color: #003D7A; -fx-border-width: 0 0 4 0; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 13px; -fx-padding: 8 15; -fx-background-radius: 5; -fx-border-radius: 5; -fx-cursor: hand;";
        String styleBoutonVert = "-fx-background-color: #00852B; -fx-border-color: #005C1E; -fx-border-width: 0 0 4 0; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px; -fx-padding: 10 20; -fx-background-radius: 8; -fx-border-radius: 8; -fx-cursor: hand;";

        // 1. EN-TÊTE
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        
        this.btnHome = new Button();
        try {
            ImageView homeView = new ImageView(new Image(getClass().getResourceAsStream("/img/70083.png")));
            homeView.setFitWidth(30); homeView.setFitHeight(30); homeView.setPreserveRatio(true);
            this.btnHome.setGraphic(homeView);
            this.btnHome.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        } catch (Exception e) { this.btnHome.setText("accueil"); }

        Label lblTitre = new Label("Composer ma Boîte Personnalisée (MOC)");
        lblTitre.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        lblTitre.setStyle("-fx-text-fill: #0055BF;");
        header.getChildren().addAll(btnHome, lblTitre);

        // 2. INFORMATIONS GÉNÉRALES
        VBox boxInfos = new VBox(10);
        boxInfos.setStyle("-fx-padding: 15; -fx-border-color: #E0E0E0; -fx-border-radius: 5;");
        Label lblInfosTitre = new Label("1. Informations générales");
        lblInfosTitre.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
        
        GridPane gridInfos = new GridPane();
        gridInfos.setHgap(15); gridInfos.setVgap(10);
        
        txtNumero = new TextField(); txtNumero.setPromptText("Ex: PERSO-001");
        txtNom = new TextField(); txtNom.setPromptText("Nom de la création");
        txtAnnee = new TextField(); txtAnnee.setPromptText("Année");
        cbTheme = new ComboBox<>(); cbTheme.setPromptText("Choisir un thème...");
        
        gridInfos.add(new Label("Numéro :"), 0, 0); gridInfos.add(txtNumero, 1, 0);
        gridInfos.add(new Label("Nom :"), 2, 0); gridInfos.add(txtNom, 3, 0);
        gridInfos.add(new Label("Année :"), 0, 1); gridInfos.add(txtAnnee, 1, 1);
        gridInfos.add(new Label("Thème :"), 2, 1); gridInfos.add(cbTheme, 3, 1);
        
        boxInfos.getChildren().addAll(lblInfosTitre, gridInfos);

        // 3. ZONE DE COMPOSITION
        HBox boxComposition = new HBox(20);
        // Suppression du Priority.ALWAYS ici pour éviter de pousser l'écran vers le bas !

        VBox boxAjouts = new VBox(20);
        boxAjouts.setPrefWidth(350);

        // -> Bloc Pièces
        VBox blockPieces = new VBox(10);
        blockPieces.setStyle("-fx-padding: 15; -fx-background-color: #F8F9FA; -fx-border-color: #CCCCCC; -fx-border-radius: 5;");
        Label lblPiecesTitre = new Label("2. Ajouter des pièces");
        lblPiecesTitre.setStyle("-fx-font-weight: bold;");
        
        cbPieces = new ComboBox<>(); cbPieces.setPromptText("Sélectionner la pièce..."); cbPieces.setMaxWidth(Double.MAX_VALUE);
        cbCouleurs = new ComboBox<>(); cbCouleurs.setPromptText("Couleur..."); cbCouleurs.setMaxWidth(Double.MAX_VALUE);
        
        HBox hbQtePiece = new HBox(10);
        hbQtePiece.setAlignment(Pos.CENTER_LEFT);
        txtQuantitePiece = new TextField(); txtQuantitePiece.setPromptText("Qté"); txtQuantitePiece.setPrefWidth(60);
        
        btnAjouterPiece = new Button("Ajouter au panier");
        btnAjouterPiece.setStyle(styleBoutonBleu); 
        
        hbQtePiece.getChildren().addAll(new Label("Quantité :"), txtQuantitePiece, btnAjouterPiece);
        blockPieces.getChildren().addAll(lblPiecesTitre, cbPieces, cbCouleurs, hbQtePiece);

        // -> Bloc Figurines
        VBox blockFigurines = new VBox(10);
        blockFigurines.setStyle("-fx-padding: 15; -fx-background-color: #F8F9FA; -fx-border-color: #CCCCCC; -fx-border-radius: 5;");
        Label lblFigTitre = new Label("3. Ajouter des figurines");
        lblFigTitre.setStyle("-fx-font-weight: bold;");
        
        cbFigurines = new ComboBox<>(); cbFigurines.setPromptText("Sélectionner la figurine..."); cbFigurines.setMaxWidth(Double.MAX_VALUE);
        
        HBox hbQteFig = new HBox(10);
        hbQteFig.setAlignment(Pos.CENTER_LEFT);
        txtQuantiteFigurine = new TextField(); txtQuantiteFigurine.setPromptText("Qté"); txtQuantiteFigurine.setPrefWidth(60);
        
        btnAjouterFigurine = new Button("Ajouter au panier");
        btnAjouterFigurine.setStyle(styleBoutonBleu); 
        
        hbQteFig.getChildren().addAll(new Label("Quantité :"), txtQuantiteFigurine, btnAjouterFigurine);
        blockFigurines.getChildren().addAll(lblFigTitre, cbFigurines, hbQteFig);
        
        boxAjouts.getChildren().addAll(blockPieces, blockFigurines);

        // -> Le Panier
        VBox boxPanier = new VBox(10);
        HBox.setHgrow(boxPanier, Priority.ALWAYS);
        Label lblPanierTitre = new Label("Contenu de la boîte");
        lblPanierTitre.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        
        listeContenuTemporaire = new ListView<>();
        VBox.setVgrow(listeContenuTemporaire, Priority.ALWAYS);
        // On limite la hauteur du panier à 120 pour faire de la place au bouton retour
        listeContenuTemporaire.setPrefHeight(120);
        
        lblTotalPieces = new Label("Total : 0 élément(s)");
        lblTotalPieces.setStyle("-fx-font-weight: bold; -fx-text-fill: #555555;");

        boxPanier.getChildren().addAll(lblPanierTitre, listeContenuTemporaire, lblTotalPieces);
        boxComposition.getChildren().addAll(boxAjouts, boxPanier);

        // 4. ZONE DE VALIDATION
        // Réduction des marges ici aussi
        VBox boxValidation = new VBox(5);
        boxValidation.setAlignment(Pos.CENTER);
        boxValidation.setPadding(new Insets(5, 0, 0, 0));
        
        btnValiderBoite = new Button("Créer la boîte personnalisée !");
        btnValiderBoite.setStyle(styleBoutonVert); 
        
        lblMessage = new Label("");
        lblMessage.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        boxValidation.getChildren().addAll(btnValiderBoite, lblMessage);

        // --- RESSORT MAGIQUE ---
        // Le ressort pousse la HBox footer tout en bas
        Region ressort = new Region();
        VBox.setVgrow(ressort, Priority.ALWAYS);

        // --- 5. PIED DE PAGE : RETOUR ---
        HBox footer = new HBox();
        footer.setAlignment(Pos.BOTTOM_LEFT); 
        VBox.setMargin(footer, new Insets(5, 0, 0, 0)); 
        this.btnRetour = new Button();
        try {
            ImageView retourImageView = new ImageView(new Image(getClass().getResourceAsStream("/img/logoRetour.png")));
            // Le bouton est à 40x40, la taille idéale
            retourImageView.setFitWidth(90); 
            retourImageView.setFitHeight(90); 
            retourImageView.setPreserveRatio(true);            
            this.btnRetour.setGraphic(retourImageView);
            this.btnRetour.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        } catch (Exception e) { 
            this.btnRetour.setText("⬅ Retour"); 
            this.btnRetour.setStyle("-fx-background-color: #E3000B; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 10 20; -fx-background-radius: 5;");
        }
        footer.getChildren().add(btnRetour);

        // Assemblage final avec le ressort ajouté avant le footer
        this.getChildren().addAll(header, new Separator(), boxInfos, boxComposition, boxValidation, ressort, footer);
    }

    public Button getBtnHome() { return btnHome; }
    public Button getBtnRetour() { return btnRetour; }
    
    public TextField getTxtNumero() { return txtNumero; }
    public TextField getTxtNom() { return txtNom; }
    public TextField getTxtAnnee() { return txtAnnee; }
    public ComboBox<Theme> getCbTheme() { return cbTheme; }
    
    public ComboBox<Piece> getCbPieces() { return cbPieces; }
    public ComboBox<Couleur> getCbCouleurs() { return cbCouleurs; }
    public TextField getTxtQuantitePiece() { return txtQuantitePiece; }
    public Button getBtnAjouterPiece() { return btnAjouterPiece; }
    
    public ComboBox<Figurine> getCbFigurines() { return cbFigurines; }
    public TextField getTxtQuantiteFigurine() { return txtQuantiteFigurine; }
    public Button getBtnAjouterFigurine() { return btnAjouterFigurine; }
    
    public ListView<String> getListeContenuTemporaire() { return listeContenuTemporaire; }
    public Button getBtnValiderBoite() { return btnValiderBoite; }

    public void afficherMessage(String msg, Color couleur) {
        lblMessage.setTextFill(couleur);
        lblMessage.setText(msg);
    }

    public void majTotal(int total) {
        lblTotalPieces.setText("Total : " + total + " élément(s)");
    }
}