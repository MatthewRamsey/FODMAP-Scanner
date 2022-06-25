package com.mr.fodmapscanner.model.api.model
import android.os.Parcelable

import com.fasterxml.jackson.annotation.JsonProperty

   
import kotlinx.parcelize.Parcelize

@Parcelize
data class IngredientsEn (

   @JsonProperty("geometry") var geometry : String?,
   @JsonProperty("imgid") var imgid : String?,
   @JsonProperty("normalize") var normalize : String?,
   @JsonProperty("rev") var rev : String?,
   //@JsonProperty("sizes") var sizes : Sizes?,
   @JsonProperty("white_magic") var whiteMagic : String?

) : Parcelable