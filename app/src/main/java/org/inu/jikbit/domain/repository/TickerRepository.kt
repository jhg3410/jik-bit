package org.inu.jikbit.domain.repository

import org.inu.jikbit.domain.model.TickerEntity

interface TickerRepository {
    suspend fun getTickers(markets: String): List<TickerEntity>
}