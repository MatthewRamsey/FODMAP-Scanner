package com.lemick.example

import com.fasterxml.jackson.annotation.JsonProperty

   
data class Small (

   @JsonProperty("en") var en : String?

)