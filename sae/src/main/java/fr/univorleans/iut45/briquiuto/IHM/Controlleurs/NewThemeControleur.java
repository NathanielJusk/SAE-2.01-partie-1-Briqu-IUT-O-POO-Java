package fr.univorleans.iut45.briquiuto.IHM.Controlleurs;

import java.sql.SQLException;
import java.util.List;

import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.modele.Theme;
import fr.univorleans.iut45.briquiuto.IHM.Vue.ViewNewTheme;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AdminHomeVue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.util.StringConverter; // Important pour l'affichage propre du nom

public class NewThemeControleur {

    private ViewNewTheme vue;
    private RequetesLEGO modele;
    private Stage fenetrePrincipale; 

    public NewThemeControleur(ViewNewTheme vue, RequetesLEGO modele, Stage fenetrePrincipale) {
        this.vue = vue;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        this.initialiser();
    }

    public void initialiser() {
        // 1. Charger les thèmes existants dans le menu déroulant
        try {
            List<Theme> lesThemes = modele.getAllThemes();
            vue.getCbParent().setItems(FXCollections.observableArrayList(lesThemes));
            
            // Correction de l'affichage du ComboBox pour n'afficher que le nom
            vue.getCbParent().setConverter(new StringConverter<Theme>() {
                @Override
                public String toString(Theme theme) {
                    return (theme == null) ? "" : theme.getNom();
                }

                @Override
                public Theme fromString(String string) {
                    return null; // Non utilisé car le ComboBox n'est pas éditable
                }
            });

        } catch (SQLException e) {
            afficherAlerte(Alert.AlertType.ERROR, "Erreur", "Impossible de charger les thèmes existants.");
        }

        // 2. Lier les boutons
        vue.getBtnValider().setOnAction(event -> handleValiderTheme());
        vue.getBtnHome().setOnAction(event -> actionRetourAdmin());
    }

    public void handleValiderTheme() {
        String strNumero = vue.getTxtNumero().getText().trim();
        String nom = vue.getTxtNom().getText().trim();
        Theme themeParent = vue.getCbParent().getValue();

        // 1. Validation de la saisie
        if (strNumero.isEmpty() || nom.isEmpty()) {
            afficherAlerte(Alert.AlertType.WARNING, "Champs incomplets", "Veuillez remplir le numéro et le nom du thème.");
            return;
        }

        int numero;
        try {
            numero = Integer.parseInt(strNumero);
        } catch (NumberFormatException e) {
            afficherAlerte(Alert.AlertType.WARNING, "Erreur de format", "Le numéro du thème doit être un entier valide.");
            return;
        }

        try {
            // 2. Création et insertion
            Theme nouveauTheme;
            if (themeParent != null) {
                nouveauTheme = new Theme(numero, nom, themeParent);
            } else {
                nouveauTheme = new Theme(numero, nom);
            }
            
            modele.ajouterTheme(nouveauTheme);
            
            // 3. Succès
            afficherAlerte(Alert.AlertType.INFORMATION, "Succès", "Le thème '" + nom + "' a été créé !");
            
            // On rafraîchit la liste des thèmes dans le ComboBox
            List<Theme> lesThemes = modele.getAllThemes();
            vue.getCbParent().setItems(FXCollections.observableArrayList(lesThemes));
            
            vue.reinitialiserFormulaire();

        } catch (SQLException e) {
            afficherAlerte(Alert.AlertType.ERROR, "Erreur Base de Données", "Impossible d'ajouter le thème. L'ID est peut-être déjà pris.");
        }
    }

    private void actionRetourAdmin() {
        AdminHomeVue vueAdmin = new AdminHomeVue();
        new AdminHomeControleur(vueAdmin, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueAdmin, 600, 500));
    }

    private void afficherAlerte(Alert.AlertType type, String titre, String message) {
        Alert alerte = new Alert(type);
        alerte.setTitle(titre);
        alerte.setHeaderText(null);
        alerte.setContentText(message);
        alerte.showAndWait();
    }
}