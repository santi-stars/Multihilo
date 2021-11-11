package com.svalero.contador;

import com.svalero.contador.util.R;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    /**
     * Todo este toston para cargar la pantalla de JavaFX
     * Para EJECUTAR este programa, pestaña Maven/Plugins/javafx/javafxRun
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        AppController controller = new AppController();

        FXMLLoader loader = new FXMLLoader();   //Nuevo objeto para cargar Java FX
        loader.setLocation(R.getUI("contador.fxml"));   //Método para llamar la interfaz gráfica
        loader.setController(controller);   // Establecemos los controles con objeto de tipo de la clase que contiene los metodos para los botones
        VBox vBox = loader.load();

        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
