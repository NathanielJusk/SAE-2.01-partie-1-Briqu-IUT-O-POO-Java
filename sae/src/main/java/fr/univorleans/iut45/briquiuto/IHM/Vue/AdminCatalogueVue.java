package fr.univorleans.iut45.briquiuto.IHM.Vue;

import fr.univorleans.iut45.briquiuto.modele.Boite;
import fr.univorleans.iut45.briquiuto.modele.Piece;
import fr.univorleans.iut45.briquiuto.modele.Theme;
import fr.univorleans.iut45.briquiuto.modele.Categorie;
import fr.univorleans.iut45.briquiuto.modele.Figurine;
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
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AdminCatalogueVue extends VBox {

    private Button btnHome;
    private TabPane tabPane;
    
    private TableView<Boite> tableBoites;
    private TableView<Piece> tablePieces;
    private TableView<Theme> tableThemes;
    private TableView<Figurine> tableFigurines;

    private ComboBox<Theme> cbFiltreThemeBoite;
    private TextField txtFiltreNomBoite;
    private Button btnClearBoite;

    private ComboBox<Categorie> cbFiltreCatPiece;
    private TextField txtFiltreNomPiece;
    private Button btnClearPiece;

    private TextField txtFiltreNomTheme;
    private TextField txtFiltreNomFigurine;

    public AdminCatalogueVue() {
        this.setSpacing(15);
        this.setPadding(new Insets(20));
        this.setStyle("-fx-background-color: #FFFFFF;");

        HBox header = new HBox(20);
        header.setAlignment(Pos.CENTER_LEFT);

        Label lblTitre = new Label("Catalogue Global");
        lblTitre.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        lblTitre.setStyle("-fx-text-fill: #0055BF;"); 
        header.getChildren().add(lblTitre);

        tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        VBox.setVgrow(tabPane, Priority.ALWAYS);

        // Onglet Boîtes
        Tab tabBoites = new Tab("Boîtes");
        
        cbFiltreThemeBoite = new ComboBox<>();
        cbFiltreThemeBoite.setPromptText("Filtrer par Thème");
        txtFiltreNomBoite = new TextField();
        txtFiltreNomBoite.setPromptText("Filtrer par nom/numéro...");
        btnClearBoite = new Button("Effacer"); 
        
        HBox filtresBoite = new HBox(10, cbFiltreThemeBoite, txtFiltreNomBoite, btnClearBoite);
        filtresBoite.setAlignment(Pos.CENTER_LEFT);

        tableBoites = new TableView<>();
        TableColumn<Boite, String> colNumBoite = new TableColumn<>("Numéro"); colNumBoite.setCellValueFactory(new PropertyValueFactory<>("numero"));
        TableColumn<Boite, String> colNomBoite = new TableColumn<>("Nom"); colNomBoite.setCellValueFactory(new PropertyValueFactory<>("nom"));
        TableColumn<Boite, Integer> colAnneeBoite = new TableColumn<>("Année"); colAnneeBoite.setCellValueFactory(new PropertyValueFactory<>("annee"));
        TableColumn<Boite, Integer> colNbPiecesBoite = new TableColumn<>("Pièces"); colNbPiecesBoite.setCellValueFactory(new PropertyValueFactory<>("nbPiece"));
        
        TableColumn<Boite, String> colThemeBoite = new TableColumn<>("Thème");
        colThemeBoite.setCellValueFactory(cellData -> {
            Theme t = cellData.getValue().getTheme();
            return new SimpleStringProperty(t != null ? t.getNom() : "Aucun");
        });

        tableBoites.getColumns().addAll(colNumBoite, colNomBoite, colAnneeBoite, colNbPiecesBoite, colThemeBoite);
        tableBoites.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        VBox vbBoites = new VBox(10, filtresBoite, tableBoites);
        vbBoites.setPadding(new Insets(10));
        VBox.setVgrow(tableBoites, Priority.ALWAYS);
        tabBoites.setContent(vbBoites);

        // Onglet Pièces
        Tab tabPieces = new Tab("Pièces");
        
        cbFiltreCatPiece = new ComboBox<>();
        cbFiltreCatPiece.setPromptText("Filtrer par Catégorie");
        txtFiltreNomPiece = new TextField();
        txtFiltreNomPiece.setPromptText("Filtrer par nom/numéro...");
        btnClearPiece = new Button("Effacer");

        HBox filtresPiece = new HBox(10, cbFiltreCatPiece, txtFiltreNomPiece, btnClearPiece);
        filtresPiece.setAlignment(Pos.CENTER_LEFT);

        tablePieces = new TableView<>();
        TableColumn<Piece, String> colNumPiece = new TableColumn<>("Numéro"); colNumPiece.setCellValueFactory(new PropertyValueFactory<>("numPiece"));
        TableColumn<Piece, String> colNomPiece = new TableColumn<>("Nom"); colNomPiece.setCellValueFactory(new PropertyValueFactory<>("nomPiece"));
        
        TableColumn<Piece, String> colCatPiece = new TableColumn<>("Catégorie");
        colCatPiece.setCellValueFactory(cellData -> {
            Categorie c = cellData.getValue().getCategorie();
            return new SimpleStringProperty(c != null ? c.getNomCat() : "Aucune");
        });

        tablePieces.getColumns().addAll(colNumPiece, colNomPiece, colCatPiece);
        tablePieces.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        VBox vbPieces = new VBox(10, filtresPiece, tablePieces);
        vbPieces.setPadding(new Insets(10));
        VBox.setVgrow(tablePieces, Priority.ALWAYS);
        tabPieces.setContent(vbPieces);

        // Onglet Thèmes
        Tab tabThemes = new Tab("Thèmes");
        txtFiltreNomTheme = new TextField();
        txtFiltreNomTheme.setPromptText("Filtrer par ID ou nom de thème...");

        tableThemes = new TableView<>();
        TableColumn<Theme, Integer> colIdTheme = new TableColumn<>("ID"); colIdTheme.setCellValueFactory(new PropertyValueFactory<>("idTheme"));
        TableColumn<Theme, String> colNomTheme = new TableColumn<>("Nom du Thème"); colNomTheme.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tableThemes.getColumns().addAll(colIdTheme, colNomTheme);
        tableThemes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        VBox vbThemes = new VBox(10, txtFiltreNomTheme, tableThemes);
        vbThemes.setPadding(new Insets(10));
        VBox.setVgrow(tableThemes, Priority.ALWAYS);
        tabThemes.setContent(vbThemes);

        // Onglet Figurines
        Tab tabFigurines = new Tab("Figurines");
        txtFiltreNomFigurine = new TextField();
        txtFiltreNomFigurine.setPromptText("Filtrer par ID ou nom de figurine...");

        tableFigurines = new TableView<>();
        TableColumn<Figurine, String> colIdFig = new TableColumn<>("ID Figurine"); colIdFig.setCellValueFactory(new PropertyValueFactory<>("idFig"));
        TableColumn<Figurine, String> colNomFig = new TableColumn<>("Nom"); colNomFig.setCellValueFactory(new PropertyValueFactory<>("nomFig"));
        TableColumn<Figurine, Integer> colNbParties = new TableColumn<>("Nb Parties"); colNbParties.setCellValueFactory(new PropertyValueFactory<>("nbParties"));
        
        tableFigurines.getColumns().addAll(colIdFig, colNomFig, colNbParties);
        tableFigurines.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        VBox vbFigurines = new VBox(10, txtFiltreNomFigurine, tableFigurines);
        vbFigurines.setPadding(new Insets(10));
        VBox.setVgrow(tableFigurines, Priority.ALWAYS);
        tabFigurines.setContent(vbFigurines);

        tabPane.getTabs().addAll(tabBoites, tabPieces, tabThemes, tabFigurines);
        // --- BOUTON RETOUR EN BAS ---
        this.btnHome = new Button();
        try {
            ImageView homeView = new ImageView(new Image(getClass().getResourceAsStream("/img/logoRetour.png")));
            homeView.setFitWidth(35); homeView.setFitHeight(35); homeView.setPreserveRatio(true);
            this.btnHome.setGraphic(homeView);
            this.btnHome.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        } catch (Exception e) { this.btnHome.setText("Retour"); }
        HBox zoneRetour = new HBox(btnHome);
        zoneRetour.setAlignment(Pos.CENTER_LEFT);
        VBox.setMargin(zoneRetour, new Insets(10, 0, 0, 0));

        this.getChildren().addAll(header, new Separator(), tabPane, zoneRetour);
    }

    public Button getBtnHome() { return btnHome; }
    public TableView<Boite> getTableBoites() { return tableBoites; }
    public TableView<Piece> getTablePieces() { return tablePieces; }
    public TableView<Theme> getTableThemes() { return tableThemes; }
    public TableView<Figurine> getTableFigurines() { return tableFigurines; }
    
    public ComboBox<Theme> getCbFiltreThemeBoite() { return cbFiltreThemeBoite; }
    public TextField getTxtFiltreNomBoite() { return txtFiltreNomBoite; }
    public Button getBtnClearBoite() { return btnClearBoite; }

    public ComboBox<Categorie> getCbFiltreCatPiece() { return cbFiltreCatPiece; }
    public TextField getTxtFiltreNomPiece() { return txtFiltreNomPiece; }
    public Button getBtnClearPiece() { return btnClearPiece; }

    public TextField getTxtFiltreNomTheme() { return txtFiltreNomTheme; }
    public TextField getTxtFiltreNomFigurine() { return txtFiltreNomFigurine; }
}