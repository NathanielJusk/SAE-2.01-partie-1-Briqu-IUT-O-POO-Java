package fr.univorleans.iut45.briquiuto;

import org.junit.jupiter.api.Test;

import fr.univorleans.iut45.briquiuto.modele.ContenirF;
import fr.univorleans.iut45.briquiuto.modele.Figurine;

import static org.junit.jupiter.api.Assertions.*;

public class ContenirFTest {
    
    @Test
    public void testSetGetFigurine() {
        ContenirF contenirF = new ContenirF(null, 0);
        Figurine figurine = new Figurine("1", "Soldat", 5);
        contenirF.setFigurine(figurine);
        assertEquals(figurine, contenirF.getFigurine());
    }
    
    @Test
    public void testSetGetQuantiteF() {
        ContenirF contenirF = new ContenirF(null, 0);
        contenirF.setQuantiteF(2);
        assertEquals(2, contenirF.getQuantiteF());
    }
    
    @Test
    public void testSetFigurineNull() {
        ContenirF contenirF = new ContenirF(null, 0);
        contenirF.setFigurine(null);
        assertNull(contenirF.getFigurine());
    }
    
}
