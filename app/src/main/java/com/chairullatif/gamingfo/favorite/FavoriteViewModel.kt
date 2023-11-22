package com.chairullatif.gamingfo.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.chairullatif.gamingfo.core.domain.usecase.GameUseCase

class FavoriteViewModel(useCase: GameUseCase) : ViewModel() {
    val favoriteGames = useCase.getFavoriteGame().toLiveData()
}