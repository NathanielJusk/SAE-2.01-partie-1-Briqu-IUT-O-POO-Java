package fr.univorleans.iut45.briquiuto.IHM.Vue;

import fr.univorleans.iut45.briquiuto.modele.Boite;
import fr.univorleans.iut45.briquiuto.modele.Piece;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VueRechercheBoiteParPiece extends VBox {

    private Label lblPiece;
    private ComboBox<Piece> cbPiece;
    private Button btnRechercher;

    private TableView<Boite> tableResultats;
    private TableColumn<Boite, String> colNumero;
    private TableColumn<Boite, String> colNom;
    private TableColumn<Boite, Integer> colAnnee;
    private TableColumn<Boite, Integer> colNbPieces;

    private Button btnRetour;
    private Image homeImage;

    public VueRechercheBoiteParPiece() {
        this.setSpacing(15);
        this.setPadding(new Insets(20));
        this.setAlignment(Pos.TOP_CENTER);

        HBox zoneRecherche = new HBox(10);
        zoneRecherche.setAlignment(Pos.CENTER_LEFT);
        zoneRecherche.setPadding(new Insets(10, 0, 10, 0));

        lblPiece = new Label("Pièce :");
        cbPiece = new ComboBox<>();
        cbPiece.setPromptText("Sélectionner une pièce...");
        cbPiece.setPrefWidth(250);

        btnRechercher = new Button("Rechercher");
        btnRechercher.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        zoneRecherche.getChildren().addAll(lblPiece, cbPiece, btnRechercher);

        tableResultats = new TableView<>();

        colNumero = new TableColumn<>("Numéro");
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colNumero.setPrefWidth(100);

        colNom = new TableColumn<>("Nom");
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colNom.setPrefWidth(250);

        colAnnee = new TableColumn<>("Année");
        // --- CORRECTION DU BOUTON RETOUR ---
        colAnnee.setCellValueFactory(new PropertyValueFactory<>("annee"));
        colAnnee.setPrefWidth(100);

        colNbPieces = new TableColumn<>("Nombre de pièces");
        colNbPieces.setCellValueFactory(new PropertyValueFactory<>("nbPiece"));
        colNbPieces.setPrefWidth(120);

        tableResultats.getColumns().addAll(colNumero, colNom, colAnnee, colNbPieces);
        VBox.setVgrow(tableResultats, Priority.ALWAYS);
        this.btnRetour = new Button();
        try {
            this.homeImage = new Image(getClass().getResourceAsStream("/img/logoRetour.png"));
            ImageView homeImageView = new ImageView(this.homeImage);
            homeImageView.setFitWidth(30);
            homeImageView.setFitHeight(30);
            homeImageView.setPreserveRatio(true);
            this.btnRetour.setGraphic(homeImageView);
            this.btnRetour.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        } catch (Exception e) {
            this.btnRetour.setText("Retour");
        }

        HBox zoneBasse = new HBox();
        zoneBasse.setAlignment(Pos.CENTER_LEFT);
        zoneBasse.getChildren().add(btnRetour);

        this.getChildren().addAll(zoneRecherche, tableResultats, zoneBasse);
    }

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

    public void alimenterListePieces(ObservableList<Piece> listePieces) {
        cbPiece.setItems(listePieces);
    }

    public void afficherResultats(ObservableList<Boite> listeBoites) {
        tableResultats.setItems(listeBoites);
    }
}