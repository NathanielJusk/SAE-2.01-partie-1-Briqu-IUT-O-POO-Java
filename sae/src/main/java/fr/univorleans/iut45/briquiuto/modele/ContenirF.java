package fr.univorleans.iut45.briquiuto.modele;

/**
 * Représente l'association d'une figurine avec une quantité.
 * Cette classe sert à savoir combien de figurines sont dans un contenu.
 */
public class ContenirF {
    private Figurine figurine;
    private int quantiteF;

    /**
     * Crée une association figurine / quantité.
     *
     * @param figurine la figurine concernée
     * @param quantiteF le nombre de figurines
     */
    public ContenirF(Figurine figurine, int quantiteF) {
        this.figurine = figurine;
        this.quantiteF = quantiteF;
    }
    
    /**
     * Retourne la figurine associée.
     *
     * @return figurine associée
     */
    public Figurine getFigurine() {
        return figurine;
    }

    /**
     * Définit la figurine associée.
     *
     * @param figurine nouvelle figurine
     */
    public void setFigurine(Figurine figurine) {
        this.figurine = figurine;
    }

    /**
     * Retourne la quantité de figurines.
     *
     * @return quantité de figurines
     */
    public int getQuantiteF() {
        return quantiteF;
    }

    /**
     * Définit la quantité de figurines.
     *
     * @param quantiteF nouvelle quantité
     */
    public void setQuantiteF(int quantiteF) {
        this.quantiteF = quantiteF;
    }
    
}

