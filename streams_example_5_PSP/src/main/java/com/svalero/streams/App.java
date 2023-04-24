package com.svalero.streams;

import java.util.ArrayList;
import java.util.List;
/*
 * Streams: Nueva funcionalidad añadida en Java 8
 * 
 */

public class App {
    public static void main(String[] args){
        Animal animal1 = new Animal("león", "Zaragoza", 2, 95);
        Animal animal2 = new Animal("tigre", "Madrid", 5, 55);
        Animal animal3 = new Animal("delfín", "Zaragoza", 3, 75);
        Animal animal4 = new Animal("tiburón", "Valencia", 4, 100);

        List<Animal> zoo = new ArrayList<>();
        zoo.add(animal1);
        zoo.add(animal2);
        zoo.add(animal3);
        zoo.add(animal4);

        // Una lista de los animales que proceden de Zaragoza
        List<Animal> animalesZaragoza = zoo.stream().filter(animal -> animal.getCity().equals("Zaragoza")).toList();
        System.out.println(animalesZaragoza);

        // La suma de todos los pesos de los animales
        long totalPeso = zoo.stream().mapToInt(animal -> animal.getWeight()).sum();
        System.out.println(totalPeso);
        
        // Combinación de los 2 ejemplos anteriores.
        long pesoZaragoza = zoo.stream().filter(animal -> animal.getCity().equals("Zaragoza")).mapToInt(animal -> animal.getWeight()).sum();
        System.out.println(pesoZaragoza);

        // La lista permanece igual
        System.out.println(zoo);
    }
}