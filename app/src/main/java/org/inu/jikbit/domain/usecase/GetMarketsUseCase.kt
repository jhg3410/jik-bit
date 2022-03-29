package org.inu.jikbit.domain.usecase

import org.inu.jikbit.data.model.MarketResponse
import org.inu.jikbit.domain.repository.MarketRepository

class GetMarketsUseCase(private val repository: MarketRepository) {

    suspend fun invoke(): List<MarketResponse>{
        return repository.getMarkets()
    }

}