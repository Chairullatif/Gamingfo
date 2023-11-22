package com.chairullatif.gamingfo.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game")
data class GameEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "gameId")
    var gameId: Int,

    @ColumnInfo(name = "name")
    var name             : String,

    @ColumnInfo(name = "released")
    var released         : String,

    @ColumnInfo(name = "backgroundImage")
    var backgroundImage  : String,

    @ColumnInfo(name = "rating")
    var rating           : Double,

    @ColumnInfo(name = "ratingsCount")
    var ratingsCount     : Int,

    @ColumnInfo(name = "suggestionsCount")
    var suggestionsCount : Int,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)
