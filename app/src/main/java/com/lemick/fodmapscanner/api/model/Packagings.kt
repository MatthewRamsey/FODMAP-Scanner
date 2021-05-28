package com.lemick.example

import com.fasterxml.jackson.annotation.JsonProperty

   
data class Packagings (

   @JsonProperty("material") var material : String?,
   @JsonProperty("shape") var shape : String?

)