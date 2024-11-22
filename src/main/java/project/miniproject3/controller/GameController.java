package project.miniproject3.controller;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
import project.miniproject3.model.Exception.GameException;
import project.miniproject3.view.GameStage;
import project.miniproject3.view.WelcomeStage;

import java.io.File;
import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class GameController implements Initializable {
    private Game game; // Class representing the main game logic
    private Random rand; // Random number generator
    private String nickname; // Player's nickname
    private String character; // Character selected by the player
    boolean isMachineVisible = false; // State of the machine's board visibility

    @FXML
    private GridPane playerBoard; // Player's board
    @FXML
    private GridPane machineBoard; // Machine's board
    @FXML
    private Label endLabel; // Label to display game-end messages
    @FXML
    ImageView endGameImage; // Image shown at the end of the game
    @FXML
    ImageView playerImageView; // Image of the player's character
    @FXML
    Label nicknameLabel; // Label to display the player's nickname
    @FXML
    Button showButton; // Button to show/hide the machine's board
    @FXML
    Button exitButton; // Button to exit the game

    // Method executed when initializing the controller
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Read player data from a CSV file
        PlainTextFileHandler plainTextFileHandler = new PlainTextFileHandler();
        String[] data = plainTextFileHandler.readFromFile("./src/main/resources/project/miniproject3/saves/player-data.csv");
        this.nickname = data[0];
        this.character = data[1];

        // Display the nickname and character in the UI
        showNickName();
        showCharacter();

        // Create and add a crosshair marker to the machine's board
        Group tempImage = Ships.createCrosshair();
        tempImage.setVisible(false);
        machineBoard.getChildren().add(tempImage);

        // Configure events for the machine's board
        machineBoard.setOnMouseMoved(event -> {
            // Calculate the cell under the mouse
            double mouseX = event.getX();
            double mouseY = event.getY();
            int col = (int) (mouseX / 40);
            int row = (int) (mouseY / 40);
            GridPane.setColumnIndex(tempImage, col);
            GridPane.setRowIndex(tempImage, row);
            tempImage.setVisible(true);
        });

        machineBoard.setOnMouseExited(event -> tempImage.setVisible(false));

        machineBoard.setOnMouseClicked(event -> {
            // Logic to handle shots when clicking the machine's board
            tempImage.setVisible(false);
            machineBoard.getChildren().remove(tempImage);

            if (game.getPlayerPoints() != 20 && game.getMachinePoints() != 20) {
                double x = event.getX();
                double y = event.getY();
                rand = new Random();

                int col = (int) (x / (machineBoard.getWidth() / machineBoard.getColumnCount()));
                int row = (int) (y / (machineBoard.getHeight() / machineBoard.getRowCount()));

                int num = game.getMachineMatrix().getNumber(row, col);

                if (num != 0 & num != 5 & num != 6 & num != 7) {
                    // Hit on an enemy ship
                    game.getMachineMatrix().setNumber(row, col, 6);
                    game.setPlayerPoints(game.getPlayerPoints() + 1);
                    machineBoard.add(Ships.createBomb(), col, row);
                    verifySunkenShips(game.getMachinePositions(), machineBoard, game.getMachineMatrix());

                    if (isGameFinished()) {
                        finishGame();
                    }
                } else if (num == 0) {
                    // Miss (water)
                    game.getMachineMatrix().setNumber(row, col, 5);
                    machineBoard.add(Ships.drawX(), col, row);
                    machineTurn(); // Machine's turn
                } else {
                    // Already shot at this point
                    try {
                        if (game.getMachineMatrix().getNumber(row, col) == 5 ||
                                game.getMachineMatrix().getNumber(row, col) == 6 ||
                                game.getMachineMatrix().getNumber(row, col) == 7) {
                            throw new GameException("Punto ya disparado");
                        }
                    } catch (GameException e) {
                        endLabel.setText(e.getMessage());
                        endLabel.setStyle("-fx-text-fill: Black;");
                        PauseTransition pause = new PauseTransition(Duration.seconds(2));
                        pause.setOnFinished(event1 -> endLabel.setText(""));
                        pause.play();
                    }
                }
            }
            machineBoard.getChildren().add(tempImage);
            tempImage.setVisible(true);
        });
    }

    // Method to handle exiting the game and saving data
    @FXML
    void handleExitButton(ActionEvent event) throws IOException {
        SerializableFileHandler serializableFileHandler = new SerializableFileHandler();
        serializableFileHandler.serialize("./src/main/resources/project/miniproject3/saves/game-data.ser", game);
        GameStage.closeInstance();
        WelcomeStage.getInstance();
    }

    public void setGame(Game game, boolean isNewGame) {
        this.game = game;
        showPlayerShips(isNewGame);
    }

    public void showCharacter() {
        switch (character) {
            case "character1":
                playerImageView.setImage(new Image(getClass().getResource("/project/miniproject3/images/selection1.png").toExternalForm()));
                break;
            case "character2":
                playerImageView.setImage(new Image(getClass().getResource("/project/miniproject3/images/selection4.png").toExternalForm()));
                break;
            case "character3":
                playerImageView.setImage(new Image(getClass().getResource("/project/miniproject3/images/selection2.png").toExternalForm()));
                break;
            case "character4":
                playerImageView.setImage(new Image(getClass().getResource("/project/miniproject3/images/selection3.png").toExternalForm()));
                break;
            case "character5":
                playerImageView.setImage(new Image(getClass().getResource("/project/miniproject3/images/selection5.png").toExternalForm()));
                break;
        }
    }

    /**
     * Displays the nickname of the player by setting it on the nickname label.
     */
    public void showNickName() {
        nicknameLabel.setText(nickname);
    }

    /**
     * Executes the machine's turn in the game.
     * The machine randomly selects a cell on the player's board to attack.
     * If a ship is hit, the machine continues its turn; otherwise, the turn ends.
     * The game state is updated accordingly.
     */
    public void machineTurn() {
        machineBoard.setDisable(true);
        PauseTransition pause = new PauseTransition(Duration.seconds(1));

        pause.setOnFinished(event -> {
            int rand1 = 0;
            int rand2 = 0;
            try {
                do {
                    rand1 = rand.nextInt(10);
                    rand2 = rand.nextInt(10);
                    System.out.println(rand1);
                    System.out.println(rand2);
                } while (game.getPlayerMatrix().getNumber(rand1, rand2) == 5
                        || game.getPlayerMatrix().getNumber(rand1, rand2) == 6
                        || game.getPlayerMatrix().getNumber(rand1, rand2) == 7);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Out of bounds in machine turn: " + e.getMessage());
            }

            int num = game.getPlayerMatrix().getNumber(rand1, rand2);

            if (num == 0) {
                game.getPlayerMatrix().setNumber(rand1, rand2, 5);
                playerBoard.add(Ships.drawX(), rand2, rand1);
                machineBoard.setDisable(false);
            } else {
                game.getPlayerMatrix().setNumber(rand1, rand2, 6);
                game.setMachinePoints(game.getMachinePoints() + 1);
                playerBoard.add(Ships.createBomb(), rand2, rand1);
                verifySunkenShips(game.getPlayerPositions(), playerBoard, game.getPlayerMatrix());
                machineTurn();
            }
            game.getPlayerMatrix().printMatrix();

            if (isGameFinished()) {
                finishGame();
            }
        });
        pause.play();
    }

    /**
     * Displays the player's ships on the board.
     * Adds graphical representations of ships and previously attacked cells to the player's board.
     *
     * @param isNewGame Indicates whether this is a new game, which determines if ships are placed on the game matrix.
     */
    public void showPlayerShips(boolean isNewGame) {
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
                if (isNewGame) game.placeShip(row, col, rowSpan, colSpan, type);
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (game.getPlayerMatrix().getNumber(i, j) == 5) {
                    playerBoard.add(Ships.drawX(), j, i);
                } else if (game.getPlayerMatrix().getNumber(i, j) == 6) {
                    playerBoard.add(Ships.createBomb(), j, i);
                } else if (game.getPlayerMatrix().getNumber(i, j) == 7) {
                    playerBoard.add(Ships.createFire(), j, i);
                }
                if (game.getMachineMatrix().getNumber(i, j) == 5) {
                    machineBoard.add(Ships.drawX(), j, i);
                } else if (game.getMachineMatrix().getNumber(i, j) == 6) {
                    machineBoard.add(Ships.createBomb(), j, i);
                } else if (game.getMachineMatrix().getNumber(i, j) == 7) {
                    machineBoard.add(Ships.createFire(), j, i);
                }
            }
        }
        game.getPlayerMatrix().printMatrix();
    }

    /**
     * Checks if the game has been finished by either the player or the machine reaching 20 points.
     *
     * @return {@code true} if the game is finished, {@code false} otherwise.
     */
    public boolean isGameFinished() {
        if (game.getMachinePoints() == 20) {
            return true;
        } else {
            return game.getPlayerPoints() == 20;
        }
    }

    /**
     * Verifies and marks ships as sunken if all their cells have been hit.
     * Updates the grid pane to visually represent sunken ships.
     *
     * @param shipData List of ship data including positions and dimensions.
     * @param pane     The grid pane where ships are displayed.
     * @param matrix   The game matrix that contains ship and hit information.
     */
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

    /**
     * Removes a node from the specified GridPane at a given column and row.
     *
     * @param gridPane The GridPane from which the node will be removed.
     * @param column   The column index of the node to remove.
     * @param row      The row index of the node to remove.
     */
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

    /**
     * Toggles the visibility of the machine's board.
     * If the board is visible, only hits are shown. Otherwise, all ships are displayed.
     *
     * @param event The action event triggered by the user.
     */
    public void showMachineMatrix(ActionEvent event) {
        if (isMachineVisible) {
            machineBoard.getChildren().clear();
            machineBoard.setGridLinesVisible(false);
            machineBoard.setGridLinesVisible(true);
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (game.getMachineMatrix().getNumber(i, j) == 5) {
                        machineBoard.add(Ships.drawX(), j, i);
                    } else if (game.getMachineMatrix().getNumber(i, j) == 6) {
                        machineBoard.add(Ships.createBomb(), j, i);
                    } else if (game.getMachineMatrix().getNumber(i, j) == 7) {
                        machineBoard.add(Ships.createFire(), j, i);
                    }
                }
            }
        } else {
            int row, col, rowSpan, colSpan, type;
            Group shape = new Group();
            for (ArrayList<Integer> shipData : game.getMachinePositions()) {
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
                    machineBoard.add(shape, col, row, colSpan, rowSpan);
                }
            }
        }
        this.isMachineVisible = !isMachineVisible;
    }

    /**
     * Plays a sound file with the specified volume.
     *
     * @param soundName   The name or path of the sound file to play.
     * @param volumeValue The volume level for the sound.
     */
    public void playSound(String soundName, float volumeValue) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            if (volume != null) {
                volume.setValue(volumeValue);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error playing sound.");
        }
    }

    /**
     * Plays the sound for a button click.
     */
    public void bSound() {
        playSound("src/main/resources/project/miniproject3/sounds/button-3.wav", -10);
    }

    /**
     * Plays the sound for starting the game.
     */
    public void startSound() {
        playSound("src/main/resources/project/miniproject3/sounds/gameStart.wav", -10);
    }

    /**
     * Ends the game, checks the winner, and deletes the saved game file if it exists.
     */
    public void finishGame() {
        if (game.getPlayerPoints() == 20) {
            setEndGameImage();
        } else if (game.getMachinePoints() == 20) {
            setEndGameImage();
        }

        File file = new File("./src/main/resources/project/miniproject3/saves/game-data.ser");
        if (file.exists()) {
            boolean deleted = file.delete();
            if (deleted) {
                System.out.println("The file was successfully deleted.");
                exitButton.setDisable(true);
                showButton.setDisable(true);
            } else {
                System.out.println("The file could not be deleted.");
            }
        } else {
            System.out.println("The file does not exist.");
        }
    }

    /**
     * Plays the sound for shooting a cannon.
     */
    public void shootSound() {
        playSound("src/main/resources/project/miniproject3/sounds/cannon.wav", -15);
    }

    /**
     * Sets the end-game image based on the winner (player or machine).
     * Displays a win or lose image with a fade and scale transition.
     */
    public void setEndGameImage() {
        if (game.getMachinePoints() == 20) {
            endGameImage.setImage(new Image(getClass().getResource("/project/miniproject3/images/loseImage.png").toExternalForm()));
            endGameImage.setOpacity(0);
            endGameImage.setScaleX(0.5);
            endGameImage.setScaleY(0.5);
            endGameImage.setVisible(true);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), endGameImage);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);

            ScaleTransition scaleIn = new ScaleTransition(Duration.seconds(0.5), endGameImage);
            scaleIn.setFromX(0.5);
            scaleIn.setFromY(0.5);
            scaleIn.setToX(1);
            scaleIn.setToY(1);

            fadeIn.play();
            scaleIn.play();
        } else if (game.getPlayerPoints() == 20) {
            endGameImage.setImage(new Image(getClass().getResource("/project/miniproject3/images/winImage.png").toExternalForm()));
            endGameImage.setOpacity(0);
            endGameImage.setScaleX(0.5);
            endGameImage.setScaleY(0.5);
            endGameImage.setVisible(true);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), endGameImage);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);

            ScaleTransition scaleIn = new ScaleTransition(Duration.seconds(0.5), endGameImage);
            scaleIn.setFromX(0.5);
            scaleIn.setFromY(0.5);
            scaleIn.setToX(1);
            scaleIn.setToY(1);

            fadeIn.play();
            scaleIn.play();
        }
    }

    /**
     * Closes the current game stage and opens the welcome stage.
     *
     * @throws IOException If an error occurs during stage switching.
     */
    public void returnImage() throws IOException {
        GameStage.closeInstance();
        WelcomeStage.getInstance();
    }
}
