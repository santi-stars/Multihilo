package com.svalero.streams;

import java.util.ArrayList;
import java.util.List;
/*
 * Streams: Nueva funcionalidad añadida en Java 8
 *
 */

public class App {
    public static void main(String[] args) {
        Instrument piano1 = new Instrument("Piano", "Yamaha", 500);
        Instrument piano2 = new Instrument("Piano", "Kurzweil", 800);
        Instrument piano3 = new Instrument("Piano", "Casio", 150);
        Instrument guitar1 = new Instrument("Guitarra", "Fender", 350);
        Instrument guitar2 = new Instrument("Guitarra", "Yamaha", 200);
        Instrument guitar3 = new Instrument("Guitarra", "Gibson", 550);
        Instrument drums1 = new Instrument("Batería", "Yamaha", 1000);

        List<Instrument> musicStore = new ArrayList<>();
        musicStore.add(piano1);
        musicStore.add(piano2);
        musicStore.add(piano3);
        musicStore.add(guitar1);
        musicStore.add(guitar2);
        musicStore.add(guitar3);
        musicStore.add(drums1);

        // Pianos de la marca Yamaha.
        List<Instrument> pianosYamaha = musicStore.stream()
                .filter(instrument -> instrument.getBrand()
                        .equalsIgnoreCase("Yamaha"))
                .filter(instrument -> instrument.getType().equalsIgnoreCase("Piano")).toList();

        System.out.println(pianosYamaha);

        // Precio total de los instrumentos de la marca Yamaha.
        long totalYamahaPrice = musicStore.stream()
                .filter(instrument -> instrument.getBrand().equalsIgnoreCase("Yamaha"))
                .mapToInt(Instrument::getPrice).sum();

        System.out.println(totalYamahaPrice);

        // Número de instrumentos distintos (".distinct()") en la tienda.
        long numDifferentInstruments = musicStore.stream().map(Instrument::getType).distinct().count();
        System.out.println(numDifferentInstruments);

        // La lista permanece igual
        System.out.println(musicStore);
    }
}