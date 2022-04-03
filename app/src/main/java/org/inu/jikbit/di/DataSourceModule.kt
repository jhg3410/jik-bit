package org.inu.jikbit.di

import org.inu.jikbit.data.datasource.remote.*
import org.koin.dsl.module


val dataSourceModule = module {
    single<AccountRemoteDataSource> { AccountRemoteDataSourceImpl(get()) }
    single<MarketRemoteDataSource> { MarketRemoteDataSourceImpl(get()) }
    single<TickerRemoteDataSource> { TickerRemoteDataSourceImpl(get()) }
}