package project.miniproject3.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Polygon;
import project.miniproject3.model.*;
import project.miniproject3.view.GameStage;
import project.miniproject3.view.WelcomeStage;
import javafx.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class PositioningController {

    private final Game game = new Game();
    @FXML
    GridPane boardGrid;
    @FXML
    Polygon carrier;
    @FXML
    Polygon submarine1;
    @FXML
    Polygon submarine2;
    @FXML
    Polygon destroyer1;
    @FXML
    Polygon destroyer2;
    @FXML
    Polygon destroyer3;
    @FXML
    Polygon frigate1;
    @FXML
    Polygon frigate2;
    @FXML
    Polygon frigate3;
    @FXML
    Polygon frigate4;
    private ArrayList<AShip> ships = new ArrayList<>();

    @FXML
    void handleStartGame(ActionEvent event) throws IOException{
        // Inicializar game con la información del boardGrid;

        WelcomeStage.closeInstance();
        GameStage.getInstance();
    }

    @FXML
    public void initialize() {
        ships.add(new Carrier(carrier, false));
        ships.add(new Submarine(submarine1, false));
        ships.add(new Submarine(submarine2, false));
        ships.add(new Destroyer(destroyer1, false));
        ships.add(new Destroyer(destroyer2, false));
        ships.add(new Destroyer(destroyer3, true));
        ships.add(new Frigate(frigate1, false));
        ships.add(new Frigate(frigate2, false));
        ships.add(new Frigate(frigate3, false));
        ships.add(new Frigate(frigate4, false));

        for (int i = 0; i < 10; i++){
            ships.get(i).getShape().setOnDragDetected(this::onDragDetected);
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

    private void onDragDropped(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;

        if (db.hasString()) {
            Polygon draggedShip = (Polygon) event.getGestureSource();
            int col = (int) (event.getX() / (boardGrid.getWidth() / boardGrid.getColumnCount()));
            int row = (int) (event.getY() / (boardGrid.getHeight() / boardGrid.getRowCount()));

            int span = 0;
            boolean horizontal = false;

            for (AShip ship : ships){
                if (ship.getShape() == draggedShip){
                    span = ship.getSpan();
                    horizontal = ship.getOrientation();
                }
            }

            if (canPlaceShip(boardGrid, col, row, span, horizontal)) {
                boardGrid.getChildren().remove(draggedShip);

                GridPane.setColumnIndex(draggedShip, col);
                GridPane.setRowIndex(draggedShip, row);
                boardGrid.add(draggedShip, col, row, horizontal ? span : 1, horizontal ? 1 : span);

                success = true;
            } else {
                System.out.println("No se puede colocar el barco, posición ocupada o fuera de límites.");
            }
        }

        event.setDropCompleted(success);
        event.consume();
    }

    private boolean canPlaceShip(GridPane gridPane, int col, int row, int span, boolean horizontal) {
        for (int i = 0; i < span; i++) {
            int checkCol = horizontal ? col + i : col;
            int checkRow = horizontal ? row : row + i;

            if (checkCol >= gridPane.getColumnCount() || checkRow >= gridPane.getRowCount() || isCellOccupied(gridPane, checkCol, checkRow)) {
                return false;
            }
        }
        return true;
    }

    private boolean isCellOccupied(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            Integer nodeCol = GridPane.getColumnIndex(node);
            Integer nodeRow = GridPane.getRowIndex(node);

            if (nodeCol != null && nodeRow != null && nodeCol == col && nodeRow == row) {
                return true;
            }
        }
        return false;
    }
}
