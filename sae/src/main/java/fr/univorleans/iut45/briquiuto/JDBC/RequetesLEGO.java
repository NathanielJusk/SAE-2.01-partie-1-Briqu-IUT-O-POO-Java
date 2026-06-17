package fr.univorleans.iut45.briquiuto.JDBC;

import fr.univorleans.iut45.briquiuto.modele.Boite;
import fr.univorleans.iut45.briquiuto.modele.BoiteComposee;
import fr.univorleans.iut45.briquiuto.modele.BriqueCollectionManager;
import fr.univorleans.iut45.briquiuto.modele.Categorie;
import fr.univorleans.iut45.briquiuto.modele.Figurine;
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
     * Recherche les boîtes par thème.
     *
     * @param theme le thème à rechercher
     * @return liste des boîtes correspondant au thème
     * @throws SQLException si la requête SQL échoue
     */
    public List<Boite> rechercherBoitesParTheme(Theme theme) throws SQLException {
        List<Boite> boites = new ArrayList<>();
        PreparedStatement ps = laConnexion.prepareStatement(
                "SELECT * FROM BOITE WHERE idtheme = ?");
        ps.setInt(1, theme.getIdTheme());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Boite b = new BoiteComposee(
                    rs.getString("numboite"),
                    rs.getInt("nbpieces"),
                    rs.getString("nomboite"),
                    rs.getInt("annee"));
            boites.add(b);
        }
        rs.close();
        ps.close();
        return boites;
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
        // On récupère la boîte ET son thème
        ResultSet rs = st
                .executeQuery("SELECT b.*, t.nomtheme FROM BOITE b LEFT JOIN THEME t ON b.idtheme = t.idtheme");
        while (rs.next()) {
            Boite b = new BoiteComposee(
                    rs.getString("numboite"),
                    rs.getInt("nbpieces"),
                    rs.getString("nomboite"),
                    rs.getInt("annee"));

            // --- NOUVEAU : On attache le thème à la boîte ---
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
        // On récupère la pièce ET sa catégorie
        ResultSet rs = st.executeQuery("SELECT p.*, c.nomcat FROM PIECE p LEFT JOIN CATEGORIE c ON p.idcat = c.idcat");
        while (rs.next()) {
            Piece p = new Piece(rs.getString("numpiece"), rs.getString("nompiece"));

            // --- NOUVEAU : On attache la catégorie à la pièce ---
            int idCat = rs.getInt("idcat");
            if (!rs.wasNull()) {
                Categorie cat = new Categorie(idCat, rs.getString("nomcat"));
                p.setCategorie(cat);
            }
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
                    rs.getString("nomtheme"));
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
     * @param numBoite     numéro de la boîte
     * @param numPiece     numéro de la pièce
     * @param idCouleur    identifiant de la couleur
     * @param quantite     quantité à ajouter
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
     * @param idFig    identifiant de la figurine
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
     * @param numSousBoite   numéro de la sous-boîte
     * @param quantite       quantité à ajouter
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

    // ── Mise à jour ────────────────────────────────────────────────────────
    /**
     * Modifie les informations d'une boîte existante dans la base de données.
     * Le numéro de la boîte (numboite) sert de clé pour trouver la bonne ligne à
     * modifier.
     *
     * @param boite l'objet boîte contenant les nouvelles valeurs à enregistrer
     * @throws SQLException si la requête SQL échoue
     */
    public void modifierBoite(Boite boite) throws SQLException {
        PreparedStatement ps = laConnexion.prepareStatement(
                "UPDATE BOITE SET nomboite = ?, annee = ?, nbpieces = ?, idtheme = ?, imgUrl = ? WHERE numboite = ?");
        // On injecte les nouvelles valeurs modifiées
        ps.setString(1, boite.getNom());
        ps.setInt(2, boite.getAnnee());
        ps.setInt(3, boite.getNbPiece());
        ps.setInt(4, boite.getTheme() != null ? boite.getTheme().getIdTheme() : 1);
        ps.setString(5, boite.getImgUrl());

        // Le WHERE utilise le numéro exact pour ne modifier QUE cette boîte
        ps.setString(5, boite.getNumero());

        // Exécute la mise à jour en base
        ps.executeUpdate();
        ps.close();
    }

    /**
     * Permet aux contrôleurs d'accéder au gestionnaire de la collection en mémoire.
     */
    public BriqueCollectionManager getManager() {
        return this.manager;
    }

    /**
     * Charge toutes les données de la base de données vers le manager en mémoire.
     */
    public void chargerDonneesDansManager() {
        try {
            // 1. Vider les catalogues actuels pour éviter les doublons
            manager.getCatalogueBoites().clear();
            manager.getCataloguePieces().clear();

            // 2. Charger les Boîtes
            Statement st = laConnexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM BOITE");
            while (rs.next()) {
                Boite b = new BoiteComposee(
                        rs.getString("numboite"),
                        rs.getInt("nbpieces"),
                        rs.getString("nomboite"),
                        rs.getInt("annee"));
                manager.ajouterBoite(b);
            }

            // 3. Charger les Pièces
            rs = st.executeQuery("SELECT * FROM PIECE");
            while (rs.next()) {
                Piece p = new Piece(
                        rs.getString("numpiece"),
                        rs.getString("nompiece")
                // Note : Si ta classe Piece nécessite une catégorie,
                // tu devras ici faire un SELECT joint avec la table CATEGORIE
                );
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

    /**
     * Recherche toutes les boîtes de la base de données qui contiennent une pièce
     * précise.
     * Utilise des jointures SQL pour éviter de surcharger la mémoire vive.
     *
     * @param numPiece Le numéro exact de la pièce
     * @return La liste des boîtes trouvées
     * @throws SQLException En cas d'erreur de base de données
     */
    public List<Boite> rechercherBoitesContenantPiece(String numPiece) throws SQLException {
        List<Boite> boitesTrouvees = new ArrayList<>();

        PreparedStatement ps = laConnexion.prepareStatement(
                "SELECT b.numboite, b.nomboite, b.annee, b.nbpieces " +
                        "FROM BOITE b " +
                        "JOIN CONTENU c ON b.numboite = c.numboite " +
                        "JOIN CONTENIRP cp ON c.idcont = cp.idcont " +
                        "WHERE cp.numpiece = ?");

        ps.setString(1, numPiece);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Boite b = new BoiteComposee(
                    rs.getString("numboite"),
                    rs.getInt("nbpieces"),
                    rs.getString("nomboite"),
                    rs.getInt("annee"));
            boitesTrouvees.add(b);
        }

        rs.close();
        ps.close();

        return boitesTrouvees;
    }

    /**
     * Récupère toutes les catégories de la base de données.
     * 
     * @return liste des catégories
     * @throws SQLException si la requête SQL échoue
     */
    public List<Categorie> getAllCategories() throws SQLException {
        List<Categorie> categories = new ArrayList<>();
        Statement st = laConnexion.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM CATEGORIE");
        while (rs.next()) {
            Categorie cat = new Categorie(
                    rs.getInt("idcat"),
                    rs.getString("nomcat"));
            categories.add(cat);
        }
        rs.close();
        st.close();
        return categories;
    }
    /**
     * Récupère toutes les figurines de la base de données.
     * @return liste des figurines
     * @throws SQLException si la requête SQL échoue
     */
    public List<Figurine> getAllFigurines() throws SQLException {
        List<Figurine> figurines = new ArrayList<>();
        Statement st = laConnexion.createStatement();
        // Assure-toi que les noms de colonnes correspondent à ceux de ton schéma SQL
        ResultSet rs = st.executeQuery("SELECT idfig, nomfig, nbparties FROM FIGURINE");
        
        while (rs.next()) {
            Figurine f = new Figurine(
                    rs.getString("idfig"),
                    rs.getString("nomfig"),
                    rs.getInt("nbparties")
            );
            figurines.add(f);
        }
        rs.close();
        st.close();
        return figurines;
    }

    public void ajouterFigurine(Figurine f) throws SQLException {
        String sql = "INSERT INTO FIGURINE (idfig, nomfig, nbparties) VALUES (?, ?, ?)";
        java.sql.PreparedStatement ps = laConnexion.prepareStatement(sql);
        ps.setString(1, f.getIdFig());
        ps.setString(2, f.getNomFig());
        ps.setInt(3, f.getNbParties());
        ps.executeUpdate();
        ps.close();
    }
}
