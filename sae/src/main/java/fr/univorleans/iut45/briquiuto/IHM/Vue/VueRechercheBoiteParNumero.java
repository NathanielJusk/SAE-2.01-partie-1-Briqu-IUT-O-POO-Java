package fr.univorleans.iut45.briquiuto.IHM.Vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class VueRechercheBoiteParNumero extends VBox {

    private TextField txtNumeroBoite;
    private Button btnRechercher;
    private Button btnRetour;
    
    private Label lblResultatNom;
    private Label lblResultatAnnee;
    private Label lblResultatNbPieces;

    public VueRechercheBoiteParNumero() {
        this.setSpacing(20);
        this.setPadding(new Insets(30));
        this.setAlignment(Pos.TOP_CENTER);

        // -- En-tête --
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        
        this.btnRetour = new Button();
        try {
            ImageView homeView = new ImageView(new Image(getClass().getResourceAsStream("/img/70083.png")));
            homeView.setFitWidth(30); homeView.setFitHeight(30); homeView.setPreserveRatio(true);
            this.btnRetour.setGraphic(homeView);
            this.btnRetour.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        } catch (Exception e) { this.btnRetour.setText("Retour"); }

        Label lblTitre = new Label("Détails d'une Boîte");
        lblTitre.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        header.getChildren().addAll(btnRetour, lblTitre);

        // -- Zone de recherche (Champ texte) --
        HBox zoneRecherche = new HBox(15);
        zoneRecherche.setAlignment(Pos.CENTER);
        
        Label lblInstruction = new Label("Numéro de la boîte :");
        txtNumeroBoite = new TextField();
        txtNumeroBoite.setPromptText("Ex: 75192");
        
        btnRechercher = new Button("Chercher");
        btnRechercher.setStyle("-fx-background-color: #287F46; -fx-text-fill: white; -fx-font-weight: bold;");

        zoneRecherche.getChildren().addAll(lblInstruction, txtNumeroBoite, btnRechercher);

        // -- Zone de résultats --
        VBox zoneResultat = new VBox(15);
        zoneResultat.setAlignment(Pos.CENTER);
        zoneResultat.setStyle("-fx-background-color: #F8F9FA; -fx-padding: 20; -fx-border-color: #ccc;");
        zoneResultat.setVisible(false); // Caché au début

        lblResultatNom = new Label("");
        lblResultatNom.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        lblResultatAnnee = new Label("");
        lblResultatNbPieces = new Label("");

        zoneResultat.getChildren().addAll(lblResultatNom, lblResultatAnnee, lblResultatNbPieces);

        this.getChildren().addAll(header, new Separator(), zoneRecherche, zoneResultat);
    }

    public TextField getTxtNumeroBoite() { return txtNumeroBoite; }
    public Button getBtnRechercher() { return btnRechercher; }
    public Button getBtnRetour() { return btnRetour; }
    public Label getLblResultatNom() { return lblResultatNom; }
    public Label getLblResultatAnnee() { return lblResultatAnnee; }
    public Label getLblResultatNbPieces() { return lblResultatNbPieces; }
}