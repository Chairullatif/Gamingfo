package com.chairullatif.gamingfo.core.data.source.local

import com.chairullatif.gamingfo.core.data.source.local.entity.GameEntity
import com.chairullatif.gamingfo.core.data.source.local.room.GameDao
import io.reactivex.Flowable

class LocalDataSource private constructor(private val gameDao: GameDao) {
    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(gameDao: GameDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(gameDao)
            }
    }

    fun getAllGames(): Flowable<List<GameEntity>> = gameDao.getAllGame()

    fun getFavoriteGames(): Flowable<List<GameEntity>> = gameDao.getFavoriteGame()

    fun insertGame(gameList: List<GameEntity>) {
        gameDao.insertGame(gameList)
    }

    fun updateFavorite(game: GameEntity, newState: Boolean) {
        game.isFavorite = newState
        gameDao.updateFavoriteGame(game)
    }

}