package com.lemick.fodmapscanner.model.fodmap

import com.lemick.fodmapscanner.model.api.model.Ingredient

data class IngredientFodmapResult (
    val ingredient: Ingredient,
    val fodmapEntry: FodmapEntry?
)
