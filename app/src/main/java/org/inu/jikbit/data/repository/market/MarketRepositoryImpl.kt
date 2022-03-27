package org.inu.jikbit.data.repository.market

import org.inu.jikbit.data.api.MarketHttpService
import org.inu.jikbit.data.model.Market

class MarketRepositoryImpl(
    private val httpService: MarketHttpService
) : MarketRepository {

    override fun getMarkets(): List<Market> {
        val tmpList = mutableListOf<Market>()
        httpService.getMarkets().execute().body()!!.forEach {
            if (it.market.contains("KRW")){
                tmpList.add(it)
            }
        }
        return tmpList
    }
}