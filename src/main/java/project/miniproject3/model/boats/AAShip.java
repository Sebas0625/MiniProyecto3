package project.miniproject3.model.boats;

public class AAShip {
    protected boolean direction; //True= vertical , Flase = horizontal
    protected int lenght;

    public AAShip() {
        lenght = 0;
        direction = false;
        System.out.println(lenght);
    }
    public void setVertical(){direction = true;}
    public void setHorizontal(){direction = false;}
    public int getLenght(){return lenght;}



}


