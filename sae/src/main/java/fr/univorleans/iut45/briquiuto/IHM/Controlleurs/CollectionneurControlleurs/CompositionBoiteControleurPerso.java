package fr.univorleans.iut45.briquiuto.IHM.Controlleurs.CollectionneurControlleurs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.modele.BoitePersonnalisee;
import fr.univorleans.iut45.briquiuto.modele.Couleur;
import fr.univorleans.iut45.briquiuto.modele.Figurine;
import fr.univorleans.iut45.briquiuto.modele.Piece;
import fr.univorleans.iut45.briquiuto.modele.Theme;
import fr.univorleans.iut45.briquiuto.IHM.Controlleurs.AccueilControleur.AccueilControleur;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AccueilVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.collec.CollectionneurHomeVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.collec.VueCompositionBoitePerso;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * Contrôleur pour composer une boîte personnalisée (panier de pièces/figurines).
 * Gère un panier temporaire, l'ajout d'éléments et l'enregistrement final.
 */
public class CompositionBoiteControleurPerso {

    // Vue de composition de boîte perso
    private VueCompositionBoitePerso vue;
    private RequetesLEGO modele;
    private Stage fenetrePrincipale;

    // --- Structures temporaires pour le "panier" ---
    private List<ChoixPiece> panierPieces;
    private List<ChoixFigurine> panierFigurines;
    private int totalElements = 0;

    public CompositionBoiteControleurPerso(VueCompositionBoitePerso vue, RequetesLEGO modele, Stage fenetrePrincipale) {
        this.vue = vue;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        
        this.panierPieces = new ArrayList<>();
        this.panierFigurines = new ArrayList<>();
        
        this.initialiser();
    }

    private void initialiser() {
        chargerComboBoxes();

        // Liaisons des boutons d'action
        vue.getBtnAjouterPiece().setOnAction(e -> handleAjouterPiece());
        vue.getBtnAjouterFigurine().setOnAction(e -> handleAjouterFigurine());
        vue.getBtnValiderBoite().setOnAction(e -> handleValiderCreation());
        
        // --- NOUVEAU : GESTION DES BOUTONS DE NAVIGATION ---
        // Le bouton Maison ramène à l'écran de connexion
        vue.getBtnHome().setOnAction(e -> actionRetourAccueil());
        // Le bouton Retour ramène au menu du collectionneur
        vue.getBtnRetour().setOnAction(e -> actionRetourCollectionneur());
    }

    private void chargerComboBoxes() {
        try {
            // 1. Thèmes
            vue.getCbTheme().setItems(FXCollections.observableArrayList(modele.getAllThemes()));
            vue.getCbTheme().setConverter(new StringConverter<Theme>() {
                @Override public String toString(Theme t) { return t == null ? "" : t.getNom(); }
                @Override public Theme fromString(String s) { return null; }
            });

            // 2. Pièces
            vue.getCbPieces().setItems(FXCollections.observableArrayList(modele.getAllPieces()));
            vue.getCbPieces().setConverter(new StringConverter<Piece>() {
                @Override public String toString(Piece p) { return p == null ? "" : p.getNomPiece(); }
                @Override public Piece fromString(String s) { return null; }
            });

            // 3. Couleurs
            vue.getCbCouleurs().setItems(FXCollections.observableArrayList(modele.getAllCouleurs()));
            vue.getCbCouleurs().setConverter(new StringConverter<Couleur>() {
                @Override public String toString(Couleur c) { return c == null ? "" : c.getNomCoul() + (c.isTransparent() ? " (Trans)" : ""); }
                @Override public Couleur fromString(String s) { return null; }
            });

            // 4. Figurines
            vue.getCbFigurines().setItems(FXCollections.observableArrayList(modele.getAllFigurines()));
            vue.getCbFigurines().setConverter(new StringConverter<Figurine>() {
                @Override public String toString(Figurine f) { return f == null ? "" : f.getNomFig(); }
                @Override public Figurine fromString(String s) { return null; }
            });

        } catch (SQLException e) {
            vue.afficherMessage("Erreur de chargement des données depuis la base.", Color.RED);
        }
    }

