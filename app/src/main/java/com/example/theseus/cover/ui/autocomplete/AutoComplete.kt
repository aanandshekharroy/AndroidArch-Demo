package com.example.theseus.cover.ui.autocomplete

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.theseus.cover.CoverApplication
import com.example.theseus.cover.R
import com.example.theseus.cover.di.modules.AutocompleteModule
import com.example.theseus.cover.ui.autocomplete.AutoCompleteDirections.actionLocationSelectionToInsuranceCarrierSelection
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.auto_complete_fragment.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AutoComplete : Fragment() {

    companion object {
        fun newInstance() = AutoComplete()
    }
    @Inject
    lateinit var autoCompleteViewModelFactory: AutoCompleteViewModelFactory
    lateinit var viewModel: AutoCompleteViewModel
    @Inject
    lateinit var mAdapter: AddressListAdapter
    @Inject
    lateinit var mCompositeDisposable: CompositeDisposable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.auto_complete_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (requireContext().applicationContext as CoverApplication).mApplicationComponent
            .autoCompleteComponent(AutocompleteModule())
            .inject(this)
        viewModel = ViewModelProviders.of(this,autoCompleteViewModelFactory)
            .get(AutoCompleteViewModel::class.java)
        setupViews()
    }

    private fun setupViews() {
        address_list.layoutManager = LinearLayoutManager(requireContext())
        mAdapter.mAddressClickListener = {
            placeSelected(it)
        }
        address_list.adapter = mAdapter
        viewModel.autoCompleteResults.observe(viewLifecycleOwner, Observer {
            it?.let {
                mAdapter.addressList = it
            }
        })
        viewModel.isFetchingData.observe(viewLifecycleOwner, Observer {
            it?.let {
                if(it){
                    loading.visibility = View.VISIBLE
                }else{
                    loading.visibility = View.INVISIBLE
                }
            }
        })
        mCompositeDisposable.add(
            RxTextView.textChanges(address_value)
                .debounce(300,TimeUnit.MILLISECONDS)
                .skip(1)
                .map {
                    it.toString()
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        if(it.isNotBlank()){
                            viewModel.findMatchingPlaces(it)
                        }else{
                            mAdapter.addressList = emptyList()
                        }

                    },onError = {

                    }
                )
        )

    }

    private fun placeSelected(place: Places) {
        view?.let {
            val navDirections = actionLocationSelectionToInsuranceCarrierSelection()
            findNavController(it).navigate(R.id.action_location_selection_to_insurance_carrier_selection)
        }
    }

    override fun onDestroyView() {
        mCompositeDisposable.dispose()
        super.onDestroyView()
    }
}
