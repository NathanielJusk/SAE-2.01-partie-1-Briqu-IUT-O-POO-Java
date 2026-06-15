package fr.univorleans.iut45.briquiuto.IHM.Vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class AccueilVue extends VBox {

    private Label titleLabel;
    private Separator separator;
    private Label menuHeader;
    private Label welcomeText;
    private Button btnCollection;
    private Button btnAdmin;
    private Button btnQuit;

    public AccueilVue() {

        super(20); // Espacement de 20 pixels entre chaque élément
        this.setPadding(new Insets(30));
        this.setAlignment(Pos.TOP_CENTER); // Centre les éléments
        this.setStyle("-fx-background-color: #ffffff;");

        // 1. Titre de l'application
        titleLabel = new Label("BRIQU'IUTO COLLECTION MANAGER®");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        
        // 2. Ligne de séparation
        separator = new Separator();
        separator.setPadding(new Insets(10, 0, 10, 0));

        // 3. En-tête du menu
        menuHeader = new Label("--- MENU PRINCIPAL ---");
        menuHeader.setFont(Font.font("Arial", 16));

        // 4. Texte de bienvenue
        welcomeText = new Label("Bienvenue dans votre espace Lego® ! Cette application vous permet de gérer votre collection, d'explorer les thèmes et de composer vos propres boîtes personnalisées");
        welcomeText.setFont(Font.font("Arial", 14));
        welcomeText.setWrapText(true); // Permet au texte de passer à la ligne
        welcomeText.setTextAlignment(TextAlignment.JUSTIFY);
        welcomeText.setPadding(new Insets(10, 0, 20, 0));

        // 5. Bouton : Collection Personnelle
        btnCollection = new Button("MA COLLECTION PERSONNELLE");
        btnCollection.setStyle("-fx-background-color: #dcdcdc; -fx-text-fill: black; -fx-padding: 8 20;");
        btnCollection.setMaxWidth(Double.MAX_VALUE); 
        btnCollection.setAlignment(Pos.CENTER_LEFT);

        // 6. Bouton : Administration
        btnAdmin = new Button("ESPACE ADMINISTRATION");
        btnAdmin.setStyle("-fx-background-color: #dcdcdc; -fx-text-fill: black; -fx-padding: 8 20;");
        btnAdmin.setMaxWidth(Double.MAX_VALUE);
        btnAdmin.setAlignment(Pos.CENTER_LEFT);

        // 7. Bouton : Quitter
        btnQuit = new Button("Quitter l'application");
        btnQuit.setStyle("-fx-background-color: #dcdcdc; -fx-text-fill: black; -fx-padding: 8 20;");
        btnQuit.setMaxWidth(Double.MAX_VALUE);
        btnQuit.setAlignment(Pos.CENTER_LEFT);

        // Assemblage final de l'arbre graphique
        this.getChildren().addAll(
            titleLabel, 
            separator, 
            menuHeader, 
            welcomeText, 
            btnCollection, 
            btnAdmin, 
            btnQuit
        );
    }

    // --- GETTERS POUR LE CONTROLEUR ---
    public Button getBtnCollection() { return btnCollection; }
    public Button getBtnAdmin() { return btnAdmin; }
    public Button getBtnQuit() { return btnQuit; }
}