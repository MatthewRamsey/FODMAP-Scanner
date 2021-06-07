package com.lemick.fodmapscanner.business

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class IngredientParserTest {

    @InjectMocks
    lateinit var model: IngredientParser

    @Test
    fun _clearIngredientName_() {
        val actual = model.clearIngredientId("en:lactose-and_milk protein")
        assertEquals("name must be cleared", "lactose and milk protein", actual)
    }
}