package project.miniproject3.controller;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import project.miniproject3.model.FileHandling.PlainTextFileHandler;
import project.miniproject3.model.Game;
import project.miniproject3.model.FileHandling.SerializableFileHandler;
import project.miniproject3.model.GameMatrix;
import project.miniproject3.model.ships.Ships;
import project.miniproject3.model.GameException;
import project.miniproject3.view.GameStage;
import project.miniproject3.view.WelcomeStage;

import java.io.File;
import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class GameController implements Initializable {
    private Game game;
    private Random rand;
    private String nickname;
    private String character;
    boolean isMachineVisible = false;
    @FXML
    private GridPane playerBoard;
    @FXML
    private GridPane machineBoard;
    @FXML
    private Label endLabel;
    @FXML
    ImageView endGameImage;
    @FXML
    ImageView playerImageView;
    @FXML
    Label nicknameLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PlainTextFileHandler plainTextFileHandler = new PlainTextFileHandler();
        String[] data = plainTextFileHandler.readFromFile("./src/main/resources/project/miniproject3/saves/player-data.csv");
        this.nickname = data[0];
        this.character = data[1];
        System.out.println(nickname);
        System.out.println(character);

        showNickName();
        showCharacter();
        Group tempImage = Ships.createCrosshair();
        tempImage.setVisible(false);
        machineBoard.getChildren().add(tempImage);
        machineBoard.setOnMouseMoved(event ->{
            double mouseX = event.getX();
            double mouseY = event.getY();

            int col = (int) (mouseX / 40);
            int row = (int) (mouseY / 40);

            GridPane.setColumnIndex(tempImage, col);
            GridPane.setRowIndex(tempImage, row);

            tempImage.setVisible(true);
        });
        machineBoard.setOnMouseExited(event -> {
            tempImage.setVisible(false);
        });

        machineBoard.setOnMouseClicked(event -> {
            tempImage.setVisible(false);
            machineBoard.getChildren().remove(tempImage);
            if (game.getPlayerPoints()!=20 && game.getMachinePoints()!=20) {
                double x = event.getX();
                double y = event.getY();
                rand= new Random();

                int col = (int) (x / (machineBoard.getWidth() / machineBoard.getColumnCount()));
                int row = (int) (y / (machineBoard.getHeight() / machineBoard.getRowCount()));

                int num = game.getMachineMatrix().getNumber(row, col);

                if (num != 0 & num != 5 & num != 6 & num != 7) {
                    game.getMachineMatrix().setNumber(row, col, 6);
                    game.setPlayerPoints(game.getPlayerPoints() + 1);
                    machineBoard.add(Ships.createBomb(), col, row);
                    verifySunkenShips(game.getMachinePositions(), machineBoard, game.getMachineMatrix());
                    if (isGameFinished()){
                        finishGame();
                    }
                } else if (num == 0) {
                    game.getMachineMatrix().setNumber(row, col, 5);
                    machineBoard.add(Ships.drawX(), col, row);
                    if (isGameFinished()){
                        finishGame();
                    }
                    machineTurn();
                }
                else {
                    try {
                        if (game.getMachineMatrix().getNumber(row, col) == 5 || game.getMachineMatrix().getNumber(row, col) == 6) {
                            throw new GameException("Punto ya disparado");
                        }
                    } catch (GameException e) {
                        endLabel.setText(e.getMessage());
                        PauseTransition pause = new PauseTransition(Duration.seconds(2));
                        pause.setOnFinished(event1 -> {
                            endLabel.setText("");
                        });
                        pause.play();
                    }
                }
            }
            machineBoard.getChildren().add(tempImage);
            tempImage.setVisible(true);
        });
    }

    @FXML
    void handleExitButton(ActionEvent event) throws IOException {
        SerializableFileHandler serializableFileHandler = new SerializableFileHandler();
        serializableFileHandler.serialize("./src/main/resources/project/miniproject3/saves/game-data.ser", game);
        GameStage.deleteInstance();
        WelcomeStage.getInstance();
    }

    public void setGame(Game game, boolean isNewGame){
        this.game = game;
        showPlayerShips(isNewGame);
    }

    public void showCharacter(){
        switch (character){
            case "character1": playerImageView.setImage(
                    new Image(getClass().getResource("/project/miniproject3/images/selection1.png").toExternalForm()));
                break;
            case "character2": playerImageView.setImage(
                    new Image(getClass().getResource("/project/miniproject3/images/selection4.png").toExternalForm()));
                break;
            case "character3": playerImageView.setImage(
                    new Image(getClass().getResource("/project/miniproject3/images/selection2.png").toExternalForm()));
                break;
            case "character4": playerImageView.setImage(
                    new Image(getClass().getResource("/project/miniproject3/images/selection3.png").toExternalForm()));
                break;
            case "character5": playerImageView.setImage(
                    new Image(getClass().getResource("/project/miniproject3/images/selection5.png").toExternalForm()));
                break;
        }
    }

    public void showNickName(){
        nicknameLabel.setText(nickname);
    }

    public void machineTurn(){
        machineBoard.setDisable(true);
        PauseTransition pause = new PauseTransition(Duration.seconds(1));

        pause.setOnFinished(event -> {
            int rand1 = 0;
            int rand2 = 0;
            try{
                do {
                    rand1 = rand.nextInt(10);
                    rand2 = rand.nextInt(10);
                    System.out.println(rand1);
                    System.out.println(rand2);
                } while (game.getPlayerMatrix().getNumber(rand1, rand2) == 5 || game.getPlayerMatrix().getNumber(rand1, rand2) == 6);
            }catch (IndexOutOfBoundsException e){
                System.out.println("Fuera del indice de la máquina " +e.getMessage());
            }
            if (game.getPlayerMatrix().getNumber(rand1, rand2) == 0) {
            } while (game.getPlayerMatrix().getNumber(rand1, rand2) == 5 || game.getPlayerMatrix().getNumber(rand1, rand2) == 6 || game.getPlayerMatrix().getNumber(rand1, rand2) == 7);

            int num = game.getPlayerMatrix().getNumber(rand1, rand2);

            if (num == 0) {
                game.getPlayerMatrix().setNumber(rand1, rand2, 5);
                playerBoard.add(Ships.drawX(), rand2, rand1);
            } else {
                game.getPlayerMatrix().setNumber(rand1, rand2, 6);
                game.setMachinePoints(game.getMachinePoints() + 1);
                playerBoard.add(Ships.createBomb(), rand2, rand1);
                verifySunkenShips(game.getPlayerPositions(), playerBoard, game.getPlayerMatrix());
            }
            game.getPlayerMatrix().printMatrix();

            if (isGameFinished()) {
                finishGame();
            }
            machineBoard.setDisable(false);
        });
        pause.play();
    }

    public void showPlayerShips(boolean isNewGame){
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
                if (isNewGame) game.placeShip(row, col, rowSpan, colSpan, type);
            }
        }
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                if (game.getPlayerMatrix().getNumber(i, j) == 5){
                    playerBoard.add(Ships.drawX(), j, i);
                } else if (game.getPlayerMatrix().getNumber(i, j) == 6){
                    playerBoard.add(Ships.createBomb(), j, i);
                } else if (game.getPlayerMatrix().getNumber(i, j) == 7){
                    playerBoard.add(Ships.createFire(), j, i);
                }
                if (game.getMachineMatrix().getNumber(i, j) == 5){
                    machineBoard.add(Ships.drawX(), j, i);
                } else if (game.getMachineMatrix().getNumber(i, j) == 6){
                    machineBoard.add(Ships.createBomb(), j, i);
                } else if (game.getMachineMatrix().getNumber(i, j) == 7){
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

    public void verifySunkenShips(ArrayList<ArrayList<Integer>> shipData, GridPane pane, GameMatrix matrix) {
        int row, col, rowSpan, colSpan;
        for (ArrayList<Integer> data : shipData) {
            row = data.get(0);
            col = data.get(1);
            rowSpan = data.get(2);
            colSpan = data.get(3);

            boolean flag = true;

            for (int i = row; i < row + rowSpan; i++) {
                for (int j = col; j < col + colSpan; j++) {
                    if (matrix.getNumber(i, j) != 6) {
                        flag = false;
                        break;
                    }
                }
                if (!flag) break;
            }
            if (flag) {
                for (int i = row; i < row + rowSpan; i++) {
                    for (int j = col; j < col + colSpan; j++) {
                        matrix.setNumber(i, j, 7);
                        removeNodeFromGridPane(pane, j, i);
                        pane.add(Ships.createFire(), j, i);
                    }
                }
            }
        }
    }

    public void removeNodeFromGridPane(GridPane gridPane, int column, int row) {
        Node nodeToRemove = null;
        for (Node node : gridPane.getChildren()) {
            Integer nodeColumn = GridPane.getColumnIndex(node);
            Integer nodeRow = GridPane.getRowIndex(node);
            if (nodeColumn != null && nodeRow != null && nodeColumn == column && nodeRow == row) {
                nodeToRemove = node;
                break;
            }
        }
        if (nodeToRemove != null) {
            gridPane.getChildren().remove(nodeToRemove);
        }
    }

    public void showMachineMatrix(ActionEvent event){
        if (isMachineVisible){
            machineBoard.getChildren().clear();
            machineBoard.setGridLinesVisible(false);
            machineBoard.setGridLinesVisible(true);
            for (int i = 0; i < 10; i++){
                for (int j = 0; j < 10; j++){
                    if (game.getMachineMatrix().getNumber(i, j) == 5){
                        machineBoard.add(Ships.drawX(), j, i);
                    } else if (game.getMachineMatrix().getNumber(i, j) == 6){
                        machineBoard.add(Ships.createBomb(), j, i);
                    } else if (game.getMachineMatrix().getNumber(i, j) == 7){
                        machineBoard.add(Ships.createFire(), j, i);
                    }
                }
            }
        }else {
            int row, col, rowSpan, colSpan, type;
            Group shape = new Group();
            for (ArrayList<Integer> shipData : game.getMachinePositions()){
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
                    machineBoard.add(shape, col, row, colSpan, rowSpan);
                }
            }
        }
        this.isMachineVisible = !isMachineVisible;
    }

    public void playSound(String soundName, float volumeValue){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            if (volume != null) {
                volume.setValue(volumeValue);
            }

        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error al reproducir el sonido.");
        }
    }

    public void bSound(){
        playSound("src/main/resources/project/miniproject3/sounds/button-3.wav",-10);
    }

    public void startSound(){
        playSound("src/main/resources/project/miniproject3/sounds/gameStart.wav",-10);
    }

    public void finishGame(){
        if(game.getPlayerPoints()==20){
            endLabel.setText("Ganaste, eres el arcano");
            setEndGameImage();
        }
        else if(game.getMachinePoints()==20){
            endLabel.setText("Perdiste, eres pobre");
            setEndGameImage();
        }

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

    public void shootSound(){playSound("src/main/resources/project/miniproject3/sounds/cannon.wav",-15);}

    public void setEndGameImage(){
        if(game.getMachinePoints()==20){
            endGameImage.setVisible(true);
            endGameImage.setImage(new Image(getClass().getResource("/project/miniproject3/images/loseImage.png").toExternalForm()));
        }
        else if(game.getPlayerPoints()==20){
            endGameImage.setVisible(true);
            endGameImage.setImage(new Image(getClass().getResource("/project/miniproject3/images/winImage.png").toExternalForm()));
        }
    }
}
