package org.inu.jikbit.data.repository

import org.inu.jikbit.data.datasource.remote.MarketRemoteDataSource
import org.inu.jikbit.data.mapper.MarketMapper
import org.inu.jikbit.domain.model.MarketEntity
import org.inu.jikbit.domain.repository.MarketRepository

class MarketRepositoryImpl(
    private val dataSource: MarketRemoteDataSource
): MarketRepository{

    override suspend fun getMarkets(): List<MarketEntity> {
        return MarketMapper.mapperToMarket(dataSource.getMarkets())
    }
}