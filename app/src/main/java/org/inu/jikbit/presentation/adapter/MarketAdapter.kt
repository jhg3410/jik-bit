package org.inu.jikbit.presentation.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import jp.wasabeef.blurry.Blurry
import org.inu.jikbit.R
import org.inu.jikbit.databinding.ItemMarketBinding
import org.inu.jikbit.domain.model.MarketEntity
import org.inu.jikbit.presentation.ui.market.MarketViewModel
import org.koin.core.component.KoinComponent

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
                Blurry.delete(view as ViewGroup)
                binding.detailView.visibility = View.INVISIBLE
            }
            else{
                item.blurry = true
                Blurry.with(binding.root.context)
                    .color(Color.argb(77, 128, 128, 128))
                    .radius(4)
                    .sampling(6)
                    .postOnto(view as ViewGroup)

                binding.detailView.visibility = View.VISIBLE
            }
        }

        fun blurryOfPre(item: MarketEntity,view:View){
            if(item.blurry){
                Blurry.with(binding.root.context)
                    .color(Color.argb(77, 128, 128, 128))
                    .radius(4)
                    .sampling(6)
                    .postOnto(view as ViewGroup)
                binding.detailView.visibility = View.VISIBLE
            }
            else{
                Blurry.delete(view as ViewGroup)
                binding.detailView.visibility = View.INVISIBLE
            }
        }

        private fun Blurry.Composer.postOnto(view: ViewGroup) {
            view.post { onto(view) }        // todo 해석하기
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