package fr.univorleans.iut45.briquiuto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FigurinTest {
    
    @Test
    public void testConstructeur() {
        Figurine figurine = new Figurine(1, "Chevalier", 3);
        assertEquals("Chevalier", figurine.getNomFig());
        assertEquals(1, figurine.getIdFig());
        assertEquals(3, figurine.getNbParties());
    }
    
    @Test
    public void testSetGetIdFig() {
        Figurine figurine = new Figurine(1, "Chevalier", 3);
        figurine.setIdFig(2);
        assertEquals(2, figurine.getIdFig());
    }
    
    @Test
    public void testSetGetNomFig() {
        Figurine figurine = new Figurine(1, "Chevalier", 3);
        figurine.setNomFig("Dragon");
        assertEquals("Dragon", figurine.getNomFig());
    }
    
    @Test
    public void testSetGetNbParties() {
        Figurine figurine = new Figurine(1, "Chevalier", 3);
        figurine.setNbParties(5);
        assertEquals(5, figurine.getNbParties());
    }
    
}
