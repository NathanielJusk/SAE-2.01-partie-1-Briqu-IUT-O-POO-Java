package fr.univorleans.iut45.briquiuto.IHM.Vue.admin;

import fr.univorleans.iut45.briquiuto.modele.Boite;
import fr.univorleans.iut45.briquiuto.modele.Figurine;
import fr.univorleans.iut45.briquiuto.modele.Piece;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;

public class AdminCatalogueVue extends VBox {

    private Button btnHome;
    private Button btnRetour;
    
    // Les tableaux pour chaque catégorie
    private TableView<Boite> tableBoites;
    private TableView<Piece> tablePieces;
    private TableView<Figurine> tableFigurines;

    public AdminCatalogueVue() {
        this.setSpacing(15);
        this.setPadding(new Insets(20));
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
        } catch (Exception e) { this.btnHome.setText("accueil"); }

        Label lblTitre = new Label("Catalogue Global (Mode Administrateur)");
        lblTitre.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        lblTitre.setStyle("-fx-text-fill: #0055BF;");
        header.getChildren().addAll(btnHome, lblTitre);

        // ==========================================
        // 2. LES ONGLETS ET LES TABLEAUX
        // ==========================================
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        VBox.setVgrow(tabPane, Priority.ALWAYS); // Prend toute la place possible

        // --- ONGLET BOÎTES ---
        Tab tabBoites = new Tab("Boîtes LEGO");
        tableBoites = new TableView<>();
        
        TableColumn<Boite, String> colImgB = creerColonneImage("Aperçu");
        TableColumn<Boite, String> colNumB = new TableColumn<>("Numéro"); colNumB.setCellValueFactory(new PropertyValueFactory<>("numero"));
        TableColumn<Boite, String> colNomB = new TableColumn<>("Nom de la Boîte"); colNomB.setCellValueFactory(new PropertyValueFactory<>("nom")); colNomB.setPrefWidth(250);
        TableColumn<Boite, Integer> colAnneeB = new TableColumn<>("Année"); colAnneeB.setCellValueFactory(new PropertyValueFactory<>("annee"));
        TableColumn<Boite, Integer> colPiecesB = new TableColumn<>("Nb Pièces"); colPiecesB.setCellValueFactory(new PropertyValueFactory<>("nbPiece"));
        
        tableBoites.getColumns().addAll(colImgB, colNumB, colNomB, colAnneeB, colPiecesB);
        tabBoites.setContent(tableBoites);

        // --- ONGLET PIÈCES ---
        Tab tabPieces = new Tab("Pièces / Briques");
        tablePieces = new TableView<>();
        
        TableColumn<Piece, String> colImgP = creerColonneImage("Aperçu");
        TableColumn<Piece, String> colNumP = new TableColumn<>("ID Pièce"); colNumP.setCellValueFactory(new PropertyValueFactory<>("numPiece"));
        TableColumn<Piece, String> colNomP = new TableColumn<>("Nom de la Pièce"); colNomP.setCellValueFactory(new PropertyValueFactory<>("nomPiece")); colNomP.setPrefWidth(300);
        
        tablePieces.getColumns().addAll(colImgP, colNumP, colNomP);
        tabPieces.setContent(tablePieces);

        // --- ONGLET FIGURINES ---
        Tab tabFigurines = new Tab("Figurines");
        tableFigurines = new TableView<>();
        
        TableColumn<Figurine, String> colImgF = creerColonneImage("Aperçu");
        TableColumn<Figurine, String> colNumF = new TableColumn<>("ID Figurine"); colNumF.setCellValueFactory(new PropertyValueFactory<>("idFig"));
        TableColumn<Figurine, String> colNomF = new TableColumn<>("Nom de la Figurine"); colNomF.setCellValueFactory(new PropertyValueFactory<>("nomFig")); colNomF.setPrefWidth(300);
        TableColumn<Figurine, Integer> colPartiesF = new TableColumn<>("Nb Parties"); colPartiesF.setCellValueFactory(new PropertyValueFactory<>("nbParties"));
        
        tableFigurines.getColumns().addAll(colImgF, colNumF, colNomF, colPartiesF);
        tabFigurines.setContent(tableFigurines);

        // On ajoute les onglets au conteneur
        tabPane.getTabs().addAll(tabBoites, tabPieces, tabFigurines);

        // ==========================================
        // 3. LE RESSORT MAGIQUE ET LE FOOTER
        // ==========================================
        Region ressort = new Region();
        VBox.setVgrow(ressort, Priority.ALWAYS);

        HBox footer = new HBox();
        footer.setAlignment(Pos.BOTTOM_LEFT); 
        this.btnRetour = new Button();
        try {
            ImageView retourView = new ImageView(new Image(getClass().getResourceAsStream("/img/logoRetour.png")));
            retourView.setFitWidth(90); retourView.setFitHeight(90); retourView.setPreserveRatio(true);
            this.btnRetour.setGraphic(retourView);
            this.btnRetour.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        } catch (Exception e) { 
            this.btnRetour.setText("⬅ Retour"); 
            this.btnRetour.setStyle("-fx-background-color: #E3000B; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 10 20; -fx-background-radius: 5;");
        }
        footer.getChildren().add(btnRetour);

        // Assemblage Final
        this.getChildren().addAll(header, new Separator(), tabPane, ressort, footer);
    }

    // ── MÉTHODE UNIVERSELLE POUR CRÉER UNE COLONNE IMAGE ──
    private <T> TableColumn<T, String> creerColonneImage(String titre) {
        TableColumn<T, String> colImage = new TableColumn<>(titre);
        
        // On récupère dynamiquement le champ imgUrl peu importe l'objet
        colImage.setCellValueFactory(data -> {
            Object objet = data.getValue();
            String url = null;
            if (objet instanceof Boite) url = ((Boite) objet).getImgUrl();
            else if (objet instanceof Piece) url = ((Piece) objet).getImgUrl();
            else if (objet instanceof Figurine) url = ((Figurine) objet).getImgUrl();
            return new SimpleStringProperty(url);
        });
        
        colImage.setStyle("-fx-alignment: CENTER;");
        colImage.setPrefWidth(120);

        colImage.setCellFactory(new Callback<TableColumn<T, String>, TableCell<T, String>>() {
            @Override
            public TableCell<T, String> call(TableColumn<T, String> param) {
                return new TableCell<T, String>() {
                    private final ImageView imgView = new ImageView();
                    @Override
                    protected void updateItem(String url, boolean empty) {
                        super.updateItem(url, empty);
                        if (empty || url == null || url.trim().isEmpty() || url.equals("null")) {
                            setGraphic(null);
                            setText("Pas d'image");
                        } else {
                            try {
                                Image image = new Image(url.trim(), true);
                                imgView.setImage(image); 
                                imgView.setFitWidth(60); imgView.setFitHeight(60); imgView.setPreserveRatio(true);
                                setGraphic(imgView);
                                setText(null);
                                image.errorProperty().addListener((obs, oldV, newV) -> {
                                    if (newV) { setGraphic(null); setText("Erreur URL"); }
                                });
                            } catch (Exception e) {
                                setGraphic(null);
                                setText("Erreur");
                            }
                        }
                    }
                };
            }
        });
        return colImage;
    }

    // ── GETTERS POUR LE CONTRÔLEUR ──
    public Button getBtnHome() { return btnHome; }
    public Button getBtnRetour() { return btnRetour; }
    public TableView<Boite> getTableBoites() { return tableBoites; }
    public TableView<Piece> getTablePieces() { return tablePieces; }
    public TableView<Figurine> getTableFigurines() { return tableFigurines; }
}