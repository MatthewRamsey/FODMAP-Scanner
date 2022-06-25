package com.mr.fodmapscanner.model.api

import com.mr.fodmapscanner.model.api.model.ProductResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IOpenFoodFactsClient {

    @GET("product/{barcode}.json")
    suspend fun findProduct(@Path("barcode") barcodeId: String?): ProductResult
}