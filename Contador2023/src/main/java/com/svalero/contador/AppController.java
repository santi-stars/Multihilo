package com.svalero.contador;

import com.svalero.contador.task.TimerTask;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class AppController {

    public TextField tvText;
    public ProgressBar pbProgreso;
    public Label statusLabel;

    private TimerTask timerTask;

    public AppController() {

    }

    @FXML
    public void startCounter(Event event) {
        // Guardo en count el texto del textView
        int count = Integer.parseInt(tvText.getText());
        // Instancio la tarea y le paso por parámetro a count
        timerTask = new TimerTask(count);
        // Antes de lanzar la tarea asignamos un listener a la tarea para cuando haya algún cambio
        timerTask.stateProperty().addListener((observableValue, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                // Si el nuevo estado de la tarea es terminado... sale un alert con el siguiente texto
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Terminado");
                alert.setContentText("Enhorabuena! la descarga ha terminado correctamente");
                alert.show();
            } else if (newState == Worker.State.CANCELLED) {
                // Si el nuevo estado de la tarea es cancelado... sale un alert con el siguiente texto
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cancelado");
                alert.setContentText("Enhorabuena! la descarga ha cancelado correctamente");
                alert.show();
            }
        });

        pbProgreso.progressProperty().unbind(); // Desliga cualquier proceso asociado a la barra de progreso
        pbProgreso.progressProperty().bind(timerTask.progressProperty());   // Asocia la tarea a la barra de progreso
        // Asignamos un listener a la tarea para que vaya actualizando el valor en la etiqueta
        timerTask.valueProperty().addListener((observableValue, oldValue, newValue) ->
                statusLabel.setText(newValue.toString() + "%"));

        new Thread(timerTask).start();

    }

    @FXML
    public void stopCounter(Event event) {
        if (timerTask != null)
            timerTask.cancel();
    }

    /*
    @FXML
    public void startCounter(Event event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("startCounter");
        //alert.setContentText("blablablablablabla START");
        alert.setContentText(tvText.getText());
        alert.show();
    }

    @FXML
    public void stopCounter(Event event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("stopCounter");
        //alert.setContentText("blablablablablabla STOP");
        alert.setContentText(tvText.getText());
        alert.show();
    }
    */
}
