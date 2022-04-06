package org.inu.jikbit.data.mapper

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.inu.jikbit.data.model.MarketResponse
import org.inu.jikbit.domain.model.MarketEntity
import org.inu.jikbit.domain.model.TickerEntity
import org.inu.jikbit.domain.repository.TickerRepository
import org.inu.jikbit.global.util.Span.notDecimalFormat
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object MarketMapper : KoinComponent{

    private val tickerRepository: TickerRepository by inject()
    private var tickerList = listOf<TickerEntity>()

    suspend fun mapperToMarket(marketResponse: List<MarketResponse>) : List<MarketEntity>{
        tickerList = withContext(Dispatchers.IO){
            tickerRepository.getTickers(getMyMarkets(marketResponse))
        }
        return setTradePrice(responseToEntity(marketResponse), tickerList)
    }

    private fun getMyMarkets(list: List<MarketResponse>): String {
        var marketsString = ""
        list.forEach {
            marketsString = marketsString.plus(it.market.plus(","))
        }
        return marketsString.substring(0, marketsString.lastIndex)
    }

    private fun responseToEntity(marketResponse:List<MarketResponse>) : List<MarketEntity>{
        val returnList = mutableListOf<MarketEntity>()

        for (market in marketResponse){
            returnList.add(
                    MarketEntity(market.market,market.korean_name,market.english_name,"","","")
            )
        }
        return returnList
    }

    private fun setTradePrice(marketList:List<MarketEntity>, tickerList:List<TickerEntity>):List<MarketEntity>{
        for (i in marketList.indices){
            marketList[i].trade_price= notDecimalFormat(tickerList[i].trade_price)
            marketList[i].low_price  = notDecimalFormat(tickerList[i].low_price)
            marketList[i].high_price = notDecimalFormat(tickerList[i].high_price)
        }
        return marketList
    }
}