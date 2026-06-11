package fr.univorleans.iut45.briquiuto;

import java.util.ArrayList;
import java.util.List;

/**
 * Gère les catalogues de boîtes, pièces, thèmes et figurines.
 * Cette classe conserve la liste des objets disponibles et propose
 * des méthodes pour ajouter ou rechercher des éléments.
 */
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

    /**
     * Retourne la liste de toutes les boîtes du catalogue.
     *
     * @return liste de boîtes
     */
    public List<Boite> getCatalogueBoites() {
        return catalogueBoites;
    }

    /**
     * Retourne la liste de toutes les pièces du catalogue.
     *
     * @return liste de pièces
     */
    public List<Piece> getCataloguePieces() {
        return cataloguePieces;
    }

    /**
     * Retourne la liste des thèmes du catalogue.
     *
     * @return liste de thèmes
     */
    public List<Theme> getCatalogueThemes() {
        return catalogueThemes;
    }

    /**
     * Retourne la liste des figurines du catalogue.
     *
     * @return liste de figurines
     */
    public List<Figurine> getCatalogueFigurines() {
        return catalogueFigurines;
    }

    // ── Ajout ─────────────────────────────────────────────────────────────

    /**
     * Ajoute une boîte dans le catalogue si elle n'existe pas déjà.
     *
     * @param boite boîte à ajouter
     */
    public void ajouterBoite(Boite boite) {
        if (boite != null && !catalogueBoites.contains(boite)) {
            catalogueBoites.add(boite);
        }
    }

    /**
     * Ajoute une pièce dans le catalogue.
     *
     * @param numPiece numéro de la pièce
     * @param nomPiece nom de la pièce
     * @param categorie catégorie de la pièce
     */
    public void ajouterPiece(String numPiece, String nomPiece, Categorie categorie) {
        Piece piece = new Piece(numPiece, nomPiece, categorie);
        cataloguePieces.add(piece);
    }

    // ── Thèmes ────────────────────────────────────────────────────────────

    /**
     * Crée un thème et l'ajoute au catalogue.
     *
     * @param id identifiant du thème
     * @param nomTheme nom du thème
     * @return thème créé
     */
    public Theme creerTheme(int id, String nomTheme) {
        Theme theme = new Theme(id, nomTheme);
        catalogueThemes.add(theme);
        return theme;
    }

    /**
     * Crée un sous-thème pour un thème parent existant.
     *
     * @param nomTheme nom du sous-thème
     * @param parent thème parent
     */
    public void creerSousTheme(String nomTheme, Theme parent) {
        new Theme(parent.getIdTheme() + 1, nomTheme, parent);
    }

    // ── Boîte composée ────────────────────────────────────────────────────

    /**
     * Crée une boîte personnalisée et l'ajoute au catalogue.
     *
     * @param numero numéro de la boîte
     * @param nom nom de la boîte
     * @param annee année de création
     * @param theme thème de la boîte
     * @param sousBoites boîtes contenues dans cette boîte
     * @return la boîte personnalisée créée
     */
    public BoitePersonnalisee creerBoiteComposee(String numero, String nom, int annee,
            Theme theme, List<Boite> sousBoites) {
        BoitePersonnalisee boite = new BoitePersonnalisee(numero, nom, annee, theme, sousBoites);
        catalogueBoites.add(boite);
        return boite;
    }

    /**
     * Met à jour le contenu d'une boîte.
     *
     * @param boite boîte à mettre à jour
     * @param contenu contenu de la boîte
     */
    public void mettreAJourContenu(Boite boite, Contenu contenu) {
        if (boite != null && contenu != null) {
            boite.setContenu(contenu);
        }
    }

    /**
     * Recherche une boîte par son numéro.
     *
     * @param numero numéro de la boîte
     * @return boîte trouvée ou null si aucune
     */
    public Boite rechercherBoiteParNumero(String numero) {
        for (Boite boite : catalogueBoites) {
            if (boite.getNumero().equals(numero))
                return boite;
        }
        return null;
    }

    /**
     * Recherche une boîte par son nom.
     *
     * @param nom nom de la boîte
     * @return boîte trouvée ou null si aucune
     */
    public Boite rechercherBoiteParNom(String nom) {
        for (Boite boite : catalogueBoites) {
            if (boite.getNom().equals(nom))
                return boite;
        }
        return null;
    }

    // public Boite rechercherBoiteParTheme(Theme theme) {
    //     for (Boite boite : catalogueBoites) {
    //         if (boite.getTheme() != null && boite.getTheme().equals(theme))
    //             return boite;
    //     }
    //     return null;
    // }

    /**
     * Recherche une boîte qui contient une pièce donnée.
     *
     * @param piece pièce recherchée
     * @return boîte contenant la pièce ou null si aucune
     */
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