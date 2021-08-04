package com.evgeniykim.mvpmoxyretrofitrxjavamoxy.data.api.models

import com.google.gson.annotations.SerializedName

data class Genre(

    @SerializedName("id")
    val mId: Long,

    @SerializedName("name")
    val mName: String?
)

data class GenreData(

    @SerializedName("genres")
    val mGenreList: List<Genre>?
)
