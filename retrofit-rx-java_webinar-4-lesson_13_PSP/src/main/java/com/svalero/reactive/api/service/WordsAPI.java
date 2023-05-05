package com.svalero.reactive.api.service;

import java.util.List;

import com.svalero.reactive.api.model.WordInformation;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WordsAPI {

    @GET("api/v2/entries/en/{word}")
    Observable<List<WordInformation>> getInformation(@Path("word") String word);
}
