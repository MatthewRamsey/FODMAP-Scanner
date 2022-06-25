package com.mr.fodmapscanner.business

class IngredientParser() {

    fun clearIngredientId(ingredientId: String): String {
        val textSplitted = ingredientId.split(":")
        val language = textSplitted[0]

        // Trad Text if EN ?
        val clearedRawText = clearRawTextIngredient(textSplitted[1]);
        return clearedRawText
    }

    private fun clearRawTextIngredient(rawText: String): String {
        return rawText.replace("[_-]".toRegex(), " ")
    }

}