package fr.univorleans.iut45.briquiuto.IHM.Controlleurs;

import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.IHM.Vue.*;
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
        this.vue.getBtnAjoutPiece().setOnAction(e -> ouvrirAjoutPiece());
        this.vue.getBtnAjoutTheme().setOnAction(e -> ouvrirAjoutTheme());
        this.vue.getBtnDeconnexion().setOnAction(e -> deconnexion());
        
        this.vue.getBtnAjoutBoite().setOnAction(e -> {
            System.out.println("Fonctionnalité 'Gérer les boîtes' à venir !");
        });
        
        // Le bouton Home fait la même action que la déconnexion pour l'admin
        this.vue.getBtnHome().setOnAction(e -> deconnexion());
    }

    private void ouvrirAjoutPiece() {
        AjoutPieceVue vuePiece = new AjoutPieceVue();
        new AjoutPieceControleur(vuePiece, modele, fenetrePrincipale); 
        fenetrePrincipale.setScene(new Scene(vuePiece, 600, 500));
    }

    private void ouvrirAjoutTheme() {
        ViewNewTheme vueTheme = new ViewNewTheme();
        new NewThemeControleur(vueTheme, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueTheme, 600, 500));
    }

    private void deconnexion() {
        AccueilVue vueAccueil = new AccueilVue();
        new AccueilControleur(vueAccueil, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueAccueil, 600, 500));
    }
}