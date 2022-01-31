package com.tecnica.domaincontroller.character.model

data class CharacterModel(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: String
)