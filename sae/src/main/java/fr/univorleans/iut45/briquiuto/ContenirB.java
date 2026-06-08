package fr.univorleans.iut45.briquiuto;

public class ContenirB {
    private Boite  boite;
    private int quantiteB;

    public ContenirB(Boite boite, int quantiteB) {
        this.boite = boite;
        this.quantiteB = quantiteB;
    }
    
    public Boite getBoite() {
        return boite;
    }
    public void setBoite(Boite boite) {
        this.boite = boite;
    }
    public int getQuantiteB() {
        return quantiteB;
    }
    public void setQuantiteB(int quantiteB) {
        this.quantiteB = quantiteB;
    }


}
