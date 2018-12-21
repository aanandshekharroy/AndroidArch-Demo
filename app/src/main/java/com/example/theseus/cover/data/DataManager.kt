package com.example.theseus.cover.data

import com.example.theseus.cover.data.api.IApiManager
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject constructor(val mApiManager: IApiManager): IDataManager {
    override fun fetchPlaces(value: String) = mApiManager.fetchPlaces(value).subscribeOn(Schedulers.io())
}