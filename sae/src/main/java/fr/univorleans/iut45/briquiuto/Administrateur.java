package fr.univorleans.iut45.briquiuto;

public class Administrateur implements utilisateur {
    public void ajouterBoite(Boite boite) {
        // Implémentation pour ajouter une boîte à la collection
    }
    public void ajouterPiece(String numPiece, String nomPiece, Categorie categorie) {
        // Implémentation pour ajouter une pièce à la collection
    }
    public Theme creerTheme(String nomTheme) {
        // Implémentation pour créer un thème
        return new Theme(nomTheme);
    }
    public void mettreAJourContenuBoite(Boite boite, Contenu contenu) {
        // Implémentation pour mettre à jour le contenu d'une boîte
    }
    public Theme creerSousTheme(String nomTheme, Theme parent) {
        // Implémentation pour créer un sous-thème
        Theme sousTheme = new Theme(nomTheme);
        parent.ajouterSousTheme(sousTheme);
        return sousTheme;
    }
    public void ajouterBoiteComposee(String numero, String nom, int annee, Theme theme, List<Boite> sousBoites) {
        // Implémentation pour ajouter une boîte composée à la collection
    }
    public void afficherMenu(){
        // Implémentation pour afficher le menu de l'administrateur
    }
}
