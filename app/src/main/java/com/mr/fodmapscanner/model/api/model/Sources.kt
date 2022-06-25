package com.mr.fodmapscanner.model.api.model
import android.os.Parcelable

import com.fasterxml.jackson.annotation.JsonProperty

   
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sources (

   @JsonProperty("fields") var fields : List<String>?,
   @JsonProperty("id") var id : String?,
   @JsonProperty("images") var images : List<String>?,
   @JsonProperty("import_t") var importT : Int?,
   @JsonProperty("url") var url : String?

) : Parcelable