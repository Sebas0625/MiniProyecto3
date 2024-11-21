package project.miniproject3.view;

import javafx.scene.text.Font;
import project.miniproject3.controller.WelcomeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The {@code WelcomeStage} class represents the initial welcome window for the Battleship application.
 * It extends {@code Stage} and handles the visual aspects and lifecycle of the welcome interface.
 */
public class WelcomeStage extends Stage {
    private WelcomeController welcomeController;

    /**
     * Constructs a new {@code WelcomeStage} instance, initializes the user interface
     * by loading the FXML layout, and sets the stage properties.
     *
     * @throws IOException If an error occurs during the loading of the FXML file.
     */
    public WelcomeStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/miniproject3/fxml/welcome-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        this.welcomeController = loader.getController();
        welcomeController.playBackgroundSound("src/main/resources/project/miniproject3/sounds/backgroundSound.wav", -20);
        scene.getStylesheets().add(getClass().getResource("/project/miniproject3/styles/welcome-view-style.css").toExternalForm());
        setScene(scene);
        setTitle("BattleShip");
        getIcons().add(new Image(getClass().getResourceAsStream("/project/miniproject3/images/icon.png")));
        setResizable(false);
        setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            deleteInstance();
        });
        show();
    }

    /**
     * Holder class for implementing the Singleton pattern for {@code WelcomeStage}.
     */
    private static class WelcomeStageHolder {
        private static WelcomeStage INSTANCE;
    }

    /**
     * Retrieves the singleton instance of {@code WelcomeStage}. If it doesn't exist,
     * a new instance will be created.
     *
     * @return The singleton instance of {@code WelcomeStage}.
     * @throws IOException If an error occurs during the loading of the FXML file.
     */
    public static WelcomeStage getInstance() throws IOException {
        WelcomeStageHolder.INSTANCE =
                WelcomeStageHolder.INSTANCE != null ? WelcomeStageHolder.INSTANCE : new WelcomeStage();
        return WelcomeStageHolder.INSTANCE;
    }

    /**
     * Prompts the user for confirmation before closing the welcome stage.
     * If confirmed, the instance will be closed and set to null.
     */
    public static void deleteInstance() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(null);
        alert.setHeaderText("¿Seguro que desea cerrar la ventana?");
        alert.setContentText("Perderá el progreso actual.");
        if (alert.showAndWait().get() == ButtonType.OK) {
            WelcomeStageHolder.INSTANCE.close();
            WelcomeStageHolder.INSTANCE = null;
        }
    }

    /**
     * Closes the current instance of {@code WelcomeStage} and sets it to null.
     */
    public static void closeInstance() {
        WelcomeStageHolder.INSTANCE.close();
        WelcomeStageHolder.INSTANCE = null;
    }

    /**
     * Retrieves the {@code WelcomeController} associated with this welcome stage.
     *
     * @return The {@code WelcomeController} instance.
     */
    public WelcomeController getWelcomeController() {
        return welcomeController;
    }
}
