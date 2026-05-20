package fr.univorleans.iut45.briquiuto;

import java.util.List;

public class Collectionneur implements Utilisateur {
    public List<Boite> rechercherBoiteParTheme(Theme theme) {
        // Implémentation de la recherche de boîtes par thème
        return new List<Boite>();
    }
    public BoitePersonnalisee composerBoitePersonnalisee(String nom, List<Piece> pieces) {
        // Implémentation de la composition d'une boîte personnalisée
        return new BoitePersonnalisee(nom, pieces.size(), nom, 0);// nessessite les 4 parametres du constructeur de BoitePersonnalisee
    }   
    public void ajouterCollection(Boite boite) {
        // Implémentation de l'ajout d'une collection à la collection du collectionneur
    }
    public void afficherMenu() {
        // Implémentation de l'affichage du menu pour le collectionneur
    }
}
