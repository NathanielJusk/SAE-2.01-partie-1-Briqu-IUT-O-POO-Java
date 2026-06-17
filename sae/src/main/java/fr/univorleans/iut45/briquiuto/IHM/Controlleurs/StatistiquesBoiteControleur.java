package fr.univorleans.iut45.briquiuto.IHM.Controlleurs;

import java.sql.SQLException;
import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.modele.Boite;
import fr.univorleans.iut45.briquiuto.IHM.Vue.VueStatistiquesBoite;
import fr.univorleans.iut45.briquiuto.IHM.Vue.VueStatistiquesBoite.LigneAffichage; // Import de notre nouvelle classe
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

        try {
            Boite boiteTrouvee = modele.rechercherBoiteParNumero(numeroSaisi);

            if (boiteTrouvee != null) {
                String nomTheme = "Inconnu";
                if (boiteTrouvee.getTheme() != null) {
                    nomTheme = boiteTrouvee.getTheme().getNom();
                }

                int totalPieces = boiteTrouvee.getNbPiece();
                vue.afficherStatsBoite(boiteTrouvee, totalPieces, nomTheme);
                vue.afficherImageBoite(boiteTrouvee.getImgUrl());

                // On utilise maintenant une liste de LigneAffichage
                ObservableList<LigneAffichage> detailsPieces = FXCollections.observableArrayList();
                
                String piecesBoite = modele.listerPiecesBoite(numeroSaisi);
                if (piecesBoite != null && !piecesBoite.isEmpty() && !piecesBoite.equals("Aucune piece trouvee.")) {
                    for (String ligne : piecesBoite.split("\n")) {
                        if (!ligne.trim().isEmpty()) {
                            
                            // NOUVEAU : On découpe le texte généré par le SQL !
                            String texteDescription = ligne;
                            String urlImage = null;
                            
                            // Si le mot clé " | Image : " est présent, on coupe la ligne en deux
                            if (ligne.contains(" | Image : ")) {
                                String[] parties = ligne.split(" \\| Image : ");
                                texteDescription = parties[0]; // La description de la pièce
                                if (parties.length > 1) {
                                    urlImage = parties[1];     // L'URL de l'image de la pièce
                                }
                            }
                            
                            detailsPieces.add(new LigneAffichage(texteDescription, urlImage));
                        }
                    }
                }

                String figurinesBoite = modele.listerFigurinesBoite(numeroSaisi);
                if (figurinesBoite != null && !figurinesBoite.isEmpty() && !figurinesBoite.equals("Aucune figurine trouvee.")) {
                    for (String ligne : figurinesBoite.split("\n")) {
                        if (!ligne.trim().isEmpty()) {
                            // Les figurines n'ont pas encore d'URL d'image, donc on met "null"
                            detailsPieces.add(new LigneAffichage("[Figurine] " + ligne, null));
                        }
                    }
                }

                if (detailsPieces.isEmpty()) {
                    detailsPieces.add(new LigneAffichage("Cette boîte est vide (aucune pièce ni figurine).", null));
                }

                vue.getTableContenu().setItems(detailsPieces);

            } else {
                vue.afficherErreur("Aucune boîte ne porte le numéro : " + numeroSaisi);
            }
        } catch (SQLException e) {
            vue.afficherErreur("Erreur lors de la récupération des composants : " + e.getMessage());
        }
    }

    private void actionRetourHome() {
        CollectionneurHomeVue vueCollec = new CollectionneurHomeVue();
        new CollectionneurHomeControleur(vueCollec, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueCollec, 600, 500));
    }
}