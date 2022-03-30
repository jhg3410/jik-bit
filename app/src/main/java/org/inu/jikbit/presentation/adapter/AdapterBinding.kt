package org.inu.jikbit.presentation.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import org.inu.jikbit.domain.model.AccountEntity
import org.inu.jikbit.domain.model.MarketEntity
import org.inu.jikbit.global.util.GlideUtil
import org.inu.jikbit.global.util.Span


@BindingAdapter("app:accounts")
fun setAccounts(recyclerView:RecyclerView, list:List<AccountEntity>?){
    list?.let{
        (recyclerView.adapter as AccountAdapter).submitList(list)
    }
}

@BindingAdapter("app:markets")
fun setMarkets(recyclerView: RecyclerView,list:List<MarketEntity>?){
    list?.let{
        (recyclerView.adapter as MarketAdapter).submitList(list)
    }
}

@BindingAdapter("app:ImageWithUrl")
fun setImageWithUrl(view: ImageView, coinName:String){
    GlideUtil.loadWithUrl(view,coinName)
}

@BindingAdapter("app:div","app:textWithKRW")
fun setTextWithKRW(view: TextView, div:String,text: String){
        Span.plusKRW(view,div,text)
}

@BindingAdapter("app:div2","app:textLast")
fun setTextLastEmp(view: TextView, div:String,text: String){
    Span.lastTextEmp(view,div,text)
}

@BindingAdapter("app:textWithFormat")
fun setTextWithFormat(view: TextView, text: String){
    Span.textDecimalFormat(view,text)
}