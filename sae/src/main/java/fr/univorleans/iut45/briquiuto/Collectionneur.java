package fr.univorleans.iut45.briquiuto;

import java.util.ArrayList;
import java.util.List;

public class Collectionneur implements Utilisateur {

    private List<Boite> collectionPersonnelle;
    private BriqueCollectionManager manager;

    public Collectionneur(BriqueCollectionManager manager) {
        this.manager = manager;
        this.collectionPersonnelle = new ArrayList<>();
    }

    public BoitePersonnalisee composerBoitePersonnalisee(String nom, int annee,
            Theme theme, List<Boite> sousBoites) {
        String numero = BoitePersonnalisee.creeIdentifiantUnique();
        BoitePersonnalisee boite = new BoitePersonnalisee(numero, nom, annee, theme, sousBoites);
        manager.ajouterBoite(boite);
        return boite;
    }

    public void ajouterCollection(Boite boite) {
        if (boite != null && !collectionPersonnelle.contains(boite)) {
            collectionPersonnelle.add(boite);
        }
    }

    public List<Boite> getCollectionPersonnelle() {
        return collectionPersonnelle;
    }

    @Override
    public void afficherMenu() {
        System.out.println("=== Menu Collectionneur ===");
        System.out.println("1. Rechercher une boîte par thème");
        System.out.println("2. Composer une boîte personnalisée");
        System.out.println("3. Ajouter à ma collection");
        System.out.println("4. Afficher ma collection");
    }
}