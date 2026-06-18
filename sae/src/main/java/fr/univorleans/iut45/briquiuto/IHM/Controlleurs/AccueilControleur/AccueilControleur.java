package fr.univorleans.iut45.briquiuto.IHM.Controlleurs.AccueilControleur;

import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.IHM.Controlleurs.ConnexionControlleurs.ConnexionControleur;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AccueilVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.Connexion.VueConnexion;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Contrôleur de la vue d'accueil de l'application.
 * Gère les boutons pour accéder à la partie collection, à l'administration
 * et pour quitter l'application. Commentaires rédigés simplement par un
 * étudiant de BUT1. Pour JavaFX, je me suis aidé de la Javadoc officielle.
 */
public class AccueilControleur {

    // Vue principale d'accueil
    private AccueilVue vue;
    private RequetesLEGO modele;
    private Stage fenetrePrincipale;

    public AccueilControleur(AccueilVue vue, RequetesLEGO modele, Stage fenetrePrincipale) {
        this.vue = vue;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        
        this.initialiser();
    }

    /**
     * Initialise les handlers des boutons de la page d'accueil.
     */
    private void initialiser() {
        // Les deux boutons ouvrent la même page de connexion
        this.vue.getBtnCollection().setOnAction(event -> ouvrirPageConnexion());
        this.vue.getBtnAdmin().setOnAction(event -> ouvrirPageConnexion());

        this.vue.getBtnQuit().setOnAction(event -> actionQuitter());
    }

    // Méthode commune pour ouvrir le formulaire de connexion
    /**
     * Ouvre la vue de connexion (formulaire de login).
     */
    private void ouvrirPageConnexion() {
        System.out.println("Ouverture de la page de connexion...");

        VueConnexion vueLogin = new VueConnexion();
        new ConnexionControleur(vueLogin, modele, fenetrePrincipale);

        // Affiche la page de connexion
        fenetrePrincipale.setScene(new Scene(vueLogin, 1000, 700));
    }

    /**
     * Quitte l'application proprement.
     */
    private void actionQuitter() {
        System.out.println("Fermeture de l'application demandée.");
        Platform.exit();
    }
}