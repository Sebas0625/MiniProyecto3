package project.miniproject3;

import javafx.scene.Group;
import javafx.scene.Scene;
import project.miniproject3.model.Ships;
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

        Group root = Ships.destructor();

        // Crea la escena y añade el grupo
        Scene scene = new Scene(root, 800, 600); // Cambia las dimensiones según lo necesites

        // Configura la ventana principal
        primaryStage.setTitle("Visualización de Figura desde FXML");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
