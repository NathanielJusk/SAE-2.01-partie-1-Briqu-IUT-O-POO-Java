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
        this.vue.getBtnHome().setOnAction(e -> deconnexion());
    }

    // On ne garde que la BONNE version de cette méthode
    private void ouvrirAjoutPiece() {
        AjoutPieceVue vuePiece = new AjoutPieceVue();
        // Le contrôleur a bien ses 3 paramètres pour pouvoir faire des retours en arrière !
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