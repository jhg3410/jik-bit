package org.inu.jikbit.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import org.inu.jikbit.data.model.Account
import org.inu.jikbit.data.model.Market


@BindingAdapter("app:accounts")
fun setAccounts(recyclerView:RecyclerView, list:List<Account>?){
    list?.let{
        (recyclerView.adapter as AccountAdapter).submitList(list)
    }
}

@BindingAdapter("app:markets")
fun setMarkets(recyclerView: RecyclerView,list:List<Market>?){
    list?.let{
        (recyclerView.adapter as MarketAdapter).submitList(list)
    }
}