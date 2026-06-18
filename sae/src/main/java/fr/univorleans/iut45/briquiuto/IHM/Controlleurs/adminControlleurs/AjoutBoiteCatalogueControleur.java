package fr.univorleans.iut45.briquiuto.IHM.Controlleurs.adminControlleurs;

import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.IHM.Vue.admin.AdminHomeVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.admin.VueAjoutBoiteCatalogueAdmin;
import fr.univorleans.iut45.briquiuto.IHM.Controlleurs.AccueilControleur.AccueilControleur;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AccueilVue;
import fr.univorleans.iut45.briquiuto.modele.Boite;
import fr.univorleans.iut45.briquiuto.modele.BoiteComposee;
import fr.univorleans.iut45.briquiuto.modele.Piece;
import fr.univorleans.iut45.briquiuto.modele.Theme;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

/**
 * Contrôleur pour ajouter une boîte dans le catalogue officiel (admin).
 * Charge thèmes/pieces, valide le formulaire et enregistre en base.
 */
public class AjoutBoiteCatalogueControleur {

    // Vue du formulaire d'ajout catalogue (admin)
    private VueAjoutBoiteCatalogueAdmin vue;
    private RequetesLEGO modele;
    private Stage fenetrePrincipale;

    public AjoutBoiteCatalogueControleur(VueAjoutBoiteCatalogueAdmin vue, RequetesLEGO modele, Stage fenetrePrincipale) {
        this.vue = vue;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        this.initialiser();
    }

    /**
     * Initialise la vue : remplissage des listes et handlers des boutons.
     */
    private void initialiser() {
        // 1. Charger les données dans les menus déroulants
        chargerDonneesInitiales();

        // 2. Définir les actions des boutons
        vue.getBtnValider().setOnAction(e -> actionEnregistrerBoite());

        // Navigation
        vue.getBtnRetour().setOnAction(e -> actionRetourAdmin());
        vue.getBtnHome().setOnAction(e -> actionRetourAccueil());
    }

    private void chargerDonneesInitiales() {
        try {
            List<Theme> themes = modele.getAllThemes();
            vue.setThemesDisponibles(themes);

            List<Piece> pieces = modele.getAllPieces();
            vue.setPiecesDisponibles(pieces);

        } catch (SQLException e) {
            vue.afficherMessage("Erreur de chargement depuis la BD : " + e.getMessage(), "#E3000B");
        }
    }

    private void actionEnregistrerBoite() {
        String numero = vue.getTxtNumero().getText().trim();
        String nom = vue.getTxtNom().getText().trim();
        String anneeStr = vue.getTxtAnnee().getText().trim();
        Theme themeChoisi = vue.getCbTheme().getValue();

        if (numero.isEmpty() || nom.isEmpty() || anneeStr.isEmpty() || themeChoisi == null) {
            vue.afficherMessage("Veuillez remplir tous les champs de base et choisir un thème.", "#E3000B"); 
            return;
        }

        int annee;
        try {
            annee = Integer.parseInt(anneeStr);
        } catch (NumberFormatException e) {
            vue.afficherMessage("L'année doit être un nombre valide.", "#E3000B");
            return;
        }

        int totalPieces = 0;
        for (VueAjoutBoiteCatalogueAdmin.LignePieceContenu ligne : vue.getListeLignesChoisies()) {
            Piece p = ligne.getCbPiece().getValue();
            String qteStr = ligne.getTxtQuantite().getText().trim();
            if (p != null && !qteStr.isEmpty()) {
                try {
                    totalPieces += Integer.parseInt(qteStr);
                } catch (NumberFormatException ignored) { }
            }
        }

        try {
            Boite boiteExistante = modele.rechercherBoiteParNumero(numero);
            if (boiteExistante != null) {
                vue.afficherMessage("Une boîte avec ce numéro existe déjà dans le catalogue.", "#E3000B");
                return;
            }

            Boite nouvelleBoite = new BoiteComposee(numero, totalPieces, nom, annee);
            nouvelleBoite.setTheme(themeChoisi);
            nouvelleBoite.setImgUrl(""); 

            modele.ajouterBoite(nouvelleBoite);
            modele.creerContenuPourBoite(numero);

            for (VueAjoutBoiteCatalogueAdmin.LignePieceContenu ligne : vue.getListeLignesChoisies()) {
                Piece p = ligne.getCbPiece().getValue();
                String qteStr = ligne.getTxtQuantite().getText().trim();
                
                if (p != null && !qteStr.isEmpty()) {
                    try {
                        int qte = Integer.parseInt(qteStr);
                        if (qte > 0) {
                            modele.ajouterPieceDansBoite(numero, p.getNumPiece(), 1, qte, false);
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println("Ligne ignorée : Quantité invalide (" + qteStr + ")");
                    }
                }
            }

            vue.afficherMessage("La boîte " + numero + " a été ajoutée avec succès au catalogue !", "#287F46"); 
            vue.reinitialiserFormulaire();

        } catch (SQLException e) {
            vue.afficherMessage("Erreur SQL lors de l'enregistrement : " + e.getMessage(), "#E3000B");
        }
    }

    // --- NAVIGATION ---
    
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