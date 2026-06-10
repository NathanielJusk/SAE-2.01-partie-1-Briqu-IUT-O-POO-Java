package fr.univorleans.iut45.briquiuto.JDBC;

import fr.univorleans.iut45.briquiuto.BriqueCollectionManager;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequetesLEGOTest {

    @Test
    void listerSousBoite_retourneLesSousBoitesEtLeursQuantites() throws Exception {
        StringBuilder sql = new StringBuilder();
        final boolean[] hasNext = {true};

        ConnexionBD connexion = new ConnexionBD() {
            @Override
            public PreparedStatement prepareStatement(String requete) {
                sql.append(requete);
                return (PreparedStatement) Proxy.newProxyInstance(
                        PreparedStatement.class.getClassLoader(),
                        new Class<?>[]{PreparedStatement.class},
                        (proxy, method, args) -> {
                            switch (method.getName()) {
                                case "setString":
                                    return null;
                                case "executeQuery":
                                    return (ResultSet) Proxy.newProxyInstance(
                                            ResultSet.class.getClassLoader(),
                                            new Class<?>[]{ResultSet.class},
                                            (rsProxy, rsMethod, rsArgs) -> {
                                                switch (rsMethod.getName()) {
                                                    case "next":
                                                        boolean current = hasNext[0];
                                                        hasNext[0] = false;
                                                        return current;
                                                    case "getString":
                                                        String column = (String) rsArgs[0];
                                                        if ("nomboite".equals(column)) return "Boîte enfant";
                                                        return null;
                                                    case "getInt":
                                                        return 3;
                                                    case "close":
                                                        return null;
                                                    default:
                                                        return null;
                                                }
                                            });
                                case "close":
                                    return null;
                                default:
                                    return null;
                            }
                        });
            }
        };

        RequetesLEGO requetesLEGO = new RequetesLEGO(connexion, new BriqueCollectionManager());

        String resultat = requetesLEGO.listerSousBoite("B-001");

        assertEquals("- Boîte enfant | Quantite : 3\n", resultat);
    }
}
