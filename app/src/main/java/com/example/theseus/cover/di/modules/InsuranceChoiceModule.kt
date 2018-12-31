package com.example.theseus.cover.di.modules

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.theseus.cover.R
import com.example.theseus.cover.ui.insuranceChoice.InsuranceCarriersAdapter
import com.example.theseus.cover.ui.insuranceChoice.InsuranceCarriersModel
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import java.nio.charset.Charset

@Module
class InsuranceChoiceModule(val applicationContext: Context) {
    @Provides
    fun providesCompositeDisposable() = CompositeDisposable()

    @Provides
    fun provideAdapter() = InsuranceCarriersAdapter()

    @Provides
    fun insuranceCarriers() :List<String>{
        val json :String= try {
                val inputStream = applicationContext.resources.openRawResource(R.raw.carriers)
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                String(buffer, Charset.forName("UTF-8"))
            }catch (e: Exception){
                null.toString()
            }
        return try{
            Gson().fromJson(json,InsuranceCarriersModel::class.java).insuranceCarriers!!
        }catch (e: Exception){
            emptyList()
        }
    }
    @Provides
    fun provideMutableLiveDataForInsuranceList(insuranceCarriers: List<String>)
            = MutableLiveData<List<String>>().apply {
        value = insuranceCarriers
    }
}