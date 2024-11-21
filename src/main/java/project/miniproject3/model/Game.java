package project.miniproject3.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Representa el juego de Batalla Naval. La clase maneja las matrices de posiciones tanto del jugador
 * como de la máquina, coloca los barcos de manera aleatoria en la matriz de la máquina y proporciona
 * métodos para interactuar con el estado del juego.
 */
public class Game implements Serializable {
    private final Random rand;
    private final GameMatrix machineMatrix;
    private final GameMatrix playerMatrix;
    private final ArrayList<ArrayList<Integer>> playerPositions;
    private final ArrayList<ArrayList<Integer>> machinePositions;
    private int machinePoints;
    private int playerPoints;

    /**
     * Constructor de la clase Game. Inicializa las matrices del jugador y la máquina,
     * y llena la matriz de la máquina con barcos aleatorios.
     */
    public Game(){
        machinePoints = 0;
        playerPoints = 0;
        rand = new Random();
        machineMatrix = new GameMatrix();
        playerMatrix = new GameMatrix();
        playerPositions = new ArrayList<>();
        machinePositions = new ArrayList<>();
        fillMachineMatrix();
    }

    /**
     * Llena la matriz de la máquina con barcos de diferentes tamaños en posiciones aleatorias.
     */
    public void fillMachineMatrix() {
        int index = 0;

        // Carrier
        placeRandomShip(4, 4, index);
        index++;

        // Submarines
        for (int i = 0; i < 2; i++) {
            placeRandomShip(3, 3, index);
            index++;
        }

        // Destroyers
        for (int i = 0; i < 3; i++) {
            placeRandomShip(2, 2, index);
            index++;
        }

        // Frigates
        for (int i = 0; i < 4; i++) {
            placeRandomShip(1, 1, index);
            index++;
        }
    }

    /**
     * Coloca un barco de tamaño específico en una posición aleatoria en la matriz de la máquina.
     *
     * @param shipSize tamaño del barco
     * @param shipType tipo de barco (usado para identificarlo en la matriz)
     * @param index índice de la lista de posiciones de los barcos de la máquina
     */
    private void placeRandomShip(int shipSize, int shipType, int index) {
        machinePositions.add(new ArrayList<>());

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

                if (checkCol >= 10 || checkRow >= 10 || machineMatrix.getNumber(checkRow, checkCol) != 0) {
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
            machineMatrix.setNumber(row, col, shipType);
        }
    }

    /**
     * Coloca un barco en la matriz del jugador en una posición y con una orientación dadas.
     *
     * @param row     fila inicial del barco
     * @param col     columna inicial del barco
     * @param rowSpan cantidad de filas que ocupa el barco
     * @param colSpan cantidad de columnas que ocupa el barco
     * @param type    tipo de barco
     */
    public void placeShip(int row, int col, int rowSpan, int colSpan, int type){
        for (int i = row; i < row + rowSpan; i++){
            for (int j = col; j < col + colSpan; j++){
                playerMatrix.setNumber(i, j, type);
            }
        }
    }

    /**
     * Obtiene las posiciones de los barcos del jugador.
     *
     * @return lista de posiciones de los barcos del jugador
     */
    public ArrayList<ArrayList<Integer>> getPlayerPositions(){
        return playerPositions;
    }

    /**
     * Obtiene las posiciones de los barcos de la máquina.
     *
     * @return lista de posiciones de los barcos de la máquina
     */
    public ArrayList<ArrayList<Integer>> getMachinePositions(){
        return machinePositions;
    }

    /**
     * Obtiene la matriz de la máquina.
     *
     * @return la matriz de la máquina
     */
    public GameMatrix getMachineMatrix(){
        return machineMatrix;
    }

    /**
     * Obtiene la matriz del jugador.
     *
     * @return la matriz del jugador
     */
    public GameMatrix getPlayerMatrix(){
        return playerMatrix;
    }

    /**
     * Obtiene los puntos actuales de la máquina.
     *
     * @return puntos de la máquina
     */
    public int getMachinePoints(){
        return machinePoints;
    }

    /**
     * Obtiene los puntos actuales del jugador.
     *
     * @return puntos del jugador
     */
    public int getPlayerPoints(){
        return playerPoints;
    }

    /**
     * Establece los puntos de la máquina.
     *
     * @param points puntos a asignar a la máquina
     */
    public void setMachinePoints(int points){
        machinePoints = points;
    }

    /**
     * Establece los puntos del jugador.
     *
     * @param points puntos a asignar al jugador
     */
    public void setPlayerPoints(int points){
        playerPoints = points;
    }
}