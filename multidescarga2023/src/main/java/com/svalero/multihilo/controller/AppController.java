package com.svalero.multihilo.controller;

import com.svalero.multihilo.util.R;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class AppController {

    public TextField tfUrl;
    public Button btDownload;
    public Button btCancel;
    public Button btRegister;
    public TabPane tpDownloads;
    public TextArea txaRegister;

    private final File defaultFile;
    protected File file;
    protected Map<String, DownloadController> allDownloads;
    private int downloadCounter;
    public ScrollPane sp;

    public AppController() {
        downloadCounter = 0;

        defaultFile = new File("C:\\Users\\santi\\Desktop");
        //defaultFile = new File(file.getAbsolutePath());
        file = defaultFile;

        allDownloads = new HashMap<>();
    }

    /**
     * Llama al método launch() para lanzar la descarga con la URL del TextFiel
     *
     * @param event
     */
    @FXML
    public void launchDownload(ActionEvent event) {

        String urlText = tfUrl.getText();   // Coje la URL del textField
        tfUrl.clear();                      // Limpia el textField
        tfUrl.requestFocus();               // Reclama el foco en el textField

        launch(urlText, file);            // Ejecuta nuestro método launch que lanza la descarga

    }

    /**
     * Abre una pestaña nueva con la descarga y la renombra recortando el link
     *
     * @param url String con la URL de la descarga
     */
    private void launch(String url, File file) {

        try {
            /*
             * Añado un contador de descargas para renombrar los "filename" con un número delante
             * y así cuando añadimos las descargas al Map<>, si tienen la misma URL no
             * guarde todas las descargas con la misma KEY, porque si no, sobreescribe en
             * la misma posición del Map<> pensando que es la misma KEY y luego a la hora
             * de cancelar todas las descargas, solo cancela la última que se introdujo
             * */
            // downloadCounter++;
            String timestamp = ZonedDateTime.now()
                    .format(DateTimeFormatter.ofPattern("dd-MM-yyy_hh-mm-ss"));

            System.out.println("timestamp " + timestamp);        // 24.12.2017, 17:34:27
            // Crea un nombre del archivo a partir de la URL seleccionando el texto despues del último "/"
            String filename = url.substring(url.lastIndexOf("/") + 1);
            // Creamos un nuevo archivo con la ruta establecida o la configurada por nosotros,
            // más "filename" con el número de contador delante como nombre de archivo
            File newFile = new File(file.toString().concat("\\" + timestamp + "_" + filename));
            // Si el nombre tiene mas de 15 caracteres, recorta el nombre con ...
            if (filename.length() > 15)
                filename = (filename.substring(0, 15) + "...");
            System.out.println("file.toString() " + file);
            System.out.println("newFile.toString() " + newFile);
            System.out.println("file.getAbsolutePath() " + file.getAbsolutePath());

            FXMLLoader loader = new FXMLLoader();   // Crea un cargador
            loader.setLocation(R.getUI("download.fxml"));   // Asigna la interfaz de las pestañas
            // Crea un objeto de nuestra clase DownloadController y le pasa la URL
            DownloadController downloadController = new DownloadController(url, newFile);
            loader.setController(downloadController);   // Añade al cargador
            VBox downloadBox = loader.load();   // Lo carga en un VBox
            // Crea una tab con el nombre anterior y con el VBox que hemos creado
            tpDownloads.getTabs().add(new Tab(filename, downloadBox));
            // Añade la descarga de tipo "downloadController" al Map "allDownloads"
            allDownloads.put(newFile.toString(), downloadController);
            System.out.println("URL -" + url);
            System.out.println("FILENAME -" + filename);
            System.out.println("Descarga -" + downloadController);
            System.out.println(allDownloads.toString());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    /**
     * Para todas las descargas activas y cierra todas las pestañas
     */
    @FXML
    public void stopAllDownloads() {

        for (DownloadController downloadController : allDownloads.values()) {
            System.out.println("DC " + downloadController.toString());
            downloadController.stop();
        }

        allDownloads.clear();
        tpDownloads.getTabs().clear();

    }

    /**
     * Abre una ventana para elegir la ubicación del archivo DLC de descarga a leer
     * y llama al método launch() por cada link de descarga para lanzar la descarga
     */
    @FXML
    public void readDLC() {

        try {   // Abre una ventana para elegir la ubicación del archivo DLC de descarga
            FileChooser fileChooser = new FileChooser();
            File dlcFile = fileChooser.showOpenDialog(tfUrl.getScene().getWindow());
            if (dlcFile == null)
                return;
            Scanner reader = new Scanner(dlcFile);

            while (reader.hasNextLine()) {
                // Mientras haya una linea siguiente llama al método launch para lanzar cada descarga
                String data = reader.nextLine();
                System.out.println(data);
                launch(data, file);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Lee el archivo del registro de descargas y lo devuelve como String
     *
     * @return String con los LOG's de las descargas
     */
    private String readFile() {

        String texto = "";

        try {
            BufferedReader bf = new BufferedReader(new FileReader("C:\\Users\\santi\\IdeaProjects\\PSP\\multidescarga2023\\multidescargas.log"));
            String temp = "";
            String bfRead;

            while ((bfRead = bf.readLine()) != null) {
                temp = temp + bfRead;
            }
            texto = temp;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return texto;
    }

    /**
     * Abre un TextArea con Scroll y muestra los LOG con las descargas
     */
    @FXML
    public void viewLog() {

        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(R.getUI("registro.fxml"));
            txaRegister = loader.load();

            Scene scene = new Scene(txaRegister);
            stage.setScene(scene);
            stage.setTitle("Registro de descargas");
            stage.show();
            // Añado un listener para poner una barra de Scroll cuando haya cambios en el texto
            txaRegister.textProperty().addListener((ChangeListener<Object>) (observable, oldValue, newValue)
                    -> txaRegister.setScrollTop(Double.MIN_VALUE));
            txaRegister.setEditable(false); // Evita que se modifique el texto
            txaRegister.setWrapText(true);  // TRUE envuelve el texto que pasamos en setText, si no, lo muestra en una linea
            txaRegister.setText(readFile());// Añade al textArea el texto del archivo del LOG a través del método: readFile()
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Elije la ruta de descarga
     */
    @FXML
    public void chooseDownloadPath(ActionEvent event) {

        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setInitialDirectory(defaultFile);
        Stage stage = (Stage) sp.getScene().getWindow();
        file = dirChooser.showDialog(stage);

        if (file == null)
            file = defaultFile;

    }

}
