package org.inu.jikbit.data.repository.ticker

import org.inu.jikbit.data.httpservice.TickerHttpService
import org.inu.jikbit.data.model.Ticker

class TickerRepositoryImpl(
    private val httpService: TickerHttpService
) : TickerRepository {

    override fun getTickers(markets: String): List<Ticker> {
        return httpService.getTickers(markets).execute().body()!!
    }
}