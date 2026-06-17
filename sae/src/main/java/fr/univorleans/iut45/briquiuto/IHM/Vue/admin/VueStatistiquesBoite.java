package fr.univorleans.iut45.briquiuto.IHM.Vue.admin;

import fr.univorleans.iut45.briquiuto.modele.Boite;
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

public class VueStatistiquesBoite extends VBox {

    private Button btnHome;
    private Button btnRetour;
    private TextField txtNumBoite;
    private Button btnRechercher;
    private Label lblErreur;

    private VBox zoneStatistiques;
    private Label lblTitreBoite;
    private ImageView imageBoiteView;
    private Label valNumero;
    private Label valAnnee;
    private Label valTheme;
    private Label valTotalPieces;

    private TableView<String[]> tableContenu;

    public VueStatistiquesBoite() {
        this.setSpacing(20);
        this.setPadding(new Insets(25));
        this.setStyle("-fx-background-color: #FFFFFF;");

        // ==========================================
        // 1. EN-TÊTE
        // ==========================================
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        
        this.btnHome = new Button();
        try {
            ImageView homeImageView = new ImageView(new Image(getClass().getResourceAsStream("/img/70083.png")));
            homeImageView.setFitWidth(35); homeImageView.setFitHeight(35); homeImageView.setPreserveRatio(true);
            this.btnHome.setGraphic(homeImageView);
            this.btnHome.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        } catch (Exception e) { this.btnHome.setText("🏠"); }

        Label lblTitre = new Label("Statistiques et Détails de la Boîte");
        lblTitre.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        lblTitre.setStyle("-fx-text-fill: #0055BF;"); 
        
        header.getChildren().addAll(btnHome, lblTitre); 
        
        Separator separateur = new Separator();
        separateur.setStyle("-fx-background-color: #F6D304; -fx-border-width: 2px;");

        // ==========================================
        // 2. ZONE DE RECHERCHE
        // ==========================================
        HBox zoneRecherche = new HBox(15);
        zoneRecherche.setAlignment(Pos.CENTER_LEFT);
        
        Label lblInstruction = new Label("Numéro de la boîte :");
        lblInstruction.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        
        txtNumBoite = new TextField(); 
        txtNumBoite.setPromptText("Ex: 75192");
        txtNumBoite.setStyle("-fx-border-color: #0055BF; -fx-border-width: 2px; -fx-border-radius: 4px; -fx-padding: 5px; -fx-font-size: 14px;");
        
        btnRechercher = new Button("Analyser");
        btnRechercher.setStyle("-fx-background-color: #287F46; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-font-size: 14px; -fx-padding: 8 20; -fx-background-radius: 5px;");

        lblErreur = new Label("");
        lblErreur.setStyle("-fx-text-fill: #E3000B; -fx-font-weight: bold; -fx-font-size: 14px;");
        lblErreur.setVisible(false);

        zoneRecherche.getChildren().addAll(lblInstruction, txtNumBoite, btnRechercher, lblErreur);

        // ==========================================
        // 3. ZONE DES STATISTIQUES
        // ==========================================
        zoneStatistiques = new VBox(15);
        zoneStatistiques.setPadding(new Insets(20));
        zoneStatistiques.setStyle("-fx-background-color: #F8F9FA; -fx-border-color: #E0E0E0; -fx-border-radius: 8; -fx-background-radius: 8;");
        zoneStatistiques.setVisible(false); 
        VBox.setVgrow(zoneStatistiques, Priority.ALWAYS); 

        lblTitreBoite = new Label("Nom de la Boîte");
        lblTitreBoite.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        
        HBox boxInfosAvecImage = new HBox(30);
        boxInfosAvecImage.setAlignment(Pos.CENTER_LEFT);

        imageBoiteView = new ImageView();
        imageBoiteView.setFitWidth(180); imageBoiteView.setFitHeight(180); imageBoiteView.setPreserveRatio(true);

        GridPane gridStats = new GridPane();
        gridStats.setHgap(30); gridStats.setVgap(12);
        String styleTitre = "-fx-font-weight: bold; -fx-text-fill: #555555; -fx-font-size: 14px;";
        String styleValeur = "-fx-font-size: 14px;";
        
        gridStats.add(new Label("Numéro officiel :") {{ setStyle(styleTitre); }}, 0, 0);
        valNumero = new Label("-"); valNumero.setStyle(styleValeur); gridStats.add(valNumero, 1, 0);
        
        gridStats.add(new Label("Année de sortie :") {{ setStyle(styleTitre); }}, 0, 1);
        valAnnee = new Label("-"); valAnnee.setStyle(styleValeur); gridStats.add(valAnnee, 1, 1);
        
        gridStats.add(new Label("Thème associé :") {{ setStyle(styleTitre); }}, 2, 0);
        valTheme = new Label("-"); valTheme.setStyle(styleValeur); gridStats.add(valTheme, 3, 0);
        
        gridStats.add(new Label("Pièces totales :") {{ setStyle(styleTitre); }}, 2, 1);
        valTotalPieces = new Label("-"); 
        valTotalPieces.setStyle("-fx-font-weight: bold; -fx-text-fill: #0055BF; -fx-font-size: 16px;");
        gridStats.add(valTotalPieces, 3, 1);

        boxInfosAvecImage.getChildren().addAll(imageBoiteView, gridStats);

        // ==========================================
        // 4. TABLEAU DU CONTENU
        // ==========================================
        Label lblContenu = new Label("Détails du contenu (Pièces et Figurines) :");
        lblContenu.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        lblContenu.setStyle("-fx-text-fill: #0055BF;");
        
        tableContenu = new TableView<>();
        tableContenu.setPlaceholder(new Label("Aucun détail disponible pour cette boîte."));
        
        TableColumn<String[], String> colNom = new TableColumn<>("Pièce / Figurine");
        colNom.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[0]));
        colNom.setPrefWidth(280);

        TableColumn<String[], String> colCouleur = new TableColumn<>("Couleur");
        colCouleur.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[1]));
        colCouleur.setPrefWidth(120);

        TableColumn<String[], String> colQte = new TableColumn<>("Qté");
        colQte.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[2]));
        colQte.setStyle("-fx-alignment: CENTER;");
        colQte.setPrefWidth(70);

        TableColumn<String[], String> colSupp = new TableColumn<>("Supplément");
        colSupp.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[3]));
        colSupp.setStyle("-fx-alignment: CENTER;");
        colSupp.setPrefWidth(100);

        TableColumn<String[], String> colImage = new TableColumn<>("Aperçu visuel");
        colImage.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[4]));
        colImage.setStyle("-fx-alignment: CENTER;");
        colImage.setPrefWidth(120);
        
        // --- LA MAGIE POUR CACHER L'URL ET AFFICHER L'IMAGE ---
        colImage.setCellFactory(col -> new TableCell<String[], String>() {
            private final ImageView imgView = new ImageView();
            
            @Override
            protected void updateItem(String url, boolean empty) {
                super.updateItem(url, empty);
                
                // Si la ligne est vide ou qu'il n'y a pas d'URL (ou que c'est le mot "null")
                if (empty || url == null || url.trim().isEmpty() || url.equals("null")) {
                    setGraphic(null);
                    setText(null); 
                } else {
                    try {
                        imgView.setImage(new Image(url, true)); 
                        imgView.setFitWidth(50);
                        imgView.setFitHeight(50);
                        imgView.setPreserveRatio(true);
                        
                        setGraphic(imgView);
                        setText(null); // <-- C'EST ÇA QUI CACHE LE TEXTE POUR DE BON !
                    } catch (Exception e) {
                        setGraphic(null);
                        setText("Indisponible");
                    }
                }
            }
        });

        tableContenu.getColumns().addAll(colNom, colCouleur, colQte, colSupp, colImage);
        VBox.setVgrow(tableContenu, Priority.ALWAYS);

        zoneStatistiques.getChildren().addAll(lblTitreBoite, new Separator(), boxInfosAvecImage, lblContenu, tableContenu);

        // ==========================================
        // 5. PIED DE PAGE (Footer) - Bouton Retour
        // ==========================================
        HBox footer = new HBox();
        footer.setAlignment(Pos.BOTTOM_LEFT); 
        
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
        
        footer.getChildren().add(btnRetour);
        this.getChildren().addAll(header, separateur, zoneRecherche, zoneStatistiques, footer);
    }

    public void afficherStatsBoite(Boite boite, int totalPieces, String nomTheme) {
        lblErreur.setVisible(false);
        zoneStatistiques.setVisible(true);
        lblTitreBoite.setText(boite.getNom());
        valNumero.setText(boite.getNumero());
        valAnnee.setText(String.valueOf(boite.getAnnee()));
        valTheme.setText(nomTheme != null ? nomTheme : "Aucun");
        valTotalPieces.setText(String.valueOf(totalPieces));
    }

    public void afficherImageBoite(String url) {
        if (url != null && !url.trim().isEmpty() && !url.equals("null")) {
            imageBoiteView.setImage(new Image(url, true));
        } else {
            imageBoiteView.setImage(null); 
        }
    }

    public void afficherErreur(String msg) {
        zoneStatistiques.setVisible(false);
        lblErreur.setText(msg);
        lblErreur.setVisible(true);
    }

    public Button getBtnHome() { return btnHome; }
    public Button getBtnRetour() { return btnRetour; }
    public Button getBtnRechercher() { return btnRechercher; }
    public TextField getTxtNumBoite() { return txtNumBoite; }
    public TableView<String[]> getTableContenu() { return tableContenu; }
}