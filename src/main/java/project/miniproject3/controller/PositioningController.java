package project.miniproject3.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Polygon;
import project.miniproject3.model.Game;
import project.miniproject3.view.GameStage;
import project.miniproject3.view.WelcomeStage;
import javafx.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class PositioningController {

    private final Game game = new Game();
    private ArrayList<Polygon> shapes = new ArrayList<>();
    @FXML
    GridPane boardGrid;
    @FXML
    GridPane shipsGrid;

    @FXML
    void handleStartGame(ActionEvent event) throws IOException{
        // Inicializar game con la información del boardGrid;

        WelcomeStage.closeInstance();
        GameStage.getInstance();
    }

    @FXML
    public void initialize() {
        int index = 0;
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 2; j++){
                Polygon shape = game.getShips().get(index).getShape();
                shipsGrid.add(shape, j, i);
                shape.setOnDragDetected(this::onDragDetected);
            }
        }

        boardGrid.setOnDragOver(this::onDragOver);
        boardGrid.setOnDragDropped(this::onDragDropped);
    }

    private void onDragDetected(MouseEvent event){
        Polygon shape = (Polygon) event.getSource();
        Dragboard dragboard = shape.startDragAndDrop(TransferMode.MOVE);
        var content = new ClipboardContent();
        content.putString("Ship");
        dragboard.setContent(content);
        event.consume();
    }

    private void onDragOver(DragEvent event){
        if (event.getGestureSource() != boardGrid && event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }

    private void onDragDropped(DragEvent event){
        Dragboard db = event.getDragboard();
        boolean success = false;

        if (db.hasString()) {
            Polygon draggedShip = (Polygon) event.getGestureSource();
            int col = (int) (event.getX() / (boardGrid.getWidth() / boardGrid.getColumnCount()));
            int row = (int) (event.getY() / (boardGrid.getHeight() / boardGrid.getRowCount()));

            if (col >= 0 && col < boardGrid.getColumnCount() && row >= 0 && row < boardGrid.getRowCount()) {
                boardGrid.getChildren().remove(draggedShip);

                boardGrid.add(draggedShip, col, row);
                success = true;
            } else {
                System.out.println("Posición fuera de los límites");
            }
        }

        event.setDropCompleted(success);
        event.consume();
    }
}
