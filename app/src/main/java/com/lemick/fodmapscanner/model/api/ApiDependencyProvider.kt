package com.lemick.fodmapscanner.model.api

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object ApiDependencyProvider {

    private const val BASE_URL = "https://world.openfoodfacts.org/api/v0/"

    val objectMapper : ObjectMapper by lazy {
        ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    val client: IOpenFoodFactsClient by lazy {
        retrofit.create(IOpenFoodFactsClient::class.java);
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .build()
    }
}