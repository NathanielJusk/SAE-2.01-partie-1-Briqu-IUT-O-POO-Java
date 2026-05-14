package fr.univorleans.iut45.briquiuto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {
    
    @Test
    public void testConstructeur() {
        Piece piece = new Piece("P001", "Brique 2x4");
        assertEquals("P001", piece.getNumPiece());
        assertEquals("Brique 2x4", piece.getNomPiece());
    }
    
    @Test
    public void testSetGetNumPiece() {
        Piece piece = new Piece("P001", "Brique 2x4");
        piece.setNumPiece("P002");
        assertEquals("P002", piece.getNumPiece());
    }
    
    @Test
    public void testSetGetNomPiece() {
        Piece piece = new Piece("P001", "Brique 2x4");
        piece.setNomPiece("Brique 1x2");
        assertEquals("Brique 1x2", piece.getNomPiece());
    }
    
    @Test
    public void testSetGetCategorie() {
        Piece piece = new Piece("P001", "Brique 2x4");
        Categorie cat = new Categorie(1, "Briques");
        piece.setCategorie(cat);
        assertEquals(cat, piece.getCategorie());
    }
    
}
