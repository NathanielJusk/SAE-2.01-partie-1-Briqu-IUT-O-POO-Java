package fr.univorleans.iut45.briquiuto.IHM.Controlleurs;

import java.sql.SQLException;
import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.modele.Boite;
import fr.univorleans.iut45.briquiuto.IHM.Vue.VueStatistiquesBoite;
import fr.univorleans.iut45.briquiuto.IHM.Vue.CollectionneurHomeVue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StatistiquesBoiteControleur {

    private VueStatistiquesBoite vue;
    private RequetesLEGO modele;
    private Stage fenetrePrincipale;

    public StatistiquesBoiteControleur(VueStatistiquesBoite vue, RequetesLEGO modele, Stage fenetrePrincipale) {
        this.vue = vue;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        this.initialiser();
    }

    private void initialiser() {
        this.vue.getBtnRechercher().setOnAction(e -> actionAnalyserBoite());
        this.vue.getBtnHome().setOnAction(e -> actionRetourHome());
    }

    private void actionAnalyserBoite() {
        String numeroSaisi = vue.getTxtNumBoite().getText().trim();

        if (numeroSaisi.isEmpty()) {
            vue.afficherErreur("Veuillez saisir un numéro de boîte !");
            return;
        }

        // 1. Recherche de la boîte dans le manager en mémoire (POO)
        Boite boiteTrouvee = modele.getManager().rechercherBoiteParNumero(numeroSaisi);

        if (boiteTrouvee != null) {
            try {
                // 2. Récupération dynamique du nom du thème depuis la BD
                String nomTheme = "Inconnu";
                if (boiteTrouvee.getTheme() != null) {
                    nomTheme = boiteTrouvee.getTheme().getNom();
                }

                // Récupération du nombre de pièces (via le modèle ou calcul direct de la partie 1)
                int totalPieces = boiteTrouvee.getNbPiece();

                // Affichage des statistiques de base dans la fiche
                vue.afficherStatsBoite(boiteTrouvee, totalPieces, nomTheme);

                // 3. Chargement des pièces de la boîte à l'aide des requêtes SQL JDBC
                ObservableList<String> detailsPieces = FXCollections.observableArrayList();
                
                // Appel à ta méthode JDBC de la partie 1 pour lister les composants de la boîte
                String piecesBoite = modele.listerPiecesBoite(numeroSaisi);
                if (piecesBoite != null && !piecesBoite.isEmpty()) {
                    for (String ligne : piecesBoite.split("\n")) {
                        if (!ligne.trim().isEmpty()) {
                            detailsPieces.add(ligne);
                        }
                    }
                }

                // Ajout des figurines également pour enrichir l'affichage
                String figurinesBoite = modele.listerFigurinesBoite(numeroSaisi);
                if (figurinesBoite != null && !figurinesBoite.isEmpty()) {
                    for (String ligne : figurinesBoite.split("\n")) {
                        if (!ligne.trim().isEmpty()) {
                            detailsPieces.add("[Figurine] " + ligne);
                        }
                    }
                }

                vue.getTableContenu().setItems(detailsPieces);

            } catch (SQLException e) {
                vue.afficherErreur("Erreur lors de la récupération des composants : " + e.getMessage());
            }
        } else {
            vue.afficherErreur("Aucune boîte ne porte le numéro : " + numeroSaisi);
        }
    }

    private void actionRetourHome() {
        CollectionneurHomeVue vueCollec = new CollectionneurHomeVue();
        new CollectionneurHomeControleur(vueCollec, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueCollec, 600, 500));
    }
}