package org.inu.jikbit.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import org.inu.jikbit.data.model.Account


@BindingAdapter("app:accounts")
fun setAccounts(recyclerView:RecyclerView, list:List<Account>?){
    list?.let{
        (recyclerView.adapter as AccountAdapter).submitList(list)
    }
}