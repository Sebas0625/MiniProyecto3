package project.miniproject3.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class Ships {
    private void Aircraft() {
        // Portaaviones
        Polygon portaaviones = new Polygon();
        portaaviones.getPoints().addAll(
                0.0,3.0
                ,3.0,0.0
                ,7.0,0.0
                ,7.0,7.0
                ,10.0,15.0
                ,10.0,30.0
                ,6.0,35.0
                ,6.0,40.0
                ,0.0,40.0
        );
        portaaviones.setFill(Color.DARKGRAY);

        // Agregar detalles al portaaviones
        Rectangle cubiertaPortaaviones = new Rectangle(3, 5, 4, 30);
        cubiertaPortaaviones.setFill(Color.LIGHTGRAY);

        // Submarino
        Polygon submarino = new Polygon();
        submarino.getPoints().addAll(
                0.0, 0.0,
                10.0, 0.0,
                10.0, 30.0,
                5.0, 30.0,
                0.0, 25.0
        );
        submarino.setFill(Color.DARKBLUE);

        // Destructor
        Polygon destructor = new Polygon();
        destructor.getPoints().addAll(
                0.0, 0.0,
                10.0, 0.0,
                10.0, 20.0,
                5.0, 20.0,
                0.0, 15.0
        );
        destructor.setFill(Color.DARKGREEN);

        // Fragata
        Polygon fragata = new Polygon();
        fragata.getPoints().addAll(
                0.0, 0.0,
                10.0, 0.0,
                10.0, 10.0,
                5.0, 10.0,
                0.0, 5.0
        );
        fragata.setFill(Color.DARKRED);

        // Posicionar los barcos en la escena
        portaaviones.setLayoutX(50);
        portaaviones.setLayoutY(50);
        cubiertaPortaaviones.setLayoutX(50);
        cubiertaPortaaviones.setLayoutY(50);

        submarino.setLayoutX(100);
        submarino.setLayoutY(50);

        destructor.setLayoutX(150);
        destructor.setLayoutY(50);

        fragata.setLayoutX(200);
        fragata.setLayoutY(50);

    }
}
