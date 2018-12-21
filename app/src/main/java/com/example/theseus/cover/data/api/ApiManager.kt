package com.example.theseus.cover.data.api

import com.example.theseus.cover.BuildConfig
import com.example.theseus.cover.data.api.model.GooglePlacesAPIResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiManager @Inject constructor(val googlePlacesService: GooglePlaceService): IApiManager {
    override fun fetchPlaces(input: String): Single<GooglePlacesAPIResponse>  = googlePlacesService.findPlaces(input,BuildConfig.API_KEY)
}