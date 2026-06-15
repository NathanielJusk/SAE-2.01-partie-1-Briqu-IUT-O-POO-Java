package fr.univorleans.iut45.briquiuto.IHM.Vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CollectionneurHomeVue extends VBox {

    private Button btnMaCollection;
    private Button btnRechercheBoite;
    private Button btnCompoBoite;
    private Button btnDeconnexion;

    public CollectionneurHomeVue() {
        this.setSpacing(20);
        this.setPadding(new Insets(40));
        this.setAlignment(Pos.TOP_CENTER);
        this.setStyle("-fx-background-color: #f5fffa;"); // Fond légèrement vert/menthe pour différencier

        Label lblTitre = new Label("Espace Collectionneur");
        lblTitre.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        
        Separator separateur = new Separator();

        btnMaCollection = new Button("Voir Ma Collection");
        styleBoutonMenu(btnMaCollection);

        btnRechercheBoite = new Button("Rechercher une Boîte (Catalogue)");
        styleBoutonMenu(btnRechercheBoite);

        btnCompoBoite = new Button("Composer une Boîte Personnalisée");
        styleBoutonMenu(btnCompoBoite);

        btnDeconnexion = new Button("Déconnexion");
        btnDeconnexion.setStyle("-fx-background-color: #ffcccc; -fx-text-fill: black; -fx-padding: 10 20;");
        VBox.setMargin(btnDeconnexion, new Insets(30, 0, 0, 0));

        this.getChildren().addAll(lblTitre, separateur, btnMaCollection, btnRechercheBoite, btnCompoBoite, btnDeconnexion);
    }

    private void styleBoutonMenu(Button btn) {
        btn.setStyle("-fx-background-color: #2e8b57; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20;");
        btn.setMaxWidth(250);
    }

    public Button getBtnMaCollection() { return btnMaCollection; }
    public Button getBtnRechercheBoite() { return btnRechercheBoite; }
    public Button getBtnCompoBoite() { return btnCompoBoite; }
    public Button getBtnDeconnexion() { return btnDeconnexion; }
}