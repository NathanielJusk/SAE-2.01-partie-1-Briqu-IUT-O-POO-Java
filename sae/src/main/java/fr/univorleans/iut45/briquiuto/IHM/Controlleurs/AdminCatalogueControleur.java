package fr.univorleans.iut45.briquiuto.IHM.Controlleurs;

import java.sql.SQLException;
import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;
import fr.univorleans.iut45.briquiuto.modele.Boite;
import fr.univorleans.iut45.briquiuto.modele.Piece;
import fr.univorleans.iut45.briquiuto.modele.Theme;
import fr.univorleans.iut45.briquiuto.modele.Categorie;
import fr.univorleans.iut45.briquiuto.modele.Figurine;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AdminCatalogueVue;
import fr.univorleans.iut45.briquiuto.IHM.Vue.AdminHomeVue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class AdminCatalogueControleur {

    private AdminCatalogueVue vue;
    private RequetesLEGO modele;
    private Stage fenetrePrincipale;

    public AdminCatalogueControleur(AdminCatalogueVue vue, RequetesLEGO modele, Stage fenetrePrincipale) {
        this.vue = vue;
        this.modele = modele;
        this.fenetrePrincipale = fenetrePrincipale;
        this.initialiser();
    }

    private void initialiser() {
        try {
            // 1. Chargement des données depuis la base
            ObservableList<Boite> listeBoites = FXCollections.observableArrayList(modele.getAllBoites());
            ObservableList<Piece> listePieces = FXCollections.observableArrayList(modele.getAllPieces());
            ObservableList<Theme> listeThemes = FXCollections.observableArrayList(modele.getAllThemes());
            ObservableList<Categorie> listeCategories = FXCollections.observableArrayList(modele.getAllCategories());
            ObservableList<Figurine> listeFigurines = FXCollections.observableArrayList(modele.getAllFigurines());

            vue.getCbFiltreThemeBoite().setItems(listeThemes);
            vue.getCbFiltreCatPiece().setItems(listeCategories);

            // 2. Filtres pour les boîtes
            FilteredList<Boite> filteredBoites = new FilteredList<>(listeBoites, b -> true);
            Runnable updateFiltreBoites = () -> {
                Theme themeSelectionne = vue.getCbFiltreThemeBoite().getValue();
                String texteRecherche = vue.getTxtFiltreNomBoite().getText().toLowerCase();

                filteredBoites.setPredicate(boite -> {
                    boolean matchTheme = (themeSelectionne == null) || 
                                         (boite.getTheme() != null && boite.getTheme().getIdTheme() == themeSelectionne.getIdTheme());
                    boolean matchText = texteRecherche.isEmpty() || 
                                        boite.getNom().toLowerCase().contains(texteRecherche) || 
                                        boite.getNumero().toLowerCase().contains(texteRecherche);
                    return matchTheme && matchText;
                });
            };
            vue.getCbFiltreThemeBoite().valueProperty().addListener((obs, old, newVal) -> updateFiltreBoites.run());
            vue.getTxtFiltreNomBoite().textProperty().addListener((obs, old, newVal) -> updateFiltreBoites.run());
            vue.getBtnClearBoite().setOnAction(e -> {
                vue.getCbFiltreThemeBoite().getSelectionModel().clearSelection();
                vue.getTxtFiltreNomBoite().clear();
            });
            lierEtAfficher(filteredBoites, vue.getTableBoites());

            // 3. Filtres pour les pièces
            FilteredList<Piece> filteredPieces = new FilteredList<>(listePieces, p -> true);
            Runnable updateFiltrePieces = () -> {
                Categorie catSelectionnee = vue.getCbFiltreCatPiece().getValue();
                String texteRecherche = vue.getTxtFiltreNomPiece().getText().toLowerCase();

                filteredPieces.setPredicate(piece -> {
                    boolean matchCat = (catSelectionnee == null) || 
                                       (piece.getCategorie() != null && piece.getCategorie().getIdCat() == catSelectionnee.getIdCat());
                    boolean matchText = texteRecherche.isEmpty() || 
                                        (piece.getNomPiece() != null && piece.getNomPiece().toLowerCase().contains(texteRecherche)) || 
                                        (piece.getNumPiece() != null && piece.getNumPiece().toLowerCase().contains(texteRecherche));
                    return matchCat && matchText;
                });
            };
            vue.getCbFiltreCatPiece().valueProperty().addListener((obs, old, newVal) -> updateFiltrePieces.run());
            vue.getTxtFiltreNomPiece().textProperty().addListener((obs, old, newVal) -> updateFiltrePieces.run());
            vue.getBtnClearPiece().setOnAction(e -> {
                vue.getCbFiltreCatPiece().getSelectionModel().clearSelection();
                vue.getTxtFiltreNomPiece().clear();
            });
            lierEtAfficher(filteredPieces, vue.getTablePieces());

            // 4. Filtres pour les thèmes
            FilteredList<Theme> filteredThemes = new FilteredList<>(listeThemes, t -> true);
            vue.getTxtFiltreNomTheme().textProperty().addListener((observable, oldValue, newValue) -> {
                filteredThemes.setPredicate(theme -> {
                    if (newValue == null || newValue.isEmpty()) return true;
                    String lowerCaseFilter = newValue.toLowerCase();
                    return (theme.getNom() != null && theme.getNom().toLowerCase().contains(lowerCaseFilter)) || 
                           String.valueOf(theme.getIdTheme()).contains(lowerCaseFilter);
                });
            });
            lierEtAfficher(filteredThemes, vue.getTableThemes());

            // 5. Filtres pour les figurines
            FilteredList<Figurine> filteredFigurines = new FilteredList<>(listeFigurines, f -> true);
            vue.getTxtFiltreNomFigurine().textProperty().addListener((observable, oldValue, newValue) -> {
                filteredFigurines.setPredicate(figurine -> {
                    if (newValue == null || newValue.isEmpty()) return true;
                    String lowerCaseFilter = newValue.toLowerCase();
                    return (figurine.getNomFig() != null && figurine.getNomFig().toLowerCase().contains(lowerCaseFilter)) || 
                           (figurine.getIdFig() != null && figurine.getIdFig().toLowerCase().contains(lowerCaseFilter));
                });
            });
            lierEtAfficher(filteredFigurines, vue.getTableFigurines());

        } catch (SQLException e) {
            Alert alerte = new Alert(Alert.AlertType.ERROR);
            alerte.setTitle("Erreur");
            alerte.setContentText("Erreur lors de la récupération des données : " + e.getMessage());
            alerte.showAndWait();
        }

        this.vue.getBtnHome().setOnAction(e -> actionRetourAdmin());
    }

    private <T> void lierEtAfficher(FilteredList<T> filteredList, javafx.scene.control.TableView<T> tableView) {
        SortedList<T> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);
    }

    private void actionRetourAdmin() {
        AdminHomeVue vueAdmin = new AdminHomeVue();
        new AdminHomeControleur(vueAdmin, modele, fenetrePrincipale);
        fenetrePrincipale.setScene(new Scene(vueAdmin, 600, 500));
    }
}