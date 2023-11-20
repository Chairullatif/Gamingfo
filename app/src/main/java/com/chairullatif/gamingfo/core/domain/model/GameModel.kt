package com.chairullatif.gamingfo.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameModel(
     val gameId: Int,
     val name: String,
     val released: String,
     val backgroundImage: String,
     val rating: Double,
     val ratingsCount: Int,
     val suggestionsCount: Int,
     val isFavorite: Boolean
) : Parcelable
