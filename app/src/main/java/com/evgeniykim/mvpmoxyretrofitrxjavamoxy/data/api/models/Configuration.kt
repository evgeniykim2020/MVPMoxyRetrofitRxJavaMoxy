package com.evgeniykim.mvpmoxyretrofitrxjavamoxy.data.api.models

import com.google.gson.annotations.SerializedName

data class Configuration(

    @SerializedName("images")
    val images: ImageConfiguration?,

    @SerializedName("change_keys")
    val changeKeys: List<String>?
)

data class ImageConfiguration(

    @SerializedName("base_url")
    val baseUrl: String?,

    @SerializedName("secure_base_url")
    val secureBaseUrl: String?,

    @SerializedName("backdrop_sizes")
    val backDropSize: List<String>?,

    @SerializedName("logo_sizes")
    val logoSizes: List<String>?,

    @SerializedName("poster_sizes")
    val profilesSizes: List<String>?,

    @SerializedName("still_sizes")
    val stillSizes: List<String>
)
