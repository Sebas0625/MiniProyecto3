package project.miniproject3.model;

public class GameMatrix {
private int[][] matrix;

 public GameMatrix() {
     matrix = new int[10][10];
     for (int i = 0; i < matrix.length; i++) {
         for (int j = 0; j < matrix[i].length; j++) {
             matrix[i][j] = 0;
         }
     }


 }
 public void setNumber(int row, int col, int number) {
    matrix[row][col] = number;
 }
 public int getNumber(int row, int col) {return matrix[row][col];}


    public void printMatrix() {for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[i].length; j++) {
            System.out.print(matrix[i][j]+" " );
        }
        System.out.println();

    }

 }
}

