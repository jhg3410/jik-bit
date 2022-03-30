package org.inu.jikbit.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.inu.jikbit.databinding.ItemAccountBinding
import org.inu.jikbit.domain.model.AccountEntity
import org.inu.jikbit.presentation.ui.account.AccountViewModel

class AccountAdapter(val viewModel: AccountViewModel) : ListAdapter<AccountEntity, AccountAdapter.ViewHolder>(AccountDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountAdapter.ViewHolder = ViewHolder.from(parent,viewModel)

    override fun onBindViewHolder(holder: AccountAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ViewHolder private constructor(val binding: ItemAccountBinding, val viewModel: AccountViewModel): RecyclerView.ViewHolder(binding.root){
        companion object {
            fun from(parent: ViewGroup, viewModel: AccountViewModel) : ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemAccountBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding, viewModel)
            }
        }
        fun bind(item: AccountEntity){
            binding.item = item
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }
}

class AccountDiffUtil: DiffUtil.ItemCallback<AccountEntity>(){
    override fun areItemsTheSame(oldItem: AccountEntity, newItem: AccountEntity): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: AccountEntity, newItem: AccountEntity): Boolean {
        return oldItem == newItem
    }


}