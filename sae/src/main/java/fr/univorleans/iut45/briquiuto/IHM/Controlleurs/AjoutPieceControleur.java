package fr.univorleans.iut45.briquiuto.IHM.Controlleurs;

import java.sql.SQLException;
import fr.univorleans.iut45.briquiuto.modele.Piece;
import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AjoutPieceVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AdminHomeVue;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AjoutPieceControleur {

    // Attributs
    private AjoutPieceVue vue;
    private RequetesLEGO modele;
    private Stage fenetrePrincipale;

    // Constructeur mis à jour (prend maintenant 3 paramètres)
    public AjoutPieceControleur(AjoutPieceVue vue, RequetesLEGO modele, Stage fenetrePrincipale) {
        this.vue = vue;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        this.initialiser();
    }

    // Initialisation des écouteurs d'événements
    public void initialiser() {
        // Associe le clic du bouton Valider
        vue.getBtnValider().setOnAction(event -> handleValiderPiece());
        
        // Associe le clic du bouton Home (Retour au menu Admin)
        vue.getBtnHome().setOnAction(event -> actionRetourAdmin());
    }

    private void actionRetourAdmin() {
        AdminHomeVue vueAdmin = new AdminHomeVue();
        new AdminHomeControleur(vueAdmin, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueAdmin, 600, 500));
    }

    // Gestion de l'action de validation
    public void handleValiderPiece() {
        // 1. Récupération des données tapées par l'utilisateur
        String numero = vue.getTxtNumero().getText().trim();
        String categorie = vue.getTxtCategorie().getText().trim(); 
        String nom = vue.getTxtNom().getText().trim();

       
        if (numero.isEmpty() || nom.isEmpty()) {
            vue.getLblErreur().setTextFill(javafx.scene.paint.Color.RED);
            vue.afficherErreur("Veuillez remplir le numéro et le nom.");
            return;
        }

        try {
            // 2. Création de l'objet métier
            Piece nouvellePiece = new Piece(numero, nom);
            
            // 3. Appel au modèle pour l'insertion en Base de Données
            modele.ajouterPiece(nouvellePiece);
            
            // 4. Succès : on vide le formulaire et on affiche un message de réussite
            vue.reinitialiserFormulaire();
            vue.getLblErreur().setTextFill(javafx.scene.paint.Color.GREEN);
            vue.afficherErreur("Pièce ajoutée avec succès !");

        } catch (SQLException e) {
            // 5. Échec : L'identifiant (numéro) est déjà présent en base
            vue.getLblErreur().setTextFill(javafx.scene.paint.Color.RED);
            vue.afficherErreur("Numero de piece deja pris");
        }
    }
}