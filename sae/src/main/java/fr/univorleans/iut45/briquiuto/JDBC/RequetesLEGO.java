package fr.univorleans.iut45.briquiuto.JDBC;

import fr.univorleans.iut45.briquiuto.modele.Boite;
import fr.univorleans.iut45.briquiuto.modele.BoiteComposee;
import fr.univorleans.iut45.briquiuto.modele.BriqueCollectionManager;
import fr.univorleans.iut45.briquiuto.modele.Categorie;
import fr.univorleans.iut45.briquiuto.modele.Couleur;
import fr.univorleans.iut45.briquiuto.modele.Figurine;
import fr.univorleans.iut45.briquiuto.modele.Piece;
import fr.univorleans.iut45.briquiuto.modele.Theme;

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
        ResultSet rs = st.executeQuery("SELECT idtheme, nomtheme FROM THEME");
        while (rs.next()) {
            themes.add(new Theme(rs.getInt("idtheme"), rs.getString("nomtheme")));
        }
        rs.close();
        st.close();
        return themes;
    }

    public Theme rechercherThemeParId(int idTheme) throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("SELECT idtheme, nomtheme FROM THEME WHERE idtheme = ?");
        ps.setInt(1, idTheme);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Theme themeTrouve = new Theme(rs.getInt("idtheme"), rs.getString("nomtheme"));
            rs.close();
            ps.close();
            return themeTrouve;
        }
        rs.close();
        ps.close();
        return null;
    }

    // ── BOITE ─────────────────────────────────────────────────────────────

    public void ajouterBoite(Boite boite) throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement(
                "INSERT INTO BOITE (numboite, nomboite, annee, nbpieces, idtheme, imgUrl) VALUES (?, ?, ?, ?, ?, ?)");
        ps.setString(1, boite.getNumero());
        ps.setString(2, boite.getNom());
        ps.setInt(3, boite.getAnnee());
        ps.setInt(4, boite.getNbPiece());
        ps.setInt(5, boite.getTheme() != null ? boite.getTheme().getIdTheme() : 1);
        ps.setString(6, boite.getImgUrl());
        ps.executeUpdate();
        ps.close();
    }

    public void modifierBoite(Boite boite) throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement(
                "UPDATE BOITE SET nomboite = ?, annee = ?, nbpieces = ?, idtheme = ?, imgUrl = ? WHERE numboite = ?");
        ps.setString(1, boite.getNom());
        ps.setInt(2, boite.getAnnee());
        ps.setInt(3, boite.getNbPiece());
        ps.setInt(4, boite.getTheme() != null ? boite.getTheme().getIdTheme() : 1);
        ps.setString(5, boite.getImgUrl());
        ps.setString(6, boite.getNumero()); 
        ps.executeUpdate();
        ps.close();
    }

    public Boite rechercherBoiteParNom(String nom) throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement(
                "SELECT numboite, nomboite, annee, nbpieces, idtheme, imgUrl FROM BOITE WHERE nomboite LIKE ?");
        ps.setString(1, "%" + nom + "%");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Boite b = new BoiteComposee(
                    rs.getString("numboite"), rs.getInt("nbpieces"), rs.getString("nomboite"), rs.getInt("annee"));
            b.setImgUrl(rs.getString("imgUrl"));
            rs.close();
            ps.close();
            return b;
        }
        rs.close();
        ps.close();
        return null;
    }

    public List<Boite> rechercherBoitesParTheme(Theme theme) throws SQLException {
        List<Boite> boites = new ArrayList<>();
        PreparedStatement ps = laConnexion.prepareStatement(
                "SELECT numboite, nomboite, annee, nbpieces, idtheme, imgUrl FROM BOITE WHERE idtheme = ?");
        ps.setInt(1, theme.getIdTheme());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Boite b = new BoiteComposee(
                    rs.getString("numboite"), rs.getInt("nbpieces"), rs.getString("nomboite"), rs.getInt("annee"));
            b.setImgUrl(rs.getString("imgUrl"));
            boites.add(b);
        }
        rs.close();
        ps.close();
        return boites;
    }

    public List<Boite> getAllBoites() throws SQLException {
        List<Boite> boites = new ArrayList<>();
        Statement st = laConnexion.createStatement();
        String sql = "SELECT b.numboite, b.nomboite, b.annee, b.nbpieces, b.idtheme, b.imgUrl, t.nomtheme " +
                     "FROM BOITE b LEFT JOIN THEME t ON b.idtheme = t.idtheme";
        ResultSet rs = st.executeQuery(sql);
        
        while (rs.next()) {
            Boite b = new BoiteComposee(
                    rs.getString("numboite"), rs.getInt("nbpieces"), rs.getString("nomboite"), rs.getInt("annee"));
            b.setImgUrl(rs.getString("imgUrl")); 
            
            int idTheme = rs.getInt("idtheme");
            if (!rs.wasNull()) {
                Theme t = new Theme(idTheme, rs.getString("nomtheme"));
                b.setTheme(t);
            }
            boites.add(b);
        }
        rs.close();
        st.close();
        return boites;
    }

    public Boite rechercherBoiteParNumero(String numero) throws SQLException {
        String sql = "SELECT b.numboite, b.nomboite, b.annee, b.nbpieces, b.idtheme, b.imgUrl, t.nomtheme " +
                     "FROM BOITE b LEFT JOIN THEME t ON b.idtheme = t.idtheme WHERE b.numboite = ?";
        PreparedStatement ps = laConnexion.prepareStatement(sql);
        ps.setString(1, numero);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            Boite b = new BoiteComposee(
                    rs.getString("numboite"), rs.getInt("nbpieces"), rs.getString("nomboite"), rs.getInt("annee"));
            b.setImgUrl(rs.getString("imgUrl")); 
            
            int idTheme = rs.getInt("idtheme");
            if (!rs.wasNull()) {
                Theme t = new Theme(idTheme, rs.getString("nomtheme"));
                b.setTheme(t);
            }
            rs.close();
            ps.close();
            return b;
        }
        rs.close();
        ps.close();
        return null;
    }
    
    public List<Boite> rechercherBoitesContenantPiece(String numPiece) throws SQLException {
        List<Boite> boitesTrouvees = new ArrayList<>();
        PreparedStatement ps = laConnexion.prepareStatement(
                "SELECT b.numboite, b.nomboite, b.annee, b.nbpieces, b.imgUrl " +
                        "FROM BOITE b " +
                        "JOIN CONTENU c ON b.numboite = c.numboite " +
                        "JOIN CONTENIRP cp ON c.idcont = cp.idcont " +
                        "WHERE cp.numpiece = ?");
        ps.setString(1, numPiece);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Boite b = new BoiteComposee(
                    rs.getString("numboite"), rs.getInt("nbpieces"), rs.getString("nomboite"), rs.getInt("annee"));
            b.setImgUrl(rs.getString("imgUrl"));
            boitesTrouvees.add(b);
        }
        rs.close();
        ps.close();
        return boitesTrouvees;
    }

    // ── PIECE ET COMPOSANTS ───────────────────────────────────────────────

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
    // La sous-requête magique pour aller chercher 1 image d'exemple pour cette pièce !
