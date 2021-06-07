package com.lemick.fodmapscanner.business

import com.lemick.fodmapscanner.model.api.model.Ingredient
import com.lemick.fodmapscanner.model.fodmap.AnalyzedIngredient

class FodmapIngredientAnalyzer(private val fodmapLocalRepository: FodmapLocalRepository,
                               private val ingredientParser: IngredientParser) {

    fun analyzeIngredients(ingredients: List<Ingredient>): List<AnalyzedIngredient> {
        return ingredients.map { analyzeIngredient(it) }
    }

    fun analyzeIngredient(ingredient: Ingredient): AnalyzedIngredient {
        val clearedId = ingredientParser.clearIngredientId(ingredient.id)
        val closestEntry = fodmapLocalRepository.searchClosestEntry(clearedId)
        val prettifiedIngredient = capitalizeIngredientText(ingredient)
        return AnalyzedIngredient(prettifiedIngredient, closestEntry)
    }


    private fun capitalizeIngredientText(ingredient: Ingredient): Ingredient {
        val ingredientCopy = ingredient.copy();
        val currentText = ingredientCopy.text;
        if (!currentText.isNullOrEmpty()) {
            ingredientCopy.text = currentText[0].uppercase() + currentText.substring(1);
        }
        return ingredientCopy;
    }
}