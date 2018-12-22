package com.example.theseus.cover.ui.insuranceChoice

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import javax.inject.Inject

class InsuranceChoiceViewModel @Inject constructor(val insuranceCarriers: List<String>): ViewModel() {
    fun insurerValueChanged(insurerCarrier: String) {
        if(insurerCarrier.isEmpty()){
            insurersCarriersList.value = insuranceCarriers
        }else{

            insurersCarriersList.value = insuranceCarriers.filter {
                it.startsWith(insurerCarrier,true)
            }
        }
    }

    val insurersCarriersList = MutableLiveData<List<String>>()
    init {
        insurersCarriersList.value = insuranceCarriers
    }
}
