package fr.univorleans.iut45.briquiuto.IHM.Controlleurs.CollectionneurControlleurs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.modele.Boite;
import fr.univorleans.iut45.briquiuto.IHM.Controlleurs.AccueilControleur.AccueilControleur;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AccueilVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.collec.CollectionneurHomeVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.collec.VueMesMOCs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * Contrôleur pour la liste des MOCs (boîtes personnalisées) d'un collectionneur.
 * Charge les boîtes dont le numéro commence par "PERSO-" et permet une
 * recherche simple via un champ texte. Rédaction simple adaptée à BUT1.
 */
public class MesMOCsControleur {

    // Vue qui affiche les MOCs
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

    /**
     * Initialise la vue : navigation et chargement des boîtes personnalisées.
     */
    private void initialiser() {
        // 1. Bouton Retour (flèche) -> Retour au menu du collectionneur
        vue.getBtnRetour().setOnAction(e -> {
            CollectionneurHomeVue vueHome = new CollectionneurHomeVue();
            new CollectionneurHomeControleur(vueHome, modele, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueHome, 1000, 700));
        });

        // 2. Bouton Home (Maison) -> Déconnexion vers l'accueil de l'application
        vue.getBtnHome().setOnAction(e -> {
            AccueilVue vueAccueil = new AccueilVue();
            new AccueilControleur(vueAccueil, modele, fenetrePrincipale);
            fenetrePrincipale.setScene(new Scene(vueAccueil, 1000, 700));
        });

        // 3. Charger les données et configurer la recherche filtrable
        try {
            List<Boite> toutesLesBoites = modele.getAllBoites();
            List<Boite> seulementMesMocs = new ArrayList<>();

            // === LE FILTRE MAGIQUE EST ICI ===
            // On ne garde que les boîtes dont le numéro commence par "PERSO-"
            for (Boite b : toutesLesBoites) {
                if (b.getNumero() != null && b.getNumero().toUpperCase().startsWith("PERSO-")) {
                    seulementMesMocs.add(b);
                }
            }

            listeToutesBoites = FXCollections.observableArrayList(seulementMesMocs);
            
            // Création d'une liste filtrable (pour la barre de recherche)
            FilteredList<Boite> filteredData = new FilteredList<>(listeToutesBoites, b -> true);
            
            // Lier le champ de texte au prédicat de filtrage dynamique
            vue.getTxtRecherche().textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(boite -> {
                    // Si le champ est vide, on affiche tous les MOCs
                    if (newValue == null || newValue.trim().isEmpty()) {
                        return true;
                    }
                    
                    String rechercheMinuscule = newValue.toLowerCase().trim();
                    
                    // Filtrage par Numéro OU par Nom
                    if (boite.getNumero() != null && boite.getNumero().toLowerCase().contains(rechercheMinuscule)) {
                        return true;
                    } else if (boite.getNom() != null && boite.getNom().toLowerCase().contains(rechercheMinuscule)) {
                        return true;
                    }
                    return false;
                });
            });

            // Injection des données filtrées dans le tableau
            vue.getTableBoites().setItems(filteredData);

        } catch (SQLException e) {
            Alert alerte = new Alert(Alert.AlertType.ERROR);
            alerte.setTitle("Erreur BD");
            alerte.setHeaderText(null);
            alerte.setContentText("Impossible de charger les boîtes depuis la base de données.");
            alerte.showAndWait();
        }
    }
}