package fr.univorleans.iut45.briquiuto.IHM.Vue.collec;

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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.StringConverter;

public class VueRechercheBoiteParPiece extends VBox {

    /**
     * Vue de recherche de boîtes contenant une pièce donnée.
     * Propose un champ de recherche et affiche les boîtes correspondantes.
     * Implémentation volontairement simple pour un projet étudiant.
     */

    private Button btnHome;
    private Label lblPiece;
    private ComboBox<Piece> cbPiece;
    private Button btnRechercher;

    private TableView<Boite> tableResultats;
    private TableColumn<Boite, String> colNumero;
    private TableColumn<Boite, String> colNom;
    private TableColumn<Boite, Integer> colAnnee;
    private TableColumn<Boite, Integer> colNbPieces;

    private Button btnRetour;

    public VueRechercheBoiteParPiece() {
        this.setSpacing(15);
        this.setPadding(new Insets(20));
        this.setAlignment(Pos.TOP_CENTER);
        this.setStyle("-fx-background-color: #FFFFFF;");

        // --- EN-TÊTE AVEC LA MAISON ---
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);

        this.btnHome = new Button();
        try {
            ImageView homeImageView = new ImageView(new Image(getClass().getResourceAsStream("/img/70083.png")));
            homeImageView.setFitWidth(35);
            homeImageView.setFitHeight(35);
            homeImageView.setPreserveRatio(true);
            this.btnHome.setGraphic(homeImageView);
            this.btnHome.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        } catch (Exception e) {
            this.btnHome.setText("🏠");
        }

        Label lblTitre = new Label("Recherche de Boîte par Pièce");
        lblTitre.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        lblTitre.setStyle("-fx-text-fill: #0055BF;");
        header.getChildren().addAll(btnHome, lblTitre);

        Separator separateur = new Separator();
        separateur.setStyle("-fx-background-color: #F6D304; -fx-border-width: 2px;");

        // --- ZONE DE RECHERCHE ---
        HBox zoneRecherche = new HBox(10);
        zoneRecherche.setAlignment(Pos.CENTER_LEFT);
        zoneRecherche.setPadding(new Insets(10, 0, 10, 0));

        lblPiece = new Label("Pièce :");
        lblPiece.setStyle("-fx-font-weight: bold;");
        cbPiece = new ComboBox<>();
        cbPiece.setPromptText("Sélectionner une pièce...");
        cbPiece.setPrefWidth(250);

        cbPiece.setConverter(new StringConverter<Piece>() {
            @Override
            public String toString(Piece piece) {
                return (piece != null) ? piece.getNomPiece() : "";
            }

            @Override
            public Piece fromString(String string) {
                return null;
            }
        });

        btnRechercher = new Button("Rechercher");
        btnRechercher.setStyle(
                "-fx-background-color: #287F46; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand;");

        zoneRecherche.getChildren().addAll(lblPiece, cbPiece, btnRechercher);

        // --- TABLEAU ---
        tableResultats = new TableView<>();
        VBox.setVgrow(tableResultats, Priority.ALWAYS); // On définit le grow avant d'ajouter les colonnes

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

        // 1. Colonne Image
        TableColumn<Boite, String> colImage = new TableColumn<>("Aperçu");
        colImage.setCellValueFactory(new PropertyValueFactory<>("imgUrl"));
        colImage.setPrefWidth(160); // Un peu plus large pour laisser respirer l'image

        // 2. Personnalisation de l'affichage
        colImage.setCellFactory(param -> new TableCell<Boite, String>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(String url, boolean empty) {
                super.updateItem(url, empty);
                if (empty || url == null || url.trim().isEmpty() || url.equals("null")) {
                    setGraphic(null);
                } else {
                    // Chargement asynchrone (true)
                    Image image = new Image(url.trim(), 200, 250, true, true);
                    imageView.setImage(image);
                    setGraphic(imageView);
                }
            }
        });

        // 3. Ajout au tableau
        tableResultats.getColumns().addAll(colNumero, colNom, colAnnee, colNbPieces, colImage);

        // 4. IMPORTANT : Désactiver le redimensionnement automatique si tu veux garder
        // tes largeurs fixes
        // Si tu as une colonne vide, c'est souvent car cette ligne est absente ou mal
        // réglée
        tableResultats.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        // --- PIED DE PAGE : RETOUR ---
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
            this.btnRetour.setStyle(
                    "-fx-background-color: #E3000B; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 10 20; -fx-background-radius: 5;");
        }
        footer.getChildren().add(btnRetour);

        this.getChildren().addAll(header, separateur, zoneRecherche, tableResultats, footer);
    }

    public Button getBtnHome() {
        return btnHome;
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