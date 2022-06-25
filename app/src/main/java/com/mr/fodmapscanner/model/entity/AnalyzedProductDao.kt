package com.mr.fodmapscanner.model.entity

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AnalyzedProductDao {

    @Insert
    fun insert(ingredientsScan: AnalyzedProduct)

    @Query("SELECT * FROM AnalyzedProduct ORDER BY id DESC")
    fun getAll(): LiveData<List<AnalyzedProduct>>
}