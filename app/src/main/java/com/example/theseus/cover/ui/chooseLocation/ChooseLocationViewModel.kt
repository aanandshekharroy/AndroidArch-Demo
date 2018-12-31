package com.example.theseus.cover.ui.chooseLocation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.theseus.cover.data.IDataManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

class ChooseLocationViewModel(val mDataManager: IDataManager,
                              val mCompositeDisposable: CompositeDisposable,
                              val isFetchingData :MutableLiveData<Boolean>,
                              val autoCompleteResults:MutableLiveData<List<Places>>,
                              val networkCallSuccessful:MutableLiveData<Boolean>)
    : ViewModel() {
    fun findMatchingPlaces(value: String) {
        isFetchingData.value = true
        networkCallSuccessful.value = true
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
                    networkCallSuccessful.postValue(false)
                }
            )
        )
    }

    override fun onCleared() {
        mCompositeDisposable.dispose()
        super.onCleared()
    }
}
