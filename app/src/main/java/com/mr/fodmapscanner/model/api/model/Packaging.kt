package com.mr.fodmapscanner.model.api.model
import android.os.Parcelable

import com.fasterxml.jackson.annotation.JsonProperty


import kotlinx.parcelize.Parcelize

@Parcelize
data class Packaging (

    @JsonProperty("non_recyclable_and_non_biodegradable_materials") var nonRecyclableAndNonBiodegradableMaterials : Int??,
    @JsonProperty("packagings") var packagings : List<Packagings?>??,
    @JsonProperty("score") var score : Int??,
    @JsonProperty("value") var value : Int??,
    @JsonProperty("warning") var warning : String?

) : Parcelable