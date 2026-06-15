package fr.univorleans.iut45.briquiuto;

import fr.univorleans.iut45.briquiuto.JDBC.ConnexionBD;
import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.modele.BriqueCollectionManager;
import fr.univorleans.iut45.briquiuto.IHM.Controlleurs.AccueilControleur;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AccueilVue;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ApplicationJavaFx extends Application {

    private RequetesLEGO requetesModel;
    private ConnexionBD connexion;

    @Override
    public void init() throws Exception {
        System.out.println("Initialisation du Modèle (Base de données)...");
        try {
            connexion = new ConnexionBD();
            // N'oublie pas de vérifier tes identifiants locaux ici
            connexion.connecter("localhost", "lego", "root", ""); 
            
            BriqueCollectionManager manager = new BriqueCollectionManager();
            requetesModel = new RequetesLEGO(connexion, manager);
            System.out.println("Connexion à la base de données réussie !");
            
        } catch (Exception e) {
            System.out.println("Attention : Base de données non connectée. Erreur : " + e.getMessage());
        }
    }

    @Override
    public void start(Stage primaryStage) {
        AccueilVue vueAccueil = new AccueilVue();
        new AccueilControleur(vueAccueil, requetesModel, primaryStage);

        Scene scene = new Scene(vueAccueil, 600, 500); 
        
        primaryStage.setTitle("Briqu'IUT-O - Accueil Principal");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        if (connexion != null && connexion.isConnecte()) {
            connexion.close();
            System.out.println("Base de données déconnectée proprement. Au revoir !");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}