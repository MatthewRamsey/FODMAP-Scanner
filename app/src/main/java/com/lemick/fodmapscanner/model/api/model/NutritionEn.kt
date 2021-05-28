package com.lemick.fodmapscanner.model.api.model

import com.fasterxml.jackson.annotation.JsonProperty

   
data class NutritionEn (

   @JsonProperty("geometry") var geometry : String?,
   @JsonProperty("imgid") var imgid : String?,
   @JsonProperty("normalize") var normalize : String?,
   @JsonProperty("rev") var rev : String?,
   //@JsonProperty("sizes") var sizes : Sizes?,
   @JsonProperty("white_magic") var whiteMagic : String?

)