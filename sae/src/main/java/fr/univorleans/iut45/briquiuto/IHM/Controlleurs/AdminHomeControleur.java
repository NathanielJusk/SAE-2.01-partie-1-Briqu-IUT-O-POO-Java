package fr.univorleans.iut45.briquiuto.IHM.Controlleurs;

import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AdminHomeVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AjoutPieceVueAdmin;
import fr.univorleans.iut45.briquiuto.IHM.Vue.ViewNewTheme;
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
            new NewThemeControleur(vueTheme, modele, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueTheme, 1000, 700));
        });

        this.vue.getBtnAjoutBoite().setOnAction(e -> {
            System.out.println("À venir : Ajout de boîte !");
        });
        
        this.vue.getBtnFigurines().setOnAction(e -> {
            AjoutFigurineVue vueFigurine = new AjoutFigurineVue();
            new AjoutFigurineControleur(vueFigurine, modele, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueFigurine, 1000, 700));
        });

        // Ouvre les Statistiques tout simplement
        this.vue.getBtnStatsAdmin().setOnAction(e -> {
            VueStatistiquesBoite vueStats = new VueStatistiquesBoite();
            new StatistiquesBoiteControleur(vueStats, modele, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueStats, 1000, 700)); 
        });

        this.vue.getBtnDeconnexion().setOnAction(e -> deconnexion());
        this.vue.getBtnHome().setOnAction(e -> deconnexion());
    }

    private void deconnexion() {
        AccueilVue vueAccueil = new AccueilVue();
        new AccueilControleur(vueAccueil, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueAccueil, 1000, 700));
    }
}