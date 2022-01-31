package com.tecnica.datarepository.character.entities

import com.google.gson.annotations.SerializedName

data class MarvelCharacterResponse (
    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("modified")
    val modified: String,

    @SerializedName("resourceUri")
    val resourceUri: String,

    @SerializedName("thumbnail")
    val thumbnailResponse: ThumbnailResponse
)