package com.svalero.contador;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;

public class Progress extends Thread {

    private ProgressBar progressBar;

    public Progress(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    public void run() {
            for (double i = 0; i < 100; i++) {
                System.out.println(i / 100);
                double finalI = i;
                Platform.runLater(() -> {
                progressBar.setProgress(finalI / 100);
        });
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

    }
}
