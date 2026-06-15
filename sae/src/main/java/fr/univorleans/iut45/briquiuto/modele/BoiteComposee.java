package fr.univorleans.iut45.briquiuto.modele;

/**
 * Représente une boîte LEGO standard qui n'est pas personnalisée.
 * Cette boîte affiche ses informations de base.
 */
public class BoiteComposee extends Boite{

    /**
     * Crée une boîte composée avec un numéro, un nom, une année et un nombre de pièces.
     */
    public BoiteComposee(String numero, int nbPiece, String nom, int annee) {
        super(numero, nbPiece, nom, annee);
    }

    /**
     * Affiche les statistiques de la boîte composée.
     */
    @Override
    public void afficherStatistiques() {
        System.out.println("Boîte Composée: " + getNom());
        System.out.println("Numéro: " + getNumero());
        System.out.println("Nombre de pièces: " + getNbPiece());
        System.out.println("Année de sortie: " + getAnnee());
    }
}