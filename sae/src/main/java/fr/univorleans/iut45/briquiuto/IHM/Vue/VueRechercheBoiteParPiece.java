package fr.univorleans.iut45.briquiuto.view;

import fr.univorleans.iut45.briquiuto.Boite;
import fr.univorleans.iut45.briquiuto.Piece;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class VueRechercheBoiteParPiece extends VBox {

    // Composants de la zone de recherche
    private Label lblPiece;
    private ComboBox<Piece> cbPiece;
    private Button btnRechercher;

    // Composants du tableau des résultats
    private TableView<Boite> tableResultats;
    private TableColumn<Boite, String> colNumero;
    private TableColumn<Boite, String> colNom;
    private TableColumn<Boite, Integer> colAnnee;
    private TableColumn<Boite, Integer> colNbPieces;

    // Bouton de navigation
    private Button btnRetour;

    public VueRechercheBoiteParPiece() {
        // 1. Configuration du conteneur principal (VBox)
        this.setSpacing(15);
        this.setPadding(new Insets(20));
        this.setAlignment(Pos.TOP_CENTER);

        // 2. Création de la zone de recherche (HBox)
        HBox zoneRecherche = new HBox(10);
        zoneRecherche.setAlignment(Pos.CENTER_LEFT);
        zoneRecherche.setPadding(new Insets(10, 0, 10, 0));

        lblPiece = new Label("Pièce :");
        cbPiece = new ComboBox<>();
        cbPiece.setPromptText("Sélectionner une pièce...");
        cbPiece.setPrefWidth(250);
        
        btnRechercher = new Button("Rechercher");
        btnRechercher.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        // Ajout des éléments dans la HBox de recherche
        zoneRecherche.getChildren().addAll(lblPiece, cbPiece, btnRechercher);

        // 3. Création et configuration de la TableView
        tableResultats = new TableView<>();
        
        // Configuration des colonnes (Liaison avec les attributs de la classe Boite)
        colNumero = new TableColumn<>("Numéro");
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colNumero.setPrefWidth(100);

        colNom = new TableColumn<>("Nom");
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colNom.setPrefWidth(250);

        colAnnee = new TableColumn<>("Année");
        colAnnee.setCellValueFactory(new PropertyValueFactory<>("annee"));
        colAnnee.setPrefWidth(100);

        colNbPieces = new TableColumn<>("nbPieces");
        colNbPieces.setCellValueFactory(new PropertyValueFactory<>("nbPiece"));
        colNbPieces.setPrefWidth(120);

        // Ajout des colonnes au tableau
        tableResultats.getColumns().addAll(colNumero, colNom, colAnnee, colNbPieces);
        
        // Permettre au tableau de s'agrandir pour occuper l'espace central disponible
        VBox.setVgrow(tableResultats, Priority.ALWAYS);

        // 4. Création de la zone basse avec le bouton Retour
        btnRetour = new Button("Retour");
        btnRetour.setPrefWidth(100);
        
        // Conteneur pour aligner le bouton retour à gauche ou au centre selon tes préférences
        HBox zoneBasse = new HBox();
        zoneBasse.setAlignment(Pos.CENTER_LEFT);
        zoneBasse.getChildren().add(btnRetour);

        // 5. Assemblage final dans la VBox racine
        this.getChildren().addAll(zoneRecherche, tableResultats, zoneBasse);
    }

    // ── Getters pour permettre au Contrôleur MVC d'interagir avec la vue ──

    public ComboBox<Piece> getCbPiece() {
        return cbPiece;
    }

    public Button getBtnRechercher() {
        return btnRechercher;
    }

    public TableView<Boite> getTableResultats() {
        return tableResultats;
    }

    public Button getBtnRetour() {
        return btnRetour;
    }

    /**
     * Permet de charger la liste des pièces disponibles dans le menu déroulant
     */
    public void alimenterListePieces(ObservableList<Piece> listePieces) {
        cbPiece.setItems(listePieces);
    }

    /**
     * Permet de mettre à jour les résultats affichés dans le tableau
     */
    public void afficherResultats(ObservableList<Boite> listeBoites) {
        tableResultats.setItems(listeBoites);
    }
}