package com.chairullatif.gamingfo.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListGameResponse(
    @SerializedName("count"                ) var count               : Int?               = null,
    @SerializedName("next"                 ) var next                : String?            = null,
    @SerializedName("previous"             ) var previous            : String?            = null,
    @SerializedName("results"              ) var results             : ArrayList<GameResponse> = arrayListOf(),
    @SerializedName("seo_title"            ) var seoTitle            : String?            = null,
    @SerializedName("seo_description"      ) var seoDescription      : String?            = null,
    @SerializedName("seo_keywords"         ) var seoKeywords         : String?            = null,
    @SerializedName("seo_h1"               ) var seoH1               : String?            = null,
    @SerializedName("noindex"              ) var noindex             : Boolean?           = null,
    @SerializedName("nofollow"             ) var nofollow            : Boolean?           = null,
    @SerializedName("description"          ) var description         : String?            = null
)
