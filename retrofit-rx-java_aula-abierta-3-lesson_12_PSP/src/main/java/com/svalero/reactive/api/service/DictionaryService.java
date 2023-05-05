package com.svalero.reactive.api.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.svalero.reactive.api.model.Definition;
import com.svalero.reactive.api.model.WordInformation;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DictionaryService {

    private String BASE_URL = "https://api.dictionaryapi.dev";
    private WordsAPI wordsAPI;

    public DictionaryService() {
    
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gsonParser = new GsonBuilder()
                .setLenient()
                .create();
        
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gsonParser))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

        this.wordsAPI = retrofit.create(WordsAPI.class);
    }

    public Observable<Definition> getInformation(String word){
        return this.wordsAPI.getInformation(word).flatMapIterable(wordInformation -> wordInformation)
            .map(wordInformation -> wordInformation.getMeanings()).flatMapIterable(meanings -> meanings)
            .map(meaning -> meaning.getDefinitions()).flatMapIterable(defintions -> defintions);
    }

}
