package project.miniproject3.model;

import java.io.Serializable;
import java.util.Arrays;

public class GameMatrix implements Serializable {
    private final int[][] matrix;

     public GameMatrix() {
         matrix = new int[10][10];
         for (int[] ints : matrix) {
             Arrays.fill(ints, 0);
         }
     }

     public void setNumber(int row, int col, int number) {
        matrix[row][col] = number;
     }

     public int getNumber(int row, int col) {return matrix[row][col];}

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

