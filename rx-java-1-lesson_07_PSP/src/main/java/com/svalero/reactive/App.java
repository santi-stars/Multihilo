package com.svalero.reactive;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class App {
       
        public static void main(String[] args) {
                Observable<String> movies = Observable.just("As Bestas", "Cinco Lobitos", "Alcatraz");
                Consumer<String> netflixUser = (x) -> System.out.println(x.toUpperCase());
                movies.subscribe(netflixUser);
        }
}