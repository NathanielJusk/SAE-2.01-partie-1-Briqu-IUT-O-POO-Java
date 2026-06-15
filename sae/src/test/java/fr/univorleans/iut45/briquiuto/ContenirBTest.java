package fr.univorleans.iut45.briquiuto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.univorleans.iut45.briquiuto.modele.Boite;
import fr.univorleans.iut45.briquiuto.modele.BoiteComposee;
import fr.univorleans.iut45.briquiuto.modele.ContenirB;

import static org.junit.jupiter.api.Assertions.*;

public class ContenirBTest {

    private Boite boite;
    private ContenirB contenirB;

    @BeforeEach
    void setUp() {
        boite = new BoiteComposee("B001", 500, "Boîte Test", 2022);
        contenirB = new ContenirB(boite, 3);
    }

    // ── getBoite / setBoite ────────────────────────────────────────────────

    @Test
    void testGetBoite_retourneBoiteInitiale() {
        assertEquals(boite, contenirB.getBoite());
    }

    @Test
    void testSetBoite_modifieBoite() {
        Boite nouvelleBoite = new BoiteComposee("B002", 200, "Autre Boîte", 2023);
        contenirB.setBoite(nouvelleBoite);
        assertEquals(nouvelleBoite, contenirB.getBoite());
    }

    @Test
    void testSetBoite_null_accepteNull() {
        contenirB.setBoite(null);
        assertNull(contenirB.getBoite());
    }

    // ── getQuantiteB / setQuantiteB ────────────────────────────────────────

    @Test
    void testGetQuantiteB_retourneQuantiteInitiale() {
        assertEquals(3, contenirB.getQuantiteB());
    }

    @Test
    void testSetQuantiteB_modifieQuantite() {
        contenirB.setQuantiteB(10);
        assertEquals(10, contenirB.getQuantiteB());
    }

    @Test
    void testSetQuantiteB_zero() {
        contenirB.setQuantiteB(0);
        assertEquals(0, contenirB.getQuantiteB());
    }
}