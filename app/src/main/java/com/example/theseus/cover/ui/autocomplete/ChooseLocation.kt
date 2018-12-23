package com.example.theseus.cover.ui.autocomplete

import android.content.Context
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
import com.example.theseus.cover.ui.OnFragmentInteractionListener
import com.example.theseus.cover.ui.autocomplete.AutoCompleteDirections.actionLocationSelectionToInsuranceCarrierSelection
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.choose_location_fragment.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ChooseLocation : Fragment() {

    companion object {
        fun newInstance() = ChooseLocation()
    }
    @Inject
    lateinit var chooseLocationViewModelFactory: ChooseLocationViewModelFactory
    lateinit var viewModel: AutoCompleteViewModel
    @Inject
    lateinit var mAdapter: AddressListAdapter
    @Inject
    lateinit var mCompositeDisposable: CompositeDisposable
    private var listener: OnFragmentInteractionListener? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnFragmentInteractionListener){
            listener = context
        }else{
            throw RuntimeException("Activity should implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.choose_location_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (requireContext().applicationContext as CoverApplication).mApplicationComponent
            .autoCompleteComponent(AutocompleteModule())
            .inject(this)
        viewModel = ViewModelProviders.of(this,chooseLocationViewModelFactory)
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
        listener?.setProgressBarToHalf()

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
