package com.example.theseus.cover.ui.autocomplete

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.theseus.cover.data.IDataManager
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class ChooseLocationViewModelFactory @Inject constructor(val mDataManager: IDataManager,
                                                         val mCompositeDisposable: CompositeDisposable)
    : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChooseLocationViewModel(mDataManager,mCompositeDisposable) as T
    }
}