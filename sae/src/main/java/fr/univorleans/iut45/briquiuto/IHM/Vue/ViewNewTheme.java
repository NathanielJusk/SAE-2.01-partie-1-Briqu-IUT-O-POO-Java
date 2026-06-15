package fr.univorleans.iut45.briquiuto.IHM.Vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ViewNewTheme extends VBox {
    private Label titreLabel;
    private Button home;
    private GridPane grid;
    private HBox hbox;

    private Label numThemeLabel;
    private TextField numThemeTextField;
    private Label nomThemeLabel;
    private TextField nomThemeTextField;
    private Label numThemeParentLabel;
    private TextField numThemeParentTextField;

    private Button validerButton;

    public ViewNewTheme() {
        // Appelle le constructeur de VBox avec un espacement de 15 pixels entre les blocs
        super(15); 
        this.setPadding(new Insets(20)); // Ajoute des marges tout autour de la fenêtre
        this.setAlignment(Pos.TOP_CENTER); // Centre les éléments horizontalement

        // Titre et bouton Home (corrigé avec un texte)
        this.titreLabel = new Label("Créer un thème ou un sous-thème");
        this.titreLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;"); // Un peu de style optionnel
        this.home = new Button("Accueil");

        // Création et configuration du GridPane
        this.grid = new GridPane();
        this.grid.setHgap(10); // Espacement horizontal entre label et champ
        this.grid.setVgap(10); // Espacement vertical entre les lignes
        this.grid.setAlignment(Pos.CENTER); // Centre le formulaire

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

        // Création de la HBox pour les boutons du bas
        this.hbox = new HBox();
        this.hbox.setAlignment(Pos.CENTER);
        this.validerButton = new Button("Valider");
        this.hbox.getChildren().add(validerButton);

        // Ajout DIRECT des composants à CETTE classe (qui est elle-même une VBox)
        this.getChildren().addAll(titreLabel, home, grid, hbox);
    }
}