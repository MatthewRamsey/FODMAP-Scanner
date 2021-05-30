package com.lemick.fodmapscanner.business

import com.lemick.fodmapscanner.model.fodmap.FodmapEntry
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FodmapDbManagerTest {

    val model = FodmapDbManager(
        arrayListOf(
            FodmapEntry(name = "Palm sugar"),
            FodmapEntry(name = "Peanut butter"),
            FodmapEntry(name = "Butter")
        )
    )

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