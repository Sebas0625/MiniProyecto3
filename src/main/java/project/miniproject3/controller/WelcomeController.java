package project.miniproject3.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

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
    public void handleContinue(ActionEvent event){

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
