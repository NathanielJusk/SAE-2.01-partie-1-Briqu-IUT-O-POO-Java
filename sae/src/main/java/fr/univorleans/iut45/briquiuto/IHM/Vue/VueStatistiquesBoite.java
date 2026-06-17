package fr.univorleans.iut45.briquiuto.IHM.Vue;

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
    private Button btnRetour; // NOUVEAU BOUTON
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

        // 1. EN-TÊTE
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        
        this.btnHome = new Button();
        try {
            ImageView homeImageView = new ImageView(new Image(getClass().getResourceAsStream("/img/70083.png")));
            homeImageView.setFitWidth(35); homeImageView.setFitHeight(35); homeImageView.setPreserveRatio(true);
            this.btnHome.setGraphic(homeImageView);
            this.btnHome.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        } catch (Exception e) { this.btnHome.setText("🏠"); }

        // --- NOUVEAU BOUTON RETOUR ---
        this.btnRetour = new Button("⬅ Retour");
        this.btnRetour.setStyle("-fx-background-color: #E3000B; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 8 15; -fx-background-radius: 5;");

        Label lblTitre = new Label("Statistiques et Détails de la Boîte");
        lblTitre.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        lblTitre.setStyle("-fx-text-fill: #0055BF;");
        
        header.getChildren().addAll(btnHome, btnRetour, lblTitre);
        
        Separator separateur = new Separator();
        separateur.setStyle("-fx-background-color: #F6D304; -fx-border-width: 1px;");

        // 2. ZONE DE RECHERCHE
        HBox zoneRecherche = new HBox(10);
        zoneRecherche.setAlignment(Pos.CENTER_LEFT);
        Label lblInstruction = new Label("Numéro de la boîte :");
        lblInstruction.setStyle("-fx-font-weight: bold;");
        txtNumBoite = new TextField(); txtNumBoite.setPromptText("Ex: 75192");
        btnRechercher = new Button("Analyser");
        btnRechercher.setStyle("-fx-background-color: #287F46; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand;");

        lblErreur = new Label("");
        lblErreur.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        lblErreur.setVisible(false);

        zoneRecherche.getChildren().addAll(lblInstruction, txtNumBoite, btnRechercher, lblErreur);

        // 3. ZONE DES STATISTIQUES
        zoneStatistiques = new VBox(15);
        zoneStatistiques.setPadding(new Insets(20));
        zoneStatistiques.setStyle("-fx-background-color: #F8F9FA; -fx-border-color: #E0E0E0; -fx-border-radius: 5; -fx-background-radius: 5;");
        zoneStatistiques.setVisible(false); 

        lblTitreBoite = new Label("Nom de la Boîte");
        lblTitreBoite.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        
        HBox boxInfosAvecImage = new HBox(30);
        boxInfosAvecImage.setAlignment(Pos.CENTER_LEFT);

        imageBoiteView = new ImageView();
        imageBoiteView.setFitWidth(180); imageBoiteView.setFitHeight(180); imageBoiteView.setPreserveRatio(true);

        GridPane gridStats = new GridPane();
        gridStats.setHgap(30); gridStats.setVgap(10);
        String styleTitre = "-fx-font-weight: bold; -fx-text-fill: #555555;";
        
        gridStats.add(new Label("Numéro officiel :") {{ setStyle(styleTitre); }}, 0, 0);
        valNumero = new Label("-"); gridStats.add(valNumero, 1, 0);
        
        gridStats.add(new Label("Année de sortie :") {{ setStyle(styleTitre); }}, 0, 1);
        valAnnee = new Label("-"); gridStats.add(valAnnee, 1, 1);
        
        gridStats.add(new Label("Thème associé :") {{ setStyle(styleTitre); }}, 2, 0);
        valTheme = new Label("-"); gridStats.add(valTheme, 3, 0);
        
        gridStats.add(new Label("Pièces totales :") {{ setStyle(styleTitre); }}, 2, 1);
        valTotalPieces = new Label("-"); valTotalPieces.setStyle("-fx-font-weight: bold; -fx-text-fill: #0055BF;");
        gridStats.add(valTotalPieces, 3, 1);

        boxInfosAvecImage.getChildren().addAll(imageBoiteView, gridStats);

        // 4. TABLEAU DU CONTENU
        Label lblContenu = new Label("Détails du contenu :");
        lblContenu.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        tableContenu = new TableView<>();
        tableContenu.setPlaceholder(new Label("Aucun détail disponible."));
        
        TableColumn<String[], String> colNom = new TableColumn<>("Pièce / Figurine");
        colNom.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[0]));
        colNom.setPrefWidth(250);

        TableColumn<String[], String> colCouleur = new TableColumn<>("Couleur");
        colCouleur.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[1]));
        colCouleur.setPrefWidth(120);

        TableColumn<String[], String> colQte = new TableColumn<>("Qté");
        colQte.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[2]));
        colQte.setStyle("-fx-alignment: CENTER;");
        colQte.setPrefWidth(60);

        TableColumn<String[], String> colSupp = new TableColumn<>("Supplément");
        colSupp.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[3]));
        colSupp.setStyle("-fx-alignment: CENTER;");
        colSupp.setPrefWidth(90);

        TableColumn<String[], String> colImage = new TableColumn<>("Aperçu visuel");
        colImage.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[4]));
        colImage.setStyle("-fx-alignment: CENTER;");
        colImage.setPrefWidth(100);
        
        colImage.setCellFactory(col -> new TableCell<String[], String>() {
            private final ImageView imgView = new ImageView();
            @Override
            protected void updateItem(String url, boolean empty) {
                super.updateItem(url, empty);
                if (empty || url == null || url.trim().isEmpty()) {
                    setGraphic(null);
                } else {
                    imgView.setImage(new Image(url, true)); 
                    imgView.setFitWidth(50);
                    imgView.setFitHeight(50);
                    imgView.setPreserveRatio(true);
                    setGraphic(imgView);
                }
            }
        });

        tableContenu.getColumns().addAll(colNom, colCouleur, colQte, colSupp, colImage);
        VBox.setVgrow(tableContenu, Priority.ALWAYS);

        zoneStatistiques.getChildren().addAll(lblTitreBoite, new Separator(), boxInfosAvecImage, lblContenu, tableContenu);
        this.getChildren().addAll(header, separateur, zoneRecherche, zoneStatistiques);
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
        if (url != null && !url.trim().isEmpty()) {
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
    public Button getBtnRetour() { return btnRetour; } // GETTER DU BOUTON RETOUR
    public Button getBtnRechercher() { return btnRechercher; }
    public TextField getTxtNumBoite() { return txtNumBoite; }
    public TableView<String[]> getTableContenu() { return tableContenu; }
}