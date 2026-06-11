package fr.univorleans.iut45.briquiuto;

/**
 * Représente une catégorie de pièces LEGO.
 * Par exemple : pièces de base, décorations, accessoires.
 */
public class Categorie {
    private int idCat;
    private String nomCat;

    /**
     * Crée une catégorie avec un identifiant et un nom.
     *
     * @param idCat identifiant de la catégorie
     * @param nomCat nom de la catégorie
     */
    public Categorie(int idCat, String nomCat) {
        this.idCat = idCat;
        this.nomCat = nomCat;
    }
    /**
     * Crée une catégorie avec seulement un nom.
     * L'identifiant est fixé à 0 par défaut.
     *
     * @param nomCat nom de la catégorie
     */
    public Categorie(String nomCat) {
        this.idCat = 0;
        this.nomCat = nomCat;
    }

    /**
     * Retourne l'identifiant de la catégorie.
     *
     * @return identifiant de la catégorie
     */
    public int getIdCat() {
        return idCat;
    }

    /**
     * Définit l'identifiant de la catégorie.
     *
     * @param idCat nouvel identifiant
     */
    public void setIdCat(int idCat) {
        this.idCat = idCat;
    }

    /**
     * Retourne le nom de la catégorie.
     *
     * @return nom de la catégorie
     */
    public String getNomCat() {
        return nomCat;
    }

    /**
     * Définit le nom de la catégorie.
     *
     * @param nomCat nouveau nom
     */
    public void setNomCat(String nomCat) {
        this.nomCat = nomCat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null)return false;
        if (!(o instanceof Categorie)) return false;
        Categorie categorie = (Categorie) o;
        return idCat == categorie.idCat;
    }   
    @Override
    public String toString() {
        return "Categorie{id=" + idCat +
               ", nom='" + nomCat + "'" +
               "}";
    }
    
}
