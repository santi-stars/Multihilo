package com.svalero.reactive;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class App {
       
        public static void main(String[] args) {
                // Creamos un observable de Strings con 3 argumentos
                Observable<String> movies = Observable.just("As Bestas", "Cinco Lobitos", "Alcatraz");
                // Creamos el consumidor que todo lo que recibe lo imprime
                Consumer<String> netflixUser = (x) -> System.out.println(x.toUpperCase());
                // Subscribimos el consumidor al observable
                movies.subscribe(netflixUser);
        }
}               // EJECUTAR:    mvn clean package
                //              java -jar target/rx-example-1.0-SNAPSHOT-jar-with-dependencies.jar