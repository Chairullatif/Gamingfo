package com.chairullatif.gamingfo.core.data

import com.chairullatif.gamingfo.core.data.source.local.LocalDataSource
import com.chairullatif.gamingfo.core.data.source.remote.RemoteDataSource
import com.chairullatif.gamingfo.core.data.source.remote.network.ApiResponse
import com.chairullatif.gamingfo.core.data.source.remote.response.GameResponse
import com.chairullatif.gamingfo.core.domain.model.GameModel
import com.chairullatif.gamingfo.core.domain.repository.IGameRepository
import com.chairullatif.gamingfo.core.utils.AppExecutors
import com.chairullatif.gamingfo.core.utils.DataMapper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GameRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IGameRepository {

    override fun getAllGames(): Flowable<com.chairullatif.gamingfo.core.data.Resource<List<GameModel>>> =
        object : com.chairullatif.gamingfo.core.data.NetworkBoundResource<List<GameModel>, List<GameResponse>>() {
            override fun loadFromDB(): Flowable<List<GameModel>> {
                return localDataSource.getAllGames().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun createCall(): Flowable<ApiResponse<List<GameResponse>>> {
                return remoteDataSource.getListGame()
            }

            override fun saveCallResult(data: List<GameResponse>) {
                val gameList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertGame(gameList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }

            override fun shouldFetch(data: List<GameModel>?): Boolean {
                return data.isNullOrEmpty()
            }

        }.asFlowable()

    override fun getFavoriteGames(): Flowable<List<GameModel>> {
        return localDataSource.getFavoriteGames().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteGame(game: GameModel, newState: Boolean) {
        val gameEntity = DataMapper.mapDomainToEntity(game)
        appExecutors.diskIO().execute { localDataSource.updateFavorite(gameEntity, newState) }
    }

    override fun getDetailGame(idGame: Int): Flowable<GameModel> {
        return remoteDataSource.getDetailGame(idGame).map { DataMapper.mapResponseToDomain(it)}
    }
}