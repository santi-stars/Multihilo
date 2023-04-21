package com.svalero.multihilo.controller;

import com.svalero.multihilo.task.DownloadTask;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class DownloadController implements Initializable {

    public Label lbStatus;
    public TextField tfUrl;
    public TextField setDeelayTime;
    public ProgressBar pbProgress;
    private DownloadTask downloadTask;
    private String urlText;
    private Stage stage;
    private File file;

    private static final Logger logger = LogManager.getLogger(DownloadController.class);

    public DownloadController(String urlText, File file) {
        logger.info("Descarga " + urlText + " creada");
        this.urlText = urlText;
        this.file = file;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tfUrl.setText(urlText);
    }

    @FXML
    public void start(ActionEvent event) {

        try {
            // Pausa el proceso de descarga durante los segundos que le indicamos en su TextVieW
            try {
                Thread.sleep(setTimeDownload() * 1000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }

            downloadTask = new DownloadTask(urlText, file);

            pbProgress.progressProperty().unbind();
            pbProgress.progressProperty().bind(downloadTask.progressProperty());

            downloadTask.stateProperty().addListener((observableValue, oldState, newState) -> {
                System.out.println(observableValue.toString());
                if (newState == Worker.State.SUCCEEDED) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Enhorabuena!");
                    alert.setContentText("La descarga ha terminado con éxito!");
                    alert.show();
                }
                if (newState == Worker.State.CANCELLED) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Aviso!");
                    alert.setContentText("La descarga ha sido cancelada");
                    alert.show();
                }
                /* else if (newState == Worker.State.FAILED) {    // Manda un mensaje si el tamaño es > de 10Mb
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("El tamaño de la descarga es superior al límte de 10Mb");
                    alert.show();
                }*/
            });

            downloadTask.messageProperty()
                    .addListener((observableValue, oldValue, newValue) -> lbStatus.setText(newValue));

            Thread.sleep(1);

            new Thread(downloadTask).start();   // Lanza nuestra clase task
        } catch (MalformedURLException murle) {
            murle.printStackTrace();
            logger.error("URL mal formada ", murle.fillInStackTrace());
        } catch (InterruptedException ie) {
            ie.printStackTrace();
            logger.error("Interrupted exception ", ie.fillInStackTrace());
        }
    }

    @FXML
    public void stop(ActionEvent event) {
        stop();
    }

    protected void stop() {

        if (downloadTask != null) {
            downloadTask.cancel();
            logger.trace("Descarga " + urlText + " detenida");

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Aviso!");
            alert.setContentText("¿Quieres eliminar el archivo?");
            alert.showAndWait();

            ButtonType result = alert.getResult();
            if (result == ButtonType.OK) {
                delete();
            }
        }

    }

    private void delete() {
        if (file != null) {
            file.delete();
        }
        logger.info("Archivo " + urlText + " borrado");
    }

    private int setTimeDownload() {
        if (setDeelayTime.getText().equals("")) {
            return 0;
        } else {
            int setDeelayTime = Integer.parseInt(this.setDeelayTime.getText());
            return setDeelayTime;
        }
    }

    public String getUrlText() {
        return urlText;
    }

}
