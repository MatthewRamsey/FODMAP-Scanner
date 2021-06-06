package com.lemick.fodmapscanner.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lemick.fodmapscanner.business.FodmapIngredientMapper
import com.lemick.fodmapscanner.model.api.model.Product
import com.lemick.fodmapscanner.model.entity.AnalyzedProduct
import com.lemick.fodmapscanner.model.entity.AnalyzedProductDao
import com.lemick.fodmapscanner.model.fodmap.AnalyzedIngredient
import kotlinx.coroutines.launch

class ProductAnalysisViewModel(private val analyzedProductDao: AnalyzedProductDao, private val fodmapIngredientMapper: FodmapIngredientMapper) : ViewModel() {

    private val _analyzedIngredientsState = MutableLiveData<List<AnalyzedIngredient>>()
    val analyzedIngredientsState: LiveData<List<AnalyzedIngredient>>
        get() = _analyzedIngredientsState

    fun analyzeProduct(product: Product) {
        viewModelScope.launch() {
            _analyzedIngredientsState.value = fodmapIngredientMapper.searchFodmapEntries(product.ingredients)

            val analyzedProduct = AnalyzedProduct(productBarcode = product.id, productName = product.productName, thumbnailUrl = product.imageThumbUrl)
            analyzedProductDao.insert(analyzedProduct)
        }
    }
}