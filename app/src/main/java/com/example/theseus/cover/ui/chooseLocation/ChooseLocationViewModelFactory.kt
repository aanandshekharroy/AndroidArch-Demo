package com.example.theseus.cover.ui.chooseLocation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.theseus.cover.data.IDataManager
import com.example.theseus.cover.di.qualifiers.IsFetching
import com.example.theseus.cover.di.qualifiers.IsNetworkCallSuccessful
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class ChooseLocationViewModelFactory @Inject constructor(val mDataManager: IDataManager,
                                                         val mCompositeDisposable: CompositeDisposable,
                                                         @IsFetching val isFetching: MutableLiveData<Boolean>,
                                                         @IsNetworkCallSuccessful val isNetworkCallSuccessful: MutableLiveData<Boolean>,
                                                         val autocompleteResult: MutableLiveData<List<Places>>)
    : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChooseLocationViewModel(mDataManager,mCompositeDisposable,isFetching,autocompleteResult,isNetworkCallSuccessful) as T
    }
}