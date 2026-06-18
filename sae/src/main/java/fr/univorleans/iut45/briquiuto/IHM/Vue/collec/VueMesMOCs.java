package fr.univorleans.iut45.briquiuto.IHM.Vue.collec;

import fr.univorleans.iut45.briquiuto.modele.Boite;
import fr.univorleans.iut45.briquiuto.modele.Theme;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class VueMesMOCs extends VBox {

    /**
     * Vue listant les MOCs (boîtes personnalisées) de l'utilisateur.
     * Simple liste + boutons d'édition/suppression. Idéale pour un TP.
     */

    private Button btnHome;
    private Button btnRetour; // <-- Le bouton retour est déclaré ici
    private TextField txtRecherche;
    private TableView<Boite> tableBoites;

    public VueMesMOCs() {
        this.setSpacing(15);
        this.setPadding(new Insets(20));
        this.setStyle("-fx-background-color: #FFFFFF;");

        // --- EN-TÊTE ---
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        
        this.btnHome = new Button();
        try {
            ImageView homeView = new ImageView(new Image(getClass().getResourceAsStream("/img/70083.png")));
            homeView.setFitWidth(30); homeView.setFitHeight(30); homeView.setPreserveRatio(true);
            this.btnHome.setGraphic(homeView);
            this.btnHome.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        } catch (Exception e) { this.btnHome.setText("🏠"); }

        Label lblTitre = new Label("Mes Créations (MOCs)");
        lblTitre.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        lblTitre.setStyle("-fx-text-fill: #0055BF;");
        header.getChildren().addAll(btnHome, lblTitre);

        // --- RECHERCHE ---
        HBox zoneRecherche = new HBox(10);
        zoneRecherche.setAlignment(Pos.CENTER_LEFT);
        Label lblRecherche = new Label("Rechercher (Numéro ou Nom) :");
        lblRecherche.setStyle("-fx-font-weight: bold;");
        txtRecherche = new TextField();
        txtRecherche.setPromptText("Ex: PERSO- ou Nom...");
        txtRecherche.setPrefWidth(250);
        zoneRecherche.getChildren().addAll(lblRecherche, txtRecherche);

        // --- TABLEAU ---
        tableBoites = new TableView<>();
        VBox.setVgrow(tableBoites, Priority.ALWAYS);

        TableColumn<Boite, String> colNum = new TableColumn<>("Numéro");
        colNum.setCellValueFactory(new PropertyValueFactory<>("numero"));
        
        TableColumn<Boite, String> colNom = new TableColumn<>("Nom de la création");
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        
        TableColumn<Boite, Integer> colAnnee = new TableColumn<>("Année");
        colAnnee.setCellValueFactory(new PropertyValueFactory<>("annee"));
        
        TableColumn<Boite, Integer> colPieces = new TableColumn<>("Nb Pièces");
        colPieces.setCellValueFactory(new PropertyValueFactory<>("nbPiece"));

        TableColumn<Boite, String> colTheme = new TableColumn<>("Thème");
        colTheme.setCellValueFactory(cellData -> {
            Theme t = cellData.getValue().getTheme();
            return new SimpleStringProperty(t != null ? t.getNom() : "Aucun");
        });

        tableBoites.getColumns().addAll(colNum, colNom, colAnnee, colPieces, colTheme);
        tableBoites.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // ==========================================
        // --- PIED DE PAGE : BOUTON RETOUR ---
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

        // --- ASSEMBLAGE FINAL ---
        this.getChildren().addAll(header, new Separator(), zoneRecherche, tableBoites, footer);
    }

    // --- GETTERS ---
    public Button getBtnHome() { return btnHome; }
    public Button getBtnRetour() { return btnRetour; } // <-- Getter pour le contrôleur
    public TextField getTxtRecherche() { return txtRecherche; }
    public TableView<Boite> getTableBoites() { return tableBoites; }
}