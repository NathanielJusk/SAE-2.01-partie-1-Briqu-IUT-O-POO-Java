package fr.univorleans.iut45.briquiuto;
public class Piece {
    private String numPiece;
    private String nomPiece;
    private Categorie categorie;

    public Piece(String numPiece, String nomPiece){
        this.nomPiece = numPiece;
        this.nomPiece = nomPiece;
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
    

    
}
