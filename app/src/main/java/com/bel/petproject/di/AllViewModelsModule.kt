package com.bel.petproject.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.bel.petproject.ui.screens.detailsScreen.DetailsViewModel

val allViewModelsModule = module {
    viewModelOf(::DetailsViewModel)
}