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

import javax.print.attribute.standard.Media;
import javax.sound.sampled.*;

public class WelcomeController {
    @FXML
    private HBox charactersBox;


    @FXML
    public void handlePlay(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/project/miniproject3/fxml/positioning-view.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/project/miniproject3/styles/positioning-view-style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleContinue(ActionEvent event) throws IOException{
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

    }

    @FXML
    public void handleCredits(){

    }

    @FXML
    public void handleCharacter(){
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



    public void bSound(){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("/sounds/button-3.mp3").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error al reproducir el sonido.");
        }
    }
}
