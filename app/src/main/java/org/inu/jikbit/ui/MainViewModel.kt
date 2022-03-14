package org.inu.jikbit.ui

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import org.inu.jikbit.base.BaseViewModel
import org.inu.jikbit.data.model.Account
import org.inu.jikbit.data.model.Market
import org.inu.jikbit.data.repository.account.AccountRepository
import org.inu.jikbit.data.repository.market.MarketRepository
import org.inu.jikbit.data.repository.ticker.TickerRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel : BaseViewModel(), KoinComponent {
    private val accountRepository: AccountRepository by inject()
    private val marketRepository: MarketRepository by inject()
    private val tickerRepository: TickerRepository by inject()

    val accountList = MutableLiveData<List<Account>>()
    val marketList = MutableLiveData<List<Market>>()


    private var unfilteredList = listOf<Market>()
    private val tmpList = MutableLiveData<List<Account>>()

    fun getAccounts() {
        CoroutineScope(Dispatchers.IO).launch {
            val accountsDeferred = async { accountRepository.getAccounts() }
            with(accountsDeferred.await()) {
                when {
                    this[0].currency.isNotEmpty() -> {  // 처음 들어왔을 때
                        val tickersList = async { tickerRepository.getTickers(getMyCurrency(this@with))}.await()
                        for (i in tickersList.indices){
                            this[i].trade_price = tickersList[i].trade_price
                        }
                        accountList.postValue(this)
                        tmpList.postValue(this)
                    }
                    else -> accountList.postValue(tmpList.value)    // 시간 텀 없이 새로고침 했을 때
                }
            }
        }
    }

    fun getMarkets(){
        CoroutineScope(Dispatchers.IO).launch {
            val marketsDeferred = async { marketRepository.getMarkets() }
            marketList.postValue(marketsDeferred.await())
            unfilteredList = marketsDeferred.await()
        }
    }

    private fun getMyCurrency(list:List<Account>): String{
        var currencyString = ""
        list.forEach {
            currencyString = currencyString.plus("KRW-".plus(it.currency.plus(",")))
        }
        return currencyString.substring(0,currencyString.lastIndex)
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
}