package fr.univorleans.iut45.briquiuto;
public class Figurine {
    private int idFig;
    private String nomFig;
    private int nbParties;

    public Figurine( int idFig, String nomFig, int nbParties) {
        this.nomFig = nomFig;
        this.idFig = idFig;
        this.nbParties = nbParties;
    }
    public Figurine(String nomFig, int nbParties) {
        this.nomFig = nomFig;
        this.idFig = 0;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null)return false;
        if (!(o instanceof Figurine)) return false;
        Figurine figurine = (Figurine) o;
        return idFig == figurine.idFig;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(idFig);
    }
    
    @Override
    public String toString() {
        return "Figurine{id=" + idFig +
               ", nom='" + nomFig + "'" +
               ", nbParties=" + nbParties + "}";
    }
}