package fr.univorleans.iut45.briquiuto.IHM.Controlleurs;

import java.sql.SQLException;
import java.util.List;

import fr.univorleans.iut45.briquiuto.modele.Piece;
import fr.univorleans.iut45.briquiuto.modele.Categorie;
import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.IHM.Vue.admin.AdminHomeVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.admin.AjoutPieceVueAdmin;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.util.StringConverter; // Import nécessaire pour nettoyer l'affichage

public class AjoutPieceControleur {

    private AjoutPieceVueAdmin vue;
    private RequetesLEGO modele;
    private Stage fenetrePrincipale; 

    public AjoutPieceControleur(AjoutPieceVueAdmin vue, RequetesLEGO modele, Stage fenetrePrincipale) {
        this.vue = vue;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        this.initialiser();
    }

    public void initialiser() {
        // 1. Charger les catégories dans le menu déroulant
        try {
            List<Categorie> lesCategories = modele.getAllCategories();
            vue.getCbCategorie().setItems(FXCollections.observableArrayList(lesCategories));
            
            // Correction : Afficher uniquement le nom de la catégorie au lieu de l'objet complet
            vue.getCbCategorie().setConverter(new StringConverter<Categorie>() {
                @Override
                public String toString(Categorie categorie) {
                    return (categorie == null) ? "" : categorie.getNomCat();
                }

                @Override
                public Categorie fromString(String string) {
                    return null; // Pas nécessaire pour un ComboBox non-éditable
                }
            });

        } catch (SQLException e) {
            afficherAlerte(Alert.AlertType.ERROR, "Erreur réseau", "Impossible de charger les catégories depuis la base de données.");
        }

        // 2. Liaison des boutons
        vue.getBtnValider().setOnAction(event -> handleValiderPiece());
        vue.getBtnHome().setOnAction(event -> actionRetourAdmin());
    }

    public void handleValiderPiece() {
        String numero = vue.getTxtNumero().getText().trim();
        String nom = vue.getTxtNom().getText().trim();
        Categorie categorieChoisie = vue.getCbCategorie().getValue();

        // 1. Validation de la saisie
        if (numero.isEmpty() || nom.isEmpty() || categorieChoisie == null) {
            afficherAlerte(Alert.AlertType.WARNING, "Champs incomplets", "Veuillez remplir le numéro, le nom et choisir une catégorie.");
            return;
        }

        try {
            // 2. Création et insertion via le Modèle
            Piece nouvellePiece = new Piece(numero, nom, categorieChoisie);
            modele.ajouterPiece(nouvellePiece);
            
            // 3. Succès
            afficherAlerte(Alert.AlertType.INFORMATION, "Succès", "La pièce '" + nom + "' a été ajoutée avec succès !");
            
            // On vide les champs pour la prochaine saisie
            vue.getTxtNumero().clear();
            vue.getTxtNom().clear();
            vue.getCbCategorie().getSelectionModel().clearSelection();

        } catch (SQLException e) {
            afficherAlerte(Alert.AlertType.ERROR, "Erreur Base de Données", "Impossible d'ajouter la pièce : le numéro est peut-être déjà utilisé.");
        }
    }

    private void actionRetourAdmin() {
        AdminHomeVue vueAdmin = new AdminHomeVue();
        new AdminHomeControleur(vueAdmin, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueAdmin, 1000, 700));
    }

    private void afficherAlerte(Alert.AlertType type, String titre, String message) {
        Alert alerte = new Alert(type);
        alerte.setTitle(titre);
        alerte.setHeaderText(null);
        alerte.setContentText(message);
        alerte.showAndWait();
    }
}