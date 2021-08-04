package com.evgeniykim.mvpmoxyretrofitrxjavamoxy.data.api.models

import com.google.gson.annotations.SerializedName

data class Image(

    @SerializedName("aspect_ratio")
    val aspectRatio: Float,

    @SerializedName("file_path")
    val filePath: String?,

    @SerializedName("width")
    val width: Float,

    @SerializedName("height")
    val height: Float,

    @SerializedName("vote_average")
    val voteAverage: Float,

    @SerializedName("vote_count")
    val voteCount: Int,

    @SerializedName("iso_639_1")
    val iso639_1: String?
)

data class ImageData(

    @SerializedName("id")
    val id: Long,

    @SerializedName("backdrops")
    val backdropsList: List<ImageData>?,

    @SerializedName("posters")
    val postersList: List<ImageData>?
)
