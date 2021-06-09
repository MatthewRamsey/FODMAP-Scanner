package com.lemick.fodmapscanner.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.lemick.fodmapscanner.MockitoKotlinHelper
import com.lemick.fodmapscanner.business.FodmapIngredientAnalyzer
import com.lemick.fodmapscanner.model.api.model.Ingredient
import com.lemick.fodmapscanner.model.api.model.Product
import com.lemick.fodmapscanner.model.entity.AnalyzedProductDao
import com.lemick.fodmapscanner.model.fodmap.AnalyzedIngredient
import com.lemick.fodmapscanner.model.fodmap.FodmapEntry
import com.lemick.fodmapscanner.model.fodmap.FodmapLevel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ProductAnalysisViewModelTest {

    lateinit var model: ProductAnalysisViewModel

    @Mock
    lateinit var analyzedProductDao: AnalyzedProductDao

    @Mock
    lateinit var fodmapIngredientAnalyzer: FodmapIngredientAnalyzer

    @Mock
    lateinit var observer: Observer<List<AnalyzedIngredient>>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Captor
    lateinit var analyzedIngredientCaptor: ArgumentCaptor<ArrayList<AnalyzedIngredient>>

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    val product_1 = Product(id = "42", productName = "Wheat pasta")

    val analyzedIngredients = arrayListOf(
        AnalyzedIngredient(Ingredient(), FodmapEntry(fodmap = FodmapLevel.HIGH)),
        AnalyzedIngredient(Ingredient(), null),
        AnalyzedIngredient(Ingredient(), FodmapEntry(fodmap = FodmapLevel.LOW))
    )

    @Before
    fun before() {
        Dispatchers.setMain(testDispatcher)
        model = ProductAnalysisViewModel(testScope, fodmapIngredientAnalyzer)
    }

    @Test
    fun _startProductAnalysis() {
        Mockito.`when`(fodmapIngredientAnalyzer.analyzeIngredients(MockitoKotlinHelper.any())).thenReturn(analyzedIngredients)
        model.analyzedIngredientsState.observeForever(observer)
        model.startProductAnalysis(product_1)

        analyzedIngredientCaptor.run {
            verify(observer, times(1).description("data is passed to the observer")).onChanged(capture())

            val expected = arrayListOf(analyzedIngredients[2], analyzedIngredients[0], analyzedIngredients[1])
            assertEquals("the array is correctly sorted", expected, value)
        }
    }
}