package com.tecnica.domaincontroller.character

import com.tecnica.domaincontroller.character.model.CharacterModel
import com.tecnica.domaincontroller.character.repository.CharacterRepository

class GetCharacterListImpl(private val characterRepository: CharacterRepository): GetCharacterList {
    override suspend fun getCharacterList(page: Int): Result<List<CharacterModel>> =
        characterRepository.getCharacters(page)
}