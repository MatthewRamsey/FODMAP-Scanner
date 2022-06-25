package com.mr.fodmapscanner.business

import com.fasterxml.jackson.databind.ObjectMapper
import com.mr.fodmapscanner.model.fodmap.FodmapEntry
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FodmapLocalRepositoryTest {

    @Mock
    lateinit var objectMapper: ObjectMapper

    lateinit var model : FodmapLocalRepository

    @Before
    fun init() {
        model = FodmapLocalRepository(
            objectMapper,
            arrayListOf(
                FodmapEntry(name = "Palm sugar"),
                FodmapEntry(name = "Peanut butter"),
                FodmapEntry(name = "Butter")
            )
        )
    }

    @Test
    fun _searchClosestEntry_case_insensitive() {
        val actual = model.searchClosestEntry("palm sugar");
        assertEquals("the search is case insensitive", "Palm sugar", actual?.name)
    }

    @Test
    fun _searchClosestEntry_many_candidates() {
        val actual = model.searchClosestEntry("butter");
        assertEquals("the closest name is returned", "Butter", actual?.name)
    }

    @Test
    fun _searchClosestEntry_no_candidate() {
        val actual = model.searchClosestEntry("glucose");
        assertNull("no item is returned", actual)
    }
}