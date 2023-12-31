package com.chairullatif.gamingfo.core.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import com.chairullatif.gamingfo.core.BuildConfig
import com.chairullatif.gamingfo.core.data.source.remote.network.ApiResponse
import com.chairullatif.gamingfo.core.data.source.remote.network.ApiService
import com.chairullatif.gamingfo.core.data.source.remote.response.GameResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class RemoteDataSource(private val apiService: ApiService) {

    @SuppressLint("CheckResult")
    fun getListGame(): Flowable<ApiResponse<List<GameResponse>>> {
        val resultData = PublishSubject.create<ApiResponse<List<GameResponse>>>()
        val key = BuildConfig.RAWG_KEY

        val client = apiService.getListGame(key)

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.results
                // below is code to handle empty data from API
                resultData.onNext(if (dataArray.isNotEmpty()) ApiResponse.Success(dataArray) else ApiResponse.Empty)
            }, { error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getDetailGame(idGame: Int): Flowable<GameResponse> {
        val resultData = PublishSubject.create<GameResponse>()
        val key = BuildConfig.RAWG_KEY

        val client = apiService.getDetailGame(idGame, key)

        client
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { gameResponse ->
                    resultData.onNext(gameResponse)
                }, { error ->
                    Log.e("RemoteDataSource", error.toString())
                })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
}