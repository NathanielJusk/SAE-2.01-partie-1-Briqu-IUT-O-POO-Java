package fr.univorleans.iut45.briquiuto;

import java.util.List;
import java.util.UUID;
public class BoitePersonnalisee extends Boite{

    public BoitePersonnalisee(String numero, int nbPiece, String nom, int annee) {
        super(numero, nbPiece, nom, annee);
        
    }
    public BoitePersonnalisee(String numero, String nom, int annee, Theme theme, List<Boite> sousBoites) {
        super(numero, 0, nom, annee);
        setTheme(theme);

    }

    public String creeIdentifiantUnique() {
        return "PERSO-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

   public int calculerTotalPieces() {
        if (getContenu() == null) return 0;
        int total = 0;
        for (ContenirP cp : getContenu().getContenirPieces()) {
            total += cp.getQuantiteP();
        }
        return total;
    }

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