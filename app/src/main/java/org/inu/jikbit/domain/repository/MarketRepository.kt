package org.inu.jikbit.domain.repository

import org.inu.jikbit.domain.model.MarketEntity

interface MarketRepository {
    suspend fun getMarkets() : List<MarketEntity>
}