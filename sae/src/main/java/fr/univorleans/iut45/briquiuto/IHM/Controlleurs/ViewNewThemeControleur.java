package fr.univorleans.iut45.briquiuto.IHM.Controlleurs;

import java.sql.SQLException;
import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.modele.Theme;
import fr.univorleans.iut45.briquiuto.IHM.Vue.admin.ViewNewTheme;
import fr.univorleans.iut45.briquiuto.IHM.Vue.admin.AdminHomeVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AccueilVue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class ViewNewThemeControleur {

    private ViewNewTheme vue;
    private RequetesLEGO modele;
    private Stage fenetrePrincipale;

    public ViewNewThemeControleur(ViewNewTheme vue, RequetesLEGO modele, Stage fenetrePrincipale) {
        this.vue = vue;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        this.initialiser();
    }

    private void initialiser() {
        chargerThemesParents();

        // Actions des boutons
        vue.getBtnValider().setOnAction(e -> actionValiderTheme());
        vue.getBtnRetour().setOnAction(e -> actionRetourAdmin());
        vue.getBtnHome().setOnAction(e -> actionRetourAccueil());
    }

    private void chargerThemesParents() {
        try {
            vue.getCbParent().setItems(FXCollections.observableArrayList(modele.getAllThemes()));
            vue.getCbParent().setConverter(new StringConverter<Theme>() {
                @Override public String toString(Theme t) { return t == null ? "" : t.getNom(); }
                @Override public Theme fromString(String s) { return null; }
            });
        } catch (SQLException e) {
            // Ignorer si la liste est vide au début
        }
    }

    private void actionValiderTheme() {
        String num = vue.getTxtNumero().getText().trim();
        String nom = vue.getTxtNom().getText().trim();
        Theme parent = vue.getCbParent().getValue();

        if (num.isEmpty() || nom.isEmpty()) {
            // Tu pourrais ajouter un Label lblMessage dans ta vue pour afficher ça !
            System.out.println("Veuillez remplir le numéro et le nom !");
            return;
        }

        try {
            Theme nouveauTheme = new Theme(Integer.parseInt(num), nom);
            if (parent != null) {
                nouveauTheme.setThemePere(parent.getIdTheme());
            }
            // Assure-toi d'avoir cette méthode dans RequetesLEGO
            modele.ajouterTheme(nouveauTheme); 
            vue.reinitialiserFormulaire();
            chargerThemesParents(); // Met à jour la liste déroulante !
            System.out.println("Thème ajouté !");
        } catch (NumberFormatException ex) {
            System.out.println("Le numéro du thème doit être un nombre entier !");
        } catch (SQLException ex) {
            System.out.println("Erreur SQL lors de l'ajout du thème.");
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