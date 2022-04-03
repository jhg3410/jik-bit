package org.inu.jikbit.data.datasource.remote

import org.inu.jikbit.data.api.MarketHttpService
import org.inu.jikbit.data.model.MarketResponse

interface MarketRemoteDataSource {
    suspend fun getMarkets(): List<MarketResponse>
}

class MarketRemoteDataSourceImpl(private val api: MarketHttpService) : MarketRemoteDataSource {
    override suspend fun getMarkets(): List<MarketResponse> {
        val result = api.getMarkets()

        try {
            return result.body()!!.filter { it.market.startsWith("KRW-") }
        } catch (e: Exception) {
            throw IllegalStateException("ERROR")
        }
    }
}