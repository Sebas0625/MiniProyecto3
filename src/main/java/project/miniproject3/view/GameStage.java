package project.miniproject3.view;

import javafx.scene.text.Font;
import project.miniproject3.controller.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The {@code GameStage} class represents the main game window for the Battleship application.
 * It extends {@code Stage} and manages the visual aspects and lifecycle of the game interface.
 */
public class GameStage extends Stage {

    private GameController gameController;

    /**
     * Constructs a new {@code GameStage} instance, initializes the user interface
     * by loading the FXML layout, and sets the stage properties.
     *
     * @throws IOException If an error occurs during the loading of the FXML file.
     */
    public GameStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/miniproject3/fxml/game-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        this.gameController = loader.getController();
        scene.getStylesheets().add(getClass().getResource("/project/miniproject3/styles/game-view-style.css").toExternalForm());
        setScene(scene);
        setTitle("Battleship");
        getIcons().add(new Image(getClass().getResourceAsStream("/project/miniproject3/images/icon.png")));
        setResizable(false);
        //getIcons().add(new Image(String.valueOf(getClass().getResource(""))));

        setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            deleteInstance();
        });
        show();
    }

    /**
     * Holder class for implementing the Singleton pattern for {@code GameStage}.
     */
    private static class GameStageHolder {
        private static GameStage INSTANCE;
    }

    /**
     * Retrieves the singleton instance of {@code GameStage}. If it doesn't exist,
     * a new instance will be created.
     *
     * @return The singleton instance of {@code GameStage}.
     * @throws IOException If an error occurs during the loading of the FXML file.
     */
    public static GameStage getInstance() throws IOException {
        GameStageHolder.INSTANCE =
                GameStageHolder.INSTANCE != null ? GameStageHolder.INSTANCE : new GameStage();
        return GameStageHolder.INSTANCE;
    }

    /**
     * Prompts the user for confirmation before closing the game stage.
     * If confirmed, the instance will be closed and set to null.
     */
    public static void deleteInstance() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(null);
        alert.setHeaderText("¿Seguro que desea cerrar la ventana?");
        alert.setContentText("Perderá el progreso actual.");
        if (alert.showAndWait().get() == ButtonType.OK) {
            GameStageHolder.INSTANCE.close();
            GameStageHolder.INSTANCE = null;
        }
    }

    /**
     * Closes the current instance of {@code GameStage} and sets it to null.
     */
    public static void closeInstance() {
        GameStageHolder.INSTANCE.close();
        GameStageHolder.INSTANCE = null;
    }

    /**
     * Retrieves the {@code GameController} associated with this game stage.
     *
     * @return The {@code GameController} instance.
     */
    public GameController getGameController() {
        return gameController;
    }
}
