package com.example.model;

import com.example.util.MatrizPrint;

public class MutantDetector {
    private static final int SEQUENCE_LENGTH = 4;
    private String[] dna;
    private char[][] dnaMatrix;
    private int N;
    private int countstep;
    private MatrizPrint mp;
    private int[][] geneHighlight; // Array to store the gene sequence coordinates

    // Constructor initializes the dna, dnaMatrix, and N
    public MutantDetector(String[] dna) {
        this.dna = dna;
        this.N = dna.length;

        // Convert dna Strings to a character matrix
        this.dnaMatrix = new char[N][N];
        for (int i = 0; i < N; i++) {
            dnaMatrix[i] = dna[i].toCharArray();
        }

        // Initialize MatrizPrint with the matrix
        this.mp = new MatrizPrint(dnaMatrix);

        System.out.println("Matriz sin resaltado:");
        mp.print();

        // Initialize the geneHighlight array (4 coordinates max at a time)
        this.geneHighlight = new int[SEQUENCE_LENGTH][2];
    }

    public MutantDetector(char[][] dnaMatrix) {
        this.dnaMatrix = dnaMatrix;
        this.N = dnaMatrix.length;

        // Initialize MatrizPrint with the matrix
        this.mp = new MatrizPrint(dnaMatrix);

        System.out.println("Matriz sin resaltado:");
        mp.print();

        // Initialize the geneHighlight array (4 coordinates max at a time)
        this.geneHighlight = new int[SEQUENCE_LENGTH][2];
    }

    public int spaceLeft(int[] posicion, int[] direccion) {
        int[] spaceLeft = {0, 0};
        for (int i = 0; i < 2; i++) {
            if (direccion[i] != 0) {
                spaceLeft[i] = N - posicion[i] - 1;
            }
            if (direccion[i] < 0) {
                spaceLeft[i] = posicion[i];
            }
            if (direccion[i]==0){
                spaceLeft[i]=N;
            }
        }
        return Math.min(spaceLeft[0], spaceLeft[1]);
    }

    public int recorrer(int[] inicio, int[] direccion) {
        char pattern = '\0';  // Initialize with a null character
        int c = 0;
        int found = 0;
        int[] coordinate = {inicio[0], inicio[1]};  // Copy of the starting coordinate

        // Traverse while the coordinates are within bounds
        while (spaceLeft(coordinate, direccion) + c + 1 >= SEQUENCE_LENGTH) {
            char currentChar = dnaMatrix[coordinate[0]][coordinate[1]];
            countstep++;
            // Check if the current character matches the pattern
            if (currentChar == pattern) {
                geneHighlight[c] = new int[]{coordinate[0], coordinate[1]};  // Save the position
                c++;  // Increment the match count
            } else {
                pattern = currentChar;  // Update pattern to the current character
                geneHighlight = new int[SEQUENCE_LENGTH][2]; // Reset highlight coordinates
                geneHighlight[0] = new int[]{coordinate[0], coordinate[1]};  // Save the new start
                c = 1;  // Reset the match count
            }

            // If a sequence of SEQUENCE_LENGTH is found
            if (c == SEQUENCE_LENGTH) {
                found++;  // Increment found sequences
                c = 0;    // Reset count for new sequences

                // Highlight the sequence
                mp.highlight(geneHighlight);
            }

            // Update the coordinates based on the direction
            coordinate[0] += direccion[0];
            coordinate[1] += direccion[1];
        }

        return found;  // Devolver el numero de secuencias encontradas
    }

    public boolean isMutant() {
        int countSequences = 0;

        // Recorremos toda la matriz
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int[] inicio = {i, j};
                // Verificamos si es posible encontrar secuencias horizontales, verticales o diagonales
                if (i == 0) {
                    int[] direccion = {1, 0};
                    countSequences += recorrer(inicio, direccion);
                }
                if (j == 0) {
                    int[] direccion = {0, 1};
                    countSequences += recorrer(inicio, direccion);
                }
                if (N - SEQUENCE_LENGTH >= i + j) {
                    int[] direccion = {1, 1};
                    countSequences += recorrer(inicio, direccion);
                }
                if (N - SEQUENCE_LENGTH >= i - j + N- 1) {
                    int[] direccion = {1, -1};
                    countSequences += recorrer(inicio, direccion);
                }
                if (countSequences >= 2) {
                    System.out.println("countstep=" + countstep);
                    return true;  // Más de una secuencia encontrada
                }
            }
        }
        System.out.println("countstep=" + countstep);
        return false;
    }
}
