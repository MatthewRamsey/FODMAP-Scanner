package com.lemick.fodmapscanner.model.api.model

import android.os.Parcelable

import com.fasterxml.jackson.annotation.JsonProperty


import kotlinx.parcelize.Parcelize

@Parcelize
data class Ingredient(
    @JsonProperty("id") var id: String = "",
    @JsonProperty("rank") var rank: Long? = null,
    @JsonProperty("text") var text: String? = null,
    @JsonProperty("vegan") var vegan: String? = null,
    @JsonProperty("vegetarian") var vegetarian: String? = null,
    @JsonProperty("has_sub_ingredients") var hasSubIngredients: String? = null,
    @JsonProperty("percent_estimate") var percentEstimate: Double? = null,
    @JsonProperty("percent_max") var percentMax: Double? = null,
    @JsonProperty("percent_min") var percentMin: Double? = null,
) : Parcelable