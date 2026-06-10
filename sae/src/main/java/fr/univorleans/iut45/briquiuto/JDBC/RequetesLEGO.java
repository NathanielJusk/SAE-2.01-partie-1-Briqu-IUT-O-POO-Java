package fr.univorleans.iut45.briquiuto.JDBC;

import fr.univorleans.iut45.briquiuto.*;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class RequetesLEGO {

    private ConnexionBD laConnexion;
    private BriqueCollectionManager manager;

    public RequetesLEGO(ConnexionBD laConnexion, BriqueCollectionManager manager) {
        this.laConnexion = laConnexion;
        this.manager = manager;
    }

    // ── THEME ─────────────────────────────────────────────────────────────

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

    public void ajouterPiece(Piece piece) throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement(
                "INSERT INTO PIECE (numpiece, nompiece, idcat) VALUES (?, ?, ?)");
        ps.setString(1, piece.getNumPiece());
        ps.setString(2, piece.getNomPiece());
        ps.setInt(3, piece.getCategorie() != null ? piece.getCategorie().getIdCat() : 1);
        ps.executeUpdate();
        ps.close();
    }

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
    // adil : a faire
    // public String listerSousBoite(String numBoite) throws SQLException{

    // }

}