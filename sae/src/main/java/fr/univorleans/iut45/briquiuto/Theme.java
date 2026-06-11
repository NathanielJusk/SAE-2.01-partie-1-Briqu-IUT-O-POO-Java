package fr.univorleans.iut45.briquiuto;

import java.util.List;
import java.util.ArrayList;

public class Theme {
    private int idTheme;
    private String nom;
    private List<Theme> sousThemes;
    private Theme themePere;
    private boolean estParent;

    public Theme(int idTheme, String nom) {
        this.idTheme = idTheme;
        this.nom = nom;
        this.sousThemes = new ArrayList<>();
        this.estParent = false;
        this.themePere = null;
    }

    /**
     * Crée un thème avec un thème parent.
     *
     * @param idTheme identifiant du thème
     * @param nom nom du thème
     * @param themePere thème parent
     */
    public Theme(int idTheme, String nom, Theme themePere) {
        this.idTheme = idTheme;
        this.nom = nom;
        this.sousThemes = new ArrayList<>();
        this.estParent = false;
        this.themePere = themePere;
        if (themePere != null) {
            themePere.ajouterSousTheme(this);
        }
    }

    /**
     * Ajoute un sous-thème à ce thème.
     *
     * @param sousTheme thème enfant à ajouter
     */
    public void ajouterSousTheme(Theme sousTheme) {
        this.sousThemes.add(sousTheme);
        this.estParent = true;
    }

    /**
     * Indique si ce thème a des sous-thèmes.
     *
     * @return vrai si le thème est un parent
     */
    public boolean estParent() {
        return this.estParent;
    }

    /**
     * Retourne l'identifiant du thème.
     *
     * @return identifiant du thème
     */
    public int getIdTheme() {
        return idTheme;
    }

    /**
     * Définit l'identifiant du thème.
     *
     * @param idTheme nouvel identifiant
     */
    public void setIdTheme(int idTheme) {
        this.idTheme = idTheme;
    }

    /**
     * Retourne le nom du thème.
     *
     * @return nom du thème
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du thème.
     *
     * @param nom nouveau nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne la liste des sous-thèmes.
     *
     * @return liste de sous-thèmes
     */
    public List<Theme> getSousThemes() {
        return sousThemes;
    }

    /**
     * Définit la liste des sous-thèmes.
     *
     * @param sousThemes nouvelle liste de sous-thèmes
     */
    public void setSousThemes(List<Theme> sousThemes) {
        this.sousThemes = sousThemes;
    }

    /**
     * Retourne le thème parent.
     *
     * @return thème parent ou null si aucun
     */
    public Theme getThemePere() {
        return themePere;
    }

    /**
     * Définit le thème parent.
     *
     * @param themePere nouveau thème parent
     */
    public void setThemePere(Theme themePere) {
        this.themePere = themePere;
    }

    /**
     * Définit si ce thème a des sous-thèmes.
     *
     * @param estParent vrai si le thème est parent
     */
    public void setEstParent(boolean estParent) {
        this.estParent = estParent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || !(o instanceof Theme))
            return false;
        Theme theme = (Theme) o;
        return idTheme == theme.idTheme;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(idTheme);
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