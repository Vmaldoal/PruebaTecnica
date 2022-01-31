package com.tecnica.viewmodel.di

import com.tecnica.viewmodel.character.HeroeDetailViewModel
import com.tecnica.viewmodel.manager.ErrorManager
import com.tecnica.viewmodel.character.HeroesListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val viewModelModule = module {
    viewModel { HeroesListViewModel(get()) }
    viewModel { HeroeDetailViewModel(get()) }
    single { ErrorManager() }
}