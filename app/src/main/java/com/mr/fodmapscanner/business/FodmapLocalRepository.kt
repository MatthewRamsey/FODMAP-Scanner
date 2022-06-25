package com.mr.fodmapscanner.business

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.mr.fodmapscanner.model.fodmap.FodmapEntry
import org.apache.commons.text.similarity.LevenshteinDistance
import java.io.InputStream

class FodmapLocalRepository(private val objectMapper : ObjectMapper, private var fodmapDb: List<FodmapEntry> = arrayListOf()) {

    private val levenshteinDistance = LevenshteinDistance.getDefaultInstance()

    fun loadFodmapDbFromStream(dbInputStream: InputStream) {
        val fodmapListJson: String = dbInputStream.bufferedReader().use { it.readText() }
        val typeRef = object : TypeReference<ArrayList<FodmapEntry>>() {}
        fodmapDb = objectMapper.readValue(fodmapListJson, typeRef)
    }

    fun searchClosestEntry(ingredientName: String): FodmapEntry? {
        val matches = fodmapDb.filter { fodmapEntry -> fodmapEntry.name.contains(ingredientName, true) }

        if (matches.size == 1) {
            return matches.first()

        } else if (matches.size > 1) {
            return matches.sortedWith(compareBy { entry -> levenshteinDistance.apply(ingredientName, entry.name) }).first()
        }
        return null
    }

}