package com.mr.fodmapscanner.model.api.model
import android.os.Parcelable

import com.fasterxml.jackson.annotation.JsonProperty

   
import kotlinx.parcelize.Parcelize

@Parcelize
data class Packagings (

   @JsonProperty("material") var material : String?,
   @JsonProperty("shape") var shape : String?

) : Parcelable