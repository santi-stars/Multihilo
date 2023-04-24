package com.svalero.downloader.controller;

import com.svalero.downloader.task.DownloadTask;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class DownloadController implements Initializable {

    public TextField tfUrl;
    public Label lbStatus;
    public ProgressBar pbProgress;
    private String urlText;
    private DownloadTask downloadTask;
    @FXML
    private Button btStop;
    private static final Logger logger = LogManager.getLogger(DownloadController.class);
    
    /* REANUDAR */
    private AtomicBoolean isDownloadPaused = new AtomicBoolean(false);

    public DownloadController(String urlText) {
        logger.info("Descarga " + urlText + " creada");
        this.urlText = urlText;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Quitar comentario para descarga desde fichero.
        /*
         * tfUrl.setText(urlText);
         * try {
         * String fileName = this.urlText.substring(this.urlText.lastIndexOf("/") + 1);
         * File file = new File(fileName);
         * file.createNewFile();
         * downloadTask = new DownloadTask(urlText, file);
         * 
         * pbProgress.progressProperty().unbind();
         * pbProgress.progressProperty().bind(downloadTask.progressProperty());
         * 
         * downloadTask.stateProperty().addListener((observableValue, oldState,
         * newState) -> {
         * System.out.println(observableValue.toString());
         * if (newState == Worker.State.SUCCEEDED) {
         * Alert alert = new Alert(Alert.AlertType.INFORMATION);
         * alert.setContentText("La descarga ha terminado");
         * alert.show();
         * }
         * });
         * 
         * downloadTask.messageProperty()
         * .addListener((observableValue, oldValue, newValue) ->
         * lbStatus.setText(newValue));
         * 
         * new Thread(downloadTask).start();
         * } catch (MalformedURLException murle) {
         * murle.printStackTrace();
         * logger.error("URL mal formada", murle.fillInStackTrace());
         * } catch (IOException e) {
         * System.out.println("An error occurred.");
         * e.printStackTrace();
         * }
         */
    }

    @FXML
    public void start(ActionEvent event) {
        try {

            // Comentar para descarga desde fichero.
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showSaveDialog(tfUrl.getScene().getWindow());
            if (file == null)
                return;
            
            System.out.println("Controller");
            System.out.println(System.identityHashCode(isDownloadPaused));

            /* REANUDAR */
            downloadTask = new DownloadTask(urlText, file, isDownloadPaused);

            pbProgress.progressProperty().unbind();
            pbProgress.progressProperty().bind(downloadTask.progressProperty());

            downloadTask.stateProperty().addListener((observableValue, oldState, newState) -> {
                System.out.println(observableValue.toString());
                if (newState == Worker.State.SUCCEEDED) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("La descarga ha terminado");
                    alert.show();
                } // Quitar comentario para limitar el tamaño de la descarga.
                /*
                 * else if (newState == Worker.State.FAILED){
                 * Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 * alert.
                 * setContentText("El tamaño de la descarga es superior al límite de 10 MB.");
                 * alert.show();
                 * }
                 */
            });

            downloadTask.messageProperty()
                    .addListener((observableValue, oldValue, newValue) -> lbStatus.setText(newValue));

            // Importante: Se crea el Thread.
            new Thread(downloadTask).start();
        } catch (MalformedURLException murle) {
            murle.printStackTrace();
            logger.error("URL mal formada", murle.fillInStackTrace());
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /* REANUDAR */
    @FXML
    public void stop(ActionEvent event) {
        pauseResume();
    }
    /* REANUDAR */
    public void pauseResume() {
        /*if (downloadTask != null)
         downloadTask.cancel();*/
        if (isDownloadPaused.get()){
            btStop.setText("Pause");
            isDownloadPaused.set(false);
            synchronized (downloadTask){
                downloadTask.notify();
            }
        }else{
            btStop.setText("Resume");
            isDownloadPaused.set(true);
        }
        
    }

    public String getUrlText() {
        return urlText;
    }
}
