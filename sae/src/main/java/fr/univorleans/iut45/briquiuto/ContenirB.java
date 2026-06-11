package fr.univorleans.iut45.briquiuto;

/**
 * Représente l'association d'une boîte dans une autre boîte.
 * Utile pour les boîtes composées qui contiennent plusieurs boîtes.
 */
public class ContenirB {
    private Boite  boite;
    private int quantiteB;

    /**
     * Crée une association boîte / quantité.
     *
     * @param boite boîte contenue
     * @param quantiteB nombre de boîtes
     */
    public ContenirB(Boite boite, int quantiteB) {
        this.boite = boite;
        this.quantiteB = quantiteB;
    }
    
    /**
     * Retourne la boîte contenue.
     *
     * @return boîte contenue
     */
    public Boite getBoite() {
        return boite;
    }

    /**
     * Définit la boîte contenue.
     *
     * @param boite nouvelle boîte
     */
    public void setBoite(Boite boite) {
        this.boite = boite;
    }

    /**
     * Retourne la quantité de boîtes.
     *
     * @return quantité de boîtes
     */
    public int getQuantiteB() {
        return quantiteB;
    }

    /**
     * Définit la quantité de boîtes.
     *
     * @param quantiteB nouvelle quantité
     */
    public void setQuantiteB(int quantiteB) {
        this.quantiteB = quantiteB;
    }


}
