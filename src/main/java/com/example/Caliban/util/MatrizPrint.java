package com.example.Caliban.util;

import java.util.HashSet;
import java.util.Set;

public class MatrizPrint {

    private char[][] matriz;
    private int N; // Variable to hold the matrix size

    // Constructor that receives the character matrix
    public MatrizPrint(char[][] matriz) {
        this.matriz = matriz;
        this.N = matriz.length; // Initialize N as the size of the matrix
    }

    // Method to print the matrix without highlighting
    public void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        // Print the underscore line after printing the matrix
        printUnderscores();
    }

    // Method to print the matrix highlighting the cells given by the tuples (coordinates)
    public void highlight(int[][] coords) {
        // Convert the array of coordinates into a Set for easy access
        Set<String> highlightCoords = new HashSet<>();
        for (int[] coord : coords) {
            highlightCoords.add(coord[0] + "," + coord[1]);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                // If the cell is in the coordinates to highlight, show it without spaces using []
                if (highlightCoords.contains(i + "," + j)) {
                    System.out.print("[" + matriz[i][j] + "]");
                } else {
                    System.out.print(matriz[i][j] + " ");
                }
            }
            System.out.println();
        }
        // Print the underscore line after printing the matrix
        printUnderscores();
    }

    // Method to print a row of underscores (N*2)-1 underscores
    private void printUnderscores() {
        for (int i = 0; i < (N * 2) - 1; i++) {
            System.out.print("_");
        }
        System.out.println();
    }
}