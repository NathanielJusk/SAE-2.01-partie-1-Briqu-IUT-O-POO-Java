package fr.univorleans.iut45.briquiuto;
public class Piece {
    private String numPiece;
    private String nomPiece;
    private Categorie categorie;

    public Piece(String numPiece, String nomPiece, Categorie categorie) {
        this.numPiece = numPiece;
        this.nomPiece = nomPiece;
        this.categorie = categorie;
    }
    public Piece(String numPiece, String nomPiece) {
        this.numPiece = numPiece;
        this.nomPiece = nomPiece;
        this.categorie = null;
    }

    public String getNumPiece() {
        return numPiece;
    }

    public void setNumPiece(String numPiece) {
        this.numPiece = numPiece;
    }

    public String getNomPiece() {
        return nomPiece;
    }

    public void setNomPiece(String nomPiece) {
        this.nomPiece = nomPiece;
    }

    public Categorie getCategorie() {
        return categorie;
    }
    
    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
    public String toString() {
        return "Piece{num='" + numPiece + "'" +
               ", nom='" + nomPiece + "'" +
               ", categorie=" + (categorie != null ? categorie.getNomCat() : "aucune") + "}";
    }
}
