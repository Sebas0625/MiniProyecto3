package project.miniproject3.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import project.miniproject3.model.Game;
import project.miniproject3.view.GameStage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        
    }
    @FXML
    void handleExitButton(ActionEvent event)throws IOException {
        GameStage.deleteInstance();
    }
}
