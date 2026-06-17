package fr.univorleans.iut45.briquiuto.IHM.Controlleurs;

import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AdminHomeVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AjoutPieceVueAdmin;
import fr.univorleans.iut45.briquiuto.IHM.Vue.ViewNewTheme;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AdminCatalogueVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AccueilVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AjoutFigurineVue;
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

        // 1. Bouton "Consulter le catalogue global" (NOUVEAU)
        this.vue.getBtnCatalogue().setOnAction(e -> {
            AdminCatalogueVue vueCatalogue = new AdminCatalogueVue();
            new AdminCatalogueControleur(vueCatalogue, modele, fenetrePrincipale);
            // On ouvre une fenêtre un peu plus grande (800x600) pour bien voir les tableaux
            fenetrePrincipale.setScene(new Scene(vueCatalogue, 1000, 700));
        });

        // 2. Bouton "Insérer une nouvelle Pièce"
        this.vue.getBtnAjoutPiece().setOnAction(e -> {
            AjoutPieceVueAdmin vueAjout = new AjoutPieceVueAdmin();
            new AjoutPieceControleur(vueAjout, modele, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueAjout, 1000, 700));
        });

        // 3. Bouton "Créer un nouveau Thème"
        this.vue.getBtnAjoutTheme().setOnAction(e -> {
            ViewNewTheme vueTheme = new ViewNewTheme();
            new NewThemeControleur(vueTheme, modele, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueTheme, 1000, 700));
        });

        // 4. Bouton "Ajouter une Boîte"
        this.vue.getBtnAjoutBoite().setOnAction(e -> {
            System.out.println("À venir : Ajout de boîte !");
        });
        // 5. Bouton "Ajouter une Figurine"
        this.vue.getBtnFigurines().setOnAction(e -> {
            AjoutFigurineVue vueFigurine = new AjoutFigurineVue();
            new AjoutFigurineControleur(vueFigurine, modele, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueFigurine, 1000, 700));
        });

        // 5. Bouton "Déconnexion"
        this.vue.getBtnDeconnexion().setOnAction(e -> deconnexion());
        this.vue.getBtnHome().setOnAction(e -> deconnexion());
    }

    private void deconnexion() {
        // Retour propre à la page d'accueil principale
        AccueilVue vueAccueil = new AccueilVue();
        new AccueilControleur(vueAccueil, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueAccueil, 1000, 700));
    }
}