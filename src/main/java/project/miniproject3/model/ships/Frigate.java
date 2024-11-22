package project.miniproject3.model.ships;

import javafx.scene.Group;

/**
 * Represents a Frigate ship in the game, extending the AShip class.
 * The Frigate is a specific type of ship with a span of 1 cell.
 */
public class Frigate extends AShip {

    /**
     * Constructs a Frigate ship with the specified shape and orientation.
     * The Frigate ship always has a span of 1 cell.
     *
     * @param shape the shape of the Frigate ship, represented by a JavaFX Group object
     * @param orientation the orientation of the ship; true for horizontal, false for vertical
     */
    public Frigate(Group shape, boolean orientation) {
        super(shape, 1, orientation); // A Frigate always has a span of 1
    }
}
