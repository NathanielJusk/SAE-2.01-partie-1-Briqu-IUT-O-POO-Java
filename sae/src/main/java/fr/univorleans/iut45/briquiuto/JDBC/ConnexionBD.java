package fr.univorleans.iut45.briquiuto.JDBC;

import java.sql.*;

/**
 * Gère la connexion à la base de données MariaDB.
 * Cette classe ouvre, ferme et prépare les requêtes SQL.
 */
public class ConnexionBD {

    private Connection mysql;
    private boolean connecte = false;

    /**
     * Charge le driver MariaDB lors de la création de l'objet.
     *
     * @throws ClassNotFoundException si le driver n'est pas trouvé
     */
    public ConnexionBD() throws ClassNotFoundException {
        this.mysql = null;
        this.connecte = false;
        Class.forName("org.mariadb.jdbc.Driver");
    }

    /**
     * Connecte la base de données en utilisant les paramètres fournis.
     *
     * @param nomServeur nom du serveur de base
     * @param nomBase nom de la base de données
     * @param nomLogin login de connexion
     * @param motDePasse mot de passe de connexion
     * @throws SQLException si la connexion échoue
     */
    public void connecter(String nomServeur, String nomBase,
            String nomLogin, String motDePasse) throws SQLException {
        this.mysql = null;
        this.connecte = false;
        this.mysql = DriverManager.getConnection(
                "jdbc:mariadb://" + "servinfo-maria" + ":3306/" + nomBase,
                nomLogin, motDePasse);
        this.connecte = true;
    }

    /**
     * Retourne la connexion JDBC active.
     *
     * @return connexion SQL
     */
    public Connection getConnection() {   
        return this.mysql;
    }

    /**
     * Ferme la connexion à la base de données.
     *
     * @throws SQLException si la fermeture échoue
     */
    public void close() throws SQLException {
        this.mysql.close();
        this.connecte = false;
    }

    /**
     * Indique si la connexion est active.
     *
     * @return vrai si c'est connecté
     */
    public boolean isConnecte() {
        return this.connecte;
    }

    /**
     * Crée un statement SQL simple.
     *
     * @return statement SQL
     * @throws SQLException si la connexion n'est pas disponible
     */
    public Statement createStatement() throws SQLException {
        return this.mysql.createStatement();
    }

    /**
     * Prépare une requête SQL avec des paramètres.
     *
     * @param requete requête SQL avec des points d'interrogation
     * @return PreparedStatement prêt à recevoir des valeurs
     * @throws SQLException si la connexion n'est pas disponible
     */
    public PreparedStatement prepareStatement(String requete) throws SQLException {
        return this.mysql.prepareStatement(requete);
    }
}