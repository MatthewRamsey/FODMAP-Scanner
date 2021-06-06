package com.lemick.fodmapscanner.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lemick.fodmapscanner.model.utils.Event
import com.lemick.fodmapscanner.model.api.IOpenFoodFactsClient
import com.lemick.fodmapscanner.model.api.model.Product
import com.lemick.fodmapscanner.model.entity.AnalyzedProduct
import com.lemick.fodmapscanner.model.entity.AnalyzedProductDao
import kotlinx.coroutines.launch

class MainViewModel(private val analyzedProductDao: AnalyzedProductDao) : ViewModel() {

    fun findAnalyzedProducts(): LiveData<List<AnalyzedProduct>> {
        return analyzedProductDao.getAll()
    }
}