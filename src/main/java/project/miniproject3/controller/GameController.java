package project.miniproject3.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import project.miniproject3.model.Game;
import project.miniproject3.view.GameStage;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    GridPane playerBoard;
    @FXML
    GridPane machineBoard;
    Game game;

    Queue<String>[] MQueueArray;
    Queue<String>[] PQueueArray;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        game = new Game();
        MQueueArray = new Queue[4];
        for (int i = 0; i < MQueueArray.length; i++) {
            MQueueArray[i] = new LinkedList<>();
        }
        PQueueArray = new Queue[4];
        for (int i = 0; i < PQueueArray.length; i++) {
            PQueueArray[i] = new LinkedList<>();
        }

        saveMachinePositions();
        printMachinePositions();
        savePlayerPositions();
        printPlayerPositions();
        machineBoard.setOnMouseClicked(event -> {
            double x = event.getX();
            double y = event.getY();

            int col = (int) (x / (machineBoard.getWidth() / machineBoard.getColumnCount()));
            int row = (int) (y / (machineBoard.getHeight() / machineBoard.getRowCount()));

            
        });
    }

    @FXML
    void handleExitButton(ActionEvent event) throws IOException {
        GameStage.deleteInstance();
    }
    void savePlayerPositions() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                //Carrier
                if(game.getPlayerMatrixNum(i,j)==4){
                    PQueueArray[0].add(String.valueOf(i));
                    PQueueArray[0].add(String.valueOf(j));
                    PQueueArray[0].add("|");
                }
                else if (game.getPlayerMatrixNum(i,j)==3) {
                    PQueueArray[1].add(String.valueOf(i));
                    PQueueArray[1].add(String.valueOf(j));
                    PQueueArray[1].add("|");

                }else if (game.getPlayerMatrixNum(i,j)==2) {
                    PQueueArray[2].add(String.valueOf(i));
                    PQueueArray[2].add(String.valueOf(j));
                    PQueueArray[2].add("|");

                }else if (game.getPlayerMatrixNum(i,j)==1) {
                    PQueueArray[3].add(String.valueOf(i));
                    PQueueArray[3].add(String.valueOf(j));
                    PQueueArray[3].add("|");
                }
            }
        }
    }
    void saveMachinePositions(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                //Carrier
                if(game.getMachineMatrixNum(i,j)==4){
                    MQueueArray[0].add(String.valueOf(i));
                    MQueueArray[0].add(String.valueOf(j));
                    MQueueArray[0].add("|");
                }
                else if (game.getMachineMatrixNum(i,j)==3) {
                    MQueueArray[1].add(String.valueOf(i));
                    MQueueArray[1].add(String.valueOf(j));
                    MQueueArray[1].add("|");

                }else if (game.getMachineMatrixNum(i,j)==2) {
                    MQueueArray[2].add(String.valueOf(i));
                    MQueueArray[2].add(String.valueOf(j));
                    MQueueArray[2].add("|");

                }else if (game.getMachineMatrixNum(i,j)==1) {
                    MQueueArray[3].add(String.valueOf(i));
                    MQueueArray[3].add(String.valueOf(j));
                    MQueueArray[3].add("|");
                }
            }
        }
    }

    void printMachinePositions(){
        System.out.println("Imprimiendo maquina");
        int aux=0;
        for (int i = 0; i < 4; i++) {
            aux= MQueueArray[i].size();
            for (int j = 0; j < aux; j++) {
                System.out.print(MQueueArray[i].poll());

            }
            System.out.println();
        }


    }
    void printPlayerPositions(){
        System.out.println("Imprimiendo player");
        int auxi =0;
        for (int i = 0; i < 4; i++) {
            auxi = PQueueArray[i].size();
            for (int j = 0; j < auxi; j++) {
                System.out.print(PQueueArray[i].poll());

            }
            System.out.println();
        }
    }


}
