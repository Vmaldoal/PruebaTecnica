package com.tecnica.domaincontroller.character

import com.tecnica.domaincontroller.character.model.CharacterModel

interface GetCharacterList {
    suspend fun getCharacterList(page: Int): Result<List<CharacterModel>>
}