package com.chairullatif.gamingfo.core.domain.usecase

import com.chairullatif.gamingfo.core.data.Resource
import com.chairullatif.gamingfo.core.domain.model.GameModel
import com.chairullatif.gamingfo.core.domain.repository.IGameRepository
import io.reactivex.Flowable

class GameInteractor(private val gameRepository: IGameRepository): GameUseCase {
    override fun getAllGame(): Flowable<Resource<List<GameModel>>> = gameRepository.getAllGames()

    override fun getFavoriteGame(): Flowable<List<GameModel>> = gameRepository.getFavoriteGames()

    override fun setFavoriteGame(game: GameModel, state: Boolean) = gameRepository.setFavoriteGame(game, state)
    override fun getDetailGame(idGame: Int): Flowable<GameModel> = gameRepository.getDetailGame(idGame)
}