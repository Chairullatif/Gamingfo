package com.chairullatif.gamingfo.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chairullatif.gamingfo.core.di.Injection
import com.chairullatif.gamingfo.core.domain.usecase.GameUseCase
import com.chairullatif.gamingfo.detail.DetailGameViewModel
import com.chairullatif.gamingfo.home.HomeViewModel

class ViewModelFactory private constructor(private val gameUseCase: GameUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideGameUseCase(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(gameUseCase) as T
            }
//            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
//                FavoriteViewModel(gameUseCase) as T
//            }
            modelClass.isAssignableFrom(DetailGameViewModel::class.java) -> {
                DetailGameViewModel(gameUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}