package com.lemick.fodmapscanner.model.fodmap

data class FodmapEntry(
    val id: String = "",
    val name: String = "",
    val fodmap: String = "",
    val category: String = "",
    val details: FodmapDetails = FodmapDetails()
)

data class FodmapDetails(
    var oligos: Int = 0,
    var fructose: Int = 0,
    var polyols: Int = 0,
    var lactose: Int = 0
)


