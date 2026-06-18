package fr.univorleans.iut45.briquiuto.IHM.Controlleurs.adminControlleurs;

import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.modele.Boite;
import fr.univorleans.iut45.briquiuto.modele.Figurine;
import fr.univorleans.iut45.briquiuto.modele.Piece;
import fr.univorleans.iut45.briquiuto.IHM.Vue.admin.AdminCatalogueVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.admin.AdminHomeVue;
import fr.univorleans.iut45.briquiuto.IHM.Controlleurs.AccueilControleur.AccueilControleur;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AccueilVue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

/**
 * Contrôleur pour la gestion du catalogue (vue admin).
 * Charge les boîtes, pièces et figurines depuis le modèle
 * et gère la navigation vers les autres vues.
 */
public class AdminCatalogueControleur {

    // Vue du catalogue côté admin
    private AdminCatalogueVue vue;
    private RequetesLEGO modele;
    private Stage fenetrePrincipale;

    public AdminCatalogueControleur(AdminCatalogueVue vue, RequetesLEGO modele, Stage fenetrePrincipale) {
        this.vue = vue;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        this.initialiser();
    }

    /**
     * Initialise les actions et charge les données pour le tableau.
     */
    private void initialiser() {
        // Navigation
        vue.getBtnRetour().setOnAction(e -> actionRetourAdmin());
        vue.getBtnHome().setOnAction(e -> actionRetourAccueil());

        // Chargement des données depuis la base (RequetesLEGO)
        chargerDonnees();
    }

    /**
     * Charge les boîtes, pièces et figurines depuis le modèle et les place
     * dans les tables JavaFX de la vue. Affiche une alerte en cas d'erreur.
     */
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
    // Navigation helpers
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