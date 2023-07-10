package com.example.stonks_app.adaters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.stonks_app.CurrencyModel
import com.example.stonks_app.databinding.ListItemBinding

class Currency_adapter : ListAdapter<CurrencyModel, Currency_adapter.Holder>(Comparator()) {
    class Holder (view: View) : RecyclerView.ViewHolder(view){
        val binding = ListItemBinding.bind(view)
        fun bind (item : CurrencyModel) = with(binding){

        }
    }
    class Comparator : DiffUtil.ItemCallback<CurrencyModel>(){
        override fun areItemsTheSame(oldItem: CurrencyModel, newItem: CurrencyModel): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: CurrencyModel, newItem: CurrencyModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        TODO("Not yet implemented")
    }
}