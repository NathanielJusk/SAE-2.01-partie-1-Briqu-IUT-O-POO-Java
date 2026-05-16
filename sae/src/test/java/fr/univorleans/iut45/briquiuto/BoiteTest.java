package fr.univorleans.iut45.briquiuto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoiteTest {
    
    @Test
    public void testConstructeur() {
        Boite boite = new BoiteComposee("L001", 500, "LEGO Classic", 2020);
        assertEquals("L001", boite.getNumero());
        assertEquals(500, boite.getNbPiece());
        assertEquals("LEGO Classic", boite.getNom());
        assertEquals(2020, boite.getAnnee());
    }
    
    @Test
    public void testSetGetNumero() {
        Boite boite = new BoiteComposee("L001", 500, "LEGO Classic", 2020);
        boite.setNumero("L002");
        assertEquals("L002", boite.getNumero());
    }
    
    @Test
    public void testSetGetNom() {
        Boite boite = new BoiteComposee("L001", 500, "LEGO Classic", 2020);
        boite.setNom("LEGO Technic");
        assertEquals("LEGO Technic", boite.getNom());
    }
    
    @Test
    public void testSetGetNbPiece() {
        Boite boite = new BoiteComposee("L001", 500, "LEGO Classic", 2020);
        boite.setNbPiece(1000);
        assertEquals(1000, boite.getNbPiece());
    }
    
    @Test
    public void testSetGetAnnee() {
        Boite boite = new BoiteComposee("L001", 500, "LEGO Classic", 2020);
        boite.setAnnee(2023);
        assertEquals(2023, boite.getAnnee());
    }
    
    @Test
    public void testEstComplete() {
        Boite boite = new BoiteComposee("L001", 500, "LEGO Classic", 2020);
        assertFalse(boite.estComplete());
    }
    
}