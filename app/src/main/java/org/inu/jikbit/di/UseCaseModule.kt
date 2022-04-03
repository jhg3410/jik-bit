package org.inu.jikbit.di

import org.inu.jikbit.data.api.AccountHttpService
import org.inu.jikbit.data.api.TickerHttpService
import org.inu.jikbit.data.datasource.remote.AccountRemoteDataSourceImpl
import org.inu.jikbit.data.datasource.remote.MarketRemoteDataSourceImpl
import org.inu.jikbit.data.datasource.remote.TickerRemoteDataSource
import org.inu.jikbit.data.datasource.remote.TickerRemoteDataSourceImpl
import org.inu.jikbit.data.repository.AccountRepositoryImpl
import org.inu.jikbit.data.repository.MarketRepositoryImpl
//import org.inu.jikbit.data.repository.MarketRepositoryImpl
import org.inu.jikbit.data.repository.TickerRepositoryImpl
import org.inu.jikbit.domain.usecase.GetAccountsUseCase
import org.inu.jikbit.domain.usecase.GetMarketsUseCase
import org.inu.jikbit.domain.usecase.GetTickersUseCase
import org.koin.dsl.module

val UseCaseModule = module{
    single<AccountHttpService> {
        buildRetrofitService()
    }

    single<TickerHttpService> {
        buildRetrofitService()
    }

    single<GetAccountsUseCase> { GetAccountsUseCase(AccountRepositoryImpl(AccountRemoteDataSourceImpl(get()))) }
//    single { GetMarketsUseCase(MarketRepositoryImpl(get())) }
    single { GetTickersUseCase(TickerRepositoryImpl(TickerRemoteDataSourceImpl(get()))) }

    single<TickerRemoteDataSource> { TickerRemoteDataSourceImpl(get()) }

    single { GetMarketsUseCase(MarketRepositoryImpl(MarketRemoteDataSourceImpl(get()))) }
}
