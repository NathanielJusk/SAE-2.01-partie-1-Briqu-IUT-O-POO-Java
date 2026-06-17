package fr.univorleans.iut45.briquiuto.IHM.Vue;

import fr.univorleans.iut45.briquiuto.modele.Boite;
import fr.univorleans.iut45.briquiuto.modele.ContenirP;
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

/**
 * Vue permettant d'afficher les statistiques et le détail complet d'une boîte (Contenu, Année, Pièces).
 */
public class VueStatistiquesBoite extends VBox {

    // En-tête
    private Button btnHome;
    
    // Zone de recherche
    private TextField txtNumBoite;
    private Button btnRechercher;
    private Label lblErreur;

    // Zone d'affichage des statistiques (Cachée par défaut)
    private VBox zoneStatistiques;
    private Label lblTitreBoite;
    private Label valNumero;
    private Label valAnnee;
    private Label valTheme;
    private Label valTotalPieces;

    // Tableau pour le contenu détaillé
    private TableView<String> tableContenu; // Simplifié en String pour l'affichage, ou à adapter avec tes objets

    public VueStatistiquesBoite() {
        this.setSpacing(20);
        this.setPadding(new Insets(25));
        this.setStyle("-fx-background-color: #FFFFFF;");

        // 1. EN-TÊTE
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        
        this.btnHome = new Button();
        try {
            Image homeImage = new Image(getClass().getResourceAsStream("/img/70083.png"));
            ImageView homeImageView = new ImageView(homeImage);
            homeImageView.setFitWidth(35); homeImageView.setFitHeight(35); homeImageView.setPreserveRatio(true);
            this.btnHome.setGraphic(homeImageView);
            this.btnHome.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        } catch (Exception e) {
            this.btnHome.setText("🏠");
        }

        Label lblTitre = new Label("Statistiques et Détails de la Boîte");
        lblTitre.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        lblTitre.setStyle("-fx-text-fill: #0055BF;"); // Bleu LEGO

        header.getChildren().addAll(btnHome, lblTitre);
        
        Separator separateur = new Separator();
        separateur.setStyle("-fx-background-color: #F6D304; -fx-border-width: 1px;");

        // 2. ZONE DE RECHERCHE
        HBox zoneRecherche = new HBox(10);
        zoneRecherche.setAlignment(Pos.CENTER_LEFT);
        
        Label lblInstruction = new Label("Numéro de la boîte :");
        lblInstruction.setStyle("-fx-font-weight: bold;");
        
        txtNumBoite = new TextField();
        txtNumBoite.setPromptText("Ex: 75192");
        
        btnRechercher = new Button("Analyser");
        btnRechercher.setStyle("-fx-background-color: #287F46; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand;");

        lblErreur = new Label("");
        lblErreur.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        lblErreur.setVisible(false);

        zoneRecherche.getChildren().addAll(lblInstruction, txtNumBoite, btnRechercher, lblErreur);

        // 3. ZONE DES STATISTIQUES (Fiche descriptive)
        zoneStatistiques = new VBox(15);
        zoneStatistiques.setPadding(new Insets(20));
        zoneStatistiques.setStyle("-fx-background-color: #F8F9FA; -fx-border-color: #E0E0E0; -fx-border-radius: 5; -fx-background-radius: 5;");
        zoneStatistiques.setVisible(false); // Caché tant qu'on n'a pas cherché

        lblTitreBoite = new Label("Nom de la Boîte");
        lblTitreBoite.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        
        GridPane gridStats = new GridPane();
        gridStats.setHgap(30);
        gridStats.setVgap(10);
        
        String styleTitre = "-fx-font-weight: bold; -fx-text-fill: #555555;";
        
        Label t1 = new Label("Numéro officiel :"); t1.setStyle(styleTitre);
        valNumero = new Label("-");
        
        Label t2 = new Label("Année de sortie :"); t2.setStyle(styleTitre);
        valAnnee = new Label("-");
        
        Label t3 = new Label("Thème associé :"); t3.setStyle(styleTitre);
        valTheme = new Label("-");
        
        Label t4 = new Label("Pièces totales :"); t4.setStyle(styleTitre);
        valTotalPieces = new Label("-"); valTotalPieces.setStyle("-fx-font-weight: bold; -fx-text-fill: #0055BF;");

        gridStats.add(t1, 0, 0); gridStats.add(valNumero, 1, 0);
        gridStats.add(t2, 0, 1); gridStats.add(valAnnee, 1, 1);
        gridStats.add(t3, 2, 0); gridStats.add(valTheme, 3, 0);
        gridStats.add(t4, 2, 1); gridStats.add(valTotalPieces, 3, 1);

        // 4. TABLEAU DU CONTENU
        Label lblContenu = new Label("Détails du contenu (Pièces) :");
        lblContenu.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        tableContenu = new TableView<>();
        tableContenu.setPlaceholder(new Label("Aucun détail de pièce disponible pour cette boîte."));
        
        // Colonne simple pour afficher le texte généré par la méthode afficherStatistiques
        TableColumn<String, String> colDetail = new TableColumn<>("Éléments de la boîte");
        colDetail.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()));
        colDetail.setPrefWidth(500);
        
        tableContenu.getColumns().add(colDetail);
        VBox.setVgrow(tableContenu, Priority.ALWAYS);

        zoneStatistiques.getChildren().addAll(lblTitreBoite, new Separator(), gridStats, lblContenu, tableContenu);

        // Assemblage
        this.getChildren().addAll(header, separateur, zoneRecherche, zoneStatistiques);
    }

    // --- Méthodes d'affichage pour le Contrôleur ---

    /**
     * Remplit l'interface avec les données de la boîte trouvée.
     */
    public void afficherStatsBoite(Boite boite, int totalPieces, String nomTheme) {
        lblErreur.setVisible(false);
        zoneStatistiques.setVisible(true); // On affiche la carte

        lblTitreBoite.setText(boite.getNom());
        valNumero.setText(boite.getNumero());
        valAnnee.setText(String.valueOf(boite.getAnnee()));
        valTheme.setText(nomTheme != null ? nomTheme : "Aucun");
        valTotalPieces.setText(String.valueOf(totalPieces));
    }

    public void afficherErreur(String msg) {
        zoneStatistiques.setVisible(false);
        lblErreur.setText(msg);
        lblErreur.setVisible(true);
    }

    // --- Getters ---
    public Button getBtnHome() { return btnHome; }
    public Button getBtnRechercher() { return btnRechercher; }
    public TextField getTxtNumBoite() { return txtNumBoite; }
    public TableView<String> getTableContenu() { return tableContenu; }
}