package project.miniproject3.model.ships;

import javafx.scene.Group;

/**
 * Represents a ship in the game, implementing the IShip interface.
 * This class encapsulates the shape and span of the ship, as well as whether it is placed horizontally or vertically.
 */
public class AShip implements IShip {

    /** The shape of the ship, represented as a Group of JavaFX shapes (e.g., polygons). */
    private final Group shape;

    /** The span or size of the ship, i.e., how many cells it occupies. */
    private final int span;

    /** Indicates whether the ship is placed horizontally. */
    private boolean horizontal;

    /**
     * Constructs an AShip object with the specified shape, span, and orientation.
     *
     * @param shape the shape of the ship, represented by a JavaFX Group object
     * @param span the span or size of the ship (number of cells it occupies)
     * @param horizontal the orientation of the ship; true for horizontal, false for vertical
     */
    public AShip(Group shape, int span, boolean horizontal) {
        this.shape = shape;
        this.span = span;
        this.horizontal = horizontal;
    }

    /**
     * Gets the span (size) of the ship.
     *
     * @return the span of the ship
     */
    @Override
    public int getSpan() {
        return span;
    }

    /**
     * Gets the shape of the ship.
     *
     * @return the Group object representing the shape of the ship
     */
    @Override
    public Group getShape() {
        return shape;
    }

    /**
     * Sets the horizontal orientation of the ship.
     *
     * @param horizontal true for horizontal orientation, false for vertical
     */
    @Override
    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    /**
     * Checks whether the ship is placed horizontally.
     *
     * @return true if the ship is horizontal, false otherwise
     */
    @Override
    public Boolean isHorizontal() {
        return horizontal;
    }
}
