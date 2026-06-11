package fr.univorleans.iut45.briquiuto;

/**
 * Représente une couleur pour une pièce ou une figurine LEGO.
 * On stocke le nom de la couleur et sa valeur RGB.
 */
public class Couleur {
    private int idCoul;
    private String nomCoul;
    private String rgb;
    private boolean transparent;

    /**
     * Crée une couleur avec un identifiant, un nom, un code RGB et une transparence.
     *
     * @param idCoul identifiant de la couleur
     * @param nomCoul nom de la couleur
     * @param rgb code RGB de la couleur
     * @param transparent vrai si la couleur est transparente
     */
    public Couleur(int idCoul, String nomCoul, String rgb, boolean transparent){
        this.idCoul = idCoul;
        this.nomCoul = nomCoul;
        this.rgb = rgb;
        this.transparent = transparent;
    }
    /**
     * Crée une couleur sans identifiant.
     * L'identifiant est fixé à 0 par défaut.
     *
     * @param nomCoul nom de la couleur
     * @param rgb code RGB de la couleur
     * @param transparent vrai si la couleur est transparente
     */
    public Couleur(String nomCoul, String rgb, boolean transparent) {
        this.idCoul      = 0;
        this.nomCoul     = nomCoul;
        this.rgb         = rgb;
        this.transparent = transparent;
    }

    /**
     * Retourne l'identifiant de la couleur.
     *
     * @return identifiant de la couleur
     */
    public int getIdCoul() {
        return idCoul;
    }

    /**
     * Définit l'identifiant de la couleur.
     *
     * @param idCoul nouvel identifiant
     */
    public void setIdCoul(int idCoul) {
        this.idCoul = idCoul;
    }

    /**
     * Retourne le nom de la couleur.
     *
     * @return nom de la couleur
     */
    public String getNomCoul() {
        return nomCoul;
    }

    /**
     * Définit le nom de la couleur.
     *
     * @param nomCoul nouveau nom
     */
    public void setNomCoul(String nomCoul) {
        this.nomCoul = nomCoul;
    }

    /**
     * Retourne le code RGB de la couleur.
     *
     * @return code RGB de la couleur
     */
    public String getRgb() {
        return rgb;
    }

    /**
     * Définit le code RGB de la couleur.
     *
     * @param rgb nouveau code RGB
     */
    public void setRgb(String rgb) {
        this.rgb = rgb;
    }

    /**
     * Indique si la couleur est transparente.
     *
     * @return vrai si la couleur est transparente
     */
    public boolean isTransparent() {
        return transparent;
    }

    /**
     * Définit si la couleur est transparente.
     *
     * @param transparent vrai si la couleur est transparente
     */
    public void setTransparent(boolean transparent) {
        this.transparent = transparent;
    }
    @Override
    public int hashCode() {
        return Integer.hashCode(idCoul);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null)return false;
        if (!(o instanceof Couleur)) return false;
        Couleur couleur = (Couleur) o;
        return idCoul == couleur.idCoul;
    }

    @Override
    public String toString() {
        return "Couleur{id=" + idCoul +
               ", nom='" + nomCoul + "'" +
               ", rgb='" + rgb + "'" +
               ", transparent=" + transparent + "}";
    }
}
