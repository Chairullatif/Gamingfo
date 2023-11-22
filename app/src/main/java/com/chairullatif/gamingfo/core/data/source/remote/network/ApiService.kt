package com.chairullatif.gamingfo.core.data.source.remote.network

import com.chairullatif.gamingfo.core.data.source.remote.response.GameResponse
import com.chairullatif.gamingfo.core.data.source.remote.response.ListGameResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    fun getListGame(
        @Query("key") key: String,
    ): Flowable<ListGameResponse>

    @GET("games/{id_game}")
    fun getDetailGame(
        @Path("id_game") idGame: Int,
        @Query("key") key: String,
    ): Flowable<GameResponse>
}