package org.inu.jikbit.data.repository.ticker

import org.inu.jikbit.data.model.Ticker

interface TickerRepository {
    fun getTickers(markets:String): List<Ticker>
}