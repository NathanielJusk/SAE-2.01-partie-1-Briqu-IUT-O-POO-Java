package fr.univorleans.iut45.briquiuto.IHM.Controlleurs.adminControlleurs;

import java.sql.SQLException;
import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.modele.Categorie;
import fr.univorleans.iut45.briquiuto.modele.Piece;
import fr.univorleans.iut45.briquiuto.IHM.Vue.admin.AjoutPieceVueAdmin;
import fr.univorleans.iut45.briquiuto.IHM.Vue.admin.AdminHomeVue;
import fr.univorleans.iut45.briquiuto.IHM.Controlleurs.AccueilControleur.AccueilControleur;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AccueilVue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * Contrôleur pour ajouter une nouvelle pièce dans le catalogue (vue admin).
 * Valide le formulaire, crée un objet `Piece` et demande au modèle
 * (RequetesLEGO) d'ajouter la pièce en base.
 */
public class AjoutPieceControleur {

    // Vue pour le formulaire d'ajout de pièce
    private AjoutPieceVueAdmin vue;
    private RequetesLEGO modele;
    private Stage fenetrePrincipale;

    public AjoutPieceControleur(AjoutPieceVueAdmin vue, RequetesLEGO modele, Stage fenetrePrincipale) {
        this.vue = vue;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        this.initialiser();
    }

    /**
     * Initialise la vue : charge la liste des catégories et fixe les actions
     * des boutons (valider, retour, home).
     */
    private void initialiser() {
        chargerCategories();

        // Actions des boutons
        vue.getBtnValider().setOnAction(e -> actionValiderPiece());
        vue.getBtnRetour().setOnAction(e -> actionRetourAdmin());
        vue.getBtnHome().setOnAction(e -> actionRetourAccueil());
    }

    /**
     * Charge les catégories depuis le modèle et les place dans le combo-box.
     */
    private void chargerCategories() {
        try {
            vue.getCbCategorie().setItems(FXCollections.observableArrayList(modele.getAllCategories()));
            vue.getCbCategorie().setConverter(new StringConverter<Categorie>() {
                @Override public String toString(Categorie c) { return c == null ? "" : c.getNomCat(); }
                @Override public Categorie fromString(String s) { return null; }
            });
        } catch (SQLException e) {
            vue.afficherMessage("Erreur chargement catégories", "red");
        }
    }

    /**
     * Récupère les valeurs du formulaire et tente d'ajouter une pièce.
     * Affiche des messages d'erreur simples si besoin.
     */
    private void actionValiderPiece() {
        String num = vue.getTxtNumeroPiece().getText().trim();
        String nom = vue.getTxtNomPiece().getText().trim();
        Categorie cat = vue.getCbCategorie().getValue();

        if (num.isEmpty() || nom.isEmpty() || cat == null) {
            vue.afficherMessage("Veuillez remplir tous les champs !", "red");
            return;
        }

        try {
            Piece nouvellePiece = new Piece(num, nom);
            nouvellePiece.setCategorie(cat);
            // Assure-toi d'avoir une méthode ajouterPiece() dans RequetesLEGO !
            modele.ajouterPiece(nouvellePiece); 
            vue.afficherMessage("Pièce ajoutée avec succès !", "green");
            vue.reinitialiserFormulaire();
        } catch (SQLException ex) {
            vue.afficherMessage("Erreur : Ce numéro de pièce existe peut-être déjà.", "red");
        }
    }

    private void actionRetourAdmin() {
        AdminHomeVue vueAdmin = new AdminHomeVue();
        new AdminHomeControleur(vueAdmin, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueAdmin, 1000, 700));
    }

    private void actionRetourAccueil() {
        AccueilVue vueAccueil = new AccueilVue();
        new AccueilControleur(vueAccueil, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueAccueil, 1000, 700));
    }
}