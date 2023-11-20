package com.chairullatif.gamingfo.core.domain.repository

import com.bumptech.glide.load.engine.Resource
import com.chairullatif.gamingfo.core.domain.model.GameModel
import io.reactivex.Flowable

interface IGameRepository {

    fun getAllGames(): Flowable<com.chairullatif.gamingfo.core.data.Resource<List<GameModel>>>

    fun getFavoriteGames(): Flowable<List<GameModel>>

    fun setFavoriteGame(game: GameModel, newState: Boolean)

}