package com.lemick.fodmapscanner.model.api.model

import com.fasterxml.jackson.annotation.JsonProperty

   
data class Full (

   @JsonProperty("h") var h : Int?,
   @JsonProperty("w") var w : Int?

)