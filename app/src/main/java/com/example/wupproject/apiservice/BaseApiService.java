package com.example.wupproject.apiservice;

import com.example.wupproject.model.Card;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BaseApiService {

    @GET("wupdigital/interview-api/master/api/v1/cards.json")
    Observable<List<Card>> cards(@Path("username") String username);
}
