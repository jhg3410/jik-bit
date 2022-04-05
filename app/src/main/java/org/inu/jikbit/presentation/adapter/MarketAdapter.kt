package org.inu.jikbit.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.inu.jikbit.databinding.ItemMarketBinding
import org.inu.jikbit.domain.model.MarketEntity

class MarketAdapter: ListAdapter<MarketEntity, MarketAdapter.ViewHolder>(MarketDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(ItemMarketBinding.inflate(
        LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: MarketAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ViewHolder(private val binding: ItemMarketBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MarketEntity){
            binding.item = item
            binding.executePendingBindings()
        }
    }
}

class MarketDiffUtil: DiffUtil.ItemCallback<MarketEntity>(){
    override fun areItemsTheSame(oldItem: MarketEntity, newItem: MarketEntity): Boolean {
        return oldItem.market == newItem.market
    }

    override fun areContentsTheSame(oldItem: MarketEntity, newItem: MarketEntity): Boolean {
        return oldItem == newItem
    }


}