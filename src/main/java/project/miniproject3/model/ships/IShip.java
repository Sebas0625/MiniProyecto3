package project.miniproject3.model.ships;

import javafx.scene.Node;

public interface IShip {
    int getSpan();
    Node getShape();
    void setHorizontal(boolean horizontal);
    Boolean isHorizontal();
}
