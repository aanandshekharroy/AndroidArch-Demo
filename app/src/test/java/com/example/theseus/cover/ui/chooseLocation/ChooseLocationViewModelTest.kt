package com.example.theseus.cover.ui.chooseLocation

import androidx.lifecycle.MutableLiveData
import com.example.theseus.cover.data.DataManager
import com.example.theseus.cover.data.api.model.GooglePlacesAPIResponse
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.io.IOException
import org.mockito.Mockito.`when` as whenever
class ChooseLocationViewModelTest {
    @Mock
    lateinit var isFetchingData :MutableLiveData<Boolean>
    @Mock
    lateinit var isNetworkCallSuccessful: MutableLiveData<Boolean>
    @Mock
    lateinit var autoCompleteResults: MutableLiveData<List<Places>>
    @Mock
    lateinit var mCompositeDisposable: CompositeDisposable
    @Mock
    lateinit var mDataManager: DataManager
    @Mock
    lateinit var mGooglePlacesAPIResponse: GooglePlacesAPIResponse
    lateinit var chooseLocationViewModel: ChooseLocationViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        chooseLocationViewModel = ChooseLocationViewModelFactory(mDataManager,mCompositeDisposable,isFetchingData,isNetworkCallSuccessful,autoCompleteResults)
            .create(ChooseLocationViewModel::class.java)
        // given
        whenever(mDataManager.fetchPlaces(Mockito.anyString()))
            .thenReturn(Single.just(mGooglePlacesAPIResponse))

    }
    @Test
    fun isFetchingDataIsSetToTrue_whenFetchingDataStarts(){
        //when
        chooseLocationViewModel.findMatchingPlaces(Mockito.anyString())
        //then
        Mockito.verify( isFetchingData, Mockito.times(1)).value = true
    }
    @Test
    fun checkDataMangerFetchMethodIsCalled(){
        //when
        chooseLocationViewModel.findMatchingPlaces(Mockito.anyString())
        //then
        verify(mDataManager).fetchPlaces(Mockito.anyString())
    }
    @Test
    fun autocompleteResultsAreUpdated_afterSuccessulNetworkRequest(){
        //when
        chooseLocationViewModel.findMatchingPlaces(Mockito.anyString())
        //then
        verify(autoCompleteResults).postValue(Mockito.anyList())
    }
    @Test
    fun autocompleteResultsArentUpdated_afterNetworkRequestFails(){
        //given
        whenever(mDataManager.fetchPlaces(Mockito.anyString()))
            .thenReturn(Single.error(IOException()))
        //when
        chooseLocationViewModel.findMatchingPlaces(Mockito.anyString())
        //then
        verifyZeroInteractions(autoCompleteResults)
    }
    @Test
    fun isNetworkSuccessfulSetToFalse_afterFailedNetworkCall(){
        //given
        whenever(mDataManager.fetchPlaces(Mockito.anyString()))
            .thenReturn(Single.error(IOException()))
        //when
        chooseLocationViewModel.findMatchingPlaces(Mockito.anyString())
        //then
        verify(isNetworkCallSuccessful).postValue(false)
    }
}