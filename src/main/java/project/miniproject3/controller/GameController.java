package project.miniproject3.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import project.miniproject3.model.Game;
import project.miniproject3.view.GameStage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    Game game;
    @FXML
    GridPane playerBoard;
    @FXML
    GridPane machineBoard;
    private int impactsCounter = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        machineBoard.setOnMouseClicked(event -> {
            double x = event.getX();
            double y = event.getY();

            int col = (int) (x / (machineBoard.getWidth() / machineBoard.getColumnCount()));
            int row = (int) (y / (machineBoard.getHeight() / machineBoard.getRowCount()));

            if (game.getPlayerMatrix().getNumber(row, col) != 0){
                game.getPlayerMatrix().setNumber(row, col, 6);
                impactsCounter++;
                if (isGameFinished()){
                    finishGame();
                }
                // evento de impacto contra barco
            } else{
                game.getPlayerMatrix().setNumber(row, col, 5);
                // evento de impacto contra el agua
            }
        });
    }

    @FXML
    void handleExitButton(ActionEvent event) throws IOException {
        GameStage.deleteInstance();
    }

    public void setGame(Game game){
        this.game = game;
    }

    public boolean isGameFinished(){
        return impactsCounter == 20;
    }

    public void finishGame(){

    }
}
