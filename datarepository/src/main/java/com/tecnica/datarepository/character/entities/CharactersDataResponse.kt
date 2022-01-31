package com.tecnica.datarepository.character.entities

import com.google.gson.annotations.SerializedName

data class CharactersDataResponse (
    @SerializedName("offset")
    val offset: Int,

    @SerializedName("limit")
    val limit: Int,

    @SerializedName("total")
    val total: Int,

    @SerializedName("count")
    val count: Int,

    @SerializedName("results")
    val results: List<MarvelCharacterResponse>
)