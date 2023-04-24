package com.svalero.multihilos;

public class Main {
    public static void main(String[] args) {
        CountThread countThread = new CountThread();
        countThread.start();
        CountThread countThread2 = new CountThread();
        countThread2.start();
        System.out.println("fin");
    }
}
