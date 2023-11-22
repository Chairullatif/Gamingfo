package com.chairullatif.gamingfo.core.domain.repository

import com.chairullatif.gamingfo.core.data.Resource
import com.chairullatif.gamingfo.core.domain.model.GameModel
import io.reactivex.Flowable

interface IGameRepository {

    fun getAllGames(): Flowable<Resource<List<GameModel>>>

    fun getFavoriteGames(): Flowable<List<GameModel>>

    fun setFavoriteGame(game: GameModel, newState: Boolean)

    fun getDetailGame(idGame: Int): Flowable<GameModel>

}