package project.miniproject3.model.ships;

import javafx.scene.Group;
import javafx.scene.shape.Polygon;

public class AShip implements IShip {
    private final Group shape;
    private final int span;
    private boolean horizontal;

    public AShip(Group shape, int span, boolean horizontal){
        this.shape = shape;
        this.span = span;
        this.horizontal = horizontal;
    }

    @Override
    public int getSpan() {
        return span;
    }

    @Override
    public Group getShape() {
        return shape;
    }

    @Override
    public void setHorizontal(boolean horizontal){
        this.horizontal = horizontal;
    }

    @Override
    public Boolean isHorizontal() {
        return horizontal;
    }
}
