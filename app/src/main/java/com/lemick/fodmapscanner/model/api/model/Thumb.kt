package com.lemick.fodmapscanner.model.api.model
import android.os.Parcelable

import com.fasterxml.jackson.annotation.JsonProperty

   
import kotlinx.parcelize.Parcelize

@Parcelize
data class Thumb (

   @JsonProperty("en") var en : String?

) : Parcelable