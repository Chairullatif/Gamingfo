package com.chairullatif.gamingfo.detail

import androidx.lifecycle.ViewModel
import com.chairullatif.gamingfo.core.domain.model.GameModel
import com.chairullatif.gamingfo.core.domain.usecase.GameUseCase

class DetailGameViewModel(private val gameUseCase: GameUseCase) : ViewModel() {
    fun setFavoriteGame(game: GameModel, newStatus: Boolean) =
        gameUseCase.setFavoriteGame(game, newStatus)
}