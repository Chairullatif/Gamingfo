package com.chairullatif.gamingfo.core.data.source.local

import com.chairullatif.gamingfo.core.data.source.local.entity.GameEntity
import com.chairullatif.gamingfo.core.data.source.local.room.GameDao
import io.reactivex.Completable
import io.reactivex.Flowable

class LocalDataSource(private val gameDao: GameDao) {

    fun getAllGames(): Flowable<List<GameEntity>> = gameDao.getAllGame()

    fun getFavoriteGames(): Flowable<List<GameEntity>> = gameDao.getFavoriteGame()

    fun insertGame(gameList: List<GameEntity>): Completable {
        return gameDao.insertGame(gameList)
    }

    fun updateFavorite(game: GameEntity, newState: Boolean) {
        game.isFavorite = newState
        gameDao.updateFavoriteGame(game)
    }

}