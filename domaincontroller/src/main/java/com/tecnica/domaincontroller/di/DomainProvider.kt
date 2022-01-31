package com.tecnica.domaincontroller.di

import com.tecnica.domaincontroller.character.GetCharacterById
import com.tecnica.domaincontroller.character.GetCharacterByIdImpl
import com.tecnica.domaincontroller.character.GetCharacterList
import com.tecnica.domaincontroller.character.GetCharacterListImpl
import org.koin.dsl.module

val domainModule = module {
    factory<GetCharacterById> { GetCharacterByIdImpl(get()) }
    factory<GetCharacterList> { GetCharacterListImpl(get()) }
}