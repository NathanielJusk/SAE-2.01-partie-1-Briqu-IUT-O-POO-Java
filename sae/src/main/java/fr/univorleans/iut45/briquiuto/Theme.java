package fr.univorleans.iut45.briquiuto;
import java.util.List;
import java.util.ArrayList;

public class Theme {
    private int idTheme;
    private String nom;
    private List<Theme> sousThemes;
    private Theme themePere;
    private boolean estParent;


public Theme(int idTheme, String nom){
    this.idTheme = idTheme;
    this.nom = nom;
    this.sousThemes = new ArrayList<>();
    this.estParent = false;
    this.themePere = null;
}

public Theme(int idTheme, String nom, Theme themePere){
    this.idTheme = idTheme;
    this.nom = nom;
    this.sousThemes = new ArrayList<>();
    this.estParent = false;
    this.themePere = themePere;
    if (themePere != null) {
        themePere.ajouterSousTheme(this);
    }
}

public void ajouterSousTheme(Theme sousTheme){
    this.sousThemes.add(sousTheme);
    this.estParent = true;
}

public boolean estParent(){
return this.estParent;
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
public void setEstParent(boolean estParent) {
    this.estParent = estParent;
}

@Override
public String toString() {
    return "Theme{id=" + idTheme +
           ", nom='" + nom + "'" +
           ", estParent=" + estParent +
           ", themePere=" + (themePere != null ? themePere.getNom() : "null") +
           ", sousThemes=" + sousThemes.size() + "}";
}
}