package com.mr.fodmapscanner.model.entity

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AnalyzedProduct::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ingredientScanDao(): AnalyzedProductDao
}