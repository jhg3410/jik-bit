package org.inu.jikbit.ui.market

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.inu.jikbit.base.BaseViewModel
import org.inu.jikbit.data.model.Market
import org.inu.jikbit.data.repository.market.MarketRepository
import org.inu.jikbit.data.repository.ticker.TickerRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MarketViewModel : BaseViewModel(), KoinComponent {
    private val marketRepository: MarketRepository by inject()
    private val tickerRepository: TickerRepository by inject()

    val marketList = MutableLiveData<List<Market>>()

    private var unfilteredList = listOf<Market>()

    fun getMarkets(){
        CoroutineScope(Dispatchers.IO).launch {
            val marketsDeferred = async { marketRepository.getMarkets() }
            unfilteredList = marketsDeferred.await()
            val tickersList = async { tickerRepository.getTickers(getMyMarkets(marketsDeferred.await()))}.await()
            for (i in tickersList.indices){
                marketsDeferred.await()[i].trade_price = tickersList[i].trade_price
            }
            marketList.postValue(marketsDeferred.await())
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

    private fun getMyMarkets(list:List<Market>):String{
        var marketsString = ""
        list.forEach {
            marketsString = marketsString.plus(it.market.plus(","))
        }
        return marketsString.substring(0,marketsString.lastIndex)
    }

    companion object{
        const val NETWORK_END = 1000
    }
}