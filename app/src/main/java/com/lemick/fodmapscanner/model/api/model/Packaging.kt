package com.lemick.fodmapscanner.model.api.model

import com.fasterxml.jackson.annotation.JsonProperty


data class Packaging (

    @JsonProperty("non_recyclable_and_non_biodegradable_materials") var nonRecyclableAndNonBiodegradableMaterials : Int??,
    @JsonProperty("packagings") var packagings : List<Packagings?>??,
    @JsonProperty("score") var score : Int??,
    @JsonProperty("value") var value : Int??,
    @JsonProperty("warning") var warning : String?

)