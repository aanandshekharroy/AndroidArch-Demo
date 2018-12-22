package com.example.theseus.cover.ui.insuranceChoice

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.theseus.cover.CoverApplication
import com.example.theseus.cover.R
import com.example.theseus.cover.di.modules.InsuranceChoiceModule
import com.example.theseus.cover.ui.OnFragmentInteractionListener
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.insurance_choice_fragment.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class InsuranceChoice : Fragment() {

    companion object {
        fun newInstance() = InsuranceChoice()
    }
    @Inject
    lateinit var mInsuranceChoiceViewModelFactory: InsuranceChoiceViewModelFactory
    private lateinit var viewModel: InsuranceChoiceViewModel
    @Inject
    lateinit var mCompositeDisposable: CompositeDisposable
    @Inject
    lateinit var mInsuranceCarriersAdapter: InsuranceCarriersAdapter
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
    ): View? {
        return inflater.inflate(R.layout.insurance_choice_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (requireContext().applicationContext as CoverApplication).mApplicationComponent.insuranceComponent(
            InsuranceChoiceModule(requireContext().applicationContext)
        ).inject(this)
        viewModel = ViewModelProviders.of(this,mInsuranceChoiceViewModelFactory).get(InsuranceChoiceViewModel::class.java)
        setupViews()
    }

    private fun setupViews() {
        insurer_list.layoutManager = LinearLayoutManager(requireContext())
        mInsuranceCarriersAdapter.mItemClickListener = {
            findNavController().navigate(R.id.complete_registration)
        }
        insurer_list.adapter = mInsuranceCarriersAdapter
        viewModel.insurersCarriersList.observe(viewLifecycleOwner, Observer {
            it?.let {
                mInsuranceCarriersAdapter.insuranceCarriersList = it
                insurer_list.scrollToPosition(0)
            }
        })
        mCompositeDisposable.add(
            RxTextView.textChanges(insurer_value)
                .debounce (200,TimeUnit.MILLISECONDS)
                .skip(1)
                .map {
                    it.toString()
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        viewModel.insurerValueChanged(it)
                    },onError = {

                    }
                )


        )
        listener?.setProgressBarToComplete()
}

    override fun onDestroyView() {
        mCompositeDisposable.dispose()
        super.onDestroyView()
    }
}
