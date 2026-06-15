package fr.univorleans.iut45.briquiuto.IHM.Controlleurs;

import java.sql.SQLException;

import fr.univorleans.iut45.briquiuto.modele.Theme;
import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.IHM.Vue.ViewNewTheme;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AccueilVue;
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
        // Action pour le bouton Valider
        this.vue.getValiderButton().setOnAction(event -> actionValiderTheme());

        // Action pour le bouton Home
        this.vue.getHomeButton().setOnAction(event -> actionRetourHome());
    }

    private void actionValiderTheme() {
        String numStr = vue.getNumThemeTextField().getText().trim();
        String nom = vue.getNomThemeTextField().getText().trim();
        String parentStr = vue.getNumThemeParentTextField().getText().trim();

        // 1. Vérification des champs obligatoires (Numéro et Nom)
        if (numStr.isEmpty() || nom.isEmpty()) {
            System.out.println("Erreur : Le numéro et le nom du thème sont obligatoires.");
            // Idéalement, affiche un Label d'erreur rouge sur la vue comme on a fait pour les pièces
            return;
        }

        try {
            int numTheme = Integer.parseInt(numStr);
            Theme parent = null;

            // 2. Gestion du thème parent optionnel
            if (!parentStr.isEmpty()) {
                int numParent = Integer.parseInt(parentStr);
                parent = modele.rechercherThemeParId(numParent); 
                
                if (parent == null) {
                    System.out.println("Erreur : Le thème parent numéro " + numParent + " n'existe pas.");
                    return; // On annule l'ajout si le parent spécifié n'existe pas
                }
            }

            // 3. Création du thème et insertion en base
            Theme nouveauTheme = new Theme(numTheme, nom, parent);
            modele.ajouterTheme(nouveauTheme);
            
            System.out.println("Succès : Thème ajouté avec succès !");
            
            // On vide le formulaire après un succès
            vue.getNumThemeTextField().clear();
            vue.getNomThemeTextField().clear();
            vue.getNumThemeParentTextField().clear();

        } catch (NumberFormatException e) {
            System.out.println("Erreur : Les numéros de thème doivent être des nombres entiers.");
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e.getMessage());
            // Souvent déclenché si l'ID (numTheme) est déjà utilisé (Clé primaire dupliquée)
        }
    }

    private void actionRetourHome() {
        System.out.println("Retour au menu Administrateur...");
        
        // On recrée la vue d'accueil et on l'affiche
        AccueilVue vueHome = new AccueilVue();
        
        // On affiche la nouvelle scène
        Scene sceneHome = new Scene(vueHome, 600, 500);
        fenetrePrincipale.setScene(sceneHome);
    }
}