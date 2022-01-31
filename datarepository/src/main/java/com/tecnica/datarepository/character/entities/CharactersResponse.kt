package com.tecnica.datarepository.character.entities

import com.google.gson.annotations.SerializedName

data class CharactersResponse (
    @SerializedName("code")
    val code: Int,

    @SerializedName("status")
    val status: String,

    @SerializedName("copyright")
    val copyright: String,

    @SerializedName("attributionText")
    val attributionText: String,

    @SerializedName("data")
    val dataResponse: CharactersDataResponse
)
