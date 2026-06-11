package fr.univorleans.iut45.briquiuto;
import java.util.ArrayList;
import java.util.List;

/**
 * Représente le contenu d'une boîte LEGO.
 * Le contenu peut contenir des pièces, des boîtes et des figurines.
 */
public class Contenu {
    private int idContenu;
    private int version;
    private List<ContenirP> contenirPieces;
    private List<ContenirB> contenirBoites;
    private List<ContenirF> contenirFigurines;
    
    /**
     * Crée un contenu avec un identifiant et une version.
     *
     * @param idContenu identifiant du contenu
     * @param version version du contenu
     */
    public Contenu(int idContenu, int version){
        this.idContenu = idContenu;
        this.version = version;
        contenirBoites = new ArrayList<>();
        contenirPieces = new ArrayList<>();
        contenirFigurines = new ArrayList<>();
    }

    /**
     * Retourne l'identifiant du contenu.
     *
     * @return identifiant du contenu
     */
    public int getIdContenu() {
        return idContenu;
    }

    /**
     * Définit l'identifiant du contenu.
     *
     * @param idContenu nouvel identifiant
     */
    public void setIdContenu(int idContenu) {
        this.idContenu = idContenu;
    }

    /**
     * Retourne la version du contenu.
     *
     * @return version du contenu
     */
    public int getVersion() {
        return version;
    }

    /**
     * Définit la version du contenu.
     *
     * @param version nouvelle version
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Retourne la liste des pièces contenues.
     *
     * @return liste des associations pièces / quantités
     */
    public List<ContenirP> getContenirPieces() {
        return contenirPieces;
    }

    /**
     * Définit la liste des pièces contenues.
     *
     * @param contenirPieces nouvelle liste de pièces
     */
    public void setContenirPieces(List<ContenirP> contenirPieces) {
        this.contenirPieces = contenirPieces;
    }

    /**
     * Retourne la liste des boîtes contenues.
     *
     * @return liste des associations boîtes / quantités
     */
    public List<ContenirB> getContenirBoites() {
        return contenirBoites;
    }

    /**
     * Définit la liste des boîtes contenues.
     *
     * @param contenirBoites nouvelle liste de boîtes
     */
    public void setContenirBoites(List<ContenirB> contenirBoites) {
        this.contenirBoites = contenirBoites;
    }

    /**
     * Retourne la liste des figurines contenues.
     *
     * @return liste des associations figurines / quantités
     */
    public List<ContenirF> getContenirFigurines() {
        return contenirFigurines;
    }

    /**
     * Définit la liste des figurines contenues.
     *
     * @param contenirFigurines nouvelle liste de figurines
     */
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
 