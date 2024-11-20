package project.miniproject3.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import project.miniproject3.model.Game;
import project.miniproject3.model.SerializableFileHandler;
import project.miniproject3.view.GameStage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.animation.TranslateTransition;

import javax.sound.sampled.*;

public class WelcomeController {
    int variableControl = 0;
    @FXML
    private HBox charactersBox;

    @FXML
    public void handlePlay(ActionEvent event) throws IOException {
        startSound();
        Parent root = FXMLLoader.load(getClass().getResource("/project/miniproject3/fxml/positioning-view.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/project/miniproject3/styles/positioning-view-style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        variableControl = 1;
    }

    @FXML
    public void handleContinue(ActionEvent event) throws IOException{
        variableControl = 1;
        startSound();
        SerializableFileHandler serializableFileHandler = new SerializableFileHandler();
        try {
            Game game = (Game) serializableFileHandler.deserialize("./src/main/resources/project/miniproject3/saves/game-data.ser");
            GameStage.getInstance().getGameController().setGame(game);
            if (game.getMachineMatrix() == null){
                System.out.println("Usted no tiene partidas guardadas");
                // no hay partidas guardadas
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("El archivo no existe");
        }
    }

    @FXML
    public void handleTutorial(){
        startSound();
        variableControl = 1;

    }

    @FXML
    public void handleCredits(){
        startSound();
    }

    @FXML
    public void handleCharacter(){
        startSound();
        TranslateTransition transition = new TranslateTransition(Duration.millis(300), charactersBox);
        if (charactersBox.isVisible()) {
            transition.setToX(-charactersBox.getWidth());
            charactersBox.setVisible(false);
        } else {
            charactersBox.setVisible(true);
            transition.setToX(0);
        }
        transition.play();
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
    public void playBackgroundSound(String sound, float volumen) {
        if(variableControl == 0) {
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

                // Ajustar el volumen. El rango típico es -80.0 (silencio) a 6.0 (máximo volumen)
                if (volume != null) {
                    volume.setValue(volumen);
                }

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                System.out.println("Error al reproducir el sonido.");
            }
        }
    }
}
