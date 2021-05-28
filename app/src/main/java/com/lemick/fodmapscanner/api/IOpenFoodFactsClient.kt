package com.lemick.fodmapscanner.api

import com.lemick.example.ProductResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IOpenFoodFactsClient {

    @GET("product/{barcode}.json")
    fun findProduct(@Path("barcode") barcodeId: String?): Call<ProductResult>
}