package fr.univorleans.iut45.briquiuto.IHM.Controlleurs.adminControlleurs;

import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.IHM.Controlleurs.AccueilControleur.AccueilControleur;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AccueilVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.admin.AdminCatalogueVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.admin.AdminHomeVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.admin.AjoutFigurineVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.admin.AjoutPieceVueAdmin;
import fr.univorleans.iut45.briquiuto.IHM.Vue.admin.ViewNewTheme;
import fr.univorleans.iut45.briquiuto.IHM.Vue.admin.*;
import fr.univorleans.iut45.briquiuto.IHM.Vue.admin.VueAjoutBoiteCatalogueAdmin;
import fr.univorleans.iut45.briquiuto.IHM.Vue.admin.VueStatistiquesBoite;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Contrôleur de la vue d'accueil de l'administrateur.
 * <p>
 * Gère les actions simples des boutons (ouvrir catalogue, ajouter des pièces,
 * thèmes, boîtes, figurines, afficher les statistiques et se déconnecter).
 * Commentaires écrits de façon simple, comme par un étudiant de BUT1.
 * Pour l'utilisation des classes JavaFX, je me suis aidé de la Javadoc officielle
 * (javafx.scene, javafx.stage) pour comprendre les méthodes de base.
 */
public class AdminHomeControleur {

    // La vue associée à ce contrôleur
    private AdminHomeVue vue;
    private RequetesLEGO modele;
    private Stage fenetrePrincipale;

    public AdminHomeControleur(AdminHomeVue vue, RequetesLEGO modele, Stage fenetrePrincipale) {
        this.vue = vue;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        this.initialiser();
    }

    /**
     * Initialise les handlers des boutons de la vue.
     * On crée de nouvelles vues/contrôleurs et on change la scène principale.
     * Méthode volontairement simple et lisible pour un projet de première année.
     */
    private void initialiser() {

        this.vue.getBtnCatalogue().setOnAction(e -> {
            AdminCatalogueVue vueCatalogue = new AdminCatalogueVue();
            new AdminCatalogueControleur(vueCatalogue, modele, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueCatalogue, 1000, 700));
        });

        this.vue.getBtnAjoutPiece().setOnAction(e -> {
            AjoutPieceVueAdmin vueAjout = new AjoutPieceVueAdmin();
            new AjoutPieceControleur(vueAjout, modele, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueAjout, 1000, 700));
        });

        this.vue.getBtnAjoutTheme().setOnAction(e -> {
            ViewNewTheme vueTheme = new ViewNewTheme();
            new ViewNewThemeControleur(vueTheme, modele, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueTheme, 1000, 700));
        });


        this.vue.getBtnAjoutBoite().setOnAction(e -> {
            VueAjoutBoiteCatalogueAdmin vueAjoutBoite = new VueAjoutBoiteCatalogueAdmin();
            new AjoutBoiteCatalogueControleur(vueAjoutBoite, modele, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueAjoutBoite, 1000, 700));
        });
        
        this.vue.getBtnFigurines().setOnAction(e -> {
            AjoutFigurineVue vueFigurine = new AjoutFigurineVue();
            new AjoutFigurineControleur(vueFigurine, modele, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueFigurine, 1000, 700));
        });

        this.vue.getBtnStatsAdmin().setOnAction(e -> {
            VueStatistiquesBoite vueStats = new VueStatistiquesBoite();
            new StatistiquesBoiteControleur(vueStats, modele, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueStats, 1000, 700)); 
        });

        this.vue.getBtnDeconnexion().setOnAction(e -> deconnexion());
        this.vue.getBtnHome().setOnAction(e -> deconnexion());
        // NOTE: Les handlers ci-dessus déconnectent et reviennent à l'accueil.
    }

    /**
     * Déconnecte l'utilisateur et revient à la vue d'accueil.
     * Crée une nouvelle `AccueilVue` et son contrôleur, puis change la scène.
     */
    private void deconnexion() {
        AccueilVue vueAccueil = new AccueilVue();
        new AccueilControleur(vueAccueil, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueAccueil, 1000, 700));
    }
}