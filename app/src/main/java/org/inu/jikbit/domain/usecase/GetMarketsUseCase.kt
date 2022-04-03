package org.inu.jikbit.domain.usecase

import org.inu.jikbit.domain.model.MarketEntity
import org.inu.jikbit.domain.repository.MarketRepository

class GetMarketsUseCase(private val repository: MarketRepository) {

    suspend operator fun invoke(): List<MarketEntity> {
        return repository.getMarkets()
    }

}