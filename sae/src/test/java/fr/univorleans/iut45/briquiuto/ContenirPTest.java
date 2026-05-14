package fr.univorleans.iut45.briquiuto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContenirPTest {
    
    @Test
    public void testSetGetCouleur() {
        ContenirP contenirP = new ContenirP();
        Couleur couleur = new Couleur(1, "Rouge", "FF0000", false);
        contenirP.setCouleur(couleur);
        assertEquals(couleur, contenirP.getCouleur());
    }
    
    @Test
    public void testSetGetPiece() {
        ContenirP contenirP = new ContenirP();
        Piece piece = new Piece("P001", "Brique 2x4");
        contenirP.setPiece(piece);
        assertEquals(piece, contenirP.getPiece());
    }
    
    @Test
    public void testSetGetQuantiteP() {
        ContenirP contenirP = new ContenirP();
        contenirP.setQuantiteP(10);
        assertEquals(10, contenirP.getQuantiteP());
    }
    
    @Test
    public void testSetGetEstSupplement() {
        ContenirP contenirP = new ContenirP();
        contenirP.setEstSupplement(true);
        assertTrue(contenirP.isEstSupplement());
    }
    
}
