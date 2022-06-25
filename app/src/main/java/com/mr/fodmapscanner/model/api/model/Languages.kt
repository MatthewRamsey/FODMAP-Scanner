package com.mr.fodmapscanner.model.api.model
import android.os.Parcelable

import com.fasterxml.jackson.annotation.JsonProperty

   
import kotlinx.parcelize.Parcelize

@Parcelize
data class Languages (

   @JsonProperty("en:english") var enEnglish : Int?

) : Parcelable