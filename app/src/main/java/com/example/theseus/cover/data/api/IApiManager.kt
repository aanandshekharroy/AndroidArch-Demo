package com.example.theseus.cover.data.api

import com.example.theseus.cover.data.api.model.GooglePlacesAPIResponse
import io.reactivex.Single

interface IApiManager {
    fun fetchPlaces(input: String): Single<GooglePlacesAPIResponse>
}