package com.example.theseus.cover.ui.autocomplete

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.theseus.cover.data.IDataManager
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class AutoCompleteViewModelFactory @Inject constructor(val mDataManager: IDataManager,
                                                       val mCompositeDisposable: CompositeDisposable)
    : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AutoCompleteViewModel(mDataManager,mCompositeDisposable) as T
    }
}