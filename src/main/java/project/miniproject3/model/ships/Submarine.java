package project.miniproject3.model.ships;
import javafx.scene.Group;

/**
 * Represents a Submarine ship in the game, which occupies 3 cells on the game board.
 * The submarine inherits from the abstract class AShip and defines its span as 3.
 * The orientation (horizontal or vertical) is passed during initialization.
 */
public class Submarine extends AShip {

    /**
     * Constructs a Submarine ship with the specified shape and orientation.
     *
     * @param shape the JavaFX shape (Group) that visually represents the submarine
     * @param orientation true if the submarine is horizontal, false if vertical
     */
    public Submarine(Group shape, boolean orientation) {
        super(shape, 3, orientation);
    }
}
