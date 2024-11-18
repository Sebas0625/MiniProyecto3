package project.miniproject3.controller;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
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
    private GridPane shipsGrid;
    private final ArrayList<AShip> ships = new ArrayList<>();
    private SerializableFileHandler serializableFileHandler;

    @FXML
    void handleStartGame(ActionEvent event) throws IOException {
        fillPlayersMatrix();
        System.out.println("Mostrando matriz del jugador:");
        game.getPlayerMatrix().printMatrix();
        System.out.println("Mostrando matriz de la máquina:");
        game.getMachineMatrix().printMatrix();
        serializableFileHandler.serialize("./src/main/resources/project/miniproject3/saves/game-data.ser", game);
        WelcomeStage.closeInstance();
        GameStage.getInstance().getGameController().setGame(game);
    }

    @FXML
    public void initialize() {
        serializableFileHandler = new SerializableFileHandler();

        initializeShipsGrid();

        for (int i = 0; i < 10; i++) {
            Group ship = ships.get(i).getShape();
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
        Group shape = (Group) event.getSource();
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
            Group draggedShip = (Group) event.getGestureSource();
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

            if (checkCol >= gridPane.getColumnCount() || checkRow >= gridPane.getRowCount() || isCellOccupied(boardGrid, checkCol, checkRow)) {
                return false;
            }
        }
        return true;
    }

    private boolean isCellOccupied(GridPane grid, int col, int row) {
        for (Node child : grid.getChildren()) {
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
            int colSpan = GridPane.getColumnSpan(ship) == null ? 0 : GridPane.getColumnSpan(ship);
            int rowSpan = GridPane.getRowSpan(ship) == null ? 0 : GridPane.getRowSpan(ship);
            int type = colSpan == 1 ? rowSpan : colSpan;
            for (int i = row; i < row + rowSpan; i++){
                for (int j = col; j < col + colSpan; j++){
                    game.getPlayerMatrix().setNumber(i, j, type);
                }
            }
        }
    }

    private void rotateShip(AShip ship) {
        Group shape = ship.getShape();
        int col = GridPane.getColumnIndex(shape) == null ? 0 : GridPane.getColumnIndex(shape);
        int row = GridPane.getRowIndex(shape) == null ? 0 : GridPane.getRowIndex(shape);
        boolean isHorizontal = ship.isHorizontal();
        int span = ship.getSpan();

        boolean newIsHorizontal = !isHorizontal;

        int newColSpan = newIsHorizontal ? span : 1;
        int newRowSpan = newIsHorizontal ? 1 : span;

        if (col + newColSpan < boardGrid.getColumnCount() &&
                row + newRowSpan < boardGrid.getRowCount() &&
                isCellOccupied(boardGrid, col, row)) {

            GridPane.setColumnSpan(shape, newColSpan);
            GridPane.setRowSpan(shape, newRowSpan);
            ship.setHorizontal(newIsHorizontal);
            shape.setRotate(newIsHorizontal ? 0 : 90);
        } else {
            System.out.println("No se puede rotar el barco aquí.");
        }
    }

    public void initializeShipsGrid(){
        Carrier carrier = new Carrier(Ships.carrier(), false);
        Submarine submarine1 = new Submarine(Ships.submarine(), false);
        Submarine submarine2 = new Submarine(Ships.submarine(), false);
        Destroyer destroyer1 = new Destroyer(Ships.destroyer(), false);
        Destroyer destroyer2 = new Destroyer(Ships.destroyer(), false);
        Destroyer destroyer3 = new Destroyer(Ships.destroyer(), true);
        Frigate frigate1 = new Frigate(Ships.frigate(), false);
        Frigate frigate2 = new Frigate(Ships.frigate(), false);
        Frigate frigate3 = new Frigate(Ships.frigate(), false);
        Frigate frigate4 = new Frigate(Ships.frigate(), false);

        ships.add(carrier);
        ships.add(submarine1);
        ships.add(submarine2);
        ships.add(destroyer1);
        ships.add(destroyer2);
        ships.add(destroyer3);
        ships.add(frigate1);
        ships.add(frigate2);
        ships.add(frigate3);
        ships.add(frigate4);

        shipsGrid.add(carrier.getShape(), 0, 0, 1, 4);
        shipsGrid.add(submarine1.getShape(), 1, 0, 1, 3);
        shipsGrid.add(submarine2.getShape(), 2, 0, 1, 3);
        shipsGrid.add(destroyer1.getShape(), 3, 0, 1, 2);
        shipsGrid.add(destroyer2.getShape(), 4, 0, 1, 2);
        destroyer3.getShape().setRotate(90);
        shipsGrid.add(destroyer3.getShape(), 3, 2, 2, 1);
        shipsGrid.add(frigate1.getShape(), 1, 3);
        shipsGrid.add(frigate2.getShape(), 2, 3);
        shipsGrid.add(frigate3.getShape(), 3, 3);
        shipsGrid.add(frigate4.getShape(), 4, 3);
    }
}
