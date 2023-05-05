package com.svalero.reactive.api.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.svalero.reactive.api.model.Change;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CodeService {
    static final String BASE_URL = "https://git.eclipse.org/r/";
    private GerritAPI gerritAPI;

    public CodeService() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder().setLenient().create();    // Procesa los Json

        Retrofit retrofit = new Retrofit.Builder()  // Con Retrofit nos conectamos a la BBDD
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // Para juntar retrofit con RxJava
                .build();

        this.gerritAPI = retrofit.create(GerritAPI.class);  // Se lo asignamos a la API de gerrit
    }

    public Observable<Change> getChanges(String status) {   // Creamos el observable de cambios en la API "Change"
        // Devuelve los cambios cargados
        return this.gerritAPI.loadChanges(status).flatMapIterable(change -> change).distinct();
        // Para pasar de un observable de listas de cambio "Observable<List<Change>>" que es lo que nos devuelve la API
        // a un observable de cambio "Observable<Change>" usamos ".flatMapIterable(change -> change).distinct();"
    }
}
