package com.mr.fodmapscanner.model.fodmap

import com.mr.fodmapscanner.model.api.model.Ingredient

data class AnalyzedIngredient (
    val ingredient: Ingredient,
    val fodmapEntry: FodmapEntry?
)
