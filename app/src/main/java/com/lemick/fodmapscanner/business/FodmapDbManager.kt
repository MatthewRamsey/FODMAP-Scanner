package com.lemick.fodmapscanner.business

import com.fasterxml.jackson.core.type.TypeReference
import com.lemick.fodmapscanner.model.api.ApiDependencyProvider
import com.lemick.fodmapscanner.model.fodmap.FodmapEntry
import org.apache.commons.text.similarity.LevenshteinDistance
import java.io.InputStream

class FodmapDbManager(private var fodmapDb: List<FodmapEntry> = arrayListOf()) {

    private val levenshteinDistance = LevenshteinDistance.getDefaultInstance()

    fun loadFodmapDbFromStream(dbInputStream: InputStream) {
        val fodmapListJson: String = dbInputStream.bufferedReader().use { it.readText() }
        val typeRef = object : TypeReference<ArrayList<FodmapEntry>>() {}
        fodmapDb = ApiDependencyProvider.objectMapper.readValue(fodmapListJson, typeRef)
    }

    fun searchClosestEntry(text: String): FodmapEntry? {
        val matches = fodmapDb.filter { fodmapEntry -> fodmapEntry.name.contains(text, true) }

        if (matches.size == 1) {
            return matches.first()

        } else if (matches.size > 1) {
            return matches.sortedWith(compareBy { entry ->
                levenshteinDistance.apply(
                    text,
                    entry.name
                )
            }).first()
        }
        return null
    }

}