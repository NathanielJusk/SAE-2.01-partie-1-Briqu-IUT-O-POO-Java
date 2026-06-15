package fr.univorleans.iut45.briquiuto.modele;

/**
 * Fournit des informations sur la version du système Java utilisé.
 */
public class SystemInfo {

    /**
     * Retourne la version de Java en cours d'exécution.
     *
     * @return version Java
     */
    public static String javaVersion() {
        return System.getProperty("java.version");
    }

    /**
     * Retourne la version de JavaFX si disponible.
     *
     * @return version JavaFX
     */
    public static String javafxVersion() {
        return System.getProperty("javafx.version");
    }

}
