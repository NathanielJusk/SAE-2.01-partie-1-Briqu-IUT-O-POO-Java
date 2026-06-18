package fr.univorleans.iut45.briquiuto.IHM.Controlleurs;

import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.modele.Boite;
import fr.univorleans.iut45.briquiuto.modele.Figurine;
import fr.univorleans.iut45.briquiuto.modele.Piece;
import fr.univorleans.iut45.briquiuto.IHM.Vue.admin.AdminCatalogueVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.admin.AdminHomeVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AccueilVue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class AdminCatalogueControleur {

    private AdminCatalogueVue vue;
    private RequetesLEGO modele;
    private Stage fenetrePrincipale;

    public AdminCatalogueControleur(AdminCatalogueVue vue, RequetesLEGO modele, Stage fenetrePrincipale) {
        this.vue = vue;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        this.initialiser();
    }

    private void initialiser() {
        // --- GESTION DES BOUTONS DE NAVIGATION ---
        vue.getBtnRetour().setOnAction(e -> actionRetourAdmin());
        vue.getBtnHome().setOnAction(e -> actionRetourAccueil());

        // --- CHARGEMENT DES DONNÉES ---
        chargerDonnees();
    }

    private void chargerDonnees() {
        try {
            // 1. Charger les Boîtes
            List<Boite> boites = modele.getAllBoites();
            vue.getTableBoites().setItems(FXCollections.observableArrayList(boites));

            // 2. Charger les Pièces
            List<Piece> pieces = modele.getAllPieces();
            vue.getTablePieces().setItems(FXCollections.observableArrayList(pieces));

            // 3. Charger les Figurines
            List<Figurine> figurines = modele.getAllFigurines();
            vue.getTableFigurines().setItems(FXCollections.observableArrayList(figurines));

        } catch (SQLException e) {
            Alert alerte = new Alert(Alert.AlertType.ERROR);
            alerte.setTitle("Erreur de chargement");
            alerte.setHeaderText(null);
            alerte.setContentText("Impossible de charger les données du catalogue.\n" + e.getMessage());
            alerte.showAndWait();
        }
    }

    // --- NAVIGATION ---
    private void actionRetourAdmin() {
        AdminHomeVue vueAdmin = new AdminHomeVue();
        new AdminHomeControleur(vueAdmin, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueAdmin, 1000, 700));
    }

    private void actionRetourAccueil() {
        AccueilVue vueAccueil = new AccueilVue();
        new AccueilControleur(vueAccueil, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueAccueil, 1000, 700));
    }
}