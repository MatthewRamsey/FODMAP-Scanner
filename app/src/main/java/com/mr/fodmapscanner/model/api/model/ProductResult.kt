package com.mr.fodmapscanner.model.api.model
import android.os.Parcelable

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductResult (

   @JsonProperty("code") var code : String?,
   @JsonProperty("product") var product : Product?,
   @JsonProperty("status") var status : Int?,
   @JsonProperty("status_verbose") var statusVerbose : String?

) : Parcelable