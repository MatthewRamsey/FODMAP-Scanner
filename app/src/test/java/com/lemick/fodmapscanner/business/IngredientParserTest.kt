package com.lemick.fodmapscanner.business

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class IngredientParserTest {

    @Mock
    lateinit var model : IngredientParser

    @Test
    fun _test_() {
        Mockito.`when`(model.clearIngredientName("test")).thenReturn("test")
        assertEquals("test", model.clearIngredientName("test"))
    }
}