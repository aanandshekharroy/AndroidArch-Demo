package com.example.theseus.cover

import android.app.Application
import com.example.theseus.cover.di.components.ApplicationComponent
import com.example.theseus.cover.di.components.DaggerApplicationComponent
import com.example.theseus.cover.di.modules.ApplicationModule

class CoverApplication:Application() {
    lateinit var mApplicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        mApplicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule()).build()
        mApplicationComponent.inject(this)
    }
}