package com.mr.fodmapscanner.di

import android.content.Context
import androidx.room.Room
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.mr.fodmapscanner.business.FodmapIngredientAnalyzer
import com.mr.fodmapscanner.business.FodmapLocalRepository
import com.mr.fodmapscanner.business.IngredientParser
import com.mr.fodmapscanner.model.api.IOpenFoodFactsClient
import com.mr.fodmapscanner.model.entity.AppDatabase
import com.mr.fodmapscanner.view.MainViewModel
import com.mr.fodmapscanner.view.ProductAnalysisViewModel
import com.mr.fodmapscanner.view.ProductScannerViewModel
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
    viewModel { ProductScannerViewModel(get(), get()) }
    viewModel { ProductAnalysisViewModel(null, get()) }
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
    return Room.databaseBuilder(context, AppDatabase::class.java, "fodmap-scanner.db").allowMainThreadQueries().build()
}