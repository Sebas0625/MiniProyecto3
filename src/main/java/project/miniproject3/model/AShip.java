package project.miniproject3.model;

import java.awt.*;

public class AShip implements IShip{
    private Polygon shape;
    private int span;
    private int impacts;
    private boolean isSunken;

    public AShip(Polygon shape, int span){
        this.shape = shape;
        this.span = span;
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
}
