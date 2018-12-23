package com.example.theseus.cover.di.components

import com.example.theseus.cover.di.modules.AutocompleteModule
import com.example.theseus.cover.ui.autocomplete.ChooseLocation
import dagger.Subcomponent

@Subcomponent(modules = [AutocompleteModule::class])
interface AutocompleteComponent {
    fun inject(chooseLocation: ChooseLocation)
}