package project.miniproject3.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Game implements Serializable {
    private final Random rand;
    private final GameMatrix machineMatrix;
    private final GameMatrix playerMatrix;
    private final ArrayList<ArrayList<Integer>> playerPositions;
    private final ArrayList<ArrayList<Integer>> machinePositions;
    private int machinePoints;
    private int PlayerPoints;

    public Game(){
        machinePoints = 0;
        PlayerPoints = 0;
        rand = new Random();
        machineMatrix = new GameMatrix();
        playerMatrix = new GameMatrix();
        playerPositions = new ArrayList<>();
        machinePositions = new ArrayList<>();
        fillMachineMatrix();
    }

    public void fillMachineMatrix() {
        int index = 0;

        // Carrier
        placeRandomShip(4, 4, index);

        // Submarines
        for (int i = 0; i < 2; i++) {
            placeRandomShip(3, 3, index);
        }

        // Destroyers
        for (int i = 0; i < 3; i++) {
            placeRandomShip(2, 2, index);
        }

        // Frigates
        for (int i = 0; i < 4; i++) {
            placeRandomShip(1, 1, index);
        }
    }


    private void placeRandomShip(int shipSize, int shipType, int index) {
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

        int rowSpan = directionaux == 0 ? 1 : shipSize;
        int colSpan = directionaux == 0 ? shipSize : 1;

        machinePositions.get(index).add(aux2);
        machinePositions.get(index).add(aux1);
        machinePositions.get(index).add(rowSpan);
        machinePositions.get(index).add(colSpan);
        machinePositions.get(index).add(shipSize);

        for (int i = 0; i < shipSize; i++) {
            int col = directionaux == 0 ? aux1 + i : aux1;
            int row = directionaux == 0 ? aux2 : aux2 + i;
            machineMatrix.setNumber(col, row, shipType);
        }
    }

    public void placeShip(int row, int col, int rowSpan, int colSpan, int type){
        for (int i = row; i < row + rowSpan; i++){
            for (int j = col; j < col + colSpan; j++){
                playerMatrix.setNumber(i, j, type);
            }
        }
    }

    public ArrayList<ArrayList<Integer>> getPlayerPositions(){
        return playerPositions;
    }

    public ArrayList<ArrayList<Integer>> getMachinePositions(){
        return machinePositions;
    }

    public GameMatrix getMachineMatrix(){
        return machineMatrix;
    }

    public GameMatrix getPlayerMatrix(){ return playerMatrix; }

    public int getMachinePoints(){return machinePoints;}

    public int getPlayerPoints(){return PlayerPoints;}

    public void setMachinePoints(int points){machinePoints = points;}

    public void setPlayerPoints(int points){PlayerPoints = points;}
}