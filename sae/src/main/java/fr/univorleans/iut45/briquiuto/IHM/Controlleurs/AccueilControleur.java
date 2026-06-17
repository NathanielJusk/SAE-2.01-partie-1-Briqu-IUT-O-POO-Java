package fr.univorleans.iut45.briquiuto.IHM.Controlleurs;

import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AccueilVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.VueConnexion;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AccueilControleur {

    private AccueilVue vue;
    private RequetesLEGO modele;
    private Stage fenetrePrincipale;

    public AccueilControleur(AccueilVue vue, RequetesLEGO modele, Stage fenetrePrincipale) {
        this.vue = vue;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        
        this.initialiser();
    }

    private void initialiser() {
        // Les deux boutons ouvrent maintenant la même page de connexion !
        this.vue.getBtnCollection().setOnAction(event -> ouvrirPageConnexion());
        this.vue.getBtnAdmin().setOnAction(event -> ouvrirPageConnexion());
        
        this.vue.getBtnQuit().setOnAction(event -> actionQuitter());
    }

    // Méthode commune pour ouvrir le formulaire de connexion
    private void ouvrirPageConnexion() {
        System.out.println("Ouverture de la page de connexion...");
        
        VueConnexion vueLogin = new VueConnexion();
        new ConnexionControleur(vueLogin, modele, fenetrePrincipale);
        
        // On affiche la page de connexion
        fenetrePrincipale.setScene(new Scene(vueLogin, 1000, 700));
    }

    private void actionQuitter() {
        System.out.println("Fermeture de l'application demandée.");
        Platform.exit();
    }
}