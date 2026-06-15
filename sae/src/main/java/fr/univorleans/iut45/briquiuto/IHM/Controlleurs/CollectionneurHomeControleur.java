package fr.univorleans.iut45.briquiuto.IHM.Controlleurs;

import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.IHM.Vue.*;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class CollectionneurHomeControleur {

    private CollectionneurHomeVue vue;
    private RequetesLEGO modele;
    private Stage fenetrePrincipale;

    public CollectionneurHomeControleur(CollectionneurHomeVue vue, RequetesLEGO modele, Stage fenetrePrincipale) {
        this.vue = vue;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        this.initialiser();
    }

    private void initialiser() {
        this.vue.getBtnMaCollection().setOnAction(e -> {
            System.out.println("Ouverture de la page : Ma Collection");
        });

        this.vue.getBtnRechercheBoite().setOnAction(e -> {
            System.out.println("Ouverture de la page : Recherche de boîtes");
        });

        this.vue.getBtnCompoBoite().setOnAction(e -> {
            System.out.println("Ouverture de la page : Composition de boîte");
        });

        this.vue.getBtnDeconnexion().setOnAction(e -> deconnexion());
    }

    private void deconnexion() {
        AccueilVue vueAccueil = new AccueilVue();
        new AccueilControleur(vueAccueil, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueAccueil, 600, 500));
    }
}