package fr.univorleans.iut45.briquiuto.IHM.Vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ViewNewTheme extends VBox {

    private TextField numThemeTextField;
    private TextField nomThemeTextField;
    private TextField numThemeParentTextField;
    private Button validerButton;
    private Button homeButton;

    public ViewNewTheme() {
        this.setSpacing(25);
        this.setPadding(new Insets(30));
        this.setStyle("-fx-background-color: #FFFFFF;");

        // --- EN-TÊTE STANDARD ---
        HBox header = new HBox(20);
        header.setAlignment(Pos.CENTER_LEFT);
        
        this.homeButton = new Button();
        try {
            Image homeImage = new Image(getClass().getResourceAsStream("/img/70083.png"));
            ImageView homeImageView = new ImageView(homeImage);
            homeImageView.setFitWidth(35); homeImageView.setFitHeight(35); homeImageView.setPreserveRatio(true);
            this.homeButton.setGraphic(homeImageView);
            this.homeButton.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        } catch (Exception e) {
            this.homeButton.setText("🏠");
        }

        Label lblTitre = new Label("Créer un thème ou sous-thème");
        lblTitre.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        lblTitre.setStyle("-fx-text-fill: #0055BF;");
        
        header.getChildren().addAll(homeButton, lblTitre);

        Separator separateur = new Separator();
        separateur.setStyle("-fx-background-color: #F6D304; -fx-border-width: 2px;");

        // --- FORMULAIRE ---
        GridPane grid = new GridPane();
        grid.setVgap(20);
        grid.setHgap(20);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(30, 0, 20, 0));

        String styleLabel = "-fx-font-weight: bold; -fx-font-size: 14px;";
        String styleChamp = "-fx-border-color: #0055BF; -fx-border-width: 2px; -fx-border-radius: 3; -fx-padding: 5;";

        Label numThemeLabel = new Label("Numéro du thème :"); numThemeLabel.setStyle(styleLabel);
        numThemeTextField = new TextField(); numThemeTextField.setStyle(styleChamp);

        Label nomThemeLabel = new Label("Nom du thème :"); nomThemeLabel.setStyle(styleLabel);
        nomThemeTextField = new TextField(); nomThemeTextField.setStyle(styleChamp);

        Label numThemeParentLabel = new Label("Numéro du parent (optionnel) :"); numThemeParentLabel.setStyle(styleLabel);
        numThemeParentTextField = new TextField(); numThemeParentTextField.setStyle(styleChamp);

        grid.add(numThemeLabel, 0, 0); grid.add(numThemeTextField, 1, 0);
        grid.add(nomThemeLabel, 0, 1); grid.add(nomThemeTextField, 1, 1);
        grid.add(numThemeParentLabel, 0, 2); grid.add(numThemeParentTextField, 1, 2);

        // --- BOUTON VALIDER (Vert LEGO) ---
        validerButton = new Button("Valider le thème");
        validerButton.setStyle("-fx-background-color: #287F46; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10 30; -fx-background-radius: 5;");
        
        VBox btnContainer = new VBox(validerButton);
        btnContainer.setAlignment(Pos.CENTER);

        this.getChildren().addAll(header, separateur, grid, btnContainer);
    }

    public TextField getNumThemeTextField() { return numThemeTextField; }
    public TextField getNomThemeTextField() { return nomThemeTextField; }
    public TextField getNumThemeParentTextField() { return numThemeParentTextField; }
    public Button getValiderButton() { return validerButton; }
    public Button getHomeButton() { return homeButton; }
}