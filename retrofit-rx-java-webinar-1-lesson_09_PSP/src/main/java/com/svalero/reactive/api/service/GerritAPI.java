package com.svalero.reactive.api.service;

import java.util.List;

import com.svalero.reactive.api.model.Change;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface GerritAPI {

    @GET("changes/")
    Observable<List<Change>> loadChanges(@Query("q") String status);
}
