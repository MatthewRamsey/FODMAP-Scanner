package com.lemick.fodmapscanner.model.api.model
import android.os.Parcelable

import com.fasterxml.jackson.annotation.JsonProperty

   
import kotlinx.parcelize.Parcelize

@Parcelize
data class Missing (

   @JsonProperty("agb_category") var agbCategory : Int?,
   @JsonProperty("labels") var labels : Int?,
   @JsonProperty("packagings") var packagings : Int?

) : Parcelable