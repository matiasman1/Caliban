package com.example.Caliban.model;

import com.example.Caliban.util.MatrizPrint;

public class MutantDetector {
    private static final int SEQUENCE_LENGTH = 4;
    private String[] dna;
    private char[][] dnaMatrix;
    private int N;
    private int countstep;
    private MatrizPrint mp;
    private int[][] geneHighlight; // Array para guardar las coordenadas de secuencia de genes

    // Constructor inicializa dna, dnaMatrix y N
    public MutantDetector(String[] dna) {

        if (dna == null) {
            throw new IllegalArgumentException("Error: DNA array es null");
        }

        this.dna = dna;
        this.N = dna.length;

        if (N == 0) {
            throw new IllegalArgumentException("Error: DNA array esta vacio");
        }

        for (String row : dna) {
            if (row == null) {
                throw new IllegalArgumentException("Error: No contiene nucleótidos");
            }
            if (row.length() != N) {
                throw new IllegalArgumentException("Error: Matriz no es cuadrada");
            }
        }

        // Convertir Strings adn a matriz de caracteres
        this.dnaMatrix = new char[N][N];
        for (int i = 0; i < N; i++) {
            if (dna[i] == null) {
                throw new IllegalArgumentException("Error: No contiene nucleótidos");
            }
            dnaMatrix[i] = dna[i].toCharArray();

            for (char c : dnaMatrix[i]) {
                if (c != 'A' && c != 'T' && c != 'C' && c != 'G') {
                    throw new IllegalArgumentException("Error: No contiene nucleótidos");
                }
            }
        }

        // Inicializar MatrizPrint con la matriz
        this.mp = new MatrizPrint(dnaMatrix);

        System.out.println("Matriz sin resaltado:");
        mp.print();

        // Initializar el array geneHighlight (4 coordenadas max)
        this.geneHighlight = new int[SEQUENCE_LENGTH][2];
    }

    public MutantDetector(char[][] dnaMatrix) {
        this.dnaMatrix = dnaMatrix;
        this.N = dnaMatrix.length;

        // Initializar MatrizPrint con la matriz
        this.mp = new MatrizPrint(dnaMatrix);

        System.out.println("Matriz sin resaltado:");
        mp.print();

        // Initializar el array geneHighlight (4 coordenadas max)
        this.geneHighlight = new int[SEQUENCE_LENGTH][2];
    }

    //Metodo que mide cuantos espacios quedan en una dirección desde una posición
    public int spaceLeft(int[] posicion, int[] direccion) {
        int[] spaceLeft = {0, 0};
        //Itero dimension i en entre 0 e 1 (X e Y)
        for (int i = 0; i < 2; i++) {
            //Si la direccion en la dimension i es distinta de 0
            if (direccion[i] != 0) {
                //el espacio que queda por delante es Tamaño menos 1 menos posicion en dimension i
                spaceLeft[i] = N - posicion[i] - 1;
            }
            //Si la dirección en la dimension i es menor a 0 (diagonal izquierda)
            if (direccion[i] < 0) {
                //el espacio que queda por delante es el indice de la posición en dimension i
                spaceLeft[i] = posicion[i];
            }
            //Si la direccion en la dimension i es 0
            if (direccion[i]==0){
                //No me estoy moviendo en dimension i, entonces no me importa esta dimension
                //entonces hago que el espacio que queda sea el mayor posible mas 1 (osea N), para la comparación final
                spaceLeft[i]=N;
            }
        }
        //Devuelvo el menor del espacio restante en ambas dimensiones
        return Math.min(spaceLeft[0], spaceLeft[1]);
    }

    public int recorrer(int[] inicio, int[] direccion) {
        char pattern = '\0';  // Inicializar con un caracter nulo para el primer If
        int c = 0;
        int found = 0;
        int[] coordinate = {inicio[0], inicio[1]};  // Copia de inicio

        // Recorrer mientras hayan espacios posibles para encontar una secuencia
        while (spaceLeft(coordinate, direccion) + c + 1 >= SEQUENCE_LENGTH) {
            char currentChar = dnaMatrix[coordinate[0]][coordinate[1]];
            countstep++;
            // Chequear si el actual caracter iguala el patrón
            if (currentChar == pattern) {
                geneHighlight[c] = new int[]{coordinate[0], coordinate[1]};  // Guardo la posicion para Highlight
                c++;  // Incremento el contador de patron
            } else {
                pattern = currentChar;  // Actualizo el patron al nuevo caracter
                geneHighlight = new int[SEQUENCE_LENGTH][2]; // Reseteo coordenadas para Highlight
                geneHighlight[0] = new int[]{coordinate[0], coordinate[1]};  // Guardo el nuevo inicio para Highlight
                c = 1;  // Reseteo el contador de patron
            }

            // Si una secuencia de SEQUENCE_LENGTH se encuentra
            if (c == SEQUENCE_LENGTH) {
                found++;  // Incremento secuencias encontradsa
                c = 0;    // Reseteo el contador de patron

                // Imprimo la secuencia encontrada con Highlight
                mp.highlight(geneHighlight);
            }

            // Incremento la coordenada con las dimensiones de la dirección
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
