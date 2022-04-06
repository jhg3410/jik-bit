package org.inu.jikbit.data.mapper

import org.inu.jikbit.data.model.TickerResponse
import org.inu.jikbit.domain.model.TickerEntity
import org.inu.jikbit.global.util.Span.notDecimalFormat

object TickerMapper {

    fun  mapperToTicker(tickerResponse: List<TickerResponse>) : List<TickerEntity>{
        val result = tickerResponse
        val returnList = mutableListOf<TickerEntity>()

        for (i in result.indices){
            returnList.add(TickerEntity(
                result[i].market,
                result[i].trade_price,
                result[i].high_price,
                result[i].low_price))
        }
        return returnList
    }
}
