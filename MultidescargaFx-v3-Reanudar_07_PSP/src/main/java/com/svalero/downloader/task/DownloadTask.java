package com.svalero.downloader.task;

import com.svalero.downloader.controller.DownloadController;
import javafx.concurrent.Task;

//Quitar comentarios para visualizar tiempo con Libería de Apache Commons
//import org.apache.commons.lang3.time.StopWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
//Quitar comentarios para visualizar tiempo con Libería Estándar de Java
//import java.time.Duration;
//import java.time.Instant;
import java.util.concurrent.atomic.AtomicBoolean;

public class DownloadTask extends Task<Integer> {

    private URL url;
    private File file;

    private static final Logger logger = LogManager.getLogger(DownloadController.class);
    /* REANUDAR */
    private AtomicBoolean isDownloadPaused;

    public DownloadTask(String urlText, File file, AtomicBoolean isDownloadPaused) throws MalformedURLException {
        this.url = new URL(urlText);
        this.file = file;
        /* REANUDAR */
        this.isDownloadPaused = isDownloadPaused;
        System.out.println("Task");
        System.out.println(System.identityHashCode(this.isDownloadPaused));
    }

    @Override
    protected Integer call() throws Exception {
        logger.trace("Descarga " + url.toString() + " iniciada");
        updateMessage("Conectando con el servidor . . .");

        URLConnection urlConnection = url.openConnection();
        double fileSize = urlConnection.getContentLength();
        // Quitar comentario para limitar el tamaño de la descarga.
        /*double megaSize = fileSize / 1048576;
        if (megaSize > 10){
            logger.trace("Máximo tamaño de fichero alcanzado");
            throw new Exception("Max. size");
        }*/

        BufferedInputStream in = new BufferedInputStream(url.openStream());
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte dataBuffer[] = new byte[1024];
        int bytesRead;
        int totalRead = 0;
        double downloadProgress = 0;
        //Quitar comentarios para visualizar tiempo con Libería Estándar de Java
        /*Instant start = Instant.now();
        Instant current;
        float elapsedTime;*/
        
        //Quitar comentarios para visualizar tiempo con Libería de Apache Commons
        /*StopWatch watch = new StopWatch();
        watch.start();*/

        while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
            /* REANUDAR */
            if (this.isDownloadPaused.get()){
                synchronized(this){
                    this.wait();
                }
            }
            downloadProgress = ((double) totalRead / fileSize);

            updateProgress(downloadProgress, 1);
            updateMessage(downloadProgress * 100 + " %");

            //Quitar comentarios para visualizar tiempo con Libería Estándar de Java
            /*
            current = Instant.now();
            elapsedTime = Duration.between(start, current).toSeconds(); 
            updateMessage(Math.round(downloadProgress * 100) + " %\t\t\t\t" + Math.round(elapsedTime) + "sec.");
            */
            //Quitar comentarios para visualizar tiempo con Libería de Apache Commons
            //updateMessage(Math.round(downloadProgress * 100) + " %\t\t\t\t" + Math.round(watch.getTime()/1000)+ " sec.");
            
            
            // Comentar para acelerar la descarga.
            Thread.sleep(1);

            fileOutputStream.write(dataBuffer, 0, bytesRead);
            totalRead += bytesRead;

            if (isCancelled()) {
                logger.trace("Descarga " + url.toString() + " cancelada");
                return null;
            }
        }

        updateProgress(1, 1);
        updateMessage("100 %");

        logger.trace("Descarga " + url.toString() + " finalizada");
        
        return null;
    }
}
