package fr.univorleans.iut45.briquiuto;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente un collectionneur de boîtes LEGO.
 * Le collectionneur peut créer une boîte personnalisée
 * et ajouter des boîtes à sa collection personnelle.
 */
public class Collectionneur implements Utilisateur {

    private List<Boite> collectionPersonnelle;
    private BriqueCollectionManager manager;

    /**
     * Crée un collectionneur en lui donnant un manager de collection.
     *
     * @param manager gestionnaire de collection utilisé par le collectionneur
     */
    public Collectionneur(BriqueCollectionManager manager) {
        this.manager = manager;
        this.collectionPersonnelle = new ArrayList<>();
    }

    /**
     * Compose une boîte personnalisée avec un nom, une année et un thème.
     *
     * @param nom nom de la boîte
     * @param annee année de création
     * @param theme thème de la boîte
     * @param sousBoites boîtes qui seront incluses dans la boîte personnalisée
     * @return la boîte personnalisée créée
     */
    public BoitePersonnalisee composerBoitePersonnalisee(String nom, int annee,
            Theme theme, List<Boite> sousBoites) {
        String numero = BoitePersonnalisee.creeIdentifiantUnique();
        BoitePersonnalisee boite = new BoitePersonnalisee(numero, nom, annee, theme, sousBoites);
        manager.ajouterBoite(boite);
        return boite;
    }

    /**
     * Ajoute une boîte à la collection personnelle du collectionneur.
     *
     * @param boite boîte à ajouter
     */
    public void ajouterCollection(Boite boite) {
        if (boite != null && !collectionPersonnelle.contains(boite)) {
            collectionPersonnelle.add(boite);
        }
    }

    /**
     * Retourne la collection personnelle du collectionneur.
     *
     * @return liste de boîtes dans la collection personnelle
     */
    public List<Boite> getCollectionPersonnelle() {
        return collectionPersonnelle;
    }

    /**
     * Affiche le menu du collectionneur.
     */
    @Override
    public void afficherMenu() {
        System.out.println("=== Menu Collectionneur ===");
        System.out.println("1. Rechercher une boîte par thème");
        System.out.println("2. Composer une boîte personnalisée");
        System.out.println("3. Ajouter à ma collection");
        System.out.println("4. Afficher ma collection");
    }
}