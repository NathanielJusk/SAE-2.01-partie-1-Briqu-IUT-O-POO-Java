package fr.univorleans.iut45.briquiuto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContenirFTest {
    
    @Test
    public void testSetGetFigurine() {
        ContenirF contenirF = new ContenirF();
        Figurine figurine = new Figurine(1, "Soldat", 5);
        contenirF.setFigurine(figurine);
        assertEquals(figurine, contenirF.getFigurine());
    }
    
    @Test
    public void testSetGetQuantiteF() {
        ContenirF contenirF = new ContenirF();
        contenirF.setQuantiteF(2);
        assertEquals(2, contenirF.getQuantiteF());
    }
    
    @Test
    public void testSetFigurineNull() {
        ContenirF contenirF = new ContenirF();
        contenirF.setFigurine(null);
        assertNull(contenirF.getFigurine());
    }
    
}
