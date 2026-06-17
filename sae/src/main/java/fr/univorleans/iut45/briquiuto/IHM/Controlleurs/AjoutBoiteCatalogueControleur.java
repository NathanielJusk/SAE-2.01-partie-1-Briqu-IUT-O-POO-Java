package fr.univorleans.iut45.briquiuto.IHM.Controlleurs;

import java.sql.SQLException;
import java.util.List;
import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.modele.Boite;
import fr.univorleans.iut45.briquiuto.modele.BoiteComposee;
import fr.univorleans.iut45.briquiuto.modele.Theme;
import fr.univorleans.iut45.briquiuto.modele.Piece;
import fr.univorleans.iut45.briquiuto.IHM.Vue.VueAjoutBoiteCatalogue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AdminHomeVue;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class AjoutBoiteCatalogueControleur {

    private VueAjoutBoiteCatalogue vue;
    private RequetesLEGO modele;
    private Stage fenetrePrincipale;

    public AjoutBoiteCatalogueControleur(VueAjoutBoiteCatalogue vue, RequetesLEGO modele, Stage fenetrePrincipale) {
        this.vue = vue;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        this.initialiser();
    }

    private void initialiser() {
        // 1. Alimenter les ComboBox au démarrage de la page
        try {
            // Chargement des thèmes existants dans le menu déroulant
            List<Theme> listeThemes = modele.getAllThemes();
            vue.setThemesDisponibles(listeThemes);
            
            // Chargement des pièces disponibles pour les lignes de composition
            List<Piece> listePieces = modele.getManager().getCataloguePieces();
            vue.setPiecesDisponibles(listePieces);

            // Nettoyage de l'affichage du ComboBox de thème (affiche uniquement le nom)
            vue.getCbTheme().setConverter(new StringConverter<Theme>() {
                @Override
                public String toString(Theme theme) {
                    return theme != null ? theme.getNom() : "";
                }
                @Override
                public Theme fromString(String string) {
                    return null;
                }
            });

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des données : " + e.getMessage());
        }

        // 2. Attribution des comportements aux boutons de la vue
        this.vue.getBtnValider().setOnAction(e -> handleEnregistrerBoite());
        this.vue.getBtnHome().setOnAction(e -> actionRetourAdmin());
    }

    private void handleEnregistrerBoite() {
        String numero = vue.getTxtNumero().getText().trim();
        String nom = vue.getTxtNom().getText().trim();
        String anneeStr = vue.getTxtAnnee().getText().trim();
        Theme themeChoisi = vue.getCbTheme().getValue();

        // Validation simple des champs obligatoires
        if (numero.isEmpty() || nom.isEmpty() || anneeStr.isEmpty() || themeChoisi == null) {
            vue.afficherMessage("Veuillez remplir toutes les informations obligatoires !", "#E3000B"); // Rouge LEGO
            return;
        }

        try {
            int annee = Integer.parseInt(anneeStr);

            // Parcours dynamique des lignes de pièces créées pour compter le nombre de pièces total
            int nbPiecesTotal = 0;
            List<VueAjoutBoiteCatalogue.LignePieceContenu> lignes = vue.getListeLignesChoisies();
            
            for (VueAjoutBoiteCatalogue.LignePieceContenu ligne : lignes) {
                String qteStr = ligne.getTxtQuantite().getText().trim();
                if (!qteStr.isEmpty()) {
                    nbPiecesTotal += Integer.parseInt(qteStr);
                }
            }

            // Instanciation de l'objet métier (Boîte composée standard d'un administrateur)
            Boite nouvelleBoite = new BoiteComposee(numero, nbPiecesTotal, nom, annee);
            nouvelleBoite.setTheme(themeChoisi);

            // Insertion dans le catalogue de la base de données relationnelle
            modele.ajouterBoite(nouvelleBoite);
            
            // Confirmation visuelle positive et remise à blanc du formulaire
            vue.afficherMessage("Succès : La boîte officielle a été ajoutée au catalogue !", "#287F46"); // Vert LEGO
            vue.reinitialiserFormulaire();
            
            // Ré-injecter le catalogue de pièces pour la nouvelle saisie réinitialisée
            vue.setPiecesDisponibles(modele.getManager().getCataloguePieces());

        } catch (NumberFormatException e) {
            vue.afficherMessage("Erreur : L'année et les quantités doivent être des nombres !", "#E3000B");
        } catch (SQLException e) {
            vue.afficherMessage("Erreur de contrainte : Ce numéro de boîte existe déjà !", "#E3000B");
        }
    }

    private void actionRetourAdmin() {
        AdminHomeVue vueAdmin = new AdminHomeVue();
        new AdminHomeControleur(vueAdmin, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueAdmin, 600, 500));
    }
}