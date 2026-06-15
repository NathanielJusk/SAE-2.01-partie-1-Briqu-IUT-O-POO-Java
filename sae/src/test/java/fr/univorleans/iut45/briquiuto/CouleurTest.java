package fr.univorleans.iut45.briquiuto;

import org.junit.jupiter.api.Test;

import fr.univorleans.iut45.briquiuto.modele.Couleur;

import static org.junit.jupiter.api.Assertions.*;

public class CouleurTest {
    
    @Test
    public void testConstructeur() {
        Couleur couleur = new Couleur(1, "Bleu", "0000FF", false);
        assertEquals(1, couleur.getIdCoul());
        assertEquals("Bleu", couleur.getNomCoul());
        assertEquals("0000FF", couleur.getRgb());
        assertFalse(couleur.isTransparent());
    }
    
    @Test
    public void testSetGetIdCoul() {
        Couleur couleur = new Couleur(1, "Bleu", "0000FF", false);
        couleur.setIdCoul(2);
        assertEquals(2, couleur.getIdCoul());
    }
    
    @Test
    public void testSetGetNomCoul() {
        Couleur couleur = new Couleur(1, "Bleu", "0000FF", false);
        couleur.setNomCoul("Vert");
        assertEquals("Vert", couleur.getNomCoul());
    }
    
    @Test
    public void testSetGetRgb() {
        Couleur couleur = new Couleur(1, "Bleu", "0000FF", false);
        couleur.setRgb("00FF00");
        assertEquals("00FF00", couleur.getRgb());
    }
    
    @Test
    public void testSetGetTransparent() {
        Couleur couleur = new Couleur(1, "Bleu", "0000FF", false);
        couleur.setTransparent(true);
        assertTrue(couleur.isTransparent());
    }
    
}
