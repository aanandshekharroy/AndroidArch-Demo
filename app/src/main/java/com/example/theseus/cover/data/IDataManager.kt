package com.example.theseus.cover.data

import com.example.theseus.cover.data.api.model.GooglePlacesAPIResponse
import io.reactivex.Single

interface IDataManager {
    fun fetchPlaces(value: String): Single<GooglePlacesAPIResponse>
}