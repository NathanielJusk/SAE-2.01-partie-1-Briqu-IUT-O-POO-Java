package fr.univorleans.iut45.briquiuto;

import java.util.List;

public class BoitePersonnalisee extends Boite{

    public BoitePersonnalisee(String numero, int nbPiece, String nom, int annee) {
        super(numero, nbPiece, nom, annee);
        
    }

     public String creeIdentifiantUnique(){
        return "Perso-" + Math.random();
    } 
    public void CalculerTotalPieces(){

    }

    @Override
    public void afficherStatistiques() {
        // Implémentation
    }

    public Boite composerBoitePersonnalisee(String nom, List<Piece> pieces, int annee){
        Boite boite = new BoitePersonnalisee(creeIdentifiantUnique(), pieces.size(), nom, annee);
        return boite;
    }

}