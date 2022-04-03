package org.inu.jikbit.di

import org.inu.jikbit.domain.usecase.GetAccountsUseCase
import org.inu.jikbit.domain.usecase.GetMarketsUseCase
import org.koin.dsl.module

val useCaseModule = module{

    single { GetAccountsUseCase(get()) }

    single { GetMarketsUseCase(get()) }
}
