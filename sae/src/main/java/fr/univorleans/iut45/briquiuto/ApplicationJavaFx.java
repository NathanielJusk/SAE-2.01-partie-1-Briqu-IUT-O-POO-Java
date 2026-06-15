package fr.univorleans.iut45.briquiuto;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
// L'import manquant pour dire à Java où aller chercher votre vue
import fr.univorleans.iut45.briquiuto.IHM.Vue.ViewNewTheme; 

public class ApplicationJavaFx extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 1. Instancier votre vue corrigée
        ViewNewTheme root = new ViewNewTheme();

        // 2. Créer une scène en lui donnant votre vue et des dimensions
        Scene scene = new Scene(root, 500, 400);

        // 3. Configurer le Stage (la fenêtre principale)
        primaryStage.setTitle("Briqu'IUT - Nouveau Thème");
        primaryStage.setScene(scene);
        
        // 4. Afficher la fenêtre
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}