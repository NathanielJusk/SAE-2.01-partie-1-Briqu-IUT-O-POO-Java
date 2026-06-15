package fr.univorleans.iut45.briquiuto.JDBC;
import fr.univorleans.iut45.briquiuto.modele.Boite;
import fr.univorleans.iut45.briquiuto.modele.BoiteComposee;
import fr.univorleans.iut45.briquiuto.modele.BriqueCollectionManager;
import fr.univorleans.iut45.briquiuto.modele.Piece;
import fr.univorleans.iut45.briquiuto.modele.Theme;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Exécute les requêtes SQL pour le projet LEGO.
 * Cette classe permet d'ajouter et de lire des boîtes, des pièces et des thèmes
 * dans la base de données.
 */
public class RequetesLEGO {

    private ConnexionBD laConnexion;
    private BriqueCollectionManager manager;

    /**
     * Crée un objet de requêtes avec une connexion et un manager.
     *
     * @param laConnexion connexion à la base de données
     * @param manager     gestionnaire de collection (peut être null)
     */
    public RequetesLEGO(ConnexionBD laConnexion, BriqueCollectionManager manager) {
        this.laConnexion = laConnexion;
        this.manager = manager;
    }

    // ── THEME ─────────────────────────────────────────────────────────────

    /**
     * Ajoute un thème dans la base de données.
     *
     * @param theme thème à ajouter
     * @throws SQLException si la requête SQL échoue
     */
    public void ajouterTheme(Theme theme) throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement(
                "INSERT INTO THEME (idtheme, nomtheme, idtheme_pere) VALUES (?, ?, ?)");
        ps.setInt(1, theme.getIdTheme());
        ps.setString(2, theme.getNom());
        if (theme.getThemePere() != null) {
            ps.setInt(3, theme.getThemePere().getIdTheme());
        } else {
            ps.setNull(3, Types.INTEGER);
        }
        ps.executeUpdate();
        ps.close();
    }

    /**
     * Récupère tous les thèmes dans la base.
     *
     * @return liste des thèmes
     * @throws SQLException si la requête SQL échoue
     */
    public List<Theme> getAllThemes() throws SQLException {
        List<Theme> themes = new ArrayList<>();
        Statement st = laConnexion.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM THEME");
        while (rs.next()) {
            Theme t = new Theme(
                    rs.getInt("idtheme"),
                    rs.getString("nomtheme"));
            themes.add(t);
        }
        rs.close();
        st.close();
        return themes;
    }

    // ── BOITE ─────────────────────────────────────────────────────────────

    /**
     * Ajoute une boîte dans la base de données.
     *
     * @param boite boîte à ajouter
     * @throws SQLException si la requête SQL échoue
     */
    public void ajouterBoite(Boite boite) throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement(
                "INSERT INTO BOITE (numboite, nomboite, annee, nbpieces, idtheme) VALUES (?, ?, ?, ?, ?)");
        ps.setString(1, boite.getNumero());
        ps.setString(2, boite.getNom());
        ps.setInt(3, boite.getAnnee());
        ps.setInt(4, boite.getNbPiece());
        ps.setInt(5, boite.getTheme() != null ? boite.getTheme().getIdTheme() : 1);
        ps.executeUpdate();
        ps.close();
    }

    /**
     * Recherche une boîte dans la base par son nom partiel.
     *
     * @param nom nom de la boîte à chercher
     * @return boîte trouvée ou null
     * @throws SQLException si la requête SQL échoue
     */
    public Boite rechercherBoiteParNom(String nom) throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement(
                "SELECT * FROM BOITE WHERE nomboite LIKE ?");
        ps.setString(1, "%" + nom + "%");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Boite b = new BoiteComposee(
                    rs.getString("numboite"),
                    rs.getInt("nbpieces"),
                    rs.getString("nomboite"),
                    rs.getInt("annee"));
            rs.close();
            ps.close();
            return b;
        }
        rs.close();
        ps.close();
        return null;
    }

    /**
     * Récupère toutes les boîtes de la base.
     *
     * @return liste de boîtes
     * @throws SQLException si la requête SQL échoue
     */
    public List<Boite> getAllBoites() throws SQLException {
        List<Boite> boites = new ArrayList<>();
        Statement st = laConnexion.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM BOITE");
        while (rs.next()) {
            Boite b = new BoiteComposee(
                    rs.getString("numboite"),
                    rs.getInt("nbpieces"),
                    rs.getString("nomboite"),
                    rs.getInt("annee"));
            boites.add(b);
        }
        rs.close();
        st.close();
        return boites;
    }

    /**
     * Recherche une boîte par son numéro exact.
     *
     * @param numero numéro de la boîte
     * @return boîte trouvée ou null
     * @throws SQLException si la requête SQL échoue
     */
    public Boite rechercherBoiteParNumero(String numero) throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement(
                "SELECT * FROM BOITE WHERE numboite = ?");
        ps.setString(1, numero);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Boite b = new BoiteComposee(
                    rs.getString("numboite"),
                    rs.getInt("nbpieces"),
                    rs.getString("nomboite"),
                    rs.getInt("annee"));
            rs.close();
            ps.close();
            return b;
        }
        rs.close();
        ps.close();
        return null;
    }

    // ── PIECE ─────────────────────────────────────────────────────────────

    /**
     * Ajoute une pièce dans la base de données.
     *
     * @param piece pièce à ajouter
     * @throws SQLException si la requête SQL échoue
     */
    public void ajouterPiece(Piece piece) throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement(
                "INSERT INTO PIECE (numpiece, nompiece, idcat) VALUES (?, ?, ?)");
        ps.setString(1, piece.getNumPiece());
        ps.setString(2, piece.getNomPiece());
        ps.setInt(3, piece.getCategorie() != null ? piece.getCategorie().getIdCat() : 1);
        ps.executeUpdate();
        ps.close();
    }

    /**
     * Récupère toutes les pièces de la base.
     *
     * @return liste de pièces
     * @throws SQLException si la requête SQL échoue
     */
    public List<Piece> getAllPieces() throws SQLException {
        List<Piece> pieces = new ArrayList<>();
        Statement st = laConnexion.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM PIECE");
        while (rs.next()) {
            Piece p = new Piece(
                    rs.getString("numpiece"),
                    rs.getString("nompiece"));
            pieces.add(p);
        }
        rs.close();
        st.close();
        return pieces;
    }

    // ── AFFICHAGES ────────────────────────────────────────────────────────

    /**
     * Retourne les boîtes qui appartiennent à un thème.
     *
     * @param idTheme identifiant du thème
     * @return texte listant les boîtes trouvées
     * @throws SQLException si la requête SQL échoue
     */
    public String listerBoitesParTheme(int idTheme) throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement(
                "SELECT numboite, nomboite, annee FROM BOITE WHERE idtheme = ?");
        ps.setInt(1, idTheme);
        ResultSet rs = ps.executeQuery();
        String res = "";
        while (rs.next()) {
            res += "[" + rs.getString("numboite") + "] "
                    + rs.getString("nomboite")
                    + " (" + rs.getInt("annee") + ")\n";
        }
        rs.close();
        ps.close();
        return res.isEmpty() ? "Aucune boite trouvee." : res;
    }

    /**
     * Recherche un thème dans la base par son identifiant exact.
     *
     * @param idTheme identifiant du thème recherché
     * @return le thème trouvé ou null s'il n'existe pas
     * @throws SQLException si la requête SQL échoue
     */
    public Theme rechercherThemeParId(int idTheme) throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement(
                "SELECT * FROM THEME WHERE idtheme = ?");
        ps.setInt(1, idTheme);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            // Si la base de données trouve le thème, on crée l'objet Java correspondant
            Theme themeTrouve = new Theme(
                    rs.getInt("idtheme"),
                    rs.getString("nomtheme")
            );
            rs.close();
            ps.close();
            return themeTrouve;
        }
        
        // Si aucun thème ne correspond à cet ID
        rs.close();
        ps.close();
        return null;
    }

    /**
     * Retourne les pièces présentes dans une boîte.
     *
     * @param numBoite numéro de la boîte
     * @return texte listant les pièces trouvées
     * @throws SQLException si la requête SQL échoue
     */
    public String listerPiecesBoite(String numBoite) throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement(
                "SELECT p.nompiece, co.nomcoul, cp.quantitep, cp.en_supplement " +
                        "FROM PIECE p " +
                        "JOIN CONTENIRP cp ON p.numpiece = cp.numpiece " +
                        "JOIN CONTENU c ON cp.idcont = c.idcont " +
                        "JOIN COULEUR co ON cp.idcoul = co.idcoul " +
                        "WHERE c.numboite = ?");
        ps.setString(1, numBoite);
        ResultSet rs = ps.executeQuery();
        String res = "";
        while (rs.next()) {
            boolean supplement = "O".equals(rs.getString("en_supplement"));
            res += "- " + rs.getString("nompiece")
                    + " | Couleur : " + rs.getString("nomcoul")
                    + " | Quantite : " + rs.getInt("quantitep")
                    + (supplement ? " [OUI en supplement ! ]" : "")
                    + "\n";
        }
        rs.close();
        ps.close();
        return res.isEmpty() ? "Aucune piece trouvee." : res;
    }

    /**
     * Retourne les sous-boîtes contenues dans une boîte.
     *
     * @param numBoite numéro de la boîte
     * @return texte listant les sous-boîtes trouvées
     * @throws SQLException si la requête SQL échoue
     */
    public String listerSousBoite(String numBoite) throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement(
                "SELECT b.nomboite, b.annee, cb.quantiteb " +
                        "FROM BOITE b " +
                        "JOIN CONTENIRB cb ON cb.numboite = b.numboite " +
                        "JOIN CONTENU c ON cb.idcont = c.idcont " +
                        "WHERE c.numboite = ?");
        ps.setString(1, numBoite);
        ResultSet rs = ps.executeQuery();
        StringBuilder res = new StringBuilder();
        while (rs.next()) {
            res.append("- ")
                    .append(rs.getString("nomboite"))
                    .append(" | Quantite : ")
                    .append(rs.getInt("quantiteb"))
                    .append("\n");
        }
        rs.close();
        ps.close();
        return res.length() == 0 ? "Aucune sous-boite trouvee." : res.toString();
    }

    /**
     * Retourne les figurines présentes dans une boîte.
     *
     * @param numBoite numéro de la boîte
     * @return texte listant les figurines trouvées
     * @throws SQLException si la requête SQL échoue
     */
    public String listerFigurinesBoite(String numBoite) throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement(
                "SELECT f.nomfig, cf.quantitef " +
                        "FROM FIGURINE f " +
                        "JOIN CONTENIRF cf ON f.idfig = cf.idfig " +
                        "JOIN CONTENU c ON cf.idcont = c.idcont " +
                        "WHERE c.numboite = ?");
        ps.setString(1, numBoite);
        ResultSet rs = ps.executeQuery();
        String res = "";
        while (rs.next()) {
            res += "- " + rs.getString("nomfig")
                    + " | Quantite : " + rs.getInt("quantitef")
                    + "\n";
        }
        rs.close();
        ps.close();
        return res.isEmpty() ? "Aucune figurine trouvee." : res;
    }

    /**
     * Retourne les boîtes appartenant à un thème et à tous ses sous-thèmes.
     *
     * @param idTheme identifiant du thème racine
     * @return texte listant les boîtes trouvées
     * @throws SQLException si la requête SQL échoue
     */
    public String listerBoitesParThemeAvecSousThemes(int idTheme) throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement(
                "SELECT numboite, nomboite, annee FROM BOITE " +
                        "WHERE idtheme = ? " +
                        "OR idtheme IN (SELECT idtheme FROM THEME WHERE idtheme_pere = ?)");
        ps.setInt(1, idTheme);
        ps.setInt(2, idTheme);
        ResultSet rs = ps.executeQuery();
        String res = "";
        while (rs.next()) {
            res += "[" + rs.getString("numboite") + "] "
                    + rs.getString("nomboite")
                    + " (" + rs.getInt("annee") + ")\n";
        }
        rs.close();
        ps.close();
        return res.isEmpty() ? "Aucune boite trouvee." : res;
    }
    /**
     * Ajoute une pièce dans une boîte.
     *
     * @param numBoite numéro de la boîte
     * @param numPiece numéro de la pièce
     * @param idCouleur identifiant de la couleur
     * @param quantite quantité à ajouter
     * @param enSupplement vrai si la pièce est en supplément
     * @throws SQLException si la requête SQL échoue
     */
    public void ajouterPieceDansBoite(String numBoite, String numPiece, int idCouleur, int quantite,
            boolean enSupplement) throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement(
                "INSERT INTO CONTENIRP (idcont, numpiece, idcoul, quantitep, en_supplement) " +
                        "VALUES ((SELECT idcont FROM CONTENU WHERE numboite = ?), ?, ?, ?, ?)");
        ps.setString(1, numBoite);
        ps.setString(2, numPiece);
        ps.setInt(3, idCouleur);
        ps.setInt(4, quantite);
        ps.setString(5, enSupplement ? "O" : "N");
        ps.executeUpdate();
        ps.close();
    }
    /**
     * Ajoute une figurine dans une boîte.
     *
     * @param numBoite numéro de la boîte
     * @param idFig identifiant de la figurine
     * @param quantite quantité à ajouter
     * @throws SQLException si la requête SQL échoue
     */
    public void ajouterFigurineDansBoite(String numBoite, String idFig, int quantite)
            throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement(
                "INSERT INTO CONTENIRF (idcont, idfig, quantitef) " +
                        "VALUES ((SELECT idcont FROM CONTENU WHERE numboite = ?), ?, ?)");
        ps.setString(1, numBoite);
        ps.setString(2, idFig);
        ps.setInt(3, quantite);
        ps.executeUpdate();
        ps.close();
    }
    /**
     * Ajoute une sous-boîte dans une boîte.
     *
     * @param numBoiteParent numéro de la boîte parent
     * @param numSousBoite numéro de la sous-boîte
     * @param quantite quantité à ajouter
     * @throws SQLException si la requête SQL échoue
     */
    public void ajouterSousBoiteDansBoite(String numBoiteParent, String numSousBoite, int quantite)
            throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement(
                "INSERT INTO CONTENIRB (idcont, numboite, quantiteb) " +
                        "VALUES ((SELECT idcont FROM CONTENU WHERE numboite = ?), ?, ?)");
        ps.setString(1, numBoiteParent);
        ps.setString(2, numSousBoite);
        ps.setInt(3, quantite);
        ps.executeUpdate();
        ps.close();
    }

}