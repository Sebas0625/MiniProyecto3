package project.miniproject3.model;

import javafx.scene.Node;

import javafx.scene.shape.Polygon;

public class AShip implements IShip{
    private final Polygon shape;
    private final int span;
    private int impacts;
    private boolean horizontal;
    private boolean isSunken;

    public AShip(Polygon shape, int span, boolean horizontal){
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
    public void setHorizontal(boolean horizontal){
        this.horizontal = horizontal;
    }

    @Override
    public Boolean isHorizontal() {
        return horizontal;
    }
}
