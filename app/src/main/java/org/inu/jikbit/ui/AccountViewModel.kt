package org.inu.jikbit.ui

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.inu.jikbit.base.BaseViewModel
import org.inu.jikbit.data.model.Account
import org.inu.jikbit.data.repository.account.AccountRepository
import org.inu.jikbit.data.repository.ticker.TickerRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AccountViewModel: BaseViewModel(), KoinComponent {
    private val accountRepository: AccountRepository by inject()
    private val tickerRepository: TickerRepository by inject()

    val accountList = MutableLiveData<List<Account>>()
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
                            this[i].propertyNow= ((this[i].balance).toDouble() * (this[i].trade_price).toDouble()).toString()
                            this[i].property= ((this[i].balance).toDouble() * (this[i].avg_buy_price).toDouble()).toString()
                            this[i].income = String.format("%.2f",(this[i].propertyNow).toDouble() - (this[i].property).toDouble())
                            this[i].yield = String.format("%.2f",((this[i].trade_price).toDouble()/((this[i].avg_buy_price).toDouble())*100)-100)
                        }
                        accountList.postValue(this)
                        tmpList.postValue(this)
                    }
                    else -> accountList.postValue(tmpList.value)    // 시간 텀 없이 새로고침 했을 때
                }
            }
        }
        viewEvent(NETWORK_END)
    }

    private fun getMyCurrency(list:List<Account>): String{
        var currencyString = ""
        list.forEach {
            currencyString = currencyString.plus("KRW-".plus(it.currency.plus(",")))
        }
        return currencyString.substring(0,currencyString.lastIndex)
    }

    companion object{
        const val NETWORK_END = 1000
    }
}