package project.miniproject3;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import project.miniproject3.view.WelcomeStage;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The main entry point for the MiniProject2 application.
 * This class extends the JavaFX Application class and is responsible
 * for launching the application and initializing the primary stage.
 */
public class Main extends Application {

    /**
     * The main method that serves as the entry point for the Java application.
     * It launches the JavaFX application by invoking the launch method.
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes the primary stage for the application.
     * This method is called after the application has been launched.
     *
     * @param primaryStage The primary stage for this application, onto which
     *                     the application scene can be set.
     * @throws IOException If an I/O error occurs while initializing the WelcomeStage.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        WelcomeStage.getInstance(); // Initialize the WelcomeStage instance

//        Pane root = new Pane();

//        Polygon aircraftExterior = new Polygon();
//        aircraftExterior.getPoints().addAll(0.0,3.0
//                ,3.0,0.0
//                ,7.0,0.0
//                ,7.0,7.0
//                ,10.0,15.0
//                ,10.0,30.0
//                ,6.0,35.0
//                ,6.0,40.0
//                ,0.0,40.0);
//        aircraftExterior.setLayoutX(100.0);
//        aircraftExterior.setLayoutY(100.0);
//
//        root.getChildren().addAll(aircraftExterior);



        // Agregar los barcos y detalles al panel
//        root.getChildren().addAll(portaaviones, cubiertaPortaaviones, submarino, destructor, fragata);
//
//        Scene scene = new Scene(root, 500, 400);
//        primaryStage.setTitle("Prueba de Polígonos");
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }
}
