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

