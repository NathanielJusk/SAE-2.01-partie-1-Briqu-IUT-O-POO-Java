package fr.univorleans.iut45.briquiuto.IHM.Controlleurs;

import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AccueilVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.CollectionneurHomeVue;
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
        // 1. Bouton "Rechercher par pièce" (Lien vers la vue de Mourad)
        this.vue.getBtnRechercheParPiece().setOnAction(e -> ouvrirRechercheBoite());

        // 2. Bouton "Explorer par Thème"
        this.vue.getBtnExplorerParTheme().setOnAction(e -> {
            System.out.println("À venir : Exploration par thème !");
        });

        // 3. Bouton "Détails d'une Boîte"
        this.vue.getBtnDetailsBoite().setOnAction(e -> {
            System.out.println("À venir : Voir le contenu d'une boîte !");
        });

        // 4. Bouton "Composer une boîte"
        this.vue.getBtnComposerBoite().setOnAction(e -> {
            System.out.println("À venir : Composition d'une boîte personnalisée !");
        });

        // 5. Actions de retour et déconnexion
        this.vue.getBtnDeconnexion().setOnAction(e -> deconnexion());
        this.vue.getBtnHome().setOnAction(e -> deconnexion());
    }

    private void ouvrirRechercheBoite() {
        VueRechercheBoiteParPiece vueRecherche = new VueRechercheBoiteParPiece();
        new RechercheBoiteControleur(vueRecherche, modele, fenetrePrincipale);
        // On affiche la page de recherche (un peu plus large pour le tableau)
        fenetrePrincipale.setScene(new Scene(vueRecherche, 700, 500));
    }

    private void deconnexion() {
        AccueilVue vueAccueil = new AccueilVue();
        new AccueilControleur(vueAccueil, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueAccueil, 600, 500));
    }
}