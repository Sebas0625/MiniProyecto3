package project.miniproject3.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Polygon;
import project.miniproject3.model.Game;
import project.miniproject3.model.GameMatrix;
import project.miniproject3.model.Ships;
import project.miniproject3.view.GameStage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class GameController implements Initializable {
    Game game;
    @FXML
    GridPane playerBoard;
    @FXML
    GridPane machineBoard;
    private int impactsCounter = 0;
    private final ArrayList<ArrayList<Integer[]>> MPosArray = new ArrayList<>();
    private final ArrayList<ArrayList<Integer[]>> PPosArray = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        machineBoard.setOnMouseClicked(event -> {
            double x = event.getX();
            double y = event.getY();

            int col = (int) (x / (machineBoard.getWidth() / machineBoard.getColumnCount()));
            int row = (int) (y / (machineBoard.getHeight() / machineBoard.getRowCount()));

            if (game.getPlayerMatrix().getNumber(row, col) != 0){
                game.getPlayerMatrix().setNumber(row, col, 6);
                impactsCounter++;
                if (isGameFinished()){
                    finishGame();
                }
                // evento de impacto contra barco
            } else{
                game.getPlayerMatrix().setNumber(row, col, 5);
                // evento de impacto contra el agua
            }
        });
    }

    @FXML
    void handleExitButton(ActionEvent event) throws IOException {
        GameStage.deleteInstance();
    }
    void savePositions(ArrayList<ArrayList<Integer[]>> PosArray, GameMatrix matrix) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int type = game.getMatrixNum(matrix, i, j);
                if (type != 0){
                    PosArray.get(4 - type).add(new Integer[]{i, j});
                }
            }
        }
    }

    void printPositions(ArrayList<ArrayList<Integer[]>> PosArray){
        System.out.println("Imprimiendo posiciones");
        int aux = 0;
        for (int i = 0; i < 4; i++) {
            aux = PosArray.get(i).size();
            for (int j = 0; j < aux; j++) {
                System.out.print(Arrays.toString(PosArray.get(i).get(j)));
            }
            System.out.println();
        }
    }

    public void setGame(Game game){
        this.game = game;

        for (int i = 0; i < 4; i++){
            PPosArray.add(new ArrayList<>());
            MPosArray.add(new ArrayList<>());
        }

        savePositions(MPosArray, game.getMachineMatrix());
        printPositions(MPosArray);
        savePositions(PPosArray, game.getPlayerMatrix());
        printPositions(PPosArray);

        showPlayerShips();
    }

    public void showPlayerShips(){
        Integer[] position;
        Integer row, col;
        for(int i = 0; i < 4 ; i++){
            int shipSpan = 4 - i;
            int typeShipCount = i + 1;
            for (int j = 0; j < typeShipCount; j++){
                int k = j * shipSpan;
                Group shape = new Group();
                switch (shipSpan){
                    case 1: shape = Ships.frigate(); break;
                    case 2: shape = Ships.destroyer(); break;
                    case 3: shape = Ships.submarine(); break;
                    case 4: shape = Ships.carrier(); break;
                }
                if (!Objects.equals(PPosArray.get(i).get(k)[0], PPosArray.get(i).get(k + 1)[0])){
                    GridPane.setRowSpan(shape, shipSpan);
                } else{
                    GridPane.setColumnSpan(shape, shipSpan);
                }
                position = PPosArray.get(i).get(k);
                row = position[0];
                col = position[1];
                playerBoard.add(shape, row, col);
            }
        }
    }

    public boolean isGameFinished(){
        return impactsCounter == 20;
    }

    public void finishGame(){

    }
}
