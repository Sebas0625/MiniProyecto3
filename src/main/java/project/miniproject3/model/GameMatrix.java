package project.miniproject3.model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Representa una matriz de 10x10 utilizada para el juego de Batalla Naval.
 * La clase proporciona métodos para manipular los valores de la matriz, como establecer y obtener
 * los números en posiciones específicas, así como imprimir la matriz completa.
 */
public class GameMatrix implements Serializable {
    private final int[][] matrix;

    /**
     * Constructor de la clase GameMatrix. Inicializa una matriz 10x10 con todos los valores en 0.
     */
    public GameMatrix() {
        matrix = new int[10][10];
        for (int[] ints : matrix) {
            Arrays.fill(ints, 0);
        }
    }

    /**
     * Establece un número en una posición específica de la matriz.
     *
     * @param row    fila en la que se desea establecer el número
     * @param col    columna en la que se desea establecer el número
     * @param number el número a establecer en la matriz
     */
    public void setNumber(int row, int col, int number) {
        matrix[row][col] = number;
    }

    /**
     * Obtiene el número en una posición específica de la matriz.
     *
     * @param row fila en la que se encuentra el número
     * @param col columna en la que se encuentra el número
     * @return el número en la posición especificada
     */
    public int getNumber(int row, int col) {
        return matrix[row][col];
    }

    /**
     * Imprime la matriz en la consola para mostrar su estado actual.
     * Utilizado principalmente para fines de depuración.
     */
    public void printMatrix() {
        System.out.println("============================================");
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
}

