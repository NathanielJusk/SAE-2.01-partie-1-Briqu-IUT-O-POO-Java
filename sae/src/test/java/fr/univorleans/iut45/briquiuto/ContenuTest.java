package fr.univorleans.iut45.briquiuto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContenuTest {
    
    @Test
    public void testConstructeur() {
        Contenu contenu = new Contenu(1, 1);
        assertEquals(1, contenu.getIdContenu());
        assertEquals(1, contenu.getVersion());
        assertNotNull(contenu.getContenirPieces());
        assertNotNull(contenu.getContenirBoites());
        assertNotNull(contenu.getContenirFigurines());
    }
    
    @Test
    public void testSetGetIdContenu() {
        Contenu contenu = new Contenu(1, 1);
        contenu.setIdContenu(2);
        assertEquals(2, contenu.getIdContenu());
    }
    
    @Test
    public void testSetGetVersion() {
        Contenu contenu = new Contenu(1, 1);
        contenu.setVersion(2);
        assertEquals(2, contenu.getVersion());
    }
    
    @Test
    public void testListsInitialisees() {
        Contenu contenu = new Contenu(1, 1);
        assertTrue(contenu.getContenirPieces().isEmpty());
        assertTrue(contenu.getContenirBoites().isEmpty());
        assertTrue(contenu.getContenirFigurines().isEmpty());
    }
    
}
