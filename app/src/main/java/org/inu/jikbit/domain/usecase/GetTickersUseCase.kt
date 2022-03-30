package org.inu.jikbit.domain.usecase

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.inu.jikbit.domain.model.TickerEntity
import org.inu.jikbit.domain.repository.TickerRepository

class GetTickersUseCase(private val repository: TickerRepository) { // UseCase 로 얘를 쓸 빠엔 getTotalUseCase 만드는 게 좋을 듯

    suspend fun invoke(markets: String): List<TickerEntity> {
        return withContext(Dispatchers.IO) {
            repository.getTickers(markets = markets)
        }
    }
}