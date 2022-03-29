package org.inu.jikbit.domain.usecase

import org.inu.jikbit.data.model.TickerResponse
import org.inu.jikbit.domain.repository.TickerRepository

class GetTickersUseCase(private val repository: TickerRepository) {

    suspend fun invoke(markets:String) : List<TickerResponse>{
        return repository.getTickers(markets = markets)
    }
}