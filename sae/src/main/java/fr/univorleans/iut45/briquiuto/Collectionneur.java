package fr.univorleans.iut45.briquiuto;

import java.util.ArrayList;
import java.util.List;

public class Collectionneur implements Utilisateur {

    private List<Boite> collectionPersonnelle;
    private BriqueCollectionManager manager;

    public Collectionneur() {
        this.collectionPersonnelle = new ArrayList<>();
        this.manager = new BriqueCollectionManager();
    }

    public List<Boite> rechercherBoitesParTheme(Theme theme) {
        List<Boite> boites = new ArrayList<>();
        for (Boite boite : manager.getCatalogueBoites()) {
            if (boite.getTheme() == theme) {
                boites.add(boite);
            }
        }
        return boites;
    }
    @Override
    public void afficherMenu() {
        System.out.println("=== Menu Collectionneur ===");
        System.out.println("1. Rechercher une boîte par thème");
        System.out.println("2. Composer une boîte personnalisée");
        System.out.println("3. Ajouter à ma collection");
        System.out.println("4. Afficher ma collection");
    }

    public void composerBoitePersonnalisee(String numero, String nom, int annee, Theme theme, List<Boite> sousBoites) {
        BoitePersonnalisee boitePersonnalisee = new BoitePersonnalisee(numero, nom, annee, theme, sousBoites);
        manager.ajouterBoite(boitePersonnalisee);
    }

    public void ajouterCollection(Boite boite) {
        collectionPersonnelle.add(boite);
    }
}
