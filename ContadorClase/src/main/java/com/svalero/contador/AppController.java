package com.svalero.contador;

import com.svalero.contador.task.TimerTask;
import javafx.concurrent.Worker;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class AppController {

    public TextField tfValue;
    public ProgressBar myProgressBar;
    public Label statusLabel;
    public TimerTask timerTask;

    public AppController() {

    }

    @FXML
    public void startCounter(Event event) {
        int count = Integer.parseInt(tfValue.getText());    //Añadimos a "count" el valor escrito en el TextField

        timerTask = new TimerTask(count);   //Creamos una nueva tarea y pasamos "count" por parámetro
        //Cada vez que haya un cambio en la tarea va a llamar a este método "changed"
        timerTask.stateProperty().addListener((observableValue, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {  //Si la tarea ha terminado, ejecuto un alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("La tarea ha terminado con éxito");
                alert.show();
            } else if (newState == Worker.State.CANCELLED) { //Si no, si se cancela la tarea, ejecuto otro alert
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("La tarea ha sido cancelada por el usuario con éxito");
                alert.show();
            }
        });

        myProgressBar.progressProperty().unbind(); // Con unbind desligo de cualquier otra tarea
        myProgressBar.progressProperty().bind(timerTask.progressProperty());
        // la barra de progreso queda ligada a lo que haga la tarea

        timerTask.valueProperty().addListener((observableValue, oldValue, newValue) ->
                statusLabel.setText(newValue.toString()));

        new Thread(timerTask).start();  //Creamos nuevo hilo y pasamos el TASK por parámetro
    }

    @FXML
    public void stopCounter(Event event) {
        // si la tarea no es NULL osea que está en marcha, la paramos con .cancel()
        if (timerTask != null)
            timerTask.cancel();
    }

}
