package com.lemick.example

import com.fasterxml.jackson.annotation.JsonProperty

   
data class AggregatedOrigins (

   @JsonProperty("origin") var origin : String?,
   @JsonProperty("percent") var percent : Int?

)