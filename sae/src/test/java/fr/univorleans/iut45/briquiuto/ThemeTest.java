package fr.univorleans.iut45.briquiuto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ThemeTest {
    
    @Test
    public void testConstructeur() {
        Theme theme = new Theme(1, "Star Wars");
        assertEquals(1, theme.getIdTheme());
        assertEquals("Star Wars", theme.getNom());
        assertNotNull(theme.getSousThemes());
        assertTrue(theme.getSousThemes().isEmpty());
    }
    
    @Test
    public void testSetGetIdTheme() {
        Theme theme = new Theme(1, "Star Wars");
        theme.setIdTheme(2);
        assertEquals(2, theme.getIdTheme());
    }
    
    @Test
    public void testSetGetNom() {
        Theme theme = new Theme(1, "Star Wars");
        theme.setNom("Harry Potter");
        assertEquals("Harry Potter", theme.getNom());
    }
    
    @Test
    public void testEstParent() {
        Theme theme = new Theme(1, "Star Wars");
        assertTrue(theme.estParent());
    }
    
    @Test
    public void testRechercherBoitesParTheme() {
        Theme theme = new Theme(1, "Star Wars");
        assertNull(theme.rechercherBoitesParTheme());
    }
    
}
