package com.lemick.fodmapscanner.business

import com.lemick.fodmapscanner.model.api.model.Ingredient
import com.lemick.fodmapscanner.model.fodmap.IngredientFodmapResult

class FodmapIngredientMapper(private val fodmapLocalRepository: FodmapLocalRepository,
                             private val ingredientParser: IngredientParser) {

    fun searchFodmapEntries(ingredients: List<Ingredient>): List<IngredientFodmapResult> {
        val prettifiedIngredients = ingredients.map { capitalizeIngredientText(it) }
        return prettifiedIngredients
            .map { searchFodmapEntry(it) }
            .sortedWith { a, b ->
                when {
                    (a.fodmapEntry == null && b.fodmapEntry == null) -> 0
                    (a.fodmapEntry == null) -> -1
                    (b.fodmapEntry == null) -> 1
                    else -> a.fodmapEntry.fodmap.compareTo(b.fodmapEntry.fodmap)
                }
            }.reversed()
    }

    private fun searchFodmapEntry(ingredient: Ingredient): IngredientFodmapResult {
        val clearedId = ingredientParser.clearIngredientId(ingredient.id)
        val closestEntry = fodmapLocalRepository.searchClosestEntry(clearedId)
        return IngredientFodmapResult(ingredient, closestEntry)
    }


    private fun capitalizeIngredientText(ingredient: Ingredient): Ingredient {
        val currentText = ingredient.text;
        if (!currentText.isNullOrEmpty()) {
            ingredient.text = currentText[0].uppercase() + currentText.substring(1);
        }
        return ingredient;
    }
}