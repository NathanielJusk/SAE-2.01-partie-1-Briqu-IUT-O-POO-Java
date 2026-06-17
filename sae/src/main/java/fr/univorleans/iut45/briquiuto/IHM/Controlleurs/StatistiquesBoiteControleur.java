package fr.univorleans.iut45.briquiuto.IHM.Controlleurs;

import java.sql.SQLException;
import java.util.List;
import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.modele.Boite;
import fr.univorleans.iut45.briquiuto.IHM.Vue.VueStatistiquesBoite;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AdminHomeVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AccueilVue;
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
        
        // --- LE RETOUR VA DIRECTEMENT A L'ADMIN ---
        this.vue.getBtnRetour().setOnAction(e -> actionRetourAdmin());
        this.vue.getBtnHome().setOnAction(e -> actionRetourAccueil());
    }

    private void actionAnalyserBoite() {
        String numeroSaisi = vue.getTxtNumBoite().getText().trim();
        if (numeroSaisi.isEmpty()) {
            vue.afficherErreur("Veuillez saisir un numéro de boîte !");
            return;
        }

        try {
            Boite boiteTrouvee = modele.rechercherBoiteParNumero(numeroSaisi);

            if (boiteTrouvee != null) {
                String nomTheme = boiteTrouvee.getTheme() != null ? boiteTrouvee.getTheme().getNom() : "Inconnu";
                vue.afficherStatsBoite(boiteTrouvee, boiteTrouvee.getNbPiece(), nomTheme);
                vue.afficherImageBoite(boiteTrouvee.getImgUrl());

                // On charge les données structurées pour les 5 colonnes
                ObservableList<String[]> detailsBoite = FXCollections.observableArrayList();
                
                List<String[]> pieces = modele.getDetailsPiecesBoite(numeroSaisi);
                detailsBoite.addAll(pieces);
                
                List<String[]> figurines = modele.getDetailsFigurinesBoite(numeroSaisi);
                detailsBoite.addAll(figurines);

                if (detailsBoite.isEmpty()) {
                    detailsBoite.add(new String[]{"Cette boîte est vide.", "-", "-", "-", ""});
                }

                vue.getTableContenu().setItems(detailsBoite);

            } else {
                vue.afficherErreur("Aucune boîte ne porte le numéro : " + numeroSaisi);
            }
        } catch (SQLException e) {
            vue.afficherErreur("Erreur BD : " + e.getMessage());
        }
    }

    private void actionRetourAdmin() {
        // Retourne toujours au menu Administrateur !
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