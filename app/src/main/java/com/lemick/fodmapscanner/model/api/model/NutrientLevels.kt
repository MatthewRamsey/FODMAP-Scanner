package com.lemick.fodmapscanner.model.api.model
import android.os.Parcelable

import com.fasterxml.jackson.annotation.JsonProperty

   
import kotlinx.parcelize.Parcelize

@Parcelize
data class NutrientLevels (

   @JsonProperty("fat") var fat : String?,
   @JsonProperty("salt") var salt : String?,
   @JsonProperty("saturated-fat") var saturatedFat : String?,
   @JsonProperty("sugars") var sugars : String?

) : Parcelable