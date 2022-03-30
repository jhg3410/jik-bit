package org.inu.jikbit.data.datasource.remote

import org.inu.jikbit.data.api.TickerHttpService
import org.inu.jikbit.data.model.TickerResponse

interface TickerRemoteDataSource {
    suspend fun getTickers(markets: String): List<TickerResponse>
}

class TickerRemoteDataSourceImpl(private val api: TickerHttpService) : TickerRemoteDataSource {
    override suspend fun getTickers(markets: String): List<TickerResponse> {
        val result = api.getTickers(markets)

        try {
            return result.body()!!
        } catch (e: Exception) {
            throw IllegalStateException("ERROR")
        }
    }
}