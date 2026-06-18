package fr.univorleans.iut45.briquiuto.IHM.Controlleurs.adminControlleurs;

import java.sql.SQLException;
import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.modele.Figurine;
import fr.univorleans.iut45.briquiuto.IHM.Vue.admin.AjoutFigurineVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.admin.AdminHomeVue;
import fr.univorleans.iut45.briquiuto.IHM.Controlleurs.AccueilControleur.AccueilControleur;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AccueilVue;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Contrôleur pour ajouter une nouvelle figurine depuis la vue admin.
 * Valide le formulaire et demande au modèle d'enregistrer la figurine.
 */
public class AjoutFigurineControleur {

    // Vue pour ajouter une figurine
    private AjoutFigurineVue vue;
    private RequetesLEGO modele;
    private Stage fenetrePrincipale;

    public AjoutFigurineControleur(AjoutFigurineVue vue, RequetesLEGO modele, Stage fenetrePrincipale) {
        this.vue = vue;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        this.initialiser();
    }

    /**
     * Initialise les actions des boutons pour la vue d'ajout de figurine.
     */
    private void initialiser() {
        vue.getBtnValider().setOnAction(e -> actionValiderFigurine());
        vue.getBtnRetour().setOnAction(e -> actionRetourAdmin());
        vue.getBtnHome().setOnAction(e -> actionRetourAccueil());
    }

    /**
     * Lit le formulaire, crée une `Figurine` et demande au modèle de l'ajouter.
     * Gère les erreurs d'entrée simples et les erreurs SQL basiques.
     */
    private void actionValiderFigurine() {
        String idFig = vue.getTxtIdFigurine().getText().trim();
        String nom = vue.getTxtNomFigurine().getText().trim();
        String nbPartiesStr = vue.getTxtNbParties().getText().trim();

        if (idFig.isEmpty() || nom.isEmpty() || nbPartiesStr.isEmpty()) {
            vue.afficherMessage("Veuillez remplir tous les champs !", "red");
            return;
        }

        try {
            int nbParties = Integer.parseInt(nbPartiesStr);
            Figurine f = new Figurine(idFig, nom, nbParties);
            
            // Assure-toi d'avoir cette méthode dans RequetesLEGO
            modele.ajouterFigurine(f);
            
            vue.afficherMessage("Figurine ajoutée avec succès !", "green");
            vue.reinitialiserFormulaire();
        } catch (NumberFormatException ex) {
            vue.afficherMessage("Le nombre de parties doit être un entier.", "red");
        } catch (SQLException ex) {
            vue.afficherMessage("Erreur BD : Identifiant peut-être déjà utilisé.", "red");
        }
    }

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