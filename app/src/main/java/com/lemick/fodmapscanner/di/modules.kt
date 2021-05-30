package com.lemick.fodmapscanner.di

import com.lemick.fodmapscanner.business.FodmapDbManager
import com.lemick.fodmapscanner.business.IngredientParser
import org.koin.dsl.module

val mainModule = module {
    single { FodmapDbManager() }
    single { IngredientParser(get()) }
}