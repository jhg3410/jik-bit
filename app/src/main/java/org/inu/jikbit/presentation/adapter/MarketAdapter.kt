package org.inu.jikbit.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.inu.jikbit.databinding.ItemMarketBinding
import org.inu.jikbit.domain.model.MarketEntity
import org.inu.jikbit.presentation.ui.market.MarketViewModel

class MarketAdapter(val viewModel: MarketViewModel) : ListAdapter<MarketEntity, MarketAdapter.ViewHolder>(MarketDiffUtil()) {

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
        fun bind(item: MarketEntity){
            binding.item = item
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }
}

class MarketDiffUtil: DiffUtil.ItemCallback<MarketEntity>(){
    override fun areItemsTheSame(oldItem: MarketEntity, newItem: MarketEntity): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MarketEntity, newItem: MarketEntity): Boolean {
        return oldItem == newItem
    }


}