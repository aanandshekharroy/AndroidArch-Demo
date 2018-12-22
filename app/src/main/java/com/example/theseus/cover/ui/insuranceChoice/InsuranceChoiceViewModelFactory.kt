package com.example.theseus.cover.ui.insuranceChoice

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class InsuranceChoiceViewModelFactory @Inject constructor(
    val insuranceCarriersList: List<String>
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return InsuranceChoiceViewModel(insuranceCarriersList) as T
    }
}