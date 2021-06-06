package com.lemick.fodmapscanner.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Blob

@Entity
data class AnalyzedProduct(

    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "product_barcode") val productBarcode: String,
    @ColumnInfo(name = "product_name") val productName: String,
    @ColumnInfo(name = "thumbnail_url") val thumbnailUrl: String,
)