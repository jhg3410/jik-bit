package org.inu.jikbit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.inu.jikbit.data.model.Market
import org.inu.jikbit.databinding.ItemMarketBinding
import org.inu.jikbit.ui.market.MarketViewModel

class MarketAdapter(val viewModel: MarketViewModel) : ListAdapter<Market, MarketAdapter.ViewHolder>(MarketDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketAdapter.ViewHolder = ViewHolder.from(parent,viewModel)

    override fun onBindViewHolder(holder: MarketAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ViewHolder private constructor(val binding: ItemMarketBinding, val viewModel: MarketViewModel): RecyclerView.ViewHolder(binding.root){
        companion object {
            fun from(parent: ViewGroup, viewModel: MarketViewModel) : ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemMarketBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding, viewModel)
            }
        }
        fun bind(item: Market){
            binding.item = item
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }
}

class MarketDiffUtil: DiffUtil.ItemCallback<Market>(){
    override fun areItemsTheSame(oldItem: Market, newItem: Market): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Market, newItem: Market): Boolean {
        return oldItem == newItem
    }


}