package fr.univorleans.iut45.briquiuto;

/**
 * Représente une boîte LEGO dans la collection.
 *
 * Une boîte a un numéro, un nom, une année de sortie, un nombre de pièces,
 * un thème, un contenu et un état indiquant si elle est possédée ou non.
 */
public abstract class Boite  {
    private String numero;
    private int nbPiece;
    private String nom;
    private int annee;
    private boolean possedee;
    private Theme theme;
    private Contenu contenu;

    /**
     * Crée une boîte avec toutes les informations, y compris si elle est possédée.
     *
     * @param numero numéro de la boîte
     * @param nbPiece nombre de pièces
     * @param nom nom de la boîte
     * @param annee année de sortie
     * @param possedee vrai si la boîte est possédée
     */
    public Boite(String numero, int nbPiece, String nom, int annee, boolean possedee){
        this.numero = numero;
        this.nom = nom;
        this.nbPiece = nbPiece;
        this.annee = annee;
        this.theme = null;
        this.contenu = null;
        this.possedee = possedee;
    } 
/**
     * Crée une boîte sans préciser si elle est possédée.
     * La boîte est alors considérée comme non possédée par défaut.
     *
     * @param numero numéro de la boîte
     * @param nbPiece nombre de pièces
     * @param nom nom de la boîte
     * @param annee année de sortie
     */
    public Boite(String numero, int nbPiece, String nom, int annee){
        this.numero = numero;
        this.nom = nom;
        this.nbPiece = nbPiece;
        this.annee = annee;
        this.possedee = false;
        this.theme = null;
        this.contenu = null;
    }  

    /**
     * Affiche des informations sur la boîte.
     * Chaque type de boîte doit expliquer ses statistiques.
     */
    public abstract void afficherStatistiques();
    
    /**
     * Vérifie si la boîte est complète.
     * Actuellement cette méthode renvoie toujours false.
     *
     * @return vrai si la boîte est complète
     */
    public boolean estComplete(){
        return false;
    }

    /**
     * Retourne le numéro de la boîte.
     *
     * @return numéro de la boîte
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Définit le numéro de la boîte.
     *
     * @param numero nouveau numéro
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Retourne le nombre de pièces de la boîte.
     *
     * @return nombre de pièces
     */
    public int getNbPiece() {
        return nbPiece;
    }

    /**
     * Modifie le nombre de pièces de la boîte.
     *
     * @param nbPiece nouveau nombre de pièces
     */
    public void setNbPiece(int nbPiece) {
        this.nbPiece = nbPiece;
    }

    /**
     * Retourne le nom de la boîte.
     *
     * @return nom de la boîte
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de la boîte.
     *
     * @param nom nouveau nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne l'année de sortie de la boîte.
     *
     * @return année de la boîte
     */
    public int getAnnee() {
        return annee;
    }

    /**
     * Définit l'année de sortie de la boîte.
     *
     * @param annee nouvelle année
     */
    public void setAnnee(int annee) {
        this.annee = annee;
    }

    /**
     * Retourne le thème de la boîte.
     *
     * @return thème de la boîte
     */
    public Theme getTheme() {
        return theme;
    }

    /**
     * Définit le thème de la boîte.
     *
     * @param theme thème à associer
     */
    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    /**
     * Retourne le contenu de la boîte.
     *
     * @return contenu de la boîte
     */
    public Contenu getContenu() {
        return contenu;
    }

    /**
     * Définit le contenu de la boîte.
     *
     * @param contenu contenu à placer dans la boîte
     */
    public void setContenu(Contenu contenu) {
        this.contenu = contenu;
    }

    /**
     * Vérifie si la boîte est possédée.
     *
     * @return vrai si la boîte est possédée
     */
    public boolean isPossedee() {
        return possedee;
    }
    /**
     * Définit si la boîte est possédée.
     *
     * @param possedee vrai si la boîte est possédée
     */
    public void setPossedee(boolean possedee) {
        this.possedee = possedee;
    }

    /**
     * Représentation texte simple de la boîte.
     *
     * @return description de la boîte
     */
    @Override
    public String toString() {
        return "Boite{num='" + numero + "'" +
               ", nom='" + nom + "'" +
               ", annee=" + annee +
               ", nbPiece=" + nbPiece +
               ", possedee=" + possedee + "}";
    }

}
