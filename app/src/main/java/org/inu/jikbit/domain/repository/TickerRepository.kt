package org.inu.jikbit.domain.repository

import org.inu.jikbit.data.model.TickerResponse

interface TickerRepository {
    suspend fun getTickers(markets:String): List<TickerResponse>
}