String sql = "SELECT p.numpiece, p.nompiece, p.idcat, " +
                 "(SELECT imgUrl FROM CONTENIRP cp WHERE cp.numpiece = p.numpiece AND cp.imgUrl IS NOT NULL LIMIT 1) as imgUrl " +
                 "FROM PIECE p LIMIT 500";
    
    try (Statement stmt = laConnexion.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
            Piece p = new Piece(
                rs.getString("numpiece"),
                rs.getString("nompiece")
            );
            // Si tu as la catégorie, ajoute-la ici selon ton code actuel
            p.setImgUrl(rs.getString("imgUrl"));
            pieces.add(p);
        }
    }
    return pieces;
}
    public List<Categorie> getAllCategories() throws SQLException {
        List<Categorie> categories = new ArrayList<>();
        Statement st = laConnexion.createStatement();
        ResultSet rs = st.executeQuery("SELECT idcat, nomcat FROM CATEGORIE");
        while (rs.next()) {
            categories.add(new Categorie(rs.getInt("idcat"), rs.getString("nomcat")));
        }
        rs.close();
        st.close();
        return categories;
    }

public List<Figurine> getAllFigurines() throws SQLException {
    List<Figurine> figurines = new ArrayList<>();
    // On ajoute imgUrl dans le SELECT
    String sql = "SELECT idfig, nomfig, nbparties, imgUrl FROM FIGURINE LIMIT 500";
    
    try (Statement stmt = laConnexion.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
            Figurine f = new Figurine(
                rs.getString("idfig"),
                rs.getString("nomfig"),
                rs.getInt("nbparties")
            );
            // On injecte l'image !
            f.setImgUrl(rs.getString("imgUrl"));
            figurines.add(f);
        }
    }
    return figurines;
}
    public void ajouterFigurine(Figurine f) throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("INSERT INTO FIGURINE (idfig, nomfig, nbparties) VALUES (?, ?, ?)");
        ps.setString(1, f.getIdFig());
        ps.setString(2, f.getNomFig());
        ps.setInt(3, f.getNbParties());
        ps.executeUpdate();
        ps.close();
    }

    public List<Couleur> getAllCouleurs() throws SQLException {
        List<Couleur> couleurs = new ArrayList<>();
        Statement st = laConnexion.createStatement();
        ResultSet rs = st.executeQuery("SELECT idcoul, nomcoul, rgb, transparent FROM COULEUR ORDER BY nomcoul ASC");
        while (rs.next()) {
            String transStr = rs.getString("transparent");
            boolean isTransparent = transStr != null && (transStr.equalsIgnoreCase("t") || transStr.equalsIgnoreCase("O"));
            couleurs.add(new Couleur(rs.getInt("idcoul"), rs.getString("nomcoul"), rs.getString("rgb"), isTransparent));
        }
        rs.close();
        st.close();
        return couleurs;
    }

    // ── CONTENU (MOC ET INVENTAIRES) ──────────────────────────────────────

    public void creerContenuPourBoite(String numBoite) throws SQLException {
        Statement st = laConnexion.createStatement();
        ResultSet rs = st.executeQuery("SELECT MAX(idcont) + 1 FROM CONTENU");
        int nextId = 1;
        if (rs.next()) {
            nextId = rs.getInt(1) == 0 ? 1 : rs.getInt(1); 
        }
        rs.close();
        st.close();

        PreparedStatement ps = laConnexion.prepareStatement(
                "INSERT INTO CONTENU (idcont, version, numboite) VALUES (?, 1, ?)");
        ps.setInt(1, nextId);
        ps.setString(2, numBoite);
        ps.executeUpdate();
        ps.close();
    }

    public void ajouterPieceDansBoite(String numBoite, String numPiece, int idCouleur, int quantite, boolean enSupplement) throws SQLException {
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

    public void ajouterFigurineDansBoite(String numBoite, String idFig, int quantite) throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement(
                "INSERT INTO CONTENIRF (idcont, idfig, quantitef) " +
                        "VALUES ((SELECT idcont FROM CONTENU WHERE numboite = ?), ?, ?)");
        ps.setString(1, numBoite);
        ps.setString(2, idFig);
        ps.setInt(3, quantite);
        ps.executeUpdate();
        ps.close();
    }

    public void ajouterSousBoiteDansBoite(String numBoiteParent, String numSousBoite, int quantite) throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement(
                "INSERT INTO CONTENIRB (idcont, numboite, quantiteb) " +
                        "VALUES ((SELECT idcont FROM CONTENU WHERE numboite = ?), ?, ?)");
        ps.setString(1, numBoiteParent);
        ps.setString(2, numSousBoite);
        ps.setInt(3, quantite);
        ps.executeUpdate();
        ps.close();
    }

    // ── AFFICHAGES TEXTUELS ───────────────────────────────────────────────

    public String listerBoitesParTheme(int idTheme) throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement("SELECT numboite, nomboite, annee FROM BOITE WHERE idtheme = ?");
        ps.setInt(1, idTheme);
        ResultSet rs = ps.executeQuery();
        String res = "";
        while (rs.next()) {
            res += "[" + rs.getString("numboite") + "] " + rs.getString("nomboite") + " (" + rs.getInt("annee") + ")\n";
        }
        rs.close();
        ps.close();
        return res.isEmpty() ? "Aucune boite trouvee." : res;
    }

    public String listerBoitesParThemeAvecSousThemes(int idTheme) throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement(
                "SELECT numboite, nomboite, annee FROM BOITE WHERE idtheme = ? OR idtheme IN (SELECT idtheme FROM THEME WHERE idtheme_pere = ?)");
        ps.setInt(1, idTheme);
        ps.setInt(2, idTheme);
        ResultSet rs = ps.executeQuery();
        String res = "";
        while (rs.next()) {
            res += "[" + rs.getString("numboite") + "] " + rs.getString("nomboite") + " (" + rs.getInt("annee") + ")\n";
        }
        rs.close();
        ps.close();
        return res.isEmpty() ? "Aucune boite trouvee." : res;
    }

    public String listerPiecesBoite(String numBoite) throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement(
                "SELECT p.nompiece, co.nomcoul, cp.quantitep, cp.en_supplement, cp.imgUrl " +
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
            String urlImage = rs.getString("imgUrl");
            
            res += "- " + rs.getString("nompiece")
                    + " | Couleur : " + rs.getString("nomcoul")
                    + " | Quantite : " + rs.getInt("quantitep")
                    + (supplement ? " [OUI en supplement ! ]" : "")
                    + (urlImage != null ? " | Image : " + urlImage : "")
                    + "\n";
        }
        rs.close();
        ps.close();
        return res.isEmpty() ? "Aucune piece trouvee." : res;
    }

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
            res += "- " + rs.getString("nomfig") + " | Quantite : " + rs.getInt("quantitef") + "\n";
        }
        rs.close();
        ps.close();
        return res.isEmpty() ? "Aucune figurine trouvee." : res;
    }

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

    // ── GESTION MANAGER (MÉMOIRE) ─────────────────────────────────────────

    public BriqueCollectionManager getManager() {
        return this.manager;
    }

    public void chargerDonneesDansManager() {
        try {
            manager.getCatalogueBoites().clear();
            manager.getCataloguePieces().clear();

            Statement st = laConnexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT numboite, nomboite, annee, nbpieces, idtheme, imgUrl FROM BOITE");
            while (rs.next()) {
                Boite b = new BoiteComposee(
                        rs.getString("numboite"), rs.getInt("nbpieces"), rs.getString("nomboite"), rs.getInt("annee"));
                
                b.setImgUrl(rs.getString("imgUrl")); 
                manager.ajouterBoite(b);
            }

            rs = st.executeQuery("SELECT numpiece, nompiece FROM PIECE");
            while (rs.next()) {
                Piece p = new Piece(rs.getString("numpiece"), rs.getString("nompiece"));
                manager.getCataloguePieces().add(p);
            }

            rs.close();
            st.close();
            System.out.println("Catalogue chargé en mémoire (" + manager.getCatalogueBoites().size() + " boîtes, "
                    + manager.getCataloguePieces().size() + " pièces).");
        } catch (SQLException e) {
            System.out.println("Erreur SQL lors du chargement des données : " + e.getMessage());
        }
    }

    // ==========================================================
    // MÉTHODES POUR LE TABLEAU À 5 COLONNES 
    // ==========================================================

    public List<String[]> getDetailsPiecesBoite(String numBoite) throws SQLException {
        List<String[]> liste = new ArrayList<>();
        java.sql.PreparedStatement ps = laConnexion.prepareStatement(
                "SELECT p.nompiece, co.nomcoul, cp.quantitep, cp.en_supplement, cp.imgUrl " +
                        "FROM PIECE p " +
                        "JOIN CONTENIRP cp ON p.numpiece = cp.numpiece " +
                        "JOIN CONTENU c ON cp.idcont = c.idcont " +
                        "JOIN COULEUR co ON cp.idcoul = co.idcoul " +
                        "WHERE c.numboite = ?");
        ps.setString(1, numBoite);
        java.sql.ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            String[] ligne = new String[5];
            ligne[0] = rs.getString("nompiece"); // Colonne 1 : Nom
            ligne[1] = rs.getString("nomcoul");  // Colonne 2 : Couleur
            ligne[2] = String.valueOf(rs.getInt("quantitep")); // Colonne 3 : Quantité
            ligne[3] = "O".equals(rs.getString("en_supplement")) ? "Oui" : "Non"; // Colonne 4 : Supplément
            ligne[4] = rs.getString("imgUrl"); // Colonne 5 : Image
            liste.add(ligne);
        }
        rs.close();
        ps.close();
        return liste;
    }

    public List<String[]> getDetailsFigurinesBoite(String numBoite) throws SQLException {
        List<String[]> liste = new ArrayList<>();
        java.sql.PreparedStatement ps = laConnexion.prepareStatement(
                "SELECT f.nomfig, cf.quantitef " +
                        "FROM FIGURINE f " +
                        "JOIN CONTENIRF cf ON f.idfig = cf.idfig " +
                        "JOIN CONTENU c ON cf.idcont = c.idcont " +
                        "WHERE c.numboite = ?");
        ps.setString(1, numBoite);
        java.sql.ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            String[] ligne = new String[5];
            ligne[0] = "[Figurine] " + rs.getString("nomfig");
            ligne[1] = "-";
            ligne[2] = String.valueOf(rs.getInt("quantitef"));
            ligne[3] = "-";
            ligne[4] = ""; // Pas d'image pour les figurines
            liste.add(ligne);
        }
        rs.close();
        ps.close();
        return liste;
    }
}