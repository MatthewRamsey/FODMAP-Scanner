package com.lemick.fodmapscanner.model.fodmap

import com.fasterxml.jackson.annotation.JsonCreator

data class FodmapEntry(
    val id: String = "",
    val name: String = "",
    val fodmap: FodmapLevel = FodmapLevel.UNKNOWN,
    val category: String = "",
    val details: FodmapDetails = FodmapDetails()
)

data class FodmapDetails(
    var oligos: Int = 0,
    var fructose: Int = 0,
    var polyols: Int = 0,
    var lactose: Int = 0
)

enum class FodmapLevel {
    HIGH, LOW, UNKNOWN
}


