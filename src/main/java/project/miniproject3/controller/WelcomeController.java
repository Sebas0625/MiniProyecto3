package project.miniproject3.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import project.miniproject3.model.FileHandling.PlainTextFileHandler;
import project.miniproject3.model.Game;
import project.miniproject3.model.FileHandling.SerializableFileHandler;
import project.miniproject3.view.GameStage;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.animation.*;
import project.miniproject3.view.WelcomeStage;

import javax.sound.sampled.*;

/**
 * Controller for the Welcome view of the application.
 * Handles user interactions such as character selection, navigation, and playing sounds.
 */
public class WelcomeController {
    /**
     * Control variable to ensure certain actions execute only once.
     */
    int variableControl = 0;

    @FXML
    private HBox charactersBox;

    @FXML
    private ImageView characterView;

    @FXML
    private ImageView character1;

    @FXML
    private ImageView character2;

    @FXML
    private ImageView character3;

    @FXML
    private ImageView character4;

    @FXML
    private ImageView character5;

    @FXML
    private ImageView tutorialImageView;

    @FXML
    private TextField nickNameField;

    /**
     * Utility for handling plain text file operations.
     */
    private final PlainTextFileHandler plainTextFileHandler = new PlainTextFileHandler();

    /**
     * Selected character identifier.
     */
    private String character;

    /**
     * Initializes the controller by setting up event handlers and default states for UI elements.
     */
    @FXML
    public void initialize() {
        tutorialImageView.setVisible(false);
        tutorialImageView.setMouseTransparent(true);

        character1.setOnMouseClicked(mouseEvent -> {
            characterView.setImage(new Image(getClass().getResource("/project/miniproject3/images/selection1.png").toExternalForm()));
            setTransition();
            character = "character1";
        });
        character2.setOnMouseClicked(mouseEvent -> {
            characterView.setImage(new Image(getClass().getResource("/project/miniproject3/images/selection4.png").toExternalForm()));
            setTransition();
            character = "character2";
        });
        character3.setOnMouseClicked(mouseEvent -> {
            characterView.setImage(new Image(getClass().getResource("/project/miniproject3/images/selection2.png").toExternalForm()));
            setTransition();
            character = "character3";
        });
        character4.setOnMouseClicked(mouseEvent -> {
            characterView.setImage(new Image(getClass().getResource("/project/miniproject3/images/selection3.png").toExternalForm()));
            setTransition();
            character = "character4";
        });
        character5.setOnMouseClicked(mouseEvent -> {
            characterView.setImage(new Image(getClass().getResource("/project/miniproject3/images/selection5.png").toExternalForm()));
            setTransition();
            character = "character5";
        });
    }

    /**
     * Sets a transition effect for the characters box, hiding it after selection.
     */
    private void setTransition() {
        TranslateTransition transition = new TranslateTransition(Duration.millis(300), charactersBox);
        transition.setToX(-charactersBox.getWidth());
        transition.setOnFinished(event -> charactersBox.setVisible(false));
        transition.play();
    }

