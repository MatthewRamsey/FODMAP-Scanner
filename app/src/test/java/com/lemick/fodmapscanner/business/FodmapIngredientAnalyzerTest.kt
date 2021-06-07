package com.lemick.fodmapscanner.business

import com.lemick.fodmapscanner.MockitoKotlinHelper
import com.lemick.fodmapscanner.MockitoKotlinHelper.Companion.any
import com.lemick.fodmapscanner.model.api.model.Ingredient
import com.lemick.fodmapscanner.model.fodmap.FodmapEntry
import com.lemick.fodmapscanner.model.fodmap.FodmapLevel
import com.lemick.fodmapscanner.model.fodmap.AnalyzedIngredient
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FodmapIngredientAnalyzerTest {

    @InjectMocks
    lateinit var model: FodmapIngredientAnalyzer

    @Mock
    lateinit var fodmapLocalRepository: FodmapLocalRepository

    @Mock
    lateinit var ingredientParser: IngredientParser

    val ingredient_1 =  Ingredient(id = "butter", text = "butter")
    val ingredient_2 = Ingredient(id = "Vegetal syrup", text = "Vegetal syrup")

    val fodmapEntry_1 = FodmapEntry(name = "Butter", fodmap = FodmapLevel.LOW)

    @Test
    fun _analyzeIngredient_entry_found() {
        Mockito.`when`(ingredientParser.clearIngredientId(any())).thenAnswer { it.arguments[0] as String}
        Mockito.`when`(fodmapLocalRepository.searchClosestEntry(any())).thenReturn(fodmapEntry_1)
        val actual = model.analyzeIngredient(ingredient_1)
        val expected = AnalyzedIngredient(Ingredient(id = "butter", text = "Butter"), fodmapEntry_1)

        assertEquals("analyzed result is correct", expected, actual)
        assertEquals("ingredient name is capitalized", "Butter", actual.ingredient.text)
    }

    @Test
    fun _analyzeIngredient_null() {
        Mockito.`when`(ingredientParser.clearIngredientId(any())).thenAnswer { it.arguments[0] as String}
        Mockito.`when`(fodmapLocalRepository.searchClosestEntry(any())).thenReturn(null)
        val actual = model.analyzeIngredient(ingredient_2)
        val expected = AnalyzedIngredient(ingredient_2, null)

        assertEquals("analyzed result is correct", expected, actual)
        assertEquals("ingredient name is capitalized", "Vegetal syrup", actual.ingredient.text)
    }
}