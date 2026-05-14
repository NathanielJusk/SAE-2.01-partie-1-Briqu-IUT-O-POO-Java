package fr.univorleans.iut45.briquiuto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoiteComposeeTest {
    
    @Test
    public void testConstructeur() {
        BoiteComposee boiteComposee = new BoiteComposee("BC001", 2000, "LEGO Architecture", 2021);
        assertEquals("BC001", boiteComposee.getNumero());
        assertEquals(2000, boiteComposee.getNbPiece());
        assertEquals("LEGO Architecture", boiteComposee.getNom());
        assertEquals(2021, boiteComposee.getAnnee());
    }
    
    @Test
    public void testHeritageBoite() {
        BoiteComposee boiteComposee = new BoiteComposee("BC001", 2000, "LEGO Architecture", 2021);
        assertTrue(boiteComposee instanceof Boite);
    }
    
    @Test
    public void testSetGetNumero() {
        BoiteComposee boiteComposee = new BoiteComposee("BC001", 2000, "LEGO Architecture", 2021);
        boiteComposee.setNumero("BC002");
        assertEquals("BC002", boiteComposee.getNumero());
    }
    
}
