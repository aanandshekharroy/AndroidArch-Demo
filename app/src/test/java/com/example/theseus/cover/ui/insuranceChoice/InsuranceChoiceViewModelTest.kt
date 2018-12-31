package com.example.theseus.cover.ui.insuranceChoice

import androidx.lifecycle.MutableLiveData
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as whenever

class InsuranceChoiceViewModelTest {
    val insurerListFromJSON = listOf("ABC","AB","A","C")
    @Mock
    lateinit var insurersCarriersList: MutableLiveData<List<String>>
    lateinit var insuranceChoiceViewModel: InsuranceChoiceViewModel
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        insuranceChoiceViewModel = InsuranceChoiceViewModelFactory(insurerListFromJSON,insurersCarriersList).create(InsuranceChoiceViewModel::class.java)
    }
    @Test
    fun defautListIsSet_WhenEmptyInputIsPassed(){
        //when
        insuranceChoiceViewModel.insurerValueChanged("")
        //then
        verify(insurersCarriersList).value = insurerListFromJSON
    }
    @Test
    fun testFilteredList_afterInpuIsProvided(){
        //when
        insuranceChoiceViewModel.insurerValueChanged("a")
        //verify
        verify(insurersCarriersList).value = listOf("ABC","AB","A")
    }
}