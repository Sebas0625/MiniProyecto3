package project.miniproject3.model;

public class GameException extends Exception{
    public GameException(){
        super();
    }
    public GameException(String message){
        super(message);
    }

    public GameException(String message, Throwable cause){
        super(message, cause);
    }
}
