package com.chairullatif.gamingfo

import android.app.Application
import com.chairullatif.gamingfo.core.di.databaseModule
import com.chairullatif.gamingfo.core.di.networkModule
import com.chairullatif.gamingfo.core.di.repositoryModule
import com.chairullatif.gamingfo.di.useCaseModule
import com.chairullatif.gamingfo.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}