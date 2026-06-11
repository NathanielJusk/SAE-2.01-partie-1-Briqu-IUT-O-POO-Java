package fr.univorleans.iut45.briquiuto;

/**
 * Représente une pièce LEGO.
 * Une pièce a un numéro, un nom et une catégorie.
 */
public class Piece {
    private String numPiece;
    private String nomPiece;
    private Categorie categorie;

    /**
     * Crée une pièce avec un numéro, un nom et une catégorie.
     *
     * @param numPiece numéro de la pièce
     * @param nomPiece nom de la pièce
     * @param categorie catégorie de la pièce
     */
    public Piece(String numPiece, String nomPiece, Categorie categorie) {
        this.numPiece = numPiece;
        this.nomPiece = nomPiece;
        this.categorie = categorie;
    }
    /**
     * Crée une pièce sans catégorie définie.
     *
     * @param numPiece numéro de la pièce
     * @param nomPiece nom de la pièce
     */
    public Piece(String numPiece, String nomPiece) {
        this.numPiece = numPiece;
        this.nomPiece = nomPiece;
        this.categorie = null;
    }

    /**
     * Retourne le numéro de la pièce.
     *
     * @return numéro de la pièce
     */
    public String getNumPiece() {
        return numPiece;
    }

    /**
     * Définit le numéro de la pièce.
     *
     * @param numPiece nouveau numéro
     */
    public void setNumPiece(String numPiece) {
        this.numPiece = numPiece;
    }

    /**
     * Retourne le nom de la pièce.
     *
     * @return nom de la pièce
     */
    public String getNomPiece() {
        return nomPiece;
    }

    /**
     * Définit le nom de la pièce.
     *
     * @param nomPiece nouveau nom
     */
    public void setNomPiece(String nomPiece) {
        this.nomPiece = nomPiece;
    }

    /**
     * Retourne la catégorie de la pièce.
     *
     * @return catégorie de la pièce
     */
    public Categorie getCategorie() {
        return categorie;
    }
    
    /**
     * Définit la catégorie de la pièce.
     *
     * @param categorie nouvelle catégorie
     */
    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
    @Override
    public int hashCode() {
        return Integer.hashCode(numPiece.hashCode());
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null)return false;
        if (!(o instanceof Piece)) return false;
        Piece piece = (Piece) o;
        return numPiece.equals(piece.numPiece);
    }
    @Override
    public String toString() {
        return "Piece{num='" + numPiece + "'" +
               ", nom='" + nomPiece + "'" +
               ", categorie=" + (categorie != null ? categorie.getNomCat() : "aucune") + "}";
    }
}
