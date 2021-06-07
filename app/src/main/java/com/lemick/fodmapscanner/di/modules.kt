package com.lemick.fodmapscanner.di

import android.content.Context
import androidx.room.Room
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.lemick.fodmapscanner.business.FodmapIngredientAnalyzer
import com.lemick.fodmapscanner.business.FodmapLocalRepository
import com.lemick.fodmapscanner.business.IngredientParser
import com.lemick.fodmapscanner.model.api.IOpenFoodFactsClient
import com.lemick.fodmapscanner.model.entity.AppDatabase
import com.lemick.fodmapscanner.view.MainViewModel
import com.lemick.fodmapscanner.view.ProductAnalysisViewModel
import com.lemick.fodmapscanner.view.ProductScannerViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

val mainModule = module {
    single { FodmapLocalRepository(get()) }
    single { IngredientParser() }
    single { FodmapIngredientAnalyzer(get(), get()) }

    single { provideObjectMapper() }
    single { provideOpenFoodFactsClient(get()) }
    single { provideRetrofit(get()) }
    single { provideDatabase(androidApplication()).ingredientScanDao() }

    viewModel { MainViewModel(get()) }
    viewModel { ProductScannerViewModel(get()) }
    viewModel { ProductAnalysisViewModel(null, get(), get()) }
}

fun provideObjectMapper(): ObjectMapper {
    return ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
}

fun provideOpenFoodFactsClient(retrofit: Retrofit): IOpenFoodFactsClient {
    return retrofit.create(IOpenFoodFactsClient::class.java);
}

fun provideRetrofit(objectMapper: ObjectMapper): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://world.openfoodfacts.org/api/v0/")
        .addConverterFactory(JacksonConverterFactory.create(objectMapper))
        .build()
}

fun provideDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, "fodmap-scanner.db").build()
}