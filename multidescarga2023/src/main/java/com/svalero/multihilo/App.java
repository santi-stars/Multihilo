package com.svalero.multihilo;

import com.svalero.multihilo.util.R;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(R.getUI("multidownload.fxml")); // Cargo la interfaz principal
        loader.setController(new AppController());  // Asigno el controlador
        ScrollPane vbox = loader.load();

        Scene scene = new Scene(vbox);  // Creo una escena
        stage.setScene(scene);          // La añado al Stage
        stage.show();                   // La muestro
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }
}

