package com.lemick.fodmapscanner.business

import com.lemick.fodmapscanner.model.api.model.Ingredient
import com.lemick.fodmapscanner.model.fodmap.FodmapEntry

class IngredientParser(private val fodmapDbManager: FodmapDbManager) {

    fun searchFodmapEntries(ingredients: List<Ingredient>): List<Pair<Ingredient, FodmapEntry?>> {
        return ingredients.map { searchFodmapEntry(it) }
    }

    fun searchFodmapEntry(ingredient: Ingredient): Pair<Ingredient, FodmapEntry?> {
        val clearedId = clearIngredientId(ingredient.id)
        val closestEntry = fodmapDbManager.searchClosestEntry(clearedId)
        return Pair(ingredient, closestEntry)
    }

    fun clearIngredientId(ingredientText: String): String {
        val textSplitted = ingredientText.split(":")
        val language = textSplitted[0]

        // Trad Text if EN ?
        val clearedRawText = clearRawTextIngredient(textSplitted[1]);
        return clearedRawText
    }

    private fun clearRawTextIngredient(rawText: String): String {
        return rawText.replace("[_-]".toRegex(), " ");
    }
}