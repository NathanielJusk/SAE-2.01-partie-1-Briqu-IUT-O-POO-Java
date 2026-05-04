package fr.univorleans.iut45.briquiuto;
public class Figurine {
    private int idFig;
    private String nomFig;
    private int nbParties;

    public Figurine(String nomFig, int idFig, int nbParties) {
        this.nomFig = nomFig;
        this.idFig = idFig;
        this.nbParties = nbParties;
    }

    public int getIdFig() {
        return idFig;
    }

    public void setIdFig(int idFig) {
        this.idFig = idFig;
    }

    public String getNomFig() {
        return nomFig;
    }

    public void setNomFig(String nomFig) {
        this.nomFig = nomFig;
    }

    public int getNbParties() {
        return nbParties;
    }

    public void setNbParties(int nbParties) {
        this.nbParties = nbParties;
    }

    

}