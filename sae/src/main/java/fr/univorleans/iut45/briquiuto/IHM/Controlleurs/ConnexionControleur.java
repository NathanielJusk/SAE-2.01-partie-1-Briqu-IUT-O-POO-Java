package fr.univorleans.iut45.briquiuto.IHM.Controlleurs;

import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.IHM.Vue.Connexion.VueConnexion;
import fr.univorleans.iut45.briquiuto.IHM.Vue.admin.AdminHomeVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.collec.CollectionneurHomeVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AccueilVue;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Contrôleur pour la vue de connexion.
 * Gère l'authentification simple (admin/collec) et la navigation
 * vers les vues correspondantes. Commentaires simples pour BUT1.
 */
public class ConnexionControleur {

    // Vue du formulaire de connexion
    private VueConnexion vue;
    private RequetesLEGO modele;
    private Stage fenetrePrincipale; 

    public ConnexionControleur(VueConnexion vue, RequetesLEGO modele, Stage fenetrePrincipale) {
        this.vue = vue;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        
        this.initialiser();
    }

    /**
     * Initialise les handlers de la vue de connexion.
     */
    public void initialiser() {
        this.vue.getBtnSeConnecter().setOnAction(event -> actionConnexion());

        // Action pour le bouton Home (icône)
        this.vue.getBtnHome().setOnAction(event -> actionRetourAccueil());
    }

    private void actionRetourAccueil() {
        AccueilVue vueAccueil = new AccueilVue();
        new AccueilControleur(vueAccueil, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueAccueil, 1000, 700));
    }

    /**
     * Valide les identifiants saisis et redirige vers la page appropriée.
     * Méthode volontairement simple (login hardcodé pour les tests).
     */
    public void actionConnexion() {
        String login = vue.getTxtIdentifiant().getText().trim();
        String mdp = vue.getTxtMotDePasse().getText().trim();

        // 1. ROLE : ADMINISTRATEUR
        if (login.equals("admin") && mdp.equals("admin")) {
            System.out.println("Connexion réussie : Bienvenue Administrateur !");
            
            AdminHomeVue vueAdmin = new AdminHomeVue();
            new AdminHomeControleur(vueAdmin, modele, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueAdmin, 1000, 700));
            
        // 2. ROLE : COLLECTIONNEUR
        } else if (login.equals("collec") && mdp.equals("collec")) {
            System.out.println("Connexion réussie : Bienvenue Collectionneur !");
            
            CollectionneurHomeVue vueCollec = new CollectionneurHomeVue();
            new CollectionneurHomeControleur(vueCollec, modele, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueCollec, 1000, 700));

        // 3. ERREUR : Identifiants faux
        } else {
            System.out.println("Erreur : Identifiants incorrects.");
            vue.getTxtIdentifiant().setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            vue.getTxtMotDePasse().setStyle("-fx-border-color: red; -fx-border-width: 2px;");
        }
    }
}