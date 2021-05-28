package com.lemick.fodmapscanner.api

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object OpenFoodFactsClient {

    private const val BASE_URL = "https://world.openfoodfacts.org/api/v0/"

    private val objectMapper : ObjectMapper by lazy {
        ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    }
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .build()
    }

    val client: IOpenFoodFactsClient by lazy {
        retrofit.create(IOpenFoodFactsClient::class.java);
    }
}