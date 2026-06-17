package fr.univorleans.iut45.briquiuto.IHM.Controlleurs;

import java.sql.SQLException;
import java.util.List;
import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.modele.Boite;
import fr.univorleans.iut45.briquiuto.modele.Theme;
import fr.univorleans.iut45.briquiuto.IHM.Vue.collec.CollectionneurHomeVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.collec.VueRechercheBoiteParThemeCollectionneur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RechercheBoiteThemeControleur {

    private VueRechercheBoiteParThemeCollectionneur vue;
    private RequetesLEGO modele;
    private Stage fenetrePrincipale;

    public RechercheBoiteThemeControleur(VueRechercheBoiteParThemeCollectionneur vue, RequetesLEGO modele, Stage fenetrePrincipale) {
        this.vue = vue;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        this.initialiser();
    }

    private void initialiser() {
        // 1. Charger la liste complète des thèmes LEGO dans le filtre déroulant
        try {
            List<Theme> lesThemes = modele.getAllThemes();
            vue.alimenterThemes(FXCollections.observableArrayList(lesThemes));
        } catch (SQLException e) {
            System.out.println("Erreur d'initialisation des thèmes : " + e.getMessage());
        }

        // 2. Établir l'écoute sur les boutons d'action
        this.vue.getBtnRechercher().setOnAction(e -> actionFiltrerParTheme());
        this.vue.getBtnRetour().setOnAction(e -> actionRetourCollectionneur());
    }

    private void actionFiltrerParTheme() {
        Theme themeSelectionne = vue.getCbTheme().getValue();

        if (themeSelectionne == null) {
            System.out.println("Avertissement : Aucun thème sélectionné.");
            return;
        }

        try {
            // Interroger le modèle pour récupérer les boîtes correspondantes (incluant sous-thèmes)
            List<Boite> boitesTrouvees = modele.rechercherBoitesParTheme(themeSelectionne);

            if (boitesTrouvees != null && !boitesTrouvees.isEmpty()) {
                ObservableList<Boite> resultats = FXCollections.observableArrayList(boitesTrouvees);
                vue.afficherResultats(resultats);
            } else {
                // Si la recherche ne donne rien, on vide proprement la TableView
                vue.afficherResultats(FXCollections.observableArrayList());
                System.out.println("Aucune boîte enregistrée pour le thème : " + themeSelectionne.getNom());
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL lors du filtrage : " + e.getMessage());
            vue.afficherResultats(FXCollections.observableArrayList());
        }
    }

    private void actionRetourCollectionneur() {
        CollectionneurHomeVue vueCollec = new CollectionneurHomeVue();
        new CollectionneurHomeControleur(vueCollec, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueCollec, 1000, 700)); // Mis à 1000x700 pour la cohérence
    }
}