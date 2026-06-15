package fr.univorleans.iut45.briquiuto.modele;

import java.util.List;
import java.util.UUID;

/**
 * Représente une boîte LEGO personnalisée créée par l'utilisateur.
 * Une boîte personnalisée peut contenir des pièces et avoir un thème.
 */
public class BoitePersonnalisee extends Boite {

    /**
     * Crée une boîte personnalisée simple avec un nombre de pièces donné.
     */
    public BoitePersonnalisee(String numero, int nbPiece, String nom, int annee) {
        super(numero, nbPiece, nom, annee);
    }

    /**
     * Crée une boîte personnalisée avec un thème et des sous-boîtes.
     *
     * @param numero numéro unique de la boîte
     * @param nom nom de la boîte
     * @param annee année de création
     * @param theme thème de la boîte
     * @param sousBoites liste de boîtes contenues dans la boîte personnalisée
     */
    public BoitePersonnalisee(String numero, String nom, int annee, Theme theme, List<Boite> sousBoites) {
        super(numero, 0, nom, annee);
        setTheme(theme);
    }

    /**
     * Génère un identifiant unique pour une boîte personnalisée.
     *
     * @return chaîne de caractères commençant par "PERSO-"
     */
    public static String creeIdentifiantUnique() {
        return "PERSO-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    /**
     * Calcule le nombre total de pièces dans le contenu de la boîte.
     *
     * @return total des pièces ou 0 si le contenu est absent
     */
    public int calculerTotalPieces() {
        if (getContenu() == null) return 0;
        int total = 0;
        for (ContenirP cp : getContenu().getContenirPieces()) {
            total += cp.getQuantiteP();
        }
        return total;
    }

    /**
     * Affiche les statistiques de la boîte personnalisée.
     */
    @Override
    public void afficherStatistiques() {
        System.out.println("Boîte Personnalisée: " + getNom());
        System.out.println("Nombre total de pièces: " + calculerTotalPieces());
        if (getContenu() != null) {
            System.out.println("Détails des pièces:");
            for (ContenirP cp : getContenu().getContenirPieces()) {
                System.out.println("- " + cp.getPiece().getNomPiece() + " (Quantité: " + cp.getQuantiteP() + ")");
            }
        }
    }
}
