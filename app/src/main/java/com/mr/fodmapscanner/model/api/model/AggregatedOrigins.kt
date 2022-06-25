package com.mr.fodmapscanner.model.api.model
import android.os.Parcelable

import com.fasterxml.jackson.annotation.JsonProperty

   
import kotlinx.parcelize.Parcelize

@Parcelize
data class AggregatedOrigins (

   @JsonProperty("origin") var origin : String?,
   @JsonProperty("percent") var percent : Int?

) : Parcelable