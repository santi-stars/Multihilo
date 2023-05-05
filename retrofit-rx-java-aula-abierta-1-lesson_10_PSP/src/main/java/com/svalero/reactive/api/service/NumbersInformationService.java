package com.svalero.reactive.api.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.svalero.reactive.api.model.NumberInformation;
import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NumbersInformationService {

    static final String BASE_URL = "http://numbersapi.com";
    private NumbersAPI numbersAPI;

    public NumbersInformationService() {
        // Creamos un parseador de JSON
        Gson gsonParser = new GsonBuilder()
                .setLenient()
                .create();
        // Construye un cliente de Retrofit con la URL Base, añadimos parseador Json y utilizamos un
        // adaptador de RxJava que hemos añadido en el Pom
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gsonParser))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        // Conectamos el cliente a la API
        this.numbersAPI = retrofit.create(NumbersAPI.class);
    }

    /**
     * Devolvemos un observable de tipo texto
     * @param number
     * @param type
     * @return
     */
    public Observable<String> getInformation(String number, String type) {
        return this.numbersAPI.getInformation(number, type).map(NumberInformation::getText);
    }

}
