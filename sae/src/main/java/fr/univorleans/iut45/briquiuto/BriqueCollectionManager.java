package fr.univorleans.iut45.briquiuto;
import java.util.ArrayList;
import java.util.List;


public class BriqueCollectionManager{

    private List<Boite> catalogueBoites;
    private List<Piece> cataloguePieces;
    private List<Theme> catalogueThemes;
    private List<Figurine> catalogueFigurines;

    public BriqueCollectionManager() {
        // Constructeur
        this.catalogueBoites = new ArrayList<>();
        this.cataloguePieces = new ArrayList<>();
        this.catalogueThemes = new ArrayList<>();
        this.catalogueFigurines = new ArrayList<>();
    }
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
    public Boite rechercherBoiteParNumero(String numero) {

        for (Boite boite : catalogueBoites) {
            if (boite.getNumero().equals(numero)) {
                return boite;
            }
        }
        return null; 
    }
    public Boite rechercherBoiteParNom(String nom) {
        for (Boite boite : catalogueBoites) {
            if (boite.getNom().equals(nom)) {
                return boite;
            }
        }
        return null; 
    }
    public Boite rechercherBoiteParTheme(Theme theme) {
        for (Boite boite : catalogueBoites) {
            if (boite.getTheme() != null && boite.getTheme().getNom().equals(theme.getNom())) {
                return boite;
            }
        }
        return null;
    }
    public Boite rechercherBoiteParPiece(Piece piece) {

        for (Boite boite : catalogueBoites) {
            if (boite.getContenu() != null && boite.getContenu().getContenirPieces() != null) {
                for (ContenirP p : boite.getContenu().getContenirPieces()) {
                    if (p.getPiece().equals(piece)) {
                        return boite;
                    }
                }
            }
        }
        return null;
    }
    public BoitePersonnalisee creerBoiteComposee(String numero, String nom, int annee, Theme theme, List<Boite> sousBoites) {
        // Implémentation pour créer une boîte composée
        return new BoitePersonnalisee(numero, nom, annee, theme, sousBoites);
    }
    public void ajouterACollectionPersonnelle(Boite boite) {
        // Implémentation pour ajouter une boîte à la collection personnelle
    }
    public void ajouterBoite(Boite boite) {
        // Implémentation pour ajouter une boîte à la collection
        catalogueBoites.add(boite);
    }
    public void ajouterPiece(String numPiece, String nomPiece, Categorie categorie) {
        // Implémentation pour ajouter une pièce à la collection
        Piece piece = new Piece(numPiece, nomPiece, categorie);
        cataloguePieces.add(piece);
    }
    public Theme creerTheme(int id, String nomTheme) {
        // Implémentation pour créer un thème
        return new Theme(id,nomTheme);
    }
    public void creerSousTheme(String nomTheme, Theme parent) {
        // Implémentation pour créer un sous-thème
        Theme sousTheme = new Theme( parent.getIdTheme() + 1, nomTheme);
        parent.ajouterSousTheme(sousTheme);
    }
    public void mettreAJourContenu(Boite boite, Contenu contenu) {
        // Implémentation pour mettre à jour le contenu d'une boîte
        boite.setContenu(contenu);
    }
}