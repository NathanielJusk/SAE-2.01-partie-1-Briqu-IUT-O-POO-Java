package fr.univorleans.iut45.briquiuto.IHM.Vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ViewNewTheme extends VBox {
    private Label titreLabel;
    private Button home;
    private Image homeImage;
    private GridPane grid;
    private HBox hbox;

    private Label numThemeLabel;
    private TextField numThemeTextField;
    private Label nomThemeLabel;
    private TextField nomThemeTextField;
    private Label numThemeParentLabel;
    private TextField numThemeParentTextField;

    private HBox hboxHautDePage;

    private Button validerButton;

    public ViewNewTheme() {
        // Appelle le constructeur de VBox avec un espacement de 15 pixels entre les blocs
        super(15); 
        this.setPadding(new Insets(20)); // Ajoute des marges tout autour de la fenêtre
        this.setAlignment(Pos.TOP_CENTER); // Centre les éléments horizontalement

        // Titre et bouton Home (corrigé avec un texte)
        this.titreLabel = new Label("Créer un thème ou un sous-thème");
        this.titreLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;"); 
        
        this.homeImage = new Image(getClass().getResourceAsStream("/img/70083.png"));
        ImageView homeImageView = new ImageView(this.homeImage);
        homeImageView.setFitWidth(30);
        homeImageView.setFitHeight(30);
        homeImageView.setPreserveRatio(true);
        this.home = new Button();
        this.home.setGraphic(homeImageView);

        // Création et configuration du GridPane
        this.grid = new GridPane();
        this.grid.setHgap(10); 
        this.grid.setVgap(10); 
        this.grid.setAlignment(Pos.CENTER);

        this.numThemeLabel = new Label("Numéro du thème :");
        this.numThemeTextField = new TextField();
        this.nomThemeLabel = new Label("Nom du thème :");
        this.nomThemeTextField = new TextField();
        this.numThemeParentLabel = new Label("Numéro du thème parent :");
        this.numThemeParentTextField = new TextField();

        grid.add(numThemeLabel, 0, 0);
        grid.add(numThemeTextField, 1, 0);
        grid.add(nomThemeLabel, 0, 1);
        grid.add(nomThemeTextField, 1, 1);
        grid.add(numThemeParentLabel, 0, 2);
        grid.add(numThemeParentTextField, 1, 2);


        this.hbox = new HBox();
        this.hbox.setAlignment(Pos.CENTER);
        this.validerButton = new Button("Valider");
        this.hbox.getChildren().add(validerButton);

        this.hboxHautDePage = new HBox();
        this.hboxHautDePage.setAlignment(Pos.CENTER);
        this.hboxHautDePage.setSpacing(100); 
        this.hboxHautDePage.getChildren().addAll(titreLabel, home);


        this.getChildren().add(hboxHautDePage);
        this.getChildren().add(grid);
        this.getChildren().add(hbox);
    }
    
    public TextField getNumThemeTextField() {
        return numThemeTextField;
    }

    public TextField getNomThemeTextField() {
        return nomThemeTextField;
    }

    public TextField getNumThemeParentTextField() {
        return numThemeParentTextField;
    }

    public Button getValiderButton() {
        return validerButton;
    }

    public Button getHomeButton() {
        return home;
    }
}
