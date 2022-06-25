package com.mr.fodmapscanner.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.fodmapscanner.model.utils.Event
import com.mr.fodmapscanner.model.api.IOpenFoodFactsClient
import com.mr.fodmapscanner.model.api.model.Product
import com.mr.fodmapscanner.model.entity.AnalyzedProduct
import com.mr.fodmapscanner.model.entity.AnalyzedProductDao
import kotlinx.coroutines.launch

class MainViewModel(private val analyzedProductDao: AnalyzedProductDao) : ViewModel() {

    fun findAnalyzedProducts(): LiveData<List<AnalyzedProduct>> {
        return analyzedProductDao.getAll()
    }
}