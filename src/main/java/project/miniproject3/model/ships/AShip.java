package project.miniproject3.model.ships;

import javafx.scene.Group;
import javafx.scene.shape.Polygon;

public class AShip implements IShip {
    private final Group shape;
    private final int span;
    private int impacts;
    private boolean horizontal;
    private boolean isSunken;

    public AShip(Group shape, int span, boolean horizontal){
        this.shape = shape;
        this.span = span;
        this.horizontal = horizontal;
        impacts = 0;
        isSunken = false;
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
    public void impact(){
        impacts++;
        if (impacts == span){
            isSunken = true;
        }
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
