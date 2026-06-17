package fr.univorleans.iut45.briquiuto.IHM.Controlleurs;

import java.sql.SQLException;
import java.util.List;

import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.modele.Boite;
import fr.univorleans.iut45.briquiuto.IHM.Vue.CollectionneurHomeVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.VueMesMOCs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class MesMOCsControleur {

    private VueMesMOCs vue;
    private RequetesLEGO modele;
    private Stage fenetrePrincipale;
    private ObservableList<Boite> listeToutesBoites;

    public MesMOCsControleur(VueMesMOCs vue, RequetesLEGO modele, Stage fenetrePrincipale) {
        this.vue = vue;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        this.initialiser();
    }

    private void initialiser() {
        // 1. Retour Accueil
        vue.getBtnHome().setOnAction(e -> {
            CollectionneurHomeVue vueHome = new CollectionneurHomeVue();
            new CollectionneurHomeControleur(vueHome, modele, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueHome, 600, 500));
        });

        // 2. Charger les données
        try {
            List<Boite> boites = modele.getAllBoites();
            listeToutesBoites = FXCollections.observableArrayList(boites);
            
            // Création d'une liste filtrable
            FilteredList<Boite> filteredData = new FilteredList<>(listeToutesBoites, b -> true);
            
            // Lier le champ de texte au filtre
            vue.getTxtRecherche().textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(boite -> {
                    // Si le champ est vide, on affiche tout
                    if (newValue == null || newValue.trim().isEmpty()) {
                        return true;
                    }
                    
                    String rechercheMinuscule = newValue.toLowerCase().trim();
                    
                    // On filtre par Numéro OU par Nom
                    if (boite.getNumero() != null && boite.getNumero().toLowerCase().contains(rechercheMinuscule)) {
                        return true;
                    } else if (boite.getNom() != null && boite.getNom().toLowerCase().contains(rechercheMinuscule)) {
                        return true;
                    }
                    return false;
                });
            });

            // Afficher dans le tableau
            vue.getTableBoites().setItems(filteredData);

        } catch (SQLException e) {
            Alert alerte = new Alert(Alert.AlertType.ERROR);
            alerte.setTitle("Erreur BD");
            alerte.setContentText("Impossible de charger les boîtes depuis la base de données.");
            alerte.showAndWait();
        }
    }
}