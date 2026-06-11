package fr.univorleans.iut45.briquiuto;

import java.util.List;

/**
 * Représente un administrateur de la collection LEGO.
 * L'administrateur peut ajouter des boîtes, des pièces, des thèmes
 * et modifier le contenu d'une boîte.
 */
public class Administrateur implements Utilisateur {

    private BriqueCollectionManager manager;

    /**
     * Crée un administrateur lié à un gestionnaire de collection.
     *
     * @param manager gestionnaire de collection utilisé par l'administrateur
     */
    public Administrateur(BriqueCollectionManager manager) {
        this.manager = manager;
    }

    /**
     * Ajoute une boîte dans le catalogue.
     *
     * @param boite boîte à ajouter
     */
    public void ajouterBoite(Boite boite) {
        if (boite != null) {
            manager.getCatalogueBoites().add(boite);
        }
    }

    /**
     * Ajoute une nouvelle pièce dans le catalogue.
     *
     * @param numPiece numéro de la pièce
     * @param nomPiece nom de la pièce
     * @param categorie catégorie de la pièce
     */
    public void ajouterPiece(String numPiece, String nomPiece, Categorie categorie) {
        Piece piece = new Piece(numPiece, nomPiece, categorie);
        manager.getCataloguePieces().add(piece);
    }

    /**
     * Crée un thème et l'ajoute au catalogue.
     *
     * @param id identifiant du thème
     * @param nomTheme nom du thème
     * @return le thème créé
     */
    public Theme creerTheme(int id, String nomTheme) {
        Theme theme = new Theme(id, nomTheme);
        manager.getCatalogueThemes().add(theme);
        return theme;
    }

    /**
     * Crée un sous-thème pour un thème existant.
     *
     * @param nomTheme nom du sous-thème
     * @param parent thème parent
     * @return le sous-thème créé
     */
    public Theme creerSousTheme(String nomTheme, Theme parent) {
        Theme sousTheme = new Theme(parent.getIdTheme() + 1, nomTheme);
        parent.ajouterSousTheme(sousTheme);
        return sousTheme;
    }

    /**
     * Met à jour le contenu d'une boîte.
     *
     * @param boite boîte à mettre à jour
     * @param contenu nouveau contenu
     */
    public void mettreAJourContenuBoite(Boite boite, Contenu contenu) {
        if (boite != null && contenu != null) {
            boite.setContenu(contenu);
        }
    }

    /**
     * Ajoute une boîte personnalisée au catalogue.
     *
     * @param numero numéro de la boîte
     * @param nom nom de la boîte
     * @param annee année de création
     * @param theme thème de la boîte
     * @param sousBoites boîtes contenues dans la boîte personnalisée
     */
    public void ajouterBoiteComposee(String numero, String nom, int annee,
                                      Theme theme, List<Boite> sousBoites) {
        BoitePersonnalisee boiteComposee = new BoitePersonnalisee(numero, nom, annee, theme, sousBoites);
        ajouterBoite(boiteComposee);
    }

    /**
     * Affiche le menu de l'administrateur.
     */
    @Override
    public void afficherMenu() {
        System.out.println("=== Menu Administrateur ===");
        System.out.println("1. Ajouter une boîte");
        System.out.println("2. Ajouter une pièce");
        System.out.println("3. Créer un thème");
        System.out.println("4. Créer un sous-thème");
        System.out.println("5. Mettre à jour le contenu d'une boîte");
        System.out.println("6. Ajouter une boîte composée");
    }
}