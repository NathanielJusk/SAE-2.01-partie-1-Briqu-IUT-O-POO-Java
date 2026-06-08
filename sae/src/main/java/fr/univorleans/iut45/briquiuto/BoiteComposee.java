package fr.univorleans.iut45.briquiuto;
public class BoiteComposee extends Boite{

    public BoiteComposee(String numero, int nbPiece, String nom, int annee) {
        super(numero, nbPiece, nom, annee);
        
    }

    @Override
    public void afficherStatistiques() {
        // Implémentation
            System.out.println("Boîte Composée: " + getNom());
            System.out.println("Numéro: " + getNumero());
            System.out.println("Nombre de pièces: " + getNbPiece());
            System.out.println("Année de sortie: " + getAnnee());
    }
}