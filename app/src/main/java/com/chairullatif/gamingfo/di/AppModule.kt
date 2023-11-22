package com.chairullatif.gamingfo.di

import com.chairullatif.gamingfo.core.domain.usecase.GameInteractor
import com.chairullatif.gamingfo.core.domain.usecase.GameUseCase
import com.chairullatif.gamingfo.detail.DetailGameViewModel
import com.chairullatif.gamingfo.favorite.FavoriteViewModel
import com.chairullatif.gamingfo.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GameUseCase> { GameInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailGameViewModel(get()) }
}