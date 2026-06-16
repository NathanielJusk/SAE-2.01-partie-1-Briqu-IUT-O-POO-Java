package fr.univorleans.iut45.briquiuto.IHM.Vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class AccueilVue extends VBox {

    private Button btnCollection;
    private Button btnAdmin;
    private Button btnQuit;

    public AccueilVue() {
        this.setAlignment(Pos.CENTER);
        
        // 1. MISE EN PLACE DU FOND D'ÉCRAN
        try {
            Image fondImage = new Image(getClass().getResourceAsStream("/img/fondEcran.jpeg"));
            BackgroundSize bgSize = new BackgroundSize(100, 100, true, true, false, true); // Effet Cover
            BackgroundImage bg = new BackgroundImage(fondImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bgSize);
            this.setBackground(new Background(bg));
        } catch (Exception e) { System.out.println("Image fondEcran.jpeg introuvable."); }

        // 2. CRÃ‰ATION DE LA CARTE BLANCHE CENTRALE (Pour la lisibilitÃ©)
        VBox carteCentrale = new VBox(20);
        carteCentrale.setPadding(new Insets(30));
        carteCentrale.setAlignment(Pos.CENTER);
        carteCentrale.setMaxWidth(450); // EmpÃªche la carte de prendre tout l'Ã©cran
        // Fond blanc Ã  90% d'opacitÃ© avec des bords arrondis et une petite ombre
        carteCentrale.setStyle("-fx-background-color: rgba(255, 255, 255, 0.92); -fx-background-radius: 15; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 15, 0, 0, 5);");

        // 3. INTÃ‰GRATION DU LOGO
        try {
            ImageView logoView = new ImageView(new Image(getClass().getResourceAsStream("/img/LogoPrincipale.png")));
            logoView.setFitHeight(70);
            logoView.setPreserveRatio(true);
            carteCentrale.getChildren().add(logoView);
        } catch (Exception e) { System.out.println("LogoPrincipale.png introuvable."); }

        Separator separator = new Separator();
        separator.setStyle("-fx-background-color: #F6D304; -fx-border-width: 2px;");

        // 4. IMAGE DE SALUTATION
        try {
            ImageView salutView = new ImageView(new Image(getClass().getResourceAsStream("/img/SalutPageAccueil.jpeg")));
            salutView.setFitHeight(100);
            salutView.setPreserveRatio(true);
            carteCentrale.getChildren().add(salutView);
        } catch (Exception e) { System.out.println("SalutPageAccueil.jpeg introuvable."); }

        Label welcomeText = new Label("Bienvenue dans votre espace ! \nGÃ©rez votre collection et composez vos boÃ®tes personnalisÃ©es.");
        welcomeText.setFont(Font.font("Arial", 14));
        welcomeText.setWrapText(true);
        welcomeText.setTextAlignment(TextAlignment.CENTER);
        welcomeText.setStyle("-fx-text-fill: #333333;");

        // 5. BOUTONS D'ACTIONS
        String styleBoutonBleu = "-fx-background-color: #0055BF; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 12 20; -fx-background-radius: 8; -fx-cursor: hand;";
        
        btnCollection = new Button("MA COLLECTION PERSONNELLE");
        btnCollection.setStyle(styleBoutonBleu);
        btnCollection.setPrefWidth(Double.MAX_VALUE);

        btnAdmin = new Button("ESPACE ADMINISTRATION");
        btnAdmin.setStyle(styleBoutonBleu);
        btnAdmin.setPrefWidth(Double.MAX_VALUE);

        btnQuit = new Button("Quitter l'application");
        btnQuit.setStyle("-fx-background-color: #E3000B; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 8 20; -fx-background-radius: 5; -fx-cursor: hand;");
        VBox.setMargin(btnQuit, new Insets(20, 0, 0, 0));

        carteCentrale.getChildren().addAll(separator, welcomeText, btnCollection, btnAdmin, btnQuit);

        // Ajout de la carte au centre du fond
        this.getChildren().add(carteCentrale);
    }

    public Button getBtnCollection() { return btnCollection; }
    public Button getBtnAdmin() { return btnAdmin; }
    public Button getBtnQuit() { return btnQuit; }
}