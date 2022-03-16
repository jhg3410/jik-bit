package org.inu.jikbit.ui.market

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.inu.jikbit.base.BaseViewModel
import org.inu.jikbit.data.model.Market
import org.inu.jikbit.data.repository.market.MarketRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MarketViewModel : BaseViewModel(), KoinComponent {
    private val marketRepository: MarketRepository by inject()
    val marketList = MutableLiveData<List<Market>>()

    private var unfilteredList = listOf<Market>()

    fun getMarkets(){
        CoroutineScope(Dispatchers.IO).launch {
            val marketsDeferred = async { marketRepository.getMarkets() }
            marketList.postValue(marketsDeferred.await())
            unfilteredList = marketsDeferred.await()
        }
        viewEvent(NETWORK_END)
    }

    private fun filter(inputText:String){
        val filteredList = mutableListOf<Market>()
        unfilteredList.forEach {
            if(it.korean_name.contains(inputText)){
                filteredList.add(it)
            }
        }
        marketList.value = filteredList
    }

    fun coinTextChanged(text : CharSequence ){
        filter(text.toString())
    }

    companion object{
        const val NETWORK_END = 1000
    }
}