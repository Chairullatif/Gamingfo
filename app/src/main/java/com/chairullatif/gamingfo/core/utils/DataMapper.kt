package com.chairullatif.gamingfo.core.utils

import com.chairullatif.gamingfo.core.data.source.local.entity.GameEntity
import com.chairullatif.gamingfo.core.data.source.remote.response.GameResponse
import com.chairullatif.gamingfo.core.domain.model.GameModel

object DataMapper {
    fun mapResponseToEntities(input: List<GameResponse>): List<GameEntity> =
        input.map {
            GameEntity(
                it.id,
                it.name,
                it.released,
                it.backgroundImage,
                it.rating,
                it.ratingsCount,
                it.suggestionsCount,
                false
            )
        }

    fun mapEntitiesToDomain(input: List<GameEntity>): List<GameModel> =
        input.map {
            GameModel(
                it.gameId,
                it.name,
                it.released,
                it.backgroundImage,
                it.rating,
                it.ratingsCount,
                it.suggestionsCount,
                it.isFavorite
            )
        }

    fun mapDomainToEntity(input: GameModel): GameEntity =
        GameEntity(
            input.gameId,
            input.name,
            input.released,
            input.backgroundImage,
            input.rating,
            input.ratingsCount,
            input.suggestionsCount,
            input.isFavorite
        )
}