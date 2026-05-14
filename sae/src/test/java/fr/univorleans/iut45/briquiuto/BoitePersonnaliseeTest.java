package fr.univorleans.iut45.briquiuto;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class BoitePersonnaliseeTest {
    
    @Test
    public void testConstructeur() {
        BoitePersonnalisee boitePersonnalisee = new BoitePersonnalisee("BP001", 500, "Ma Boîte", 2023);
        assertEquals("BP001", boitePersonnalisee.getNumero());
        assertEquals(500, boitePersonnalisee.getNbPiece());
        assertEquals("Ma Boîte", boitePersonnalisee.getNom());
        assertEquals(2023, boitePersonnalisee.getAnnee());
    }
    
    @Test
    public void testCreeIdentifiantUnique() {
        BoitePersonnalisee boitePersonnalisee = new BoitePersonnalisee("BP001", 500, "Ma Boîte", 2023);
        String id = boitePersonnalisee.creeIdentifiantUnique();
        assertTrue(id.startsWith("Perso-"));
    }
    
    @Test
    public void testComposerBoitePersonnalisee() {
        BoitePersonnalisee boitePersonnalisee = new BoitePersonnalisee("BP001", 500, "Ma Boîte", 2023);
        List<Piece> pieces = new ArrayList<>();
        Boite boiteComposee = boitePersonnalisee.composerBoitePersonnalisee("Ma créa", pieces, 2024);
        assertNotNull(boiteComposee);
        assertEquals(0, boiteComposee.getNbPiece());
    }
    
}
