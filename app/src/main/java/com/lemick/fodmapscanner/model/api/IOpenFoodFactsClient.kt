package com.lemick.fodmapscanner.model.api

import com.lemick.fodmapscanner.model.api.model.ProductResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IOpenFoodFactsClient {

    @GET("product/{barcode}.json")
    fun findProduct(@Path("barcode") barcodeId: String?): Call<ProductResult>
}