package fr.univorleans.iut45.briquiuto;
import java.util.ArrayList;
import java.util.List;

public class Contenu {
    private int idContenu;
    private int version;
    private List<ContenirP> contenirPieces;
    private List<ContenirB> contenirBoites;
    private List<ContenirF> contenirFigurines;
    
    public Contenu(int idContenu, int version){
        this.idContenu = idContenu;
        this.version = version;
        contenirBoites = new ArrayList<>();
        contenirPieces = new ArrayList<>();
        contenirFigurines = new ArrayList<>();
    }

    public int getIdContenu() {
        return idContenu;
    }

    public void setIdContenu(int idContenu) {
        this.idContenu = idContenu;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<ContenirP> getContenirPieces() {
        return contenirPieces;
    }

    public void setContenirPieces(List<ContenirP> contenirPieces) {
        this.contenirPieces = contenirPieces;
    }

    public List<ContenirB> getContenirBoites() {
        return contenirBoites;
    }

    public void setContenirBoites(List<ContenirB> contenirBoites) {
        this.contenirBoites = contenirBoites;
    }

    public List<ContenirF> getContenirFigurines() {
        return contenirFigurines;
    }

    public void setContenirFigurines(List<ContenirF> contenirFigurines) {
        this.contenirFigurines = contenirFigurines;
    }
    @Override
    public int hashCode() {
        return Integer.hashCode(idContenu);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null)return false;
        if (!(o instanceof Contenu)) return false;
        Contenu contenu = (Contenu) o;
        return idContenu == contenu.idContenu;
    }

    @Override
    public String toString() {
        return "Contenu{id=" + idContenu +
               ", version=" + version +
               ", nbBoites=" + contenirBoites.size() +
               ", nbPieces=" + contenirPieces.size() +
               ", nbFigurines=" + contenirFigurines.size() +
               "}";
    }

    
}  
 