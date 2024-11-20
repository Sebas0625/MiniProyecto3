package project.miniproject3.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import project.miniproject3.model.Game;
import project.miniproject3.model.SerializableFileHandler;
import project.miniproject3.view.GameStage;

import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.animation.*;

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
    public void initialize(){
        character1.setOnMouseClicked(mouseEvent ->
        { characterView.setImage(new Image(getClass().getResource("/project/miniproject3/images/selection1.png").toExternalForm()));
            setTransition();});
        character2.setOnMouseClicked(mouseEvent ->
        { characterView.setImage(new Image(getClass().getResource("/project/miniproject3/images/selection4.png").toExternalForm()));
            setTransition();});
        character3.setOnMouseClicked(mouseEvent ->
        { characterView.setImage(new Image(getClass().getResource("/project/miniproject3/images/selection2.png").toExternalForm()));
            setTransition();});
        character4.setOnMouseClicked(mouseEvent ->
        { characterView.setImage(new Image(getClass().getResource("/project/miniproject3/images/selection3.png").toExternalForm()));
            setTransition();});
        character5.setOnMouseClicked(mouseEvent ->
        { characterView.setImage(new Image(getClass().getResource("/project/miniproject3/images/selection5.png").toExternalForm()));
            setTransition();});
    }

    private void setTransition(){
        TranslateTransition transition = new TranslateTransition(Duration.millis(300), charactersBox);
        transition.setToX(-charactersBox.getWidth());
        transition.setOnFinished(event1 -> charactersBox.setVisible(false));
        transition.play();
    }

    @FXML
    public void handlePlay(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/project/miniproject3/fxml/positioning-view.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/project/miniproject3/styles/positioning-view-style.css").toExternalForm());
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), scene.getRoot());
        fadeTransition.setFromValue(0);  // Comienza totalmente transparente
        fadeTransition.setToValue(1);

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
            transition.setOnFinished(event -> charactersBox.setVisible(false));
            transition.play();
        } else {
            charactersBox.setVisible(true);
            charactersBox.setTranslateX(-charactersBox.getWidth());
            transition.setToX(0);
            transition.play();
        }
    }
}
