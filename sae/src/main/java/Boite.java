public class Boite  {
    private String numero;
    private int nbPiece;
    private String nom;
    private int annee;

    public Boite(String numero, int nbPiece, String nom, int annee){
        this.numero = numero;
        this.nbPiece = nbPiece;
        this.annee = annee;
    }   

    public void afficherStatistiques(){

    }
    
    public boolean estComplete(){
        return false;
    }
}
