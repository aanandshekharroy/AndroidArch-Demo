package com.example.theseus.cover.data.api

import com.example.theseus.cover.data.api.model.GooglePlacesAPIResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GooglePlaceService {
    @GET("maps/api/place/autocomplete/json")
    fun findPlaces(@Query("input")input: String,
                   @Query("key") apiKey: String,
                   @Query ("types") types: String ="address"):Single<GooglePlacesAPIResponse>
}