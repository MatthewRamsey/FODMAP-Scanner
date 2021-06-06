package com.lemick.fodmapscanner.business

import com.lemick.fodmapscanner.model.api.model.Ingredient
import com.lemick.fodmapscanner.model.fodmap.FodmapEntry
import com.lemick.fodmapscanner.model.fodmap.FodmapLevel
import com.lemick.fodmapscanner.model.fodmap.AnalyzedIngredient
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FodmapIngredientMapperTest {

    @InjectMocks
    lateinit var model: FodmapIngredientMapper

    @Mock
    lateinit var fodmapLocalRepository: FodmapLocalRepository

    @Mock
    lateinit var ingredientParser: IngredientParser

    val mockData = arrayListOf(
        Pair(
            Ingredient(id = "en:vegetal-syrup", text = "Vegetal syrup"),
            null
        ),
        Pair(
            Ingredient(id = "en:butter", text = "butter"),
            FodmapEntry(name = "Butter", fodmap = FodmapLevel.LOW)
        ),
        Pair(
            Ingredient(id = "en:wheat-flour", text = "wheat flour"),
            FodmapEntry(name = "Wheat flour", fodmap = FodmapLevel.HIGH)
        ),
    )

    @Before
    fun init() {
        for (mockPair in mockData) {
            Mockito.`when`(ingredientParser.clearIngredientId(mockPair.first.id)).thenReturn(mockPair.first.id)
            Mockito.`when`(fodmapLocalRepository.searchClosestEntry(mockPair.first.id)).thenReturn(mockPair.second)
        }
    }

    @Test
    @Ignore
    fun _searchFodmapEntries() {
        val ingredients = mockData.map { it.first }
        val actual = model.searchFodmapEntries(ingredients)
        val expected = arrayListOf(
            AnalyzedIngredient(mockData[1].first, mockData[1].second),
            AnalyzedIngredient(mockData[2].first, mockData[2].second),
            AnalyzedIngredient(mockData[3].first, mockData[3].second)
        )
    }
}