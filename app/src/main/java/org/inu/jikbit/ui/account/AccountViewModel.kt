package org.inu.jikbit.ui.account

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import org.inu.jikbit.base.BaseViewModel
import org.inu.jikbit.data.model.Account
import org.inu.jikbit.data.repository.account.AccountRepository
import org.inu.jikbit.data.repository.ticker.TickerRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.text.DecimalFormat

class AccountViewModel: BaseViewModel(), KoinComponent {
    private val accountRepository: AccountRepository by inject()
    private val tickerRepository: TickerRepository by inject()

    val accountList = MutableLiveData<List<Account>>()
    private val tmpList = MutableLiveData<List<Account>>()
    var aniState= MutableLiveData<Boolean>(false)
    var aniText = MutableLiveData<String>("▼")

    private val totalKRW = MutableLiveData<Double>(0.0)
    val totalProperty = MutableLiveData<Double>(0.0)
    val totalPurchaseAmount = MutableLiveData<Double>(0.0)
    val totalEvaluationAmount = MutableLiveData<Double>(0.0)
    val totalValuationAmount = MutableLiveData<Double>(0.0)
    val totalYieldAmount = MutableLiveData<Double>(0.0)


    fun getAccounts() {
        CoroutineScope(Dispatchers.IO).launch {
            val accountsDeferred = async { accountRepository.getAccounts() }
            with(accountsDeferred.await()) {
                when {
                    this[0].currency.isNotEmpty() -> {  // 처음 들어왔을 때
                        withContext(Dispatchers.Main) {
                            initialTotal()
                        }
                        val tickersList = async { tickerRepository.getTickers(getMyCurrency(this@with))}.await()
                        val decimal = DecimalFormat("#,###.##")
                        for (i in tickersList.indices){
                            this[i].trade_price = tickersList[i].trade_price
                            this[i].propertyNow= ((this[i].balance).toDouble() * (this[i].trade_price).toDouble()).toString()
                            this[i].property= ((this[i].balance).toDouble() * (this[i].avg_buy_price).toDouble()).toString()
                            this[i].income = ((this[i].propertyNow).toDouble() - (this[i].property).toDouble()).toString()
                            this[i].yield = (((this[i].trade_price).toDouble()/((this[i].avg_buy_price).toDouble())*100)-100).toString()
                            withContext(Dispatchers.Main) {
                                totalProperty.value =
                                    totalProperty.value!! + this@with[i].propertyNow.toDouble()
                                totalPurchaseAmount.value =
                                    totalPurchaseAmount.value!! + this@with[i].property.toDouble()
                                totalEvaluationAmount.value =
                                    totalEvaluationAmount.value!! + this@with[i].propertyNow.toDouble()
                                totalYieldAmount.value =
                                    totalYieldAmount.value!! + this@with[i].yield.toDouble()
                                totalValuationAmount.value =
                                    totalValuationAmount.value!! + this@with[i].income.toDouble()
                            }
                            this[i].income = decimal.format(this[i].income.toDouble())
                            this[i].yield = decimal.format(this[i].yield.toDouble())
                        }
                        withContext(Dispatchers.Main) {
                            totalYieldAmount.value =
                                (totalYieldAmount.value!! / tickersList.size)
                        }
                        accountList.postValue(this)
                        tmpList.postValue(this)
                    }
                    else -> accountList.postValue(tmpList.value)    // 시간 텀 없이 새로고침 했을 때
                }
            }
            withContext(Dispatchers.Main) {
                viewEvent(NETWORK_END)
            }
        }
    }

    private fun getMyCurrency(list:List<Account>): String{
        var currencyString = ""
        list.forEach {
            currencyString = currencyString.plus("KRW-".plus(it.currency.plus(",")))
        }
        return currencyString.substring(0,currencyString.lastIndex)
    }

    fun aniButtonClick(){
        aniState.value = aniState.value!!.not()
        viewEvent(ANI_BUTTON_CLICK)
        with(aniState.value){
            if( this == true)   aniText.value = "▲"
            else    aniText.value = "▼"
        }
    }

    private fun initialTotal(){
        totalKRW.value = 0.0
        totalProperty.value = 0.0
        totalPurchaseAmount.value = 0.0
        totalEvaluationAmount.value = 0.0
        totalValuationAmount.value = 0.0
        totalYieldAmount.value = 0.0
    }

    companion object{
        const val NETWORK_END = 1000
        const val ANI_BUTTON_CLICK = 1001
    }
}