package com.chairullatif.gamingfo.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameResponse(
    @SerializedName("id"                 ) var id               : Int,
    @SerializedName("name"               ) var name             : String,
    @SerializedName("released"           ) var released         : String,
    @SerializedName("background_image"   ) var backgroundImage  : String,
    @SerializedName("rating"             ) var rating           : Double,
    @SerializedName("ratings_count"      ) var ratingsCount     : Int,
    @SerializedName("suggestions_count"  ) var suggestionsCount : Int,
    @SerializedName("description"        ) var description      : String,
)
