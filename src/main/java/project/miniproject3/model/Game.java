package project.miniproject3.model;

import project.miniproject3.model.boats.Carrier;
import project.miniproject3.model.boats.Destroyers;
import project.miniproject3.model.boats.Submarine;

import java.util.Random;

public class Game {
    private Random rand;
    private GameMatrix humanMatrix;
    private GameMatrix machineMatrix;
    private int remainingCarrier;
    private int remainingSubmarine;
    private int remainingDestroyers;
    private int remainingFrigates;
    private Submarine[] submarines = new Submarine[2];
    private Carrier carrier;
    private Destroyers[] destroyers = new Destroyers[2];
    int machineLives;

    public void fillMachineMatrix() {
        int aux1;
        int aux2;
        int directionaux;
        boolean boolaux = false;

        //1 for carriers, 2 for submarines, 3 for destroyers and 4 for frigates

        aux1 = rand.nextInt(7);
        aux2 = rand.nextInt(7);
        System.out.println(aux1);
        System.out.println(aux2);
        directionaux = rand.nextInt(2);
        if (directionaux == 0) {
            for (int i = 0; i < carrier.getLenght(); i++) {
                machineMatrix.setNumber(aux1 + i, aux2, 1);
            }
        } else {
            for (int i = 0; i < carrier.getLenght(); i++) {
                machineMatrix.setNumber(aux1, aux2 + i,1);
            }
        }
        machineMatrix.printMatrix();
        //Submarinos
        for (int i = 0; i < 2; i++) {
            do {
                boolaux = false;
                aux1 = rand.nextInt(8);
                aux2 = rand.nextInt(8);
                System.out.println(aux1);
                System.out.println(aux2);
                directionaux = rand.nextInt(2);
                if (directionaux == 0) {
                    for (int f = 0; f < 3; f++) {
                        if (machineMatrix.getNumber(aux1 + f, aux2) == 1) {
                            boolaux = true;
                        }
                    }

                } else {
                    for (int l = 0; l < 3; l++) {
                        if (machineMatrix.getNumber(aux1, aux2 + i) == 1) {
                            boolaux = true;
                        }
                    }
                }
            } while (boolaux);
            if (directionaux == 0) {
                for (int f = 0; f < 3; f++) {
                    machineMatrix.setNumber(aux1+f, aux2 ,2);
                }
            } else {
                for (int l = 0; l < 3; l++) {
                    machineMatrix.setNumber(aux1, aux2 + l,2);
                }
            }
        }
        //Destructores
        for (int i = 0; i < 3; i++) {
            do {
                boolaux = false;
                aux1 = rand.nextInt(9);
                aux2 = rand.nextInt(9);
                System.out.println(aux1);
                System.out.println(aux2);
                directionaux = rand.nextInt(2);
                if (directionaux == 0) {
                    for (int f = 0; f < 2; f++) {
                        if (machineMatrix.getNumber(aux1 + f, aux2) == 1) {
                            boolaux = true;
                        }
                    }

                } else {
                    for (int l = 0; l < 2; l++) {
                        if (machineMatrix.getNumber(aux1, aux2 + i) == 1) {
                            boolaux = true;
                        }
                    }
                }
            } while (boolaux);
            if (directionaux == 0) {
                for (int f = 0; f < 2; f++) {
                    machineMatrix.setNumber(aux1+f, aux2,3 );
                }
            } else {
                for (int l = 0; l < 2; l++) {
                    machineMatrix.setNumber(aux1, aux2 + l,3);
                }
            }
        }
        //Fragatas
        for (int i = 0; i < 4; i++) {
            do {
                boolaux = false;
                aux1 = rand.nextInt(10);
                aux2 = rand.nextInt(10);
                System.out.println(aux1);
                System.out.println(aux2);
                directionaux = rand.nextInt(2);
                if (directionaux == 0) {
                        if (machineMatrix.getNumber(aux1 , aux2) == 1) {
                            boolaux = true;
                        }
                } else {
                        if (machineMatrix.getNumber(aux1, aux2)==1) {
                            boolaux = true;
                        }
                }
            } while (boolaux);
            if (directionaux == 0) {
                    machineMatrix.setNumber(aux1, aux2,4);
            } else {
                    machineMatrix.setNumber(aux1, aux2 ,4);
            }
        }

            machineMatrix.printMatrix();


    }

    public Game(){
        rand = new Random();
        carrier = new Carrier();
        machineMatrix = new GameMatrix();

    }


}

