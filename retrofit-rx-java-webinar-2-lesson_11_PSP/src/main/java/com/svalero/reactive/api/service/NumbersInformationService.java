package com.svalero.reactive.api.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NumbersInformationService {
    static final String BASE_URL = "http://numbersapi.com";
    private NumbersAPI numbersAPI;

    public NumbersInformationService() {
        Gson gsonParser = new GsonBuilder()
                .setLenient()
                .create();
        
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonParser))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
        
        this.numbersAPI = retrofit.create(NumbersAPI.class);
    }

    public Observable<String> getInformation(String number, String type){
        return this.numbersAPI.getInformation(number, type).map(numberInformation -> numberInformation.getText());
    }
}
