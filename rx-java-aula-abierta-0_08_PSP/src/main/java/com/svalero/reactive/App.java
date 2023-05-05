package com.svalero.reactive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class App {

    public static void main(String[] args) {
        /* EJEMPLO 1 */
        //Observable<String> news = Observable.just("Noticia Deportes 1", "Noticia Sociedad 1", "Noticia Deportes 2");
        //Consumer<String> user = (x) -> System.out.println(x);
        ////Consumer<String> user = System.out::println;
        ////news.subscribe(user);

        /* EJEMPLO 2 */
        //Observable<String> movies = Observable.just("Comedia 1", "Thriller 1", "Documental 1");
        //Consumer<String> user2 = System.out::println;
        //movies.map(movie -> movie.toUpperCase()).subscribe(user2);

        /* EJEMPLO 3 */
        //Observable<String> movies = Observable.just("Comedia 1", "Thriller 1", "Documental 1", "Comedia 2");
        //Consumer<String> user3 = System.out::println;
        //movies.map(movie -> movie.toUpperCase()).filter(movie -> movie.contains("COMEDIA")).subscribe(user3);

        /* EJEMPLO 4 */
        Observable<Integer> temperatures = Observable.just(25, 30, 32, 15, 10);
        //Consumer<Integer> city1 = (x) -> System.out.println("Soy la ciudad 1 y la temperatura es: " + x);
        //Consumer<Integer> city2 = (x) -> System.out.println("Soy la ciudad 2 y la temperatura es: " + x);
        //temperatures.filter(temperature -> temperature > 30).subscribe(city1);
        //temperatures.filter(temperature -> temperature < 20).subscribe(city2);

        /* EJEMPLO 5 */
        List<Instrument> musicStore = new ArrayList<Instrument>(Arrays.asList(
                new Instrument("piano", "Yamaha", 200),
                new Instrument("guitarra", "Fender", 500),
                new Instrument("guitarra", "Ibanez", 300)
        ));

        Observable<Instrument> observableMusicStore = Observable.fromIterable(musicStore);
        observableMusicStore
                .map(instrument -> instrument.getBrand())
                .map(brand -> brand.toUpperCase())
                .filter(brand -> brand.contains("FENDER"))
                .subscribe(System.out::println);

        /* EJEMPLO 6 */
        Consumer<Instrument> guitarPlayerWithNoMoney = (instrument) -> {
            if (instrument.getPrice() <= 300 && instrument.getType() == "guitarra")
                System.out.println("Comprar " + instrument.getBrand());
            else
                System.out.println("No comprar");
        };

        Consumer<Instrument> pianoPlayerWithMoney = (instrument) -> {
            if (instrument.getPrice() < 600 && instrument.getType() == "piano")
                System.out.println("Comprar " + instrument.getBrand());
            else
                System.out.println("No comprar");
        };

        observableMusicStore.subscribe(guitarPlayerWithNoMoney);
        observableMusicStore.subscribe(pianoPlayerWithMoney);
    }
}