package fr.univorleans.iut45.briquiuto;

public class Boite  {
    private String numero;
    private int nbPiece;
    private String nom;
    private int annee;
    private Theme theme;
    private Contenu contenu;

    public Boite(String numero, int nbPiece, String nom, int annee){
        this.numero = numero;
        this.nom = nom;
        this.nbPiece = nbPiece;
        this.annee = annee;
    }   

    public void afficherStatistiques(){

    }
    
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

    


}
