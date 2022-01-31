package com.tecnica.datarepository.character

import com.tecnica.datarepository.character.entities.MarvelCharacterResponse
import com.tecnica.domaincontroller.character.model.CharacterModel

class Mapper {

    companion object {
        fun mapToDomain(characterRepository: MarvelCharacterResponse): CharacterModel {
            return CharacterModel(
                id = characterRepository.id,
                name = characterRepository.name,
                description = characterRepository.description,
                thumbnail = characterRepository.thumbnailResponse.path.plus(".")
                    .plus(characterRepository.thumbnailResponse.extension)
            )
        }
    }
}