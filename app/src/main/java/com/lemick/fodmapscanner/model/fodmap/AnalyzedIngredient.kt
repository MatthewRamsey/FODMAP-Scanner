package com.lemick.fodmapscanner.model.fodmap

import com.lemick.fodmapscanner.model.api.model.Ingredient

data class AnalyzedIngredient (
    val ingredient: Ingredient,
    val fodmapEntry: FodmapEntry?
)
