package com.lemick.fodmapscanner.model.api.model

import com.fasterxml.jackson.annotation.JsonProperty

   
data class Packagings (

   @JsonProperty("material") var material : String?,
   @JsonProperty("shape") var shape : String?

)