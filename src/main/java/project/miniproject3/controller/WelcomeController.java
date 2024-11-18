package project.miniproject3.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import project.miniproject3.model.Game;
import project.miniproject3.model.SerializableFileHandler;
import project.miniproject3.view.GameStage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class WelcomeController {

    @FXML
    public void handlePlay(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/project/miniproject3/fxml/positioning-view.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
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

    }
}
