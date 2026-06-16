package fr.univorleans.iut45.briquiuto.IHM.Controlleurs;

import java.sql.SQLException;

import fr.univorleans.iut45.briquiuto.modele.Theme;
import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.IHM.Vue.ViewNewTheme;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AdminHomeVue; // Changement ici !
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NewThemeControleur {

    private ViewNewTheme vue;
    private RequetesLEGO modele;
    private Stage fenetrePrincipale;

    public NewThemeControleur(ViewNewTheme vue, RequetesLEGO modele, Stage fenetrePrincipale) {
        this.vue = vue;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        
        this.initialiser();
    }

    private void initialiser() {
        this.vue.getValiderButton().setOnAction(event -> actionValiderTheme());
        
        // Action pour le bouton Home
        this.vue.getHomeButton().setOnAction(event -> actionRetourAdmin());
    }

    private void actionValiderTheme() {
        String numStr = vue.getNumThemeTextField().getText().trim();
        String nom = vue.getNomThemeTextField().getText().trim();
        String parentStr = vue.getNumThemeParentTextField().getText().trim();

        if (numStr.isEmpty() || nom.isEmpty()) {
            System.out.println("Erreur : Le numéro et le nom du thème sont obligatoires.");
            return;
        }

        try {
            int numTheme = Integer.parseInt(numStr);
            Theme parent = null;

            if (!parentStr.isEmpty()) {
                int numParent = Integer.parseInt(parentStr);
                parent = modele.rechercherThemeParId(numParent); 
                
                if (parent == null) {
                    System.out.println("Erreur : Le thème parent numéro " + numParent + " n'existe pas.");
                    return; 
                }
            }

            Theme nouveauTheme = new Theme(numTheme, nom, parent);
            modele.ajouterTheme(nouveauTheme);
            
            System.out.println("Succès : Thème ajouté avec succès !");
            
            vue.getNumThemeTextField().clear();
            vue.getNomThemeTextField().clear();
            vue.getNumThemeParentTextField().clear();

        } catch (NumberFormatException e) {
            System.out.println("Erreur : Les numéros de thème doivent être des nombres entiers.");
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e.getMessage());
        }
    }

    // CORRECTION DU RETOUR ICI !
    private void actionRetourAdmin() {
        System.out.println("Retour au menu Administrateur...");
        
        // 1. On recrée la vue Admin
        AdminHomeVue vueAdmin = new AdminHomeVue();
        
        // 2. LA MAGIE EST ICI : On DOIT lier le contrôleur pour réveiller les boutons !
        new AdminHomeControleur(vueAdmin, modele, fenetrePrincipale);
        
        // 3. On affiche la nouvelle scène
        Scene sceneAdmin = new Scene(vueAdmin, 600, 500);
        fenetrePrincipale.setScene(sceneAdmin);
    }
}