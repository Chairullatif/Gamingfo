package com.chairullatif.gamingfo.core.di

import android.content.Context
import com.chairullatif.gamingfo.core.data.GameRepository
import com.chairullatif.gamingfo.core.data.source.local.LocalDataSource
import com.chairullatif.gamingfo.core.data.source.local.room.GameDatabase
import com.chairullatif.gamingfo.core.data.source.remote.RemoteDataSource
import com.chairullatif.gamingfo.core.data.source.remote.network.ApiConfig
import com.chairullatif.gamingfo.core.domain.repository.IGameRepository
import com.chairullatif.gamingfo.core.domain.usecase.GameInteractor
import com.chairullatif.gamingfo.core.domain.usecase.GameUseCase
import com.chairullatif.gamingfo.core.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context): IGameRepository {
        val database = GameDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.gameDao())
        val appExecutors = AppExecutors()

        return GameRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideGameUseCase(context: Context): GameUseCase {
        val repository = provideRepository(context)
        return GameInteractor(repository)
    }
}
