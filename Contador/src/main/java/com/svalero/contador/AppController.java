package com.svalero.contador;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class AppController {

    public TextField tfValue;

    @FXML
    public ProgressBar myProgressBar;
    public Progress progress;

    public AppController() {

    }

    @FXML
    public void startCounter(Event event) {

        // Codigo para mostrar una alerta
        /*
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("startCounter");
        alert.setContentText("Contenido de la alerta");
        alert.show();
        */
        progress = new Progress(myProgressBar);
        progress.start();
    }

    @FXML
    public void stopCounter(Event event) {
        // Codigo para mostrar una alerta
        /*
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("stopCounter");
        alert.setContentText(tfValue.getText());
        alert.show();
         */
        progress.stop();
    }

}
