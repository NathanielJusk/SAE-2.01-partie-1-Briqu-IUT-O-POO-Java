package fr.univorleans.iut45.briquiuto.IHM.Vue.collec;

import fr.univorleans.iut45.briquiuto.modele.Boite;
import fr.univorleans.iut45.briquiuto.modele.Theme;
import javafx.collections.ObservableList;
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
import javafx.util.StringConverter;

/**
 * Vue permettant de filtrer et afficher les boîtes du catalogue en fonction du thème sélectionné.
 */
public class VueRechercheBoiteParThemeCollectionneur extends VBox {

    // Composants d'en-tête
    private Label lblTitre;

    // Composants de la barre de filtre
    private Label lblTheme;
    private ComboBox<Theme> cbTheme;
    private Button btnRechercher;

    // Tableau de résultats
    private TableView<Boite> tableResultats;
    private TableColumn<Boite, String> colNumero;
    private TableColumn<Boite, String> colNom;
    private TableColumn<Boite, Integer> colAnnee;
    private TableColumn<Boite, Integer> colNbPieces;

    // Bouton de navigation basse
    private Button btnRetour;

    public VueRechercheBoiteParThemeCollectionneur() {
        // 1. Configuration globale (VBox)
        this.setSpacing(15);
        this.setPadding(new Insets(20));
        this.setAlignment(Pos.TOP_CENTER);
        this.setStyle("-fx-background-color: #FFFFFF;");

        // 2. Création de l'en-tête
        lblTitre = new Label("Rechercher les boîtes par thème");
        lblTitre.setFont(Font.font("Arial", FontWeight.BOLD, 24)); // Agrandi pour harmoniser avec les autres vues
        lblTitre.setStyle("-fx-text-fill: #0055BF;"); // Bleu LEGO

        Separator separateur = new Separator();
        separateur.setStyle("-fx-background-color: #F6D304; -fx-border-width: 2px;"); // Jaune LEGO

        // 3. Barre de filtre (HBox)
        HBox zoneFiltre = new HBox(12);
        zoneFiltre.setAlignment(Pos.CENTER_LEFT);
        zoneFiltre.setPadding(new Insets(5, 0, 5, 0));

        lblTheme = new Label("Thème :");
        lblTheme.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        cbTheme = new ComboBox<>();
        cbTheme.setPromptText("Sélectionner un thème...");
        cbTheme.setPrefWidth(250);
        cbTheme.setStyle("-fx-border-color: #0055BF; -fx-border-width: 2px; -fx-border-radius: 4px;");

        // Nettoyage de l'affichage pour n'avoir que le nom du thème dans la boîte déroulante
        cbTheme.setConverter(new StringConverter<Theme>() {
            @Override
            public String toString(Theme theme) {
                return (theme != null) ? theme.getNom() : "";
            }

            @Override
            public Theme fromString(String string) {
                return null; 
            }
        });

        btnRechercher = new Button("Rechercher");
        btnRechercher.setStyle("-fx-background-color: #287F46; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 5 15;");

        zoneFiltre.getChildren().addAll(lblTheme, cbTheme, btnRechercher);

        // 4. Configuration de la TableView pour les résultats
        tableResultats = new TableView<>();
        tableResultats.setPlaceholder(new Label("Aucun résultat. Sélectionnez un thème et validez la recherche."));

        colNumero = new TableColumn<>("Numéro");
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colNumero.setPrefWidth(120);

        colNom = new TableColumn<>("Nom");
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colNom.setPrefWidth(400);

        colAnnee = new TableColumn<>("Année");
        colAnnee.setCellValueFactory(new PropertyValueFactory<>("annee"));
        colAnnee.setPrefWidth(120);

        colNbPieces = new TableColumn<>("Pièces");
        colNbPieces.setCellValueFactory(new PropertyValueFactory<>("nbPiece"));
        colNbPieces.setPrefWidth(120);

        tableResultats.getColumns().addAll(colNumero, colNom, colAnnee, colNbPieces);
        
        // Permet au tableau d'occuper de manière dynamique l'espace restant
        VBox.setVgrow(tableResultats, Priority.ALWAYS);

        // ==========================================
        // 5. Zone basse (Bouton retour standardisé)
        // ==========================================
        HBox zoneBasse = new HBox();
        zoneBasse.setAlignment(Pos.BOTTOM_LEFT); 
        
        this.btnRetour = new Button();
        try {
            // Chemin relatif standard utilisé dans tes autres vues
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

        zoneBasse.getChildren().add(btnRetour);

        // Assemblage de l'arbre
        this.getChildren().addAll(lblTitre, separateur, zoneFiltre, tableResultats, zoneBasse);
    }

    // ── GETTERS POUR LE CONTRÔLEUR (MVC) ──

    public ComboBox<Theme> getCbTheme() {
        return cbTheme;
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
     * Remplit le menu déroulant avec les thèmes récupérés depuis la base/modèle.
     */
    public void alimenterThemes(ObservableList<Theme> listeThemes) {
        cbTheme.setItems(listeThemes);
    }

    /**
     * Injecte et affiche la liste des boîtes trouvées dans le tableau.
     */
    public void afficherResultats(ObservableList<Boite> listeBoites) {
        tableResultats.setItems(listeBoites);
    }
}