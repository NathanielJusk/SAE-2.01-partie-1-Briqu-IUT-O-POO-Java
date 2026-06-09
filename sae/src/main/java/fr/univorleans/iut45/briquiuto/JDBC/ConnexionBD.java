package fr.univorleans.iut45.briquiuto.JDBC;

import java.sql.*;

public class ConnexionBD {

    private Connection mysql;
    private boolean connecte = false;

    public ConnexionBD() throws ClassNotFoundException {
        this.mysql = null;
        this.connecte = false;
        Class.forName("org.mariadb.jdbc.Driver");
    }

    public void connecter(String nomServeur, String nomBase,
            String nomLogin, String motDePasse) throws SQLException {
        this.mysql = null;
        this.connecte = false;
        this.mysql = DriverManager.getConnection(
                "jdbc:mariadb://" + nomServeur + ":3306/" + nomBase, 
                nomLogin, motDePasse);
        this.connecte = true;
    }

    public void close() throws SQLException {
        this.mysql.close();
        this.connecte = false;
    }

    public boolean isConnecte() {
        return this.connecte;
    }

    public Statement createStatement() throws SQLException {
        return this.mysql.createStatement();
    }

    public PreparedStatement prepareStatement(String requete) throws SQLException {
        return this.mysql.prepareStatement(requete);
    }
}