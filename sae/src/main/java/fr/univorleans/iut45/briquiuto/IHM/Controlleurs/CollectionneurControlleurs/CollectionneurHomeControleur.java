package fr.univorleans.iut45.briquiuto.IHM.Controlleurs.CollectionneurControlleurs;

import fr.univorleans.iut45.briquiuto.IHM.Vue.collec.CollectionneurHomeVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.collec.VueCompositionBoitePerso;
import fr.univorleans.iut45.briquiuto.IHM.Vue.collec.VueMesMOCs;
import fr.univorleans.iut45.briquiuto.IHM.Vue.collec.VueRechercheBoiteParNumero;
import fr.univorleans.iut45.briquiuto.IHM.Vue.collec.VueRechercheBoiteParPiece;
import fr.univorleans.iut45.briquiuto.IHM.Vue.collec.VueRechercheBoiteParThemeCollectionneur;
import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AccueilVue;
import fr.univorleans.iut45.briquiuto.IHM.Controlleurs.AccueilControleur.AccueilControleur;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Contrôleur principal pour le collectionneur.
 * Dirige vers les sous-vues : recherche par pièce, thème, numéro,
 * composition de boîte et liste des MOCs.
 */
public class CollectionneurHomeControleur {

    // Vue principale pour le collectionneur
    private CollectionneurHomeVue vue;
    private RequetesLEGO modele;
    private Stage fenetrePrincipale;

    public CollectionneurHomeControleur(CollectionneurHomeVue vue, RequetesLEGO modele, Stage fenetrePrincipale) {
        this.vue = vue;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        this.initialiser();
    }

    /**
     * Initialise les handlers des boutons du menu collectionneur.
     */
    private void initialiser() {
        // 1. Bouton "Rechercher par pièce" (Lien vers la vue de Mourad)
        this.vue.getBtnRechercheParPiece().setOnAction(e -> ouvrirRechercheBoite());

        // 2. Bouton "Explorer par Thème" (Correction : Utilisation du bon Contrôleur de recherche)
        this.vue.getBtnExplorerParTheme().setOnAction(e -> {
            VueRechercheBoiteParThemeCollectionneur vueRechercheTheme = new VueRechercheBoiteParThemeCollectionneur();
            new RechercheBoiteThemeControleur(vueRechercheTheme, modele, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueRechercheTheme, 1000, 700));
        });

        // 3. Bouton "Détails d'une Boîte" (Correction : Page du collectionneur, pas celle de l'Admin !)
        this.vue.getBtnDetailsBoite().setOnAction(e -> {
            VueRechercheBoiteParNumero vueRechercheNum = new VueRechercheBoiteParNumero();
            new RechercheBoiteNumControleur(vueRechercheNum, modele, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueRechercheNum, 1000, 700));
        });

        // 4. Bouton "Composer une boîte"
        this.vue.getBtnComposerBoite().setOnAction(e -> {
            VueCompositionBoitePerso vueCompo = new VueCompositionBoitePerso();
            new CompositionBoiteControleurPerso(vueCompo, modele, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueCompo, 1000, 900)); // Standardisé à 1000x700
        });

        // 5. Bouton "Mes MOCs"
        this.vue.getBtnMesMOCs().setOnAction(e -> {
            VueMesMOCs vueMOCs = new VueMesMOCs();
            new MesMOCsControleur(vueMOCs, modele, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueMOCs, 1000, 900)); 
        });

        // 6. Actions de retour et déconnexion
        this.vue.getBtnDeconnexion().setOnAction(e -> deconnexion());
        this.vue.getBtnHome().setOnAction(e -> deconnexion());
    }

    private void ouvrirRechercheBoite() {
        VueRechercheBoiteParPiece vueRecherche = new VueRechercheBoiteParPiece();
        new RechercheBoiteControleur(vueRecherche, modele, fenetrePrincipale);
        // On affiche la page de recherche standardisée
        fenetrePrincipale.setScene(new Scene(vueRecherche, 1000, 700));
    }

    private void deconnexion() {
        AccueilVue vueAccueil = new AccueilVue();
        new AccueilControleur(vueAccueil, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueAccueil, 1000, 700));
    }
}
