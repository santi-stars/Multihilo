package com.sanvalero.hiloslocos;

public class HilosLocos {

    public static void main(String[] args) {
        Hilos hiloLoco = new Hilos();
        hiloLoco.start();
        try {
            hiloLoco.join(); // Espera a que acabe el hilo antes de seguir con el siguiente proceso
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

        HiloRun hiloRun = new HiloRun();
        Thread hiloThreadRun = new Thread(hiloRun);
        hiloThreadRun.start();
    }

}
