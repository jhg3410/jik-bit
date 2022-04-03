package org.inu.jikbit.di

import org.inu.jikbit.data.api.AccountHttpService
import org.inu.jikbit.data.api.MarketHttpService
import org.inu.jikbit.data.api.TickerHttpService
import org.inu.jikbit.data.repository.AccountRepositoryImpl
import org.inu.jikbit.data.repository.MarketRepositoryImpl
import org.inu.jikbit.data.repository.TickerRepositoryImpl
import org.inu.jikbit.di.factory.RetrofitServiceFactory
import org.inu.jikbit.domain.repository.AccountRepository
import org.inu.jikbit.domain.repository.MarketRepository
import org.inu.jikbit.domain.repository.TickerRepository
import org.koin.dsl.module

val repositoryModule = module{
    single<AccountHttpService> {
        RetrofitServiceFactory.create()
    }
    single<MarketHttpService> {
        RetrofitServiceFactory.create()
    }

    single<TickerHttpService> {
        RetrofitServiceFactory.create()
    }

    single<AccountRepository> {
        AccountRepositoryImpl(get())
    }

    single<MarketRepository> {
        MarketRepositoryImpl(get())
    }

    single<TickerRepository> {
        TickerRepositoryImpl(get())
    }

}