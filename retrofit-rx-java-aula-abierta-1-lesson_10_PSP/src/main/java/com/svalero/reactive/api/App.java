package com.svalero.reactive.api;

import com.svalero.reactive.api.service.NumbersInformationService;

import io.reactivex.functions.Consumer;

public class App {

    public static void main(String[] args) {
        NumbersInformationService numbersInformationService = new NumbersInformationService();
        // Crea los consumidores u OBSERVADORES
        Consumer<String> primeFan = (informationText) -> {
            if (informationText.contains("prime")) {
                System.out.println("Fan Primos: Número primo encontrado.");
            }
        };
        // Crea los consumidores u OBSERVADORES
        Consumer<String> standard = (informationText) -> {
            System.out.println("Usuario Standard: " + informationText);
        };
        // Lanza el OBSERVABLE y SUBSCRIBE a cada uno de los OBSERVADORES
        numbersInformationService.getInformation("6", "math").subscribe(primeFan);
        numbersInformationService.getInformation("6", "math").subscribe(standard);
    }
}