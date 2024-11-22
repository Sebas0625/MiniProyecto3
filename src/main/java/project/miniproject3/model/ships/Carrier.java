package project.miniproject3.model.ships;

import javafx.scene.Group;

/**
 * Represents a Carrier ship in the game, extending the AShip class.
 * The Carrier is a specific type of ship with a span of 4 cells.
 */
public class Carrier extends AShip {

    /**
     * Constructs a Carrier ship with the specified shape and orientation.
     * The Carrier ship always has a span of 4 cells.
     *
     * @param shape the shape of the Carrier ship, represented by a JavaFX Group object
     * @param orientation the orientation of the ship; true for horizontal, false for vertical
     */
    public Carrier(Group shape, boolean orientation) {
        super(shape, 4, orientation); // A Carrier always has a span of 4
    }
}
