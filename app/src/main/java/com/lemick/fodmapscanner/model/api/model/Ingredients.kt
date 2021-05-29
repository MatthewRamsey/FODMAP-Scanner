package com.lemick.fodmapscanner.model.api.model

import android.os.Parcelable

import com.fasterxml.jackson.annotation.JsonProperty


import kotlinx.parcelize.Parcelize

@Parcelize
data class Ingredients(

    @JsonProperty("id") var id: String?,
    @JsonProperty("rank") var rank: Long?,
    @JsonProperty("text") var text: String?,
    @JsonProperty("vegan") var vegan: String?,
    @JsonProperty("vegetarian") var vegetarian: String?,
    @JsonProperty("has_sub_ingredients") var hasSubIngredients: String?,
    @JsonProperty("percent_estimate") var percentEstimate: Double?,
    @JsonProperty("percent_max") var percentMax: Double?,
    @JsonProperty("percent_min") var percentMin: Double?,

    ) : Parcelable