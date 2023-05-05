package com.svalero.reactive.api.service;

import com.svalero.reactive.api.model.NumberInformation;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NumbersAPI {

    @GET("{number}/{type}?json")
    Observable<NumberInformation> getInformation(@Path("number") String number, @Path("type") String type);
    
}
