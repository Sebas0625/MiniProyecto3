package project.miniproject3.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import project.miniproject3.model.FileHandling.PlainTextFileHandler;
import project.miniproject3.model.Game;
import project.miniproject3.model.FileHandling.SerializableFileHandler;
import project.miniproject3.view.GameStage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.animation.*;

import javax.sound.sampled.*;

public class WelcomeController {
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
    private TextField nickNameField;
    private final PlainTextFileHandler plainTextFileHandler = new PlainTextFileHandler();
    private String character;

    @FXML
    public void initialize(){
        character1.setOnMouseClicked(mouseEvent ->
        { characterView.setImage(new Image(getClass().getResource("/project/miniproject3/images/selection1.png").toExternalForm()));
            setTransition();
            character = "character1";});
        character2.setOnMouseClicked(mouseEvent ->
        { characterView.setImage(new Image(getClass().getResource("/project/miniproject3/images/selection4.png").toExternalForm()));
            setTransition();
            character = "character2";});
        character3.setOnMouseClicked(mouseEvent ->
        { characterView.setImage(new Image(getClass().getResource("/project/miniproject3/images/selection2.png").toExternalForm()));
            setTransition();
            character = "character3";});
        character4.setOnMouseClicked(mouseEvent ->
        { characterView.setImage(new Image(getClass().getResource("/project/miniproject3/images/selection3.png").toExternalForm()));
            setTransition();
            character = "character4";});
        character5.setOnMouseClicked(mouseEvent ->
        { characterView.setImage(new Image(getClass().getResource("/project/miniproject3/images/selection5.png").toExternalForm()));
            setTransition();
            character = "character5";});
    }

    private void setTransition(){
        TranslateTransition transition = new TranslateTransition(Duration.millis(300), charactersBox);
        transition.setToX(-charactersBox.getWidth());
        transition.setOnFinished(event1 -> charactersBox.setVisible(false));
        transition.play();
    }


    @FXML
    public void handlePlay(ActionEvent event) throws IOException {
        startSound();
        Parent root = FXMLLoader.load(getClass().getResource("/project/miniproject3/fxml/positioning-view.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/project/miniproject3/styles/positioning-view-style.css").toExternalForm());
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), scene.getRoot());
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);

        String nickname = nickNameField.getText() == "" ? "Anónimo" : nickNameField.getText();
        plainTextFileHandler.writeToFile("./src/main/resources/project/miniproject3/saves/player-data.csv",
                nickname + "," + character);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleContinue(ActionEvent event) throws IOException{
        startSound();
        SerializableFileHandler serializableFileHandler = new SerializableFileHandler();
        PlainTextFileHandler plainTextFileHandler = new PlainTextFileHandler();
        try {
            String[] data = plainTextFileHandler.readFromFile("./src/main/resources/project/miniproject3/saves/player-data.csv");
            String nickname = data[0];
            String character = data[1];

            Game game = (Game) serializableFileHandler.deserialize("./src/main/resources/project/miniproject3/saves/game-data.ser");

            GameStage.getInstance().getGameController().setGame(game);
            if (game.getMachineMatrix() == null){
                System.out.println("Usted no tiene partidas guardadas");
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("El archivo no existe");
        }
    }

    @FXML
    public void handleTutorial(){
        startSound();

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
            transition.setOnFinished(event -> charactersBox.setVisible(false));
            transition.play();
        } else {
            charactersBox.setVisible(true);
            charactersBox.setTranslateX(-charactersBox.getWidth());
            transition.setToX(0);
            transition.play();
        }
    }

    public void ReproducirSonido(String nombreSonido){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error al reproducir el sonido.");
        }
    }

    public void bSound(){
        ReproducirSonido("src/main/resources/project/miniproject3/sounds/button-3.wav");
    }
    public void startSound(){
        ReproducirSonido("src/main/resources/project/miniproject3/sounds/gameStart.wav");
    }
}
