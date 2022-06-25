package com.mr.fodmapscanner.model.api.model
import android.os.Parcelable

import com.fasterxml.jackson.annotation.JsonProperty

   
import kotlinx.parcelize.Parcelize

@Parcelize
data class Full (

   @JsonProperty("h") var h : Int?,
   @JsonProperty("w") var w : Int?

) : Parcelable