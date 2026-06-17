package fr.univorleans.iut45.briquiuto.IHM.Controlleurs;

import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AdminHomeVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AjoutPieceVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.ViewNewTheme;
import fr.univorleans.iut45.briquiuto.IHM.Vue.VueAjoutBoiteCatalogueAdmin;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AdminCatalogueVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AccueilVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AjoutFigurineVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.VueStatistiquesBoite;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminHomeControleur {

    private AdminHomeVue vue;
    private RequetesLEGO modele;
    private Stage fenetrePrincipale;

    public AdminHomeControleur(AdminHomeVue vue, RequetesLEGO modele, Stage fenetrePrincipale) {
        this.vue = vue;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        this.initialiser();
    }

    private void initialiser() {

        // 1. Bouton "Consulter le catalogue global"
        this.vue.getBtnCatalogue().setOnAction(e -> {
            AdminCatalogueVue vueCatalogue = new AdminCatalogueVue();
            new AdminCatalogueControleur(vueCatalogue, modele, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueCatalogue, 800, 600));
        });

        // 2. Bouton "Insérer une nouvelle Pièce"
        this.vue.getBtnAjoutPiece().setOnAction(e -> {
            AjoutPieceVue vueAjout = new AjoutPieceVue();
            new AjoutPieceControleur(vueAjout, modele, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueAjout, 600, 500));
        });

        // 3. Bouton "Créer un nouveau Thème"
        this.vue.getBtnAjoutTheme().setOnAction(e -> {
            ViewNewTheme vueTheme = new ViewNewTheme();
            new NewThemeControleur(vueTheme, modele, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueTheme, 600, 500));
        });

        // 4. Bouton "Ajouter une Boîte" -> Redirige vers ta vue catalogue officielle
        this.vue.getBtnAjoutBoite().setOnAction(e -> {
            VueAjoutBoiteCatalogueAdmin vueAjoutCatalogue = new VueAjoutBoiteCatalogueAdmin();
            new AjoutBoiteCatalogueControleur(vueAjoutCatalogue, modele, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueAjoutCatalogue, 750, 600));
        });

        // 5. Bouton "Ajouter une Figurine"
        this.vue.getBtnFigurines().setOnAction(e -> {
            AjoutFigurineVue vueFigurine = new AjoutFigurineVue();
            new AjoutFigurineControleur(vueFigurine, modele, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueFigurine, 600, 500));
        });

        // 6. CONNECTEUR DU NOUVEAU BOUTON : "Afficher les Statistiques"
        this.vue.getBtnStatsAdmin().setOnAction(e -> {
            VueStatistiquesBoite vueStats = new VueStatistiquesBoite();
            
            // On instancie le contrôleur de statistiques qu'on a créé ensemble
            new StatistiquesBoiteControleur(vueStats, modele, fenetrePrincipale);
            
            // Pour l'ergonomie, on modifie à la volée l'action du bouton Home de la page de stats 
            // pour qu'il revienne bien chez l'ADMIN et pas chez le collectionneur !
            vueStats.getBtnHome().setOnAction(event -> {
                AdminHomeVue homeAdmin = new AdminHomeVue();
                new AdminHomeControleur(homeAdmin, modele, fenetrePrincipale);
                fenetrePrincipale.setScene(new Scene(homeAdmin, 600, 500));
            });

            fenetrePrincipale.setScene(new Scene(vueStats, 750, 600));
        });

        // 7. Bouton "Déconnexion"
        this.vue.getBtnDeconnexion().setOnAction(e -> deconnexion());
        this.vue.getBtnHome().setOnAction(e -> deconnexion());
    }

    private void deconnexion() {
        AccueilVue vueAccueil = new AccueilVue();
        new AccueilControleur(vueAccueil, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueAccueil, 600, 500));
    }
}