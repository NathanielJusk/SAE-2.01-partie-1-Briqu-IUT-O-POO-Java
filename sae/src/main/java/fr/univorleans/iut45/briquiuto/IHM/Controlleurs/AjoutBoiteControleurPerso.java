package fr.univorleans.iut45.briquiuto.IHM.Controlleurs;

import java.sql.SQLException;

import fr.univorleans.iut45.briquiuto.IHM.Vue.admin.AdminHomeVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.collec.ViewNewBoitePerso;
import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.modele.Boite;
import fr.univorleans.iut45.briquiuto.modele.BoiteComposee;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Contrôleur pour ajouter une boîte personnalisée (vue collectionneur).
 * Valide le formulaire, crée une `BoiteComposee` et envoie au modèle.
 */
public class AjoutBoiteControleurPerso {

    // Vue du formulaire de nouvelle boîte perso
    private ViewNewBoitePerso vue;
    private RequetesLEGO modele;
    private Stage fenetrePrincipale;

    public AjoutBoiteControleurPerso(ViewNewBoitePerso vue, RequetesLEGO modele, Stage fenetrePrincipale) {
        this.vue = vue;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        this.initialiser();
    }

    /**
     * Fixe les actions pour valider la création ou retourner à l'accueil.
     */
    public void initialiser() {
        vue.getValiderButton().setOnAction(event -> handleValiderBoite());
        vue.getHomeButton().setOnAction(event -> actionRetourAdmin());
    }

    private void actionRetourAdmin() {
        AdminHomeVue vueAdmin = new AdminHomeVue();
        new AdminHomeControleur(vueAdmin, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueAdmin, 1000, 700));
    }

    private void handleValiderBoite() {
        String numero = vue.getNumTextField().getText().trim();
        String nom = vue.getNomTextField().getText().trim();
        String anneeStr = vue.getAnneeTextField().getText().trim();
        String piecesStr = vue.getNbPiecesTextField().getText().trim();

        // 1. Validation des champs vides
        if (numero.isEmpty() || nom.isEmpty() || anneeStr.isEmpty() || piecesStr.isEmpty()) {
            vue.afficherMessage("Veuillez remplir tous les champs.", Color.RED);
            return;
        }

        try {
            // 2. Conversions numériques
            int annee = Integer.parseInt(anneeStr);
            int nbPieces = Integer.parseInt(piecesStr);

            // 3. Instanciation
            Boite nouvelleBoite = new BoiteComposee(numero, nbPieces, nom, annee);

            // 4. Envoi au modèle JDBC
            modele.ajouterBoite(nouvelleBoite);

            // 5. Nettoyage et succès
            vue.reinitialiserFormulaire();
            vue.afficherMessage("La boîte composée a été ajoutée avec succès !", Color.GREEN);

        } catch (NumberFormatException e) {
            vue.afficherMessage("L'année et le nombre de pièces doivent être des nombres.", Color.RED);
        } catch (SQLException e) {
            // --- POP-UP NUMÉRO DE BOÎTE EN ERREUR / DÉJÀ PRIS ---
            vue.afficherMessage("Erreur : Numéro de boîte déjà pris ou invalide !", Color.RED);
            
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Numéro de boîte invalide");
            alert.setContentText("Le numéro de boîte spécifié est déjà utilisé ou incorrect. Veuillez en choisir un autre.");
            alert.showAndWait();
        }
    }
}