package fr.univorleans.iut45.briquiuto.IHM.Controlleurs;

import fr.univorleans.iut45.briquiuto.IHM.Vue.VueRechercheBoiteParThemeCollectionneur;
import fr.univorleans.iut45.briquiuto.IHM.Vue.VueStatistiquesBoite;
import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AccueilVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.CollectionneurHomeVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.VueRechercheBoiteParPiece;
import fr.univorleans.iut45.briquiuto.IHM.Vue.VueCompositionBoitePerso;
import fr.univorleans.iut45.briquiuto.IHM.Vue.VueMesMOCs;
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
        // 1. Bouton "Rechercher par pièce" (Lien vers la vue de Mourad)
        this.vue.getBtnRechercheParPiece().setOnAction(e -> ouvrirRechercheBoite());

        // 2. Bouton "Explorer par Thème"
        this.vue.getBtnExplorerParTheme().setOnAction(e -> {
            System.out.println("À venir : Exploration par thème !");
        });

        // 3. Bouton "Détails d'une Boîte"
        this.vue.getBtnDetailsBoite().setOnAction(e -> {
            VueStatistiquesBoite vueStats = new VueStatistiquesBoite();
            new StatistiquesBoiteControleur(vueStats, modele, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueStats, 750, 600));
        });

        // 4. Bouton "Composer une boîte"
        this.vue.getBtnComposerBoite().setOnAction(e -> {
            VueCompositionBoitePerso vueCompo = new VueCompositionBoitePerso();
            new CompositionBoiteControleurPerso(vueCompo, modele, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueCompo, 850, 650));
        });

        // --- NOUVEAU BOUTON VERS MES MOCS ---
        this.vue.getBtnMesMOCs().setOnAction(e -> {
            VueMesMOCs vueMOCs = new VueMesMOCs();
            new MesMOCsControleur(vueMOCs, modele, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueMOCs, 800, 600));
        });

        // 5. Actions de retour et déconnexion
        this.vue.getBtnDeconnexion().setOnAction(e -> deconnexion());
        this.vue.getBtnHome().setOnAction(e -> deconnexion());
    }

    private void ouvrirRechercheBoite() {
        VueRechercheBoiteParPiece vueRecherche = new VueRechercheBoiteParPiece();
        new RechercheBoiteControleur(vueRecherche, modele, fenetrePrincipale);
        // On affiche la page de recherche (un peu plus large pour le tableau)
        fenetrePrincipale.setScene(new Scene(vueRecherche, 1000, 700));
    }

    private void deconnexion() {
        AccueilVue vueAccueil = new AccueilVue();
        new AccueilControleur(vueAccueil, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueAccueil, 1000, 700));
    }
}