package com.example.theseus.cover.ui.chooseLocation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.theseus.cover.R
import kotlinx.android.synthetic.main.address_item.view.*
import kotlin.properties.Delegates

class AddressListAdapter: RecyclerView.Adapter<AddressListAdapter.AddressItemViewHolder>(),
AutoUpdatableAdapter{
    lateinit var mAddressClickListener: (p: Places) ->Unit
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

    inner class AddressItemViewHolder(val view: View): RecyclerView.ViewHolder(view){
        fun bind(adapterPosition: Int) {
            val place = addressList[adapterPosition]
            view.address_title.text = place.title
            view.address_subtitle.text = place.subtitle
            view.setOnClickListener {
                mAddressClickListener(addressList[adapterPosition])
            }
        }

    }
}