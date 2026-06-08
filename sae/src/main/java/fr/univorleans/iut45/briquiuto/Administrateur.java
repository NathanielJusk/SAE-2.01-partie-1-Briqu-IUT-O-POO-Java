package fr.univorleans.iut45.briquiuto;

import java.util.List;

public class Administrateur implements Utilisateur {
        private BriqueCollectionManager manager;
            private List<Boite> boites;


    public void ajouterBoite(Boite boite) {
        // Implémentation pour ajouter une boîte à la collection
        boites.add(boite);

    }
    public void ajouterPiece(String numPiece, String nomPiece, Categorie categorie) {
        // Implémentation pour ajouter une pièce à la collection
    }
    public Theme creerTheme(int id, String nomTheme) {
        // Implémentation pour créer un thème
        return new Theme(id, nomTheme);
    }
    public void mettreAJourContenuBoite(Boite boite, Contenu contenu) {
        // Implémentation pour mettre à jour le contenu d'une boîte
    }
    public Theme creerSousTheme(String nomTheme, Theme parent) {
        // Implémentation pour créer un sous-thème
        Theme sousTheme = new Theme(parent.getIdTheme() + 1, nomTheme);
        parent.ajouterSousTheme(sousTheme);
        return sousTheme;
    }
    public void ajouterBoiteComposee(String numero, String nom, int annee, Theme theme, List<Boite> sousBoites) {
        // Implémentation pour ajouter une boîte composée à la collection
        BoitePersonnalisee boiteComposee = new BoitePersonnalisee(numero, nom, annee, theme, sousBoites);
        ajouterBoite(boiteComposee);    
        
    }
    public void afficherMenu(){
        // Implémentation pour afficher le menu de l'administrateur
    }
}
