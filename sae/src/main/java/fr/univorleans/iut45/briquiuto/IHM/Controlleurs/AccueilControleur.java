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

    // Constructeur MVC
    public AccueilControleur(AccueilVue vue, RequetesLEGO modele, Stage fenetrePrincipale) {
        this.vue = vue;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        
        this.initialiser();
    }

    // Initialisation des écouteurs d'événements
    private void initialiser() {
        this.vue.getBtnCollection().setOnAction(event -> actionCollection());
        this.vue.getBtnAdmin().setOnAction(event -> actionAdmin());
        this.vue.getBtnQuit().setOnAction(event -> actionQuitter());
    }

    // --- METHODES D'ACTIONS ---

    private void actionCollection() {
        System.out.println("Redirection vers la Collection Personnelle...");
        // Ici, tu pourras charger la vue du Collectionneur plus tard
        // Exemple :
        // VueCollection vueCol = new VueCollection();
        // new CollectionControleur(vueCol, modele, fenetrePrincipale);
        // fenetrePrincipale.setScene(new Scene(vueCol, 600, 500));
    }

    private void actionAdmin() {
        System.out.println("Redirection vers l'espace Administration...");
        
        // On charge la page de connexion sécurisée !
        VueConnexion vueLogin = new VueConnexion();
        new ConnexionControleur(vueLogin, modele, fenetrePrincipale);
        
        Scene sceneLogin = new Scene(vueLogin, 400, 400);
        fenetrePrincipale.setScene(sceneLogin);
    }

    private void actionQuitter() {
        System.out.println("Fermeture de l'application demandée.");
        // Platform.exit() est la méthode propre en JavaFX pour fermer l'application
        Platform.exit();
    }
}