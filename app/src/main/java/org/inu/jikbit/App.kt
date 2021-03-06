package org.inu.jikbit

import android.app.Application
import org.inu.jikbit.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(dataSourceModule)
            modules(repositoryModule)
            modules(useCaseModule)
        }
    }
}