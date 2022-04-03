package org.inu.jikbit.presentation.ui.account

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import org.inu.jikbit.domain.model.AccountEntity
import org.inu.jikbit.global.base.BaseViewModel
import org.inu.jikbit.domain.model.TotalEntity
import org.inu.jikbit.domain.usecase.GetAccountsUseCase
import org.inu.jikbit.domain.usecase.GetTotalUseCase
import org.inu.jikbit.global.util.Span.decimalFormat
import org.inu.jikbit.presentation.ui.market.MarketViewModel.Companion.NETWORK_END
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AccountViewModel : BaseViewModel(), KoinComponent {

    private val getAccountsUseCase: GetAccountsUseCase by inject()
    private val getTotalUseCase = GetTotalUseCase()

    val accountList = MutableLiveData<List<AccountEntity>>()
    private val accountTmpList = MutableLiveData<List<AccountEntity>>() // 서버에서 받아온 값
    private val errorTmpList = MutableLiveData<List<AccountEntity>>()   // 에러가 났을 때 처리하기 위한 값
    private  var totalList  = TotalEntity()

    var aniState = MutableLiveData(false)
    var aniText = MutableLiveData("▼")

    val totalKRW = MutableLiveData(0.0)
    val totalProperty = MutableLiveData(0.0)
    val totalPurchaseAmount = MutableLiveData(0.0)
    val totalEvaluationAmount = MutableLiveData(0.0)
    val totalValuationAmount = MutableLiveData(0.0)
    val totalYieldAmount = MutableLiveData(0.0)


    fun getAccounts() {
        viewModelScope.launch {
            accountTmpList.value =  getAccountsUseCase()!!
            with(accountTmpList.value!!){
                if (this[0].currency != "에러지롱"){    // 처음 들어왔을 떄
                    var coinList = this.filter { it.currency != "KRW" }
                    getTotal(coinList,this.find { it.currency == "KRW" }!!.balance.toDouble())
                    coinList = modifyDecimalFormat(coinList)
                    accountList.postValue(coinList) // 대입
                    errorTmpList.postValue(coinList)
                }
                else{   // 시간 텀 없이 새로고침 했을 때
                    accountList.postValue(errorTmpList.value)
                }
            }

            viewEvent(NETWORK_END)
        }
    }

    private fun getTotal(coinList: List<AccountEntity>,krw:Double){
        totalList = getTotalUseCase(coinList)
        totalKRW.value = krw
        totalProperty.value =totalList.totalProperty
        totalPurchaseAmount.value = totalList.totalPurchaseAmount
        totalEvaluationAmount.value = totalList.totalEvaluationAmount
        totalValuationAmount.value = totalList.totalValuationAmount
        totalYieldAmount.value = totalList.totalYieldAmount
    }

    private fun modifyDecimalFormat(coinList:List<AccountEntity>) : List<AccountEntity> {
        for (coin in coinList) {
            coin.income = decimalFormat(coin.income)
            coin.yield = decimalFormat(coin.yield)
        }
        return coinList
    }


    fun aniButtonClick() {
        aniState.value = aniState.value!!.not()
        viewEvent(ANI_BUTTON_CLICK)
        with(aniState.value) {
            if (this == true) aniText.value = "▲"
            else aniText.value = "▼"
        }
    }

    companion object {
        const val NETWORK_END = 1000
        const val ANI_BUTTON_CLICK = 1001
    }
}