package com.svalero.multihilos;

public class Main {
    public static void main(String[] args) {

        CountThread countThread = new CountThread();
        Thread count1 = new Thread(countThread);
        count1.start();

        try {
            count1.join();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        CountThread countThread2 = new CountThread();

        Thread count2 = new Thread(countThread2);
        count2.start();

        try {
            count2.join();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

        System.out.println("fin");
    }
}
