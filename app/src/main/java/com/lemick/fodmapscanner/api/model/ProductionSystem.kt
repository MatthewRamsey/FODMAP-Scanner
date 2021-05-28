package com.lemick.example

import com.fasterxml.jackson.annotation.JsonProperty

   
data class ProductionSystem (

   @JsonProperty("warning") var warning : String?

)