package org.inu.jikbit.data.repository

import org.inu.jikbit.data.httpservice.MarketHttpService
import org.inu.jikbit.data.model.Market

class MarketRepositoryImpl(
    private val httpService: MarketHttpService
) : MarketRepository{

    override fun getMarkets(): List<Market> {
        return httpService.getMarkets().execute().body()!!
    }
}