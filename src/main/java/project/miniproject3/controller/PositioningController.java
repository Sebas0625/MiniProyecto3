package project.miniproject3.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import project.miniproject3.model.Game;
import project.miniproject3.view.GameStage;
import project.miniproject3.view.WelcomeStage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PositioningController implements Initializable {

    private Game game;

    @FXML
    void handleStartGame(ActionEvent event) throws IOException{
        WelcomeStage.deleteInstance();
        GameStage.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        game = new Game();


    }

}
