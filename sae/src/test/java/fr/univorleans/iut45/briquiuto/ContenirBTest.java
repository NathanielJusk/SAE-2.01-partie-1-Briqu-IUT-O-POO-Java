package fr.univorleans.iut45.briquiuto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContenirBTest {
    
    @Test
    public void testSetGetBoite() {
        ContenirB contenirB = new ContenirB();
        Boite boite = new Boite("B001", 500, "Boîte Test", 2022);
        contenirB.setBoite(boite);
        assertEquals(boite, contenirB.getBoite());
    }
    
    @Test
    public void testSetGetQuantiteB() {
        ContenirB contenirB = new ContenirB();
        contenirB.setQuantiteB(3);
        assertEquals(3, contenirB.getQuantiteB());
    }
    
    @Test
    public void testSetBoiteNull() {
        ContenirB contenirB = new ContenirB();
        contenirB.setBoite(null);
        assertNull(contenirB.getBoite());
    }
    
}
