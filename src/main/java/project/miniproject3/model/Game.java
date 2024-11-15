package project.miniproject3.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Game {
    private final ArrayList<ArrayList<Integer>> playerBoard = new ArrayList<>();
    private final ArrayList<ArrayList<Integer>> computerBoard = new ArrayList<>();

    public Game(){
        createBoards();
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
}
