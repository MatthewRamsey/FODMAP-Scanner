package com.lemick.fodmapscanner.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lemick.fodmapscanner.model.api.IOpenFoodFactsClient
import com.lemick.fodmapscanner.model.api.model.Product
import com.lemick.fodmapscanner.model.api.model.ProductResult
import kotlinx.coroutines.launch

class ProductViewModel(private val openFoodFactsClient: IOpenFoodFactsClient) : ViewModel() {

    private val _productState = MutableLiveData<Product?>()
    val productState: LiveData<Product?>
        get() = _productState


    fun fetchProduct(barcode: String) {
        viewModelScope.launch() {
            try {
                val productResult = openFoodFactsClient.findProduct(barcode)
                _productState.value = productResult.product
            } catch (e: Throwable) {
                Log.e("APP", "Error when retrieving the product infos", e)
                _productState.value = null
            }
        }
    }
}