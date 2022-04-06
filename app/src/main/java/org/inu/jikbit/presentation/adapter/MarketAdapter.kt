package org.inu.jikbit.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import org.inu.jikbit.R
import org.inu.jikbit.databinding.ItemMarketBinding
import org.inu.jikbit.domain.model.MarketEntity
import org.inu.jikbit.global.util.Blurry.blurryOff
import org.inu.jikbit.global.util.Blurry.blurryOn
import org.inu.jikbit.presentation.ui.market.MarketViewModel
import org.koin.core.component.KoinComponent
import render.animations.Attention
import render.animations.Render


class MarketAdapter(val viewModel: MarketViewModel): ListAdapter<MarketEntity, MarketAdapter.ViewHolder>(MarketDiffUtil()), KoinComponent {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(ItemMarketBinding.inflate(
        LayoutInflater.from(parent.context),parent,false),viewModel)

    override fun onBindViewHolder(holder: MarketAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.findViewById<MaterialCardView>(R.id.materialCard).setOnClickListener {
            holder.blurryOfClick(getItem(position),it)
        }
        holder.blurryOfPre(getItem(position),holder.itemView.findViewById<MaterialCardView>(R.id.materialCard))
    }

    class ViewHolder(private val binding: ItemMarketBinding, val viewModel:MarketViewModel):RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MarketEntity){
            binding.item = item
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }

        fun blurryOfClick(item: MarketEntity,view: View){
            if (item.blurry){
                item.blurry = false
                blurryOff(view)
                invisible(binding.detailView)
            }
            else{
                item.blurry = true
                blurryOn(view)
                visibleWithAni(binding.detailView)
            }
        }
        fun blurryOfPre(item: MarketEntity,view:View){
            if(item.blurry){
                blurryOn(view)
                binding.detailView.visibility = View.VISIBLE
            }
            else{
                blurryOff(view)
                invisible(binding.detailView)
            }
        }

        private fun visibleWithAni(view:View){
            view.visibility = View.VISIBLE
            Render(view.context).run {
                setAnimation(Attention().Bounce(view))
                setDuration(500)
                start()
            }
        }
        private fun invisible(view:View){
            view.visibility = View.INVISIBLE
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