package fr.univorleans.iut45.briquiuto;


public class ContenirF {
    private Figurine figurine;
    private int quantiteF;

    public ContenirF(Figurine figurine, int quantiteF) {
        this.figurine = figurine;
        this.quantiteF = quantiteF;
    }
    
    public Figurine getFigurine() {
        return figurine;
    }
    public void setFigurine(Figurine figurine) {
        this.figurine = figurine;
    }
    public int getQuantiteF() {
        return quantiteF;
    }
    public void setQuantiteF(int quantiteF) {
        this.quantiteF = quantiteF;
    }
    
}

