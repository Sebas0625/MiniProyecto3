package project.miniproject3.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Polygon;
import project.miniproject3.model.*;
import project.miniproject3.model.ships.*;
import project.miniproject3.view.GameStage;
import project.miniproject3.view.WelcomeStage;
import javafx.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class PositioningController {

    private final Game game = new Game();
    @FXML
    private GridPane boardGrid;
    @FXML
    private Polygon carrier;
    @FXML
    private Polygon submarine1;
    @FXML
    private Polygon submarine2;
    @FXML
    private Polygon destroyer1;
    @FXML
    private Polygon destroyer2;
    @FXML
    private Polygon destroyer3;
    @FXML
    private Polygon frigate1;
    @FXML
    private Polygon frigate2;
    @FXML
    private Polygon frigate3;
    @FXML
    private Polygon frigate4;
    private ArrayList<AShip> ships = new ArrayList<>();
    private SerializableFileHandler serializableFileHandler;

    @FXML
    void handleStartGame(ActionEvent event) throws IOException {
        fillPlayersMatrix();
        game.getPlayerMatrix().printMatrix();
        serializableFileHandler.serialize("./src/main/resources/project/miniproject3/saves/game-data.ser", game);
        WelcomeStage.closeInstance();
        GameStage.getInstance();
        GameStage.getInstance().getGameController().setGame(game);
    }

    @FXML
    public void initialize() {
        serializableFileHandler = new SerializableFileHandler();

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

        for (int i = 0; i < 10; i++) {
            Polygon ship = ships.get(i).getShape();
            ship.setOnDragDetected(this::onDragDetected);
            int finalI = i;
            EventHandler<KeyEvent> keyPressedHandler = keyEvent -> {
                if (keyEvent.getCode() == KeyCode.R) {
                    rotateShip(ships.get(finalI));
                }
            };
            ship.setOnMouseEntered(mouseEvent -> {
                ship.requestFocus();
                ship.addEventHandler(KeyEvent.KEY_PRESSED, keyPressedHandler);
            });
            ship.setOnMouseExited(mouseEvent -> {
                ship.removeEventHandler(KeyEvent.KEY_PRESSED, keyPressedHandler);
            });
        }

        boardGrid.setOnDragOver(this::onDragOver);
        boardGrid.setOnDragDropped(this::onDragDropped);
    }

    private void onDragDetected(MouseEvent event) {
        Polygon shape = (Polygon) event.getSource();
        Dragboard dragboard = shape.startDragAndDrop(TransferMode.MOVE);
        var content = new ClipboardContent();
        content.putString("Ship");
        dragboard.setContent(content);
        event.consume();
    }

    private void onDragOver(DragEvent event) {
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

            for (AShip ship : ships) {
                if (ship.getShape() == draggedShip) {
                    span = ship.getSpan();
                    horizontal = ship.isHorizontal();
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

            if (checkCol >= gridPane.getColumnCount() || checkRow >= gridPane.getRowCount() || isCellOccupied(checkCol, checkRow)) {
                return false;
            }
        }
        return true;
    }

    private boolean isCellOccupied(int col, int row) {
        for (Node child : boardGrid.getChildren()) {
            Integer childCol = GridPane.getColumnIndex(child);
            Integer childRow = GridPane.getRowIndex(child);

            if (childCol == null) childCol = 0;
            if (childRow == null) childRow = 0;

            int colSpan = GridPane.getColumnSpan(child) == null ? 1 : GridPane.getColumnSpan(child);
            int rowSpan = GridPane.getRowSpan(child) == null ? 1 : GridPane.getRowSpan(child);

            if (col >= childCol && col < childCol + colSpan &&
                    row >= childRow && row < childRow + rowSpan) {
                return true;
            }
        }
        return false;
    }

    public void fillPlayersMatrix(){
        for (Node ship : boardGrid.getChildren()){
            int row = GridPane.getRowIndex(ship) == null ? 0 : GridPane.getRowIndex(ship);
            int col = GridPane.getColumnIndex(ship) == null ? 0 : GridPane.getColumnIndex(ship);
            int colSpan = GridPane.getColumnSpan(ship) == null ? 1 : GridPane.getColumnSpan(ship);
            int rowSpan = GridPane.getRowSpan(ship) == null ? 1 : GridPane.getRowSpan(ship);
            int type = colSpan == 1 ? rowSpan : colSpan;
            for (int i = row; i < row + rowSpan; i++){
                for (int j = col; j < col + colSpan; j++){
                    game.getPlayerMatrix().setNumber(i, j, type);
                }
            }
        }
    }

    private void rotateShip(AShip ship) {
        Polygon shape = ship.getShape();
        int col = GridPane.getColumnIndex(shape) == null ? 0 : GridPane.getColumnIndex(shape);
        int row = GridPane.getRowIndex(shape) == null ? 0 : GridPane.getRowIndex(shape);
        boolean isHorizontal = ship.isHorizontal();
        int span = ship.getSpan();

        boolean newIsHorizontal = !isHorizontal;

        int newColSpan = newIsHorizontal ? span : 1;
        int newRowSpan = newIsHorizontal ? 1 : span;

        if (col + newColSpan < boardGrid.getColumnCount() &&
                row + newRowSpan < boardGrid.getRowCount() &&
                isCellOccupied(col, row)) {

            GridPane.setColumnSpan(shape, newColSpan);
            GridPane.setRowSpan(shape, newRowSpan);
            ship.setHorizontal(newIsHorizontal);
            shape.setRotate(newIsHorizontal ? 0 : 90);
        } else {
            System.out.println("No se puede rotar el barco aquí.");
        }
    }
}
