package com.example.theseus.cover.ui.insuranceChoice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.theseus.cover.R
import com.example.theseus.cover.ui.chooseLocation.AutoUpdatableAdapter
import kotlinx.android.synthetic.main.insurer_item.view.*
import kotlin.properties.Delegates

class InsuranceCarriersAdapter: RecyclerView.Adapter<InsuranceCarriersAdapter.InsuranceCarrierViewHolder>() , AutoUpdatableAdapter{
    var insuranceCarriersList : List<String>  by Delegates.observable(emptyList()){
        _, oldValue, newValue ->
        autoNotify(oldValue,newValue){
            o,n->o==n
        }
    }
    lateinit var mItemClickListener: (insuranceCarrier: String) -> Unit
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): InsuranceCarrierViewHolder {
        return InsuranceCarrierViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.insurer_item,parent,false))
    }

    override fun getItemCount(): Int {
        return insuranceCarriersList.size
    }

    override fun onBindViewHolder(holder: InsuranceCarrierViewHolder, position: Int) {
        holder.bind(holder.adapterPosition)
    }



    inner class InsuranceCarrierViewHolder(val view: View): RecyclerView.ViewHolder(view){
        fun bind(adapterPosition: Int) {
            view.insurer_name.text = insuranceCarriersList[adapterPosition]
            view.setOnClickListener {
                mItemClickListener(insuranceCarriersList[adapterPosition])
            }
        }

    }
}