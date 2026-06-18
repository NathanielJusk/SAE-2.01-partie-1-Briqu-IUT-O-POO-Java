package fr.univorleans.iut45.briquiuto.IHM.Controlleurs;

import java.sql.SQLException;
import java.util.List;
import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.modele.Boite;
import fr.univorleans.iut45.briquiuto.IHM.Vue.admin.VueStatistiquesBoite;
import fr.univorleans.iut45.briquiuto.IHM.Vue.admin.AdminHomeVue;
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

                // --- CORRECTION ICI : On utilise la fameuse LigneAffichage ---
                ObservableList<VueStatistiquesBoite.LigneAffichage> detailsBoite = FXCollections.observableArrayList();
                
                // On récupère les pièces et on les transforme
                List<String[]> pieces = modele.getDetailsPiecesBoite(numeroSaisi);
                for (String[] p : pieces) {
                    // On fusionne les infos textes (Qté, Nom, Couleur, Supplément) en une seule phrase propre
                    String description = p[2] + "x  " + p[0] + "  (Couleur: " + p[1] + ")  -  Supplément: " + p[3];
                    String urlImage = (p.length > 4) ? p[4] : "";
                    
                    detailsBoite.add(new VueStatistiquesBoite.LigneAffichage(description, urlImage));
                }
                
                // On récupère les figurines et on les transforme
                List<String[]> figurines = modele.getDetailsFigurinesBoite(numeroSaisi);
                for (String[] f : figurines) {
                    String description = f[2] + "x  " + f[0] + "  (Couleur: " + f[1] + ")  -  Supplément: " + f[3];
                    String urlImage = (f.length > 4) ? f[4] : "";
                    
                    detailsBoite.add(new VueStatistiquesBoite.LigneAffichage(description, urlImage));
                }

                if (detailsBoite.isEmpty()) {
                    detailsBoite.add(new VueStatistiquesBoite.LigneAffichage("Cette boîte est vide.", ""));
                }

                vue.getTableContenu().setItems(detailsBoite);
                // -------------------------------------------------------------

            } else {
                vue.afficherErreur("Aucune boîte ne porte le numéro : " + numeroSaisi);
            }
        } catch (SQLException e) {
            vue.afficherErreur("Erreur BD : " + e.getMessage());
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