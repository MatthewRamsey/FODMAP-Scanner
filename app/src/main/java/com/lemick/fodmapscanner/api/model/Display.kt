package com.lemick.example

import com.fasterxml.jackson.annotation.JsonProperty

   
data class Display (

   @JsonProperty("en") var en : String?

)