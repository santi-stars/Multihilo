package com.svalero.multihilos;

import java.util.Random;

public class CountThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            try {
                // Thread.sleep(100);
                Thread.sleep(new Random().nextInt(10) * 8);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

}
