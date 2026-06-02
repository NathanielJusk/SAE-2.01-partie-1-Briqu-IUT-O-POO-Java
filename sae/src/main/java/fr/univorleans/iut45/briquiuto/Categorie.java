package fr.univorleans.iut45.briquiuto;

public class Categorie {
    private int idCat;
    private String nomCat;

    public Categorie(int idCat, String nomCat) {
        this.idCat = idCat;
        this.nomCat = nomCat;
    }
    public Categorie(String nomCat) {
        this.idCat = 0;
        this.nomCat = nomCat;
    }

    public int getIdCat() {
        return idCat;
    }

    public void setIdCat(int idCat) {
        this.idCat = idCat;
    }

    public String getNomCat() {
        return nomCat;
    }

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
