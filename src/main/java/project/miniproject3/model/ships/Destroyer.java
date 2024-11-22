package project.miniproject3.model.ships;

import javafx.scene.Group;

/**
 * Represents a Destroyer ship in the game, extending the AShip class.
 * The Destroyer is a specific type of ship with a span of 2 cells.
 */
public class Destroyer extends AShip {

    /**
     * Constructs a Destroyer ship with the specified shape and orientation.
     * The Destroyer ship always has a span of 2 cells.
     *
     * @param shape the shape of the Destroyer ship, represented by a JavaFX Group object
     * @param orientation the orientation of the ship; true for horizontal, false for vertical
     */
    public Destroyer(Group shape, boolean orientation) {
        super(shape, 2, orientation); // A Destroyer always has a span of 2
    }
}
