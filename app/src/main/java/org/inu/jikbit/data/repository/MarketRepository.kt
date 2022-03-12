package org.inu.jikbit.data.repository

import org.inu.jikbit.data.model.Market

interface MarketRepository {
    fun getMarkets() : List<Market>
}