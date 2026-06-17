package fr.univorleans.iut45.briquiuto.IHM.Controlleurs;

import java.sql.SQLException;
import fr.univorleans.iut45.briquiuto.JDBC.ConnexionBD;
import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.modele.BriqueCollectionManager;
import fr.univorleans.iut45.briquiuto.IHM.Vue.ConnexionBDVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AccueilVue;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ConnexionBDControleur {

    private ConnexionBDVue vue;
    private ConnexionBD connexion;
    private Stage fenetrePrincipale;

    public ConnexionBDControleur(ConnexionBDVue vue, ConnexionBD connexion, Stage fenetrePrincipale) {
        this.vue = vue;
        this.connexion = connexion;
        this.fenetrePrincipale = fenetrePrincipale;
        this.initialiser();
    }

    private void initialiser() {
        this.vue.getBtnConnecterBD().setOnAction(e -> validerConnexion());
    }

private void validerConnexion() {
        String login = vue.getTxtLoginBD().getText().trim();
        String mdp = vue.getTxtMdpBD().getText().trim();

        if (login.isEmpty() || mdp.isEmpty()) {
            vue.afficherErreur("Veuillez remplir tous les champs !");
            return;
        }

        try {
            // Tentative de connexion au serveur de l'IUT pour la base LEGO
            connexion.connecter("servinfo-maria", "DBo22403771", login, mdp);
            System.out.println("Connexion à la BD MariaDB réussie !");

            // Création du modèle principal maintenant qu'on est connecté
            BriqueCollectionManager manager = new BriqueCollectionManager();
            RequetesLEGO requetesModel = new RequetesLEGO(connexion, manager);
            
            // --- C'EST ICI QU'ON APPELLE LA MÉTHODE POUR CHARGER LES DONNÉES ---
            requetesModel.chargerDonneesDansManager();

            // Redirection vers le VRAI menu principal
            AccueilVue vueAccueil = new AccueilVue();
            new AccueilControleur(vueAccueil, requetesModel, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueAccueil, 1000, 700));

        } catch (SQLException ex) {
            System.out.println("Erreur BD : " + ex.getMessage());
            vue.afficherErreur("Identifiants IUT incorrects ou serveur injoignable.");
        }
    }}