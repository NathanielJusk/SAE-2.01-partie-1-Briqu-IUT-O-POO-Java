package fr.univorleans.iut45.briquiuto;
import java.util.List;
import java.util.ArrayList;

public class Theme {
    private int idTheme;
    private String nom;
    private List<Theme> sousThemes;
    private Theme themePere;


public Theme(int idTheme, String nom){
    this.idTheme = idTheme;
    this.nom = nom;
    this.sousThemes = new ArrayList<>();
}

public boolean estParent(){
return true;
}

public List<Boite> rechercherBoitesParTheme(){
 return null;
}

public int getIdTheme() {
    return idTheme;
}

public void setIdTheme(int idTheme) {
    this.idTheme = idTheme;
}

public String getNom() {
    return nom;
}

public void setNom(String nom) {
    this.nom = nom;
}

public List<Theme> getSousThemes() {
    return sousThemes;
}

public void setSousThemes(List<Theme> sousThemes) {
    this.sousThemes = sousThemes;
}

public Theme getThemePere() {
    return themePere;
}

public void setThemePere(Theme themePere) {
    this.themePere = themePere;
}


}
