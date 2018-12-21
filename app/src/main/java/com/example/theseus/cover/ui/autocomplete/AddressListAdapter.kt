package com.example.theseus.cover.ui.autocomplete

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.theseus.cover.R
import kotlinx.android.synthetic.main.address_item.view.*
import kotlin.properties.Delegates

class AddressListAdapter: RecyclerView.Adapter<AddressListAdapter.AddressItemViewHolder>(),
AutoUpdatableAdapter{
    var addressList : List<Places> by Delegates.observable(emptyList()){
        _, oldValue, newValue ->
        autoNotify(oldValue,newValue){
            o,n->
            o.id==n.id
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): AddressItemViewHolder {
        return AddressItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.address_item,parent,false))
    }

    override fun getItemCount() = addressList.size

    override fun onBindViewHolder(holder: AddressItemViewHolder, position: Int) {
        holder.bind(holder.adapterPosition)
    }

    inner class AddressItemViewHolder(val view: View):RecyclerView.ViewHolder(view){
        fun bind(adapterPosition: Int) {
            val place = addressList[adapterPosition]
            view.address_title.text = place.title
            view.address_subtitle.text = place.subtitle
        }

    }
}