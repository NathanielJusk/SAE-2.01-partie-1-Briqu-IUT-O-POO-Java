package fr.univorleans.iut45.briquiuto;

public class ContenirP {

    private Couleur couleur;
    private Piece piece;
    private int quantiteP;
    private boolean estSupplement;

        public ContenirP(Couleur couleur, Piece piece, int quantiteP, boolean estSupplement) {
            this.couleur = couleur;
            this.piece = piece;
            this.quantiteP = quantiteP;
            this.estSupplement = estSupplement;
        }
    
    public Couleur getCouleur() {
        return couleur;
    }
    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }
    public Piece getPiece() {
        return piece;
    }
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    public int getQuantiteP() {
        return quantiteP;

    }
    public int setQuantiteP() {
        return this.quantiteP;
    }

    public void setQuantiteP(int quantiteP) {
        this.quantiteP = quantiteP;
    }
    public boolean isEstSupplement() {
        return estSupplement;
    }
    public void setEstSupplement(boolean estSupplement) {
        this.estSupplement = estSupplement;
    }

    

    
}
