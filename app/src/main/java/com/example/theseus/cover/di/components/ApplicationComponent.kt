package com.example.theseus.cover.di.components

import android.app.Application
import com.example.theseus.cover.di.modules.ApplicationModule
import com.example.theseus.cover.di.modules.AutocompleteModule
import com.example.theseus.cover.di.modules.InsuranceChoiceModule
import com.example.theseus.cover.di.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ApplicationModule::class])
interface ApplicationComponent {
    fun inject(application: Application)
    fun autoCompleteComponent(autocompleteModule: AutocompleteModule):AutocompleteComponent
    fun insuranceComponent(insuranceChoiceModule: InsuranceChoiceModule):InsuranceChoiceComponent
}