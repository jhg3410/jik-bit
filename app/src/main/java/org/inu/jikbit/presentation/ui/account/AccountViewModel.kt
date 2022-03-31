package org.inu.jikbit.presentation.ui.account

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import org.inu.jikbit.domain.model.AccountEntity
import org.inu.jikbit.global.base.BaseViewModel
import org.inu.jikbit.domain.model.TickerEntity
import org.inu.jikbit.domain.usecase.GetAccountsUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.text.DecimalFormat

class AccountViewModel : BaseViewModel(), KoinComponent {

    private val getAccountsUseCase: GetAccountsUseCase by inject()

    val accountList = MutableLiveData<List<AccountEntity>>()
    private val accountTmpList = MutableLiveData<List<AccountEntity>>() // 서버에서 받아온 값
    private val errorTmpList = MutableLiveData<List<AccountEntity>>()   // 에러가 났을 때 처리하기 위한 값

    var aniState = MutableLiveData<Boolean>(false)
    var aniText = MutableLiveData<String>("▼")

    val totalKRW = MutableLiveData<Double>(0.0)
    val totalProperty = MutableLiveData<Double>(0.0)
    val totalPurchaseAmount = MutableLiveData<Double>(0.0)
    val totalEvaluationAmount = MutableLiveData<Double>(0.0)
    val totalValuationAmount = MutableLiveData<Double>(0.0)
    val totalYieldAmount = MutableLiveData<Double>(0.0)


    fun getAccounts() {
        viewModelScope.launch {
            accountTmpList.value =  getAccountsUseCase()!!
            if (accountTmpList.value!![0].currency != "에러지롱") {     // 처음 들어왔을 떄
                accountList.postValue(accountTmpList.value) // 대입
                errorTmpList.postValue(accountTmpList.value)
            }
            else{   // 시간 텀 없이 새로고침 했을 때
                accountList.postValue(errorTmpList.value)
            }
            viewEvent(NETWORK_END)
        }
    }
//            withContext(Dispatchers.IO) {
//                val accountsDeferred = async { accountRepository.getAccounts() }
//                val await = accountsDeferred.await()
//                when {
//                    await[0].currency.isNotEmpty() -> {  // 처음 들어왔을 때
//                        withContext(Dispatchers.Main) {
//                            initialTotal()
//                            totalKRW.value =
//                                await.find { it.currency == "KRW" }?.balance?.toDouble()
//                            coinList.value = await.filter { it.currency != "KRW" }
//                        }
//                        with(coinList.value!!) {
//                            if (this.isNotEmpty()) {
//                                val tickersDeferred =
//                                    async { tickerRepository.getTickers(getMyCurrency(this@with)) }
//                                setNotProvideByServer(this, tickersDeferred.await())
//                                accountList.postValue(this)
//                                tmpList.postValue(this)
//                            }
//                            withContext(Dispatchers.Main) {
//                                totalProperty.value =
//                                    (totalProperty.value!! + totalKRW.value!!)
//                            }
//                        }
//                    }
//                    else -> accountList.postValue(tmpList.value)    // 시간 텀 없이 새로고침 했을 때
//
//                }
//                withContext(Dispatchers.Main) {
//                    viewEvent(NETWORK_END)
//                }
//            }


    fun aniButtonClick() {
        aniState.value = aniState.value!!.not()
        viewEvent(ANI_BUTTON_CLICK)
        with(aniState.value) {
            if (this == true) aniText.value = "▲"
            else aniText.value = "▼"
        }
    }

    private fun initialTotal() {
        totalKRW.value = 0.0
        totalProperty.value = 0.0
        totalPurchaseAmount.value = 0.0
        totalEvaluationAmount.value = 0.0
        totalValuationAmount.value = 0.0
        totalYieldAmount.value = 0.0
    }

    private suspend fun setTotal(coinList: List<AccountEntity>, i: Int) {
        withContext(Dispatchers.Main) {
            totalProperty.value =
                totalProperty.value!! + coinList[i].propertyNow.toDouble()
            totalPurchaseAmount.value =
                totalPurchaseAmount.value!! + coinList[i].property.toDouble()
            totalEvaluationAmount.value =
                totalEvaluationAmount.value!! + coinList[i].propertyNow.toDouble()
            totalYieldAmount.value =
                totalYieldAmount.value!! + coinList[i].yield.toDouble()
            totalValuationAmount.value =
                totalValuationAmount.value!! + coinList[i].income.toDouble()
        }
    }

//    private suspend fun setNotProvideByServer(accountList:List<AccountEntity>, tickersList:List<TickerEntity>{
//        val decimal = DecimalFormat("#,###.##")
//        withContext(Dispatchers.IO) {
//            for (i in accountResponseList.indices) {
//                with(accountResponseList) {
//                    this[i].trade_price = tickersList[i].trade_price
//                    this[i].propertyNow =
//                        ((this[i].balance).toDouble() * (this[i].trade_price).toDouble()).toString()
//                    this[i].property =
//                        ((this[i].balance).toDouble() * (this[i].avg_buy_price).toDouble()).toString()
//                    this[i].income =
//                        ((this[i].propertyNow).toDouble() - (this[i].property).toDouble()).toString()
//                    this[i].yield =
//                        (((this[i].trade_price).toDouble() / ((this[i].avg_buy_price).toDouble()) * 100) - 100).toString()
//                    setTotal(this, i)
//                    this[i].income = decimal.format(this[i].income.toDouble())
//                    this[i].yield = decimal.format(this[i].yield.toDouble())
//                }
//            }
//        }
//        withContext(Dispatchers.Main) {
//            totalYieldAmount.value =
//                (totalYieldAmount.value!! / tickersList.size)
//        }
//    }

    companion object {
        const val NETWORK_END = 1000
        const val ANI_BUTTON_CLICK = 1001
    }}