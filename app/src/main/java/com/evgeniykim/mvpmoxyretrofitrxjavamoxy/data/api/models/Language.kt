package com.evgeniykim.mvpmoxyretrofitrxjavamoxy.data.api.models

import com.google.gson.annotations.SerializedName

data class Language(
    @SerializedName("id")
    val id: Long,

    @SerializedName("iso_639_1")
    val iso: String?,

    @SerializedName("english_name")
    val englishName: String?,

    @SerializedName("name")
    val name: String?
)