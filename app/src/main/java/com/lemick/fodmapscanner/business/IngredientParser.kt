package com.lemick.fodmapscanner.business

import com.lemick.fodmapscanner.model.api.model.Ingredient
import com.lemick.fodmapscanner.model.fodmap.FodmapLevel
import com.lemick.fodmapscanner.model.fodmap.IngredientFodmapResult

class IngredientParser(private val fodmapDbManager: FodmapDbManager) {

    // TODO remove, pas du parsing
    fun searchFodmapEntries(ingredients: List<Ingredient>): List<IngredientFodmapResult> {
        val prettifiedIngredients = prettifyIngredientList(ingredients)
        val mappedResults = prettifiedIngredients.map { searchFodmapEntry(it) }
        return mappedResults.sortedWith { a, b ->
            when {
                (a.fodmapEntry == null && b.fodmapEntry == null) -> 0
                (a.fodmapEntry == null) -> -1
                (b.fodmapEntry == null) -> 1
                else -> a.fodmapEntry.fodmap.compareTo(b.fodmapEntry.fodmap)
            }
        }.reversed()
    }

    // TODO remove, pas du parsing
    fun searchFodmapEntry(ingredient: Ingredient): IngredientFodmapResult {
        val clearedId = clearIngredientId(ingredient.id)
        val closestEntry = fodmapDbManager.searchClosestEntry(clearedId)
        return IngredientFodmapResult(ingredient, closestEntry)
    }

    fun clearIngredientId(ingredientText: String): String {
        val textSplitted = ingredientText.split(":")
        val language = textSplitted[0]

        // Trad Text if EN ?
        val clearedRawText = clearRawTextIngredient(textSplitted[1]);
        return clearedRawText
    }

    fun prettifyIngredientList(ingredients: List<Ingredient>): List<Ingredient> {
        return ingredients.map { capitalizeIngredientText(it) }
    }

    private fun clearRawTextIngredient(rawText: String): String {
        return rawText.replace("[_-]".toRegex(), " ")
    }

    private fun capitalizeIngredientText(ingredient: Ingredient): Ingredient {
        val currentText = ingredient.text;
        if (!currentText.isNullOrEmpty()) {
            ingredient.text = currentText[0].uppercase() + currentText.substring(1);
        }
        return ingredient;
    }
}