package com.example.theseus.cover.di.modules

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import com.example.theseus.cover.di.qualifiers.IsFetching
import com.example.theseus.cover.di.qualifiers.IsNetworkCallSuccessful
import com.example.theseus.cover.ui.chooseLocation.AddressListAdapter
import com.example.theseus.cover.ui.chooseLocation.ChooseLocationViewModel
import com.example.theseus.cover.ui.chooseLocation.ChooseLocationViewModelFactory
import com.example.theseus.cover.ui.chooseLocation.Places
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ChooseLocationModule(val fragment: Fragment) {
    @Provides
    fun providesCompositeDisposable() = CompositeDisposable()

    @Provides
    fun provideAdapter() = AddressListAdapter()

    @Provides
    fun provideViewModel(viewModelFactory: ChooseLocationViewModelFactory)
            = ViewModelProviders.of(fragment,viewModelFactory).get(ChooseLocationViewModel::class.java)

    /*
    * val isFetchingData :MutableLiveData<Boolean>,
                              val autoCompleteResults:MutableLiveData<List<Places>>,
                              val networkCallSuccessful:MutableLiveData<Boolean>
    * */
    @Provides
    @IsFetching
    fun provideIsFetchingMutableLiveData() = MutableLiveData<Boolean>()

    @Provides
    @IsNetworkCallSuccessful
    fun provideNetworkCallSuccessfulMutableLiveData() = MutableLiveData<Boolean>()

    @Provides
    fun provideAutocompleteResultMutableLiveData() = MutableLiveData<List<Places>>()


}