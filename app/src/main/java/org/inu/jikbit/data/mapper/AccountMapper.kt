package org.inu.jikbit.data.mapper

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.inu.jikbit.data.model.AccountResponse
import org.inu.jikbit.domain.model.AccountEntity
import org.inu.jikbit.domain.model.TickerEntity
import org.inu.jikbit.domain.repository.TickerRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.text.DecimalFormat

object AccountMapper : KoinComponent {

    private val tickerRepository: TickerRepository by inject()

    private var tickerList = listOf<TickerEntity>()

    suspend fun mapperToAccount(accountResponse: List<AccountResponse>): List<AccountEntity> {
        val errorList = listOf(AccountEntity("에러지롱", "", "", "", false, "", "", "", "", "", ""))
        val returnList: List<AccountEntity>
        val result = accountResponse
        val coinList = result.filter { it.currency != "KRW" }
        tickerList = withContext(Dispatchers.Default) {
            tickerRepository.getTickers(getMyCurrency(coinList))
        }

        returnList = when {
            result[0].currency.isNotEmpty() -> setNotProvideByServer(responseToEntity(coinList), tickerList)
            else -> errorList
        }
        return returnList
    }


    private fun getMyCurrency(list: List<AccountResponse>): String {
        var currencyString = ""
        list.forEach {
            currencyString = currencyString.plus("KRW-".plus(it.currency.plus(",")))
        }
        return currencyString.substring(0, currencyString.lastIndex)
    }


    private fun responseToEntity(accountResponse: List<AccountResponse>):List<AccountEntity>{
        val result = accountResponse
        val returnList = mutableListOf<AccountEntity>()

        for (i in result.indices){
            returnList.add(AccountEntity(
                result[i].currency,
                result[i].balance,
                result[i].locked,
                result[i].avg_buy_price,
                result[i].avg_buy_price_modified,
                result[i].unit_currency,
                "","","","",""
                )
            )
        }
        return returnList
    }


    private fun setNotProvideByServer(coinList: List<AccountEntity>, tickerList: List<TickerEntity>):List<AccountEntity> {
        val decimal = DecimalFormat("#,###.##")
        for (i in coinList.indices) {
            with(coinList) {
                this[i].trade_price = tickerList[i].trade_price
                this[i].propertyNow =
                    ((this[i].balance).toDouble() * (this[i].trade_price).toDouble()).toString()
                this[i].property =
                    ((this[i].balance).toDouble() * (this[i].avg_buy_price).toDouble()).toString()
                this[i].income =
                    ((this[i].propertyNow).toDouble() - (this[i].property).toDouble()).toString()
                this[i].yield =
                    (((this[i].trade_price).toDouble() / ((this[i].avg_buy_price).toDouble()) * 100) - 100).toString()
                this[i].income = decimal.format(this[i].income.toDouble())
                this[i].yield = decimal.format(this[i].yield.toDouble())
            }
        }
        return coinList
    }
}