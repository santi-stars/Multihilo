package com.svalero.reactive.api;

import com.svalero.reactive.api.service.NumbersInformationService;

import io.reactivex.functions.Consumer;

public class App {

        public static void main(String[] args) {
                NumbersInformationService numbersInformationService = new NumbersInformationService();
                Consumer<String> primeFan = (informationText) -> {
                        if (informationText.contains("prime")){
                                System.out.println("Fan Primos: Número primo encontrado.");
                        }
                };

                Consumer<String> standard = (informationText) -> {
                        System.out.println("Usuario Standard: " + informationText);
                };

                numbersInformationService.getInformation("5", "math").subscribe(primeFan);
                numbersInformationService.getInformation("5", "math").subscribe(standard);
        }
}