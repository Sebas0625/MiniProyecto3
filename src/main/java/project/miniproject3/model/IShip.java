package project.miniproject3.model;

import javafx.scene.Node;

public interface IShip {
    int getSpan();
    Node getShape();
    void impact();
    void setOrientation(boolean orientation);
}
