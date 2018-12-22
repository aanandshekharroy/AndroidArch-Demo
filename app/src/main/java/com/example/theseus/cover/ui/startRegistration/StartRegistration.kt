package com.example.theseus.cover.ui.startRegistration

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.theseus.cover.R
import com.example.theseus.cover.ui.OnFragmentInteractionListener
import kotlinx.android.synthetic.main.start_registration_fragment.*

class StartRegistration : Fragment() {


    companion object {
        fun newInstance() = StartRegistration()
    }
    private var listener: OnFragmentInteractionListener ? = null
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
        return inflater.inflate(R.layout.start_registration_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        start_registration.setOnClickListener {
            findNavController().navigate(R.id.action_start_registration)
        }

        listener?.hideProgressBar()
    }

}
