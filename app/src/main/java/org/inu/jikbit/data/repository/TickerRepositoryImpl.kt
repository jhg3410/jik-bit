package org.inu.jikbit.data.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.inu.jikbit.data.datasource.remote.TickerRemoteDataSource
import org.inu.jikbit.data.mapper.TickerMapper
import org.inu.jikbit.domain.model.TickerEntity
import org.inu.jikbit.domain.repository.TickerRepository

class TickerRepositoryImpl(
    private val dataSource: TickerRemoteDataSource
) : TickerRepository {

    override suspend fun getTickers(markets: String): List<TickerEntity> {
        return withContext(Dispatchers.IO){
            TickerMapper.mapperToTicker(dataSource.getTickers(markets))
//        return dataSource.getTickers(markets).map{
//            TickerMapper.mapperToTicker(it)
//        }
        }
    }
}