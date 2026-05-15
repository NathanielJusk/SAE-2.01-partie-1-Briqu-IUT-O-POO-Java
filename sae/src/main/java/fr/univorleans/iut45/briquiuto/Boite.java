package fr.univorleans.iut45.briquiuto;

public abstract class Boite  {
    private String numero;
    private int nbPiece;
    private String nom;
    private int annee;
    private boolean possedee;
    private Theme theme;
    private Contenu contenu;

    public Boite(String numero, int nbPiece, String nom, int annee, boolean possedee){
        this.numero = numero;
        this.nom = nom;
        this.nbPiece = nbPiece;
        this.annee = annee;
        this.theme = null;
        this.contenu = null;
        this.possedee = possedee;
    } 
    public Boite(String numero, int nbPiece, String nom, int annee){
        this.numero = numero;
        this.nom = nom;
        this.nbPiece = nbPiece;
        this.annee = annee;
        this.possedee = false;
        this.theme = null;
        this.contenu = null;
    }  

    public abstract void afficherStatistiques();
    
    public boolean estComplete(){
        return false;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getNbPiece() {
        return nbPiece;
    }

    public void setNbPiece(int nbPiece) {
        this.nbPiece = nbPiece;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Contenu getContenu() {
        return contenu;
    }

    public void setContenu(Contenu contenu) {
        this.contenu = contenu;
    }

    public boolean isPossedee() {
        return possedee;
    }
    public void setPossedee(boolean possedee) {
        this.possedee = possedee;
    }
    @Override
    public String toString() {
        return "Boite{num='" + numero + "'" +
               ", nom='" + nom + "'" +
               ", annee=" + annee +
               ", nbPiece=" + nbPiece +
               ", possedee=" + possedee + "}";
    }

}
