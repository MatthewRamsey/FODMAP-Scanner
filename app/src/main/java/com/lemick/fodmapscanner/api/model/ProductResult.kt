package com.lemick.example

import com.fasterxml.jackson.annotation.JsonProperty

   
data class ProductResult (

   @JsonProperty("code") var code : String?,
   @JsonProperty("product") var product : Product?,
   @JsonProperty("status") var status : Int?,
   @JsonProperty("status_verbose") var statusVerbose : String?

)