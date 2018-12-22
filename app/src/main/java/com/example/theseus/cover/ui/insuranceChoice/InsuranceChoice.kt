package com.example.theseus.cover.ui.insuranceChoice

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.theseus.cover.CoverApplication
import com.example.theseus.cover.R
import com.example.theseus.cover.di.modules.InsuranceChoiceModule
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
//        mInsuranceCarriersAdapter.mListener{
//
//        }
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
}

    override fun onDestroyView() {
        mCompositeDisposable.dispose()
        super.onDestroyView()
    }
}
