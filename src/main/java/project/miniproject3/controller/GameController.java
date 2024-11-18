package project.miniproject3.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import project.miniproject3.model.Game;
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

    public void setGame(Game game){
        this.game = game;
        showPlayerShips();
    }

    public void showPlayerShips(){
        int row, col, rowSpan, colSpan, type;
        Group shape = new Group();
        for (ArrayList<Integer> shipData : game.getPlayerPositions()){
            row = shipData.get(0);
            col = shipData.get(1);
            rowSpan = shipData.get(2);
            colSpan = shipData.get(3);
            type = shipData.get(4);
            shape = switch (type) {
                case 4 -> Ships.carrier();
                case 3 -> Ships.submarine();
                case 2 -> Ships.destroyer();
                case 1 -> Ships.frigate();
                default -> shape;
            };
            if (colSpan != 1) shape.setRotate(90);
            if (!(row == -1 || col == -1 || rowSpan == -1 || colSpan == -1)) playerBoard.add(shape, col, row, colSpan, rowSpan);
            game.placeShip(row, col, row, colSpan, type);
        }
    }

    public boolean isGameFinished(){
        return impactsCounter == 20;
    }

    public void finishGame(){

    }
}