    private void handleAjouterPiece() {
        Piece piece = vue.getCbPieces().getValue();
        Couleur couleur = vue.getCbCouleurs().getValue();
        String qteStr = vue.getTxtQuantitePiece().getText().trim();

        if (piece == null || couleur == null || qteStr.isEmpty()) {
            vue.afficherMessage("Veuillez sélectionner une pièce, une couleur et une quantité.", Color.ORANGE);
            return;
        }

        try {
            int qte = Integer.parseInt(qteStr);
            if (qte <= 0) throw new NumberFormatException();

            panierPieces.add(new ChoixPiece(piece, couleur, qte));
            totalElements += qte;
            
            vue.getListeContenuTemporaire().getItems().add("[Pièce] " + qte + "x " + piece.getNomPiece() + " (" + couleur.getNomCoul() + ")");
            vue.majTotal(totalElements);
            
            vue.getTxtQuantitePiece().clear();
            vue.afficherMessage("Pièce ajoutée au panier !", Color.GREEN);

        } catch (NumberFormatException e) {
            vue.afficherMessage("La quantité doit être un nombre entier positif.", Color.RED);
        }
    }

    private void handleAjouterFigurine() {
        Figurine figurine = vue.getCbFigurines().getValue();
        String qteStr = vue.getTxtQuantiteFigurine().getText().trim();

        if (figurine == null || qteStr.isEmpty()) {
            vue.afficherMessage("Veuillez sélectionner une figurine et une quantité.", Color.ORANGE);
            return;
        }

        try {
            int qte = Integer.parseInt(qteStr);
            if (qte <= 0) throw new NumberFormatException();

            panierFigurines.add(new ChoixFigurine(figurine, qte));
            totalElements += qte;
            
            vue.getListeContenuTemporaire().getItems().add("[Figurine] " + qte + "x " + figurine.getNomFig());
            vue.majTotal(totalElements);
            
            vue.getTxtQuantiteFigurine().clear();
            vue.afficherMessage("Figurine ajoutée au panier !", Color.GREEN);

        } catch (NumberFormatException e) {
            vue.afficherMessage("La quantité doit être un nombre entier positif.", Color.RED);
        }
    }

    private void handleValiderCreation() {
        String num = vue.getTxtNumero().getText().trim();
        String nom = vue.getTxtNom().getText().trim();
        String anneeStr = vue.getTxtAnnee().getText().trim();
        Theme theme = vue.getCbTheme().getValue();

        if (num.isEmpty() || nom.isEmpty() || anneeStr.isEmpty() || theme == null) {
            vue.afficherMessage("Veuillez remplir toutes les informations générales.", Color.RED);
            return;
        }

        if (panierPieces.isEmpty() && panierFigurines.isEmpty()) {
            vue.afficherMessage("Votre boîte est vide ! Ajoutez au moins un élément.", Color.RED);
            return;
        }

        try {
            int annee = Integer.parseInt(anneeStr);

            BoitePersonnalisee moc = new BoitePersonnalisee(num, totalElements, nom, annee);
            moc.setTheme(theme);

            modele.ajouterBoite(moc);
            modele.creerContenuPourBoite(num);

            for (ChoixPiece cp : panierPieces) {
                modele.ajouterPieceDansBoite(num, cp.piece.getNumPiece(), cp.couleur.getIdCoul(), cp.qte, false);
            }

            for (ChoixFigurine cf : panierFigurines) {
                modele.ajouterFigurineDansBoite(num, cf.figurine.getIdFig(), cf.qte);
            }

            vue.afficherMessage("Félicitations ! Votre MOC a été enregistrée avec succès.", Color.GREEN);
            vue.getBtnValiderBoite().setDisable(true);

        } catch (NumberFormatException e) {
            vue.afficherMessage("L'année doit être un nombre.", Color.RED);
        } catch (SQLException e) {
            vue.afficherMessage("Erreur BD : Numéro déjà pris ou problème d'insertion.", Color.RED);
            e.printStackTrace();
        }
    }

    // --- NOUVELLES MÉTHODES DE NAVIGATION ---
    
    private void actionRetourCollectionneur() {
        CollectionneurHomeVue vueHome = new CollectionneurHomeVue();
        new CollectionneurHomeControleur(vueHome, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueHome, 1000, 700)); // Taille standardisée !
    }

    private void actionRetourAccueil() {
        AccueilVue vueAccueil = new AccueilVue();
        new AccueilControleur(vueAccueil, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueAccueil, 1000, 700)); // Taille standardisée !
    }

    // --- Classes internes pour stocker temporairement les choix (Panier) ---
    private class ChoixPiece {
        Piece piece;
        Couleur couleur;
        int qte;
        ChoixPiece(Piece p, Couleur c, int qte) { this.piece = p; this.couleur = c; this.qte = qte; }
    }

    private class ChoixFigurine {
        Figurine figurine;
        int qte;
        ChoixFigurine(Figurine f, int qte) { this.figurine = f; this.qte = qte; }
    }
}