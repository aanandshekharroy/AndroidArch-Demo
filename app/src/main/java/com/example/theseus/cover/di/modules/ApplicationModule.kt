package com.example.theseus.cover.di.modules

import com.example.theseus.cover.data.DataManager
import com.example.theseus.cover.data.IDataManager
import com.example.theseus.cover.data.api.ApiManager
import com.example.theseus.cover.data.api.IApiManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {
    @Provides
    @Singleton
    fun providesDataManager(mDataManager: DataManager) = mDataManager as IDataManager

    @Provides
    @Singleton
    fun providesApiManager(mApiManager: ApiManager) = mApiManager as IApiManager


}