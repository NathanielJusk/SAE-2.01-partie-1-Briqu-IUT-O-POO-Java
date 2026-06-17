package fr.univorleans.iut45.briquiuto.modele;

/**
 * Représente l'association d'une pièce avec sa couleur et sa quantité.
 * Ce modèle sert à indiquer combien de pièces d'un type sont présentes.
 */
public class ContenirP {

    private Couleur couleur;
    private Piece piece;
    private int quantiteP;
    private boolean estSupplement;
    private String imgUrl;

    /**
     * Crée une association pièce/couleur/quantité.
     *
     * @param couleur couleur de la pièce
     * @param piece pièce concernée
     * @param quantiteP quantité de la pièce
     * @param estSupplement vrai si la pièce est en supplément
     * @param imgUrl URL de l'image de la pièce
     */
    public ContenirP(Couleur couleur, Piece piece, int quantiteP, boolean estSupplement, String imgUrl) {
        this.couleur = couleur;
        this.piece = piece;
        this.quantiteP = quantiteP;
        this.estSupplement = estSupplement;
        this.imgUrl = imgUrl;
    }
    
    /**
     * Retourne la couleur de la pièce.
     *
     * @return couleur de la pièce
     */
    public Couleur getCouleur() {
        return couleur;
    }

    /**
     * Définit la couleur de la pièce.
     *
     * @param couleur nouvelle couleur
     */
    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    /**
     * Retourne la pièce associée.
     *
     * @return pièce associée
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Définit la pièce associée.
     *
     * @param piece nouvelle pièce
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * Retourne la quantité de cette pièce.
     *
     * @return quantité de la pièce
     */
    public int getQuantiteP() {
        return quantiteP;
    }

    /**
     * Définit la quantité de cette pièce.
     *
     * @param quantiteP nouvelle quantité
     */
    public void setQuantiteP(int quantiteP) {
        this.quantiteP = quantiteP;
    }

    /**
     * Indique si la pièce est en supplément.
     *
     * @return vrai si la pièce est en supplément
     */
    public boolean isEstSupplement() {
        return estSupplement;
    }

    /**
     * Définit si la pièce est en supplément.
     *
     * @param estSupplement vrai si la pièce est en supplément
     */
    public void setEstSupplement(boolean estSupplement) {
        this.estSupplement = estSupplement;
    }

    /**
     * Retourne l'URL de l'image de la pièce.
     *
     * @return URL de l'image
     */
    public String getImgUrl() {
        return imgUrl; 
    }

    /**
     * Définit l'URL de l'image de la pièce.
     *
     * @param imgUrl nouvelle URL de l'image
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    

    
}
