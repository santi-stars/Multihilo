package com.svalero.downloader.controller;

import com.svalero.downloader.util.R;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AppController {

    public TextField tfUrl;
    public Button btDownload;
    public TabPane tpDownloads;

    private Map<String, DownloadController> allDownloads;

    public AppController() {
        allDownloads = new HashMap<>();
    }

    @FXML
    public void launchDownload(ActionEvent event) {
        String urlText = tfUrl.getText();
        tfUrl.clear();
        tfUrl.requestFocus();
        launch(urlText);
    }
    
    // Quitar comentario para descarga desde fichero.
    /*@FXML
    public void launchFileDownload(ActionEvent event) {
        String urlText = tfUrl.getText();
        tfUrl.clear();
        tfUrl.requestFocus();
        readDLC();
    }*/

    private void launch(String url) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(R.getUI("download.fxml"));

            DownloadController downloadController = new DownloadController(url);
            loader.setController(downloadController);
            VBox downloadBox = loader.load();

            String filename = url.substring(url.lastIndexOf("/") + 1);
            tpDownloads.getTabs().add(new Tab(filename, downloadBox));

            allDownloads.put(url, downloadController);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void stopAllDownloads() {
        for (DownloadController downloadController : allDownloads.values())
            downloadController.pauseResume();
    }

    @FXML
    public void readDLC() {
        
        // Quitar comentario para descarga desde fichero.
        /*  try {
            // Quitar comentario para descarga desde fichero "dlc.txt".
            //File dlcFile = new File("dlc.txt");

            // Quitar comentario para descarga desde el fichero que el usuario seleccione.
            FileChooser fileChooser = new FileChooser();
            File dlcFile = fileChooser.showOpenDialog(tfUrl.getScene().getWindow());
            if (dlcFile == null)
                return;
            
            // Leer fichero
            Scanner reader = new Scanner(dlcFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                System.out.println(data);
                // Lanzar controlador y descarga
                launch(data);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Se ha producido un error");
            e.printStackTrace();
        }*/
    }
}
