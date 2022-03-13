package org.inu.jikbit.ui

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import org.inu.jikbit.base.BaseViewModel
import org.inu.jikbit.data.model.Account
import org.inu.jikbit.data.model.Market
import org.inu.jikbit.data.repository.AccountRepository
import org.inu.jikbit.data.repository.MarketRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel : BaseViewModel(), KoinComponent {
    private val accountRepository: AccountRepository by inject()
    private val marketRepository: MarketRepository by inject()

    val accountList = MutableLiveData<List<Account>>()
    val marketList = MutableLiveData<List<Market>>()
    private var unfilteredList = listOf<Market>()
    private val tmpList = MutableLiveData<List<Account>>()

    fun getAccounts() {
        CoroutineScope(Dispatchers.IO).launch {
            val accountsDeferred = async { accountRepository.getAccounts() }
            with(accountsDeferred.await()) {
                when {
                    this[0].currency.isNotEmpty() -> {
                        accountList.postValue(this)
                        tmpList.postValue(this)
                    }
                    else -> accountList.postValue(tmpList.value)
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