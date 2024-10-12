package com.example.Caliban.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MutantDetectorTest {

    @Test
    void testValidMutantDNA() {
        String[] validMutantDNA = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        MutantDetector detector = new MutantDetector(validMutantDNA);
        assertTrue(detector.isMutant(), "El ADN debería ser detectado commo mutante");
    }

    @Test
    void testValidHumanDNA() {
        String[] validHumanDNA = {
                "AAAT",
                "AACC",
                "AAAC",
                "CGGG"
        };
        MutantDetector detector = new MutantDetector(validHumanDNA);
        assertFalse(detector.isMutant(), "El ADN debería ser detectado commo humano");
    }

    @Test
    void testEmptyArray() {
        String[] emptyDNA = {};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new MutantDetector(emptyDNA);
        });
        assertEquals("Error: DNA array esta vacio", exception.getMessage());
    }

    @Test
    void testNonSquareMatrix() {
        String[] nonSquareDNA = {
                "ATGCG",
                "CAGTG",
                "TTATG",
                "AGAAG",
                "CCCCTA"
        };
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new MutantDetector(nonSquareDNA);
        });
        assertEquals("Error: Matriz no es cuadrada", exception.getMessage());
    }

    @Test
    void testNullDNA() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            String[] emptyDNA = null;
            new MutantDetector(emptyDNA);
        });
        assertEquals("Error: DNA array es null", exception.getMessage());
    }

    @Test
    void testNullElementsInDNA() {
        String nullString = null;
        String[] nullElementsDNA = {
                nullString,
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new MutantDetector(nullElementsDNA);
        });
        assertEquals("Error: No contiene nucleótidos", exception.getMessage());
    }

    @Test
    void testNonNucleotideCharacters() {
        String[] invalidDNA = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGX",
                "CCCCTA",
                "TCACTG"
        };
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new MutantDetector(invalidDNA);
        });
        assertEquals("Error: No contiene nucleótidos", exception.getMessage());
    }

    @Test
    void testNumericCharacters() {
        String[] numericDNA = {
                "123456",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new MutantDetector(numericDNA);
        });
        assertEquals("Error: No contiene nucleótidos", exception.getMessage());
    }
}