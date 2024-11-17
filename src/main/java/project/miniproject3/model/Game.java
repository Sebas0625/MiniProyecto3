package project.miniproject3.model;

import project.miniproject3.model.boats.Carrier;
import project.miniproject3.model.boats.Destroyers;
import project.miniproject3.model.boats.Submarine;

import java.util.Random;

public class Game {
    private final Random rand;
    private final GameMatrix playerMatrix;
    private final GameMatrix machineMatrix;

    public Game(){
        rand = new Random();
        machineMatrix = new GameMatrix();
        playerMatrix = new GameMatrix();
        fillMachineMatrix();
    }

    public void fillMachineMatrix() {
        // Carrier
        placeShip(4, 4);

        // Submarines
        for (int i = 0; i < 2; i++) {
            placeShip(3, 3);
        }

        // Destroyers
        for (int i = 0; i < 3; i++) {
            placeShip(2, 2);
        }

        // Frigates
        for (int i = 0; i < 4; i++) {
            placeShip(1, 1);
        }

        machineMatrix.printMatrix();
    }


    private void placeShip(int shipSize, int shipType) {
        boolean validPlacement;
        int aux1, aux2, directionaux;

        do {
            validPlacement = true;
            aux1 = rand.nextInt(10 - (shipSize - 1));
            aux2 = rand.nextInt(10 - (shipSize - 1));
            directionaux = rand.nextInt(2);

            for (int i = 0; i < shipSize; i++) {
                int checkCol = directionaux == 0 ? aux1 + i : aux1;
                int checkRow = directionaux == 0 ? aux2 : aux2 + i;

                if (checkCol >= 10 || checkRow >= 10 || machineMatrix.getNumber(checkCol, checkRow) != 0) {
                    validPlacement = false;
                    break;
                }
            }
        } while (!validPlacement);

        for (int i = 0; i < shipSize; i++) {
            int col = directionaux == 0 ? aux1 + i : aux1;
            int row = directionaux == 0 ? aux2 : aux2 + i;
            machineMatrix.setNumber(col, row, shipType);
        }
    }

    public int getPlayerMatrixNum(int row, int col){return playerMatrix.getNumber(row, col);}
    public int getMachineMatrixNum(int row, int col){return machineMatrix.getNumber(row, col);}

    public GameMatrix getPlayerMatrix(){
        return playerMatrix;
    }

    public GameMatrix getMachineMatrix(){
        return machineMatrix;
    }
}