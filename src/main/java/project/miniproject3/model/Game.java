package project.miniproject3.model;

import java.awt.*;
import java.lang.reflect.Array;
import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.Collection;

public class Game {
    private final ArrayList<AShip> ships = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> playerBoard;
    private ArrayList<ArrayList<Integer>> computerBoard;

    public Game(){
        createBoards();

        Carrier carrier = new Carrier(false);
        Submarine submarine1 = new Submarine(false);
        Submarine submarine2 = new Submarine(false);
        Destroyer destroyer1 = new Destroyer(false);
        Destroyer destroyer2 = new Destroyer(false);
        Destroyer destroyer3 = new Destroyer(false);
        Frigate frigate1 = new Frigate(false);
        Frigate frigate2 = new Frigate(false);
        Frigate frigate3 = new Frigate(false);
        Frigate frigate4 = new Frigate(false);

        ships.add(carrier);
        ships.add(submarine1);
        ships.add(submarine2);
        ships.add(destroyer1);
        ships.add(destroyer2);
        ships.add(destroyer3);
        ships.add(frigate1);
        ships.add(frigate2);
        ships.add(frigate3);
        ships.add(frigate4);
    }

    public void createBoards(){
        for (int i = 0; i < 10; i++){
            playerBoard.add(new ArrayList<>());
            computerBoard.add(new ArrayList<>());
            for (int j = 0; j < 10; j++){
                playerBoard.get(i).add(0);
                computerBoard.get(i).add(0);
            }
        }
    }

    public ArrayList<AShip> getShips(){
        return ships;
    }
}
