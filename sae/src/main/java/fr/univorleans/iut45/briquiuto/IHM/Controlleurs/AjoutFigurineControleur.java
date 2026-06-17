package fr.univorleans.iut45.briquiuto.IHM.Controlleurs;

import java.sql.SQLException;
import fr.univorleans.iut45.briquiuto.modele.Figurine;
import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.IHM.Vue.admin.AdminHomeVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.admin.AjoutFigurineVue;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class AjoutFigurineControleur {

    private AjoutFigurineVue vue;
    private RequetesLEGO modele;
    private Stage fenetrePrincipale;

    public AjoutFigurineControleur(AjoutFigurineVue vue, RequetesLEGO modele, Stage fenetrePrincipale) {
        this.vue = vue;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        this.initialiser();
    }

    private void initialiser() {
        this.vue.getBtnValider().setOnAction(event -> handleValiderFigurine());
        this.vue.getBtnHome().setOnAction(event -> actionRetourAdmin());
    }

    public void handleValiderFigurine() {
        String id = vue.getTxtIdFig().getText().trim();
        String nom = vue.getTxtNomFig().getText().trim();
        String nbPartiesStr = vue.getTxtNbParties().getText().trim();

        // 1. Validation de la saisie
        if (id.isEmpty() || nom.isEmpty() || nbPartiesStr.isEmpty()) {
            afficherAlerte(Alert.AlertType.WARNING, "Champs incomplets", "Veuillez remplir tous les champs.");
            return;
        }

        int nbParties;
        try {
            nbParties = Integer.parseInt(nbPartiesStr);
        } catch (NumberFormatException e) {
            afficherAlerte(Alert.AlertType.WARNING, "Erreur de format", "Le nombre de pièces doit être un entier.");
            return;
        }

        try {
            // 2. Création et insertion via le modèle
            Figurine nouvelleFigurine = new Figurine(id, nom, nbParties);
            modele.ajouterFigurine(nouvelleFigurine);
            
            // 3. Succès
            afficherAlerte(Alert.AlertType.INFORMATION, "Succès", "La figurine '" + nom + "' a été ajoutée.");
            vue.reinitialiserFormulaire();

        } catch (SQLException e) {
            afficherAlerte(Alert.AlertType.ERROR, "Erreur Base de Données", "Impossible d'ajouter la figurine.");
        }
    }

    private void actionRetourAdmin() {
        AdminHomeVue vueAdmin = new AdminHomeVue();
        new AdminHomeControleur(vueAdmin, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueAdmin, 1000, 700));
    }

    private void afficherAlerte(Alert.AlertType type, String titre, String message) {
        Alert alerte = new Alert(type);
        alerte.setTitle(titre);
        alerte.setHeaderText(null);
        alerte.setContentText(message);
        alerte.showAndWait();
    }
}