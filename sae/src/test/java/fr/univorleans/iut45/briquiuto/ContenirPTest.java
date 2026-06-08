package fr.univorleans.iut45.briquiuto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContenirPTest {

    private ContenirP contenirP;
    private Piece piece;
    private Categorie categorie;

    @BeforeEach
    void setUp() {
        categorie = new Categorie(1, "Structure");
        piece = new Piece("P-001", "Brique Rouge", categorie);
        contenirP = new ContenirP(null, piece, 5, false);
    }

    // ── getPiece / setPiece ────────────────────────────────────────────────

    @Test
    void testGetPiece_retournePieceInitiale() {
        assertEquals(piece, contenirP.getPiece());
    }

    @Test
    void testSetPiece_modifiePiece() {
        Piece nouvellePiece = new Piece("P-002", "Fenêtre", categorie);
        contenirP.setPiece(nouvellePiece);
        assertEquals(nouvellePiece, contenirP.getPiece());
    }

    @Test
    void testSetPiece_null() {
        contenirP.setPiece(null);
        assertNull(contenirP.getPiece());
    }

    // ── getQuantiteP / setQuantiteP ────────────────────────────────────────

    @Test
    void testGetQuantiteP_retourneQuantiteInitiale() {
        assertEquals(5, contenirP.getQuantiteP());
    }

    @Test
    void testSetQuantiteP_modifieQuantite() {
        contenirP.setQuantiteP(10);
        assertEquals(10, contenirP.getQuantiteP());
    }

    @Test
    void testSetQuantiteP_zero() {
        contenirP.setQuantiteP(0);
        assertEquals(0, contenirP.getQuantiteP());
    }

    // ── isEstSupplement / setEstSupplement ────────────────────────────────

    @Test
    void testIsEstSupplement_falseParDefaut() {
        assertFalse(contenirP.isEstSupplement());
    }

    @Test
    void testSetEstSupplement_true() {
        contenirP.setEstSupplement(true);
        assertTrue(contenirP.isEstSupplement());
    }

    // ── getCouleur / setCouleur ────────────────────────────────────────────

    @Test
    void testGetCouleur_nullParDefaut() {
        assertNull(contenirP.getCouleur());
    }

    @Test
    void testSetCouleur_modifieCouleur() {
        Couleur couleur = new Couleur(1, "Rouge", "#FF0000", false);
        contenirP.setCouleur(couleur);
        assertEquals(couleur, contenirP.getCouleur());
    }

    // ── Constructeur ──────────────────────────────────────────────────────

    @Test
    void testConstructeur_tousAttributsCorrects() {
        Couleur couleur = new Couleur(2, "Bleu", "#0000FF", false);
        ContenirP cp = new ContenirP(couleur, piece, 7, true);

        assertEquals(couleur, cp.getCouleur());
        assertEquals(piece, cp.getPiece());
        assertEquals(7, cp.getQuantiteP());
        assertTrue(cp.isEstSupplement());
    }
}