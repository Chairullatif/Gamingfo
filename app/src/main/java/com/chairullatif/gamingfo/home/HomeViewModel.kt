package com.chairullatif.gamingfo.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.chairullatif.gamingfo.core.domain.usecase.GameUseCase

class HomeViewModel(gameUseCase: GameUseCase) : ViewModel() {
    val listGame = gameUseCase.getAllGame().toLiveData()
}