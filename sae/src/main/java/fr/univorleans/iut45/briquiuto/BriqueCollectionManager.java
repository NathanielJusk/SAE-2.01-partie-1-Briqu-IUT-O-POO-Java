package fr.univorleans.iut45.briquiuto;

import java.util.ArrayList;
import java.util.List;

public class BriqueCollectionManager {

    private List<Boite> catalogueBoites;
    private List<Piece> cataloguePieces;
    private List<Theme> catalogueThemes;
    private List<Figurine> catalogueFigurines;

    public BriqueCollectionManager() {
        this.catalogueBoites = new ArrayList<>();
        this.cataloguePieces = new ArrayList<>();
        this.catalogueThemes = new ArrayList<>();
        this.catalogueFigurines = new ArrayList<>();
    }

    // ── Getters ───────────────────────────────────────────────────────────

    public List<Boite> getCatalogueBoites() {
        return catalogueBoites;
    }

    public List<Piece> getCataloguePieces() {
        return cataloguePieces;
    }

    public List<Theme> getCatalogueThemes() {
        return catalogueThemes;
    }

    public List<Figurine> getCatalogueFigurines() {
        return catalogueFigurines;
    }

    // ── Ajout ─────────────────────────────────────────────────────────────

    public void ajouterBoite(Boite boite) {
        if (boite != null && !catalogueBoites.contains(boite)) {
            catalogueBoites.add(boite);
        }
    }

    public void ajouterPiece(String numPiece, String nomPiece, Categorie categorie) {
        Piece piece = new Piece(numPiece, nomPiece, categorie);
        cataloguePieces.add(piece);
    }

    // ── Thèmes ────────────────────────────────────────────────────────────

    public Theme creerTheme(int id, String nomTheme) {
        Theme theme = new Theme(id, nomTheme);
        catalogueThemes.add(theme);
        return theme;
    }

    public void creerSousTheme(String nomTheme, Theme parent) {
        new Theme(parent.getIdTheme() + 1, nomTheme, parent);
    }

    // ── Boîte composée ────────────────────────────────────────────────────

    public BoitePersonnalisee creerBoiteComposee(String numero, String nom, int annee,
            Theme theme, List<Boite> sousBoites) {
        BoitePersonnalisee boite = new BoitePersonnalisee(numero, nom, annee, theme, sousBoites);
        catalogueBoites.add(boite);
        return boite;
    }

    public void mettreAJourContenu(Boite boite, Contenu contenu) {
        if (boite != null && contenu != null) {
            boite.setContenu(contenu);
        }
    }

    public Boite rechercherBoiteParNumero(String numero) {
        for (Boite boite : catalogueBoites) {
            if (boite.getNumero().equals(numero))
                return boite;
        }
        return null;
    }

    public Boite rechercherBoiteParNom(String nom) {
        for (Boite boite : catalogueBoites) {
            if (boite.getNom().equals(nom))
                return boite;
        }
        return null;
    }

    public Boite rechercherBoiteParTheme(Theme theme) {
        for (Boite boite : catalogueBoites) {
            if (boite.getTheme() != null && boite.getTheme().equals(theme))
                return boite;
        }
        return null;
    }

    public Boite rechercherBoiteParPiece(Piece piece) {
        for (Boite boite : catalogueBoites) {
            if (boite.getContenu() != null) {
                for (ContenirP cp : boite.getContenu().getContenirPieces()) {
                    if (cp.getPiece().equals(piece))
                        return boite;
                }
            }
        }
        return null;
    }
}