package fr.univorleans.iut45.briquiuto.IHM.Controlleurs;

import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.IHM.Vue.VueConnexion;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AdminHomeVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.CollectionneurHomeVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AccueilVue;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ConnexionControlleur {

    private VueConnexion vue;
    private RequetesLEGO modele;
    private Stage fenetrePrincipale; 

    public ConnexionControlleur(VueConnexion vue, RequetesLEGO modele, Stage fenetrePrincipale) {
        this.vue = vue;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        
        this.initialiser();
    }

    public void initialiser() {
        this.vue.getBtnSeConnecter().setOnAction(event -> actionConnexion());
        
        // Action pour le bouton Home (l'icÃ´ne de maison)
        this.vue.getBtnHome().setOnAction(event -> actionRetourAccueil());
    }

    private void actionRetourAccueil() {
        AccueilVue vueAccueil = new AccueilVue();
        new AccueilControleur(vueAccueil, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueAccueil, 600, 500));
    }

    public void actionConnexion() {
        String login = vue.getTxtIdentifiant().getText().trim();
        String mdp = vue.getTxtMotDePasse().getText().trim();

        // 1. ROLE : ADMINISTRATEUR
        if (login.equals("admin") && mdp.equals("admin")) {
            System.out.println("Connexion rÃ©ussie : Bienvenue Administrateur !");
            
            AdminHomeVue vueAdmin = new AdminHomeVue();
            new AdminHomeControleur(vueAdmin, modele, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueAdmin, 600, 500));
            
        // 2. ROLE : COLLECTIONNEUR
        } else if (login.equals("collec") && mdp.equals("collec")) {
            System.out.println("Connexion rÃ©ussie : Bienvenue Collectionneur !");
            
            CollectionneurHomeVue vueCollec = new CollectionneurHomeVue();
            new CollectionneurHomeControleur(vueCollec, modele, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueCollec, 600, 500));

        // 3. ERREUR : Identifiants faux
        } else {
            System.out.println("Erreur : Identifiants incorrects.");
            vue.getTxtIdentifiant().setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            vue.getTxtMotDePasse().setStyle("-fx-border-color: red; -fx-border-width: 2px;");
        }
    }
}