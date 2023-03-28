package com.svalero.multihilo;

import com.svalero.multihilo.util.R;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


public class AppController {

    public TextField tfUrl;
    public Button btDownload;
    public TabPane tpDownloads;

    public AppController() {

    }

    @FXML
    public void launchDownload(ActionEvent event) {

        String urlText = tfUrl.getText();   // Coje la URL del textField
        tfUrl.clear();                      // Limpia el textField
        tfUrl.requestFocus();               // Reclama el foco en el textField

        launchDownload(urlText);            // Ejecuta nuestro método launchDownload

    }

    private void launchDownload(String url) {

        try {
            FXMLLoader loader = new FXMLLoader();   // Crea un cargador
            loader.setLocation(R.getUI("download.fxml"));   // Asigna la interfaz de las pestañas
            // Crea un objeto de nuestra clase DownloadController y le pasa la URL
            DownloadController downloadController = new DownloadController(url);
            loader.setController(downloadController);   // Añade al cargador
            VBox downloadBox = loader.load();   // Lo carga en un VBox
            // Crea un nombre del archivo a raiz de la URL
            String filename = url.substring(url.lastIndexOf("/") + 1);
            // Crea una tab con el nombre anterior y con el VBox que hemos creado
            tpDownloads.getTabs().add(new Tab(filename, downloadBox));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    @FXML
    public void stopAllDownloads() {
    }

    @FXML
    public void readDLC() {
    }
}
