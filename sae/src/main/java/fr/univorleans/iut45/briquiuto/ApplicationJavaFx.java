package fr.univorleans.iut45.briquiuto;

import fr.univorleans.iut45.briquiuto.JDBC.ConnexionBD;
import fr.univorleans.iut45.briquiuto.IHM.Controlleurs.ConnexionControlleurs.ConnexionBDControleur;
import fr.univorleans.iut45.briquiuto.IHM.Vue.Connexion.ConnexionBDVue;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ApplicationJavaFx extends Application {

    private ConnexionBD connexion;

    @Override
    public void init() throws Exception {
        System.out.println("Initialisation du driver de la Base de données...");
        try {
            // On prépare juste l'objet, on ne se connecte pas encore !
            connexion = new ConnexionBD(); 
        } catch (Exception e) {
            System.out.println("Erreur Driver : " + e.getMessage());
        }
    }

    @Override
    public void start(Stage primaryStage) {
        // L'application démarre maintenant sur la page de connexion à la Base de Données
        ConnexionBDVue vueInitiale = new ConnexionBDVue();
        new ConnexionBDControleur(vueInitiale, connexion, primaryStage);

        Scene scene = new Scene(vueInitiale, 600, 500); 
        
        primaryStage.setTitle("Briqu'IUT-O - Connexion Système");
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