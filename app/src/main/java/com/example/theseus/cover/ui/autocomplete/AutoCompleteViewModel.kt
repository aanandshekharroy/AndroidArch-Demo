package com.example.theseus.cover.ui.autocomplete

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.theseus.cover.data.IDataManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

class AutoCompleteViewModel(val mDataManager: IDataManager, val mCompositeDisposable: CompositeDisposable)
    : ViewModel() {
    fun findMatchingPlaces(value: String) {
        isFetchingData.value = true

        mCompositeDisposable.add(
            mDataManager.fetchPlaces(value)
                .map {
                    it.predictions.map {
                        Places(it.id,it.structuredFormatting.mainText,it.structuredFormatting.secondaryText)
                    }
                }
            .subscribeBy(
                onSuccess = {
                    isFetchingData.postValue(false)
                    autoCompleteResults.postValue(it)
                }, onError = {
                    isFetchingData.postValue(false)
                }
            )
        )
    }
    val isFetchingData = MutableLiveData<Boolean>()
    val autoCompleteResults = MutableLiveData<List<Places>>()
    override fun onCleared() {
        mCompositeDisposable.dispose()
        super.onCleared()
    }
}
