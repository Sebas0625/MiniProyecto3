package project.miniproject3.model.ships;

import javafx.scene.Node;

/**
 * Interface that defines the structure of a ship in the game.
 * A ship is characterized by its span, shape, and orientation.
 */
public interface IShip {

    /**
     * Returns the span of the ship, which is the number of cells the ship occupies on the board.
     *
     * @return the span (size) of the ship
     */
    int getSpan();

    /**
     * Returns the shape of the ship, represented by a JavaFX Node.
     * The shape can be used to render the ship on the game board.
     *
     * @return the shape of the ship
     */
    Node getShape();

    /**
     * Sets the orientation of the ship.
     * The ship can either be horizontal or vertical.
     *
     * @param horizontal true if the ship is horizontal, false if it is vertical
     */
    void setHorizontal(boolean horizontal);

    /**
     * Returns whether the ship is horizontal or not.
     *
     * @return true if the ship is horizontal, false if it is vertical
     */
    Boolean isHorizontal();
}
