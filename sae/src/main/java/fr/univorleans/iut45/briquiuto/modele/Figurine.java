package fr.univorleans.iut45.briquiuto.modele;

/**
 * Représente une figurine LEGO.
 * Une figurine a un identifiant, un nom et un nombre de pièces.
 */
public class Figurine {
    private String idFig;
    private String nomFig;
    private int nbParties;
    private String imgUrl;

    /**
     * Crée une figurine avec un identifiant, un nom et un nombre de pièces.
     *
     * @param idFig identifiant de la figurine
     * @param nomFig nom de la figurine
     * @param nbParties nombre de pièces de la figurine
     */
    public Figurine( String idFig, String nomFig, int nbParties) {
        this.nomFig = nomFig;
        this.idFig = idFig;
        this.nbParties = nbParties;
    }
    /**
     * Crée une figurine sans identifiant défini.
     * L'identifiant est fixé à "0" par défaut.
     *
     * @param nomFig nom de la figurine
     * @param nbParties nombre de pièces de la figurine
     */
    public Figurine(String nomFig, int nbParties) {
        this.nomFig = nomFig;
        this.idFig = "0";
        this.nbParties = nbParties;
    }

    /**
     * Retourne l'identifiant de la figurine.
     *
     * @return identifiant de la figurine
     */
    public String getIdFig() {
        return idFig;
    }

    /**
     * Définit l'identifiant de la figurine.
     *
     * @param idFig nouvel identifiant
     */
    public void setIdFig(String idFig) {
        this.idFig = idFig;
    }

    /**
     * Retourne le nom de la figurine.
     *
     * @return nom de la figurine
     */
    public String getNomFig() {
        return nomFig;
    }

    /**
     * Définit le nom de la figurine.
     *
     * @param nomFig nouveau nom
     */
    public void setNomFig(String nomFig) {
        this.nomFig = nomFig;
    }

    /**
     * Retourne le nombre de pièces de la figurine.
     *
     * @return nombre de pièces
     */
    public int getNbParties() {
        return nbParties;
    }

    /**
     * Définit le nombre de pièces de la figurine.
     *
     * @param nbParties nouveau nombre de pièces
     */
    public void setNbParties(int nbParties) {
        this.nbParties = nbParties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null)return false;
        if (!(o instanceof Figurine)) return false;
        Figurine figurine = (Figurine) o;
        return idFig.equals(figurine.idFig);
    }
    // Ajoute ces deux méthodes en bas de ta classe
public String getImgUrl() {
    return imgUrl;
}
public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
}
    @Override
    public int hashCode() {
        return idFig.hashCode();
    }
    
    @Override
    public String toString() {
        return "Figurine{id=" + idFig +
               ", nom='" + nomFig + "'" +
               ", nbParties=" + nbParties + "}";
    }
}