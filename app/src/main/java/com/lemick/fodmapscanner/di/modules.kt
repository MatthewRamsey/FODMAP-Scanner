package com.lemick.fodmapscanner.di

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.lemick.fodmapscanner.business.FodmapIngredientMapper
import com.lemick.fodmapscanner.business.FodmapLocalRepository
import com.lemick.fodmapscanner.business.IngredientParser
import com.lemick.fodmapscanner.model.api.IOpenFoodFactsClient
import com.lemick.fodmapscanner.view.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

val mainModule = module {
    single { FodmapLocalRepository(get()) }
    single { IngredientParser() }
    single { FodmapIngredientMapper(get(), get()) }

    single { provideObjectMapper() }
    single { provideOpenFoodFactsClient(get()) }
    single { provideRetrofit(get()) }

    viewModel { ProductViewModel(get()) }
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