package org.inu.jikbit.domain.repository

import org.inu.jikbit.data.model.MarketResponse

interface MarketRepository {
    suspend fun getMarkets() : List<MarketResponse>
}