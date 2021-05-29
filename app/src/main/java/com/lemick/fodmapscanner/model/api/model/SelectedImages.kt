package com.lemick.fodmapscanner.model.api.model
import android.os.Parcelable

import com.fasterxml.jackson.annotation.JsonProperty


import kotlinx.parcelize.Parcelize

@Parcelize
data class SelectedImages (

    @JsonProperty("front") var front : Front?,
    @JsonProperty("ingredients") var ingredients : IngredientsImage?,
    @JsonProperty("nutrition") var nutrition : Nutrition?

) : Parcelable