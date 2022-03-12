package org.inu.jikbit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.inu.jikbit.data.model.Account
import org.inu.jikbit.databinding.ItemAccountBinding
import org.inu.jikbit.ui.MainViewModel

class AccountAdapter(val viewModel:MainViewModel) : ListAdapter<Account, AccountAdapter.ViewHolder>(AccountDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountAdapter.ViewHolder = ViewHolder.from(parent,viewModel)

    override fun onBindViewHolder(holder: AccountAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ViewHolder private constructor(val binding: ItemAccountBinding, val viewModel: MainViewModel): RecyclerView.ViewHolder(binding.root){
        companion object {
            fun from(parent: ViewGroup, viewModel: MainViewModel) : ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemAccountBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding, viewModel)
            }
        }
        fun bind(item: Account){
            binding.item = item
            binding.property.text = (item.balance.toDouble() * item.avg_buy_price.toDouble()).toString()
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }
}

class AccountDiffUtil: DiffUtil.ItemCallback<Account>(){
    override fun areItemsTheSame(oldItem: Account, newItem: Account): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Account, newItem: Account): Boolean {
        return oldItem == newItem
    }


}