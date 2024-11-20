package project.miniproject3.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import project.miniproject3.model.Game;
import project.miniproject3.model.Ships;
import project.miniproject3.view.GameStage;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
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

            if (game.getMachineMatrix().getNumber(row, col) != 0 & game.getPlayerMatrix().getNumber(row, col) != 5) {
                game.getMachineMatrix().setNumber(row, col, 6);
                impactsCounter++;
                machineBoard.add(Ships.createFire(), col, row);
                if (isGameFinished()) {
                    finishGame();
                }
            } else {
                game.getMachineMatrix().setNumber(row, col, 5);
                machineBoard.add(Ships.drawX(), col, row);
            }
        });
    }

    @FXML
    void handleExitButton(ActionEvent event) throws IOException {
        GameStage.deleteInstance();
    }

    public void setGame(Game game) {
        this.game = game;
        showPlayerShips();
    }

    public void showPlayerShips() {
        int row, col, rowSpan, colSpan, type;
        Group shape = new Group();
        for (ArrayList<Integer> shipData : game.getPlayerPositions()) {
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
            if (!(row == -1 || col == -1 || rowSpan == -1 || colSpan == -1)) {
                playerBoard.add(shape, col, row, colSpan, rowSpan);
                game.placeShip(row, col, rowSpan, colSpan, type);
            }
        }
        game.getPlayerMatrix().printMatrix();
    }

    public boolean isGameFinished() {
        return impactsCounter == 20;
    }

    public void finishGame() {

    }

    public void reproducirSonido(String nombreSonido, float volumen){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            // Ajustar el volumen. El rango típico es -80.0 (silencio) a 6.0 (máximo volumen)
            if (volume != null) {
                volume.setValue(volumen);
            }

        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error al reproducir el sonido.");
        }
    }

    public void bSound(){
        reproducirSonido("src/main/resources/project/miniproject3/sounds/button-3.wav",-10);
    }
    public void startSound(){
        reproducirSonido("src/main/resources/project/miniproject3/sounds/gameStart.wav",-10);
    }

}
