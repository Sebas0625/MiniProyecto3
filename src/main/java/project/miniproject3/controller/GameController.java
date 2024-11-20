package project.miniproject3.controller;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import project.miniproject3.model.FileHandling.PlainTextFileHandler;
import project.miniproject3.model.Game;
import project.miniproject3.model.FileHandling.SerializableFileHandler;
import project.miniproject3.model.ships.Ships;
import project.miniproject3.view.GameStage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class GameController implements Initializable {
    private Game game;
    private Random rand;
    private String nickname;
    private String character;
    @FXML
    private GridPane playerBoard;
    @FXML
    private GridPane machineBoard;
    @FXML
    private Label endLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PlainTextFileHandler plainTextFileHandler = new PlainTextFileHandler();
        String[] data = plainTextFileHandler.readFromFile("./src/main/resources/project/miniproject3/saves/player-data.csv");
        this.nickname = data[0];
        this.character = data[1];
        // HACER ALGO CON LA SERIALIZACIÓN
        System.out.println(nickname);
        System.out.println(character);

        machineBoard.setOnMouseClicked(event -> {
            if (game.getPlayerPoints()!=20 && game.getMachinePoints()!=20) {
                double x = event.getX();
                double y = event.getY();
                rand= new Random();

                int col = (int) (x / (machineBoard.getWidth() / machineBoard.getColumnCount()));
                int row = (int) (y / (machineBoard.getHeight() / machineBoard.getRowCount()));

                if (game.getMachineMatrix().getNumber(row, col) != 0 & game.getMachineMatrix().getNumber(row, col) != 5 & game.getMachineMatrix().getNumber(row, col) != 6) {
                    game.getMachineMatrix().setNumber(row, col, 6);
                    game.setPlayerPoints(game.getPlayerPoints() + 1);
                    machineBoard.add(Ships.createFire(), col, row);
                    if (isGameFinished()){
                        finishGame();
                    }
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
        SerializableFileHandler serializableFileHandler = new SerializableFileHandler();
        serializableFileHandler.serialize("./src/main/resources/project/miniproject3/saves/game-data.ser", game);
        GameStage.deleteInstance();
    }

    public void setGame(Game game){
        this.game = game;
        showPlayerShips();
    }

    public void machineTurn(){
        machineBoard.setDisable(true);
        PauseTransition pause = new PauseTransition(Duration.seconds(1));

        pause.setOnFinished(event -> {
            int rand1 = 0;
            int rand2 = 0;

            do {
                rand1 = rand.nextInt(10);
                rand2 = rand.nextInt(10);
                System.out.println(rand1);
                System.out.println(rand2);
            } while (game.getPlayerMatrix().getNumber(rand1, rand2) == 5 || game.getPlayerMatrix().getNumber(rand1, rand2) == 6);

            if (game.getPlayerMatrix().getNumber(rand1, rand2) == 0) {
                game.getPlayerMatrix().setNumber(rand1, rand2, 5);
                playerBoard.add(Ships.drawX(), rand2, rand1);
            } else {
                game.getPlayerMatrix().setNumber(rand1, rand2, 6);
                game.setMachinePoints(game.getMachinePoints() + 1);
                playerBoard.add(Ships.createFire(), rand2, rand1);
            }
            game.getPlayerMatrix().printMatrix();

            if (isGameFinished()) {
                finishGame();
            }
            machineBoard.setDisable(false);
        });
        pause.play();
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
            if (!(row == -1 || col == -1 || rowSpan == -1 || colSpan == -1)){
                playerBoard.add(shape, col, row, colSpan, rowSpan);
                game.placeShip(row, col, rowSpan, colSpan, type);
            }
        }
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                if (game.getPlayerMatrix().getNumber(i, j) == 5){
                    playerBoard.add(Ships.drawX(), j, i);
                } else if (game.getPlayerMatrix().getNumber(i, j) == 6){
                    playerBoard.add(Ships.createFire(), j, i);
                }
                if (game.getMachineMatrix().getNumber(i, j) == 5){
                    machineBoard.add(Ships.drawX(), j, i);
                } else if (game.getMachineMatrix().getNumber(i, j) == 6){
                    machineBoard.add(Ships.createFire(), j, i);
                }
            }
        }
        game.getPlayerMatrix().printMatrix();
    }

    public boolean isGameFinished(){
        if (game.getMachinePoints()==20){return true;}
        else return game.getPlayerPoints() == 20;

    }

    public void finishGame(){
        if(game.getPlayerPoints()==20){
            endLabel.setText("Ganaste, eres el arcano " + nickname);
        }
        else if(game.getMachinePoints()==20){endLabel.setText("Perdiste, eres pobre");}
        File file = new File("./src/main/resources/project/miniproject3/saves/game-data.ser");
        if (file.exists()) {
            boolean deleted = file.delete();
            if (deleted) {
                System.out.println("El archivo fue eliminado exitosamente.");
            } else {
                System.out.println("No se pudo eliminar el archivo.");
            }
        } else {
            System.out.println("El archivo no existe.");
        }
    }
}
