package com.example.theseus.cover.di.components

import com.example.theseus.cover.di.modules.ChooseLocationModule
import com.example.theseus.cover.ui.chooseLocation.ChooseLocation
import dagger.Subcomponent

@Subcomponent(modules = [ChooseLocationModule::class])
interface AutocompleteComponent {
    fun inject(chooseLocation: ChooseLocation)
}