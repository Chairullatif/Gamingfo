package com.chairullatif.gamingfo.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.chairullatif.gamingfo.core.data.Resource
import com.chairullatif.gamingfo.core.domain.model.GameModel
import com.chairullatif.gamingfo.core.domain.usecase.GameUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel(private val gameUseCase: GameUseCase) : ViewModel() {

    private val _listGame = MutableLiveData<Resource<List<GameModel>>>()
    val listGame = _listGame

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    fun getListGame() {
        val disposable = gameUseCase.getAllGame()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {it ->
                _listGame.postValue(it)
            },{
                _listGame.postValue(null)
            } )
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}