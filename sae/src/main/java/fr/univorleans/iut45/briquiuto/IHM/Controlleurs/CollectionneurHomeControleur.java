package fr.univorleans.iut45.briquiuto.IHM.Controlleurs;

import fr.univorleans.iut45.briquiuto.IHM.Vue.VueRechercheBoiteParThemeCollectionneur;
import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AccueilVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.CollectionneurHomeVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.VueCompositionBoitePerso;
import fr.univorleans.iut45.briquiuto.IHM.Vue.VueRechercheBoiteParPiece;
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
   
        this.vue.getBtnRechercheParPiece().setOnAction(e -> ouvrirRechercheBoite());

        this.vue.getBtnExplorerParTheme().setOnAction(e -> {
            VueRechercheBoiteParThemeCollectionneur vueTheme = new VueRechercheBoiteParThemeCollectionneur();
            new RechercheBoiteThemeControleur(vueTheme, modele, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueTheme, 700, 550));
        });

        this.vue.getBtnDetailsBoite().setOnAction(e -> {
            System.out.println("À venir : Voir le contenu d'une boîte !");
        });

        this.vue.getBtnComposerBoite().setOnAction(e -> {
            VueCompositionBoitePerso vueCompo = new VueCompositionBoitePerso();
            new CompositionBoiteControleurPerso(vueCompo, modele, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueCompo, 850, 650)); // J'ai mis une fenêtre un peu plus large pour que tout tienne bien
        });

        this.vue.getBtnDeconnexion().setOnAction(e -> deconnexion());
        this.vue.getBtnHome().setOnAction(e -> deconnexion());
    }

    private void ouvrirRechercheBoite() {
        VueRechercheBoiteParPiece vueRecherche = new VueRechercheBoiteParPiece();
        new RechercheBoiteControleur(vueRecherche, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueRecherche, 700, 500));
    }

    private void deconnexion() {
        AccueilVue vueAccueil = new AccueilVue();
        new AccueilControleur(vueAccueil, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueAccueil, 600, 500));
    }
}