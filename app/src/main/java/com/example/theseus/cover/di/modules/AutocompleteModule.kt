package com.example.theseus.cover.di.modules

import com.example.theseus.cover.ui.autocomplete.AddressListAdapter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class AutocompleteModule {
    @Provides
    fun providesCompositeDisposable() = CompositeDisposable()

    @Provides
    fun provideAdapter() = AddressListAdapter()
}