    /**
     * Handles the "Play" button action, transitioning to the positioning view.
     *
     * @param event the triggered ActionEvent
     * @throws IOException if the FXML or stylesheet files are not found
     */
    @FXML
    public void handlePlay(ActionEvent event) throws IOException {
        startSound();
        Parent root = FXMLLoader.load(getClass().getResource("/project/miniproject3/fxml/positioning-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/project/miniproject3/styles/positioning-view-style.css").toExternalForm());

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), scene.getRoot());
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);

        String nickname = nickNameField.getText().isEmpty() ? "Anónimo" : nickNameField.getText();
        plainTextFileHandler.writeToFile("./src/main/resources/project/miniproject3/saves/player-data.csv",
                nickname + "," + character);

        stage.setScene(scene);
        stage.show();
        variableControl = 1;
    }

    /**
     * Handles the "Continue" button action by loading a saved game.
     *
     * @param event the triggered ActionEvent
     * @throws IOException if deserialization fails or the save file is not found
     */
    @FXML
    public void handleContinue(ActionEvent event) throws IOException {
        variableControl = 1;
        startSound();
        SerializableFileHandler serializableFileHandler = new SerializableFileHandler();
        try {
            Game game = (Game) serializableFileHandler.deserialize("./src/main/resources/project/miniproject3/saves/game-data.ser");
            WelcomeStage.closeInstance();
            GameStage.getInstance().getGameController().setGame(game, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Usted no tiene partidas guardadas");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Usted no tiene partidas guardadas");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al cargar la partida");
        }
    }

    /**
     * Toggles the visibility of the tutorial image with transition effects.
     */
    @FXML
    public void handleTutorial() {
        Image tutorial = new Image(getClass().getResource("/project/miniproject3/images/tutorial.png").toExternalForm());
        startSound();

        tutorialImageView.setImage(tutorial);

        if (!tutorialImageView.isVisible()) {
            tutorialImageView.setOpacity(0);
            tutorialImageView.setScaleX(0.5);
            tutorialImageView.setScaleY(0.5);
            tutorialImageView.setVisible(true);
            tutorialImageView.setMouseTransparent(true);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), tutorialImageView);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);

            ScaleTransition scaleIn = new ScaleTransition(Duration.seconds(0.5), tutorialImageView);
            scaleIn.setFromX(0.5);
            scaleIn.setFromY(0.5);
            scaleIn.setToX(1);
            scaleIn.setToY(1);

            fadeIn.play();
            scaleIn.play();

            scaleIn.setOnFinished(e -> tutorialImageView.setMouseTransparent(false));
        } else {
            tutorialImageView.setOpacity(1);
            tutorialImageView.setScaleX(1);
            tutorialImageView.setScaleY(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), tutorialImageView);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);

            ScaleTransition scaleOut = new ScaleTransition(Duration.seconds(0.5), tutorialImageView);
            scaleOut.setFromX(1);
            scaleOut.setFromY(1);
            scaleOut.setToX(0.5);
            scaleOut.setToY(0.5);

            fadeOut.play();
            scaleOut.play();

            scaleOut.setOnFinished(e -> {
                tutorialImageView.setVisible(false);
                tutorialImageView.setMouseTransparent(true);
            });
        }
    }

    /**
     * Sets the tutorial image view to be transparent with a fade-out and scale-out transition.
     * The image view is hidden once the animation is complete, and mouse interactions are disabled.
     */
    public void setTransparent() {
        if (tutorialImageView.isVisible()) {
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), tutorialImageView);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);

            ScaleTransition scaleOut = new ScaleTransition(Duration.seconds(0.5), tutorialImageView);
            scaleOut.setFromX(1);
            scaleOut.setFromY(1);
            scaleOut.setToX(0.5);
            scaleOut.setToY(0.5);

            fadeOut.play();
            scaleOut.play();

            scaleOut.setOnFinished(e -> {
                tutorialImageView.setVisible(false);
                tutorialImageView.setMouseTransparent(true);
            });
        }
    }

    /**
     * Handles the "Credits" button action.
     * Currently, plays a sound when clicked but does not trigger any additional actions.
     */
    @FXML
    public void handleCredits() {
        startSound();

        Stage creditsStage = new Stage();
        creditsStage.setTitle("Créditos");

        creditsStage.initModality(Modality.WINDOW_MODAL);
        creditsStage.initStyle(StageStyle.UNDECORATED);

        Label titleLabel = new Label("CRÉDITOS");
        titleLabel.setStyle("-fx-text-fill: white;" +
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 10px;");

        Label creditLabel1 = new Label("Desarrollado por: Juan Sebástian Sierra, Daniel Andrade Reyes y Miguel Angel Castillo ");
        Label creditLabel2 = new Label("Códigos: 202343656 - 202343792 - 2341975");
        Label creditLabel4 = new Label("Materia: Programación Orientada a Eventos - 2024-2");

        String labelStyle = "-fx-text-fill: white; -fx-text-alignment: center;";
        creditLabel1.setStyle(labelStyle);
        creditLabel2.setStyle(labelStyle);
        creditLabel4.setStyle(labelStyle);

        creditLabel1.setWrapText(true);
        creditLabel2.setWrapText(true);
        creditLabel4.setWrapText(true);

        Button closeButton = new Button("Cerrar");
        closeButton.setOnAction(e -> creditsStage.close());
        closeButton.setStyle(
                "-fx-background-color: #c900ff;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 14px;" +
                        "-fx-border-color: white;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 10px;" +
                        "-fx-background-radius: 10px;" +
                        "-fx-padding: 5px 10px;"
        );
        VBox layout = new VBox(10);
        layout.getChildren().addAll(titleLabel, creditLabel1, creditLabel2, creditLabel4, closeButton);
        layout.setStyle("-fx-background-color: rgba(0,0,0,0.4);");
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 350, 250);
        creditsStage.setScene(scene);
        creditsStage.showAndWait();

    }

    /**
     * Toggles the visibility of the character selection box with a sliding transition effect.
     * If the box is visible, it slides out. If it's hidden, it slides in.
     */
    @FXML
    public void handleCharacter() {
        startSound();
        TranslateTransition transition = new TranslateTransition(Duration.millis(300), charactersBox);
        if (charactersBox.isVisible()) {
            transition.setToX(-charactersBox.getWidth());
            transition.setOnFinished(event -> charactersBox.setVisible(false));
            transition.play();
        } else {
            charactersBox.setVisible(true);
            charactersBox.setTranslateX(-charactersBox.getWidth());
            transition.setToX(0);
            transition.play();
        }
    }

    /**
     * Plays a sound file from the specified path at the specified volume.
     *
     * @param nombreSonido the path to the sound file to be played
     * @param volumen the volume level for the sound, typically in decibels
     */
    public void playSound(String nombreSonido, float volumen) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            if (volume != null) {
                volume.setValue(volumen);
            }

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error al reproducir el sonido.");
        }
    }

    /**
     * Plays a sound for a button click with a predefined file and volume.
     */
    public void bSound() {
        playSound("src/main/resources/project/miniproject3/sounds/button-3.wav", -10);
    }

    /**
     * Plays the sound that signifies the start of the game with a predefined file and volume.
     */
    public void startSound() {
        playSound("src/main/resources/project/miniproject3/sounds/gameStart.wav", -10);
    }

    /**
     * Plays a background sound on a loop with the specified volume, but only if the background sound has not already started.
     *
     * @param sound the path to the background sound file
     * @param volumeValue the volume level for the background sound, typically in decibels
     */
    public void playBackgroundSound(String sound, float volumeValue) {
        if (variableControl == 0) {
            System.out.println(variableControl);
            variableControl++;
            System.out.println(variableControl);
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(sound).getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);

                FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

                if (volume != null) {
                    volume.setValue(volumeValue);
                }

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                System.out.println("Error al reproducir el sonido.");
            }
        }
    }
}
