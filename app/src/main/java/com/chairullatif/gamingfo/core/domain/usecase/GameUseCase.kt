package com.chairullatif.gamingfo.core.domain.usecase

import com.chairullatif.gamingfo.core.data.Resource
import com.chairullatif.gamingfo.core.domain.model.GameModel
import io.reactivex.Flowable

interface GameUseCase {
    fun getAllGame(): Flowable<Resource<List<GameModel>>>
    fun getFavoriteGame(): Flowable<List<GameModel>>
    fun setFavoriteGame(tourism: GameModel, state: Boolean)
}