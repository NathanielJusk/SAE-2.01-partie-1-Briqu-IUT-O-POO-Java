package fr.univorleans.iut45.briquiuto.IHM.Controlleurs;

import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.IHM.Vue.VueConnexion;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AccueilVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AdminHomeVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.CollectionneurHomeVue;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ConnexionControleur {

    private VueConnexion vue;
    private RequetesLEGO modele;
    private Stage fenetrePrincipale; 

    // Constructeur
    public ConnexionControleur(VueConnexion vue, RequetesLEGO modele, Stage fenetrePrincipale) {
        this.vue = vue;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        
        this.initialiser();
    }

    // On écoute le clic sur le bouton "se connecter"
    // Dans ta classe ConnexionControleur, ajoute ceci dans initialiser() :
    public void initialiser() {
        this.vue.getBtnSeConnecter().setOnAction(event -> actionConnexion());
        // Action du bouton Home
        this.vue.getBtnHome().setOnAction(event -> actionRetourHome());
    }

    // Et ajoute cette nouvelle méthode :
    private void actionRetourHome() {
        AccueilVue vueAccueil = new AccueilVue();
        new AccueilControleur(vueAccueil, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueAccueil, 600, 500));
    }

    // Méthode déclenchée quand on clique sur "se connecter"
    public void actionConnexion() {
        String login = vue.getTxtIdentifiant().getText().trim();
        String mdp = vue.getTxtMotDePasse().getText().trim();

        // 1. SI C'EST L'ADMINISTRATEUR
        if (login.equals("admin") && mdp.equals("admin")) {
            System.out.println("Connexion réussie : Bienvenue Administrateur !");
            
            // On crée la vue Administrateur
            AdminHomeVue vueAdmin = new AdminHomeVue();
            // On l'associe à son contrôleur
            new AdminHomeControleur(vueAdmin, modele, fenetrePrincipale);
            
            // ON CHANGE LA FENETRE POUR AFFICHER LE MENU ADMIN
            Scene sceneAdmin = new Scene(vueAdmin, 600, 500);
            fenetrePrincipale.setScene(sceneAdmin);
            
        // 2. SI C'EST LE COLLECTIONNEUR
        } else if (login.equals("collec") && mdp.equals("collec")) {
            System.out.println("Connexion réussie : Bienvenue Collectionneur !");
            
            CollectionneurHomeVue vueCollec = new CollectionneurHomeVue();
            new CollectionneurHomeControleur(vueCollec, modele, fenetrePrincipale);
            
            Scene sceneCollec = new Scene(vueCollec, 600, 500);
            fenetrePrincipale.setScene(sceneCollec);

        // 3. SI LE MOT DE PASSE EST FAUX
        } else {
            System.out.println("Erreur : Identifiants incorrects.");
            vue.getTxtIdentifiant().setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            vue.getTxtMotDePasse().setStyle("-fx-border-color: red; -fx-border-width: 2px;");
        }
    }
}