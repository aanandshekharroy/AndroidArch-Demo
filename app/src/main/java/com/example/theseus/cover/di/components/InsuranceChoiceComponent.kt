package com.example.theseus.cover.di.components

import com.example.theseus.cover.di.modules.InsuranceChoiceModule
import com.example.theseus.cover.ui.insuranceChoice.InsuranceChoice
import dagger.Subcomponent

@Subcomponent(modules = [InsuranceChoiceModule::class])
interface InsuranceChoiceComponent {
    fun inject(insuranceChoice: InsuranceChoice)
}