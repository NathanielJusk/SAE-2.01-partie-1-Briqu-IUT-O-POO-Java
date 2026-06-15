package fr.univorleans.iut45.briquiuto;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ApplicationJavaFx extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 1. Créer le composant racine (le conteneur principal)
        StackPane root = new StackPane();
        
        // 2. Créer un texte et l'ajouter au conteneur
        Label label = new Label("Bonjour JavaFX 100% Java !");
        root.getChildren().add(label);

        // 3. Créer la scène (qui contient la racine)
        Scene scene = new Scene(root, 400, 300);

        // 4. Configurer la fenêtre (Stage) et l'afficher
        primaryStage.setTitle("Ma Super Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Lance l'application JavaFX
    }
}