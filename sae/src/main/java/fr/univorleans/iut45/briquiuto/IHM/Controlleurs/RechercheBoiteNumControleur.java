package fr.univorleans.iut45.briquiuto.IHM.Controlleurs;

import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.modele.Boite;
import fr.univorleans.iut45.briquiuto.IHM.Vue.collec.CollectionneurHomeVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.collec.VueRechercheBoiteParNumero;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AccueilVue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contrôleur pour rechercher une boîte par numéro et afficher détails + graphique.
 * Affiche image, texte et répartition des couleurs sous forme de camembert.
 */
public class RechercheBoiteNumControleur {

    // Vue de recherche par numéro
    private VueRechercheBoiteParNumero vue;
    private RequetesLEGO modele;
    private Stage fenetrePrincipale;

    public RechercheBoiteNumControleur(VueRechercheBoiteParNumero vue, RequetesLEGO modele, Stage fenetrePrincipale) {
        this.vue = vue;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        this.initialiser();
    }

    /**
     * Initialise les handlers (rechercher, retour, home).
     */
    private void initialiser() {
        this.vue.getBtnRechercher().setOnAction(e -> actionRechercher());
        this.vue.getBtnRetour().setOnAction(e -> actionRetour());
        this.vue.getBtnHome().setOnAction(e -> actionRetourAccueil());
    }

    private void actionRechercher() {
        String numeroSaisi = vue.getTxtNumeroBoite().getText().trim();

        if (numeroSaisi.isEmpty()) {
            vue.afficherErreur("Veuillez saisir un numéro de boîte !");
            return;
        }

        try {
            Boite boiteTrouvee = modele.rechercherBoiteParNumero(numeroSaisi);

            if (boiteTrouvee != null) {
                vue.cacherErreur();
                vue.getZoneResultat().setVisible(true);

                // 1. Textes
                vue.getLblResultatNom().setText(boiteTrouvee.getNom());
                vue.getLblResultatAnnee().setText("Année de sortie : " + boiteTrouvee.getAnnee());
                vue.getLblResultatNbPieces().setText("Pièces totales : " + boiteTrouvee.getNbPiece());

                // 2. Image
                String urlImage = boiteTrouvee.getImgUrl();
                if (urlImage != null && !urlImage.trim().isEmpty() && !urlImage.equals("null")) {
                    try {
                        vue.getImageBoiteView().setImage(new Image(urlImage.trim(), true));
                    } catch (Exception ex) {
                        vue.getImageBoiteView().setImage(null);
                    }
                } else {
                    vue.getImageBoiteView().setImage(null);
                }

                // 3. Graphique (Top 10 + Autres)
                List<String[]> detailsPieces = modele.getDetailsPiecesBoite(numeroSaisi);
                
                if (detailsPieces == null || detailsPieces.isEmpty()) {
                    vue.getGraphiqueCouleurs().setData(FXCollections.observableArrayList());
                    vue.getGraphiqueCouleurs().setTitle("Aucun inventaire de couleur disponible");
                } else {
                    vue.getGraphiqueCouleurs().setTitle("Répartition des Couleurs (Top 10)");
                    Map<String, Integer> compteCouleurs = new HashMap<>();

                    for (String[] ligne : detailsPieces) {
                        String couleur = ligne[1]; 
                        int quantite = Integer.parseInt(ligne[2]); 
                        compteCouleurs.put(couleur, compteCouleurs.getOrDefault(couleur, 0) + quantite);
                    }

                    // On trie les couleurs par quantité décroissante
                    List<Map.Entry<String, Integer>> listeTriee = new ArrayList<>(compteCouleurs.entrySet());
                    listeTriee.sort((a, b) -> b.getValue().compareTo(a.getValue()));

                    ObservableList<PieChart.Data> donneesGraphique = FXCollections.observableArrayList();
                    int maxCouleurs = 10;
                    int autresQuantite = 0;
                    int index = 0;

                    for (Map.Entry<String, Integer> entree : listeTriee) {
                        if (entree.getValue() > 0) {
                            if (index < maxCouleurs) {
                                donneesGraphique.add(new PieChart.Data(entree.getKey() + " (" + entree.getValue() + ")", entree.getValue()));
                            } else {
                                autresQuantite += entree.getValue();
                            }
                            index++;
                        }
                    }

                    // On ajoute la part "Autres" si besoin
                    if (autresQuantite > 0) {
                        donneesGraphique.add(new PieChart.Data("Autres couleurs (" + autresQuantite + ")", autresQuantite));
                    }

                    vue.getGraphiqueCouleurs().setData(donneesGraphique);
                }

            } else {
                vue.afficherErreur("Aucune boîte ne porte le numéro : " + numeroSaisi);
            }
        } catch (SQLException ex) {
            vue.afficherErreur("Erreur de connexion à la base de données.");
        }
    }

    private void actionRetour() {
        CollectionneurHomeVue vueCollec = new CollectionneurHomeVue();
        new CollectionneurHomeControleur(vueCollec, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueCollec, 1000, 700));
    }

    private void actionRetourAccueil() {
        AccueilVue vueAccueil = new AccueilVue();
        new AccueilControleur(vueAccueil, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueAccueil, 1000, 700));
    }
}