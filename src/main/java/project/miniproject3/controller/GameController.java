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

    Random rand;

    @FXML
    GridPane playerBoard;
    @FXML
    GridPane machineBoard;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        machineBoard.setOnMouseClicked(event -> {
            if (game.getPlayerPoints()!=20 && game.getMachinePoints()!=20) {

                double x = event.getX();
                double y = event.getY();
                rand= new Random();

                int col = (int) (x / (machineBoard.getWidth() / machineBoard.getColumnCount()));
                int row = (int) (y / (machineBoard.getHeight() / machineBoard.getRowCount()));
                //Le pega
                if (game.getMachineMatrix().getNumber(row, col) != 0 & game.getMachineMatrix().getNumber(row, col) != 5 & game.getMachineMatrix().getNumber(row, col) != 6) {
                    game.getMachineMatrix().setNumber(row, col, 6);
                    game.setPlayerPoints(game.getPlayerPoints() + 1);
                    machineBoard.add(Ships.createFire(), col, row);
                    if (isGameFinished()){
                        finishGame();
                    }
                    machineTurn();

                    //Falla
                } else if (game.getMachineMatrix().getNumber(row, col) != 6) {
                    game.getMachineMatrix().setNumber(row, col, 5);
                    machineBoard.add(Ships.drawX(), col, row);
                    if (isGameFinished()){
                        finishGame();
                    }
                    machineTurn();
                }
                game.getMachineMatrix().printMatrix();

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

    public void machineTurn(){

        int rand1=0;
        int rand2=0;

        do{
            rand1=rand.nextInt(10);
            rand2=rand.nextInt(10);
            System.out.println(rand1);
            System.out.println(rand2);
        }while (game.getPlayerMatrix().getNumber(rand1, rand2) ==5 || game.getPlayerMatrix().getNumber(rand1, rand2) ==6);

        if(game.getPlayerMatrix().getNumber(rand1, rand2) ==0){
            game.getPlayerMatrix().setNumber(rand1,rand2,5);
        }
        else {game.getPlayerMatrix().setNumber(rand1,rand2,6);
            game.setPlayerPoints(game.getPlayerPoints() + 1);
        }
        game.getPlayerMatrix().printMatrix();


        if (isGameFinished()){finishGame();}



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
        if (game.getMachinePoints()==20){return true;}
        else if(game.getPlayerPoints()==20){return true;}
        return false;

    }

    public void finishGame(){

    }
}
