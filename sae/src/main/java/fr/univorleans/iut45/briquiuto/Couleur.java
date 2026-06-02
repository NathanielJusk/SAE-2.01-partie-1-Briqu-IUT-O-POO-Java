package fr.univorleans.iut45.briquiuto;

public class Couleur {
    private int idCoul;
    private String nomCoul;
    private String rgb;
    private boolean transparent;

    public Couleur(int idCoul, String nomCoul, String rgb, boolean transparent){
        this.idCoul = idCoul;
        this.nomCoul = nomCoul;
        this.rgb = rgb;
        this.transparent = transparent;
    }
    public Couleur(String nomCoul, String rgb, boolean transparent) {
        this.idCoul      = 0;
        this.nomCoul     = nomCoul;
        this.rgb         = rgb;
        this.transparent = transparent;
    }

    public int getIdCoul() {
        return idCoul;
    }

    public void setIdCoul(int idCoul) {
        this.idCoul = idCoul;
    }

    public String getNomCoul() {
        return nomCoul;
    }

    public void setNomCoul(String nomCoul) {
        this.nomCoul = nomCoul;
    }

    public String getRgb() {
        return rgb;
    }

    public void setRgb(String rgb) {
        this.rgb = rgb;
    }

    public boolean isTransparent() {
        return transparent;
    }

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
