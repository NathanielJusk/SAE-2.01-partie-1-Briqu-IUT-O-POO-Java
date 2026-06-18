package fr.univorleans.iut45.briquiuto.IHM.Vue.collec;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class VueRechercheBoiteParNumero extends VBox {

    /**
     * Vue pour rechercher une boîte par son numéro (ex: 75192).
     * Affiche les résultats dans une liste et gère les cas d'erreur simples.
     * Écrite de façon claire pour un rendu de projet de première année.
     */

    private Button btnHome;
    private Button btnRetour;
    private TextField txtNumeroBoite;
    private Button btnRechercher;
    
    private VBox zoneResultat;
    private Label lblResultatNom;
    private Label lblResultatAnnee;
    private Label lblResultatNbPieces;
    private Label lblErreur;
    
    private ImageView imageBoiteView;
    private PieChart graphiqueCouleurs;

    public VueRechercheBoiteParNumero() {
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

        Label lblTitre = new Label("Recherche de Boîte (Collectionneur)");
        lblTitre.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        lblTitre.setStyle("-fx-text-fill: #0055BF;"); 
        
        header.getChildren().addAll(btnHome, lblTitre);
        Separator separateur = new Separator();
        separateur.setStyle("-fx-background-color: #F6D304; -fx-border-width: 2px;");

        // 2. ZONE DE RECHERCHE
        HBox zoneRecherche = new HBox(15);
        zoneRecherche.setAlignment(Pos.CENTER_LEFT);
        
        Label lblInstruction = new Label("Numéro de la boîte :");
        lblInstruction.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        
        txtNumeroBoite = new TextField(); 
        txtNumeroBoite.setPromptText("Ex: 75192");
        txtNumeroBoite.setStyle("-fx-border-color: #0055BF; -fx-border-width: 2px; -fx-border-radius: 4px; -fx-padding: 5px; -fx-font-size: 14px;");
        
        btnRechercher = new Button("Rechercher");
        btnRechercher.setStyle("-fx-background-color: #287F46; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-font-size: 14px; -fx-padding: 8 20; -fx-background-radius: 5px;");

        lblErreur = new Label("");
        lblErreur.setStyle("-fx-text-fill: #E3000B; -fx-font-weight: bold; -fx-font-size: 14px;");
        lblErreur.setVisible(false);

        zoneRecherche.getChildren().addAll(lblInstruction, txtNumeroBoite, btnRechercher, lblErreur);

        // 3. ZONE DE RÉSULTAT
        zoneResultat = new VBox(20);
        zoneResultat.setPadding(new Insets(20));
        zoneResultat.setStyle("-fx-background-color: #F8F9FA; -fx-border-color: #E0E0E0; -fx-border-radius: 8; -fx-background-radius: 8;");
        zoneResultat.setVisible(false);
        VBox.setVgrow(zoneResultat, Priority.ALWAYS);

        HBox contenuResultat = new HBox(40);
        contenuResultat.setAlignment(Pos.CENTER_LEFT);

        // Partie Gauche : Infos
        VBox infosBox = new VBox(15);
        infosBox.setPrefWidth(300); // On fixe la taille de la zone info
        imageBoiteView = new ImageView();
        imageBoiteView.setFitWidth(280); 
        imageBoiteView.setFitHeight(280); 
        imageBoiteView.setPreserveRatio(true);

        lblResultatNom = new Label("-");
        lblResultatNom.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        lblResultatNom.setStyle("-fx-text-fill: #0055BF;");
        lblResultatNom.setWrapText(true);

        lblResultatAnnee = new Label("-");
        lblResultatAnnee.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #555555;");

        lblResultatNbPieces = new Label("-");
        lblResultatNbPieces.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #555555;");

        infosBox.getChildren().addAll(imageBoiteView, lblResultatNom, lblResultatAnnee, lblResultatNbPieces);

        // Partie Droite : Graphique (Agrandi !)
        graphiqueCouleurs = new PieChart();
        graphiqueCouleurs.setTitle("Répartition des Couleurs");
        graphiqueCouleurs.setLabelsVisible(false); 
        graphiqueCouleurs.setLegendVisible(true);
        graphiqueCouleurs.setLegendSide(Side.RIGHT); // On place la légende à droite
        graphiqueCouleurs.setMinSize(450, 350); // Taille minimum garantie
        HBox.setHgrow(graphiqueCouleurs, Priority.ALWAYS); // On lui dit de prendre toute la place restante !

        contenuResultat.getChildren().addAll(infosBox, graphiqueCouleurs);
        zoneResultat.getChildren().add(contenuResultat);

        // 4. PIED DE PAGE
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
        this.getChildren().addAll(header, separateur, zoneRecherche, zoneResultat, footer);
    }

    public void afficherErreur(String msg) {
        zoneResultat.setVisible(false);
        lblErreur.setText(msg);
        lblErreur.setVisible(true);
    }
    public void cacherErreur() { lblErreur.setVisible(false); }

    public Button getBtnHome() { return btnHome; }
    public Button getBtnRetour() { return btnRetour; }
    public Button getBtnRechercher() { return btnRechercher; }
    public TextField getTxtNumeroBoite() { return txtNumeroBoite; }
    
    public VBox getZoneResultat() { return zoneResultat; }
    public Label getLblResultatNom() { return lblResultatNom; }
    public Label getLblResultatAnnee() { return lblResultatAnnee; }
    public Label getLblResultatNbPieces() { return lblResultatNbPieces; }
    public ImageView getImageBoiteView() { return imageBoiteView; }
    public PieChart getGraphiqueCouleurs() { return graphiqueCouleurs; }
}