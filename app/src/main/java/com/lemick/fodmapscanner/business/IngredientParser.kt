package com.lemick.fodmapscanner.business

class IngredientParser {

    fun clearIngredientName(ingredientText: String) : String{
        val textSplitted = ingredientText.split(":")
        val language = textSplitted[0]

        // Trad Text if EN ?
        val clearedRawText = clearRawTextIngredient(textSplitted[1]);
        return clearedRawText

    }

    private fun clearRawTextIngredient(rawText : String): String {
        return rawText.replace("[_-]", "");
    }
}