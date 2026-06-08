package fr.univorleans.iut45.briquiuto;

import java.util.List;

public class Administrateur implements Utilisateur {

    private BriqueCollectionManager manager;

    public Administrateur(BriqueCollectionManager manager) {
        this.manager = manager;
    }

    public void ajouterBoite(Boite boite) {
        if (boite != null) {
            manager.getCatalogueBoites().add(boite);
        }
    }

    public void ajouterPiece(String numPiece, String nomPiece, Categorie categorie) {
        Piece piece = new Piece(numPiece, nomPiece, categorie);
        manager.getCataloguePieces().add(piece);
    }

    public Theme creerTheme(int id, String nomTheme) {
        Theme theme = new Theme(id, nomTheme);
        manager.getCatalogueThemes().add(theme);
        return theme;
    }

    public Theme creerSousTheme(String nomTheme, Theme parent) {
        Theme sousTheme = new Theme(parent.getIdTheme() + 1, nomTheme);
        parent.ajouterSousTheme(sousTheme);
        return sousTheme;
    }

    public void mettreAJourContenuBoite(Boite boite, Contenu contenu) {
        if (boite != null && contenu != null) {
            boite.setContenu(contenu);
        }
    }

    public void ajouterBoiteComposee(String numero, String nom, int annee,
                                      Theme theme, List<Boite> sousBoites) {
        BoitePersonnalisee boiteComposee = new BoitePersonnalisee(numero, nom, annee, theme, sousBoites);
        ajouterBoite(boiteComposee);
    }

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