package project.miniproject3.model;

import javafx.scene.Node;

import javafx.scene.shape.Polygon;

public class AShip implements IShip{
    private final Polygon shape;
    private final int span;
    private int impacts;
    private boolean orientation;
    private boolean isSunken;

    public AShip(Polygon shape, int span, boolean orientation){
        this.shape = shape;
        this.span = span;
        this.orientation = orientation;
        impacts = 0;
        isSunken = false;
    }

    @Override
    public int getSpan() {
        return span;
    }

    @Override
    public Polygon getShape() {
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
    public void setOrientation(boolean orientation){
        this.orientation = orientation;
    }
}
