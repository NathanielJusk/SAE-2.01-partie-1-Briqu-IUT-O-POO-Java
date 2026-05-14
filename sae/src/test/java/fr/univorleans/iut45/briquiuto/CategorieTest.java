package fr.univorleans.iut45.briquiuto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CategorieTest {
    
    @Test
    public void testConstructeur() {
        Categorie categorie = new Categorie(1, "Briques");
        assertEquals(1, categorie.getIdCat());
        assertEquals("Briques", categorie.getNomCat());
    }
    
    @Test
    public void testSetGetIdCat() {
        Categorie categorie = new Categorie(1, "Briques");
        categorie.setIdCat(2);
        assertEquals(2, categorie.getIdCat());
    }
    
    @Test
    public void testSetGetNomCat() {
        Categorie categorie = new Categorie(1, "Briques");
        categorie.setNomCat("Figurines");
        assertEquals("Figurines", categorie.getNomCat());
    }
    
}
