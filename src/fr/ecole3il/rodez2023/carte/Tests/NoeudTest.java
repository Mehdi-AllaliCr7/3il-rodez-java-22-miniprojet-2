package fr.ecole3il.rodez2023.carte.Tests;


import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.Tests.NoeudTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NoeudTest {
    private Noeud<Integer> noeud1;
    private Noeud<Integer> noeud2;

    @BeforeEach
    void setUp() {
        noeud1 = new Noeud<>(1);
        noeud2 = new Noeud<>(2);
    }

    @Test
    void ajouterVoisinAddsNeighborToNode() {
        noeud1.ajoutNoeud(noeud2);

        assertTrue(noeud1.getVoisins().contains(noeud2));
    }

    @Test
    void ajouterVoisinAddsNodeAsNeighborToNeighbor() {
        noeud1.ajoutNoeud(noeud2);

        assertTrue(noeud2.getVoisins().contains(noeud1));
    }

    @Test
    void ajouterVoisinDoesNotAddDuplicateNeighbors() {
        noeud1.ajoutNoeud(noeud2);
        noeud1.ajoutNoeud(noeud2);

        assertEquals(1, noeud1.getVoisins().size());
    }

    @Test
    void ajouterVoisinDoesNotAddNodeAsNeighborToItself() {
        noeud1.ajoutNoeud(noeud1);

        assertFalse(noeud1.getVoisins().contains(noeud1));
    }
}
