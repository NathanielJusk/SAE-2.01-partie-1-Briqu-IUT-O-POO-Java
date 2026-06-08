package fr.univorleans.iut45.briquiuto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;



 class BoitePersonnaliseeTest {

    private BoitePersonnalisee boiteSimple;
    private BoitePersonnalisee boiteAvecContenu;
    private Contenu contenu;
    private Piece piece1;
    private Piece piece2;
    private Categorie categorie;

    @BeforeEach
    void setUp() {
        // Boîte simple sans contenu
        boiteSimple = new BoitePersonnalisee("BP-001", 10, "Château Fort", 2022);

        // Objets nécessaires
        categorie = new Categorie(1, "Structure");
        piece1 = new Piece("P-001", "Brique Rouge", categorie);
        piece2 = new Piece("P-002", "Fenêtre", categorie);

        // Contenu avec 2 pièces
        contenu = new Contenu(1, 1);
        contenu.getContenirPieces().add(new ContenirP(null, piece1, 5, false));
        contenu.getContenirPieces().add(new ContenirP(null, piece2, 3, true));

        // Boîte avec contenu
        boiteAvecContenu = new BoitePersonnalisee("BP-002", 8, "Tour Médiévale", 2023);
        boiteAvecContenu.setContenu(contenu);
    }

    // ── Constructeur simple ────────────────────────────────────────────────

    @Test
    void testConstructeurSimple_attributsCorrects() {
        assertEquals("BP-001", boiteSimple.getNumero());
        assertEquals("Château Fort", boiteSimple.getNom());
        assertEquals(2022, boiteSimple.getAnnee());
        assertEquals(10, boiteSimple.getNbPiece());
    }

    @Test
    void testConstructeurSimple_contenuNullParDefaut() {
        assertNull(boiteSimple.getContenu());
    }

    @Test
    void testConstructeurSimple_nonPossedeeParDefaut() {
        assertFalse(boiteSimple.isPossedee());
    }

    // ── Constructeur avec thème ────────────────────────────────────────────

    @Test
    void testConstructeurAvecTheme_themeCorrect() {
        Theme theme = new Theme(1, "Médiéval");
        List<Boite> sousBoites = new ArrayList<>();
        BoitePersonnalisee bp = new BoitePersonnalisee("BP-003", "Donjon", 2024, theme, sousBoites);

        assertEquals("Donjon", bp.getNom());
        assertEquals("BP-003", bp.getNumero());
        assertEquals(theme, bp.getTheme());
        assertEquals(0, bp.getNbPiece());
    }

    // ── creeIdentifiantUnique ──────────────────────────────────────────────

    @Test
    void testCreeIdentifiantUnique_formatCorrect() {
        String id = boiteSimple.creeIdentifiantUnique();
        assertTrue(id.startsWith("PERSO-"),
                "L'identifiant doit commencer par 'PERSO-'");
    }

    @Test
    void testCreeIdentifiantUnique_deuxAppelsRetournentValeursDistinctes() {
        String id1 = boiteSimple.creeIdentifiantUnique();
        String id2 = boiteSimple.creeIdentifiantUnique();
        assertNotEquals(id1, id2,
                "Deux identifiants générés doivent être différents");
    }

    @Test
    void testCreeIdentifiantUnique_longueurCorrecte() {
        String id = boiteSimple.creeIdentifiantUnique();
        // "PERSO-" (6) + 8 caractères UUID = 14
        assertEquals(14, id.length());
    }

    // ── calculerTotalPieces ────────────────────────────────────────────────

    @Test
    void testCalculerTotalPieces_sansContenu_retourneZero() {
        assertEquals(0, boiteSimple.calculerTotalPieces());
    }

    @Test
    void testCalculerTotalPieces_avecContenu_retourneSomme() {
        // piece1 : 5, piece2 : 3 → total = 8
        assertEquals(8, boiteAvecContenu.calculerTotalPieces());
    }

    @Test
    void testCalculerTotalPieces_contenuVide_retourneZero() {
        BoitePersonnalisee bp = new BoitePersonnalisee("BP-004", 0, "Vide", 2020);
        bp.setContenu(new Contenu(2, 1)); // contenu sans pièces
        assertEquals(0, bp.calculerTotalPieces());
    }

    // ── afficherStatistiques ───────────────────────────────────────────────

    // @Test
    // void testAfficherStatistiques_nepasLeverException() {
    //     // Vérifie que la méthode ne plante pas
    //     assertDoesNotThrow(() -> boiteAvecContenu.afficherStatistiques());
    // }

    // @Test
    // void testAfficherStatistiques_sansContenu_nepasLeverException() {
    //     assertDoesNotThrow(() -> boiteSimple.afficherStatistiques());
    // }
}